<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<%      
	List results = (List) request.getSession().getAttribute( Constants.USERMODELLIST );
	int size = results.size();
	System.out.println( "SIZE: " + size );
%>

<!-- submitModels.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>

	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="60%" height="100%">
	<tr><td>
	
		<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" width="100%">
			<tr>
				<td class="formTitle" height="20" colspan="4">Submit and Edit Models &nbsp;
	                <camod:cshelp topic="submit_edit_models_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>		
			</tr>
			<tr><td class="resultsBoxGreyEnd">			
					<TABLE summary="This table is used to format page content" width="100%">
						<tr>
						<td>
								  Welcome back <b><%= session.getAttribute("camod.loggedon.username") %></b>.<br>
							      To edit one of your existing models click on the name of the model.<br>
							      To add a new model select "<html:link action="AnimalModelPopulateAction.do?method=dropdown"><font color=red>Add New Model</font></html:link>".<br>
								<br>
							      If you are unfamiliar with the submission process please refer to <html:link styleId="<%=Constants.USER_GUIDE_WIKI_LINK%>" styleClass="subMenuPrimary" href='javascript:void(0)' onclick="myRef = window.open(this.id,'mywin',
															'left=20,top=20,width=700,height=500,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">HELP</html:link>.<br>
								<br>
							      There are <%= size %> records returned.
							     <br>						    
									<logic:messagesPresent>
									  <br>
									  <b><font color=red>
									  <html:messages id="errors">
										<%=errors %>
									  </html:messages>
									  </font></b>
									  
									</logic:messagesPresent>						     			
						    </td>				      
						    <td><img alt="Submit" src="images/submit.gif"> </td>
					    </tr>
				    </TABLE>
			</td></tr>
		</TABLE><br>	

		<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="5">Your Models</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="5%">Duplicate<br>Record</td>
				<td class="greySubTitleLeft" width="40%">Model Descriptor</td>
				<td class="greySubTitleLeft" width="20%">Entered on</td>
				<td class="greySubTitleLeft" width="20%">Modified on</td>
				<td class="greySubTitle" width="5%">Remove</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="5%">&nbsp;</td>
				<td class="resultsBoxWhite" width="40%"><html:link action="AnimalModelPopulateAction?method=dropdown"><font color=red><b>Add New Model</font></b></html:link></td>
				<td class="resultsBoxWhite" width="20%">
					<jsp:useBean id="now" class="java.util.Date" />
					<fmt:formatDate value="${now}" type="date" pattern="yyyy-MM-dd" />
				</td>
				<td class="resultsBoxWhite" width="20%">&nbsp;</td>
				<td class="resultsBoxWhiteEnd" width="5%">&nbsp;</td>
			</tr>
							
			<% if ( size > 0 ) { %>
			<logic:iterate id="aModel" name="usermodellist" type="AnimalModel">
			<c:choose>
				<c:when test="${aModel.state == 'Inactive'}">
				</c:when>
			<c:otherwise>		
		         <TR>
		             <td class="resultsBoxGrey" width="5%">
		             	<center>
		             	    <c:set var="dupLink" value="return confirm('Are you sure you want to duplicate this record (${aModel.modelDescriptor})?');"/>   
		                    <c:set var="uri" value="/camod/DuplicateAnimalModelAction.do?method=duplicate&aModelID=${aModel.id}"/>
		                    <a href='<c:out value="${uri}"/>' onclick='<c:out value="${dupLink}"/>' ><IMG alt="Duplicate" src="images/dupRecord.gif" border=0></a>  
		       	        </center>
		             </td>
		             <td class="resultsBoxGrey" width="40%">
						<html:link action="SubmitAction.do?method=setModelConstants" paramId="aModelID" paramName="aModel" paramProperty="id"><c:out value="${aModel.modelDescriptor}" escapeXml="false"/> (<bean:write name="aModel" property="state" filter="true"/>)</html:link>	             
		             </td>
		             <td class="resultsBoxGrey" width="20%">
		                <bean:write name="aModel" property="availability.enteredDate" filter="true"/>
		             </td>
		             <td class="resultsBoxGrey" width="20%">
		                <bean:write name="aModel" property="availability.modifiedDate" filter="true"/>&nbsp;
		             </td>
		             <td class="resultsBoxGreyEnd" width="5%">
			             <center>
		                    <c:set var="deleteLink" value="return confirm('Are you sure you want to delete this record (${aModel.modelDescriptor})?');"/>   
		                    <c:set var="uri" value="/camod/DeleteAnimalModelAction.do?method=delete&aModelID=${aModel.id}"/>
		                    <a href='<c:out value="${uri}"/>' onclick='<c:out value="${deleteLink}"/>' ><IMG alt="Remove" src="images/remove.gif" border=0/></a>               	
		                </center>
		             </td>                     
		         </TR>
				</c:otherwise>
			</c:choose>        
			</logic:iterate>
			<% } %>				
		</TABLE>
	
	</td></tr></TABLE>				
</td></tr></TABLE>	

<%@ include file="/jsp/footer.jsp" %>





