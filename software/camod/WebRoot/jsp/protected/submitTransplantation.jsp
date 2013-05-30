<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%

/**
 * 
 * $Id: submitTransplantation.jsp,v 1.5 2009-05-04 17:27:53 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2009/04/30 18:39:25  pandyas
 * modified for #17833  	Make sure all references to Transplantation are properly named
 * - modified 4 more files
 *
 * Revision 1.3  2009/03/25 16:27:54  pandyas
 * modified for #17833  	Make sure all references to Tranplantation are properly named
 *
 * Revision 1.2  2008/01/23 22:26:25  pandyas
 * Fixed #11831  	maximize buttion in pop-up windows disabled which prevents user to see full page
 *
 * Revision 1.1  2008/01/16 18:34:35  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.1  2007/10/31 19:25:33  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.2  2007/08/22 13:59:50  pandyas
 * bug #8363: Problems with "other" filed on updated graft page on dev - filed writable when user selects a value for various dropdowns
 *
 * Revision 1.1  2007/08/07 19:36:47  pandyas
 * Removed reference to Transplant as per VCDE comments and after modification to object definition for CDE
 *
 * Revision 1.1  2007/07/31 12:00:10  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.55  2007/06/18 16:12:14  pandyas
 * Fixedmethod name for Zebrafish tree and made text entry enable
 *
 * Revision 1.54  2007/05/18 15:35:31  pandyas
 * Modified species and organ tree to default to no organ button and to work when user goes from species to empty selection
 *
 * Revision 1.53  2007/05/17 19:10:45  pandyas
 * Modified for organ tree code - needs to populate select button correctly still
 *
 * Revision 1.52  2007/05/17 17:58:50  pandyas
 * Consolidated all the clear field scripts to reuse generically on all vocab tree screens where tree value in not a required field
 *
 * Revision 1.51  2007/05/17 12:24:17  pandyas
 * Modified screen to display EVSTree vacabulary
 *
 * Revision 1.50  2007/05/10 02:19:32  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.49  2007/04/25 15:05:51  pandyas
 * Agreed on one help icon for all title bars and one icon for light grey tool tip - removed all others
 *
 * Revision 1.48  2007/04/04 13:25:19  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.47  2007/03/26 12:08:01  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.46  2006/11/10 22:01:34  pandyas
 * TestTrack #465 - delete tooltops, keep tooltips for vocabulary trees and link them to vocab tree help pages
 *
 * Revision 1.45  2006/10/27 13:01:26  pandyas
 * topic="skip" allows us to remove the onclick for ToolTips while preserving it for the title of each page
 *
 * Revision 1.44  2006/08/13 18:29:32  pandyas
 * updated on-line help from Robohelp to ePublisher - modified links
 *
 * Revision 1.43  2006/05/24 16:37:50  georgeda
 * Slight cleanup of clear button
 *
 * Revision 1.42  2006/05/19 18:50:28  pandyas
 * defect #225 - Add clearOrgan functionality to Xenograft screen
 *
 * Revision 1.41  2006/05/19 16:45:00  pandyas
 * Defect #249 - add other to species on the Xenograft screen, needed to add javascript code and field
 *
 * Revision 1.40  2006/05/03 19:05:50  georgeda
 * Move to new EVSTree
 *
 * Revision 1.39  2006/04/20 19:46:23  pandyas
 * Modified host species/  host strain / otherHostStrain text on Xenograft screen
 *
 * Revision 1.38  2006/04/17 19:17:03  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.37  2006/03/31 13:47:17  georgeda
 * Changed the EVSTree call to work w/ new servers
 *
 * Revision 1.36  2006/01/17 19:12:57  pandyas
 * Defect# 378: ToolTip to Organ/Tissue links to histopathology_help instead of xenograft_transplant_help
 *
 * Revision 1.35  2005/12/21 18:00:02  pandyas
 * Modified jsp to add test for other fields - and cleaned up the javascript
 *
 * Revision 1.34  2005/12/12 17:33:37  georgeda
 * Defect #265, store host/origin species in correct places
 *
 * Revision 1.33  2005/12/05 21:29:32  pandyas
 * Follow up for defect#191:  moved text above drop down boxes on page to be consistent.
 *
 * Revision 1.32  2005/12/01 20:04:07  schroedn
 * Defect #243
 *
 * Changed the way the method of observation and observation fields interact. when observation is blank 'method of observation' is greyed out and when the user types in obsevation, the other is editable
 *
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Strain" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.TransplantationForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aTransplantationID = request.getParameter( "aTransplantationID" );
    String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
    
	//if aTransplantationID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "TransplantationAction.do?method=save";
	
	if ( aTransplantationID != null && aTransplantationID.length() > 0 && isDeleted == null) {
		actionName = "TransplantationAction.do?method=edit";
	}
	else {
	    aTransplantationID = "";
	}
%>

<SCRIPT LANGUAGE="JavaScript">
	function getResults(control) {
		getOptions( control );
		chkOtherSpecies();
	}
	function getOptions( control ) {
		form = control.form;
		form.action  = "TransplantationPopulateAction.do?method=setStrainOrganValues";
		form.submit();		
	}	
		
	function chkOtherSpecies() {	
		chkOther(document.forms[0].donorScientificName, document.forms[0].otherDonorScientificName);
	}
	function chkOtherStrain() {
		chkOther(document.forms[0].donorEthinicityStrain, document.forms[0].otherDonorEthinicityStrain);
	}
	function chkObservation() {
	
	    geneticManipulation = document.forms[0].geneticManipulation;
	
		if( geneticManipulation.value != null && geneticManipulation.value != "" ) {
			enableField(document.forms[0].modificationDescription);
		}
		else {
			disableField(document.forms[0].modificationDescription);
		}	
	}
	
</SCRIPT>



<!-- submitTransplantation.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<html:form action="<%= actionName %>" focus="name">
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Transplantation
			<camod:cshelp topic="transplant_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="transName">Name of Transplantation:</label>
		</td>
		<td class="formField">
				<html:text styleClass="formFieldSized" styleId="transName" property="name"  size="30" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="donorScientificName">Donor Species:</label></td>
		<td class="formField">
		<br>
		- if species is not listed, <br>then please select "Other" and then specify it below:
		<br>
		<br>		
			<html:select styleClass="formFieldSized" size="1" styleId="donorScientificName" property="donorScientificName" onchange="getResults(this);" >
				<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
			</html:select>
		</td>
	</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="otherDonorScientificName">if other Species:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" styleId="otherDonorScientificName" property="otherDonorScientificName" size="30"/>			
			</td>
		</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="donorEthinicityStrain">Donor Strain:</label></td>
		<td class="formField">
		<br>
		- if strain is not listed, <br>then please select "Other" and then specify it below:
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" styleId="donorEthinicityStrain" property="donorEthinicityStrain" onchange="chkOtherStrain()">
				<html:options name="<%= Dropdowns.STRAINDROP %>" />
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherDonorEthinicityStrain">if other Strain:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized"  styleId="otherDonorEthinicityStrain" property="otherDonorEthinicityStrain" size="30" />	
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
 			<!-- Display anatomy tree based on animal model species or allow for text entry if no specific tree exists -->
 			<c:choose>
				<c:when test="${donorspeciescommonname == 'Mouse'}">
				<td class="formLabel"><label for="mouseOrgan">Organ/Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />							
					<a href="javascript:showMouseTissueTree('transplantationForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle border=0></a>
				</td>				
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">					
						<html:text styleClass="formFieldSized" disabled="true" styleId="mouseOrgan" property="organ" size="20" />
						<a href="javascript: clearField(document.forms[0].organ, document.forms[0].organTissueCode);"><img border="0" align=middle src="/camod/images/clear.gif"></a>						
					</td>
				</c:when>
				<c:when test="${donorspeciescommonname == 'Rat'}">	
				<td class="formLabel"><label for="ratOrgan">Organ/Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showRatTissueTree('transplantationForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle border=0></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" styleId="ratOrgan" property="organ" size="20"/>
						<a href="javascript: clearField(document.forms[0].organ, document.forms[0].organTissueCode);"><img border="0" align=middle src="/camod/images/clear.gif"></a>						
					</td>
				</c:when>	
				<c:when test="${donorspeciescommonname == 'Zebrafish'}">
				<td class="formLabel"><label for="zebOrgan">Organ/Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showZebrafishTissueTree('transplantationForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle border=0></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" styleId="zebOrgan" property="organ" size="20" />
						<a href="javascript: clearField(document.forms[0].organ, document.forms[0].organTissueCode);"><img border="0" align=middle src="/camod/images/clear.gif"></a>						
					</td>
				</c:when>
				<c:when test="${donorspeciescommonname == 'Human'}">
				<td class="formLabel"><label for="humanOrgan">Organ/Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showHumanTissueTree('transplantationForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle border=0></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" styleId="humanOrgan" property="organ" size="20"  />
						<a href="javascript: clearField(document.forms[0].organ, document.forms[0].organTissueCode);"><img border="0" align=middle src="/camod/images/clear.gif"></a>						
					</td>
				</c:when>				
				<c:otherwise>
				<td class="formLabel"><label for="otherOrgan">Organ/Tissue:</label>&nbsp;
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="false" styleId="otherOrgan" property="organ" size="20"  />
					</td>				
				</c:otherwise>				
    		</c:choose>
	</tr>	


	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="sourceType">Source Type:</label></td>
		<td class="formField">
		<br>				
		- if transplantation type is not listed, <br>then please select "Other" and then specify it below:
		<br>
		<br>		
			<html:select styleClass="formFieldSized" size="1" styleId="sourceType" property="sourceType" onclick="chkOtherSourceType(this);">
				<html:options name="<%= Dropdowns.SOURCETYPEDROP %>" />	
			</html:select>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherSourceType">If other Source Type:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" styleId="otherSourceType" property="otherSourceType"  size="30" />	
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="parentalCellLineName">Parental Cell line:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" styleId="parentalCellLineName" property="parentalCellLineName"  size="30" />	
		</td>
	</tr>			
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="atccNumber">ATTC number (if available):</label>
			</td>
			<td class="formField">		
				<input type=button value="Find ATTC#" onClick="myRef = window.open('http://www.atcc.org/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				<html:text styleClass="formFieldUnSized" size="15" styleId="atccNumber" property="atccNumber"  />&nbsp;&nbsp;<a class="sideMenuLink" href="#" onClick="myRef = window.open('html/disclaimer.html#external','mywin',
										'left=20,top=20,width=800,height=200,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()">Disclaimer</a>
			</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="conditioningRegimen">Conditioning Regimen:</label></td>
		<td class="formField">
		<br>
		- if conditioning regime is not listed, <br>then please select "Other" and then specify it below:
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" styleId="conditioningRegimen" property="conditioningRegimen" onchange="chkOtherCondRegimen()">
				<html:options name="<%= Dropdowns.CONDITIONINGREGIMEN %>" />
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherConditioningRegimen">if other Conditioning Regimen:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized"  styleId="otherConditioningRegimen" property="otherConditioningRegimen" size="30" />	
		</td>
	</tr>		

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="geneticManipulation">Genetic Alteration:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="geneticManipulation" property="geneticManipulation" cols="32" rows="4" onkeypress="chkObservation();" />
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="modificationDescription">Method of Modification:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="modificationDescription" property="modificationDescription" cols="32" rows="4" disabled="true" />
		</td>
	</tr>


    <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="cellAmount">Amount of Cells:</label>
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldUnSized" styleId="cellAmount" property="cellAmount"  size="15" />
		</td>
	</tr>
	
    <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="growthPeriod">Growth Period:</label>
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldUnSized" styleId="growthPeriod" property="growthPeriod"  size="15" />
		</td>
	</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="administrativeSite">Site of Administration:</label>
		</td>
		<td class="formField">
		<br>
		- if administration site is not listed, <br>then please select "Other" and then specify it below:
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" styleId="administrativeSite" property="administrativeSite"  onclick="chkOtherAdminSite();">												
				<html:options name="<%= Dropdowns.TRANSPLANTATIONADMINSITESDROP %>"/>					
			</html:select>			
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherAdministrativeSite">Other Site of Administration:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" size="30" styleId="otherAdministrativeSite" property="otherAdministrativeSite" />			
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
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Host Species / Strain:</td>
		<td class="formField">
			<c:choose>
				<c:when test="${empty modelstrain}">
					<c:out value="${modelspecies}"/> / <c:out value="${othermodelstrain}"/>						
				</c:when>
				<c:otherwise>
					<c:out value="${modelspecies}"/> / <c:out value="${modelstrain}"/>
				</c:otherwise>
			</c:choose>		
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

			      <c:if test="${not empty aTransplantationID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
				  </c:if>
				  				
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aTransplantationID" value="<%= aTransplantationID %>">
							
			</TABLE>
		</td>
	</tr>
	</TABLE>
	
	</td></tr></TABLE>
</td></tr>
</html:form>
</TABLE>


<SCRIPT LANGUAGE="JavaScript">
	chkOtherSourceType();
	chkOtherStrain();
	chkOtherAdminSite();
	chkObservation();
	chkOtherSpecies();
	chkOtherCondRegimen();
	getOptions();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>
