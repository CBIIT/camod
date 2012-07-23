/**
 * 
 * $Id: GrowthFactorPopulateAction.java,v 1.13 2008-08-14 16:52:49 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2007/10/31 18:11:18  pandyas
 * Fixed #8188 	Rename UnctrlVocab items to text entries
 *
 * Revision 1.11  2006/04/17 19:09:41  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.10  2005/11/03 13:59:10  georgeda
 * Fixed delete functionality
 *
 * Revision 1.9  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.8  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.7  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.6  2005/10/20 20:38:17  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.GrowthFactorForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GrowthFactorPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form SurgeryForm Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("<GrowthFactorPopulateAction populate> ... ");

		GrowthFactorForm growthFactorForm = (GrowthFactorForm) form;

        String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");
        
        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
        CarcinogenExposure ce = carcinogenExposureManager.get(aCarcinogenExposureID);

		// Handle back-arrow on the delete
		if (ce == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {

            request.setAttribute("aCarcinogenExposureID", aCarcinogenExposureID);
			
			// Set the otherName and/or the selected name attribute
			if (ce.getEnvironmentalFactor().getNameAlternEntry() != null) {
				growthFactorForm.setName(Constants.Dropdowns.OTHER_OPTION);
				growthFactorForm.setOtherName(ce.getEnvironmentalFactor().getNameAlternEntry());
			} else {
				growthFactorForm.setName(ce.getEnvironmentalFactor().getName());
			}

			// Set the other administrative route and/or the selected
			// administrative route
			// Added for MTB models that do not have a treatment id
			if (ce.getTreatment() != null ) {			
				if (ce.getTreatment().getAdminRouteAlternEntry() != null) {
					growthFactorForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
					growthFactorForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
				} else {
					growthFactorForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
				}
	
				if (ce.getTreatment().getSexDistribution() != null) {
					growthFactorForm.setType(ce.getTreatment().getSexDistribution().getType());
				}
				growthFactorForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
	            growthFactorForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit());
				growthFactorForm.setDosage(ce.getTreatment().getDosage());
	            growthFactorForm.setDosageUnit(ce.getTreatment().getDosageUnit());
				growthFactorForm.setRegimen(ce.getTreatment().getRegimen());
			}
			
            if (ce.getEnvironmentalFactor().getComments() != null)
            {
            	growthFactorForm.setComments(ce.getEnvironmentalFactor().getComments());
            }			
		}

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);
		saveToken(request);

		return mapping.findForward("submitGrowthFactors");
	}

	/**
	 * Populate the dropdown menus for submitSurgeryOther
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
		GrowthFactorForm growthFactorForm = (GrowthFactorForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, growthFactorForm);

		// setup dropdown menus
		this.dropdown(request, response);
		saveToken(request);

		return mapping.findForward("submitGrowthFactors");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Prepopulate all dropdow2n fields, set the global Constants to the
		// following
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GROWTHFACTORDOSEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GROWTHFACTORDROP, Constants.Dropdowns.ADD_BLANK);
	}

}
