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
 * $Id: SubmitEditDeletePublicationTest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.1  2005/12/27 15:04:09  georgeda
 * Test cleanup
 *
 * Revision 1.1  2005/12/12 16:23:59  pandyas
 * JUnit test case for Publication
 *
 * 
 */
package unit.web.submission;

import java.util.ResourceBundle;
import junit.framework.Test;
import junit.framework.TestSuite;
import unit.web.base.BaseModelNeededTest;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

public class SubmitEditDeletePublicationTest extends BaseModelNeededTest {

	public SubmitEditDeletePublicationTest(String arg0) {
		super(arg0);
	}

    protected void setUp() throws Exception {
        
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
        TestSuite suite = new TestSuite(SubmitEditDeletePublicationTest.class);
        return suite;
    }
	
    public void testPublication() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Publication link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Publications");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("For publications with a PubMed record");
        WebForm theForm = theCurrentPage.getFormWithName("publicationForm");
        theForm.setParameter("authors", "Pandya S");
        theForm.setParameter("name", "Published");
        theForm.setParameter("firstTimeReported", "yes");
        theForm.setParameter("pmid", "16323327");
        theForm.setParameter("title", "Dying with dignity: a round-table.");        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Publication to this model!");
        
        /* Find Publication link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Pandya");        
        assertNotNull("Unable to find link to edit the Publication", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("For publications with a PubMed record");
        theForm = theCurrentPage.getFormWithName("publicationForm");
        theForm.setParameter("authors", "Pandya S");
        theForm.setParameter("name", "Published");
        theForm.setParameter("firstTimeReported", "yes");
        theForm.setParameter("pmid", "16323327");
        theForm.setParameter("title", "Dying with dignity: a round-table.");  
        theForm.setParameter("year", "1999");         
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Publication.");      
        
        /* Find Publication link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Pandya");
        assertNotNull("Unable to find link to delete the Publication", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("For publications with a PubMed record");
        theForm = theCurrentPage.getFormWithName("publicationForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Publication.");         
    }   	

}
