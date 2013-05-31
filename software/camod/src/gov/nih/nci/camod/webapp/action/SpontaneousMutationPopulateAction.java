/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: SpontaneousMutationPopulateAction.java,v 1.14 2007-10-31 18:03:30 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2007/04/04 13:19:08  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.12  2007/03/26 12:02:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.11  2006/11/09 17:31:28  pandyas
 * Commented out debug code
 *
 * Revision 1.10  2006/05/04 19:27:37  pandyas
 * Changed GeneticAlterationCollection to GeneticAlteration relationship from SpontaneousMutation and InducedMutation objects
 *
 * Revision 1.9  2006/04/20 18:12:11  pandyas
 * Modified save of Genetic Alteration
 *
 * Revision 1.8  2006/04/19 17:38:57  pandyas
 * Removed TODO text
 *
 * Revision 1.7  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.GeneIdentifier;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.SpontaneousMutation;
import gov.nih.nci.camod.service.impl.SpontaneousMutationManagerSingleton;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SpontaneousMutationPopulateAction extends BaseAction {

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.debug("<SpontaneousMutationPopulateAction populate> Entering populate() ");
        SpontaneousMutationForm spontaneousMutationForm = (SpontaneousMutationForm) form;

        String aSpontaneousMutationID = request.getParameter("aSpontaneousMutationID");
        SpontaneousMutation theSpontaneousMutation = SpontaneousMutationManagerSingleton.instance().get(
                aSpontaneousMutationID);

        if (theSpontaneousMutation == null) {
        	request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {

            request.setAttribute("aSpontaneousMutationID", aSpontaneousMutationID);
            spontaneousMutationForm.setName(theSpontaneousMutation.getName());
            spontaneousMutationForm.setComments(theSpontaneousMutation.getComments());
            
            // Set GeneIdentifier attribute 
            if (theSpontaneousMutation.getGeneIdentifier() != null) {
                spontaneousMutationForm.setGeneIdentifier(theSpontaneousMutation.getGeneIdentifier().getEntrezGeneID());
            }            

            // Set GeneticAlteration attributes 
            if (theSpontaneousMutation.getGeneticAlteration() != null) {
                spontaneousMutationForm.setObservation(theSpontaneousMutation.getGeneticAlteration().getObservation());
                spontaneousMutationForm.setMethodOfObservation(theSpontaneousMutation.getGeneticAlteration()
                        .getMethodOfObservation());
            }

            MutationIdentifier inMutationIdentifier = theSpontaneousMutation.getMutationIdentifier();
            if (inMutationIdentifier != null){
            	if (inMutationIdentifier.getMgiId() != null && inMutationIdentifier.getMgiId().length() > 0) {
            		spontaneousMutationForm.setMgiId(inMutationIdentifier.getMgiId());
            	}
            	if (inMutationIdentifier.getZfinId() != null && inMutationIdentifier.getZfinId().length() > 0) {
            		spontaneousMutationForm.setZfinId(inMutationIdentifier.getZfinId());
                }
            	if (inMutationIdentifier.getRgdId() != null && inMutationIdentifier.getRgdId().length() > 0) {
            		spontaneousMutationForm.setRgdId(inMutationIdentifier.getRgdId());
                }              	
            }
            

        }

		saveToken(request);

        return mapping.findForward("submitSpontaneousMutation");
    }

    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.debug("<SpontaneousMutationPopulateAction dropdown> Entering dropdown() ");

		saveToken(request);
        return mapping.findForward("submitSpontaneousMutation");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("<SpontaneousMutationPopulateAction dropdown> Entering void dropdown()");
        log.debug("<SpontaneousMutationPopulateAction dropdown> Exiting void dropdown()");
    }

}
