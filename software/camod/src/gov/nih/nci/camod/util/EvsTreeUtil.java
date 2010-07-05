/**
 *  @author georgeda 
 *  
 *  $Id: EvsTreeUtil.java,v 1.22 2009-06-05 16:52:15 pandyas Exp $  
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.21  2009/06/04 18:48:50  pandyas
 *  Testing disease issue
 *
 *  Revision 1.20  2009/06/04 16:57:56  pandyas
 *  getting ready for QA build
 *
 *  Revision 1.19  2009/06/04 16:27:34  pandyas
 *  The Property object for the Zebrafish vocabulary returns only  Preferred_Name, Synonym, NCI_Preferred_Term.  We used to display the "display_name" for both the NCI_Thesaurus and Zebrafish vocabs
 *
 *  Revision 1.18  2009/06/04 16:12:38  pandyas
 *  testing preferred description in new methods
 *
 *  Revision 1.17  2009/06/04 15:02:00  pandyas
 *  testing preferred description in new methods
 *
 *  Revision 1.16  2009/06/01 16:53:42  pandyas
 *  getting ready for QA build
 *
 *  Revision 1.15  2009/05/28 19:10:13  pandyas
 *  getting ready for QA build
 *
 *  Revision 1.14  2009/05/20 17:11:50  pandyas
 *  modified for gforge #17325 Upgrade caMOD to use caBIO 4.x and EVS 4.x to get data
 *
 *  Revision 1.13  2008/08/14 06:27:33  schroedn
 *  Check for null first
 *
 *  Revision 1.12  2008/01/15 19:31:28  pandyas
 *  Modified debug statements to build to dev tier
 *
 *  Revision 1.11  2008/01/14 21:04:56  pandyas
 *  Enabled logging for dev tier instability issue testing
 *
 *  Revision 1.10  2008/01/14 17:17:48  pandyas
 *  Added to dev instance to look at get Preferred Description error iwth caCORE
 *
 *  Revision 1.9  2007/08/27 15:38:08  pandyas
 *  hide debug code printout
 *
 *  Revision 1.8  2007/08/23 16:11:50  pandyas
 *  Removed extra code
 *
 *  Revision 1.7  2007/08/14 17:05:02  pandyas
 *  Bug #8414:  getEVSPreferredDiscription needs to be implemented for Zebrafish vocabulary source
 *
 *  Revision 1.6  2007/08/14 12:03:59  pandyas
 *  Implementing EVSPreferredName for Zebrafish models
 *
 *  Revision 1.5  2006/08/17 17:59:34  pandyas
 *  Defect# 410: Externalize properties files - Code changes to get properties
 *
 *  Revision 1.4  2006/04/21 13:42:12  georgeda
 *  Cleanup
 *
 *  Revision 1.3  2005/11/03 21:47:56  georgeda
 *  Changed EVS api
 *
 *  Revision 1.2  2005/09/22 13:04:31  georgeda
 *  Added app server call
 *
 *  Revision 1.1  2005/09/21 20:34:59  georgeda
 *  Create util for fetching/caching EVS data
 *
 *  
 */
package gov.nih.nci.camod.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.LexGrid.LexBIG.DataModel.Collections.CodingSchemeRenderingList;
import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.Utility.ConvenienceMethods;
import org.LexGrid.concepts.Concept;
import org.LexGrid.LexBIG.DataModel.Collections.ConceptReferenceList;
import org.LexGrid.LexBIG.Exceptions.LBException;


/**
 * Static helper class for caching EVS values.
 *
 */
public class EvsTreeUtil
{
    static private final Log log = LogFactory.getLog(EvsTreeUtil.class);
    static private Map<String, String> ourDescriptions = new HashMap<String, String>();
    static private LexBIGService appService = null;
	static String serviceUrl = "http://lexevsapi51.nci.nih.gov/lexevsapi51";

   
    private EvsTreeUtil()  {
    }

    
    public String getSupportedCodingSchemes(String codingSchemeName) throws Exception {
    	
        CodingSchemeRenderingList csrl = appService.getSupportedCodingSchemes();
        for(int i = 0; i < csrl.getCodingSchemeRenderingCount(); i++)
        {   
            //get a version of the NCI Thesaurus on the server
            if(csrl.getCodingSchemeRendering(i).getCodingSchemeSummary().getFormalName().equals(codingSchemeName) ){
                if(csrl.getCodingSchemeRendering(i).getRenderingDetail().getVersionTags().getTagCount() > 0)
                {return csrl.getCodingSchemeRendering(i).getCodingSchemeSummary().getRepresentsVersion();}
            }
        }
        throw new IllegalStateException("No versions of coding scheme not found: "+codingSchemeName);
    }	

	public static String getConceptByCode(String codingSchemeName, String vers, String ltag, String code)
	{
		log.info("Entered getConceptByCode.");
		CodedNodeSet cns = null;
		String myConcept = null;
		
        try {
        	log.info("getConceptByCode inside try.");
        	
        	if (appService != null) {
	        	cns =  appService.getCodingSchemeConcepts(codingSchemeName, null);			
	        	log.info("getConceptByCode got cns.");
	        	
			    // LexEVS 5.1
			    ConceptReferenceList crefs = ConvenienceMethods.createConceptReferenceList(new String[] { code}, codingSchemeName); 
	    		cns.restrictToCodes(crefs); 
	    		ResolvedConceptReferenceList matches = cns.resolveToList(null, null, null, 1);	    
	    		log.info("getConceptByCode matches: " );
    		
		       if (matches.getResolvedConceptReferenceCount() > 0) {
	   			ResolvedConceptReference ref = (ResolvedConceptReference)matches.enumerateResolvedConceptReference().nextElement();
	    		Concept entry = ref.getReferencedEntry();
	   	    		for (int i = 0; i < entry.getPresentationCount(); i++) {
	   	    			if (entry.getPresentation(i).getPropertyName().equals(Constants.Evs.DISPLAY_NAME_TAG) || entry.getPresentation(i).getPropertyName().equals(Constants.Evs.PREFERRED_NAME_TAG))
	  	    				myConcept = entry.getPresentation(i).getValue().getContent();
	    	    	} 
	    	    			
	    	    }
        	} else {
        		log.info("appservice is null. " );
        	}
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }
		 log.info("getConceptByCode myConcept: " + myConcept);
		 return myConcept;
	}

	public static String getConceptDetails(String version, String code)
	{
		log.info("EvsTreeUtil.getConceptDetails Entered: ");
        String scheme = "";
        String theDescription = ""; 

		try {
    		log.info("get appService =null above.");
    		appService = (LexBIGService)ApplicationServiceProvider.getApplicationServiceFromUrl(serviceUrl, "EvsServiceInfo");        			
			
		} catch (FileNotFoundException e) {
			log.error("FileNotFound exception in getApplicationService.",e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("IO exception getApplicationService. ", e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error("Caught general exception getApplicationService. ", e);
			e.printStackTrace();
		}        
        
		if( code != null ){
            if(code.contains("ZFA")){
                log.info("Zebrafish modelSpecies");
        		scheme = Constants.Evs.ZEBRAFISH_SCHEMA;
        	//Define parameters for all NCI_Thesaurus schema
        	} else {
                log.info("NCI modelSpecies");
                scheme = Constants.Evs.NCI_SCHEMA;
        	}
		}

		// returns Concept
        theDescription = getConceptByCode(scheme, null, null, code);
        log.info("EvsTreeUtil.getConceptDetails Entered theDescription: " +theDescription);
        return theDescription;
	}
	


    public static void main(String[] args)
 	{
  		EvsTreeUtil test = new EvsTreeUtil();
		String scheme = "NCI Thesaurus";
		String version = null;
		String code = "C17763";
  		//test.getConceptDetails(scheme, version, code);
		test.getConceptDetails(version, code);

		scheme = "Zebrafish";
		version = null;
		code = "ZFA_0000315";
		//test.getConceptDetails(scheme, version, code);
		test.getConceptDetails(version, code);
    }
}
