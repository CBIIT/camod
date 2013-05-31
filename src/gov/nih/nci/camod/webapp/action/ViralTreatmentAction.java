/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: ViralTreatmentAction.java,v 1.12 2005-11-09 00:17:25 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/11/02 21:48:09  georgeda
 * Fixed validate
 *
 * Revision 1.10  2005/11/02 19:02:08  pandyas
 * Added e-mail functionality
 *
 * Revision 1.9  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
 * Revision 1.8  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.7  2005/10/20 20:40:39  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.ViralTreatmentForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * ViralTreatmentAction Class
 */

public class ViralTreatmentAction extends BaseAction {

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

		System.out.println("<ViralTreatmentAction edit> Entering... ");
		
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		// Grab the current Therapy we are working with related to this
		// animalModel
		String aTherapyID = request.getParameter("aTherapyID");

		ViralTreatmentForm viralTreatmentForm = (ViralTreatmentForm) form;

		System.out.println("<ViralTreatmentAction editing> editing... " + "\n\t name: " + viralTreatmentForm.getName()
				+ "\n\t otherName: " + viralTreatmentForm.getOtherName() + "\n\t type: " + viralTreatmentForm.getType()
				+ "\n\t regimen: " + viralTreatmentForm.getRegimen() + "\n\t dosage: " + viralTreatmentForm.getDosage()
				+ "\n\t doseUnit: " + viralTreatmentForm.getDoseUnit() + "\n\t ageAtTreatment: "
				+ viralTreatmentForm.getAgeAtTreatment() + "\n\t ageUnit: " + viralTreatmentForm.getAgeUnit());

		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {
	        // retrieve animal model by it's id	        
	        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
	        AnimalModel theAnimalModel = theAnimalModelManager.get(modelID); 			
			
            if ("Delete".equals(theAction)) {
				therapyManager.remove(aTherapyID, theAnimalModel);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("viralTreatment.delete.successful"));
				saveErrors(request, msg);

			} else {

				Therapy theTherapy = therapyManager.get(aTherapyID);
				therapyManager.update(theAnimalModel, viralTreatmentForm, theTherapy);

				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("viralTreatment.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to get add a chemical drug action: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

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

		System.out.println("<ViralTreatmentAction save> Entering... ");

		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		ViralTreatmentForm viralTreatmentForm = (ViralTreatmentForm) form;

		System.out.println("<GrowthFactorAction save> Adding... " + "\n\t name: " + viralTreatmentForm.getName()
				+ "\n\t otherName: " + viralTreatmentForm.getOtherName() + "\n\t type: " + viralTreatmentForm.getType()
				+ "\n\t regimen: " + viralTreatmentForm.getRegimen() + "\n\t dosage: " + viralTreatmentForm.getDosage()
				+ "\n\t doseUnit: " + viralTreatmentForm.getDoseUnit() + "\n\t ageAtTreatment: "
				+ viralTreatmentForm.getAgeAtTreatment() + "\n\t ageUnit: " + viralTreatmentForm.getAgeUnit());

		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addTherapy(animalModel, viralTreatmentForm);

			// Add a message to be displayed in submitOverview.jsp saying you've
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("viralTreatment.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Unable to get add a chemical drug action: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}

}
