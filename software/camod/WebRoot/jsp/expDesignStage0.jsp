<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%
/*
 *  author:  pandyas
 *
 *  $Log: not supported by cvs2svn $
 *  Revision 1.3  2005/11/15 17:38:22  pandyas
 *  Fixed Defects #7-8 and #9-11:  6 Links to experimental design and yeast strains, formatting 3 experimental design headers
 *
 *
 *  $Id: expDesignStage0.jsp,v 1.4 2005-11-17 17:59:24 pandyas Exp $
 *
 */
%>
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

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
				<td class="formTitle" height="20" colspan="9">Experiment Design - Yeast Screening - Stage 0</td>				
			</tr>

			</TABLE>
			<br>
			<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" align="center" width="100%">
				<TR>
					<TD class="formTitleBlue" height="20"><B>Experimental Design</B></TD>
				</TR>

				<td colspan="9">
			<table summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
				<TR>
	  				<TD class="resultsBoxGreyEnd" width="100%">Stage0 experiments were performed at a single dose, 50<FONT face=Symbol>m</FONT>M, in duplicate. </TD>
				</TR>
				<TR>	  			
	  				<TD class="resultsBoxWhiteEnd" >Six yeast strains were used in Stage0 experiments, containing 1 or 2 mutations of interest.  
      				All 6 strains also were mutant for 3 genes(      			
     			
      				<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=ERG6','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>erg6</I></a>, 
					<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=pdr1','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>pdr1</I></A> and
					<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=pdr3','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>pdr3</I></A>)

      				that increase yeast&rsquo;s sensitivity to drugs. Three strains contained single mutations in genes of interest: 

      				<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=rad50','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>rad50</I></A>, 
			      	<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=rad53','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>mec2-1</I></A> or 
				 	<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=bub3','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>bub3</I></A>.
      	
			      	The other three strains were doubly mutant: 
      	
			      	<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=rad18','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>rad18</I></A> + 
			      	<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=mlh1','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>mlh1</I></A>, 
			      	<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=sgs1','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>sgs1</I></A> +
			      	<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?sgdid=S0002359','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>mgt1</I></A> and 
			      	<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=rad14','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>rad14</I></A> +  
			      	<A href="JavaScript:void window.open('http://www.yeastgenome.org/cgi-bin/locus.fpl?locus=cln2','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');"><I>CLN2oe</I></A>.
			      	<br/><input type="button" value="Disclaimer" onclick="myRef = window.open('html/disclaimer.html#external','mywin',
						'left=20,top=20,width=800,height=200,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()" class="submitLink"/>
					</TD>
				</TR>
				<TR>
					<TD class="resultsBoxGreyEnd" >A compound is considered active when one or more strains show &gt; 70% growth inhibition. </TD>
				</TR>

			</table></td>

			<tr><td colspan="9"> &nbsp; </td></tr>

			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>