package web.submission;

import java.io.File;
import java.util.ResourceBundle;

import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;

import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SubmitEditDeleteImage extends BaseModelNeededTest {

    public SubmitEditDeleteImage(String testName) {
        super(testName);
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
        TestSuite suite = new TestSuite(SubmitEditDeleteImage.class);
        return suite;
    }

    public void testImage() throws Exception {
        navigateToModelForEditing(myModelName);
        ResourceBundle theBundle = ResourceBundle.getBundle("test");
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Images");        
        assertNotNull("Unable to find link to enter a Image", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(Image of type .jpg, .jpeg, .gif, .sid or .png)");
        WebForm theForm = theCurrentPage.getFormWithName("imageForm");
        theForm.setParameter("fileLocation", new File(theBundle.getString("imagedirectory")) );
        theForm.setParameter("title", "test image");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Image to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "test image");        
        assertNotNull("Unable to find link to edit a Image", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(Image of type .jpg, .jpeg, .gif, .sid or .png)");
        theForm = theCurrentPage.getFormWithName("imageForm");
       // theForm.setParameter("fileLocation", new File(theBundle.getString("imagedirectory")) );
        theForm.setParameter("title", "test image1");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Image.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "test image1");
        assertNotNull("Unable to find link to delete a Image", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(Image of type .jpg, .jpeg, .gif, .sid or .png)");
        theForm = theCurrentPage.getFormWithName("imageForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();         
        //TODO: Add check to see if Image is successfully removed from caIMAGE
        assertCurrentPageContains("You have successfully deleted an Image.");             
    }
}