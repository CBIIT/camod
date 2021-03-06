/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * @author schroedln
 * 
 * $Id: GeneDeliveryManagerImpl.java,v 1.27 2008-08-14 16:32:57 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.26  2007/10/31 19:04:08  pandyas
 * Fixed #8355 	Add comments field to every submission page
 * Fixed #8188 	Rename UnctrlVocab items to text entries
 *
 * Revision 1.25  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.24  2007/06/26 17:26:41  pandyas
 * populateOrgan method was accidentally deleted before uploading to cvs
 *
 * Revision 1.23  2007/06/26 16:13:43  pandyas
 * Fixed save when organ cleared from text entry and by use of the clear button for trees
 *
 * Revision 1.22  2007/06/25 17:48:37  pandyas
 * Fixed save and edit for text
 *
 * Revision 1.21  2007/05/10 02:20:34  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.20  2007/04/30 20:09:43  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.19  2006/10/23 17:08:13  pandyas
 * removed unused import
 *
 * Revision 1.18  2006/09/18 16:26:14  georgeda
 * moved getOrgan code inside check for null - check first, get organ if selected by user.  Also took out duplicate getOrgan code - threw unique constraint error and should not be there.
 *
 * Revision 1.17  2006/08/17 18:27:14  pandyas
 * Defect# 410: Externalize properties files - Code Changes to send mail method
 *
 * Revision 1.16  2006/05/24 15:29:32  pandyas
 * Added functionality to clear organ from screen on edit - not required
 *
 * Revision 1.15  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.14  2006/01/18 14:24:24  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.13  2005/11/29 16:32:27  pandyas
 * Defect #223: Fixed code so Organ object is not created when no Organ is selected. Added code to check for null Organ in the populateAction.
 *
 * Revision 1.12  2005/11/28 13:45:05  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.11  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.10  2005/11/14 14:18:33  georgeda
 * Cleanup
 *
 * Revision 1.9  2005/11/09 00:17:16  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.8  2005/11/03 21:47:48  georgeda
 * Changed EVS api
 *
 * Revision 1.7  2005/11/02 19:02:55  pandyas
 * Added e-mail functionality
 *
 * Revision 1.6  2005/10/20 20:02:42  pandyas
 * EVSTree (organ) functions properly
 *
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
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.domain.Treatment;
import gov.nih.nci.camod.service.GeneDeliveryManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.GeneDeliveryData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class GeneDeliveryManagerImpl extends BaseManager implements
		GeneDeliveryManager {
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

	public void remove(String id, AnimalModel inAnimalModel) throws Exception {
		log.debug("In GeneDeliveryManagerImpl.remove");
		inAnimalModel.getGeneDeliveryCollection().remove(get(id));
		super.save(inAnimalModel);
	}

	public GeneDelivery create(AnimalModel inAnimalModel,
			GeneDeliveryData inGeneDeliveryForm) throws Exception {
		log.debug("Entering GeneDeliveryManagerImpl.create");

		GeneDelivery theGeneDelivery = new GeneDelivery();
		populateOrgan(inGeneDeliveryForm, theGeneDelivery);
		populateGeneDelivery(inAnimalModel, inGeneDeliveryForm, theGeneDelivery);

		log.debug("Exiting GeneDeliveryManagerImpl.create");
		return theGeneDelivery;
	}

	public void update(AnimalModel inAnimalModel,
			GeneDeliveryData inGeneDeliveryForm, GeneDelivery inGeneDelivery)
			throws Exception {
		log.debug("Entering GeneDeliveryManagerImpl.update");
		// Populate w/ the new values and save
		populateOrgan(inGeneDeliveryForm, inGeneDelivery);
		populateGeneDelivery(inAnimalModel, inGeneDeliveryForm, inGeneDelivery);

		save(inGeneDelivery);

		log.debug("Exiting GeneDeliveryManagerImpl.update");
	}
    
    private void populateOrgan(GeneDeliveryData inGeneDeliveryData,
                            GeneDelivery inGeneDelivery) throws Exception {
                        
        // Update loop handeled separately for conceptCode = 00000
        if (inGeneDeliveryData.getOrganTissueCode().equals(Constants.Dropdowns.CONCEPTCODEZEROS)){
            if(inGeneDeliveryData.getOrgan() != null && inGeneDeliveryData.getOrgan().length() >0 ) {
                log.debug("Organ update loop for text: " + inGeneDeliveryData.getOrgan()); 
                inGeneDelivery.setOrgan(new Organ());
                inGeneDelivery.getOrgan().setName(inGeneDeliveryData.getOrgan());   
                inGeneDelivery.getOrgan().setConceptCode(
                        Constants.Dropdowns.CONCEPTCODEZEROS);     
            } else {
                log.debug("Clear previously entered organ text: " );
                inGeneDelivery.setOrgan(null); 
            }
        } else {            
            // Using trees loop, new save loop and update loop
            if (inGeneDeliveryData.getOrganTissueCode() != null && inGeneDeliveryData.getOrganTissueCode().length() > 0
                    && inGeneDeliveryData.getOrganTissueName() != null && inGeneDeliveryData.getOrganTissueName().length() > 0) {
                log.debug("OrganTissueCode: " + inGeneDeliveryData.getOrganTissueCode());
                log.debug("OrganTissueName: " + inGeneDeliveryData.getOrganTissueName()); 
                
                log.debug("OrganTissueCode() != null - getOrCreate method used");
                // when using tree, organTissueName populates the organ name entry
                Organ theNewOrgan = OrganManagerSingleton.instance().getOrCreate(
                        inGeneDeliveryData.getOrganTissueCode(),
                        inGeneDeliveryData.getOrganTissueName());
                
                log.debug("theNewOrgan: " + theNewOrgan);
                inGeneDelivery.setOrgan(theNewOrgan); 
            } 
            // Clear organ selection via GUI
            if (inGeneDeliveryData.getOrgan() == null && inGeneDeliveryData.getOrganTissueCode().length() <1 ) {
                log.debug("Null out organ when cleared: " );
                inGeneDelivery.setOrgan(null);                 

            }
            // initial save text entry organ from GUI
            if (inGeneDeliveryData.getOrgan() != null && inGeneDeliveryData.getOrgan().length() >0 ) {
                // text entry loop = new save
                log.debug("Organ (initial text entry): " + inGeneDeliveryData.getOrgan()); 
                inGeneDelivery.setOrgan(new Organ());
                inGeneDelivery.getOrgan().setName(inGeneDeliveryData.getOrgan());                
                inGeneDelivery.getOrgan().setConceptCode(
                        Constants.Dropdowns.CONCEPTCODEZEROS); 
                log.debug("New Organ: " + inGeneDelivery.getOrgan().toString());
            }           
            
        }  

     }    

	private void populateGeneDelivery(AnimalModel inAnimalModel,
			GeneDeliveryData inGeneDeliveryData, GeneDelivery inGeneDelivery)
			throws Exception {
		log.debug("Entering GeneDeliveryManagerImpl.populateGeneDelivery");

		// Set the treatment
		Treatment theTreatment = inGeneDelivery.getTreatment();
		if (theTreatment == null) {
			theTreatment = new Treatment();
			inGeneDelivery.setTreatment(theTreatment);
		}

		// Set the gender
		SexDistribution sexDistribution = SexDistributionManagerSingleton
				.instance().getByType(inGeneDeliveryData.getType());

		// save the treatment
		theTreatment.setSexDistribution(sexDistribution);

		theTreatment.setAgeAtTreatment(inGeneDeliveryData.getAgeAtTreatment());
		theTreatment.setAgeAtTreatmentUnit(inGeneDeliveryData
				.getAgeAtTreatmentUnit());

		// anytime the viral vector is "other"
		if (inGeneDeliveryData.getViralVector().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			// Send e-mail for OtherViralVector
			sendEmail(inAnimalModel, inGeneDeliveryData.getOtherViralVector(),
					"ViralVector");

			inGeneDelivery.setViralVector(null);
			inGeneDelivery.setViralVectorAlternEntry(inGeneDeliveryData
					.getOtherViralVector());
		}
		// anytime viral vector is not other set uncontrolled vocab to null
		// (covers editing)
		else {
			inGeneDelivery.setViralVector(inGeneDeliveryData.getViralVector());
			inGeneDelivery.setViralVectorAlternEntry(null);
		}

		inGeneDelivery.getTreatment().setRegimen(
				inGeneDeliveryData.getRegimen());
		inGeneDelivery.setGeneInVirus(inGeneDeliveryData.getGeneInVirus());

		log.debug("Exiting GeneDeliveryManagerImpl.populateGeneDelivery");
		
		inGeneDelivery.setComments(inGeneDeliveryData.getComments());
		
        // Update loop handeled separately for conceptCode = 00000
        if (inGeneDeliveryData.getOrganTissueCode().equals(Constants.Dropdowns.CONCEPTCODEZEROS)){
            if(inGeneDeliveryData.getOrgan() != null && inGeneDeliveryData.getOrgan().length() >0 ) {
                log.debug("Organ update loop for text: " + inGeneDeliveryData.getOrgan()); 
                inGeneDelivery.setOrgan(new Organ());
                inGeneDelivery.getOrgan().setName(inGeneDeliveryData.getOrgan());   
                inGeneDelivery.getOrgan().setConceptCode(
                        Constants.Dropdowns.CONCEPTCODEZEROS);     
            } else {
                log.debug("Clear previously entered organ text: " );
                inGeneDelivery.setOrgan(null); 
            }
        } else {            
            // Using trees loop, new save loop and update loop
            if (inGeneDeliveryData.getOrganTissueCode() != null && inGeneDeliveryData.getOrganTissueCode().length() > 0
                    && inGeneDeliveryData.getOrganTissueName() != null && inGeneDeliveryData.getOrganTissueName().length() > 0) {
                log.debug("OrganTissueCode: " + inGeneDeliveryData.getOrganTissueCode());
                log.debug("OrganTissueName: " + inGeneDeliveryData.getOrganTissueName()); 
                
                log.debug("OrganTissueCode() != null - getOrCreate method used");
                // when using tree, organTissueName populates the organ name entry
                Organ theNewOrgan = OrganManagerSingleton.instance().getOrCreate(
                        inGeneDeliveryData.getOrganTissueCode(),
                        inGeneDeliveryData.getOrganTissueName());
                
                log.debug("theNewOrgan: " + theNewOrgan);
                inGeneDelivery.setOrgan(theNewOrgan); 
            } 
            // Clear organ selection via GUI
            if (inGeneDeliveryData.getOrgan() == null && inGeneDeliveryData.getOrganTissueCode().length() <1 ) {
                log.debug("Null out organ when cleared: " );
                inGeneDelivery.setOrgan(null);                 

            }
            // initial save text entry organ from GUI
            if (inGeneDeliveryData.getOrgan() != null && inGeneDeliveryData.getOrgan().length() >0 ) {
                // text entry loop = new save
                log.debug("Organ (initial text entry): " + inGeneDeliveryData.getOrgan()); 
                inGeneDelivery.setOrgan(new Organ());
                inGeneDelivery.getOrgan().setName(inGeneDeliveryData.getOrgan());                
                inGeneDelivery.getOrgan().setConceptCode(
                        Constants.Dropdowns.CONCEPTCODEZEROS); 
                log.debug("New Organ: " + inGeneDelivery.getOrgan().toString());
            }           
            
        } 

	}

	private void sendEmail(AnimalModel inAnimalModel,
			String theUncontrolledVocab, String inType) {
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

		// Iterate through all the reciepts in the config file
		String recipients = UserManagerSingleton.instance()
				.getEmailForCoordinator();
		StringTokenizer st = new StringTokenizer(recipients, ",");
		String inRecipients[] = new String[st.countTokens()];
		for (int i = 0; i < inRecipients.length; i++) {
			inRecipients[i] = st.nextToken();
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
