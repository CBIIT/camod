/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: NutritionalFactorAction.java,v 1.15 2008-08-14 16:55:06 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2007/10/31 17:12:01  pandyas
 * Modified comments for #8355 	Add comments field to every submission page
 *
 * Revision 1.13  2006/10/27 16:34:38  pandyas
 * fixed printout on error - typo
 *
 * Revision 1.12  2006/04/17 19:09:41  pandyas
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
 * Revision 1.6  2005/10/20 20:39:04  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.NutritionalFactorForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * NutritionalFactorAction Class
 * 
 */
public class NutritionalFactorAction extends BaseAction {

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
		
		// Create a form to edit
		NutritionalFactorForm nutritForm = (NutritionalFactorForm) form;

        log.info("<NutritionalFactorAction save> following Characteristics:" + "\n\t name: "
				+ nutritForm.getName() + "\n\t otherName: " + nutritForm.getOtherName() + "\n\t dosage: "
				+ nutritForm.getDosage()  + "\n\t dosageUnit: " + nutritForm.getDosageUnit()
                + "\n\t regimen: " + nutritForm.getRegimen() + "\n\t ageAtTreatment: "
                + nutritForm.getAgeAtTreatmentUnit()
				+ nutritForm.getAgeAtTreatment() + "\n\t type: " + nutritForm.getType() 
				+ "\n\t Comments: " + nutritForm.getComments()+ "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

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
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("nutritionalfactor.delete.successful"));
				saveErrors(request, msg);

			} else {
               
                CarcinogenExposure theCarcinogenExposure = carcinogenExposureManager.get(aCarcinogenExposureID);
                carcinogenExposureManager.update(theAnimalModel, nutritForm, theCarcinogenExposure);                

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("nutritionalfactor.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to get add a nutritional factor action: ", e);

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
		
		NutritionalFactorForm nutritForm = (NutritionalFactorForm) form;

        log.info("<NutritionalFactorAction save> following Characteristics:" + "\n\t name: "
				+ nutritForm.getName() + "\n\t otherName: " + nutritForm.getOtherName() + "\n\t dosage: "
				+ nutritForm.getDosage()  + "\n\t dosageUnit: " + nutritForm.getDosageUnit()
                + "\n\t regimen: " + nutritForm.getRegimen() + "\n\t ageAtTreatment: "
				+ nutritForm.getAgeAtTreatment() + "\n\t type: " + nutritForm.getType() 
				+ "\n\t Comments: " + nutritForm.getComments()+ "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		/* Grab the current modelID from the session */
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		/* Create all the manager objects needed for Screen */
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

		/* Set modelID in AnimalModel object */
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addCarcinogenExposure(animalModel, nutritForm);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("nutritionalfactor.creation.successful"));
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
