/**
 * 
 * $Id: SubmitGDTest.java,v 1.4 2005-12-27 19:10:57 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/12/14 20:15:14  pandyas
 * Added JavaDocs
 *
 * 
 */
package web.submission;

import java.util.ResourceBundle;

import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;
import web.util.TestUtil;

import com.meterware.httpunit.*;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SubmitGDTest extends BaseModelNeededTest {

    public SubmitGDTest(String testName) {
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
        TestSuite suite = new TestSuite(SubmitGDTest.class);
        return suite;
    }

    public void testAddEngineeredTransgene() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Enter Engineered Transgene");        
        assertNotNull("Unable to find link to enter a Engineered Transgene", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("Poly A Signal:");
        WebForm theForm = theCurrentPage.getFormWithName("engineeredTransgeneForm");
        theForm.setParameter("locationOfIntegration", "Random");
        theForm.setParameter("name", "Testing123");
        theForm.setParameter("scientificName", "Mus musculus");
        theForm.setParameter("transcriptional1_name", "Testing123");
        theForm.setParameter("transcriptional1_species", "Mus musculus");
        theForm.setParameter("transgeneId", "");        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Engineered Transgene to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Testing123");        
        assertNotNull("Unable to find link to edit a Engineered Transgene", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Poly A Signal:");
        theForm = theCurrentPage.getFormWithName("engineeredTransgeneForm");
        theForm.setParameter("locationOfIntegration", "Random");
        theForm.setParameter("name", "Testing123");
        theForm.setParameter("scientificName", "Mus musculus");
        theForm.setParameter("transcriptional1_name", "Testing1234");
        theForm.setParameter("transcriptional1_species", "Mus musculus");    
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Engineered Transgene.");
        
        //Adding Assoc Expression
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Enter Assoc Expression");        
        assertNotNull("Unable to find link to enter a Assoc Expression", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Organ / Tissue:");
        theForm = theCurrentPage.getFormWithName("associatedExpressionForm");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22509" );
        theForm.getScriptableObject().setParameterValue( "organTissueName", "Esophagus" );
        theForm.getScriptableObject().setParameterValue( "organ", "Esophagus_MMHCC" );        
        theForm.setParameter("expressionLevel", "expressed");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Associated Expression Transgene to this model!");
        
        //Editing Assoc Expression
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Esophagus");        
        assertNotNull("Unable to find link to enter a Assoc Expression", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Organ / Tissue:");
        theForm = theCurrentPage.getFormWithName("associatedExpressionForm");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22509" );
        theForm.getScriptableObject().setParameterValue( "organTissueName", "Esophagus" );
        theForm.getScriptableObject().setParameterValue( "organ", "Esophagus_MMHCC" );               
        theForm.setParameter("expressionLevel", "highly expressed");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Associated Expression.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Testing123");
        assertNotNull("Unable to find link to delete a Engineered Transgene", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Poly A Signal:");
        theForm = theCurrentPage.getFormWithName("engineeredTransgeneForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted an Engineered Transgene.");                
    }      

    public void testAddGenomicSegment() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Enter Genomic Segment");        
        assertNotNull("Unable to find link to enter a Genomic Segment", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("Segment Type:");
        WebForm theForm = theCurrentPage.getFormWithName("genomicSegmentForm");
        theForm.setParameter("locationOfIntegration", "Random");
        theForm.setParameter("segmentName", "BAC");
        theForm.setParameter("cloneDesignator", "123");
        theForm.setParameter("segmentId", "");  
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Genomic Segment to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "123");        
        assertNotNull("Unable to find link to edit a Genomic Segment", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Segment Type:");
        theForm = theCurrentPage.getFormWithName("genomicSegmentForm");
        theForm.setParameter("locationOfIntegration", "Random");
        theForm.setParameter("segmentName", "Cosmid");
        theForm.setParameter("cloneDesignator", "123");      
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Genomic Segment.");
        
        //Adding Assoc Expression
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Enter Assoc Expression");        
        assertNotNull("Unable to find link to enter a Assoc Expression", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Expression Level:");
        theForm = theCurrentPage.getFormWithName("associatedExpressionForm");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22509" );
        theForm.getScriptableObject().setParameterValue( "organTissueName", "Esophagus" );
        theForm.getScriptableObject().setParameterValue( "organ", "Esophagus_MMHCC" );
        theForm.setParameter("expressionLevel", "expressed");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Associated Expression Transgene to this model!");
        
        //Editing Assoc Expression
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Esophagus");        
        assertNotNull("Unable to find link to enter a Assoc Expression", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Expression Level:");
        theForm = theCurrentPage.getFormWithName("associatedExpressionForm");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22509" );
        theForm.getScriptableObject().setParameterValue( "organTissueName", "Esophagus" );
        theForm.getScriptableObject().setParameterValue( "organ", "Esophagus_MMHCC" );
        theForm.setParameter("expressionLevel", "highly expressed");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Associated Expression.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "123");
        assertNotNull("Unable to find link to delete a Genomic Segment", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Segment Type:");
        theForm = theCurrentPage.getFormWithName("genomicSegmentForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Genomic Segment.");                
    }							 
    
    public void testAddTargetedModification() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
        		"Enter Targeted Modification");        
        assertNotNull("Unable to find link to enter a Targeted Modification", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("Targeted Gene/Locus:");
        WebForm theForm = theCurrentPage.getFormWithName("targetedModificationForm");
        theForm.setParameter("name", "test targeted");
        theForm.setParameter("modificationType", "Misense");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Targeted Modification to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"test targeted");        
        assertNotNull("Unable to find link to edit a Targeted Modification", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Targeted Gene/Locus:");
        theForm = theCurrentPage.getFormWithName("targetedModificationForm");
        theForm.setParameter("name", "test targeted");
        theForm.setParameter("modificationType", "Nonsense");    
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Targeted Modification.");
        
        //Adding Assoc Expression
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Enter Assoc Expression");        
        assertNotNull("Unable to find link to enter a Assoc Expression", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Expression Level:");
        theForm = theCurrentPage.getFormWithName("associatedExpressionForm");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22509" );
        theForm.getScriptableObject().setParameterValue( "organTissueName", "Esophagus" );
        theForm.getScriptableObject().setParameterValue( "organ", "Esophagus_MMHCC" );
        theForm.setParameter("expressionLevel", "expressed");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Associated Expression Transgene to this model!");
        
        //Editing Assoc Expression
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Esophagus");        
        assertNotNull("Unable to find link to enter a Assoc Expression", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Expression Level:");
        theForm = theCurrentPage.getFormWithName("associatedExpressionForm");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22509" );
        theForm.getScriptableObject().setParameterValue( "organTissueName", "Esophagus" );
        theForm.getScriptableObject().setParameterValue( "organ", "Esophagus_MMHCC" );
        theForm.setParameter("expressionLevel", "highly expressed");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Associated Expression.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"test targeted");
        assertNotNull("Unable to find link to delete a Targeted Modification", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Targeted Gene/Locus:");
        theForm = theCurrentPage.getFormWithName("targetedModificationForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Targeted Modification.");                
    }
    
    public void testAddInducedMutation() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
        		"Enter Induced Mutation");        
        assertNotNull("Unable to find link to enter a Induced Mutation", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("Inducing Agent Category:");
        WebForm theForm = theCurrentPage.getFormWithName("inducedMutationForm");
        theForm.setParameter("name", "test agent");
        theForm.setParameter("type", "Radiation");
        theCurrentPage = theForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully added an Induced Mutation to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"test agent");        
        assertNotNull("Unable to find link to edit a Induced Mutation", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Inducing Agent Category:");
        theForm = theCurrentPage.getFormWithName("inducedMutationForm");
        theForm.setParameter("name", "test agent");
        theForm.setParameter("type", "Chemical / Drug");        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Induced Mutation.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"test agent");
        assertNotNull("Unable to find link to delete a Induced Mutation", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Inducing Agent Category:");
        theForm = theCurrentPage.getFormWithName("inducedMutationForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted an Induced Mutation.");                
    }
    
    public void testAddSpontaneousMutation() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Enter Spontaneous Mutation");        
        assertNotNull("Unable to find link to enter a Spontaneous Mutation", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("Gene Name:");
        WebForm theForm = theCurrentPage.getFormWithName("spontaneousMutationForm");
        theForm.setParameter("name", "test mutation");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Spontaneous Mutation to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"test mutation");        
        assertNotNull("Unable to find link to edit a Spontaneous Mutation", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Gene Name:");
        theForm = theCurrentPage.getFormWithName("spontaneousMutationForm");
        theForm.setParameter("name", "test mutation");
        theForm.setParameter("comments", "no comment");        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Spontaneous Mutation.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"test mutation");
        assertNotNull("Unable to find link to delete a Spontaneous Mutation", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Gene Name:");
        theForm = theCurrentPage.getFormWithName("spontaneousMutationForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Spontaneous Mutation.");                
    }
}