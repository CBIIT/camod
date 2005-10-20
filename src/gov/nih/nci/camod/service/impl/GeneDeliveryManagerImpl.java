/**
 * @author schroedln
 * 
 * $Id: GeneDeliveryManagerImpl.java,v 1.6 2005-10-20 20:02:42 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/10/19 18:08:18  pandyas
 * added age and gender to genedelivery
 *
 * Revision 1.4  2005/09/28 21:20:01  georgeda
 * Finished up converting to new manager
 *
 * Revision 1.3  2005/09/28 15:12:27  schroedn
 * Added GeneDelivery and Xenograft/Transplant, businass logic in Managers
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.GeneDeliveryManager;
import gov.nih.nci.camod.webapp.form.GeneDeliveryData;
import java.util.List;
import gov.nih.nci.camod.util.EvsTreeUtil;

public class GeneDeliveryManagerImpl extends BaseManager implements GeneDeliveryManager {

    public List getAll() throws Exception {
        log.debug("In GeneDeliveryManagerImpl.getAll");
        return super.getAll(GeneDelivery.class);
    }

    public GeneDelivery get(String id) throws Exception {
        log.debug("In GeneDeliveryManagerImpl.get");
        return (GeneDelivery) super.get(id, GeneDelivery.class);
    }

    public void save(GeneDelivery geneDelivery) throws Exception {
        log.debug("In GeneDeliveryManagerImpl.save");
        super.save(geneDelivery);
    }

    public void remove(String id) throws Exception {
        log.debug("In GeneDeliveryManagerImpl.remove");
        super.remove(id, GeneDelivery.class);
    }

    public GeneDelivery create(GeneDeliveryData inGeneDeliveryForm) throws Exception {

        log.info("Entering GeneDeliveryManagerImpl.create");

        GeneDelivery theGeneDelivery = new GeneDelivery();

        populateGeneDelivery(inGeneDeliveryForm, theGeneDelivery);
        
        log.info("Exiting GeneDeliveryManagerImpl.create");        
        return theGeneDelivery;
    }

    public void update(GeneDeliveryData inGeneDeliveryForm, GeneDelivery inGeneDelivery) throws Exception {

        log.info("Entering GeneDeliveryManagerImpl.update");
        log.info("Updating GeneDeliveryForm: " + inGeneDelivery.getId());

        // Populate w/ the new values and save
        populateGeneDelivery(inGeneDeliveryForm, inGeneDelivery);

        save(inGeneDelivery);

        log.debug("Exiting GeneDeliveryManagerImpl.update");
    }

    private void populateGeneDelivery(GeneDeliveryData inGeneDeliveryData, GeneDelivery inGeneDelivery)
            throws Exception {

        log.info("Entering GeneDeliveryManagerImpl.populateGeneDelivery");
        
        // Set the treatment
        Treatment theTreatment = inGeneDelivery.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            inGeneDelivery.setTreatment(theTreatment);
        }        
        
        // Set the gender
        SexDistribution sexDistribution = SexDistributionManagerSingleton.instance().getByType(inGeneDeliveryData.getType());
        
        // save the treatment
        theTreatment.setSexDistribution(sexDistribution);

        // Append the ageunit onto the age at treatment variable
        theTreatment.setAgeAtTreatment(inGeneDeliveryData.getAgeAtTreatment() + " " + inGeneDeliveryData.getAgeUnit());
        
        //anytime the viral vector is "other"
        if (inGeneDeliveryData.getViralVector().equals(Constants.Dropdowns.OTHER_OPTION) )   {
            // TODO: send an email
        	System.out.println("viral vector equals other");
            System.out.println("SENDING EMAIL VIRAL VECTOR");

            inGeneDelivery.setViralVector(Constants.Dropdowns.OTHER_OPTION);
            inGeneDelivery.setViralVectorUnctrlVocab(inGeneDeliveryData.getOtherViralVector());
        }        
        // anytime viral vector is not other set uncontrolled vocab to null (covers editing)
        else   {
        	System.out.println("viral vector not other");
        	inGeneDelivery.setViralVector(inGeneDeliveryData.getViralVector());
        	inGeneDelivery.setViralVectorUnctrlVocab(null);
        }        
        
        inGeneDelivery.getTreatment().setRegimen(inGeneDeliveryData.getRegimen());
        inGeneDelivery.setGeneInVirus(inGeneDeliveryData.getGeneInVirus());

        /*
         * Add a Organ to AnimalModel with correct IDs, conceptCode 
         */
        
        //new submission - organ will be null
        if (inGeneDelivery.getOrgan() == null) {
        	System.out.println("Creating new Organ object");        	
        	inGeneDelivery.setOrgan(new Organ());
        }

        String newConceptCode = inGeneDeliveryData.getOrganTissueCode();
    	System.out.println("newConceptCode: " + newConceptCode);
    	
        String oldConceptCode = inGeneDelivery.getOrgan().getConceptCode();
    	System.out.println("oldConceptCode: " + oldConceptCode); 
    	
        if( !newConceptCode.equals(oldConceptCode) ) 
        {
       	System.out.println("Organ is new or was modified so retrieve attributes");
        //always get/store organ name through the concept code - never deal with converting name back and forth
        String preferedOrganName = EvsTreeUtil.getEVSPreferedOrganDescription(inGeneDeliveryData.getOrganTissueCode());
        	
        System.out.println("preferedOrganName: " + preferedOrganName);
        inGeneDelivery.getOrgan().setName(preferedOrganName); 
            
        System.out.println("populateGeneDelivery - getOrgan().setConceptCode - OrganTissueCode: " +inGeneDeliveryData.getOrganTissueCode());
        inGeneDelivery.getOrgan().setConceptCode(inGeneDeliveryData.getOrganTissueCode());            
        }

        log.info("Exiting GeneDeliveryManagerImpl.populateGeneDelivery");
    }

}
