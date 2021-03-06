<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Therapy" %>
<%@ page import="gov.nih.nci.camod.domain.CellLine" %>	
<%@ page import="gov.nih.nci.camod.domain.CarcinogenExposure" %>
<%@ page import="gov.nih.nci.camod.domain.TransientInterference" %>
<%@ page import="gov.nih.nci.camod.domain.CaelmirStudyData" %>
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<bean:define id="mdl" name="animalmodel"/>
<TR><TD class=subMenuPrimaryTitle height=22>MODEL DETAILS</TD></TR>
<TR><TD class=subMenuPrimaryGreyTitle height=27>
	Viewing Model: <b>
	<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>&nbsp;( <%= request.getSession().getAttribute(Constants.MODELID)%> )</b><br>
</TD></TR>
<TR><TD class=subMenuPrimaryItems>
<DIV>
	<bean:parameter id="mdl" name="aModelID"/>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewModelAction.do?unprotected_method=populateModelCharacteristics&aModelID=<%=mdl%>" styleClass="subMenuPrimary">MODEL CHARACTERISTICS</a>
	<BR>	
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			AnimalModel am = (AnimalModel)request.getSession().getAttribute(Constants.ANIMALMODEL);
			List l = new ArrayList(am.getEngineeredGeneCollection());
			int cc = (l!=null)?l.size():0;
			boolean found = false;
				if ( cc > 0 ) {
				found = true;
				}
				if (!found) {
					if (am.getSpontaneousMutationCollection() != null && am.getSpontaneousMutationCollection().size() > 0) {
				    	found = true;
					}
				}
			if (found) {
		%>
		<a href="ViewModelAction.do?unprotected_method=populateEngineeredGene&aModelID=<%=mdl%>" styleClass="subMenuPrimary">GENETIC DESCRIPTION</a>	
	    <%} else { %>
		GENETIC DESCRIPTION
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = new ArrayList(am.getCarcinogenExposureCollection());
			cc = (l!=null)?l.size():0;
			found = false;
			if ( cc > 0 ) {
				for (int i=0; i<cc; i++) {
					CarcinogenExposure ce = (CarcinogenExposure)l.get(i);
						if( ce.getEnvironmentalFactor() !=null) {
							found = true;
							break;
						}					
				}
			}
			if (!found) {
				if (am.getGeneDeliveryCollection() != null && am.getGeneDeliveryCollection().size() > 0) {
				    found = true;
				}
			}
			if (found) {
		%>
		<a href="ViewModelAction.do?unprotected_method=populateCarcinogenicInterventions&aModelID=<%=mdl%>" styleClass="subMenuPrimary">CARCINOGENIC INTERVENTIONS</a>
	    <%} else { %>
		CARCINOGENIC INTERVENTIONS
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = new ArrayList(am.getTransientInterferenceCollection());
			cc = (l!=null)?l.size():0;
			found = false;
			if ( cc > 0 ) {
                found = true;
			}					
			if (found) {
		%>
		<a href="ViewModelAction.do?unprotected_method=populateTransientInterference&aModelID=<%=mdl%>" styleClass="subMenuPrimary">TRANSIENT INTERFERENCE</a>
	    <%} else { %>
		TRANSIENT INTERFERENCE
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = new ArrayList(am.getPublicationCollection());
			cc = (l!=null)?l.size():0;
			found = false;
				if ( cc >0 ) {
					found = true;
				}
			if (!found) {
				l = new ArrayList(am.getCellLineCollection());
				cc = (l!=null)?l.size():0;
					if ( cc > 0 ) {
						for (int i=0; i<cc; i++) {					
						CellLine c = (CellLine)l.get(i);
						if( c.getPublicationCollection() !=null && c.getPublicationCollection().size() > 0) {
							found = true;
							break;
						}
					}
				}
			}
			if (!found) {
				l = new ArrayList(am.getTherapyCollection());
				cc = (l!=null)?l.size():0;
					if ( cc > 0 ) {
						for (int i=0; i<cc; i++) {					
						Therapy t = (Therapy)l.get(i);
						if( t.getPublicationCollection() !=null && t.getPublicationCollection().size() > 0) {
							found = true;
							break;
						}
					}
				}
			}
			if (found) {			
		%>
		<a href="ViewModelAction.do?unprotected_method=populatePublications&aModelID=<%=mdl%>" styleClass="subMenuPrimary">PUBLICATIONS</a>	
	    <%} else { %>
		PUBLICATIONS
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = new ArrayList(am.getHistopathologyCollection());
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateHistopathology&aModelID=<%=mdl%>" styleClass="subMenuPrimary">HISTOPATHOLOGY</a>
	    <%} else { %>
		HISTOPATHOLOGY
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = new ArrayList(am.getTherapyCollection());
			cc = (l!=null)?l.size():0;
			found = false;
			if ( cc > 0 ) {
				for (int i=0; i<cc; i++) {
					Therapy t = (Therapy)l.get(i);
						if( t.getAgent() !=null) {
							found = true;
							break;
						}					
				}
			}
			if (!found) {
				l = new ArrayList(am.getCaelmirStudyDataCollection());
				cc = (l!=null)?l.size():0;
				found = false;
				if ( cc > 0 ) {
					for (int i=0; i<cc; i++) {
						CaelmirStudyData c = (CaelmirStudyData)l.get(i);
							if( c.getStudyName() !=null) {
								found = true;
								break;
							}					
					}
				}
			}			
			if (found) {
		%>
		<a href="ViewModelAction.do?unprotected_method=populateTherapeuticApproaches&aModelID=<%=mdl%>" styleClass="subMenuPrimary">THERAPEUTIC APPROACHES</a>
	    <%} else { %>
		THERAPEUTIC APPROACHES
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = new ArrayList(am.getCellLineCollection());
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateCellLines&aModelID=<%=mdl%>" styleClass="subMenuPrimary">CELL LINES</a>
	    <%} else { %>
		CELL LINES
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = new ArrayList(am.getImageCollection());
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateImages&aModelID=<%=mdl%>" styleClass="subMenuPrimary">IMAGES</a>
	    <%} else { %>
		IMAGES
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = new ArrayList(am.getMicroArrayDataCollection());
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateMicroarrays&aModelID=<%=mdl%>" styleClass="subMenuPrimary">MICROARRAYS</a>	
	    <%} else { %>
		MICROARRAYS
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = new ArrayList(am.getTransplantationCollection());
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) {
		%>
		<a href="ViewModelAction.do?unprotected_method=populateTransplantation&aModelID=<%=mdl%>" styleClass="subMenuPrimary">TRANSPLANTATION</a>	
	    <%} else { %>
			TRANSPLANTATION
		<%}%>
	<BR>
	<BR>
	<!-- Add checks to see if in submission mode, if so add VIEW MY MODELS option -->
	
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;<html:link styleClass="subMenuPrimary" action="searchResults"> BACK TO SEARCH RESULTS</html:link>	
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;<html:link styleId="<%=Constants.USER_GUIDE_WIKI_LINK%>" styleClass="subMenuPrimary" href='javascript:void(0)' onclick="myRef = window.open(this.id,'mywin',
															'left=20,top=20,width=700,height=500,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"> HELP</html:link>	


</DIV>
</TD></TR>