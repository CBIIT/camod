/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: EngineeredTransgeneAction.java,v 1.16 2007-09-12 19:36:40 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.15  2007/07/31 12:13:42  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.14  2007/07/23 17:40:42  pandyas
 * Fixed typo in word occurred
 *
 * Revision 1.13  2007/04/04 13:19:07  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.12  2007/03/26 12:02:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.11  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.EngineeredTransgeneManager;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * EngineeredTransgeneAction Class
 */
public final class EngineeredTransgeneAction extends BaseAction {

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

        // Create a form to edit
        EngineeredTransgeneForm engineeredTransgeneForm = (EngineeredTransgeneForm) form;

        // Grab the current modelID from the session
        String aEngineeredTransgeneID = engineeredTransgeneForm.getTransgeneId();

        log.debug("<EngineeredTransgeneAction save> following Characteristics:"

                 + "\n\t getIsRandom: " + engineeredTransgeneForm.getIsRandom()
                 + "\n\t getLocationOfIntegration: " + engineeredTransgeneForm.getLocationOfIntegration()
                 + "\n\t getName: " + engineeredTransgeneForm.getName() + "\n\t getScientificName: "
                + engineeredTransgeneForm.getScientificName() + "\n\t getOtherScientificName: "
                + engineeredTransgeneForm.getOtherScientificName()

                + "\n\t getTranscriptional1_name: " + engineeredTransgeneForm.getTranscriptional1_name()
                + "\n\t getTranscriptional1_species: " + engineeredTransgeneForm.getTranscriptional1_species()
                + "\n\t getTranscriptional1_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional1_otherSpecies()

                + "\n\t getTranscriptional2_name: " + engineeredTransgeneForm.getTranscriptional2_name()
                + "\n\t getTranscriptional2_species: " + engineeredTransgeneForm.getTranscriptional2_species()
                + "\n\t getTranscriptional2_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional2_otherSpecies()

                + "\n\t getTranscriptional3_name: " + engineeredTransgeneForm.getTranscriptional3_name()
                + "\n\t getTranscriptional3_species: " + engineeredTransgeneForm.getTranscriptional3_species()
                + "\n\t getTranscriptional3_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional3_otherSpecies()

                + "\n\t getPolyASignal_name: " + engineeredTransgeneForm.getPolyASignal_name()
                + "\n\t getPolyASignal_species: " + engineeredTransgeneForm.getPolyASignal_species()
                + "\n\t getPolyASignal_otherSpecies: " + engineeredTransgeneForm.getPolyASignal_otherSpecies()

                + "\n\t getSpliceSites_name: " + engineeredTransgeneForm.getSpliceSites_name()
                + "\n\t getSpliceSites_species: " + engineeredTransgeneForm.getSpliceSites_species()
                + "\n\t getSpliceSites_otherSpecies: " + engineeredTransgeneForm.getSpliceSites_otherSpecies()

                + "\n\t getGeneFunctions: " + engineeredTransgeneForm.getGeneFunctions() 
                + "\n\t getMgiId: " + engineeredTransgeneForm.getMgiId()
                + "\n\t getZfinId: " + engineeredTransgeneForm.getRgdId()
                + "\n\t getRgdId: " + engineeredTransgeneForm.getMgiId()                
                + "\n\t getFunction: " + engineeredTransgeneForm.getFunction()
                + "\n\t getConditionedBy: " + engineeredTransgeneForm.getConditionedBy() + "\n\t getDescription: "
                + engineeredTransgeneForm.getDescription() + "\n\t getComments: "
                + engineeredTransgeneForm.getComments() + "\n\t getUrl: "
                + engineeredTransgeneForm.getUrl() + "\n\t getTitle: "
                + engineeredTransgeneForm.getTitle() + "\n\t getDescription: "
                + engineeredTransgeneForm.getDescription()

                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try {

            EngineeredTransgeneManager engineeredTransgeneManager = (EngineeredTransgeneManager) getBean("engineeredTransgeneManager");

            if ("Delete".equals(theAction)) {

                // Grab the current modelID from the session
                String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

                engineeredTransgeneManager.remove(aEngineeredTransgeneID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("engineeredtransgene.delete.successful"));
                saveErrors(request, msg);

            } else {

                log.debug("EngineeredTransgene edit");
                // retrieve model and update w/ new values

                Transgene theEngineeredTransgene = engineeredTransgeneManager.get(aEngineeredTransgeneID);

                engineeredTransgeneManager.update(engineeredTransgeneForm, theEngineeredTransgene, request);

                // Add a message to be displayed in submitOverview.jsp saying
                // you've
                // created a new model successfully
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("engineeredtransgene.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (IllegalArgumentException e) {
            log.error("Exception ocurred editing an EngineeredTransgene", e);

            theForward = "input";

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.image.unsupportedfiletype"));
            saveErrors(request, msg);
        } catch (Exception e) {
            log.error("Exception ocurred editing EngineeredTransgene", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting edit");
		resetToken(request);

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

        log.trace("Entering save");
        if (!isTokenValid(request)) {
			return mapping.findForward("failure");
		}

        // Create a form to edit
        EngineeredTransgeneForm engineeredTransgeneForm = (EngineeredTransgeneForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.debug("<EngineeredTransgeneAction save> following Characteristics:"
                + "\n\t getIsRandom: " + engineeredTransgeneForm.getIsRandom()
                + "\n\t getLocationOfIntegration: " + engineeredTransgeneForm.getLocationOfIntegration()
                + "\n\t getName: " + engineeredTransgeneForm.getName() + "\n\t getScientificName: "
                + engineeredTransgeneForm.getScientificName() + "\n\t getOtherScientificName: "
                + engineeredTransgeneForm.getOtherScientificName()

                + "\n\t getTranscriptional1_name: " + engineeredTransgeneForm.getTranscriptional1_name()
                + "\n\t getTranscriptional1_species: " + engineeredTransgeneForm.getTranscriptional1_species()
                + "\n\t getTranscriptional1_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional1_otherSpecies()

                + "\n\t getTranscriptional2_name: " + engineeredTransgeneForm.getTranscriptional2_name()
                + "\n\t getTranscriptional2_species: " + engineeredTransgeneForm.getTranscriptional2_species()
                + "\n\t getTranscriptional2_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional2_otherSpecies()

                + "\n\t getTranscriptional3_name: " + engineeredTransgeneForm.getTranscriptional3_name()
                + "\n\t getTranscriptional3_species: " + engineeredTransgeneForm.getTranscriptional3_species()
                + "\n\t getTranscriptional3_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional3_otherSpecies()

                + "\n\t getPolyASignal_name: " + engineeredTransgeneForm.getPolyASignal_name()
                + "\n\t getPolyASignal_species: " + engineeredTransgeneForm.getPolyASignal_species()
                + "\n\t getPolyASignal_otherSpecies: " + engineeredTransgeneForm.getPolyASignal_otherSpecies()

                + "\n\t getSpliceSites_name: " + engineeredTransgeneForm.getSpliceSites_name()
                + "\n\t getSpliceSites_species: " + engineeredTransgeneForm.getSpliceSites_species()
                + "\n\t getSpliceSites_otherSpecies: " + engineeredTransgeneForm.getSpliceSites_otherSpecies()

                + "\n\t getGeneFunctions: " + engineeredTransgeneForm.getGeneFunctions() 
                + "\n\t getMgiId: " + engineeredTransgeneForm.getMgiId()
                + "\n\t getZfinId: " + engineeredTransgeneForm.getZfinId() 
                + "\n\t getRgdId: " + engineeredTransgeneForm.getRgdId() 
                + "\n\t getFunction: " + engineeredTransgeneForm.getFunction()
                + "\n\t getConditionedBy: " + engineeredTransgeneForm.getConditionedBy() + "\n\t getDescription: "
                + engineeredTransgeneForm.getDescription() + "\n\t getComments: "
                + engineeredTransgeneForm.getComments() + "\n\t getUrl: "
                + engineeredTransgeneForm.getUrl() + "\n\t getTitle: "
                + engineeredTransgeneForm.getTitle() + "\n\t getDescription: "
                + engineeredTransgeneForm.getDescription()

                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGeneticDescription(theAnimalModel, engineeredTransgeneForm, request);

            log.debug("New EngineeredTransgene created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("engineeredtransgene.creation.successful"));
            saveErrors(request, msg);

        } catch (IllegalArgumentException e) {
            log.error("Exception ocurred saving an EngineeredTransgene", e);

            theForward = "input";

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.image.unsupportedfiletype"));
            saveErrors(request, msg);
        } catch (Exception e) {
            log.error("Exception occurred creating EngineeredTransgene", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
		resetToken(request);

        return mapping.findForward(theForward);
    }
}