<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<bean:define id="hpColl" name="mdl" property="histopathologyCollection"/>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" >
				Histopathology - Model:
				<c:out value="${mdl.modelDescriptor}"/>
				</td>				
			</tr>
<%      
	final List histopathColl = ((AnimalModel)mdl).getHistopathologyCollection();
	final int cc = (histopathColl!=null)?histopathColl.size():0;
	System.out.println("Histopathology rowCount==>" + cc);
%>
		<% if ( cc > 0 ) { %>
			<tr>
				<td class="resultsBoxWhiteEnd">
					<c:forEach var="h" items="${hpColl}" varStatus="histstat">
						<a href="<c:out value="#histo_${histstat.count}"/>">
							C<c:out value="${h.organ.conceptCode}"/> - <c:out value="${h.organ.EVSPreferredDescription}"/>
						</a>
						<bean:define id="mtsColl" name="h" property="metastatisCollection"/>
						<c:forEach var="m" items="${mtsColl}" varStatus="metastat">
							<br>&nbsp;&nbsp;-&nbsp;
							<a href="<c:out value="#metas_${histstat.count}_${metastat.count}"/>">
								C<c:out value="${m.organ.conceptCode}"/> - <c:out value="${m.organ.EVSPreferredDescription}"/>
							</a>&nbsp;(Metastasis)
						</c:forEach>
						<br/>
					</c:forEach>
					<br><br>
					<b>Clincal Markers</b> TBD<br/>
					<br>&nbsp;&nbsp;Marker XYZ ( Value: High )
					<br>&nbsp;&nbsp;Marker XYZ ( Value: High )
				</td>				
			</tr>	
	    <%} else { %>
		     <TR>
		  		<TD class="resultsBoxGreyEnd" colspan=4>
		  		<B><I>No information is available. </I></B> 
		   		</TD>
		     </TR>
		<%}%>		
			
			</TABLE>
			
			<br>

			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<c:forEach var="h" items="${hpColl}" varStatus="histstat">
			<a name="<c:out value="histo_${histstat.count}"/>"/>
			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
				Lesion / Tumor in <c:out value="${h.organ.EVSPreferredDescription}"/>
				</td>
			</tr>				
			
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Organ / Tissue</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
				<c:out value="${h.organ.EVSPreferredDescription}"/>
				</td>
			</tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Age of Tumor Onset</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
					<c:out value="${h.ageOfOnset}"/>
				</td>
			</tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%">
				<b>Average Weight of Tumor (mg)</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<c:out value="${h.weightOfTumor}"/>
				</td>
			</tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Average Volume of Tumor (mm<sup>3</sup>)</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
					 <c:out value="${h.volumeOfTumor}"/>
				</td>
			</tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Tumor Incidence (%)</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
					<c:out value="${h.relationalOperation}"/>
					<c:out value="${h.tumorIncidenceRate}"/>
				</td>
			</tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Survival Information</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
				<c:out value="${h.survivalInfo}" escapeXml="false"/>
				</td>
			</tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Macroscopic Description</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
					<c:out value="${h.grossDescription}" escapeXml="false"/>
				</td>
			</tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Microscopic Description</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
				<c:out value="${h.microscopicDescription}" escapeXml="false"/>
				</td>
			</tr>
			
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<bean:define id="dc" name="h" property="diseaseCollection"/>
				<c:forEach var="d" items="${dc}">
					<c:out value="${d.EVSPreferredDescription}"/><br>
				</c:forEach>
				</td>
			</tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Genetic Alterations found in the Tumor</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${h.geneticAlteration.observation}" escapeXml="false"/>
					<c:if test="${not empty m.geneticAlteration.methodOfObservation}"><br/>
					Method - &nbsp;
					<c:out value="${m.geneticAlteration.methodOfObservation}" escapeXml="false"/>
					</c:if>&nbsp;
				</td>
			</tr>

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Comments</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<c:out value="${h.comments}" escapeXml="false" />
				</td>
			</tr>
            <tr><td></td></tr>
			<bean:define id="mtsColl" name="h" property="metastatisCollection"/>
			<c:forEach var="m" items="${mtsColl}" varStatus="metastat">
			    <a name="<c:out value="metas_${histstat.count}_${metastat.count}"/>"/>
				<tr>
					<td class="greySubTitleLeftEnd" height="20" colspan="2">
						Metastasis in <c:out value="${m.organ.EVSPreferredDescription}"/>
					</td>
				</tr>

				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Organ / Tissue</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${m.organ.EVSPreferredDescription}"/>
					</td>
				</tr>

				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Age at Onset of Metastasis</b></td>
					<td class="resultsBoxGreyEnd" width="75%">&nbsp;
						<c:out value="${m.ageOfOnset}"/>
					</td>
				</tr>			
	
				<tr>
					<td class="resultsBoxWhite" width="25%">
					<b>Average Weight of Tumor (mg)</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
					<c:out value="${m.weightOfTumor}"/>
					</td>
				</tr>

				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Average Volume of Tumor (mm<sup>3</sup>)</b></td>
					<td class="resultsBoxGreyEnd" width="75%">&nbsp;
						 <c:out value="${m.volumeOfTumor}"/>
					</td>
				</tr>			
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Incidence of Metastasis(%)</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
						<c:out value="${m.relationalOperation}"/>
						<c:out value="${m.tumorIncidenceRate}"/>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Survival Information</b></td>
					<td class="resultsBoxGreyEnd" width="75%">&nbsp;
					<c:out value="${m.survivalInfo}"/>
					</td>
				</tr>			
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Macroscopic Description</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
						<c:out value="${m.grossDescription}"/>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Microscopic Description</b></td>
					<td class="resultsBoxGreyEnd" width="75%">&nbsp;
					<c:out value="${m.microscopicDescription}"/>
					</td>
				</tr>
				
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
					<bean:define id="dc" name="m" property="diseaseCollection"/>
					<c:forEach var="d" items="${dc}">
						<c:out value="${d.EVSPreferredDescription}"/><br>
					</c:forEach>
					</td>
				</tr>

				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Genetic Alterations found in the Metastasis</b></td>
					<td class="resultsBoxGreyEnd" width="75%">&nbsp;
						<c:out value="${m.geneticAlteration.observation}"/>
						&nbsp; 
						<c:if test="${not empty m.geneticAlteration.methodOfObservation}"><br/>
						Method - &nbsp;
						<c:out value="${m.geneticAlteration.methodOfObservation}"/>
						</c:if>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Comments</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
					<c:out value="${m.comments}"/>
					</td>
				</tr>
				<tr><td></td></tr>
			</c:forEach>
			</c:forEach>
			
			</TABLE>
		</td>
		</tr>
		</TABLE>
    </td></tr></TABLE>
    </td></tr>
</TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.HISTOPATHOLOGY); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>