/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * DuplicateUtilTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:13 PM
 */

package unit.gov.nih.nci.camod.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author piparom
 */
public class DuplicateUtilTest extends TestCase {
  
  public DuplicateUtilTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(DuplicateUtilTest.class);
    
    return suite;
  }

  /**
   * Test of duplicateBean method, of class gov.nih.nci.camod.util.DuplicateUtil.
   */
  public void testDuplicateBean() {
    System.out.println("testDuplicateBean");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
