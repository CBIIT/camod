/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * @author pandyas
 * 
 * $Id: SearchPopulateTherapyTest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.3  2006/04/27 15:08:43  pandyas
 * Modified while testing caMod 2.1
 *
 * Revision 1.2  2006/04/17 19:37:34  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.1  2006/01/06 16:08:22  pandyas
 * Added testing for populate methods
 *
 * Revision 1.3  2005/12/29 18:43:48  pandyas
 * Fixed defect# 286: Link to Publications not showing up for just a therapy pub
 *
 * Revision 1.2  2005/12/16 17:43:13  pandyas
 * Added a therapy publication to script - there are two issues that need resolved - there is additional source code to upload for this to run
 *
 * Revision 1.1  2005/12/13 20:39:14  pandyas
 * JUnit test case for Search Therapy - depends on other changes in main tree that will NOT be uploaded until we go to production
 *
 * 
 */

package unit.web.search;

import gov.nih.nci.camod.webapp.form.PublicationForm;
import gov.nih.nci.camod.webapp.form.TherapyForm;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import unit.web.base.BaseModelNeededTest;
import unit.web.util.TestUtil;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SearchPopulateTherapyTest extends BaseModelNeededTest {

	public SearchPopulateTherapyTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		
		try {
			
			setupJNDIdatasource();
			
		} catch (NamingException ex) {
            System.out.println("NamingException in datasouuce binding: " + SearchPopulateTherapyTest.class.getName());
        }
		
        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theUsername = theBundle.getString("username");
        String thePassword = theBundle.getString("password");
        
        loginToApplication(theUsername, thePassword);
        createModel();		
	}

	protected void tearDown() throws Exception {
        deleteModel();
        logoutOfApplication();
	}
	
    public static Test suite() {
        TestSuite suite = new TestSuite(SearchPopulateTherapyTest.class);
        return suite;
    }

	public void testPopulateTherapyWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding a Therapy
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Therapy");
		assertNotNull("Unable to find link to enter a Therapy", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Chemical Class:");
		WebForm theWebForm = theCurrentPage.getFormWithName("therapyForm");

		TherapyForm theForm = new TherapyForm();
		theForm.setName("TESTTHERAPY");
        theForm.setNscNumber("33832"); 
        theForm.setCasNumber("50-81-7");
        
		List<String> theParamsToIgnore = new ArrayList<String>();
		theParamsToIgnore.add("chemicalClasses");
		theParamsToIgnore.add("selectedChemicalClasses");
		theParamsToIgnore.add("processes");
		theParamsToIgnore.add("selectedProcesses");
		theParamsToIgnore.add("targets");
		theParamsToIgnore.add("selectedTargets");
		theParamsToIgnore.add("administrativeRoute");
        theParamsToIgnore.add("jaxJNumber");

	
		/* Add parameters found on submit screen but not displayed on search screen  */
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("selectedChemicalClasses");
		theParamsToSkip.add("selectedProcesses");
		theParamsToSkip.add("selectedTargets");
        theParamsToSkip.add("comments");
        theParamsToSkip.add("jaxJNumber");
		
		
		TestUtil.setRandomValues(theForm, theWebForm, true, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		
		assertCurrentPageContains("You have successfully added a Therapy to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"TESTTHERAPY");
		assertNotNull("Unable to find link to verify a Therapy", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("Chemical Class:");
		theWebForm = theCurrentPage.getFormWithName("therapyForm");
		
		//Add parameters found behind but not populate screen
		theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");
		theParamsToSkip.add("processes");
		theParamsToSkip.add("selectedProcesses");
		theParamsToSkip.add("targets");		
		theParamsToSkip.add("selectedTargets");
		theParamsToSkip.add("chemicalClasses");			
		theParamsToSkip.add("selectedChemicalClasses");	
		theParamsToSkip.add("aTherapyID");
		//TODO:  Fix these values so they are tested
		theParamsToSkip.add("otherAdministrativeRoute");		
		theParamsToSkip.add("administrativeRoute");
        theParamsToSkip.add("dosageUnit"); 
        theParamsToSkip.add("jaxJNumber");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
		
		/******* Adding Publication to Therapy *****************************/
		navigateToModelForEditing(myModelName);

		// Adding a Publication
		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(
				WebLink.MATCH_TEXT, "Enter Publication for Therapy");
		assertNotNull("Unable to find link to enter a Publication for Therapy", theLink);
		theCurrentPage = theLink.click();		
		assertCurrentPageContains("For publications with a PubMed record");
		theWebForm = theCurrentPage.getFormWithName("publicationForm");
		// set explicitly so validation works
		theWebForm.setParameter("firstTimeReported", "yes");		

		PublicationForm thePubForm = new PublicationForm();
		thePubForm.setAuthors("AUTHORSABCDEFGH");
		thePubForm.setFirstTimeReported("yes");
		thePubForm.setPmid("16323327");
		thePubForm.setYear("1999");
		thePubForm.setStartPage("1111");
		thePubForm.setEndPage("9999");		
		thePubForm.setVolume("10");
		thePubForm.setTitle("title");
        thePubForm.setJaxJNumber("J:54533");

		//TODO: clean up the use of aCellID and ACellID, ATherapyID vs aTherapyID and APubID vs aPubID
		theParamsToIgnore = new ArrayList<String>();
		theParamsToIgnore.add("volume");
		theParamsToIgnore.add("title");		
		theParamsToIgnore.add("aCellID");
		theParamsToIgnore.add("ACellID");		
		theParamsToIgnore.add("APubID");
		theParamsToIgnore.add("ATherapyID");
		theParamsToIgnore.add("aTherapyID");
        theParamsToIgnore.add("jaxJNumber");   

		//Add parameters found on submit screen but not displayed on search
		theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("firstTimeReported");
		theParamsToSkip.add("aCellID");
		theParamsToSkip.add("ACellID");		
		theParamsToSkip.add("APubID");
		theParamsToSkip.add("volume");
		theParamsToSkip.add("title");
		theParamsToSkip.add("ATherapyID");
		theParamsToSkip.add("aTherapyID");
        theParamsToSkip.add("jaxJNumber");

		TestUtil.setRandomValues(thePubForm, theWebForm, false,	theParamsToIgnore);
		TestUtil.setValuesOnForm(thePubForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Publication to this model! ");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"AUTHORSABCDEFGH");
		assertNotNull("Unable to find link to verify therapy publication", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("For publications with a PubMed record");
		theWebForm = theCurrentPage.getFormWithName("publicationForm");
		
		//Add parameters found behind but not populate screen
		theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");		
		theParamsToSkip.add("APubID");
		theParamsToSkip.add("ATherapyID");		
		theParamsToSkip.add("ACellID");
        theParamsToSkip.add("jaxJNumber");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip); 		
	}      
    
	public void testSearchForTherapy() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding a Therapy
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Therapy");
		assertNotNull("Unable to find link to enter a Therapy", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Chemical Class:");
		WebForm theWebForm = theCurrentPage.getFormWithName("therapyForm");

		TherapyForm theForm = new TherapyForm();
        theForm.setNscNumber("33832"); 
        theForm.setCasNumber("50-81-7");
        
		List<String> theParamsToIgnore = new ArrayList<String>();
		theParamsToIgnore.add("chemicalClasses");
		theParamsToIgnore.add("selectedChemicalClasses");
		theParamsToIgnore.add("processes");
		theParamsToIgnore.add("selectedProcesses");
		theParamsToIgnore.add("targets");
		theParamsToIgnore.add("selectedTargets");
		theParamsToIgnore.add("administrativeRoute");
        theParamsToIgnore.add("dosageUnit");
	
		/* Add parameters found on submit screen but not displayed on search screen  */
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("selectedChemicalClasses");
		theParamsToSkip.add("selectedProcesses");
		theParamsToSkip.add("selectedTargets");
        theParamsToSkip.add("dosageUnit");
		
		
		TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		
		assertCurrentPageContains("You have successfully added a Therapy to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"THERAPEUTIC APPROACHES");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
		
		/******* Adding Publication to Therapy ***********/
		navigateToModelForEditing(myModelName);

		// Adding a Publication
		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(
				WebLink.MATCH_TEXT, "Enter Publication for Therapy");
		assertNotNull("Unable to find link to enter a Publication for Therapy", theLink);
		theCurrentPage = theLink.click();		
		assertCurrentPageContains("For publications with a PubMed record");
		theWebForm = theCurrentPage.getFormWithName("publicationForm");
		// set explicitly so validation works
		theWebForm.setParameter("firstTimeReported", "yes");		

		PublicationForm thePubForm = new PublicationForm();
		thePubForm.setFirstTimeReported("yes");
		thePubForm.setPmid("16323327");
		thePubForm.setYear("1999");
		thePubForm.setStartPage("1111");
		thePubForm.setEndPage("9999");		
		thePubForm.setVolume("10");
		thePubForm.setTitle("title");
        thePubForm.setJaxJNumber("J:54533");

		//TODO: clean up the use of aCellID and ACellID, ATherapyID vs aTherapyID and APubID vs aPubID
		theParamsToIgnore = new ArrayList<String>();
		theParamsToIgnore.add("volume");
		theParamsToIgnore.add("title");		
		theParamsToIgnore.add("aCellID");
		theParamsToIgnore.add("ACellID");		
		theParamsToIgnore.add("APubID");
		theParamsToIgnore.add("ATherapyID");
		theParamsToIgnore.add("aTherapyID");
        theParamsToIgnore.add("jaxJNumber");
        

		//Add parameters found on submit screen but not displayed on search
		theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("firstTimeReported");
		theParamsToSkip.add("aCellID");
		theParamsToSkip.add("ACellID");		
		theParamsToSkip.add("APubID");
		theParamsToSkip.add("volume");
		theParamsToSkip.add("title");
		theParamsToSkip.add("ATherapyID");
		theParamsToSkip.add("aTherapyID");
        theParamsToSkip.add("jaxJNumber");
	

		TestUtil.setRandomValues(thePubForm, theWebForm, false,	theParamsToIgnore);
		TestUtil.setValuesOnForm(thePubForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Publication to this model! ");

		TestUtil.moveModelToEditedApproved(myModelName);

		// check if publication is on cell line page
		navigateToSpecificSearchPage(myModelName, "THERAPEUTIC APPROACHES");
		verifyValuesOnPage(theWebForm, theParamsToSkip);
		
		/* check if publication is on publication search page
		* Fixed Defect #286 */ 
		navigateToSpecificSearchPage(myModelName, "PUBLICATIONS");
		verifyValuesOnPage(theWebForm, theParamsToSkip);
				
	}  

}
