/*
 * ChemicalClassManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:35 PM
 */

package unit.gov.nih.nci.camod.service.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author piparom
 */
public class ChemicalClassManagerImplTest extends TestCase {
  
  public ChemicalClassManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(ChemicalClassManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getByName method, of class gov.nih.nci.camod.service.impl.ChemicalClassManagerImpl.
   */
  public void testGetByName() {
    System.out.println("testGetByName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
