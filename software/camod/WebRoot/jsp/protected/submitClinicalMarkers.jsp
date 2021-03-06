<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.ClinicalMarkerForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aHistopathologyID = request.getParameter( "aHistopathologyID" );
	String aClinicalMarkerID = request.getParameter( "aClinicalMarkerID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);	
	
	//if aClinicalMarkerID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "ClinicalMarkerAction.do?method=save";
	
	if ( aClinicalMarkerID != null && aClinicalMarkerID.length() > 0 && isDeleted == null) {
		actionName = "ClinicalMarkerAction.do?method=edit";
	} else {
        aClinicalMarkerID = "";
    }
%>

<html:form action="<%= actionName %>" focus="name">

<!-- submitClinicalMarkers.jsp -->
<!-- Main Content Begins -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">

	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

	<tr>
		<td class="formTitle" height="20" colspan="3">Clinical Marker&nbsp;
			<camod:cshelp topic="clinical_marker_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

        <TR align="LEFT" valign="TOP">
                <td class="formRequiredNotice" width="5">*</td>        
                <TD class="formRequiredLabel">Select Clinical Marker:</TD>
			<td class="formField">
			<br>
			<label for="cmName">(if Clinical Marker is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>						
			<html:select styleClass="formFieldSized" size="1" styleId="cmName" property="name" onclick="chkOtherName( this );" >
				<html:options name="<%= Dropdowns.CLINICALMARKERSDROP %>" />										
			</html:select>
			</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherName">Other Clinical Marker:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" styleId="otherName" property="otherName"  size="40" />
		</td>
	</tr>	

        <TR align="LEFT" valign="TOP">
            <td class="formRequiredNotice" width="5">&nbsp;</td>                    
            <TD class="formLabel"><label for="cmValue">Value:</label></TD>
			<td class="formField">
			<html:text styleClass="formFieldSized" size="30" styleId="cmValue" property="value" />			
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="comments">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" styleId="comments" property="comments" cols="32" rows="4"/>			
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
	  				  
	  		      <c:if test="${not empty aClinicalMarkerID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>	  				  
	  				  
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aHistopathologyID" value="<%= aHistopathologyID %>">
				  <input type="hidden" name="aClinicalMarkerID" value="<%= aClinicalMarkerID %>">
				  
				  			
				</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>

	</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>
</html:form>

<SCRIPT>
chkOtherName();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>