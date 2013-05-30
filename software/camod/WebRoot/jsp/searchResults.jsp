<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%
/**
 * 
 * $Id: searchResults.jsp,v 1.34 2009-06-17 16:06:06 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.33  2009/04/01 19:38:02  pandyas
 * minor modified for #17833  	Make sure all references to Transplantation are properly named
 *
 * Revision 1.32  2009/03/30 16:10:49  pandyas
 * modified for #17833  	Make sure all references to Tranplantation are properly named
 *
 * Revision 1.31  2008/10/21 06:11:42  schroedn
 * Bug #7474 Changed field names
 *
 * Revision 1.30  2008/08/14 17:10:53  pandyas
 * remove debug line
 *
 * Revision 1.29  2007/09/11 15:55:30  pandyas
 * Comment out debug
 *
 * Revision 1.28  2007/09/06 19:09:33  pandyas
 * Slight modification to force the newest jsp to build
 *
 * Revision 1.27  2007/09/06 19:05:47  pandyas
 * Slight modification to force the newest jsp to build
 *
 * Revision 1.26  2007/07/31 12:00:10  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.25  2006/11/17 17:33:22  pandyas
 * #480 - move MTB icon in front of the model descriptor in search results list
 *
 * Revision 1.24  2006/11/13 19:59:44  pandyas
 * #463	images on image search page do not open
 * Modified link to show complete img src location:
 * <IMG SRC="/camod/images/...">
 *
 * Revision 1.23  2006/11/08 20:13:22  pandyas
 * Removed height and width for mtb_logo
 *
 * Revision 1.22  2006/11/08 19:11:17  pandyas
 * added MTB logo onto view screens for Jackson Lab models
 *
 * Revision 1.21  2006/10/18 18:44:07  pandyas
 * took out image and microarray icon code
 *
 * Revision 1.20  2006/10/17 16:08:28  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.19  2006/08/13 17:43:43  pandyas
 * Updated online help - redefined camod tag by substituting mapId for topic (ePublisher changes)
 *
 * Revision 1.18  2006/05/10 18:03:00  schroedn
 * Fixed crash when loading page with no search results.
 *
 * Revision 1.17  2006/05/10 15:37:23  schroedn
 * Fixed Dup_Name bug
 *
 * Revision 1.16  2006/05/10 14:22:10  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.15  2006/04/28 19:39:21  schroedn
 * Defect # 261, 238
 * Many changes, displays any search result column user has setup, options to save/update Query
 *
 *
 */
%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Histopathology" %>
<%@ page import="gov.nih.nci.camod.domain.SavedQuery" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="gov.nih.nci.camod.util.CriteriaTableUtil" %>
<%@ page import="gov.nih.nci.camod.webapp.form.SearchForm" %>
<%@ page import="gov.nih.nci.camod.webapp.form.SaveQueryForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Float" %>
<%@ page import="java.util.ArrayList" %>

<%      
	List results = ( List ) request.getSession().getAttribute( Constants.SEARCH_RESULTS );	
	if ( results == null ) 
	{
	    results = new ArrayList();
	    request.getSession().setAttribute( Constants.SEARCH_RESULTS, results );
	}
	
	int size = results.size();
	
	String pageSize = ( String ) request.getSession().getAttribute( Constants.ITEMSPERPAGE );	
	if ( pageSize == null ) 
	{
		pageSize = "15";
	}
		
	String[] resultColumns = ( String[] ) request.getSession().getAttribute( Constants.SEARCHRESULTCOLUMNS );
   	if ( resultColumns == null ) 
   	{
   		resultColumns = new String[] { "Model Identifier", "Model Descriptor", "Tumor Sites", "Species" };   //default                
  	}  
  	
	//String noSaveOption = request.getParameter( "noSaveOption" );  	  	 
%>

<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<!-- searchResults.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
<%@ include file="/jsp/searchMenu.jsp" %>
<tr><td>
	<logic:present name="<%=Constants.NOSAVEOPTION%>">
		<bean:define id="noSaveOption" name="<%=Constants.NOSAVEOPTION%>" />	
	</logic:present>
	<logic:present name="<%=Constants.CRITERIATABLE%>">
		<bean:define id="criteriaTable" name="<%=Constants.CRITERIATABLE%>" />	
	</logic:present>	
	<logic:present name="<%=Constants.DUP_NAME%>">
		<bean:define id="dupName" name="<%=Constants.DUP_NAME%>" />
	</logic:present>
	<logic:present name="<%=Constants.DUP_NAME_VALUE%>">
		<bean:define id="dupNameValue" name="<%=Constants.DUP_NAME_VALUE%>" />
	</logic:present>			
	<logic:present name="<%=Constants.QUERY_NAME%>">
		<bean:define id="aQueryName" name="<%=Constants.QUERY_NAME%>" />
	</logic:present>	
	<logic:present name="<%=Constants.RERUN_QUERY%>">
		<bean:define id="reRunQuery" name="<%=Constants.RERUN_QUERY%>" />	
	</logic:present>
	<logic:present name="<%=Constants.ASAVEDQUERYID%>">
		<bean:define id="aSavedQueryId" name="<%=Constants.ASAVEDQUERYID%>" />	
	</logic:present>	
	<logic:present name="<%=Constants.CURRENTUSER%>">
		<bean:define id="currentUser" name="<%=Constants.CURRENTUSER%>" />	
	</logic:present>
		
	<%							
		request.getSession().setAttribute( Constants.DUP_NAME, "false" );
			
		// Get elapsed time in seconds	
		float elapsedTimeSec = 0;
		if ( (Long) request.getSession().getAttribute( Constants.ELAPSED_TIME ) != null )
		{		
			elapsedTimeSec = ( (Long) request.getSession().getAttribute( Constants.ELAPSED_TIME )).floatValue()/1000;
		} 				
	%>



    <TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="60%" height="100%">
	<tr><td>
		<TABLE summary="This table is used to format page content" cellpadding="7" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr>
			<td>
				<TABLE cellpadding="5" cellspacing="0" border="0" width="100%">
					<TR>
						<TH scope="col" class="formTitle" height="20">Search Criteria ( <font color="red"><%= elapsedTimeSec %></font> seconds )</TH>
					</TR>
					<TR>
						<TD class="formFieldAll"><c:out value="${criteriaTable}" escapeXml="false"/></TD>
					</TR>
				</TABLE>												
				
			    <c:if test="${ dupName == 'true' }">
					<TABLE summary="This table is used to format page content" border="0" class="contentPage" width="100%">
						<TR>
							<TD align="left" colspan="2" width="100%">
								<font color="red">*The name "<c:out value="${dupNameValue}" escapeXml="false"/>" is already being used, please choose a different name.</font><br>
						    </TD>
						</TR>
					</TABLE>	
			    </c:if>
				
				<c:choose>
								    
				    <c:when test="${ noSaveOption == 'true' }">
						<TABLE summary="This table is used to format page content" border="0" class="contentPage" width="100%">
							<TR>
								<TD width="40%">&nbsp;</TD>
								<TD align="right" width="60%">
									<font color="red">Query saved as "<c:out value="${aQueryName}" escapeXml="false"/>".</font><br>
					            </TD>
					        </TR>
				        </TABLE>		
				        <BR>
				    </c:when>
	
				    <c:when test="${ !empty reRunQuery }">
						<TABLE summary="This table is used to format page content" border="0" class="contentPage" width="100%">
							<TR>
								<TD width="40%">&nbsp;</TD>
								<TD align="right" width="60%">
									<font color="red">Query "<c:out value="${aQueryName}" escapeXml="false"/>" ran successfully.</font><br>
					            </TD>
					        </TR>
				        </TABLE>		
				        <BR>
				    </c:when>
	
					<c:when test="${ !empty aSavedQueryId }">
						<html:form action="SaveQueryAction.do?method=save" focus="queryName">
							<TABLE summary="This table is used to format page content" border="0" class="contentPage" width="100%">
								<TR>
									<TD width="20%">&nbsp;</TD>
									<TD align="left" width="80%">				
							 				<html:radio styleId="save1" property="saveAsNew" value="no" /><label for="">Update saved query</label> "<c:out value="${aQueryName}" escapeXml="false"/>" with the new criteria. 
							 				<br>
											<html:radio styleId="save2" property="saveAsNew" value="yes" /><label for="">Save this criteria</label> as a new saved query called <html:text styleClass="formFieldUnSized" value="My Saved Query" property="queryName" size="20"/>.             						           
											<br>
						                <input type="submit" value="Save Query" src="images/savequery.gif" />
						            </TD>
						        </TR>
					        </TABLE>
						</html:form>	
				 	</c:when>
	
				    <c:when test="${ !empty currentUser }">
						<html:form action="SaveQueryAction.do?method=save" focus="queryName">
							<TABLE summary="This table is used to format page content" border="0" class="contentPage" width="100%">
								<TR>
									<TD width="40%">&nbsp;</TD>
									<TD align="right" width="60%">
							            <html:text styleId="queryName" styleClass="formFieldUnSized" value="My Saved Query" property="queryName" size="20"/>
			            				<INPUT name="saveAsNew" value="yes" type="hidden"/>
						                <input type="submit" value="Save Query" src="images/savequery.gif" /><label for="queryName">&#160;</label>
						            </TD>
						        </TR>
					        </TABLE>
						</html:form>
				    </c:when>
				    
				    <c:otherwise>
					   <br>
				    </c:otherwise>
				    		    			    				
			</c:choose>
			
			</td>
		</tr>
		
		<tr><td>
			<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" width="100%">	
				<tr>
					<td class="formTitle" height="20" colspan="4">Search Results&nbsp;
					<camod:cshelp topic="search_results_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>
					</td>
				</tr>
			</TABLE>
			
            <display:table id="row" name="${sessionScope.searchResults}"              		
	          		 pagesize="<%= pageSize %>"
	          		 cellpadding="5" 
	          		 cellspacing="0" 
	          		 border="1"
 	                 width="100%"
 	                 > 	 
 	                 
 	                 <display:column title="No.">
 	                     <c:out value="${row_rowNum}"/>
				     </display:column>  
				     
					<%
					    for (int i = 0; i < resultColumns.length; i++) {
					    	
							if( resultColumns[i].equals("Model Identifier") ) { %>
							     <display:column title="Unique Model Identifier" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.modelId}"/></camod:highlight>
							     </display:column>	 							
							<% }					    	
					    	else if( resultColumns[i].equals("Unique Model Identifier") ) { %>
							     <display:column title="Unique Model Identifier" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.modelId}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Model Descriptor") ) { %>
					             <display:column href="/camod/ViewModelAction.do?unprotected_method=populateModelCharacteristics&" paramId="aModelID" paramProperty="id" title="Model Descriptor" sortable="true" >
									<c:if test="${row.externalSource == 'Jax MTB'}">
										<IMG alt="Jax MTB" src="/camod/images/mtb_logo.jpg">
									</c:if>						             
					                 <c:out escapeXml="false" value="${row.modelDescriptor}"/>
									<c:if test="${not empty row.imageTitle}">
										<IMG alt="Image Title" src="/camod/images/image_icon.jpg" width="20" height="20">
									</c:if>	
									<c:if test="${not empty row.microarray}">
										<IMG alt="MicroArray" src="/camod/images/microarray_icon.jpg" width="20" height="20">
									</c:if>																								                 	
							     </display:column>										
							<% }						
							else if( resultColumns[i].equals("Species") ) { %>
							     <display:column title="Species" sortable="true">
							         <camod:highlight><c:out value="${row.species}"/></camod:highlight>
							     </display:column>  								
							<% }       							
							else if( resultColumns[i].equals("Tumor Sites") ) { %>
							     <display:column title="Tumor Sites" sortable="true" >
							         <camod:highlight><c:out escapeXml="false" value="${row.tumorSites}"/></camod:highlight>
							     </display:column>	 							
							<% } 
							else if( resultColumns[i].equals("Submitter") ) { %>
							     <display:column title="Submitter" sortable="true" >
							         <camod:highlight><c:out escapeXml="false" value="${row.submitterName}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Submitted on") ) { %>
							     <display:column title="Submitted on" sortable="true" >
							         <camod:highlight><c:out escapeXml="false" value="${row.submittedDate}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Strain") ) { %>
							     <display:column title="Strain" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.strain}"/></camod:highlight>
							     </display:column>	 							
							<% }

							else if( resultColumns[i].equals("Principal Investigator") ) { %>
							     <display:column title="Principal Investigator" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.principalInvestigatorName}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Gender") ) { %>
							     <display:column title="Gender" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.gender}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Transgene") ) { %>
							     <display:column title="Transgene" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.transgene}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Promoter") ) { %>
							     <display:column title="Promoter" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.transcriptional1}"/></camod:highlight>
							     </display:column>	 														     
							<% }												
							else if( resultColumns[i].equals("Genomic Segment Type") ) { %>
							     <display:column title="Genomic Segment Type" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.segmentType}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Genomic Segment Designator") ) { %>
							     <display:column title="Genomic Segment Designator" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.designator}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Targeted Modification") ) { %>
							     <display:column title="Targeted Modification" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.targetedGeneLocus}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Type of Modification") ) { %>
							     <display:column title="Type of Modification" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.typeOfModification}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Name of inducing agent") ) { %>
							     <display:column title="Name of inducing agent" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.nameOfInducingAgent}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Spontaneous Mutation in Gene") ) { %>
							     <display:column title="Spontaneous Mutation in Gene" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.geneName}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Chemical") ) { %>
							     <display:column title="Chemical" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.chemical}"/></camod:highlight>
							     </display:column>	 							
							<% }																																																																													 							
							else if( resultColumns[i].equals("Environmental Factor") ) { %>
							     <display:column title="Environmental Factor" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.environmentalFactor}"/></camod:highlight>
							     </display:column>	 							
							<% }		
							else if( resultColumns[i].equals("Hormone") ) { %>
							     <display:column title="Hormone" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.hormone}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Nutritional Factor") ) { %>
							     <display:column title="Nutritional Factor" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.nutritionalFactor}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("Radiation") ) { %>
							     <display:column title="Radiation" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.radiation}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Virus") ) { %>
							     <display:column title="Virus" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.virus}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Year of Publication") ) { %>
							     <display:column title="Year of Publication" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.yearOfPublication}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("Journal") ) { %>
							     <display:column title="Journal" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.journal}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("PubMed Identifier") ) { %>
							     <display:column title="PubMed Identifier" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.PMIDNumber}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Site of Primary Tumor") ) { %>
							     <display:column title="Site of Primary Tumor" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.siteOfLesionTumor}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Diagnosis") ) { %>
							     <display:column title="Diagnosis" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.diagnosis}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("Age of Tumor Onset") ) { %>
							     <display:column title="Age of Tumor Onset" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.ageOfOnset}"/></camod:highlight>
							     </display:column>	 							
							<% }						
							else if( resultColumns[i].equals("Site and Diagnosis of Metastasis") ) { %>
							     <display:column title="Site and Diagnosis of Metastasis" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.siteAndDiagnosisOfMetastasis}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("Therapeutic Agent Name") ) { %>
							     <display:column title="Therapeutic Agent Name" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.drugCompoundName}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Name of Cell line") ) { %>
							     <display:column title="Name of Cell line" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.nameOfCellLine}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Cell Line Organ of Origin") ) { %>
							     <display:column title="Cell Line Organ of Origin" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.organTissue}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Image Title") ) { %>
							     <display:column title="Image Title" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.imageTitle}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Animal Distributor") ) { %>
							     <display:column title="Animal Distributor" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.distributor}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Name of Transplanted Cell Line") ) { %>
							     <display:column title="Name of Transplanted Cell Line" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.cellLine}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Transplantation Donor Species") ) { %>
							     <display:column title="Transplantation Donor Species" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.donorSpecies}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Source Type") ) { %>
							     <display:column title="Source Type" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.sourceType}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Carcinogenic Agent") ) { %>
							     <display:column title="Carcinogenic Agent" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.carcinogen}"/></camod:highlight>
							     </display:column>	 							
							<% }			
							else if( resultColumns[i].equals("Microarray Experiment Title") ) { %>
							     <display:column title="Microarray Experiment Title" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.microarray}"/></camod:highlight>
							     </display:column>	 							
							<% }																										
							else {}  							
						}
					%>
    	    </display:table>		          
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>