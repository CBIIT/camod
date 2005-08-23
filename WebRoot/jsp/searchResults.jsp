<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	

				<tr>
					<td class="formTitle" height="20" colspan="4">Search Results
					</td>

				</tr>

				<tr>
					<td class="greySubTitleLeft" height="20" colspan="2">Your search returned <font color=red><b>24</b></font> records</td>
					<td class="greySubTitleRight" align="right" height="20" colspan="2"><b>Page 1 of 3</b> <html:link action="searchResults"><font color=red>1</font></html:link> <html:link action="searchResults">2</html:link> <html:link action="searchResults">3</html:link>next&gt;</td>
				</tr>
			</TABLE>
			
			<br>
			
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	
				<tr>
					<td class="formTitleBlue" width="4%">No.</td>
					<td class="formTitleBlue" width="32%">Model Descriptor</td>
					<td class="formTitleBlue" width="32%">Species</td>
					<td class="formTitleBlue" width="32%">Tumor Sites</td>
				</tr>

				<!-- Use code to generate Search Results similiar in format to the following static results -->
				<!-- Remember to remove direct .jsp references, when implementing this section -->
				<tr>
					<td class="resultsBoxWhite" width="4%">1</td>
					<td class="resultsBoxWhite" width="32%"><html:link action="viewModelCharacteristics">PB-p53(R172L)</html:link></td>
					<td class="resultsBoxWhite" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxWhiteEnd" width="32%">&nbsp;</td>			
				</tr>

				<tr>
					<td class="resultsBoxGrey" width="4%">2</td>
					<td class="resultsBoxGrey" width="32%"><html:link action="viewModelCharacteristics">PB-p53(R172L) x TRAMP</html:link></td>
					<td class="resultsBoxGrey" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxGreyEnd" width="32%"><b>Prostate Glands</b></td>			
				</tr>

				<tr>
					<td class="resultsBoxWhite" width="4%">3</td>
					<td class="resultsBoxWhite" width="32%"><html:link action="viewModelCharacteristics">12T-10 (LPB-Tag)</html:link></td>
					<td class="resultsBoxWhite" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxWhiteEnd" width="32%"><b>Prostate Glands</b><br>Lymph Node (Metastasis)<br>Liver - Hepatocyte (Metastasis)<br>Lung (Metastasis)<br>Bone (Metastasis)</td>							

				</tr>	

				<tr>
					<td class="resultsBoxGrey" width="4%">4</td>
					<td class="resultsBoxGrey" width="32%"><html:link action="viewModelCharacteristics">AIB+/- x MMTV/PyMT</html:link></td>
					<td class="resultsBoxGrey" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxGreyEnd" width="32%"><b>Prostate Glands</b><br>Liver - Hepatocyte (Metastasis)<br>Lung (Metastasis)<br>Bone (Metastasis)</td>							

				</tr>	

				<tr>
					<td class="resultsBoxWhite" width="4%">5</td>
					<td class="resultsBoxWhite" width="32%"><html:link action="viewModelCharacteristics">PY-p14(R1422)</html:link></td>
					<td class="resultsBoxWhite" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxWhiteEnd" width="32%">&nbsp;</td>			
				</tr>			

				<tr>
					<td class="resultsBoxGrey" width="4%">6</td>
					<td class="resultsBoxGrey" width="32%"><html:link action="viewModelCharacteristics">PB-p53(R172L)</html:link></td>
					<td class="resultsBoxGrey" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxGreyEnd" width="32%"><b>Liver</b></td>			
				</tr>			

				<tr>
					<td class="resultsBoxWhite" width="4%">7</td>
					<td class="resultsBoxWhite" width="32%"><html:link action="viewModelCharacteristics">12T-10 (LPB-Tag)</html:link></td>
					<td class="resultsBoxWhite" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxWhiteEnd" width="32%"><b>Prostate Glands</b><br>Lymph Node (Metastasis)<br>Liver - Hepatocyte (Metastasis)<br>Lung (Metastasis)<br>Bone (Metastasis)</td>							

				</tr>	

				<tr>
					<td class="resultsBoxGrey" width="4%">8</td>
					<td class="resultsBoxGrey" width="32%"><html:link action="viewModelCharacteristics">PB-p53(R172L)</html:link></td>
					<td class="resultsBoxGrey" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxGreyEnd" width="32%"><b>Brain (CNS)</b><br>Lymph Node (Metastasis)</td>			
				</tr>	

				<tr>
					<td class="resultsBoxWhite" width="4%">9</td>
					<td class="resultsBoxWhite" width="32%"><html:link action="viewModelCharacteristics">PB-p53(R172L)</html:link></td>
					<td class="resultsBoxWhite" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxWhiteEnd" width="32%"><b>Brain (CNS)</b><br>Lymph Node (Metastasis)</td>			
				</tr>

				<tr>			
					<td class="resultsBoxGrey" width="4%">10</td>
					<td class="resultsBoxGrey" width="32%"><html:link action="viewModelCharacteristics">PB-p53(R172L)</html:link></td>
					<td class="resultsBoxGrey" width="32%">Mouse (Mus musculus)</td>
					<td class="resultsBoxGreyEnd" width="32%"><b>Brain (CNS)</b><br>Lymph Node (Metastasis)</td>			
				</tr>

			</TABLE>
			
			<br>
			
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	
				<tr>
					<td class="greySubTitleLeft" height="20" colspan="2">Your search returned <font color=red><b>24</b></font> records</td>
					<td class="greySubTitleRight" align="right" height="20" colspan="2"><b>Page 1 of 3</b> <html:link action="searchResults"><font color=red>1</font></html:link> <html:link action="searchResults">2</html:link> <html:link action="searchResults">3</html:link>next&gt;</td>
				</tr>
			
			</TABLE>
			
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>