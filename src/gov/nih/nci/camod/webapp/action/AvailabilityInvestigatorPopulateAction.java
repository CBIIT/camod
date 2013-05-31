/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *
 * @author pandyas
 * 
 * $Id: AvailabilityInvestigatorPopulateAction.java,v 1.7 2005-12-08 21:44:47 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/11/03 13:59:10  georgeda
 * Fixed delete functionality
 *
 * Revision 1.5  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.4  2005/10/28 17:45:10  georgeda
 * PI no longer required
 *
 * Revision 1.3  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.2  2005/10/27 16:27:06  georgeda
 * More validation
 *
 * Revision 1.1  2005/10/26 20:14:34  pandyas
 * implemented model availability
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.impl.AvailabilityManagerSingleton;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.webapp.form.AvailabilityForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AvailabilityInvestigatorPopulateAction extends BaseAction {

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.info("<AvailabilityInvestigatorPopulateAction populate> Entering ");

        // Create a form to edit
        AvailabilityForm availabilityForm = (AvailabilityForm) form;

        // Grab the current Availability we are working with related to this
        // animalModel
        String aAvailabilityID = request.getParameter("aAvailabilityID");

        AnimalAvailability avilablity = AvailabilityManagerSingleton.instance().get(aAvailabilityID);
        log.info("avilablity (id and name): " + avilablity);

        if (avilablity == null) {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {

            request.setAttribute("aAvailabilityID", aAvailabilityID);
            // display strin name
            availabilityForm.setName(avilablity.getName());
            availabilityForm.setStockNumber("");

            if (avilablity.getStockNumber() != null && avilablity.getStockNumber().length() > 0) {
                try {
                    // Hack to work around caMOD mapping issue
                    if (avilablity.getStockNumber().equals("-1")) {

                        log.info("Old 2-tier format.  Setting the stock number to the PI");

                        // Get the PI from the model
                        String theModelID = "" + request.getSession().getAttribute(Constants.MODELID);
                        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                        AnimalModel theAnimalModel = theAnimalModelManager.get(theModelID);

                        availabilityForm.setStockNumber(theAnimalModel.getPrincipalInvestigator().getUsername());

                    } else {

                        // get the username from the PI-id stored under
                        // stock_number
                        // in
                        // AnimalAvailability table
                        Person thePerson = PersonManagerSingleton.instance().get(avilablity.getStockNumber());
                        availabilityForm.setStockNumber(thePerson.getUsername());
                    }
                } catch (Exception e) {
                    log.error("Unable to get person for availability", e);
                }
            }
        }

        // setup dropdown menus
        this.dropdown(request, response);

        log.info("<AvailabilityInvestigatorPopulateAction populate> Exiting ");

        return mapping.findForward("submitInvestigator");
    }

    /**
     * Populate the dropdown menus for 4 submission screens for Animal
     * Availability
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.info("<AvailabilityInvestigatorPopulateAction ActionForward dropdown> Entering ");

        // blank out the FORMDATA Constant field
        AvailabilityForm availabilityForm = (AvailabilityForm) form;

        String theSource = (String) request.getParameter("lab");
        availabilityForm.setSource(theSource);

        // setup dropdown menus
        this.dropdown(request, response);

        log.info("<AvailabilityInvestigatorPopulateAction ActionForward dropdown> Exiting ");

        return mapping.findForward("submitInvestigator");

    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("<AvailabilityInvestigatorPopulateAction dropdown> Entering ");

        // Prepopulate dropdown field on submitInvestigator screen only
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PRINCIPALINVESTIGATORDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);
    }

}
