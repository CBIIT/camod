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
 * $Id: SearchPopulateTransplantTest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.2  2008/10/01 23:54:11  schroedn
 * Junit test fixes for caMOD 2.5 - revision 1
 *
 * Revision 1.1  2008/01/16 18:28:51  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.3  2007/11/01 13:53:51  pandyas
 * Fixed #8290     Rename graft object into transplant object
 *
 * Revision 1.2  2007/08/08 16:03:17  pandyas
 * Removed reference to transplant - as per VCDE changes
 *
 * Revision 1.1  2007/07/31 12:00:41  pandyas
 * VCDE silver level  changes
 * Modified all names used for a new attribute
 *
 * Revision 1.5  2006/10/11 15:47:55  pandyas
 * changes while testing 2.1.1
 *
 * Revision 1.4  2006/04/27 15:33:43  pandyas
 * uncommented deleteModel statement
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
 * Revision 1.5  2006/01/03 20:56:58  pandyas
 * added TODO note
 *
 * Revision 1.4  2005/12/21 22:04:14  pandyas
 * commented out debug line
 *
 * Revision 1.3  2005/12/21 21:35:23  pandyas
 * Added test for "Other" dropdown options
 *
 * Revision 1.2  2005/12/21 18:00:31  pandyas
 * Added test for "Other" dropdown options
 *
 * Revision 1.1  2005/12/12 19:04:21  pandyas
 * first version of JUnit test case
 *
 * 
 */
package unit.web.search;

import gov.nih.nci.camod.webapp.form.TransplantationForm;
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

public class SearchPopulateTransplantationTest extends BaseModelNeededTest
{

    public SearchPopulateTransplantationTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
    	
		try {
			
			setupJNDIdatasource();
			
		} catch (NamingException ex) {
            System.out.println("NamingException in datasouuce binding: " + SearchPopulateTransplantationTest.class.getName());
        }    	

        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theUsername = theBundle.getString("username");
        String thePassword = theBundle.getString("password");

        loginToApplication(theUsername, thePassword);
        createModel();
    }

    protected void tearDown() throws Exception
    {
        deleteModel();
        logoutOfApplication();
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite(SearchPopulateTransplantationTest.class);
        return suite;
    }

    public void testPopulateTransplantWithOthers() throws Exception
    {

        navigateToModelForEditing(myModelName);

        // Adding a Transplantation
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                                                                                  "Enter Transplantation");

        assertNotNull("Unable to find link to enter a Transplantation", theLink);
        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("- if transplantation type is not listed,");
        
        // Fill in the values and hit submit; Should fail the first time and the
        // populate will fill in the ethnicity strain values        
        WebForm theWebForm = theCurrentPage.getFormWithName("transplantationForm");
        
        theWebForm.setParameter("donorScientificName", "Mus musculus");
        theCurrentPage = theWebForm.submit();
        assertCurrentPageContains("- if transplantation type is not listed,");
        // Set the ethnicity strain and submit again
        theWebForm = theCurrentPage.getFormWithName("transplantationForm");
        //theWebForm.setParameter("donorEthinicityStrain", "129");
        theCurrentPage = theWebForm.submit();
        
        TransplantationForm theForm = new TransplantationForm();
       // theForm.setTransplantationName("TESTTransplantation");
        theForm.setOrgan("Heart");
        theForm.setOrganTissueName("Heart");
        theForm.setOrganTissueCode("C22498");
        theForm.setAtccNumber("2");
        theForm.setCellAmount("10");
        theForm.setGrowthPeriod("20");        
        //theForm.setDonorEthinicityStrain("129");
        
        
        List<String> theParamsToIgnore = new ArrayList<String>();
        //TODO - remove disabled=true but keep disabled until geneticManipulation is entered
        theParamsToIgnore.add("modificationDescription");
        theParamsToIgnore.add("donorScientificName");
        theParamsToIgnore.add("donorEthinicityStrain");

        // Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("organTissueCode");
        theParamsToSkip.add("organTissueName");
        //Find a way to test these fields
        theParamsToSkip.add("donorScientificName");
        theParamsToSkip.add("donorEthinicityStrain");        

        TestUtil.setRandomValues(theForm, theWebForm, true, theParamsToIgnore);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        //for debugging validation failures
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added a Transplantation to this model!");
        
        // Verify that populate method returns complete and correct data
        navigateToModelForEditing(myModelName);

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "TESTTransplantation");
        assertNotNull("Unable to find link to verify Transplantation", theLink);
        theCurrentPage = theLink.click();
        assertCurrentPageContains("if transplantation type is not listed");
        theWebForm = theCurrentPage.getFormWithName("transplantationForm");

        //Add parameters found behind but not populate screen
        theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("aTransplantationID");
        theParamsToSkip.add("submitAction");
        theParamsToSkip.add("otherHostEthinicityStrain");
        theParamsToSkip.add("modificationDescription");

        verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
    }

    public void testSearchForTransplantation() throws Exception
    {

        navigateToModelForEditing(myModelName);

        // Adding a Transplantation
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                                                                                  "Enter Transplantation");
        assertNotNull("Unable to find link to enter a Transplantation", theLink);
        WebResponse theCurrentPage = theLink.click();
        
        assertCurrentPageContains("if transplantation type is not listed,");
        WebForm theForm = theCurrentPage.getFormWithName("transplantationForm");
        
        // select from species list, then get strain list without violating validation
        theForm.setParameter("name", "Test Transplantation");
        theForm.setParameter("donorScientificName", "Mus musculus");
        theForm.setParameter("sourceType", "Cell Line");
        theForm.setParameter("organ", "Heart");
        theForm.setParameter("organTissueName", "Heart");
        theForm.setParameter("organTissueCode", "C22498");
        theForm.setParameter("atccNumber", "2");
        theForm.setParameter("cellAmount", "10");        
        // submit page to get the ethnicity strain list
        theCurrentPage = theForm.submit();
        
        // Set the ethnicity strain and submit again
        theForm = theCurrentPage.getFormWithName("transplantationForm");
        theForm.setParameter("donorEthinicityStrain", "129");
        //theCurrentPage = theForm.submit();        
        
        System.out.println( "Current URL in testSearchForTransplantation: " + theCurrentPage.getURL() );
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        List<String> theParamsToIgnore = new ArrayList<String>();
        //TODO - remove disabled=true but keep disabled until geneticManipulation is entered
        theParamsToIgnore.add("modificationDescription");

        // Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("organTissueCode");
        theParamsToSkip.add("organTissueName");
        theParamsToSkip.add("donorEthinicityStrain");

        TestUtil.setRandomValues(theForm, theForm, false, theParamsToIgnore);
        TestUtil.setValuesOnForm(theForm, theForm);

        theCurrentPage = theForm.submit();
        //for debugging validation failures
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added a Transplantation to this model!");

        TestUtil.moveModelToEditedApproved(myModelName);

        navigateToSpecificSearchPage(myModelName, "Transplantation");

        verifyValuesOnPage(theForm, theParamsToSkip);
    }

    public void testSearchForTransplantationWithOthers() throws Exception
    {

        navigateToModelForEditing(myModelName);

        // Adding a Transplantation
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                                                                                  "Enter Transplantation");
        assertNotNull("Unable to find link to enter a Transplantation", theLink);
        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("if transplantation type is not listed");
        WebForm theWebForm = theCurrentPage.getFormWithName("transplantationForm");

        TransplantationForm theForm = new TransplantationForm();
        theWebForm.setParameter("organ", "Heart");
        
        theForm.setOrgan("Heart");
        theForm.setOrganTissueName("Heart");
        theForm.setOrganTissueCode("C22498");
        theForm.setAtccNumber("2");
        theForm.setCellAmount("10");
        theForm.setDonorScientificName("Mus musculus");
        // submit page to get the ethnicity strain list
        theCurrentPage = theWebForm.submit();
        
        // Set the ethnicity strain and submit again
        theWebForm = theCurrentPage.getFormWithName("transplantationForm");
        theWebForm.setParameter("donorEthinicityStrain", "129");
        theCurrentPage = theWebForm.submit();          

        List<String> theParamsToIgnore = new ArrayList<String>();
        //textarea fails - investigate if needed
        theParamsToIgnore.add("modificationDescription");

        // Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("organTissueCode");
        theParamsToSkip.add("organTissueName");
        //not set in original model so must be skipped
        theParamsToSkip.add("otherHostEthinicityStrain");

        TestUtil.setRandomValues(theForm, theWebForm, true, theParamsToIgnore);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added a Transplantation to this model!");

        TestUtil.moveModelToEditedApproved(myModelName);

        navigateToSpecificSearchPage(myModelName, "Transplantation");

        verifyValuesOnPage(theWebForm, theParamsToSkip);
    }

}
