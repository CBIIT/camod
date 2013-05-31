/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.impl.TOCManager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SearchTableOfContentsPopulateAction extends BaseAction {

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("In SearchTableOfContentsPopulateAction.populate");

        // Get the curation manager workflow XML
        TOCManager theTOCManager = new TOCManager(getServlet().getServletContext().getRealPath("/")
                + Constants.TOCSearch.TOC_QUERY_FILE);

        List theResults = theTOCManager.process();

        request.getSession().setAttribute(Constants.TOCSearch.TOC_QUERY_RESULTS, theResults);

        return mapping.findForward("next");
    }

}
