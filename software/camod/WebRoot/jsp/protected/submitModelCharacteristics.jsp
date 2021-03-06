<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>
<SCRIPT src="/camod/scripts/CalendarPopup.js" type=text/javascript></SCRIPT>
<SCRIPT src="/camod/scripts/RoboHelp_CSH.js" type=text/javascript></SCRIPT>

<SCRIPT LANGUAGE="JavaScript" ID="js1">
var cal1 = new CalendarPopup();
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
	
	function chkOtherStrain() {
		chkOther(document.forms[0].ethinicityStrain, document.forms[0].otherEthnicityStrain);
	}

	function getOptions( control ) {
		form = control.form;
		form.action = "AnimalModelPopulateAction.do?method=setStrainDropdown&page=modelChar";
		form.submit();
	}
	
	function immediateRelease()
	{
	    document.forms[0].calendarReleaseDateDisp.value = '';
	    document.forms[0].calendarReleaseDate.value = '';
	    
	    return true;
	}
	
	function selectFromCalendar()
	{
		cal1.select(document.forms[0].calendarReleaseDateDisp,
		            'calendarReleaseDateDisp',
		            'MM/dd/yyyy'); 
	    return true;
	}
	
	function transferFields() {
		document.forms[0].calendarReleaseDate.value = document.forms[0].calendarReleaseDateDisp.value;
		document.forms[0].calendarReleaseDate.disabled = false;
	}
	
	function setFields() {
		document.forms[0].calendarReleaseDateDisp.value = document.forms[0].calendarReleaseDate.value;
	}
	
</SCRIPT>

<!-- submitModelCharacteristics.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	<html:form action="EditAnimalModel.do?method=edit" focus="modelDescriptor" onsubmit="transferFields()">
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

		<tr>
			<td class="formTitle" height="20" colspan="3"><a>Model Characteristics</a>&nbsp;
				<camod:cshelp topic="model_characteristics_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="modelDescriptor">Model Descriptor:</label>
			</td>
			<td class="formField">	
					<html:text styleClass="formFieldSized" styleId="modelDescriptor" property="modelDescriptor" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="principalInvestigator">Principal Investigator:</label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" styleId="principalInvestigator" property="principalInvestigator">
					<html:optionsCollection name="<%= Dropdowns.PRINCIPALINVESTIGATORDROP %>" />	
				</html:select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Is this model a tool strain?			
			</td>
			<td class="formField">
				<html:radio styleId="isToolStrain1" property="isToolStrain" value="yes" /> <label for="isToolStrain1">Yes</label> 
				<html:radio styleId="isToolStrain2" property="isToolStrain" value="no" /> <label for="isToolStrain2">No</label>  
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="scientificName"><b>Species:</b></label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" styleId="scientificName" property="scientificName" onchange="getOptions(this);">
					<html:optionsCollection name="<%= Dropdowns.NONHUMANSPECIESDROP %>" filter="false"/>												
				</html:select>				
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="ethinicityStrain"><b>Strain:</b></label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" styleId="ethinicityStrain" property="ethinicityStrain" onclick="chkOtherStrain();">
					<html:options name="<%= Dropdowns.STRAINDROP %>"/>		
				</html:select>
			</td>
		</tr>	

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="otherEthnicityStrain">if other Strain:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" styleId="otherEthnicityStrain" property="otherEthnicityStrain" size="30"/>			
			</td>
		</tr>

		<c:if test="${modelspeciescommonname == 'Zebrafish'}">
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="developmentalStage">Developmental Stage:</label>				
			</td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" styleId="developmentalStage" property="developmentalStage" >
					<html:options name="<%= Dropdowns.DEVELOPMENTALSTAGES %>"/>		
				</html:select>
			</td>
		</tr>
		</c:if>
				
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="genotype">Genotype:</label>				
			</td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" styleId="genotype" property="genotype" size="30"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="nomenclature">Nomenclature:</label>				
			</td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" styleId="nomenclature" property="nomenclature" size="30"/>
			</td>
		</tr>				

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="experimentDesign">Experimental Design:</label>
			</td>
			<td class="formField">
					<html:textarea styleClass="formFieldUnSized" styleId="experimentDesign" property="experimentDesign" cols="50" rows="4" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="phenotype">Phenotype:</label>
			</td>
			<td class="formField">
					<html:textarea styleClass="formFieldUnSized" styleId="phenotype" property="description" cols="50" rows="4" />			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="gender">Gender:</label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" styleId="gender" property="type" >												
					<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
				</html:select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="breedingNotes">Breeding Notes:</label>
			</td>
			<td class="formField">
					<html:text styleClass="formFieldSized" styleId="breedingNotes" property="breedingNotes" size="30"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="url">Website for add. info:</label>
			</td>
			<td class="formField">
					<html:text styleClass="formFieldSized" styleId="url" property="url" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><b>Record Release Date:</b>
			</td>
			<td class="formField">
				<html:radio styleId="releaseDate1" property="releaseDate" value="immediately" onclick="return immediateRelease();" /> <label for="releaseDate1">Release record immediately</label> <br> 
				<html:radio styleId="releaseDate2" property="releaseDate" value="after" onclick="return selectFromCalendar();" /> <label for="releaseDate2">Release Record After:</label><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="calendarReleaseDateDisp">(Select date from pop up calender)</label> 
				<html:hidden disabled="false" property="calendarReleaseDate" />
				<INPUT styleClass="formFieldSized2" disabled="true" property="calendarReleaseDateDisp" id="calendarReleaseDateDisp" size="10"/>	<br>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="comments">Comment:</label>
			</td>
				<td class="formField">
						<html:textarea styleId="comments" styleClass="formFieldUnSized" property="comments" cols="50" rows="4" />			
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
				</TABLE>
			</td>
		</tr>
	</html:form>		
	</TABLE>

<!-- -->
	</td></tr></TABLE>
	
</td></tr></TABLE>


<SCRIPT LANGUAGE="JavaScript">
	chkOtherStrain();
	setFields();
</SCRIPT>	

<%@ include file="/jsp/footer.jsp" %>





