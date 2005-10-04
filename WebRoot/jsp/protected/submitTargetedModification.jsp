<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.TargetedModification" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.TargetedModificationForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>
<%
	String aTargetedModificationID = request.getParameter( "aTargetedModificationID" );

	//if aInducedMutationID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "TargetedModificationAction.do?method=save";
	
	if ( aTargetedModificationID != null )
		actionName = "TargetedModificationAction.do?method=edit";
%>

<SCRIPT LANGUAGE="JavaScript">
		
	function chkOther( control ) {
		ideControl = document.forms[0].otherType;
			
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
		<td class="formTitle" height="20" colspan="3">Enter Information for the Targeted Modification</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Targeted Gene/Locus:</label></td>
		<td class="formField">
			<html:form action="<%= actionName %>" focus="name">	
			
			<html:text styleClass="formFieldSized" property="name" size="10" name="formdata"/>		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Type of Modification:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldSized" size="1" property="modificationType" onchange="chkOther( this );" >
				<html:options name="<%= Dropdowns.TARGETEDMODIFICATIONDROP %>" />										
			</html:select>
			<br>
			-if category you are looking for is not listed, <br>select "Other" and enter the category in the text field below:
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other modification type:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherModificationType" disabled="true" size="10" name="formdata"/>	
		</td>
	</tr>

	<tr>
               
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gene ID ( Entrez ):</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="geneId" size="10" name="formdata"/>	
	</tr>


	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Genetic Background</td>
		<td class="formField">
			<label valign="TOP" for="field1">ES Cell Line &nbsp;</label><br>
				<html:text styleClass="formFieldSized" property="esCellLineName" size="10" name="formdata"/>
			<br>
			<br>
			<label valign="TOP" for="field1">Blastocyst&nbsp;</label><br>
				<html:text styleClass="formFieldSized" property="blastocystName" size="10" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Conditional?</td>
		<td class="formField">
			<html:radio property="conditionedBy" value="yes" /> Conditional 
			<html:radio property="conditionedBy" value="no" /> Not Conditional  
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Conditional Description</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="description" size="10" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Additional Features</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="comments" size="10" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2"><a href="http://www.informatics.jax.org/">MGI Number</a></label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="numberMGI" size="10" name="formdata"/>
		</td>
	</tr>		
	
	<tr>
		<td class="formTitle" height="20" colspan="6">&nbsp;</td>				
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Upload Construct Map (Image)</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="fileServerLocation" size="10" name="formdata"/>			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Title of Construct <br>(enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formUnFieldSized" property="title" rows="4" cols="30" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Description of Construct<br>(enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldUnSized" property="descriptionOfConstruct"  rows="4" cols="30"  name="formdata"/>
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
				  <input type="hidden" name="aTargetedModificationID" value="<%= aTargetedModificationID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
	</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>