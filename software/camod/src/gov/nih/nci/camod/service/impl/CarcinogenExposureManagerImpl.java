/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * @author pandyas
 * 
 * $Id: CarcinogenExposureManagerImpl.java,v 1.11 2008-08-15 18:23:01 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2008/08/12 19:49:16  pandyas
 * Fixed #12825  	induced mutation entries need to be flagged (requires OM change) and searches for induced mutation and carcinogenic interventions need to be fixed
 *
 * Note:  All CE and IM models now set a flag in Environmental_Factor object
 *
 * Revision 1.9  2007/10/31 19:01:53  pandyas
 * Fixed #8355 	Add comments field to every submission page
 * Fixed #8188 	Rename UnctrlVocab items to text entries
 *
 * Revision 1.8  2007/09/14 13:56:05  pandyas
 * Cleaned up name for class
 *
 * Revision 1.7  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.6  2006/09/22 18:53:40  pandyas
 * cleaned up code for mail
 *
 * Revision 1.5  2006/08/17 17:48:47  pandyas
 * Defect# 410: Externalize properties files - Code Changes to send mail method
 *
 * Revision 1.4  2006/05/24 15:26:57  pandyas
 * Cleaned up sendEmail method
 *
 * Revision 1.3  2006/05/23 15:11:27  pandyas
 * fixed delete - still references Therapy instead of CE
 *
 * Revision 1.2  2006/05/04 14:27:20  pandyas
 * Moved e-mail code into separate method
 *
 * Revision 1.1  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.domain.Treatment;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.*;
import gov.nih.nci.camod.webapp.form.cibase.AdministrationData;
import gov.nih.nci.camod.webapp.form.cibase.AgeGenderData;
import gov.nih.nci.camod.webapp.form.cibase.DoseData;
import gov.nih.nci.camod.webapp.form.cibase.NameData;
import gov.nih.nci.camod.webapp.form.cibase.TreatmentData;

public class CarcinogenExposureManagerImpl extends BaseManager implements CarcinogenExposureManager
{
    /**
     * Get a specific CarcinogenExposure by id
     * 
     * @param id
     *            the unique id for a CarcinogenExposure
     * 
     * @return the matching CarcinogenExposure object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public CarcinogenExposure get(String id) throws Exception
    {
        log.trace("In CarcinogenExposureManagerImpl.get");
        return (CarcinogenExposure) super.get(id, CarcinogenExposure.class);
    }

    /**
     * Remove a specific CarcinogenExposure by id
     * 
     * @param id
     *            the unique id for a CarcinogenExposure
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        log.debug("In CarcinogenExposureManagerImpl.remove");

        CarcinogenExposure theCarcinogenExposure = get(id);

        inAnimalModel.getCarcinogenExposureCollection().remove(theCarcinogenExposure);
        super.save(inAnimalModel);
    }

    /**
     * Save CarcinogenExposure
     * 
     * @param CarcinogenExposure
     *            the CarcinogenExposure to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(CarcinogenExposure carcinogenExposure) throws Exception
    {
        log.debug("In CarcinogenExposureManagerImpl.save");
        super.save(carcinogenExposure);
    }


    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inChemicalDrugData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a CarcinogenExposure
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
                                     ChemicalDrugData inChemicalDrugData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();

        populateName(inAnimalModel, inChemicalDrugData, theCarcinogenExposure, "Chemical / Drug");
        populateInducedMutationTrigger(theCarcinogenExposure);
        populateAgeGender(inChemicalDrugData, theCarcinogenExposure);
        populateTreatment(inChemicalDrugData, theCarcinogenExposure);
        populateDose(inChemicalDrugData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inChemicalDrugData, theCarcinogenExposure);

        populateChemicalDrug(inChemicalDrugData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }


    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inChemicalDrugData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inCarcinogenExposure
     *            the CarcinogenExposure object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
                       ChemicalDrugData inChemicalDrugData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inChemicalDrugData, inCarcinogenExposure, "Chemical / Drug");
        populateAgeGender(inChemicalDrugData, inCarcinogenExposure);
        populateTreatment(inChemicalDrugData, inCarcinogenExposure);
        populateDose(inChemicalDrugData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inChemicalDrugData, inCarcinogenExposure);

        populateChemicalDrug(inChemicalDrugData, inCarcinogenExposure);
        save(inCarcinogenExposure);
    }


    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inEnvironmentalFactorData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a therapy
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
                                     EnvironmentalFactorData inEnvironmentalFactorData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();

        populateName(inAnimalModel, inEnvironmentalFactorData, theCarcinogenExposure, "Environment");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateAgeGender(inEnvironmentalFactorData, theCarcinogenExposure);
        populateTreatment(inEnvironmentalFactorData, theCarcinogenExposure);
        populateDose(inEnvironmentalFactorData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inEnvironmentalFactorData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inEnvironmentalFactorData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inCarcinogenExposure
     *            the CarcinogenExposure object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
                       EnvironmentalFactorData inEnvironmentalFactorData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inEnvironmentalFactorData, inCarcinogenExposure, "Environment");
        populateAgeGender(inEnvironmentalFactorData, inCarcinogenExposure);
        populateTreatment(inEnvironmentalFactorData, inCarcinogenExposure);
        populateDose(inEnvironmentalFactorData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inEnvironmentalFactorData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inGrowthFactorData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a therapy
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
                                     GrowthFactorData inGrowthFactorData)
    {
        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inGrowthFactorData, theCarcinogenExposure, "Growth Factor");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateAgeGender(inGrowthFactorData, theCarcinogenExposure);
        populateTreatment(inGrowthFactorData, theCarcinogenExposure);
        populateDose(inGrowthFactorData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inGrowthFactorData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inGrowthFactorData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
                       GrowthFactorData inGrowthFactorData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inGrowthFactorData, inCarcinogenExposure, "Growth Factor");
        populateAgeGender(inGrowthFactorData, inCarcinogenExposure);
        populateTreatment(inGrowthFactorData, inCarcinogenExposure);
        populateDose(inGrowthFactorData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inGrowthFactorData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inHormoneData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a therapy
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
                                     HormoneData inHormoneData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inHormoneData, theCarcinogenExposure, "Hormone");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateAgeGender(inHormoneData, theCarcinogenExposure);
        populateTreatment(inHormoneData, theCarcinogenExposure);
        populateDose(inHormoneData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inHormoneData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inHormoneData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inCarcinogenExposure
     *            the CarcinogenExposure object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
                       HormoneData inHormoneData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inHormoneData, inCarcinogenExposure, "Hormone");
        populateAgeGender(inHormoneData, inCarcinogenExposure);
        populateTreatment(inHormoneData, inCarcinogenExposure);
        populateDose(inHormoneData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inHormoneData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inNutritionalFactorData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a CarcinogenExposure
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
                                     NutritionalFactorData inNutritionalFactorData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inNutritionalFactorData, theCarcinogenExposure, "Nutrition");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateTreatment(inNutritionalFactorData, theCarcinogenExposure);
        populateAgeGender(inNutritionalFactorData, theCarcinogenExposure);
        populateDose(inNutritionalFactorData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inNutritionalFactorData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inTherapy
     *            the CarcinogenExposure object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
                       NutritionalFactorData inNutritionalFactorData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inNutritionalFactorData, inCarcinogenExposure, "Nutrition");
        populateTreatment(inNutritionalFactorData, inCarcinogenExposure);
        populateAgeGender(inNutritionalFactorData, inCarcinogenExposure);
        populateDose(inNutritionalFactorData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inRadiationData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a therapy
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
                                     RadiationData inRadiationData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inRadiationData, theCarcinogenExposure, "Radiation");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateAgeGender(inRadiationData, theCarcinogenExposure);
        populateTreatment(inRadiationData, theCarcinogenExposure);
        populateDose(inRadiationData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inRadiationData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inRadiationData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
                       RadiationData inRadiationData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inRadiationData, inCarcinogenExposure, "Radiation");
        populateAgeGender(inRadiationData, inCarcinogenExposure);
        populateTreatment(inRadiationData, inCarcinogenExposure);
        populateDose(inRadiationData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inRadiationData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inSurgeryData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a therapy
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
                                     SurgeryData inSurgeryData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inSurgeryData, theCarcinogenExposure, "Other");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateTreatment(inSurgeryData, theCarcinogenExposure);
        populateAgeGender(inSurgeryData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inSurgeryData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
                       SurgeryData inSurgeryData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inSurgeryData, inCarcinogenExposure, "Other");
        populateTreatment(inSurgeryData, inCarcinogenExposure);
        populateAgeGender(inSurgeryData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }


    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inViralTreatmentData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a therapy
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
                                     ViralTreatmentData inViralTreatmentData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inViralTreatmentData, theCarcinogenExposure, "Viral");
        populateAgeGender(inViralTreatmentData, theCarcinogenExposure);
        populateTreatment(inViralTreatmentData, theCarcinogenExposure);
        populateDose(inViralTreatmentData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inViralTreatmentData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inViralTreatmentData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
                       ViralTreatmentData inViralTreatmentData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inViralTreatmentData, inCarcinogenExposure, "Viral");
        populateAgeGender(inViralTreatmentData, inCarcinogenExposure);
        populateTreatment(inViralTreatmentData, inCarcinogenExposure);
        populateDose(inViralTreatmentData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inViralTreatmentData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inAntibodyData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a Antibody
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
    		AntibodyData inAntibodyData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inAntibodyData, theCarcinogenExposure, "Antibody");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateAgeGender(inAntibodyData, theCarcinogenExposure);
        populateTreatment(inAntibodyData, theCarcinogenExposure);
        populateDose(inAntibodyData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inAntibodyData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inAntibodyData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inCarcinogenExposure
     *            the CarcinogenExposure object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
    		AntibodyData inAntibodyData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inAntibodyData, inCarcinogenExposure, "Antibody");
        populateAgeGender(inAntibodyData, inCarcinogenExposure);
        populateTreatment(inAntibodyData, inCarcinogenExposure);
        populateDose(inAntibodyData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inAntibodyData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    
    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inBacteriaData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a Bacteria
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
    		BacteriaData inBacteriaData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inBacteriaData, theCarcinogenExposure, "Bacteria");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateAgeGender(inBacteriaData, theCarcinogenExposure);
        populateTreatment(inBacteriaData, theCarcinogenExposure);
        populateDose(inBacteriaData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inBacteriaData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inBacteriaData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inCarcinogenExposure
     *            the CarcinogenExposure object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
    		BacteriaData inBacteriaData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inBacteriaData, inCarcinogenExposure, "Bacteria");
        populateAgeGender(inBacteriaData, inCarcinogenExposure);
        populateTreatment(inBacteriaData, inCarcinogenExposure);
        populateDose(inBacteriaData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inBacteriaData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    
    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inPlasmidData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a Plasmid
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
    		PlasmidData inPlasmidData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inPlasmidData, theCarcinogenExposure, "Plasmid");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateAgeGender(inPlasmidData, theCarcinogenExposure);
        populateTreatment(inPlasmidData, theCarcinogenExposure);
        populateDose(inPlasmidData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inPlasmidData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inPlasmidData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inCarcinogenExposure
     *            the CarcinogenExposure object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
    		PlasmidData inPlasmidData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inPlasmidData, inCarcinogenExposure, "Plasmid");
        populateAgeGender(inPlasmidData, inCarcinogenExposure);
        populateTreatment(inPlasmidData, inCarcinogenExposure);
        populateDose(inPlasmidData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inPlasmidData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    
    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inSignalingMoleculeData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a therapy
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
    		SignalingMoleculeData inSignalingMoleculeData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inSignalingMoleculeData, theCarcinogenExposure, "Signaling Molecule");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateAgeGender(inSignalingMoleculeData, theCarcinogenExposure);
        populateTreatment(inSignalingMoleculeData, theCarcinogenExposure);
        populateDose(inSignalingMoleculeData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inSignalingMoleculeData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inSignalingMoleculeData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inCarcinogenExposure
     *            the CarcinogenExposure object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
    		SignalingMoleculeData inSignalingMoleculeData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inSignalingMoleculeData, inCarcinogenExposure, "Signaling Molecule");
        populateAgeGender(inSignalingMoleculeData, inCarcinogenExposure);
        populateTreatment(inSignalingMoleculeData, inCarcinogenExposure);
        populateDose(inSignalingMoleculeData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inSignalingMoleculeData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    
    /**
     * Create a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inTransposonData
     *            the interface to create the CarcinogenExposure object from
     * 
     * @returns a therapy
     */
    public CarcinogenExposure create(AnimalModel inAnimalModel,
    		TransposonData inTransposonData)
    {

        log.debug("In CarcinogenExposureManagerImpl.create");

        CarcinogenExposure theCarcinogenExposure = new CarcinogenExposure();
        populateName(inAnimalModel, inTransposonData, theCarcinogenExposure, "Transposon");
        populateInducedMutationTrigger(theCarcinogenExposure);        
        populateAgeGender(inTransposonData, theCarcinogenExposure);
        populateTreatment(inTransposonData, theCarcinogenExposure);
        populateDose(inTransposonData, theCarcinogenExposure);
        populateAdministration(inAnimalModel, inTransposonData, theCarcinogenExposure);

        return theCarcinogenExposure;
    }

    /**
     * Update a CarcinogenExposure object with the correct data filled in.
     * 
     * @param inTransposonData
     *            the interface to update the CarcinogenExposure object from
     * 
     * @param inCarcinogenExposure
     *            the CarcinogenExposure object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel,
    		TransposonData inTransposonData,
                       CarcinogenExposure inCarcinogenExposure) throws Exception
    {

        log.debug("In CarcinogenExposureManagerImpl.update");

        populateName(inAnimalModel, inTransposonData, inCarcinogenExposure, "Transposon");
        populateAgeGender(inTransposonData, inCarcinogenExposure);
        populateTreatment(inTransposonData, inCarcinogenExposure);
        populateDose(inTransposonData, inCarcinogenExposure);
        populateAdministration(inAnimalModel, inTransposonData, inCarcinogenExposure);

        save(inCarcinogenExposure);
    }

    
    // ///////////////////////////////////////////////////////
    // Populate methods for the specific interfaces that
    // each interface implements
    // ///////////////////////////////////////////////////////
    private void populateName(AnimalModel inAnimalModel,
                              NameData inNameData,
                              CarcinogenExposure theCarcinogenExposure,
                              String inType)
    {

        log.debug("In CarcinogenExposureManagerImpl.populateName");

        // Set the treatment
        Treatment theTreatment = theCarcinogenExposure.getTreatment();
        if (theTreatment == null)
        {
            theTreatment = new Treatment();
            theCarcinogenExposure.setTreatment(theTreatment);
        }

        EnvironmentalFactor theEF = theCarcinogenExposure.getEnvironmentalFactor();
        if (theEF == null)
        {
            theEF = new EnvironmentalFactor();
            theCarcinogenExposure.setEnvironmentalFactor(theEF);
        }
        theEF.setType(inType);
        
        //set comments from EF object
        theEF.setComments(inNameData.getComments());

        /* Set other name or selected chemical name */
        // anytime the name is "other"
        if (inNameData.getName().equals(Constants.Dropdowns.OTHER_OPTION))
        {

            log.debug("Sending Notification eMail - new Name added");
            sendEmail(inAnimalModel, inNameData.getOtherName(), "CarcinogenExposureName");

            theEF.setName(null);
            theEF.setNameAlternEntry(inNameData.getOtherName());
        }
        // anytime name is not other, set uncontrolled vocab to null (covers editing)
        else
        {
            log.debug("Name is not other");
            theEF.setName(inNameData.getName());
            theEF.setNameAlternEntry(null);
        }

    }

    private void populateInducedMutationTrigger(CarcinogenExposure theCarcinogenExposure)
    {

		log.debug("In CarcinogenExposureManagerImpl.populateAgeGender");
		
        EnvironmentalFactor theEF = theCarcinogenExposure.getEnvironmentalFactor();
        if (theEF == null)
        {
            theEF = new EnvironmentalFactor();
            theCarcinogenExposure.setEnvironmentalFactor(theEF);
        }
        theEF.setIsInducedMutationTrigger(false);

    }    

    private void populateAgeGender(AgeGenderData inAgeGenderData,
                                   CarcinogenExposure theCarcinogenExposure)
    {

        log.debug("In CarcinogenExposureManagerImpl.populateAgeGender");

        // Set the treatment
        Treatment theTreatment = theCarcinogenExposure.getTreatment();
        if (theTreatment == null)
        {
            theTreatment = new Treatment();
            theCarcinogenExposure.setTreatment(theTreatment);
        }

        // Set the gender
        SexDistribution sexDistribution = SexDistributionManagerSingleton.instance().getByType(inAgeGenderData.getType());

        // save the treatment
        theTreatment.setSexDistribution(sexDistribution);

        theTreatment.setAgeAtTreatment(inAgeGenderData.getAgeAtTreatment());
        theTreatment.setAgeAtTreatmentUnit(inAgeGenderData.getAgeAtTreatmentUnit());
    }

    private void populateTreatment(TreatmentData inTreatmentData,
                                   CarcinogenExposure theCarcinogenExposure)
    {

        log.debug("In CarcinogenExposureManagerImpl.populateTreatment");

        // Set the treatment
        Treatment theTreatment = theCarcinogenExposure.getTreatment();
        if (theTreatment == null)
        {
            theTreatment = new Treatment();
            theCarcinogenExposure.setTreatment(theTreatment);
        }

        // save the treatment
        theTreatment.setRegimen(inTreatmentData.getRegimen());
        
     }

    private void populateDose(DoseData inDoseData,
                              CarcinogenExposure theCarcinogenExposure)
    {

        // Set the treatment
        Treatment theTreatment = theCarcinogenExposure.getTreatment();
        if (theTreatment == null)
        {
            theTreatment = new Treatment();
            theCarcinogenExposure.setTreatment(theTreatment);
        }

        theTreatment.setDosage(inDoseData.getDosage());
        theTreatment.setDosageUnit(inDoseData.getDosageUnit());
    }


    private void populateAdministration(AnimalModel inAnimalModel,
                                        AdministrationData inAdministrationData,
                                        CarcinogenExposure theCarcinogenExposure)
    {

        log.debug("In CarcinogenExposureManagerImpl.populateAdministration");

        if (inAdministrationData.getAdministrativeRoute() != null && inAdministrationData.getAdministrativeRoute().length() > 0)
        {
            // Set the treatment
            Treatment theTreatment = theCarcinogenExposure.getTreatment();
            if (theTreatment == null)
            {
                theTreatment = new Treatment();
                theCarcinogenExposure.setTreatment(theTreatment);
            }
        }

        /* Set other adminstrative route or selected adminstrative route */
        // anytime admin route is other
        if (inAdministrationData.getAdministrativeRoute().equals(Constants.Dropdowns.OTHER_OPTION))
        {
            log.debug("admin route equals other");

            theCarcinogenExposure.getTreatment().setAdministrativeRoute(null);
            theCarcinogenExposure.getTreatment().setAdminRouteAlternEntry(inAdministrationData.getOtherAdministrativeRoute());

            log.debug("Sending Notification eMail - new Administrative Route added");
            sendEmail(inAnimalModel, inAdministrationData.getOtherAdministrativeRoute(), "AdministrativeRoute");


            // anytime admin route is not other, set uncontrolled vocab to null
            // (covers editing)
        }
        else if (inAdministrationData.getAdministrativeRoute() != null)
        {
            log.debug("admin route not other or null");

            theCarcinogenExposure.getTreatment().setAdministrativeRoute(inAdministrationData.getAdministrativeRoute());
            theCarcinogenExposure.getTreatment().setAdminRouteAlternEntry(null);
        }

        EnvironmentalFactor theEF = theCarcinogenExposure.getEnvironmentalFactor();
        if (theEF == null)
        {
            theEF = new EnvironmentalFactor();
            theCarcinogenExposure.setEnvironmentalFactor(theEF);
        }
    }


    private void populateChemicalDrug(ChemicalDrugData inChemicalDrug,
                                      CarcinogenExposure theCarcinogenExposure)
    {

        log.debug("In CarcinogenExposureManagerImpl.populateChemicalDrug");

        EnvironmentalFactor theEF = theCarcinogenExposure.getEnvironmentalFactor();

        String theNscNumber = inChemicalDrug.getNscNumber().trim();
        if (theNscNumber != null && theNscNumber.length() > 0)
        {
            try
            {
                theEF.setNscNumber(Long.valueOf(theNscNumber));
            }
            catch (NumberFormatException e)
            {
                log.error("Bad NSC number: " + theNscNumber);
            }
        }
        String theCasNumber = inChemicalDrug.getCasNumber().trim();
        if (theCasNumber != null && theCasNumber.length() > 0)
        {
            theEF.setCasNumber(theCasNumber);
        }
    }

    private void sendEmail(AnimalModel inAnimalModel,
                           String theUncontrolledVocab,
                           String inType)
    {
    	log.debug("In CarcinogenExposureManagerImpl.sendEmail Enter");
        // Get the e-mail resource
		Properties camodProperties = new Properties();
		String camodPropertiesFileName = null;

		camodPropertiesFileName = System.getProperty("gov.nih.nci.camod.camodProperties");
		
		try {			
		FileInputStream in = new FileInputStream(camodPropertiesFileName);
		camodProperties.load(in);	
		} 
		catch (FileNotFoundException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();			
		} catch (IOException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();			
		}
		
		String recipients = UserManagerSingleton.instance()
		.getEmailForCoordinator();
   	
        StringTokenizer st = new StringTokenizer(recipients, ",");
        String inRecipients[] = new String[st.countTokens()];
        for (int i = 0; i < inRecipients.length; i++)
        {
            inRecipients[i] = st.nextToken();
        	log.debug("Defining recipients from the properties file: " + inRecipients[i]);             
        }

    	String inSubject = camodProperties.getProperty("model.new_unctrl_vocab_subject");
  	
        String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

        // gather message keys and variable values to build the e-mail
        String[] messageKeys = { Constants.Admin.NONCONTROLLED_VOCABULARY };
        Map<String, Object> values = new TreeMap<String, Object>();
        values.put("type", inType);
        values.put("value", theUncontrolledVocab);
        values.put("submitter", inAnimalModel.getSubmitter());
        values.put("model", inAnimalModel.getModelDescriptor());
        values.put("modelstate", inAnimalModel.getState());
        
    	log.debug("In CarcinogenExposureManagerImpl.sendEmail Enter");
        // Send the email
        try
        {
            MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys, values);            
        }
        catch (Exception e)
        {
            log.error("Caught exception sending mail: ", e);
            e.printStackTrace();
        }
    }

}
