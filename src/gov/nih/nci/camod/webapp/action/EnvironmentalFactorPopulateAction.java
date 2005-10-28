/**
 * 
 * $Id: EnvironmentalFactorPopulateAction.java,v 1.11 2005-10-28 12:47:26 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.9  2005/10/20 20:37:53  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import java.util.List;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.EnvironmentalFactorForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EnvironmentalFactorPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form EnvironmentalFactorForm Used by
	 * submitEnvironmentalFactors
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<EnvironmentalFactorPopulateAction populate> Entering populate() ");

		// Create a form to edit
		EnvironmentalFactorForm envForm = (EnvironmentalFactorForm) form;

		// Grab the current Therapy we are working with related to this
		// animalModel
		String aTherapyID = request.getParameter("aTherapyID");
		request.setAttribute("aTherapyID", aTherapyID);

		/* Grab the current modelID from the session */
		String modelID = "" + request.getSession().getAttribute(Constants.MODELID);
		System.out.println("<EnvironmentalFactorAction save> Grabed the current modelID");

		// Use the current animalModel based on the ID stored in the session
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel am = animalModelManager.get(modelID);

		// retrieve the list of all therapies from the current animalModel
		List therapyList = am.getTherapyCollection();

		Therapy therapy = new Therapy();

		// find the specific one we need
		for (int i = 0; i < therapyList.size(); i++) {
			therapy = (Therapy) therapyList.get(i);
			if (therapy.getId().toString().equals(aTherapyID))
				break;
		}

		// Set the otherName and/or the selected name attribute
		if (therapy.getAgent().getNameUnctrlVocab() != null) {
			envForm.setName(Constants.Dropdowns.OTHER_OPTION);
			envForm.setOtherName(therapy.getAgent().getNameUnctrlVocab());
		} else {
			envForm.setName(therapy.getAgent().getName());
		}

		// Set the other administrative route and/or the selected administrative
		// route
		if (therapy.getTreatment().getAdminRouteUnctrlVocab() != null) {
			envForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
			envForm.setOtherAdministrativeRoute(therapy.getTreatment().getAdminRouteUnctrlVocab());
		} else {
			envForm.setAdministrativeRoute(therapy.getTreatment().getAdministrativeRoute());
		}

		if (therapy.getTreatment().getSexDistribution() != null) {
			envForm.setType(therapy.getTreatment().getSexDistribution().getType());
		}
		envForm.setDosage(therapy.getTreatment().getDosage());
		envForm.setRegimen(therapy.getTreatment().getRegimen());
		envForm.setAgeAtTreatment(therapy.getTreatment().getAgeAtTreatment());

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		// Store the Form in session to be used by the JSP
		request.getSession().setAttribute(Constants.FORMDATA, envForm);

		return mapping.findForward("submitEnvironmentalFactors");

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

		// blank out the FORMDATA Constant field
		EnvironmentalFactorForm envForm = (EnvironmentalFactorForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, envForm);

		// setup dropdown menus
		this.dropdown(request, response);

		return mapping.findForward("submitEnvironmentalFactors");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("<EnvironmentalFactorPopulateAction dropdown> Entering void dropdown()");

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ENVIRONFACTORDROP, Constants.Dropdowns.ADD_BLANK);

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ENVFACTORUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);

	}
}
