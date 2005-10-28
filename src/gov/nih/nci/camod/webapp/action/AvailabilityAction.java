/**
 *
 * @author pandyas
 * 
 * $Id: AvailabilityAction.java,v 1.3 2005-10-28 12:47:26 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/10/26 20:14:34  pandyas
 * implemented model availability
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.AvailabilityManager;
import gov.nih.nci.camod.webapp.form.AvailabilityForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * AvailabilityAction Class
 */
public class AvailabilityAction extends BaseAction {

	/**
	 * Edit
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("<AvailabilityAction> Entering edit");

		// Create a form to edit
		AvailabilityForm availabilityForm = (AvailabilityForm) form;

		// Grab the current aAvailabilityID from the session
		String aAvailabilityID = request.getParameter("aAvailabilityID");

		System.out.println("<AvailabilityAction edit> following Characteristics:" + "\n\t name: "
				+ availabilityForm.getName() + "\n\t stockNuber: " + availabilityForm.getStockNumber() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		/* Create all the manager objects needed for Screen */
		AvailabilityManager availabilityManager = (AvailabilityManager) getBean("availabilityManager");

		String theForward = "AnimalModelTreePopulateAction";

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

			if (theAction.equals("Delete")) {
				availabilityManager.remove(aAvailabilityID);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("availability.delete.successful"));
				saveErrors(request, msg);

			} else {

				AnimalAvailability theAvailability = availabilityManager.get(aAvailabilityID);

				availabilityManager.update(availabilityForm, theAvailability);

				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("availability.creation.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {
			log.error("Exception ocurred creating Availability", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, msg);

			theForward = "failure";
		}

		log.info("<AvailabilityAction> Exiting edit");
		return mapping.findForward(theForward);
	}

	/**
	 * Save
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("<AvailabilityAction> Entering save");

		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		// Create a form to edit
		AvailabilityForm availabilityForm = (AvailabilityForm) form;

		log.info("<AvailabilityAction edit> following Characteristics:" + "\n\t name: " + availabilityForm.getName()
				+ "\n\t stockNuber: " + availabilityForm.getStockNumber() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		/* Create all the manager objects needed for Screen */
		AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");

		/* Set modelID in AnimalModel object */
		AnimalModel animalModel = theAnimalModelManager.get(modelID);

		try {
			theAnimalModelManager.addAvailability(animalModel, availabilityForm);

			log.info("New Availability created");

			// Add a message to be displayed in submitOverview.jsp saying you've
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("availability.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {
			log.error("Exception ocurred creating Availability", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, msg);
		}

		log.trace("<AvailabilityAction> Exiting save");
		return mapping.findForward("AnimalModelTreePopulateAction");
	}

}
