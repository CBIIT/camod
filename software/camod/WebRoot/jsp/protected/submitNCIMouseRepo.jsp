<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.webapp.form.AvailabilityForm" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aAvailabilityID = request.getParameter( "aAvailabilityID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
    
	//if aAvailabilityID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "NCIMouseRepoAction.do?method=save";
	
	if ( aAvailabilityID != null && aAvailabilityID.length() > 0 && isDeleted == null) {
		actionName = "NCIMouseRepoAction.do?method=edit";
	}
	else {
	    aAvailabilityID = "";
	}
%>

<!-- submitNCIMouseRepo.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	<html:form action="<%= actionName %>" focus="name">
	<tr>
	    <html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Available from  NCI Mouse Repository 
			<camod:cshelp topic="MMHCC_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>					
	</tr>
	
	<tr>
			<td class="formRequiredNotice" width="3">*</td>
			<td class="formRequiredLabel"><label for="strainName">Strain Name:</label>
			</td>
			<td class="formField">
			<html:hidden property="source" />		
				<html:text styleClass="formFieldUnSized" size="40" styleId="strainName" property="name"  />
			</td>
	</tr>
	
    <tr>
        <td class="formRequiredNotice" width="5">&nbsp;</td>
        <td class="formLabel"><label for="stockNumber">Strain Number:</label>
        </td>
		<td class="formField">
            <input type=button value="Find Info" onClick="myRef = window.open('http://mouse.ncifcrf.gov','mywin',
				'left=20,top=20,width=800,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
			<html:text styleClass="formFieldSized" size="30" styleId="stockNumber" property="stockNumber"  />			
		</td>
    </tr>
        
	<tr>
		<td align="right" colspan="3">
			<!-- action buttons begins -->
			<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:reset styleClass="actionButton">
					  	  <bean:message key="button.reset"/>
	  				  </html:reset>
	  				  
						<c:if test="${not empty aAvailabilityID}">
							<html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton"
								onclick="return confirm('Are you sure you want to delete?');">
								<bean:message key="button.delete" />
							</html:submit>
						</c:if>
	  				  
					  <!--  Done this way since html:hidden doesn't seem to work correctly -->
  				  	  <input type="hidden" name="aAvailabilityID" value="<%= aAvailabilityID %>">	  				  
			</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>
	</html:form>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>













