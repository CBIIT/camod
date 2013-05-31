/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: DrugScreenSearchAction.java,v 1.7 2005-11-22 15:17:16 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/11/17 20:57:17  georgeda
 * Defect #117, handle back arrow gracefully
 *
 * Revision 1.5  2005/11/15 22:13:46  georgeda
 * Cleanup of drug screening
 *
 * Revision 1.4  2005/11/11 21:24:19  georgeda
 * Defect #29.  Separate drug screening and animal model search results.
 *
 * Revision 1.3  2005/11/10 22:07:36  georgeda
 * Fixed part of bug #21
 *
 * Revision 1.2  2005/10/05 20:28:00  guruswas
 * implementation of drug screening search page
 *
 * Revision 1.1  2005/09/30 18:42:24  guruswas
 * intial implementation of drug screening search and display page
 *
 * Revision 1.2  2005/09/16 15:52:56  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.camod.service.impl.QueryManagerSingleton;
import gov.nih.nci.camod.webapp.form.DrugScreenSearchForm;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.hibernate.HQLParameter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Hibernate;

/**
 * 
 * Action used to implement the search for animal models
 *
 */
public final class DrugScreenSearchAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        DrugScreenSearchForm theForm = (DrugScreenSearchForm) form;
        log.info("the form=" + theForm);
        request.getSession().setAttribute(Constants.DRUG_SCREEN_OPTIONS, theForm);

		// The following two objects are needed for eQBE.
        try {
			HQLParameter[] theParams = new HQLParameter[1];
			theParams[0] = new HQLParameter();
			theParams[0].setName("nscNumber");
			theParams[0].setValue(new Long(theForm.getNSCNumber()));
			theParams[0].setType(Hibernate.LONG);
		
			String theHQLQuery = "from Agent where nsc_number = :nscNumber";
			log.debug("The HQL query: " + theHQLQuery);
			List agents = Search.query(theHQLQuery, theParams);
            
            request.getSession().setAttribute(Constants.DRUG_SCREEN_SEARCH_RESULTS, agents);
			final int agtCount = (agents!=null)?agents.size():0;
			AgentManager myAgentManager = (AgentManager)getBean("agentManager");
			final HashMap clinProtocols = new HashMap();
			final HashMap yeastResults = new HashMap();
			final HashMap invivoResults = new HashMap();
			final HashMap preClinicalResults = new HashMap();
            String theAgentName = null;
            String theCasNumber = null;
            
            Long theNscNumber = new Long(theForm.getNSCNumber());
            
			for(int i=0; i<agtCount; i++) {
				Agent a = (Agent)agents.get(i);
				if (a != null) {
                    theNscNumber = a.getNscNumber();
                    
                    // Get the name
                    if (theAgentName == null) {
                        theAgentName = a.getName();
                    }
                    
                    // Get the cas number
                    if (theCasNumber == null) {
                        theCasNumber = a.getCasNumber();
                    }
                    
					if (theNscNumber != null) {
						if (theForm.isDoClinical()) {
							Collection protocols = myAgentManager.getClinicalProtocols(a);
							clinProtocols.put(theNscNumber, protocols);
						}
						if (theForm.isDoPreClinical()) {
							try {
								List models = QueryManagerSingleton.instance().getModelsForThisCompound(theNscNumber);
								preClinicalResults.put(a.getId(), models);
							}catch (Exception x) {
								x.printStackTrace();
							}
						}
						if (theForm.isDoYeast()) {
							// get the yeast data
							List yeastStages = myAgentManager.getYeastResults(a, false);
							yeastResults.put(a.getId(), yeastStages);
						}
						if (theForm.isDoInvivo()) {
							// now get invivo/Xenograft data
							List xenograftResults = QueryManagerSingleton.instance().getInvivoResults(a, false);
							invivoResults.put(a.getId(), xenograftResults);
						}
					}
				}
			}
			request.getSession().setAttribute(Constants.CLINICAL_PROTOCOLS, clinProtocols);
			request.getSession().setAttribute(Constants.YEAST_DATA, yeastResults);
			request.getSession().setAttribute(Constants.INVIVO_DATA, invivoResults);
			request.getSession().setAttribute(Constants.PRECLINICAL_MODELS, preClinicalResults);
            request.getSession().setAttribute("agentName", theAgentName);
            request.getSession().setAttribute("casNumber", theCasNumber);
            request.getSession().setAttribute("nscNumber", theNscNumber);
        } catch (Exception e) {
            log.error("Exception occurred in DrugScreenSearchAction.execute()", e);
            request.getSession().setAttribute(Constants.DRUG_SCREEN_SEARCH_RESULTS, new ArrayList());
            // Set the error message
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }
        log.trace("Exiting DrugScreenSearchAction.execute");
        // Forward control to the specified success URI
        return mapping.findForward("next");
    }
}