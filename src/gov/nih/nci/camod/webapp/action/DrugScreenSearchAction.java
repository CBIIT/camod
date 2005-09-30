/**
 * 
 * $Id: DrugScreenSearchAction.java,v 1.1 2005-09-30 18:42:24 guruswas Exp $
 * 
 * $Log: not supported by cvs2svn $
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
/*        Agent agt = new Agent();
		Long myNSCNumber = new Long(theForm.getNSCNumber());
		agt.setNscNumber(myNSCNumber);
		List agents = null;
*/        try {
			HQLParameter[] theParams = new HQLParameter[1];
			theParams[0] = new HQLParameter();
			theParams[0].setName("nscNumber");
			theParams[0].setValue(new Long(theForm.getNSCNumber()));
			theParams[0].setType(Hibernate.LONG);
		
			String theHQLQuery = "from Agent where nsc_number = :nscNumber";
			log.debug("The HQL query: " + theHQLQuery);
			List agents = Search.query(theHQLQuery, theParams);

			//agents = Search.query(agt);
            request.getSession().setAttribute(Constants.SEARCH_RESULTS, agents);
			final int agtCount = (agents!=null)?agents.size():0;
			AgentManager myAgentManager = (AgentManager)getBean("agentManager");
			final HashMap clinProtocols = new HashMap();
			final HashMap yeastResults = new HashMap();
			final HashMap invivoResults = new HashMap();
			for(int i=0; i<agtCount; i++) {
				Agent a = (Agent)agents.get(i);
				if (a != null) {
					Long nscNumber = a.getNscNumber();
					if (nscNumber != null) {
						if (theForm.isDoClinical()) {
							Collection protocols = myAgentManager.getClinicalProtocols(a);
							clinProtocols.put(nscNumber, protocols);
						}
						if (theForm.isDoYeast()) {
							// get the yeast data
							List yeastStages = myAgentManager.getYeastResults(a);
							yeastResults.put(nscNumber, yeastStages);
						}
						if (theForm.isDoInvivo()) {
							// now get invivo/Xenograft data
							List xenograftResults = QueryManagerSingleton.instance().getInvivoResults(a);
							invivoResults.put(nscNumber, xenograftResults);
						}
					}
				}
			}
			request.getSession().setAttribute(Constants.CLINICAL_PROTOCOLS, clinProtocols);
			request.getSession().setAttribute(Constants.YEAST_DATA, yeastResults);
			request.getSession().setAttribute(Constants.INVIVO_DATA, invivoResults);
        } catch (Exception e) {
            log.error("Exception occurred in DrugScreenSearchAction.execute()", e);
            request.getSession().setAttribute(Constants.SEARCH_RESULTS, new ArrayList());
            // Set the error message
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }
        log.trace("Exiting DrugScreenSearchAction.execute");
        // Forward control to the specified success URI
        return mapping.findForward("searchResultsDrugScreen");
    }
}