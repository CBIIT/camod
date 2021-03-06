<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%
/*
 * $Id: subAdminMenu.jsp,v 1.14 2008-08-12 19:23:50 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2006/12/28 18:27:37  pandyas
 * Renamed folder !SSL!( from robohelp) to caMOD (folder name not used in ePublisher)
 *
 * Revision 1.12  2006/08/14 14:29:31  pandyas
 * updated on-line help from Robohelp to ePublisher - modified links
 *
 * Revision 1.11  2006/08/13 18:19:34  pandyas
 * updated on-line help from Robohelp to ePublisher - modified links
 *
 * Revision 1.10  2005/11/18 21:07:44  georgeda
 * Defect #130, added superuser
 *
 *
 */
%>

<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants' %>
<%@ page import='java.util.List' %>

<SCRIPT src="/camod/scripts/RoboHelp_CSH.js" type=text/javascript></SCRIPT>


<SCRIPT LANGUAGE="JavaScript">	
	function blankCurrentState() {
        document.curationAssignmentForm.currentState.value = '';
    }
</SCRIPT>

<TR><TD class=subMenuPrimaryTitle height=22>ADMINISTRATION</TD></TR>
<TR><TD class=subMenuPrimaryItems>

<DIV>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminRolesPopulateAction">ADMIN ROLES</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminCommentsAssignmentPopulateAction?link=true">VIEW COMMENT ASSIGNMENT</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminModelsAssignmentPopulateAction" onclick="blankCurrentState()">VIEW MODEL ASSIGNMENT</html:link>
	<BR>
	<% 
	   List theRoles = (List) pageContext.getSession().getAttribute(Constants.CURRENTUSERROLES);
	   if (theRoles.contains(Admin.Roles.COORDINATOR))
	   {
	%>  
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminRolesAssignmentPopulateAction">ROLE MANAGEMENT</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminUserManagementPopulateAction">USER MANAGEMENT</html:link>
	<BR>
	<%
	   }
	%>	
	<% 
	   if (theRoles.contains(Admin.Roles.SUPER_USER))
	   {
	%>
    <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminEditModelsPopulateAction"  onclick="blankCurrentState()">EDIT MODELS</html:link>
	<BR>
	<%
	   }
	%>	
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleId="<%=Constants.USER_GUIDE_WIKI_LINK%>" styleClass="subMenuPrimary" href='javascript:void(0)' onclick="myRef = window.open(this.id,'mywin',
															'left=20,top=20,width=700,height=500,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">HELP</html:link>	
</DIV>

<BR>
</TD></TR>




