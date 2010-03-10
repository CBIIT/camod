package unit.web.allwebtests;

import unit.web.search.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author pandyas
 */
public class AllSearchTests extends TestCase {
	
	public AllSearchTests(String arg0) {
		super(arg0);
	}	

    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        // Search tests - two test still do not work (remove comment when fixed)
        //suite.addTest(SearchPopulateCellLinesTest.suite());
        suite.addTest(SearchPopulateCITest.suite());
        suite.addTest(SearchPopulateGeneticDescriptionTest.suite());
        suite.addTest(SearchPopulateHistopathologyTest.suite());
        suite.addTest(SearchPopulateModelCharacteristicsTest.suite());        
        suite.addTest(SearchPopulatePublicationTest.suite());
        suite.addTest(SearchPopulateTherapyTest.suite());        
        suite.addTest(SearchPopulateTransIntTest.suite());
        //suite.addTest(SearchPopulateTransplantTest.suite());

        return suite;
    }	
	
}
