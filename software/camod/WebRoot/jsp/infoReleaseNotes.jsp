<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
    <tr>
        <td>
			<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
				<tr>
				    <td valign="top">
						<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
							<tr>
							    <td>
									<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
										<tr>
											<td class="formTitle" height="20">Release Notes</td>				
										</tr>			
										<tr>
											<td class="resultsBoxWhiteEnd">
<pre> 
 Release Notes 
     
                             caMOD
                          Version 2.6.1
                          May 2010

       National Cancer Institute's Center for Biomedical Informatics and Information Technology 
 
 
 
================================================================ 
                            Contents 
================================================================ 
     
    1.0 caMOD Introduction and History 
    2.0 Release History 
    3.0 New Features and Updates 
    4.0 Bugs Fixed Since Last Release 
    5.0 Known Issues/Defects 
    6.0 Bug Reports and Support 
    7.0 NCICB Web Pages 
 
 
================================================================ 
    1.0 caMOD Version 2.6.1 Introduction and History 
================================================================ 
  
 
    caMOD 2.6.1, the Cancer Models Database, is a open source data management 
    system developed for the management and sharing of data of animal models. 
    caMOD features controlled vocabularies from a shared, publicly accessible metadata repository (caDSR) and 
    enterprise vocabulary services (EVS) from the National 
    Cancer Institute..   
 
    -- http://cancermodels.nci.nih.gov 
 
    caMOD is a product of the NCI's Center for Biomedical Informatics and Information Technology and its partners. 
    Visit the NCICB web site and the emice website for more information:  
	 
   -- http://ncicb.nci.nih.gov/ 
   -- http://emice.nci.nih.gov/  
 
 
================================================================ 
    2.0 Release History 
================================================================ 
     caMOD Version 2.6.1
    --	May 2010
    
     caMOD Version 2.6 
    --	March 2010
    
    caMOD Version 2.5.1  
    --	June 2009
    
    caMOD Version 2.5  
    --	Dec 2008  
     
    caMOD Version 2.4  
    --	Feb 2008  
 
    caMOD Version 2.3  
    --	Sept 2007  
         
    caMOD Version 2.2 
    --	November 30th 2006  
     
    caMOD Version 2.1.1 
    --	October 16th 2006  
     
    caMOD Version 2.1 
    --	May 30th 2006     
     
    caMOD Version 2.0 
    --	December 21 2005     
 
    caMOD Version 1.0 
    --	Fall 2001; continously updated until April 2005 
 
================================================================ 
    3.0 New Features and Updates 
================================================================ 
caIMAGE connection
The goal of this release is to update the connection to caIAMGE.  The caIMAGE application is being upgraded and the 
servers and image viewing technology is being changed.  Therefore, both the submission of images and search of images in 
caMOD needs to be modified. 

Other feature requests
- Retrieve developmental stage from DB when EVS does not return value (GForge entry #27836)
- Change Zebrafish concept codes to accommodate new NCI Thesaurus release with improved Zebrafish vocabularies (GForge entry #27533)
- BDA - automate changes to external properties for caIMAGE upgrade (GForge entry #25294)
- Links to sid, gif, jpeg, jpg, tif images needs to be modified for caIMAGE upgrade (GForge entry #23750)
- Wrap First Author field for MTB pages (GForge entry #23452)
- Display PMID number on search results detail page (GForge entry #19879)
- Assign various records to curator - data cleanup (GForge entry #18685)
- Separation between keyword search and simple/advanced search is not obvious to user (GForge entry #14560)
- Make sorting meaningful for humans and not for computers (GForge entry #14379)

The caMOD 2.5 Common Data Elements (CDEs) are available via caDSR    
   
================================================================ 
    4.0 Defects Fixed Since Last Release 
================================================================ 
 
- Can't enter "Other" for site of administration on Transplant page (GForge entry #27700)
- delete image record for image_id=1058 (GForge entry #27167)
- Entered date changes every single time the model characteristics page is changed (GForge entry #20593)
- Change label from MMHCC Repository to NCI Mouse Repository (GForge entry #18438) 
 
================================================================ 
    5.0 Known Issues/Defects 
================================================================ 
     
    1. Organ-specific pre-selection of diagnoses in the vocabulary trees is 
       not available
    
    2. Genetic Description view screen does not include data for the following 
        sections: Libraries and Tissues (from EST data) or Protein Similarities 
        (from UniGene). caBio discontinued support for the Protein Similarities and 
        the performance for the Libraries and Tissues query did not meet minimum standards.  
        After meeting with the caBio team, it was concluded that the development team will need to eliminate lazy fetching for 
        library and tissue attributes if we want to improve performance.
     
    Please report any defects found to application support. 
 
================================================================ 
    6.0 Bug Reports and Support 
================================================================ 
     
    Send email to ncicb@pop.nci.nih.gov to request support. 
 
    In addition, mailing lists are used by the caMOD developer 
    and user community to exchange ideas and make announcements. 
 
    You can subscribe at this address: http://list.nih.gov 
 
    caMOD users 
        CAMOD_USERS-L 
         
    caMOD developers 
       CAMOD_DEVELOPERS-L  
 
================================================================ 
    7.0 NCICB Web Pages 
================================================================ 
     
    The NCI Center for Bioinformatics 
    --  http://ncicb.nci.nih.gov/ 
     
    NCICB Application Support 
    --  http://ncicb.nci.nih.gov/NCICB/support 
 
    caMOD 
    --  http://cancermodels.nci.nih.gov/ 
     
     
</pre>

											</td>
										</tr>
									</TABLE>
							    </td>
							</tr>
						</TABLE>
				    </td>
				</tr>
			</TABLE>
        </td>
    </tr>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>