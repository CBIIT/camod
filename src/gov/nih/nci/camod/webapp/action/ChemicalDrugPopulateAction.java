package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.ChemicalDrugForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChemicalDrugPopulateAction extends BaseAction{
	
	/** 
	 * Pre-populate all field values in the form SurgeryForm 
	 *  Used by <jspName>
	 * 
	 */ 
	public ActionForward populate( ActionMapping mapping, 
								   ActionForm form,
						           HttpServletRequest request,
						           HttpServletResponse response)
	  throws Exception {
	  
		System.out.println( "<ChemicalDrugPopulateAction populate> ... " );
		
		ChemicalDrugForm chemicalDrugForm = ( ChemicalDrugForm ) form;
		
		String aTherapyID = request.getParameter( "aTherapyID" );
		
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		AnimalModel am = animalModelManager.get( modelID );	
		
		//retrieve the list of all therapies from the current animalModel
		List therapyList = am.getTherapyCollection();
		
		Therapy ty = new Therapy();
		
		//find the specific one we need
		for ( int i=0; i<therapyList.size(); i++ )
		{
			ty = (Therapy)therapyList.get(i);
			if ( ty.getId().toString().equals( aTherapyID) )
				break;
		}
		
		chemicalDrugForm.setType( ty.getTreatment().getSexDistribution().getType() );
		chemicalDrugForm.setAgeAtTreatment( ty.getTreatment().getAgeAtTreatment() );
		chemicalDrugForm.setDosage( ty.getTreatment().getDosage() );		
		chemicalDrugForm.setName( ty.getAgent().getName() );
		chemicalDrugForm.setRegimen(ty.getTreatment().getRegimen() );
		chemicalDrugForm.setAdministrativeRoute(ty.getTreatment().getAdministrativeRoute() );		
		chemicalDrugForm.setCASNumber( ty.getAgent().getCasNumber() );
		chemicalDrugForm.setNSCNumber( ty.getAgent().getNscNumber().toString() );
		
		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );
		
		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, chemicalDrugForm );
		
      return mapping.findForward( "submitChemicalDrug" );
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
	public ActionForward dropdown( ActionMapping mapping, 
			   					   ActionForm form,
			   					   HttpServletRequest request,
			   					   HttpServletResponse response )
	  throws Exception {	
		
		System.out.println( "<ChemicalDrugPopulateAction dropdown> ... " );
		
		//blank out the FORMDATA Constant field
		ChemicalDrugForm chemicalDrugForm = ( ChemicalDrugForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, chemicalDrugForm );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		System.out.println( "<ChemicalDrugPopulateAction> exiting... " );
		
		return mapping.findForward( "submitChemicalDrug" );
	}
	
	/**
	 * Populate all drowpdowns for this type of form 
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown( HttpServletRequest request,
						  HttpServletResponse response )
	  throws Exception {
		
		System.out.println( "<ChemicalDrugPopulateAction dropdown> Entering... " );
	
		//Prepopulate all dropdown fields, set the global Constants to the following
		NewDropdownUtil drop = new NewDropdownUtil();
					
		drop.populateDropdown( request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, "" );			 		
		drop.populateDropdown( request, Constants.Dropdowns.AGEUNITSDROP, "" );
		drop.populateDropdown( request, Constants.Dropdowns.CHEMICALDRUGDROP, "" );
		drop.populateDropdown( request, Constants.Dropdowns.DOSAGEUNITSDROP, "" );
		drop.populateDropdown( request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP, "" );
	}

}
