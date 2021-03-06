/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: SearchTableOfContentsPopulateAction.java,v 1.3 2009-06-11 17:42:53 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.impl.TOCManager;
import gov.nih.nci.camod.util.SafeHTMLUtil;

import java.util.Enumeration;
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

        log.info("In SearchTableOfContentsPopulateAction.populate");

    	// get and clean header to prevent SQL injection
        String sID = null;
        if (request.getHeader("X-Forwarded-For") != null){
        	sID = request.getHeader("X-Forwarded-For");
            log.info("cleaned X-Forwarded-For: " + sID);
            sID = SafeHTMLUtil.clean(sID);
        }
        
        // Clean all headers for security scan (careful about what chars you allow)
    	String headername = "";
    	for(Enumeration e = request.getHeaderNames(); e.hasMoreElements();){
    		headername = (String)e.nextElement();
    		log.info("SearchTableOfContentsPopulateAction headername: " + headername);
    		String cleanHeaders = SafeHTMLUtil.clean(headername);
    		log.info("SearchTableOfContentsPopulateAction cleaned headername: " + headername);
    	}     	


        // Get the curation manager workflow XML
        TOCManager theTOCManager = new TOCManager(getServlet().getServletContext().getRealPath("/")
                + Constants.TOCSearch.TOC_QUERY_FILE);

        List theResults = theTOCManager.process();

        request.getSession().setAttribute(Constants.TOCSearch.TOC_QUERY_RESULTS, theResults);
        
        log.info("Exiting SearchTableOfContentsPopulateAction.populate");
        return mapping.findForward("next");
    }

}
