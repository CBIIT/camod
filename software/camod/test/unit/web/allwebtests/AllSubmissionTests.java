/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package unit.web.allwebtests;

import unit.web.submission.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * @author pandyas
 */
public class AllSubmissionTests extends TestCase {

	public AllSubmissionTests(String arg0) {
		super(arg0);
	}
	
    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        // Submission tests - two test still do not work (remove comment when fixed)
        suite.addTest(EditModelCharacteristicsTest.suite());
        suite.addTest(SubmitEditDeleteCITest.suite());
        suite.addTest(SubmitEditDeleteCellLinesTest.suite());
        suite.addTest(SubmitEditDeleteHistopathologyTest.suite());
        //suite.addTest(SubmitEditDeleteImageTest.suite());
        suite.addTest(SubmitEditDeleteModelAvailabilityTest.suite());
        suite.addTest(SubmitEditDeletePublicationTest.suite());
        suite.addTest(SubmitEditDeleteTherapyTest.suite());
        suite.addTest(SubmitEditDeleteTransIntTest.suite());
        //suite.addTest(SubmitEditDeleteTransplantationTest.suite());        
        suite.addTest(SubmitEditDeleteGDTest.suite());     
        suite.addTest(SubmitPageTest.suite());

        return suite;
    }

    public static void main(String args[])
    {
        TestRunner.run(suite());
    }	

}
