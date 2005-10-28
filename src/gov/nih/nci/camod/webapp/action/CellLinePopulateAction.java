/**
 * @author pandyas
 * 
 * $Id: CellLinePopulateAction.java,v 1.9 2005-10-28 12:47:26 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2005/10/27 12:53:24  georgeda
 * Added validation
 *
 * Revision 1.7  2005/10/20 20:26:21  pandyas
 * EVSTree (organ) functions properly
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.service.CellLineManager;
import gov.nih.nci.camod.webapp.form.CellLineForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CellLinePopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<CellLinePopulateAction populate> Entered");

		// Create a form to edit
		CellLineForm cellLineForm = (CellLineForm) form;

		// Grab the current Cell Line we are working with related to this
		// animalModel
		String aCellID = request.getParameter("aCellID");
		request.setAttribute("aCellID", aCellID);

		CellLineManager theCellLineManager = (CellLineManager) getBean("cellLineManager");
		CellLine cellLine = theCellLineManager.get(aCellID);

		cellLineForm.setCellLineName(cellLine.getName());
		cellLineForm.setExperiment(cellLine.getExperiment());
		cellLineForm.setResults(cellLine.getResults());
		cellLineForm.setComments(cellLine.getComments());

		/* set Organ attributes */
		System.out.println("<CellLinePopulateAction populate> get the Organ attributes");

		// since we are always querying from concept code (save and edit),
		// simply display VSPreferredDescription
		cellLineForm.setOrgan(cellLine.getOrgan().getEVSPreferredDescription());
		System.out.println("setOrgan= " + cellLine.getOrgan().getEVSPreferredDescription());

		cellLineForm.setOrganTissueCode(cellLine.getOrgan().getConceptCode());
		System.out.println("OrganTissueCode= " + cellLine.getOrgan().getConceptCode());

		// Store the Form in session to be used by the JSP
		request.getSession().setAttribute(Constants.FORMDATA, cellLineForm);

		System.out.println("<CellLinePopulateAction populate> Exited");

		return mapping.findForward("submitCellLines");
	}

	/**
	 * Populate the dropdown menus for submitEnvironmentalFactors
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("submitCellLines");
	}

}
