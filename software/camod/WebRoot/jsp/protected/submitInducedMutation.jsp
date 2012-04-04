<%

/**
 * 
 * $Id: submitInducedMutation.jsp,v 1.35 2008-01-23 22:26:25 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.34  2007/11/01 15:30:08  pandyas
 * Fixed #9756     geneID has been replaced with entrezGeneID - existing values need to be moved to new place
 *
 * Revision 1.33  2007/04/09 12:35:02  pandyas
 * modified after caMOD 2.3 unit testing
 *
 * Revision 1.32  2007/04/04 13:24:28  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.31  2007/03/26 12:07:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.30  2006/11/10 22:01:34  pandyas
 * TestTrack #465 - delete tooltops, keep tooltips for vocabulary trees and link them to vocab tree help pages
 *
 * Revision 1.29  2006/10/27 13:01:26  pandyas
 * topic="skip" allows us to remove the onclick for ToolTips while preserving it for the title of each page
 *
 * Revision 1.28  2006/08/13 18:26:47  pandyas
 * updated on-line help from Robohelp to ePublisher - modified links
 *
 * Revision 1.27  2006/04/17 19:07:33  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.26  2005/12/27 20:12:00  pandyas
 * Moved html:form action, cleaned up script, removed disabled=true
 *
 * Revision 1.25  2005/12/12 17:54:56  georgeda
 * Defect #268, added a return in front of all the confirms.
 *
 * Revision 1.24  2005/12/01 20:05:13  schroedn
 * Added CVS log statement to top of file
 *
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.InducedMutation" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.InducedMutationForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aInducedMutationID = request.getParameter("aInducedMutationID");
    String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
    
	//if aInducedMutationID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "InducedMutationAction.do?method=save";
	
	if ( aInducedMutationID != null && aInducedMutationID.length() > 0 && isDeleted == null) {
		actionName = "InducedMutationAction.do?method=edit";
	}
	else {
	    aInducedMutationID = "";
	}
%>

<html:form action="<%= actionName %>" focus="name">	

<SCRIPT LANGUAGE="JavaScript">
		
	function chkInducingAgent() {
	    chkOther(document.forms[0].type, document.forms[0].otherType);
	}	 
</SCRIPT>


<!-- submitInducedMutation.jsp -->
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
		<td class="formTitle" height="20" colspan="3">Induced Mutation
			<camod:cshelp topic="induced_mutation_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>				
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="indName">Name of Inducing Agent:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" styleId="indName" property="name" size="10" />		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="indType">Inducing Agent Category:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldSized" size="1" styleId="indType" property="type" onchange="chkInducingAgent();" >
				<html:optionsCollection name="<%= Dropdowns.INDUCEDMUTATIONDROP %>" />										
			</html:select>
			<br>
			-if category you are looking for is not listed, <br>select "Other" and enter the category in the text field below:
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherType">Other Category:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" styleId="otherType" property="otherType"  size="30" />		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="casNumber">CAS number:</label>
		</td>
		<td class="formField">
			<input type=button value="Find CAS #" onClick="myRef = window.open('http://chemfinder.cambridgesoft.com/','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>		
			&nbsp;&nbsp;
			<html:text styleClass="formFieldUnSized" styleId="casNumber" property="casNumber" size="20" />&nbsp;&nbsp;<a class="sideMenuLink" href="#" onClick="myRef = window.open('html/disclaimer.html#external','mywin',
										'left=20,top=20,width=800,height=200,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()">Disclaimer</a>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="geneIdentifier">Entrez Gene ID:</label>
		</td>
		<td class="formField">
		<input type=button value="Find Gene ID" onClick="myRef = window.open('http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=gene','mywin',
		'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>		
		&nbsp;&nbsp;
		<html:text styleClass="formFieldUnSized" styleId="geneIdentifier" property="geneIdentifier" size="20" />		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="indDesc">Description:</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="indDesc" property="description"  rows="4" cols="32"  />		
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="observation">Observation:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="observation" property="observation" rows="4" cols="32" onkeypress="chkObservation();"/>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="methodOfObservation">Method of Observation:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="methodOfObservation" property="methodOfObservation" rows="4" cols="32"  />		
		</td>
	</tr>
		
	<tr>
		<c:if test="${modelspeciescommonname == 'Mouse'}">			
				<td class="formRequiredNotice" width="5">&nbsp;</td>
				<td class="formLabel"><label for="mgiId">MGI Identifier:</label>
				</td>
				<td class="formField">
					<input type=button value="Find MGI ID" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
								'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
					&nbsp;&nbsp;
					<html:text styleClass="formFieldUnSized" size="25" styleId="mgiId" property="mgiId"  />&nbsp;&nbsp;<a class="sideMenuLink" href="#" onClick="myRef = window.open('html/disclaimer.html#external','mywin',
										'left=20,top=20,width=800,height=200,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()">Disclaimer</a>
				</td>
		</c:if>				
	</tr>	
	<tr>
		<c:if test="${modelspeciescommonname == 'Zebrafish'}">	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="zfinId">ZFIN Identifier:</label>
			</td>
			<td class="formField">
				<input type=button value="Find ZFIN ID" onClick="myRef = window.open('http://zfin.org/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				&nbsp;&nbsp;
				<html:text styleClass="formFieldUnSized" size="25" styleId="zfinId" property="zfinId"  />
			</td>
		</c:if>
	</tr>	
	
	<tr>
		<c:if test="${modelspeciescommonname == 'Rat'}">	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="rgdId">RGD Identifier:</label>
			</td>
			<td class="formField">
				<input type=button value="Find RGD ID" onClick="myRef = window.open('http://rgd.mcw.edu/strains/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				&nbsp;&nbsp;
				<html:text styleClass="formFieldUnSized" size="25" styleId="rgdId" property="rgdId"  />
			</td>
		</c:if>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="comments">Comment:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="comments" property="comments"  rows="4" cols="32"  />	
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
				
				  <c:if test="${not empty aInducedMutationID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aInducedMutationID" value="<%= aInducedMutationID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
	</TABLE>

	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
chkInducingAgent();
chkObservation();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>