package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TargetedModificationManager;
import gov.nih.nci.camod.webapp.form.TargetedModificationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * TargetedModificationAction Class
 */
public final class TargetedModificationAction extends BaseAction {
	
    /**
     * Delete
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        return mapping.findForward("");
    }

	/**
	 * Cancel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward duplicate(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
    	
    	 return mapping.findForward("");
    }    
    
    
    /**
     * Edit
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
    	
        log.trace("Entering edit");

        TargetedModificationForm targetedModificationForm = (TargetedModificationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, targetedModificationForm);
                
        // Grab the current SpontaneousMutation we are working with related to this
        String aTargetedModificationID = request.getParameter("aTargetedModificationID");

        log.info("<TargetedModificationAction save> following Characteristics:" 
        		+ "\n\t getName: " + targetedModificationForm.getName()
                + "\n\t getModificationType: " + targetedModificationForm.getModificationType()              
                + "\n\t getOtherModificationType: " + targetedModificationForm.getOtherModificationType() 
                + "\n\t getGeneId: " + targetedModificationForm.getGeneId()
                + "\n\t getEsCellLineName: " + targetedModificationForm.getEsCellLineName()
                + "\n\t getBlastocystName: " + targetedModificationForm.getBlastocystName()
                + "\n\t getConditionedBy: " + targetedModificationForm.getConditionedBy()
                + "\n\t getDescription: " + targetedModificationForm.getDescription()
                + "\n\t getComments: " + targetedModificationForm.getComments()
                + "\n\t getNumberMGI: " + targetedModificationForm.getNumberMGI()
                + "\n\t getFileServerLocation: " + targetedModificationForm.getFileServerLocation()
                + "\n\t getTitle: " + targetedModificationForm.getTitle()
                + "\n\t getDescriptionOfConstruct: " + targetedModificationForm.getDescriptionOfConstruct()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));


        TargetedModificationManager targetedModificationManager = (TargetedModificationManager) getBean("targetedModificationManager");
        
        try {
        	
        	TargetedModification theTargetedModification = targetedModificationManager.get( aTargetedModificationID );
        	targetedModificationManager.update( targetedModificationForm, theTargetedModification, request );
        	
            log.info( "TargetedModification edited" );

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "targetedmodification.edit.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating SpontaneousMutation", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting edit");
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

        // Create a form to edit
        TargetedModificationForm targetedModificationForm = (TargetedModificationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, targetedModificationForm);
             
        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<TargetedModificationAction save> following Characteristics:" 
        		+ "\n\t getName: " + targetedModificationForm.getName()
                + "\n\t getModificationType: " + targetedModificationForm.getModificationType()              
                + "\n\t getOtherModificationType: " + targetedModificationForm.getOtherModificationType() 
                + "\n\t getGeneId: " + targetedModificationForm.getGeneId()
                + "\n\t getEsCellLineName: " + targetedModificationForm.getEsCellLineName()
                + "\n\t getBlastocystName: " + targetedModificationForm.getBlastocystName()
                + "\n\t getConditionedBy: " + targetedModificationForm.getConditionedBy()
                + "\n\t getDescription: " + targetedModificationForm.getDescription()
                + "\n\t getComments: " + targetedModificationForm.getComments()
                + "\n\t getNumberMGI: " + targetedModificationForm.getNumberMGI()
                + "\n\t getFileServerLocation: " + targetedModificationForm.getFileServerLocation()
                + "\n\t getTitle: " + targetedModificationForm.getTitle()
                + "\n\t getDescriptionOfConstruct: " + targetedModificationForm.getDescriptionOfConstruct()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            theAnimalModelManager.addGeneticDescription( theAnimalModel, targetedModificationForm, request );

            log.info("New TargetedModification created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("targetedmodification.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating TargetedModification", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}