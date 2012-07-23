/**
 * $Id: GrowthFactorAction.java,v 1.15 2008-08-14 16:52:02 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2007/10/31 17:10:13  pandyas
 * Modified comments for #8355 	Add comments field to every submission page
 *
 * Revision 1.13  2006/10/27 16:34:55  pandyas
 * fixed printout on error - typo
 *
 * Revision 1.12  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.11  2005/11/09 00:17:26  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.10  2005/11/02 21:48:09  georgeda
 * Fixed validate
 *
 * Revision 1.9  2005/11/02 19:02:08  pandyas
 * Added e-mail functionality
 *
 * Revision 1.8  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
 * Revision 1.7  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.6  2005/10/19 19:26:19  pandyas
 * added admin route to growth factor
 *
 * Revision 1.5  2005/09/28 21:20:12  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.GrowthFactorForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * GrowthFactorAction Class
 */

public class GrowthFactorAction extends BaseAction {

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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		
		if (!isTokenValid(request)) {
			return mapping.findForward("failure");
		}
		

        // Grab the current CarcinogenExposure we are working with related to this animalModel
        String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");
		
		GrowthFactorForm growthFactorForm = (GrowthFactorForm) form;

        log.info("<GrowthFactorAction editing> editing... " + "\n\t name: " + growthFactorForm.getName()
				+ "\n\t otherName: " + growthFactorForm.getOtherName() + "\n\t type: " + growthFactorForm.getType()
				+ "\n\t regimen: " + growthFactorForm.getRegimen() + "\n\t dosage: " + growthFactorForm.getDosage()
				+ "\n\t dosageUnit: " + growthFactorForm.getDosageUnit() + "\n\t ageAtTreatment: "
				+ growthFactorForm.getAgeAtTreatment() + "\n\t Comments: " + growthFactorForm.getComments()
				+ "\n\t ageAtTreatmentUnit: " + growthFactorForm.getAgeAtTreatmentUnit());

        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

            // Grab the current modelID from the session
            String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            if ("Delete".equals(theAction)) {
                
                carcinogenExposureManager.remove(aCarcinogenExposureID, theAnimalModel);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("growthFactor.delete.successful"));
				saveErrors(request, msg);

			} else {

                CarcinogenExposure theCarcinogenExposure = carcinogenExposureManager.get(aCarcinogenExposureID);
                carcinogenExposureManager.update(theAnimalModel, growthFactorForm, theCarcinogenExposure);


				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("growthFactor.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to get add a growth factor action: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}
		resetToken(request);

		return mapping.findForward("AnimalModelTreePopulateAction");
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		
		if (!isTokenValid(request)) {
			return mapping.findForward("failure");
		}


		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		GrowthFactorForm growthFactorForm = (GrowthFactorForm) form;

        log.info("<GrowthFactorAction save> Adding... " + "\n\t name: " + growthFactorForm.getName()
				+ "\n\t otherName: " + growthFactorForm.getOtherName() + "\n\t type: " + growthFactorForm.getType()
				+ "\n\t regimen: " + growthFactorForm.getRegimen() + "\n\t dosage: " + growthFactorForm.getDosage()
				+ "\n\t dosageUnit: " + growthFactorForm.getDosageUnit() + "\n\t administrativeRoute: "
				+ growthFactorForm.getAdministrativeRoute() + "\n\t ageAtTreatment: "
				+ growthFactorForm.getAgeAtTreatment() + "\n\t ageAtTreatmentUnit: " + growthFactorForm.getAgeAtTreatmentUnit()
				+ "\n\t Comments: " + growthFactorForm.getComments()
				+ "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addCarcinogenExposure(animalModel, growthFactorForm);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("growthFactor.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Unable to get add an environmental factor: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}
		resetToken(request);

		return mapping.findForward("AnimalModelTreePopulateAction");
	}
}
