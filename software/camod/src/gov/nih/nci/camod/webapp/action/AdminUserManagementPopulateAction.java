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
 * $Id: AdminUserManagementPopulateAction.java,v 1.2 2005-10-24 13:28:17 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/17 13:29:00  georgeda
 * Initial revision
 *
 * Revision 1.2  2005/10/10 14:10:48  georgeda
 * Changes for comment curation
 *
 * Revision 1.1  2005/09/19 19:53:51  georgeda
 * New model assignment functionality
 *
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * Used to populate the form used to assign roles to a user.
 * 
 */
public class AdminUserManagementPopulateAction extends BaseAction {

	/**
	 * Action used to populate the various admin lists for the curation process
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering execute");

		String theForward = "next";
		try {
			NewDropdownUtil.populateDropdown(inRequest, Constants.Dropdowns.USERSDROP, "");
		} catch (Exception e) {

			theForward = "failure";

			log.error("Unable to get roles for user");

			// Encountered an error 
			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(inRequest, theMsg);
		}

		log.trace("Exiting execute");

		return inMapping.findForward(theForward);
	}
}