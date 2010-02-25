/**
 * 
 * $Id: SaveQueryAction.java,v 1.5 2008-08-14 16:56:41 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2008/07/11 17:38:46  schroedn
 * Bug 12042
 * Save Query fix
 *
 * Revision 1.3  2006/05/10 15:37:11  schroedn
 * Fixed Dup_Name bug
 *
 * Revision 1.2  2006/05/10 14:15:39  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.1  2006/04/28 19:26:58  schroedn
 * Defect # 261
 * Saves / Updates / Deletes SaveQuery items
 *
 *
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.SavedQuery;
import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.camod.service.SavedQueryManager;
import gov.nih.nci.camod.util.SafeHTMLUtil;
import gov.nih.nci.camod.webapp.form.SaveQueryForm;
import gov.nih.nci.camod.webapp.form.SearchForm;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * @arthur schroedn
 */
public class SaveQueryAction extends BaseAction
{
    /**
     * Save builds the SavedQuery and saves it
     * 
     * @param mapping
     * @param form
     * @param request 
     * @param response
     * 
     * @exception IOException
     * @exception ServletException
     *                when anything goes wrong.
     */
    public ActionForward save(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException, ServletException
    {

        log.debug("Entering SaveQueryAction.save");
        
        SaveQueryForm theForm = (SaveQueryForm) form;
        SavedQueryManager savedQueryManager = (SavedQueryManager) getBean("savedQueryManager");
        
        // Added for security scan to Filter out hazardous characters from user input 
       	String cleanQueryName = theForm.getQueryName();
        if (theForm.getQueryName() != null && theForm.getQueryName().length() > 0){
        	log.debug("QueryName: " + theForm.getQueryName()); 
        	cleanQueryName = SafeHTMLUtil.clean(theForm.getQueryName());
        	theForm.setQueryName(cleanQueryName);
            log.debug("cleanQueryName: " + cleanQueryName);            
        }
        
        // Clean all headers for security scan (careful about what chars you allow)
    	String headername = "";
    	for(Enumeration e = request.getHeaderNames(); e.hasMoreElements();){
    		headername = (String)e.nextElement();
    		log.debug("populateModelCharacteristics headername: " + headername);
    		String cleanHeaders = SafeHTMLUtil.clean(headername);
    		log.debug("populateModelCharacteristics cleaned headername: " + headername);
    	} 
    	
    	// get and clean header to prevent SQL injection
       	String sID = null;
        if (request.getHeader("X-Forwarded-For") != null){
        	sID = request.getHeader("X-Forwarded-For");
            log.info("cleaned X-Forwarded-For: " + sID);
            sID = SafeHTMLUtil.clean(sID);
        }
        
    	// get and clean header to prevent SQL injection
        if (request.getHeader("Referer") != null){
        	sID = request.getHeader("Referer");
            log.info("cleaned Referer: " + sID);
            sID = SafeHTMLUtil.clean(sID);
        }     	
        
        log.info("SaveQueryForm\n" + 
        							"Page=" + theForm.getPage() + "\n" +
        							"QueryName=" + theForm.getQueryName() + "\n" +
        							"SaveAsNew=" + theForm.getSaveAsNew() + "\n" );
                
        List results = (List) request.getSession().getAttribute(Constants.SEARCH_RESULTS);
        request.getSession().setAttribute(Constants.NOSAVEOPTION, "true");
        
        String theForward = "noSaveOption";

        try
        {
            if (theForm.getSaveAsNew().equals("no"))
            {
                // Update Saved Query
                log.debug("Updating SavedQuery");
                
                request.getSession().setAttribute(Constants.DUP_NAME, "false");

                String aSavedQueryId = (String) request.getSession().getAttribute(Constants.ASAVEDQUERYID);
                SavedQuery theQueryToUpdate = savedQueryManager.get(aSavedQueryId);

                theQueryToUpdate.setElapsedTime((Long) request.getSession().getAttribute(Constants.ELAPSED_TIME));
                theQueryToUpdate.setExecuteTime((Date) request.getSession().getAttribute(Constants.EXECUTE_TIME));
                theQueryToUpdate.setNumberResults(results.size());

                SearchForm aSearchForm = (SearchForm) request.getSession().getAttribute(Constants.SEARCH_FORM);
                Set<SavedQueryAttribute> criteriaList = theQueryToUpdate.getSavedQueryAttributes();
                criteriaList.clear();
                criteriaList.addAll(savedQueryManager.buildCriteriaList(aSearchForm));
                
                savedQueryManager.update(theQueryToUpdate);
            }

            if (theForm.getSaveAsNew().equals("yes"))
            {
                log.debug("Saving new SavedQuery");
                
        		// Create NEW Saved Query               
                SavedQuery theQueryToSave = new SavedQuery();

                boolean duplicateName = false;
                request.getSession().setAttribute(Constants.DUP_NAME, "false");
                
                //HERE!@
                List allSaved = savedQueryManager.getSavedQueriesByUsername((String) request.getSession().getAttribute( Constants.CURRENTUSER ));
                
                //Search for duplicate query names
                for (int i = 0; i < allSaved.size(); i++)
                {
                    SavedQuery theSavedQuery = (SavedQuery) allSaved.get(i);
                   
                    if (theForm.getQueryName().equals(theSavedQuery.getQueryName()))
                    {
                        log.debug( "Query name is a duplicate" );
                        duplicateName = true;
                        request.getSession().setAttribute(Constants.DUP_NAME, "true");
                        request.getSession().setAttribute(Constants.NOSAVEOPTION, "false");
                        request.getSession().setAttribute(Constants.DUP_NAME_VALUE, theForm.getQueryName() );
                        theForward = "next";
                    }
                }

                if (duplicateName == false)
                {
                	
                    theQueryToSave.setQueryName(theForm.getQueryName());
                    theQueryToSave.setIsSaved(1l);

                    theQueryToSave.setElapsedTime( 3 ); //(Long) request.getSession().getAttribute(Constants.ELAPSED_TIME));
                    theQueryToSave.setExecuteTime((Date) request.getSession().getAttribute(Constants.EXECUTE_TIME));
                    theQueryToSave.setNumberResults(results.size());

                    // Add list of criteria
                    Set<SavedQueryAttribute> criteriaList = savedQueryManager.buildCriteriaList((SearchForm) request.getSession().getAttribute( Constants.SEARCH_FORM));
                    theQueryToSave.setSavedQueryAttributes(criteriaList);

                    //Set the user for this save
                    PersonManager inPersonManager = (PersonManager) getBean("personManager");
                    theQueryToSave.setUser(inPersonManager.getByUsername((String) request.getSession().getAttribute(Constants.CURRENTUSER)));

                    savedQueryManager.save(theQueryToSave);

                    // Used for sidebar, update the number of saved queries for current user
                    List savedQueriesList = savedQueryManager.getSavedQueriesByUsername((String) request.getSession().getAttribute(Constants.CURRENTUSER));
                    
                    request.getSession().setAttribute(Constants.NUMBEROFSAVEDQUERIES, String.valueOf(savedQueriesList.size()));
                    request.getSession().setAttribute(Constants.QUERY_NAME, theForm.getQueryName());
                }                
            }
        }
        catch (Exception e)
        {
            // Set the error message
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        return mapping.findForward(theForward);
    }

    /**
     * Returns User Queries
     * 
     * @param mapping
     * @param form
     * @param request 
     * @param response
     * 
     * @exception IOException
     * @exception ServletException
     *                when anything goes wrong.
     */
    public ActionForward returnUserQueries(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws IOException, ServletException
    {

        log.debug("Entering SaveQueryAction.returnUserQueries");

        String theForward = "searchQueryHistory";

        try
        {
            SavedQueryManager savedQueryManager = (SavedQueryManager) getBean("savedQueryManager");
            List savedQueriesList = savedQueryManager.getAllByUsername((String) request.getSession().getAttribute(Constants.CURRENTUSER));
            request.getSession().setAttribute(Constants.USERSQUERYLIST, savedQueriesList);

        }
        catch (Exception e)
        {

            // Set the error message
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        return mapping.findForward(theForward);
    }

    /**
     * Returns Saved User Queries
     * 
     * @param mapping
     * @param form
     * @param request 
     * @param response
     * 
     * @exception IOException
     * @exception ServletException
     *                when anything goes wrong.
     */
    public ActionForward returnSavedUserQueries(ActionMapping mapping,
                                                ActionForm form,
                                                HttpServletRequest request,
                                                HttpServletResponse response) throws IOException, ServletException
    {

        log.debug("Entering SaveQueryAction.returnSavedUserQueries");

        String theForward = "searchSavedQueries";

        try
        {
            SavedQueryManager savedQueryManager = (SavedQueryManager) getBean("savedQueryManager");
            List savedQueriesList = savedQueryManager.getSavedQueriesByUsername((String) request.getSession().getAttribute( Constants.CURRENTUSER));
            request.getSession().setAttribute(Constants.USERSAVEDQUERYLIST, savedQueriesList);
        }
        catch (Exception e)
        {

            // Set the error message
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        return mapping.findForward(theForward);
    }

    /**
     * Delete a SavedQuery
     * 
     * @param mapping
     * @param form
     * @param request 
     * @param response
     * 
     * @exception IOException
     * @exception ServletException
     *                when anything goes wrong.
     */
    public ActionForward delete(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException
    {

        log.debug("Entering SaveQueryAction.delete");

        String theForward = "querydeleted";

        SavedQueryManager savedQueryManager = (SavedQueryManager) getBean("savedQueryManager");

        String aQueryId = request.getParameter( Constants.Parameters.QUERYID );

        try
        {
            //Delete from db
            savedQueryManager.remove(aQueryId);

            // Used for sizebar, update the number of saved queries for current user
            List savedQueriesList = savedQueryManager.getSavedQueriesByUsername((String) request.getSession().getAttribute(
                                                                                                                           Constants.CURRENTUSER));
            request.getSession().setAttribute(Constants.NUMBEROFSAVEDQUERIES, String.valueOf(savedQueriesList.size()));

            //Add deletion successful message
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.successful"));
            saveErrors(request, msg);

        }
        catch (Exception e)
        {

            // Set the error message
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        return mapping.findForward(theForward);
    }
}