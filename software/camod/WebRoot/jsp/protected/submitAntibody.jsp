<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.AntibodyForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aCarcinogenExposureID = request.getParameter( "aCarcinogenExposureID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aCarcinogenExposureID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "AntibodyAction.do?method=save";
	
	if ( aCarcinogenExposureID != null && aCarcinogenExposureID.length() > 0 && isDeleted == null) {
		actionName = "AntibodyAction.do?method=edit";
	}
	else {
	    aCarcinogenExposureID = "";
	}
%>
			
<!-- submitAntibody.jsp -->
<!-- Main Content Begins -->			
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
<!-- -->
	<html:form action="<%= actionName %>" focus="name">
	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
	    <html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Antibody 
			<camod:cshelp topic="antibody_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="abName">Antibody:</label></td>				
		<td class="formField">
			<br>
			(if Antibody is not listed, then please<br>select "Other" from the list and specify it below)
			<br><br>
			<html:select styleId="abName" styleClass="formFieldSized" size="1" property="name"  onclick="chkOtherName();">												
				<html:options name="<%= Dropdowns.ANTIBODYDROP %>"/>					
			</html:select>			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherName">Other Antibody:</label></td>
		<td class="formField">
			  <html:text styleClass="formFieldSized" styleId="otherName" property="otherName"  size="30" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="dosage">Dose:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" styleId="dosage" property="dosage"  size="15" />
			<label for="dosageUnit">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" styleId="dosageUnit" property="dosageUnit" >												
				<html:options name="<%= Dropdowns.ANTIBODYUNITSDROP %>"/>					
			</html:select>	
 		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="administrativeRoute">Administrative Route:</label>
		</td>
		<td class="formField">
		<br>
		- if Administration Route is not listed, <br>then please select "Other" and then specify it below:
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" styleId="administrativeRoute" property="administrativeRoute"  onclick="chkOtherAdminRoute();">												
				<html:options name="<%= Dropdowns.ADMINISTRATIVEROUTEDROP %>"/>					
			</html:select>			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherAdministrativeRoute">Other Administrative Route:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" size="30" styleId="otherAdministrativeRoute" property="otherAdministrativeRoute" />			
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="regimen">Treatment Regimen:</label>		
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" styleId="regimen" property="regimen"  size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="ageAtTreatment">Age at Treatment:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" styleId="ageAtTreatment" property="ageAtTreatment"  size="15" />
			<label for="ageAtTreatmentUnit">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" styleId="ageAtTreatmentUnit" property="ageAtTreatmentUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="gender">Gender:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" styleId="gender" property="type" >												
				<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
			</html:select>
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
			<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
			
				  <html:submit styleClass="actionButton">
					  <bean:message key="button.submit"/>
				  </html:submit>
				  
				  <html:reset styleClass="actionButton">
				  	  <bean:message key="button.reset"/>
  				  </html:reset>

  				  <c:if test="${not empty aCarcinogenExposureID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      				
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aCarcinogenExposureID" value="<%= aCarcinogenExposureID %>">
				  	
		
			</TABLE>
		</td>
	</tr>
</TABLE>
	</html:form>	
	</td></tr></TABLE>
</td></tr>
</TABLE>

<SCRIPT>
chkOtherName();
chkOtherAdminRoute();
</SCRIPT>
<%@ include file="/jsp/footer.jsp" %>