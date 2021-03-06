/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * @pandyas
 * 
 * $Id: TransientInterferenceManagerImpl.java,v 1.6 2007-10-31 19:11:42 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.4  2007/04/04 13:18:06  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.3  2007/03/26 12:01:11  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.2  2006/10/23 14:21:27  pandyas
 * cleaned up code
 *
 * Revision 1.1  2006/10/17 16:14:05  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.1  2006/05/03 20:04:04  pandyas
 * Modified to add Morpholino object data to application
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.TransientInterferenceManager;
import gov.nih.nci.camod.webapp.form.*;
import gov.nih.nci.camod.util.MailUtil;

/**
 * Manager provides get method
 */
public class TransientInterferenceManagerImpl extends BaseManager implements
		TransientInterferenceManager {

	public List getAll() throws Exception {
		log.debug("In TransientInterferenceManagerImpl.getAll");
		return super.getAll(TransientInterference.class);
	}

	/**
	 * Get a specific TransientInterference by id
	 * 
	 * @param id
	 *            the unique id for a TransientInterference
	 * 
	 * @return the matching TransientInterference object, or null if not found.
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public TransientInterference get(String id) throws Exception {
		log.debug("In TransientInterferenceManagerImpl.get");
		return (TransientInterference) super.get(id,
				TransientInterference.class);
	}

	/**
	 * Save TransientInterference
	 * 
	 * @param TransientInterference
	 *            the TransientInterference to save
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public void save(TransientInterference transientInterference)
			throws Exception {
		log.debug("In TransientInterferenceManagerImpl.save");
		super.save(transientInterference);
	}

	/**
	 * Remove a specific TransientInterference by id
	 * 
	 * @param id
	 *            the unique id for a TransientInterference
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public void remove(String id, AnimalModel inAnimalModel) throws Exception {
		log.debug("In TransientInterferenceManagerImpl.remove");

		TransientInterference theTransientInterference = get(id);

		inAnimalModel.getTransientInterferenceCollection().remove(
				theTransientInterference);
		super.save(inAnimalModel);
	}

	/**
	 * Create a TransientInterference object with the correct data filled in.
	 * 
	 * @param inTransientInterferenceData
	 *            the interface to create the TransientInterference object from
	 * 
	 * @returns a TransientInterference
	 */
	public TransientInterference create(AnimalModel inAnimaModel,
			TransientInterferenceData inTransientInterferenceData)
			throws Exception {

		log.debug("In TransientInterferenceManagerImpl.create Entering");

		TransientInterference theTransientInterference = new TransientInterference();

		// Set TransientInterferenceMethod only once during create - skip during
		// update
		populateTransIntMethod(inTransientInterferenceData,
				theTransientInterference, inAnimaModel);
		populate(inTransientInterferenceData, theTransientInterference,
				inAnimaModel);

		log.debug("In TransientInterferenceManagerImpl.create Exiting");
		return theTransientInterference;
	}

	public void update(AnimalModel inAnimalModel,
			TransientInterferenceData inTransientInterferenceData,
			TransientInterference inTransientInterference) throws Exception {

		log.debug("In TransientInterferenceManagerImpl.update");
		populate(inTransientInterferenceData, inTransientInterference,
				inAnimalModel);

		save(inTransientInterference);

	}

	private void populateTransIntMethod(
			TransientInterferenceData inTransIntData,
			TransientInterference inTransInt,
			AnimalModel inAnimalModel) throws Exception {
		
		log.debug("Entering populateTransMethod");

		log.debug("inTransientInterferenceData.getConceptCode(): "
				+ inTransIntData.getConceptCode());

		/* get TransientInterferenceMethod object */
		TransientInterferenceMethod theMethod = TransIntMethodManagerSingleton
				.instance().getByConceptCode(
						inTransIntData.getConceptCode());
		log.debug("In populateTransMethod TIMethod: " + theMethod);

		// set TransientInterferenceMethod
		inTransInt.setTransientInterferenceMethod(theMethod);
	}

	private void populate(
			TransientInterferenceData inTransIntData,
			TransientInterference inTransInt,
			AnimalModel inAnimalModel) throws Exception {
		log.debug("<TransientInterferenceManagerImpl> Entering populate");

		// Save Source
		if (inTransIntData.getSource().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			log.debug("source equals other");
			inTransInt.setSource(null);
			inTransInt.setSourceAlternEntry(inTransIntData
							.getOtherSource());

			log.debug("Sending Notification eMail - new Source added");
			sendEmail(inAnimalModel, inTransIntData
					.getOtherSource(), "otherSource");
		} else if (inTransIntData.getSource() != null) {
			log.debug("source not other or null");
			inTransInt.setSource(inTransIntData
					.getSource());
			inTransInt.setSourceAlternEntry(null);
		}

		// Save Type
		inTransInt.setType(inTransIntData.getType());
		
		// Save  Target site - for morpholino only - will be null for siRNA submission
		if (inTransIntData.getTargetSite() != null && inTransIntData.getTargetSite().length() >0 ) {
			inTransInt.setTargetSite(inTransIntData.getTargetSite());
		}
		
		// Save SequenceDirection
		inTransInt
				.setSequenceDirection(inTransIntData
						.getSequenceDirection());

		// Save Targeted Region
		inTransInt.setTargetedRegion(inTransIntData
				.getTargetedRegion());

		// Save Concentration
		inTransInt.setConcentration(inTransIntData
				.getConcentration());
		inTransInt
				.setConcentrationUnit(inTransIntData
						.getConcentrationUnit());

		// Save Delivery Method
		if (inTransIntData.getDeliveryMethod().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			log.debug("DeliveryMethod equals other");
			inTransInt.setDeliveryMethod(null);
			inTransInt
					.setDeliveryMethodAlternEntry(inTransIntData
							.getOtherDeliveryMethod());

			log.debug("Sending Notification eMail - new DeliveryMethod added");
			sendEmail(inAnimalModel, inTransIntData
					.getOtherDeliveryMethod(), "otherDeliveryMethod");
		} else if (inTransIntData.getDeliveryMethod() != null) {
			log.debug("DeliveryMethod not other or null");
			inTransInt
					.setDeliveryMethod(inTransIntData
							.getDeliveryMethod());
			inTransInt.setDeliveryMethodAlternEntry(null);
		}

		// Save visualizationLigands
		if (inTransIntData.getVisualLigand().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			log.debug("visualizationLigands equals other");
			inTransInt.setVisualLigand(null);
			inTransInt
					.setVisualLigandAlternEntry(inTransIntData
							.getOtherVisualLigand());

			log.debug("Sending Notification eMail - new VisualLigands added");
			sendEmail(inAnimalModel, inTransIntData
					.getOtherVisualLigand(), "otherVisualLigands");
		} else if (inTransIntData.getVisualLigand() != null) {
			log.debug("visualLigands not other or null");
			inTransInt.setVisualLigand(inTransIntData
					.getVisualLigand());
			inTransInt.setVisualLigandAlternEntry(null);
		}

		// Save Comment
		inTransInt.setComments(inTransIntData
				.getComments());

		log.debug("<TransientInterfaceManagerImpl> Exiting populate");
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
