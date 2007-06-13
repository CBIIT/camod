/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyManagerImpl.java,v 1.22 2007-06-13 19:39:38 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.21  2007/06/13 17:10:17  pandyas
 * Modified save for Zebrafish vocab
 *
 * Revision 1.20  2007/06/13 17:01:29  pandyas
 * Modified save for organTissueName
 *
 * Revision 1.19  2007/06/13 12:09:51  pandyas
 * Modified for save of organ/diagnosis for each tree options
 *
 * Revision 1.18  2007/05/16 12:31:52  pandyas
 * Cleaned up unused code
 *
 * Revision 1.17  2007/04/30 20:09:43  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.16  2006/11/08 18:05:13  pandyas
 * Modified TumorIncidenceRate float to String (weight of tumor and volume of tumor also needed modified to delete properly)
 *
 * Revision 1.15  2006/10/23 16:52:20  pandyas
 * minor comments change
 *
 * Revision 1.14  2006/10/17 16:13:46  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.13  2006/05/22 20:11:45  pandyas
 * removed debugging print statements
 *
 * Revision 1.12  2006/05/22 15:01:47  pandyas
 * Removed commented out line of code
 *
 * Revision 1.11  2006/04/21 13:40:03  georgeda
 * Cleanup
 *
 * Revision 1.10  2006/04/20 19:18:53  pandyas
 * Moved save Assoc Met from AnimalModel to the Histopathology
 *
 * Revision 1.9  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.8  2006/01/18 14:24:24  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.7  2005/11/22 16:35:43  georgeda
 * Defect #107, cleanup of disease tree
 *
 * Revision 1.6  2005/11/18 22:50:02  georgeda
 * Defect #184.  Cleanup editing of old models
 *
 * Revision 1.5  2005/11/09 00:17:16  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.4  2005/11/07 19:15:17  pandyas
 * modified for clinical marker screen
 *
 * Revision 1.3  2005/11/04 14:44:25  georgeda
 * Cleaned up histopathology/assoc metastasis
 *
 * Revision 1.2  2005/11/03 21:47:48  georgeda
 * Changed EVS api
 *
 * Revision 1.1  2005/11/03 18:54:29  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Disease;
import gov.nih.nci.camod.domain.GeneticAlteration;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.HistopathologyManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.AssociatedMetastasisData;
import gov.nih.nci.camod.webapp.form.HistopathologyData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class HistopathologyManagerImpl extends BaseManager implements
		HistopathologyManager {
	public List getAll() throws Exception {
		log.trace("In HistopathologyManagerImpl.getAll");
		return super.getAll(Histopathology.class);
	}

	public Histopathology get(String id) throws Exception {
		log.trace("In HistopathologyManagerImpl.get");
		return (Histopathology) super.get(id, Histopathology.class);
	}

	public void save(Histopathology histopathology) throws Exception {
		log.trace("In HistopathologyManagerImpl.save");
		super.save(histopathology);
	}

	public void remove(String id, AnimalModel inAnimalModel) throws Exception {
		log.trace("In HistopathologyManagerImpl.save");

		Histopathology theHistopathology = get(id);

		inAnimalModel.getHistopathologyCollection().remove(theHistopathology);
		super.save(inAnimalModel);
	}

	public void removeAssociatedMetastasis(String id,
			Histopathology inHistopathology) throws Exception {
		log.info("Entering HistopathologyManagerImpl.createMetastasis");

		Histopathology theMetastasis = get(id);

		inHistopathology.getMetastasisCollection().remove(theMetastasis);
		save(inHistopathology);

		log.info("Exiting HistopathologyManagerImpl.createMetastasis");
	}

	public Histopathology createHistopathology(AnimalModel inAnimalModel,
			HistopathologyData inHistopathologyData) throws Exception {
		log.info("Entering HistopathologyManagerImpl.createHistopathology");

		Histopathology theHistopathology = new Histopathology();
		populateOrganDisease(inAnimalModel, inHistopathologyData,
				theHistopathology);
		populateHistopathology(inHistopathologyData, theHistopathology);

		log.info("Exiting HistopathologyManagerImpl.createHistopathology");

		return theHistopathology;
	}

	public void updateHistopathology(AnimalModel inAnimalModel,
			HistopathologyData inHistopathologyData,
			Histopathology inHistopathology) throws Exception {
		log.info("Entering HistopathologyManagerImpl.updateHistopathology");
		log.info("Updating HistopathologyData: " + inHistopathology.getId());

		// Populate w/ the new values and save
		populateOrganDisease(inAnimalModel, inHistopathologyData,
				inHistopathology);
		populateHistopathology(inHistopathologyData, inHistopathology);

		save(inHistopathology);

		log.info("Exiting HistopathologyManagerImpl.updateHistopathology");
	}

	private void populateOrganDisease(AnimalModel inAnimalModel,
			HistopathologyData inHistopathologyData,
			Histopathology inHistopathology) throws Exception {
		
		log.info("<HistopathologyManagerImpl> Entering populateOrganDisease");

		// every submission - lookup organ or create one new
        // Note:  When using two trees, use organTissueName, organTissueCode, DiagnosisName, DiagnosisCode
        // When entering text, use organ, TumorClassification
        // Zebrafish (half tree, half dropdown), use organTissueName, organTissueCode, TumorClassification 
        
        // Using trees loop 
		if (inHistopathologyData.getOrganTissueCode() != null && inHistopathologyData.getOrganTissueCode().length() > 0) {
            log.info("OrganTissueCode: " + inHistopathologyData.getOrganTissueCode());
            log.info("OrganTissueName: " + inHistopathologyData.getOrganTissueName()); 
            
            log.info("OrganTissueCode() != null - getOrCreate method used");
            // when using tree, organTissueName populates the organ name entry
            Organ theNewOrgan = OrganManagerSingleton.instance().getOrCreate(
                    inHistopathologyData.getOrganTissueCode(),
                    inHistopathologyData.getOrganTissueName());
            inHistopathology.setOrgan(theNewOrgan); 
		} else {
            log.info("Organ: " + inHistopathologyData.getOrgan());            
            
            // Create new organ with conceptCode = 000000, use name field
            inHistopathology.setOrgan(new Organ());
            inHistopathology.getOrgan().setName(inHistopathologyData.getOrgan());                
            inHistopathology.getOrgan().setConceptCode(
                    Constants.Dropdowns.CONCEPTCODEZEROS);
		} 
		
		
		// every submission - lookup disease or create one new
		if (inHistopathologyData.getDiagnosisCode() != null && inHistopathologyData.getDiagnosisCode().length() > 0 ) {
            
            if (inHistopathologyData.getOtherTumorClassification() != null && 
                    inHistopathologyData.getOtherTumorClassification().length() > 0) {
                log.info("TumorClassification: " + inHistopathologyData.getTumorClassification());
                log.info("other TumorClassification: " + inHistopathologyData.getOtherTumorClassification());
                
                log.info("Sending Notification eMail - new Zebrafish Diagnosis added");
                sendEmail(inAnimalModel, inHistopathologyData
                         .getDiagnosisName(), "otherDiagnosisName");
                inHistopathology.setDisease(new Disease());
                log.info("Concept code set to 000000");
                inHistopathology.getDisease().setConceptCode(
                         Constants.Dropdowns.CONCEPTCODEZEROS);              
                inHistopathology.getDisease().setNameUnctrlVocab(
                         inHistopathologyData.getOtherTumorClassification());
                inHistopathology.getDisease().setName(null);
           } else {           
                log.info("DiagnosisCode: " + inHistopathologyData.getDiagnosisCode());
                log.info("DiagnosisName: " + inHistopathologyData.getDiagnosisName());             
                
                Disease theNewDisease = DiseaseManagerSingleton.instance()
                        .getOrCreate(inHistopathologyData.getDiagnosisCode(),
                                inHistopathologyData.getDiagnosisName());
                inHistopathology.setDisease(theNewDisease); 
            }
		} else {
            log.info("TumorClassification: " + inHistopathologyData.getTumorClassification());            
            
            log.info("inHistopathologyData.getDiagnosisCode() is null: ");

            inHistopathology.setDisease(new Disease());
            inHistopathology.getDisease().setName(
                    inHistopathologyData.getTumorClassification());                
            log.info("Concept code set to 000000");
            inHistopathology.getDisease().setConceptCode(
                    Constants.Dropdowns.CONCEPTCODEZEROS);
         }
	}

	private void populateHistopathology(
			HistopathologyData inHistopathologyData,
			Histopathology inHistopathology) throws Exception {
		log.info("<HistopathologyManagerImpl> Entering populateHistopathology");

		log.info("Saving: Histopathology object attributes ");
		inHistopathology.setComments(inHistopathologyData.getComments());
		inHistopathology.setGrossDescription(inHistopathologyData
				.getGrossDescription());

		inHistopathology.setTumorIncidenceRate(inHistopathologyData
				.getTumorIncidenceRate());

		inHistopathology
				.setSurvivalInfo(inHistopathologyData.getSurvivalInfo());
		inHistopathology.setMicroscopicDescription(inHistopathologyData
				.getMicroscopicDescription());
		inHistopathology.setComparativeData(inHistopathologyData
				.getComparativeData());
		log.info("inHistopathologyData.getComparativeData():  "
				+ inHistopathologyData.getComparativeData());

		inHistopathology.setWeightOfTumor(inHistopathologyData
				.getWeightOfTumor());

		inHistopathology.setVolumeOfTumor(inHistopathologyData
				.getVolumeOfTumor());

		// Histopathology attributes - AgeOfOnset
		inHistopathology.setAgeOfOnset(inHistopathologyData.getAgeOfOnset());
		inHistopathology.setAgeOfOnsetUnit(inHistopathologyData
				.getAgeOfOnsetUnit());

		// Histopathology attributes - AgeOfDetection
		inHistopathology.setAgeOfDetection(inHistopathologyData
				.getAgeOfDetection());
		inHistopathology.setAgeOfDetectionUnit(inHistopathologyData
				.getAgeOfDetectionUnit());

		// No genetic alteration and we have data for it
		if (inHistopathology.getGeneticAlteration() == null
				&& inHistopathologyData.getObservation() != null
				&& inHistopathologyData.getObservation().length() > 0) {
			inHistopathology.setGeneticAlteration(new GeneticAlteration());
			log
					.info("Saving: inHistopathology.getGeneticAlteration() attributes ");

			inHistopathology.getGeneticAlteration().setObservation(
					inHistopathologyData.getObservation());
			inHistopathology.getGeneticAlteration().setMethodOfObservation(
					inHistopathologyData.getMethodOfObservation());
		}

		// Already have a genetic alteration. Either blank it out or update it
		else {
			if (inHistopathologyData.getObservation() != null
					&& inHistopathologyData.getObservation().length() > 0) {
				inHistopathology.getGeneticAlteration().setObservation(
						inHistopathologyData.getObservation());
				inHistopathology.getGeneticAlteration().setMethodOfObservation(
						inHistopathologyData.getMethodOfObservation());
			} else {
				inHistopathology.setGeneticAlteration(null);
			}
		}

		log.info("<HistopathologyManagerImpl> Exiting populateHistopathology");
	}

	public void createAssociatedMetastasis(AnimalModel inAnimalModel,
			AssociatedMetastasisData inAssociatedMetastasisData,
			Histopathology inHistopathology) throws Exception {
		log
				.info("Entering HistopathologyManagerImpl.createAssociatedMetastasis");

		Histopathology theAssociatedMetastasis = new Histopathology();
		populateOrganDisease(inAnimalModel, inAssociatedMetastasisData,
				theAssociatedMetastasis);		
		populateHistopathology(inAssociatedMetastasisData,
				theAssociatedMetastasis);

		inHistopathology.addMetastasis(theAssociatedMetastasis);
		save(inHistopathology);
		log
				.info("Exiting HistopathologyManagerImpl.createAssociatedMetastasis");
	}

	public void addAssociatedMetastasis(AnimalModel inAnimalModel, Histopathology inHistopathology,
			AssociatedMetastasisData inAssociatedMetastasisData)
			throws Exception {

		log.info("Entering HistopathologyManagerImpl.addAssociatedMetastasis");
		HistopathologyManagerSingleton.instance().createAssociatedMetastasis(inAnimalModel, 
				inAssociatedMetastasisData, inHistopathology);

		log.info("Exiting HistopathologyManagerImpl.addHistopathology");
	}

	public void updateAssociatedMetastasis(AnimalModel inAnimalModel,
			AssociatedMetastasisData inAssociatedMetastasisData,
			Histopathology inAssociatedMetastasis) throws Exception {

		log
				.info("Entering HistopathologyManagerImpl.updateAssociatedMetastasis");
		log.info("Updating HistopathologyData: "
				+ inAssociatedMetastasis.getId());

		// Populate w/ the new values and save
		populateOrganDisease(inAnimalModel, inAssociatedMetastasisData,
				inAssociatedMetastasis);		
		populateHistopathology(inAssociatedMetastasisData,
				inAssociatedMetastasis);
		save(inAssociatedMetastasis);

		log
				.info("Exiting HistopathologyManagerImpl.updateAssociatedMetastasis");
	}

	private void sendEmail(AnimalModel inAnimalModel,
			String theUncontrolledVocab, String inType) {
		log.info("In HistopathologyManagerImpl.sendEmail Enter");
		// Get the e-mail resource
		Properties camodProperties = new Properties();
		String camodPropertiesFileName = null;

		camodPropertiesFileName = System
				.getProperty("gov.nih.nci.camod.camodProperties");

		try {
			FileInputStream in = new FileInputStream(camodPropertiesFileName);
			camodProperties.load(in);
		} catch (FileNotFoundException e) {
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
		for (int i = 0; i < inRecipients.length; i++) {
			inRecipients[i] = st.nextToken();
			log.info("Defining recipients from the properties file: "
					+ inRecipients[i]);
		}

		String inSubject = camodProperties
				.getProperty("model.new_unctrl_vocab_subject");

		String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

		// gather message keys and variable values to build the e-mail
		String[] messageKeys = { Constants.Admin.NONCONTROLLED_VOCABULARY };
		Map<String, Object> values = new TreeMap<String, Object>();
		values.put("type", inType);
		values.put("value", theUncontrolledVocab);
		values.put("submitter", inAnimalModel.getSubmitter());
		values.put("model", inAnimalModel.getModelDescriptor());
		values.put("modelstate", inAnimalModel.getState());

		log.info("In HistopathologyManagerImpl.sendEmail Enter");
		// Send the email
		try {
			MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys,
					values);
		} catch (Exception e) {
			log.error("Caught exception sending mail: ", e);
			e.printStackTrace();
		}
	}
}
