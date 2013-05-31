/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * @author dgeorge
 * 
 * $Id: EmailActionImpl.java,v 1.18 2005-12-08 19:06:10 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.17  2005/11/28 13:44:04  georgeda
 * Defect #192, handle back arrow for curation changes
 *
 * Revision 1.16  2005/11/21 16:09:49  georgeda
 * Defects #98 and #99, make sure to send e-mail to coordinator
 *
 * Revision 1.15  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.14  2005/11/14 14:18:22  georgeda
 * Cleanup
 *
 * Revision 1.13  2005/10/20 19:24:20  stewardd
 * modified comments in code for accuracy
 *
 * Revision 1.12  2005/10/20 19:15:39  stewardd
 * Employs new EmailUtil API supporting e-mail content built from ResourceBundle-stored templates with support for variables (via Velocity API)
 *
 * Revision 1.11  2005/10/20 19:13:01  stewardd
 * Employs new EmailUtil API supporting e-mail content built from ResourceBundle-stored templates with support for variables (via Velocity API)
 *
 * Revision 1.10  2005/10/18 20:39:02  stewardd
 * Checked in as a work-in-progress for the sake of not breaking the build. The altered signature of MailUtil.sendmail is accounted for in this revision. The outcome of calling sendmail herein is not tested but at least the code will permit the build without compile errors. Testing of course will be performed immediately and a new revision checked in as necessary.
 *
 * Revision 1.9  2005/10/10 14:08:13  georgeda
 * Changes for comment curation
 *
 * Revision 1.8  2005/09/22 15:13:43  georgeda
 * Update
 *
 * Revision 1.7  2005/09/19 13:09:24  georgeda
 * Send e-mail to submitter for certain states
 *
 * Revision 1.6  2005/09/16 15:52:57  georgeda
 * Changes due to manager re-write
 * Revision 1.5 2005/09/14 12:45:06 georgeda
 * Renamed mail utility
 * 
 * Revision 1.4 2005/09/13 20:26:35 georgeda More cleanup Revision 1.3
 * 2005/09/13 19:18:46 georgeda Email updates and CSM integration Revision 1.2
 * 2005/09/12 18:22:11 georgeda Curation changes and addition of e-mail
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.CurateableAction;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.AnimalModelStateData;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * Action called by the curation workflow that sends an e-mail
 * 
 */
public class EmailActionImpl extends BaseCurateableAction {

	/**
	 * Protected constructor. Use the create method to get an instance.
	 */
	protected EmailActionImpl() {
	}

	/**
	 * Create an instance of this curatable action
	 * 
	 * @param inArgs
	 *            the arguments to the action
	 * @param inObject
	 *            the curatable object to get information from
	 */
	public CurateableAction create() {
		return new EmailActionImpl();
	}

	/**
	 * Execute the curatable action
	 * 
	 * @param inArgs
	 *            the arguments to the action
	 * @param inObject
	 *            the curatable object to get information from
	 */
	public void execute(Map inArgs, Curateable inObject) {

		log.trace("Entering execute");

		log.debug("Arguments: " + inArgs);

		if (inArgs.containsKey(Constants.FORMDATA)) {

			try {
				AnimalModelStateData theData = (AnimalModelStateData) inArgs.get(Constants.FORMDATA);

				// Get the various domain objects
				AnimalModel theAnimalModel = (AnimalModel) inObject;
				Log theLog = LogManagerSingleton.instance().getCurrentByModel(theAnimalModel);
				Person thePerson = (Person) theLog.getSubmitter();

				// Get the e-mail for the assigned user
				String[] theRecipients = null;
				String[] theAssignee = { UserManagerSingleton.instance().getEmailForUser(thePerson.getUsername()) };
				String[] theCoordinator = new String[] { UserManagerSingleton.instance().getEmailForCoordinator() };

				// Build the message text
				String theMailSubject = "";
				String theMailText = theData.getNote();
				String[] theMailStandardText = null;

				// Customize the text based on the action.
				// Do so by specifying what template to use for e-mail content
				// Templates are represented here by keys that map to stored
				// text blocks--see MailUtil class
				// TODO: Should be centralized.
				if (theData.getEvent().equals(Constants.Admin.Actions.ASSIGN_SCREENER)) {
					theMailSubject = "You have been assigned screener for the following model: "
							+ theData.getModelDescriptor();
					theRecipients = theAssignee;
					theMailStandardText = new String[] { Constants.Admin.Actions.ASSIGN_SCREENER };
				} else if (theData.getEvent().equals(Constants.Admin.Actions.ASSIGN_EDITOR)) {
					theMailSubject = "You have been assigned editor for the following model: "
							+ theAnimalModel.getModelDescriptor();
					theRecipients = theAssignee;
					theMailStandardText = new String[] { Constants.Admin.Actions.ASSIGN_EDITOR };
				} else if (theData.getEvent().equals(Constants.Admin.Actions.NEED_MORE_INFO)) {
					theMailSubject = "The editor is requesting more information for the following model: "
							+ theAnimalModel.getModelDescriptor();
					theRecipients = theCoordinator;
					theMailStandardText = new String[] { Constants.Admin.Actions.NEED_MORE_INFO };
				} else if (theData.getEvent().equals(Constants.Admin.Actions.SCREENER_REJECT)) {
					theMailSubject = "The following model has been rejected: " + theAnimalModel.getModelDescriptor();
					theRecipients = theCoordinator;
					theMailStandardText = new String[] { Constants.Admin.Actions.SCREENER_REJECT };
				} else if (theData.getEvent().equals(Constants.Admin.Actions.SCREENER_APPROVE)) {
					theRecipients = theCoordinator;
					theMailSubject = "The following model has been approved: " + theAnimalModel.getModelDescriptor();
					theMailStandardText = new String[] { Constants.Admin.Actions.SCREENER_APPROVE };
				} else if (theData.getEvent().equals(Constants.Admin.Actions.EDITOR_APPROVE)) {
					theRecipients = theCoordinator;
					theMailSubject = "The following model has been approved: " + theAnimalModel.getModelDescriptor();
					theMailStandardText = new String[] { Constants.Admin.Actions.EDITOR_APPROVE };
				} else if (theData.getEvent().equals(Constants.Admin.Actions.COMPLETE)) {
					theRecipients = theCoordinator;
					theMailSubject = "The following model has been completed: " + theAnimalModel.getModelDescriptor();
					theMailStandardText = new String[] { Constants.Admin.Actions.COMPLETE };
				} else {
					theMailSubject = "The following model has changed: " + theAnimalModel.getModelDescriptor();
					theMailStandardText = new String[] {};
				}

				if (theRecipients.length > 0) {

					// gather variable values to build the e-mail content with
					TreeMap valuesForVariables = new TreeMap();
					valuesForVariables.put("name", theAnimalModel.getSubmitter().getDisplayName());
					valuesForVariables.put("submitter", theAnimalModel.getSubmitter().getDisplayName());
					valuesForVariables.put("modelstate", theAnimalModel.getState());
					valuesForVariables.put("species", theAnimalModel.getSpecies());
					valuesForVariables.put("piname", theAnimalModel.getPrincipalInvestigator().getDisplayName());

					// launch the email
					MailUtil.sendMail(theRecipients, theMailSubject, theMailText, UserManagerSingleton.instance()
							.getEmailForCoordinator(), theMailStandardText, valuesForVariables);
				} else {
					log.warn("No e-mail address assigned to user: " + thePerson.getUsername());
				}

			} catch (Exception e) {
				log.error("Error sending e-mail:", e);
			}
		} else {
			log.error("Missing argument formdata");
		}
		log.trace("Exiting execute");
	}
}