<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
<%@ include file="/jsp/searchMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20">Help Menu</td>				
			</tr>			
			
			<tr>
				<td class="resultsBoxWhiteEnd">				
					<table summary="This table is used to format page content" width="100%">
						<tr>
							<td>
								<P>
									<br>
									<a href="helpSearching.jsp"><b>Help for Searching</b></a>
									<br>&nbsp;
									<br>
									<a href="helpSubmission.jsp"><b>Help for Submission</b></a>
									<br>&nbsp;
					                                    <br>
									<a href="helpAdmin.jsp"><b>Help for Administration</b></a>
									<br>&nbsp;					
								</P>	
							</td>		
							<td align="right">
								<img src="/camod/images/iconHelp.gif">
							</td>
						</tr>
					</table>	
						
				</td> 
			</tr>
			
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>