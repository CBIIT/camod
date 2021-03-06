<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.Image" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.ImageForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>
<%@ page import="gov.nih.nci.camod.Constants.CaImage" %>
<%@ page import="java.util.*" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	ImageForm form = (ImageForm) request.getAttribute( "imageForm" );

	// if aImageID is passed in, then we are dealing with 
	// a previously entered model and are editing it
	// otherwise, create a new one
	
	String actionName = "ImageAction.do?method=save";
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	if ( form.getImageId() != null && form.getImageId().length() > 0 && isDeleted == null) {
		actionName = "ImageAction.do?method=edit";
	}
%>

<html:form action="<%= actionName %>" focus="fileLocation" enctype="multipart/form-data">

<SCRIPT LANGUAGE="JavaScript">
	
	function chkStaining() {
	    chkOther(document.forms[0].stainingMethod, document.forms[0].otherStainingMethod);
	}
	
</SCRIPT>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>
	
<!-- submitImages.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
	<!-- -->

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
		<tr>
		    <html:errors/>
			<td class="formMessage" colspan="3">* indicates a required field</td>
		</tr>

		<tr>
			<td class="formTitle" height="20" colspan="3">Images &nbsp;<camod:cshelp topic="images_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="imageTitle">Title of Image<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" styleId="imageTitle" property="title" rows="4" cols="40"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="fileLocation">Upload Image<br>(Image of type .jpg, .jpeg, .gif, or .png)<br>(Required if Alternate URL not used)</label></td>
			<td class="formField">
			
			<c:if test="${not empty imageForm.url}">
				Current Image Thumbnail: <br>
					
				<a href='<c:out value="${imageForm.imageUrl}"/>'>						
				<img src="<c:out value="${imageForm.thumbUrl}"/>" height="40" width="40" alt="<c:out value="${imageForm.url}"/>" target="_blank">				
				Click to View</a>
				<br><br>													
			</c:if>
						
			<html:file styleClass="formFieldSized" size="40" styleId="fileLocation" property="fileLocation"/>	
			
			</td>
		</tr>
	
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="urlAlternEntry">Alternate URL<br>(Required if Upload Image not used)</label>
			</td>
			<td class="formField">
				<br>
				Please enter a complete URL with http:// <br>(http, https, ftp schemes allowed)
				<br>
				<br>			
				<html:text styleClass="formFieldSized" styleId="urlAlternEntry" property="urlAlternEntry" size="30"/>			
			</td>
		</tr>
	
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="descriptionOfConstruct">Description of Image<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" styleId="descriptionOfConstruct" property="descriptionOfConstruct"  rows="4" cols="40" />	
		</tr>
		
	<tr>

		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="stainingMethod">Staining Method:</label>&nbsp;

			<a href="javascript:showStainingMethodTree('imageForm', 'stainingMethodCode', 'stainingMethodName', 'stainingMethod', true)">
				<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle border=0>
			</a>
		</td>						
		<td class="formField">		
			<html:hidden property="stainingMethodCode"/>
			<input type="hidden" name="stainingMethodName" />				
			<html:text styleClass="formFieldSized" disabled="true" styleId="stainingMethod" property="stainingMethod" size="30"  />
			<a href="javascript: clearField(document.forms[0].stainingMethod, document.forms[0].stainingMethodCode);"><img alt="Clear the Selection" border="0" src="/camod/images/clear.gif"></a>
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

	  				  <c:if test="${not empty imageForm.imageId}">
		  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
							  <bean:message key="button.delete"/>
						  </html:submit>
				      </c:if>
				      					
					  <!--  Done this way since html:hidden doesn't seem to work correctly -->
					  <html:hidden property="imageId"/>
					  	
					</html:form>			
				</TABLE>
			</td>
		</tr>
	</TABLE>

	<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
	chkStaining();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>
