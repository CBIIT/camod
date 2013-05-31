/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: LoginAction.java,v 1.9 2005-12-06 19:51:39 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2005/12/06 14:50:45  georgeda
 * Defect #253, change the lowecase to the login action so that roles match
 *
 * Revision 1.7  2005/11/28 18:33:35  georgeda
 * Defect #104.  Clicking on a model and then using the backarrow works after login
 *
 * Revision 1.6  2005/09/22 15:18:13  georgeda
 * More changes
 *
 * Revision 1.5  2005/09/16 15:52:55  georgeda
 * Changes due to manager re-write
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.impl.UserManagerSingleton;
import gov.nih.nci.camod.webapp.form.LoginForm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public final class LoginAction extends BaseAction {

    /**
     * Authenticates the user using the CSM AuthenticationManager and stores in
     * the session.
     * 
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        LoginForm loginForm = (LoginForm) form;

        log.debug("Logon Username: " + loginForm.getUsername());
        log.debug("System Config file is: " + System.getProperty("gov.nih.nci.security.configFile"));

        String theUsername = loginForm.getUsername().toLowerCase();
        
        // check login credentials using Authentication Mangager
        boolean loginOK = UserManagerSingleton.instance().login(theUsername, loginForm.getPassword(), request);

        String forward = "failure";

        if (loginOK) {
            log.debug("Successful login");
            forward = "success";
            request.getSession().setAttribute(Constants.CURRENTUSER, theUsername);
        } else {
            log.debug("Login failed");
            request.getSession().setAttribute(Constants.LOGINFAILED, "true");
        }

        // Forward control to the specified success URI
        return mapping.findForward(forward);
    }
}