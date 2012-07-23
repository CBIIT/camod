/**
 * 
 * $Id: SpontaneousMutationAction.java,v 1.13 2007-10-31 17:13:01 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.11  2007/07/23 17:38:26  pandyas
 * Fixed typo in word occurred
 *
 * Revision 1.10  2007/04/04 13:19:07  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.9  2007/03/28 20:53:18  pandyas
 * Added print for rgd and zfin numbers in save and edit methods
 *
 * Revision 1.8  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.SpontaneousMutation;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.SpontaneousMutationManager;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * SpontaneousMutationAction Class
 */

public class SpontaneousMutationAction extends BaseAction {

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

        log.trace("Entering edit");
		if (!isTokenValid(request)) {
			return mapping.findForward("failure");
		}

        // Grab the current SpontaneousMutation we are working with related to
        // this
        String aSpontaneousMutationID = request.getParameter("aSpontaneousMutationID");
        
        SpontaneousMutationForm spontaneousMutationForm = (SpontaneousMutationForm) form;
        
        log.debug("<SpontaneousMutationAction edit> following Characteristics:" + "\n\t name: "
                + spontaneousMutationForm.getName() + "\n\t getMgiId: "
                + spontaneousMutationForm.getMgiId() + "\n\t getRgdId: "
                + spontaneousMutationForm.getRgdId()+ "\n\t getZfinId: "
                + spontaneousMutationForm.getZfinId()         
                + "\n\t getObservation: "
                + spontaneousMutationForm.getObservation() + "\n\t getMethodofObservation: "
                + spontaneousMutationForm.getMethodOfObservation() +  "\n\t getGeneIdentifier: "                       
                + spontaneousMutationForm.getGeneIdentifier() +  "\n\t getComments: "
                + spontaneousMutationForm.getComments()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));      
        
        

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try {


            SpontaneousMutationManager spontaneousMutationManager = (SpontaneousMutationManager) getBean("spontaneousMutationManager");

            if ("Delete".equals(theAction)) {

                // Grab the current modelID from the session
                String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

                spontaneousMutationManager.remove(aSpontaneousMutationID, theAnimalModel);

                log.debug("SpontaneousMutation deleted");

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("spontaneousmutation.delete.successful"));
                saveErrors(request, msg);

            } else {

                SpontaneousMutation theSpontaneousMutation = spontaneousMutationManager.get(aSpontaneousMutationID);
                spontaneousMutationManager.update(spontaneousMutationForm, theSpontaneousMutation);

                log.debug("SpontaneousMutation edited");

                // Add a message to be displayed in submitOverview.jsp saying
                // you've
                // created a new model successfully
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("spontaneousmutation.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (Exception e) {
            log.error("Exception occurred creating SpontaneousMutation", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting edit");
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

        log.trace("Entering save");
		if (!isTokenValid(request)) {
			return mapping.findForward("failure");
		}

        // Create a form to edit
        SpontaneousMutationForm spontaneousMutationForm = (SpontaneousMutationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, spontaneousMutationForm);

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.debug("<SpontaneousMutationAction save> following Characteristics:" + "\n\t name: "
                + spontaneousMutationForm.getName() + "\n\t getMgiId: " + spontaneousMutationForm.getMgiId()
                + "\n\t getRgdId: "
                + spontaneousMutationForm.getRgdId()+ "\n\t getZfinId: "
                + spontaneousMutationForm.getZfinId()  
                + "\n\t getObservation: " + spontaneousMutationForm.getObservation() + "\n\t getMethodofObservation: "
                + spontaneousMutationForm.getMethodOfObservation() + "\n\t getComments: "
                + spontaneousMutationForm.getComments()+  "\n\t getGeneIdentifier: "                       
                + spontaneousMutationForm.getGeneIdentifier() 
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGeneticDescription(theAnimalModel, spontaneousMutationForm);

            log.debug("New SpontaneousMutation created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("spontaneousmutation.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception occurred creating SpontaneousMutation", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
		resetToken(request);

        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}
