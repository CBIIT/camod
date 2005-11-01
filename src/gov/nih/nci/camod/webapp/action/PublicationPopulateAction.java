package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.PublicationStatus;
import gov.nih.nci.camod.service.PublicationManager;
import gov.nih.nci.camod.webapp.form.PublicationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PublicationPopulateAction extends BaseAction {

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		System.out.println("<PublicationPopulateAction populate> Entering populate() ");

		// Create a form to edit
		PublicationForm pubForm = (PublicationForm) form;

		String aPubID = request.getParameter("aPubID");		
		String aCellID = request.getParameter("aCellID");
		String aTherapyID = request.getParameter("aTherapyID");
		
		pubForm.setAPubID( aPubID );
		pubForm.setACellID( aCellID );
		pubForm.setATherapyID( aTherapyID );
		
		// Use the current animalModel based on the ID stored in the session
		PublicationManager thePublicationManager = (PublicationManager) getBean("publicationManager");
		Publication thePublication = thePublicationManager.get(aPubID);

		if ( thePublication != null) {
			
			pubForm.setAuthors(thePublication.getAuthors());

			pubForm.setTitle(thePublication.getTitle());

			pubForm.setJournal(thePublication.getJournal());
			pubForm.setVolume(thePublication.getVolume());

			if (thePublication.getYear() != null) {
				pubForm.setYear(thePublication.getYear().toString());
			}
			if (thePublication.getStartPage() != null) {
				pubForm.setStartPage(thePublication.getStartPage().toString());
			}
			if (thePublication.getEndPage() != null) {
				pubForm.setEndPage(thePublication.getEndPage().toString());
			}
			if (thePublication.getPmid() != null) {
				pubForm.setPmid(thePublication.getPmid().toString());
			}
			if (thePublication.isFirstTimeReported().booleanValue()) {
				pubForm.setFirstTimeReported("yes");
			} else {
				pubForm.setFirstTimeReported("no");
			}

			PublicationStatus pubStatus = thePublication.getPublicationStatus();
			pubForm.setName(pubStatus.getName());
		}

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);
		
		return mapping.findForward("submitPublications");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		String aPubID = request.getParameter( "aPubID" );	
		String aCellID = request.getParameter( "aCellID" );
		String aTherapyID = request.getParameter( "aTherapyID" );
		
		// Create a form to edit
		PublicationForm pubForm = (PublicationForm) form;
		
		pubForm.setAPubID( aPubID );
		pubForm.setACellID( aCellID );
		pubForm.setATherapyID( aTherapyID );

		this.dropdown( request, response );
		
		return mapping.findForward("submitPublications");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		System.out.println("<PublicationPopulateAction dropdown> Entering void dropdown()");
		
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PUBDROP, Constants.Dropdowns.ADD_BLANK);
	}
}