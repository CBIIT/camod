<!-- clinical trials protocols -->
<c:set var="cp" value="${clinProtocols[agentId]}"/>

<c:if test="${not empty cp}">
	<c:set var="foundClinicalData" value="1"/>
	<tr>
	    <td class="formTitleBlue" height="20" colspan="2" align="center">
	        <b>Current Clinical Trials</b>
	    </td>
    </tr>	
	<c:forEach var="cpitem" items="${cp}" varStatus="stat2">
		<tr>
			<c:choose>
				<c:when test = "${stat2.count % 2 == 0}">
					<c:set var="tdClass" value="resultsBoxWhite"/>
				</c:when>
				<c:otherwise>
					<c:set var="tdClass" value="resultsBoxGrey"/>
				</c:otherwise>
			</c:choose>
			<td class="<c:out value="${tdClass}"/>" width="25%">
				<c:out value="${cpitem.leadOrganizationName}"/>
			</td>
			<td class="<c:out value="${tdClass}"/>End" width="75%">
				<!-- TODO PU Name -->
				PI:<c:out value="${cpitem.PIName}"/><br/>
				Phase: <c:out value="${cpitem.phase}"/><br/>
				Status of Trial: <c:out value="${cpitem.currentStatus}"/><br/>
			</td>
		</tr>
	</c:forEach>
	<tr><td>&nbsp;</td></tr>
</c:if>