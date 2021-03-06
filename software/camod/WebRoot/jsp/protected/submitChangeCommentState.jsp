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
<%@ page import="gov.nih.nci.camod.Constants" %>

<!-- submitChangeCommentState.jsp -->
<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/adminMenu.jsp" %>
<tr><td>
	<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="60%" height="100%">
	<tr><td>
		<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
		 <html:form action="ChangeCommentsStateAction">
			<tr>
				<td class="formMessage" colspan="3">
					<logic:messagesPresent>
					  <ul>
					    <font color="red">
					      <html:messages id="error">
					        <li><%=error %></li>
					      </html:messages>
					    </font>
					  </ul>
					</logic:messagesPresent>
					* indicates a required field
				</td>
			</tr>

			<tr>
				<td class="formTitle" height="20" colspan="3">
				    Comment for AnimalModel <c:out value="${animalModelStateForm.modelDescriptor}" escapeXml="false"/>
				</td>
			</tr>
	
			<logic:notEmpty name="<%=Constants.Dropdowns.USERSFORROLEDROP%>" >
			    <tr>
				    <td class="formRequiredNotice" width="5">*</td>
				    <td class="formRequiredLabel"><label for="field1">Assigned To</label></td>
				    <td class="formField">			
			            <html:select property="assignedTo">
				            <html:optionsCollection name="<%=Constants.Dropdowns.USERSFORROLEDROP%>"/>
			            </html:select>
				    </td>
			    </tr>
			</logic:notEmpty>
			<logic:empty name="<%=Constants.Dropdowns.USERSFORROLEDROP%>" >
		        <html:hidden property="assignedTo"/>
			</logic:empty>
			<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field2">Remark</label></td>
				<td class="formField">
						<html:textarea styleClass="formFieldSized" property="remark" cols="32" rows="4"/>			
				</td>
			</tr>
	        <html:hidden property="modelId" />
	        <html:hidden property="modelDescriptor" />
	        <html:hidden property="commentsId" />
	        <html:hidden property="event" />
			<tr>
				<td align="right" colspan="3">
					<TABLE cellpadding="4" cellspacing="0" border="0">					
						  <html:submit styleClass="actionButton">
							  <bean:message key="button.submit"/>
						  </html:submit>						  
						  <html:cancel styleClass="actionButton">
						  	  <bean:message key="button.cancel"/>
		  				  </html:cancel>
					</TABLE>
				</td>
			</tr>
		</html:form>
		</TABLE>
	</td></tr>
	</TABLE>
</td></tr>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>