/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * StrainTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 1:19 PM
 */

package gov.nih.nci.camod.domain;

import junit.framework.*;
import java.io.Serializable;

/**
 *
 * @author piparom
 */
public class StrainTest extends TestCase {
  
  public StrainTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(StrainTest.class);
    
    return suite;
  }

  /**
   * Test of getName method, of class gov.nih.nci.camod.domain.Strain.
   */
  public void testGetName() {
    System.out.println("testGetName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of setName method, of class gov.nih.nci.camod.domain.Strain.
   */
  public void testSetName() {
    System.out.println("testSetName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
