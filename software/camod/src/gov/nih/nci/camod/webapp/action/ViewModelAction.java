/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *  @author sguruswami
 *  
 *  $Id: ViewModelAction.java,v 1.69 2009-06-01 17:02:46 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.68  2009/05/20 17:16:34  pandyas
 *  modified for gforge #17325 Upgrade caMOD to use caBIO 4.x and EVS 4.x to get data
 *
 *  Revision 1.67  2009/03/25 16:24:58  pandyas
 *  modified for #17833  	Make sure all references to Tranplantation are properly named
 *
 *  Revision 1.66  2009/03/13 17:03:46  pandyas
 *  modified for #19205  	Sort therapies in the order they are entered
 *
 *  Revision 1.65  2008/08/14 17:07:03  pandyas
 *  remove debug line
 *
 *  Revision 1.64  2008/08/14 17:01:42  pandyas
 *  modified debug line to use log
 *
 *  Revision 1.63  2008/08/01 14:15:10  pandyas
 *  Modifed to prevent SQL inject - added HTTP Header clean
 *  App scan performed on July 30, 2008
 *
 *  Revision 1.62  2008/07/28 17:19:02  pandyas
 *  Modifed to prevent SQL inject - added HTTP Header
 *  App scan performed on July 24, 2008
 *
 *  Revision 1.61  2008/07/21 18:08:31  pandyas
 *  Modified to prevent SQL injection
 *  Scan performed on July 21, 2008
 *
 *  Revision 1.60  2008/07/17 19:05:26  pandyas
 *  Modified to clean header to prevent SQL injection/Cross-Site Scripting
 *  Scan performed on July 16, 2008 by IRT
 *
 *  Revision 1.59  2008/06/30 18:18:28  pandyas
 *  Removed code originally added for security scan when it caused null pointer errors
 *
 *  Revision 1.58  2008/06/30 15:29:05  pandyas
 *  Modified to prevent Cross-Site Scripting
 *  Cleaned parameter name before proceeding
 *  Fixed code added in previous version
 *
 *  Revision 1.57  2008/05/27 14:36:40  pandyas
 *  Modified to prevent SQL injection
 *  Cleaned HTTP Header before proceeding
 *  Re: Apps Scan run 05/23/2008
 *
 *  Revision 1.56  2008/02/05 17:10:09  pandyas
 *  Removed debug statement for build to dev
 *
 *  Revision 1.55  2008/02/05 17:09:34  pandyas
 *  Removed debug statement for build to dev
 *
 *  Revision 1.54  2008/01/31 22:27:52  pandyas
 *  remove log printouts now that bug is resolved
 *
 *  Revision 1.53  2008/01/31 22:23:22  pandyas
 *  remove log printouts now that bug is resolved
 *
 *  Revision 1.52  2008/01/31 17:09:54  pandyas
 *  Modified to send new gene identifier (entrez gene id) to caBIO from new object location
 *
 *  Revision 1.51  2008/01/28 18:45:18  pandyas
 *  Modified to debug caBIO data not returning to caMOD on dev
 *
 *  Revision 1.50  2008/01/16 20:09:31  pandyas
 *  removed caBIO logging so the page renders when connection to caBIO fails
 *
 *  Revision 1.49  2008/01/16 18:29:57  pandyas
 *  Renamed value to Transplant for #8290
 *
 *  Revision 1.48  2008/01/10 15:55:01  pandyas
 *  modify output for final dev deployment
 *
 *  Revision 1.47  2008/01/02 17:57:44  pandyas
 *  modified for #816  	Connection to caELMIR - retrieve data for therapy search page
 *
 *  Revision 1.46  2007/12/27 22:32:33  pandyas
 *  Modified  for feature #8816  	Connection to caELMIR - retrieve data for therapy search page
 *  Also added code to display Therapy link when only caELMIR data is available for a study
 *
 *  Revision 1.45  2007/12/27 21:44:00  pandyas
 *  re-commit - changes did not show up in project
 *
 *  Revision 1.44  2007/12/18 13:31:32  pandyas
 *  Added populate method for study data from caELMIRE for integration of Therapy study data
 *
 *  Revision 1.43  2007/12/17 18:03:22  pandyas
 *  Removed * in searchFilter used for getting e-mail from LDAP
 *  Apps Support ticket was submitted (31169 - incorrect e-mail associated with my caMOD account) stating:
 *
 *  Cheryl Marks submitted a ticket to NCICB Application Support in which she requested that the e-mail address associated with her account in the "User Settings" screen in caMOD be corrected. She has attempted to correct it herself, but because the program queries the LDAP Server for the e-mail address, her corrections were not retained.
 *
 *  Revision 1.42  2007/12/04 13:49:19  pandyas
 *  Modified code for #8816  	Connection to caELMIR - retrieve data for therapy search page
 *
 *  Revision 1.41  2007/11/25 23:34:23  pandyas
 *  Initial version for feature #8816  	Connection to caELMIR - retrieve data for therapy search page
 *
 *  Revision 1.40  2007/10/31 18:39:30  pandyas
 *  Fixed #8188 	Rename UnctrlVocab items to text entries
 *  Fixed #8290 	Rename graft object into transplant object
 *
 *  Revision 1.39  2007/09/14 18:53:37  pandyas
 *  Fixed Bug #8954:  link to invivo detail page does not work
 *
 *  Revision 1.38  2007/09/12 19:36:40  pandyas
 *  modified debug statements for build to stage tier
 *
 *  Revision 1.37  2007/08/07 19:49:46  pandyas
 *  Removed reference to Transplant as per VCDE comments and after modification to object definition for CDE
 *
 *  Revision 1.36  2007/08/07 18:26:20  pandyas
 *  Renamed to GRAFT as per VCDE comments
 *
 *  Revision 1.35  2007/07/31 12:02:55  pandyas
 *  VCDE silver level  and caMOD 2.3 changes
 *
 *  Revision 1.34  2007/06/19 20:42:59  pandyas
 *  Users not logged in can not access the session property to check the model species.  Therefore, we must show the attribute for all models.
 *
 *  Revision 1.33  2007/06/19 18:39:21  pandyas
 *  Constant for species common name needs to be set for viewModelCharacteristics so it shows up for Zebrafish models
 *
 *  Revision 1.32  2006/08/17 18:10:44  pandyas
 *  Defect# 410: Externalize properties files - Code changes to get properties
 *
 *  Revision 1.31  2006/05/24 18:37:27  georgeda
 *  Workaround for bug in caBIO
 *
 *  Revision 1.30  2006/05/09 18:57:54  georgeda
 *  Changes for searching on transient interfaces
 *
 *  Revision 1.29  2006/05/08 13:43:15  georgeda
 *  Reformat and clean up warnings
 *
 *  Revision 1.28  2006/04/19 19:31:58  georgeda
 *  Fixed display issue w/ GeneDelivery
 *
 *  Revision 1.27  2006/04/19 18:50:01  georgeda
 *  Fixed issue w/ engineered genes displaying
 *
 *  Revision 1.26  2006/04/17 19:09:41  pandyas
 *  caMod 2.1 OM changes
 *
 *  Revision 1.25  2005/11/21 18:38:31  georgeda
 *  Defect #35.  Trim whitespace from items that are freeform text
 *
 *  Revision 1.24  2005/11/15 22:13:46  georgeda
 *  Cleanup of drug screening
 *
 *  Revision 1.23  2005/11/14 14:21:44  georgeda
 *  Added sorting and spontaneous mutation
 *
 *  Revision 1.22  2005/11/11 18:39:30  georgeda
 *  Removed unneeded call
 *
 *  Revision 1.21  2005/11/10 22:07:36  georgeda
 *  Fixed part of bug #21
 *
 *  Revision 1.20  2005/11/10 18:12:23  georgeda
 *  Use constant
 *
 *  Revision 1.19  2005/11/07 13:57:39  georgeda
 *  Minor tweaks
 *
 *  Revision 1.18  2005/11/03 15:47:11  georgeda
 *  Fixed slow invivo results
 *
 *  Revision 1.17  2005/10/27 18:13:48  guruswas
 *  Show all publications in the publications display page.
 *
 *  Revision 1.16  2005/10/20 21:35:37  georgeda
 *  Fixed xenograft display bug
 *
 *  Revision 1.15  2005/10/19 18:56:00  guruswas
 *  implemented invivo details page
 *
 *  Revision 1.14  2005/10/11 18:15:25  georgeda
 *  More comment changes
 *
 *  Revision 1.13  2005/10/10 14:12:24  georgeda
 *  Changes for comment curation
 *
 *  Revision 1.12  2005/10/07 21:15:03  georgeda
 *  Added caarray variables
 *
 *  Revision 1.11  2005/10/06 13:37:01  georgeda
 *  Removed informational message
 *
 *  Revision 1.10  2005/09/30 18:42:24  guruswas
 *  intial implementation of drug screening search and display page
 *
 *  Revision 1.9  2005/09/22 21:34:51  guruswas
 *  First stab at carcinogenic intervention pages
 *
 *  Revision 1.8  2005/09/22 15:23:41  georgeda
 *  Cleaned up warnings
 *
 *  Revision 1.7  2005/09/21 21:02:24  guruswas
 *  Display the organ, disease names from NCI Thesaurus
 *
 *  Revision 1.6  2005/09/21 20:47:16  georgeda
 *  Cleaned up
 *
 *  Revision 1.5  2005/09/16 19:30:00  guruswas
 *  Display invivo data (from DTP) in the therapuetic approaches page
 *
 *  Revision 1.4  2005/09/16 15:52:56  georgeda
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import edu.wustl.common.util.CaElmirInterfaceManager;
import gov.nih.nci.camod.biodbnet.Gene;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CaelmirStudyData;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.domain.ClinicalTrialProtocol;
import gov.nih.nci.camod.domain.Comments;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.GeneIdentifier;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.domain.MicroArrayData;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.Transplantation;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.domain.SpontaneousMutation;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CommentsManager;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.camod.service.TransplantationManager;
import gov.nih.nci.camod.service.impl.QueryManagerSingleton;
import gov.nih.nci.camod.util.CtrpIntegration;
import gov.nih.nci.camod.util.SafeHTMLUtil;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.camod.biodbnet.BioDBnetService;
import gov.nih.nci.camod.biodbnet.Db2DbParams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewModelAction extends BaseAction
{

    /**
     * sets the cancer model object in the session
     * 
     * @param request
     *            the httpRequest
     */
    private void setCancerModel(HttpServletRequest request)
    {
        String modelID = request.getParameter(Constants.Parameters.MODELID);
        log.debug("<setCancerModel> modelID: " + modelID);
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel am = null;
        try
        {
            am = animalModelManager.get(modelID);
        }
        catch (Exception e)
        {
            log.error("Unable to get cancer model in setCancerModel");
            e.printStackTrace();
        }
        request.getSession().setAttribute(Constants.ANIMALMODEL, am);
        // Set model id to display on subViewModelMenu on left menu bar
        request.getSession().setAttribute(Constants.MODELID, am.getId().toString());        
    }

    /**
     * sets the cancer model object in the session
     * 
     * @param request
     *            the httpRequest
     * @throws Exception
     */
    private void setComments(HttpServletRequest request,
                             String inSection) throws Exception
    {

        String theCommentsId = request.getParameter(Constants.Parameters.COMMENTSID);

        CommentsManager theCommentsManager = (CommentsManager) getBean("commentsManager");

        log.debug("Comments id: " + theCommentsId);
        List<Comments> theCommentsList = new ArrayList<Comments>();
        if (theCommentsId != null && theCommentsId.length() > 0)
        {
            Comments theComments = theCommentsManager.get(theCommentsId);
            if (theComments != null)
            {
                log.debug("Found a comment: " + theComments.getRemark());
                theCommentsList.add(theComments);
            }
        }

        // Get all comments that are either approved or owned by this user
        else
        {
            PersonManager thePersonManager = (PersonManager) getBean("personManager");
            Person theCurrentUser = thePersonManager.getByUsername((String) request.getSession().getAttribute(Constants.CURRENTUSER));

            AnimalModel theAnimalModel = (AnimalModel) request.getSession().getAttribute(Constants.ANIMALMODEL);

            theCommentsList = theCommentsManager.getAllBySection(inSection, theCurrentUser, theAnimalModel);
        }

        request.setAttribute(Constants.Parameters.COMMENTSLIST, theCommentsList);
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward populateModelCharacteristics(ActionMapping mapping,
                                                      ActionForm form,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) throws Exception
    {

        request.getSession(true); 
        
        try {  
        
    	// get and clean header to prevent SQL injection
       	String sID = null;
        if (request.getHeader("X-Forwarded-For") != null){
        	sID = request.getHeader("X-Forwarded-For");
            log.debug("cleaned X-Forwarded-For: " + sID);
            sID = SafeHTMLUtil.clean(sID);
        }
        

        // Clean all headers for security scan (careful about what chars you allow)
    	String headername = "";
    	for(Enumeration e = request.getHeaderNames(); e.hasMoreElements();){
    		headername = (String)e.nextElement();
    		log.debug("populateModelCharacteristics headername: " + headername);
    		String cleanHeaders = SafeHTMLUtil.clean(headername);
    		log.debug("populateModelCharacteristics cleaned headername: " + headername);
    	}       
        
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.info("methodName: " + methodName);
        if (!methodName.equals("populateModelCharacteristics")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("cleaned methodName: " + methodName);
        }                
        	
	        setCancerModel(request);
	        setComments(request, Constants.Pages.MODEL_CHARACTERISTICS);
	        
	        // Call method so therapy link displays for models with caELMIR-only data
	        //caELMIR server went down and we experienced performance issues trying to connect
	        //populateCaelmirTherapyDetails(mapping, form, request, response);
        } 
        catch (Exception e)
        {
            log.error("Error in populateModelCharacteristics", e);
        }
        
        return mapping.findForward("viewModelCharacteristics");
    }

    /**
     * 
     */
    public ActionForward populateEngineeredGene(ActionMapping mapping,
                                                ActionForm form,
                                                HttpServletRequest request,
                                                HttpServletResponse response) throws Exception
    {
        log.debug("<populateEngineeredGene> modelID" + request.getParameter("aModelID"));
        
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateEngineeredGene")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }
        
        String modelID = request.getParameter("aModelID");

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel am = animalModelManager.get(modelID);

        final Set egc = am.getEngineeredGeneCollection();
        final int egcCnt = (egc != null) ? egc.size() : 0;
        final List<EngineeredGene> tgc = new ArrayList<EngineeredGene>();
        int tgCnt = 0;// Transgene
        final List<EngineeredGene> gsc = new ArrayList<EngineeredGene>();
        int gsCnt = 0;// GenomicSegment
        final List<EngineeredGene> tmc = new ArrayList<EngineeredGene>();
        int tmCnt = 0;// TargetedModification
        final Map<Long, Gene> tmGeneMap = new HashMap<Long, Gene>();
        final List<EngineeredGene> imc = new ArrayList<EngineeredGene>();
        final List<SpontaneousMutation> smc = new ArrayList<SpontaneousMutation>(am.getSpontaneousMutationCollection());
        Iterator it = egc.iterator();
        int imCnt = 0;// InducedMutation
        
     // Variables for related models
        List modelsByMGIColl = new ArrayList();
        final Map<String, List> mgiAnimalModelMap = new HashMap<String, List>(); 
        
        List modelsByEntrezGeneIdColl = new ArrayList();
        final Map<String, List> entrezGeneAnimalModelMap = new HashMap<String, List>();
        
        List modelsByZFinColl = new ArrayList();
        final Map<String, List> zfinAnimalModelMap = new HashMap<String, List>();
        
        List modelsByRgdColl = new ArrayList();
        final Map<String, List> rgdAnimalModelMap = new HashMap<String, List>(); 
        
        while (it.hasNext())
        {
            EngineeredGene eg = (EngineeredGene) it.next();
            if (eg instanceof Transgene)
            {
                tgc.add(eg);
                tgCnt++;
                
             // Get related models by mutation identifier
                Transgene t = (Transgene) eg;
                populateRelatedMutationModels(modelID, mgiAnimalModelMap,
						zfinAnimalModelMap, rgdAnimalModelMap,
						t);
            }
            else if (eg instanceof GenomicSegment)
            {
                gsc.add(eg);
                gsCnt++;
                
             // Get related models by mutation identifier
                GenomicSegment t = (GenomicSegment) eg;
                populateRelatedMutationModels(modelID, mgiAnimalModelMap,
						zfinAnimalModelMap, rgdAnimalModelMap,
						t);
            }
            else if (eg instanceof TargetedModification)
            {
                tmc.add(eg);
                tmCnt++;
                // now go to caBIO and query the gene object....
                TargetedModification tm = (TargetedModification) eg;
                GeneIdentifier geneIdentifier = tm.getGeneIdentifier();                
                if (geneIdentifier != null)
                {
                    log.info("Connecting to BioDBnet to look up gene " + geneIdentifier);
                    // the geneId is available
                    Gene myGene = new Gene();
                    BioDBnetService bioDBnetService = BioDBnetService.getService();
                    Db2DbParams db2DbParams = bioDBnetService.setupInputs(geneIdentifier.getEntrezGeneID());

                    db2DbParams = bioDBnetService.setupOutput(db2DbParams, BioDBnetService.GENETIC_INFO_ALL);
                    myGene = bioDBnetService.search(myGene, db2DbParams);
                    
                    tmGeneMap.put(tm.getId(), myGene);
            /*
                    try
                    {
                    	CaBioApplicationService appService = (CaBioApplicationService)ApplicationServiceProvider.getApplicationService();

                        log.info("appService: " + appService.toString());
                        DatabaseCrossReference dcr = new DatabaseCrossReference(); 
                        dcr.setCrossReferenceId(geneIdentifier.getEntrezGeneID());

                        dcr.setType("gov.nih.nci.cabio.domain.Gene");
                        dcr.setDataSourceName("LOCUS_LINK_ID");
                        List<DatabaseCrossReference> cfcoll = new ArrayList<DatabaseCrossReference>();
                        cfcoll.add(dcr);
                        log.info("cfcoll.size(): " + cfcoll.size());
                        Gene myGene = new Gene();
                        myGene.setDatabaseCrossReferenceCollection(cfcoll);
                        List resultList = appService.search(Gene.class, myGene);
                        log.info("resultList.size(): " + resultList.size());
                        
                        final int geneCount = (resultList != null) ? resultList.size() : 0;
                        log.info("Got " + geneCount + " Gene Objects");
                        if (geneCount > 0)
                        {
                            myGene = (Gene) resultList.get(0);
                            log.info("Gene:" + geneIdentifier + " ==>" + myGene);
                            tmGeneMap.put(tm.getId(), myGene);
                        }
                    }
                    catch (Exception e)
                    {
                        log.error("ViewModelAction Unable to get information from caBIO", e);
                    }
             */
                    /* Get list of related models by entrez gene id
                    if (geneIdentifier != null)
                    {
                        log.info("ViewModelAction  pubs.size(): " + geneIdentifier.size());
                        Iterator it = geneIdentifier.iterator();
                    	
                    }
                    */
                    
                    modelsByEntrezGeneIdColl = QueryManagerSingleton.instance().getRelatedModelsForThisEntrezGene(geneIdentifier.getEntrezGeneID(), modelID);
	            	log.info("ViewModelAction  modelsByEntrezGeneIdColl: " + modelsByEntrezGeneIdColl.size()); 
	            	entrezGeneAnimalModelMap.put(geneIdentifier.getEntrezGeneID(), modelsByEntrezGeneIdColl); 
	            	
	            	TargetedModification t = (TargetedModification) eg;
	                populateRelatedMutationModels(modelID, mgiAnimalModelMap,
							zfinAnimalModelMap, rgdAnimalModelMap,
							t);

                    
               
                }
            }
            else if (eg instanceof InducedMutation)
            {
                imc.add(eg);
                imCnt++;
                
                InducedMutation t = (InducedMutation) eg;
                populateRelatedMutationModels(modelID, mgiAnimalModelMap,
						zfinAnimalModelMap, rgdAnimalModelMap,
						t);
            }
        }
        
        // Find related models for SpontaneousMutation
        for( SpontaneousMutation sm: smc) {
    		if (sm.getMutationIdentifier() != null && sm.getMutationIdentifier().getMgiId() != null) {
    			// get related models by MGI number  
    			String mgiId= sm.getMutationIdentifier().getMgiId();
    			if(! mgiAnimalModelMap.containsKey(mgiId)) {
    				modelsByMGIColl = QueryManagerSingleton.instance().getRelatedModelsForThisMGI(mgiId, modelID, false);
    				log.info("ViewModelAction  modelsByMGIColl: " + modelsByMGIColl.size()); 
    				mgiAnimalModelMap.put(mgiId, modelsByMGIColl);
    			}
    			
    		} else if (sm.getMutationIdentifier() != null && sm.getMutationIdentifier().getRgdId() != null){
    			// get related models by RGD number
    			String rgdId= sm.getMutationIdentifier().getRgdId();
    			if(! rgdAnimalModelMap.containsKey(rgdId)) {
    				modelsByRgdColl = QueryManagerSingleton.instance().getRelatedModelsForThisRgd(rgdId, modelID, false);
    				log.info("ViewModelAction  modelsByRgdColl: " + modelsByRgdColl.size()); 
    				rgdAnimalModelMap.put(rgdId, modelsByRgdColl);
    			}
    			
    		} else if (sm.getMutationIdentifier() != null && sm.getMutationIdentifier().getZfinId() != null){
    			// get related models by ZFIN number
    			String zfinId= sm.getMutationIdentifier().getZfinId();
    			if(! zfinAnimalModelMap.containsKey(zfinId)) {
    				modelsByZFinColl = QueryManagerSingleton.instance().getRelatedModelsForThisZFin(zfinId, modelID, false);
    				log.info("ViewModelAction  modelsByZFinColl: " + modelsByZFinColl.size()); 
    				zfinAnimalModelMap.put(zfinId, modelsByZFinColl);  
    			}
    		}
        }

        log.info("<populateEngineeredGene> " + "egcCnt=" + egcCnt + "tgc=" + tgCnt + "gsc=" + gsCnt + "tmc=" + tmCnt + "imc=" + imCnt);
        request.getSession().setAttribute(Constants.ANIMALMODEL, am);
        request.getSession().setAttribute(Constants.TRANSGENE_COLL, tgc);
        request.getSession().setAttribute(Constants.GENOMIC_SEG_COLL, gsc);
        request.getSession().setAttribute(Constants.TARGETED_MOD_COLL, tmc);
        request.getSession().setAttribute(Constants.TARGETED_MOD_GENE_MAP, tmGeneMap);
        request.getSession().setAttribute(Constants.INDUCED_MUT_COLL, imc);
        request.getSession().setAttribute(Constants.SPONTANEOUS_MUT_COLL, smc);
        
     // attributes for the related models by mgi, zfin, rgd, and entrez gene id
        request.getSession().setAttribute(Constants.RELATED_MODELS_BY_MGI, mgiAnimalModelMap);
        request.getSession().setAttribute(Constants.RELATED_MODELS_BY_RGD, rgdAnimalModelMap);
        request.getSession().setAttribute(Constants.RELATED_MODELS_BY_ZFIN, zfinAnimalModelMap);
        request.getSession().setAttribute(Constants.RELATED_MODELS_BY_ENTREZ_GENE, entrezGeneAnimalModelMap);
        
        log.debug("<populateEngineeredGene> set attributes done.");

        setComments(request, Constants.Pages.GENETIC_DESCRIPTION);

        return mapping.findForward("viewGeneticDescription");
    }

	private void populateRelatedMutationModels(String modelID,
			Map<String, List> mgiAnimalModelMap,
			Map<String, List> zfinAnimalModelMap,
			Map<String, List> rgdAnimalModelMap, EngineeredGene t)
			throws PersistenceException {
		List modelsByMGIColl = new ArrayList();
		List modelsByZFinColl = new ArrayList();
		List modelsByRgdColl = new ArrayList();
		if (t.getMutationIdentifier() != null && t.getMutationIdentifier().getMgiId() != null) {
			// get related models by MGI number  
			String mgiId= t.getMutationIdentifier().getMgiId();
			if(! mgiAnimalModelMap.containsKey(mgiId)) {
				modelsByMGIColl = QueryManagerSingleton.instance().getRelatedModelsForThisMGI(mgiId, modelID, true);
				log.info("ViewModelAction  modelsByMGIColl: " + modelsByMGIColl.size()); 
				mgiAnimalModelMap.put(mgiId, modelsByMGIColl);
			}
			
		} else if (t.getMutationIdentifier() != null && t.getMutationIdentifier().getRgdId() != null){
			// get related models by RGD number
			String rgdId= t.getMutationIdentifier().getRgdId();
			if(! rgdAnimalModelMap.containsKey(rgdId)) {
				modelsByRgdColl = QueryManagerSingleton.instance().getRelatedModelsForThisRgd(rgdId, modelID, true);
				log.info("ViewModelAction  modelsByRgdColl: " + modelsByRgdColl.size()); 
				rgdAnimalModelMap.put(rgdId, modelsByRgdColl);
			}
			
		} else if (t.getMutationIdentifier() != null && t.getMutationIdentifier().getZfinId() != null){
			// get related models by ZFIN number
			String zfinId= t.getMutationIdentifier().getZfinId();
			if(! zfinAnimalModelMap.containsKey(zfinId)) {
				modelsByZFinColl = QueryManagerSingleton.instance().getRelatedModelsForThisZFin(zfinId, modelID, true);
				log.info("ViewModelAction  modelsByZFinColl: " + modelsByZFinColl.size()); 
				zfinAnimalModelMap.put(zfinId, modelsByZFinColl);  
			}
		}
	}

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateCarcinogenicInterventions(ActionMapping mapping,
                                                           ActionForm form,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) throws Exception
    {
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateCarcinogenicInterventions")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }
        
        setCancerModel(request);
        String modelID = request.getParameter(Constants.Parameters.MODELID);
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel am = animalModelManager.get(modelID);

        final Set ceColl = am.getCarcinogenExposureCollection();
        Iterator it = ceColl.iterator();
        final Map<String, List<Object>> interventionTypeMap = new HashMap<String, List<Object>>();

        while (it.hasNext())
        {
            CarcinogenExposure ce = (CarcinogenExposure) it.next();
            if (ce != null)
            {
                log.debug("Checking agent:" + ce.getEnvironmentalFactor().getNscNumber());
                String theType = ce.getEnvironmentalFactor().getType();
                if (theType == null || theType.length() == 0)
                {
                    theType = ce.getEnvironmentalFactor().getTypeAlternEntry();
                    if (theType == null || theType.length() == 0)
                    {
                        theType = "Not specified";
                    }
                }
                List<Object> theTypeColl = interventionTypeMap.get(theType);
                if (theTypeColl == null)
                {
                    theTypeColl = new ArrayList<Object>();
                    interventionTypeMap.put(theType, theTypeColl);
                }
                theTypeColl.add(ce);
            }
        }

        if (am.getGeneDeliveryCollection().size() > 0)
        {
            List<Object> theGeneDeliveryCollection = new ArrayList<Object>(am.getGeneDeliveryCollection());
            interventionTypeMap.put("GeneDelivery", theGeneDeliveryCollection);
        }

        request.getSession().setAttribute(Constants.CARCINOGENIC_INTERVENTIONS_COLL, interventionTypeMap);

        setComments(request, Constants.Pages.CARCINOGENIC_INTERVENTION);

        return mapping.findForward("viewCarcinogenicInterventions");
    }


    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populatePublications(ActionMapping mapping,
                                              ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws Exception
    {
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populatePublications")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }    	
    	
        setCancerModel(request);
        String modelID = request.getParameter("aModelID");
        List pubs = null;
        List modelsByPMIDColl = new ArrayList();
        final Map<Long, List> pmidAnimalModelMap = new HashMap<Long, List>();

        
        try
        {
            pubs = QueryManagerSingleton.instance().getAllPublications(Long.valueOf(modelID).longValue());
            log.debug("pubs.size(): " + pubs.size());
            
            Iterator it = pubs.iterator();
            
            while (it.hasNext())
            {
            	Publication p = (Publication) it.next();
            	log.info("ViewModelAction  modelID: " + modelID);
            	log.info("ViewModelAction  p.getId().toString(): " + p.getId().toString());
            	if(p != null){
            		Long pmid = p.getPmid();
            		modelsByPMIDColl = QueryManagerSingleton.instance().getRelatedModelsForThisPMID(pmid, modelID);
            		log.info("ViewModelAction  modelsByPMIDColl: " + modelsByPMIDColl.size());           		
            		pmidAnimalModelMap.put(pmid, modelsByPMIDColl);
            	}
            }
        }
        catch (Exception e)
        {
            log.error("Unable to get publications");
            e.printStackTrace();
        }
        request.getSession().setAttribute(Constants.PUBLICATIONS, pubs);
        request.getSession().setAttribute(Constants.RELATED_MODELS_BY_PMID, pmidAnimalModelMap);
        setComments(request, Constants.Pages.PUBLICATIONS);
        return mapping.findForward("viewPublications");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateHistopathology(ActionMapping mapping,
                                                ActionForm form,
                                                HttpServletRequest request,
                                                HttpServletResponse response) throws Exception
    {
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateHistopathology")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }     	
    	
        setCancerModel(request);
        setComments(request, Constants.Pages.HISTOPATHOLOGY);

        return mapping.findForward("viewHistopathology");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateTherapeuticApproaches(ActionMapping mapping,
                                                       ActionForm form,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response) throws Exception
    {
        log.info("<ViewModelAction>  populateTherapeuticApproaches");
        
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateTherapeuticApproaches")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }          
        
        setCancerModel(request);
        //
        // query caBIO and load clinical protocols information
        // store clinicalProtocol info in a hashmap keyed by NSC#
        //
        final HashMap<Long, Collection> clinProtocols = new HashMap<Long, Collection>();
        final HashMap<Long, Collection> yeastResults = new HashMap<Long, Collection>();
        final HashMap<Long, Collection> invivoResults = new HashMap<Long, Collection>();
        final List<Therapy> therapeuticApprochesColl = new ArrayList<Therapy>();
        List modelsByNSCColl = new ArrayList();
        final Map<Long, List> nscAnimalModelMap = new HashMap<Long, List>();

        String modelID = request.getParameter(Constants.Parameters.MODELID);
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel am = animalModelManager.get(modelID);
        final Set therapyColl = am.getTherapyCollection();
        Iterator it = therapyColl.iterator();

        final int cc = (therapyColl != null) ? therapyColl.size() : 0;
        log.info("Looking up clinical protocols for " + cc + " agents...");

        while (it.hasNext())
        {
            Therapy t = (Therapy) it.next();
            log.info("ViewModelAction  NscNumber: " + t.getAgent().getNscNumber());                     

            if (t != null)
            {
            	if(t.getAgent().getNscNumber() !=null){
	            	// get related models by NSC number  
	            	Long nscNumber= t.getAgent().getNscNumber();
	            	modelsByNSCColl = QueryManagerSingleton.instance().getRelatedModelsForThisNSC(nscNumber, modelID);
	            	log.info("ViewModelAction  modelsByNSCColl: " + modelsByNSCColl.size()); 
	            	nscAnimalModelMap.put(nscNumber, modelsByNSCColl);            	
            	}
                therapeuticApprochesColl.add(t);
            }
            // Sort therapy in order entered as requested by user
            Collections.sort(therapeuticApprochesColl);
            log.info("therapeuticApprochesColl: " + therapeuticApprochesColl.toString());
            
            Agent a = t.getAgent();
            AgentManager myAgentManager = (AgentManager) getBean("agentManager");
            if (a != null)
            {
                Long nscNumber = a.getNscNumber();
                if (nscNumber != null)                		                	
                {
                	log.info("nscNumber: " + nscNumber);
                    //Collection protocols = myAgentManager.getClinicalProtocols(a);
                	CtrpIntegration ctrpIntegration = new CtrpIntegration();
					Collection<ClinicalTrialProtocol> protocols = ctrpIntegration.getClinicalProtocols(a.getNscNumber());
					
                    clinProtocols.put(nscNumber, protocols);
                    log.info("clinProtocols.size(): " + clinProtocols.size());
                    // get the yeast data
                    log.info("ViewModelAction.populateThearapeuticApproaches() calls AgentManager to get yeast data with useNscNumber=true.");
                    List yeastStages = myAgentManager.getYeastResults(a, true);
                    log.info("yeastStages.size(): " + yeastStages.size());
                    if (yeastStages.size() > 0)
                    {
                        yeastResults.put(a.getId(), yeastStages);
                    }
                    // now get invivo/Transplantation data
                    log.info("ViewModelAction.populateThearapeuticApproaches() calls QueryManager....getInvivoResults() with useNscNumber=true.");
                    List transplantationResults = QueryManagerSingleton.instance().getInvivoResults(a, true);
                    log.info("transplantationResults.size(): " + transplantationResults.size());
                    invivoResults.put(a.getId(), transplantationResults);
                }
            }
        }

        request.getSession().setAttribute(Constants.THERAPEUTIC_APPROACHES_COLL, therapeuticApprochesColl);
        request.getSession().setAttribute(Constants.CLINICAL_PROTOCOLS, clinProtocols);
        request.getSession().setAttribute(Constants.YEAST_DATA, yeastResults);
        request.getSession().setAttribute(Constants.INVIVO_DATA, invivoResults);
        request.getSession().setAttribute(Constants.RELATED_MODELS_BY_NSC, nscAnimalModelMap);

        setComments(request, Constants.Pages.THERAPEUTIC_APPROACHES);
        
        //caELMIR server went down and we experienced performance issues trying to connect
        //populateCaelmirTherapyDetails(mapping, form, request, response);

        return mapping.findForward("viewTherapeuticApproaches");
    }

	/**
	 * Populate the session and/or request with the objects necessary to display
	 * the page.
	 * 
	 * @param mapping
	 *            the struts action mapping
	 * @param form
	 *            the web form
	 * @param request
	 *            HTTPRequest
	 * @param response
	 *            HTTPResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward populateCaelmirTherapyDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("<ViewModelAction>  populateCaelmirTherapyDetails Enter");
		
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateCaelmirTherapyDetails")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        } 		

		setCancerModel(request);
		JSONArray jsonArray = new JSONArray();
		JSONObject jobj = new JSONObject();
		Vector h = new Vector();
		ArrayList caelmirStudyData = new ArrayList();

		String modelID = request.getParameter(Constants.Parameters.MODELID);
        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);  

		try {
			log.debug("<ViewModelAction>  populateCaelmirTherapyDetails Enter try");
			// Link to the inteface provided by caElmir
			URL url = new URL("http://chichen-itza.compmed.ucdavis.edu:8080/"
					+ CaElmirInterfaceManager.getStudyInfoUrl());
			// set your proxy server and port
			//System.setProperty("proxyHost", "ptproxy.persistent.co.in");
			//System.setProperty("proxyPort", "8080");

			URLConnection urlConnection = url.openConnection();
			//log.debug("populateCaelmirTherapyDetails open connection");
			// needs to be set to True for writing to the output stream.This
			// allows to pass data to the url.
			urlConnection.setDoOutput(true);

			JSONObject jsonObj = new JSONObject();
			// setting the model id.
			jsonObj.put(CaElmirInterfaceManager.getModelIdParameter(), modelID);
			PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
			out.write(jsonObj.toString());
			out.flush();
			out.close();
			//log.debug("populateCaelmirTherapyDetails created JSONObject");

			// start reading the responce
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream()));
			if (bufferedReader != null) {
				String resultStr = (String) bufferedReader.readLine();
				jsonArray = new JSONArray(resultStr);
				String status = null;
				status = ((JSONObject) jsonArray.get(0)).get(
						CaElmirInterfaceManager.getStatusMessageKey())
						.toString();
				//log.debug("populateCaelmirTherapyDetails status: " + status);
				// Imporatant:first check for the status
				if (!CaElmirInterfaceManager.getSuccessKey().equals(status)) {
					// prints the status
					log.info(status);
				}

                CaelmirStudyData studyData = new CaelmirStudyData();
                
				// start reading study data from index 1
				for (int i = 1; i < jsonArray.length(); i++) {
					jobj = (JSONObject) jsonArray.get(i);
                    
                    studyData = new CaelmirStudyData();
					studyData.setDescription(jobj.getString(CaElmirInterfaceManager.getStudyDesrciptionKey()));
					studyData.setEmail(jobj.getString(CaElmirInterfaceManager.getEmailKey()));
					studyData.setHypothesis(jobj.getString(CaElmirInterfaceManager.getStudyHypothesisKey()));
					studyData.setInstitution(jobj.getString(CaElmirInterfaceManager.getInstitutionKey()));
					studyData.setInvestigatorName(jobj.getString(CaElmirInterfaceManager.getPrimaryInvestigatorKey()));
					studyData.setStudyName(jobj.getString(CaElmirInterfaceManager.getStudyName()));
					studyData.setUrl(jobj.getString(CaElmirInterfaceManager.getStudyUrlKey()));

                    caelmirStudyData.add(studyData);                
				}    
			}
		} catch (MalformedURLException me) {
			log.debug("MalformedURLException: " + me);
		} catch (IOException ioe) {
			log.debug("IOException: " + ioe);
		}
		
		// Set collection so therapy link will display if caELMIR data is available
        // Needed for models with caELMIR data but no caMOD data 
		theAnimalModel.setCaelmirStudyDataCollection(caelmirStudyData);
		
		request.getSession().setAttribute(Constants.CAELMIR_STUDY_DATA,
				caelmirStudyData);
        
		return mapping.findForward("viewTherapeuticApproaches");
	}
    
    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateCellLines(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
    {
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateCellLines")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }     	
    	
        setCancerModel(request);
        setComments(request, Constants.Pages.CELL_LINES);

        return mapping.findForward("viewCellLines");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateTransientInterference(ActionMapping mapping,
                                                       ActionForm form,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response) throws Exception
    {
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateTransientInterference")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }     	
    	
        setCancerModel(request);
        setComments(request, Constants.Pages.TRANSIENT_INTERFERENCE);

        return mapping.findForward("viewTransientInterference");
    }


    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateImages(ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception
    {
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateImages")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }      	
    	
        setCancerModel(request);
        setComments(request, Constants.Pages.IMAGES);

        return mapping.findForward("viewImages");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateMicroarrays(ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws Exception
    {
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateMicroarrays")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        } 
        
        List modelsByMicroArrayColl = new ArrayList();
        final Map<String, List> microArrayAnimalModelMap = new HashMap<String, List>();
        
        setCancerModel(request);
        
        AnimalModel am = (AnimalModel)request.getSession().getAttribute(Constants.ANIMALMODEL);
        Set<MicroArrayData> maColl = am.getMicroArrayDataCollection();
        String modelID = request.getParameter(Constants.Parameters.MODELID);
        
        for( MicroArrayData ma: maColl) {
            modelsByMicroArrayColl = QueryManagerSingleton.instance().getRelatedModelsForThisMicroArray(ma.getUrl(), ma.getExperimentName(), modelID);
        	log.info("ViewModelAction  modelsByMicroArrayColl: " + modelsByMicroArrayColl.size()); 
        	microArrayAnimalModelMap.put(ma.getUrl(), modelsByMicroArrayColl); 
        }
        

        //Get external properties file
		Properties camodProperties = new Properties();
		String camodPropertiesFileName = null;

		camodPropertiesFileName = System.getProperty("gov.nih.nci.camod.camodProperties");
		
		try {
		
		FileInputStream in = new FileInputStream(camodPropertiesFileName);
		camodProperties.load(in);
	
		} 
		catch (FileNotFoundException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();			
		} catch (IOException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();			
		}

        request.setAttribute("uri_start", camodProperties.getProperty("caarray.uri_start"));
        request.setAttribute("uri_end", camodProperties.getProperty("caarray.uri_end"));
        request.getSession().setAttribute(Constants.RELATED_MODELS_BY_MICROARRAY, microArrayAnimalModelMap);

        setComments(request, Constants.Pages.MICROARRAY);

        return mapping.findForward("viewMicroarrays");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateTransplantation(ActionMapping mapping,
                                                     ActionForm form,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) throws Exception
    {
        log.debug("<populateTransplantation> Enter:"); 
        
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateTransplantation")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }         
        
        setCancerModel(request);
        setComments(request, Constants.Pages.TRANSPLANTATION);
        log.debug("<populateTransplantation> Exit:"); 
        return mapping.findForward("viewTransplantation");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateTransplantationDetails(ActionMapping mapping,
                                                  ActionForm form,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response) throws Exception
    {
        log.debug("<populateTransplantationDetails> Enter:"); 
        
        // Get and clean method to prevent Cross-Site Scripting 
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        if (!methodName.equals("populateTransplantationDetails")){
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);
        }        
        
        String modelID = request.getParameter("tModelID");
        request.getSession().setAttribute(Constants.MODELID, modelID);
        String nsc = request.getParameter("nsc");
        if (nsc != null && nsc.length() == 0)
            return mapping.findForward("viewModelCharacteristics");
        log.debug("<populateTransplantationDetails> modelID:" + modelID);
        log.debug("<populateTransplantationDetails> nsc:" + nsc);
        TransplantationManager mgr = (TransplantationManager) getBean("transplantationManager");

        Transplantation t = mgr.get(modelID);

        request.getSession().setAttribute(Constants.TRANSPLANTATIONMODEL, t);
        request.getSession().setAttribute(Constants.NSC_NUMBER, nsc);
        request.getSession().setAttribute(Constants.TRANSPLANTATIONRESULTLIST, t.getInvivoResultCollectionByNSC(nsc));
        return mapping.findForward("viewInvivoDetails");
    }
}
