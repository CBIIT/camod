/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * AnimalDistributorManagerSingletonTest.java
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
public class AnimalDistributorManagerSingletonTest extends TestCase {
  
  public AnimalDistributorManagerSingletonTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AnimalDistributorManagerSingletonTest.class);
    
    return suite;
  }

  /**
   * Test of instance method, of class gov.nih.nci.camod.service.impl.AnimalDistributorManagerSingleton.
   */
  public void testInstance() {
    System.out.println("testInstance");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
