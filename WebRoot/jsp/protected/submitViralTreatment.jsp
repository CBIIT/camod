<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.ViralTreatmentForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%
	String aTherapyID = request.getParameter( "aTherapyID" );
	
	//if aTherapyID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "ViralTreatmentAction.do?method=save";
	
	if ( aTherapyID != null )
		actionName = "ViralTreatmentAction.do?method=edit";
%>

<SCRIPT LANGUAGE="JavaScript">
	
	function chkOther( control ) {
		ideControl = document.ViralTreatmentForm.otherName;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
		
</SCRIPT>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
	<tr>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

	<tr>
		<td class="formTitle" height="20" colspan="3">Viral Treatment</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Virus</label></td>

		<td class="formField">
		<br>
		<label for="field3">(if Virus is not listed, then please<br>select "Other" from the list and specify it below)</label>
		<br><br>
			<html:form action="<%= actionName %>" focus="name">			 
			
			<html:select styleClass="formFieldSized" size="1" property="name" name="formdata" onclick="chkOther( this );">												
				<html:options name="<%= Dropdowns.VIRUSDROP %>"/>					
			</html:select>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Virus:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherName"  size="30" name="formdata"/>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Administrative Routes:</label></td>
		<td class="formField">
			<br>
			<label for="field3">- if Administration Route is not listed, <br>then please select "Other" and then specify it below:</label>
			<br>
			<br>
			<html:select styleClass="formFieldSized" size="1" property="administrativeRoute" name="formdata" onclick="chkOther( this );">												
				<html:options name="<%= Dropdowns.ADMINISTRATIVEROUTEDROP %>"/>					
			</html:select>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Administrative Route:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherAdministrativeRoute"  size="30" name="formdata"/>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Dose:</label></td>
		<td class="formField">			
			<html:text styleClass="formFieldUnSized" property="dosage"  size="10" name="formdata"/>
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="doseUnit" name="formdata">												
				<html:options name="<%= Dropdowns.VIRALTREATUNITSDROP %>"/>					
			</html:select>	
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="regimen" size="30" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageAtTreatment"  size="10" name="formdata"/>
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="ageUnit" name="formdata">												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Gender:</label></td>
		<td class="formField">

			<html:select styleClass="formFieldUnSized" size="1" property="type" name="formdata">												
				<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
			</html:select>
		</td>
	</tr>

	<tr>
		<td align="right" colspan="3">
			<TABLE cellpadding="4" cellspacing="0" border="0">
			
				  <html:submit styleClass="actionButton">
					  <bean:message key="button.submit"/>
				  </html:submit>
				  
				  <html:reset styleClass="actionButton">
				  	  <bean:message key="button.reset"/>
  				  </html:reset>
				
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aTherapyID" value="<%= aTherapyID %>">
				  	
			  </html:form>			
			</TABLE>
		</td>
	</tr>
	</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>