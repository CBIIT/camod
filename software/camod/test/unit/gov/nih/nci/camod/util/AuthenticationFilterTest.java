/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * AuthenticationFilterTest.java
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
public class AuthenticationFilterTest extends TestCase {
  
  public AuthenticationFilterTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AuthenticationFilterTest.class);
    
    return suite;
  }

  /**
   * Test of init method, of class gov.nih.nci.camod.util.AuthenticationFilter.
   */
  public void testInit() {
    System.out.println("testInit");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of doFilter method, of class gov.nih.nci.camod.util.AuthenticationFilter.
   */
  public void testDoFilter() {
    System.out.println("testDoFilter");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of destroy method, of class gov.nih.nci.camod.util.AuthenticationFilter.
   */
  public void testDestroy() {
    System.out.println("testDestroy");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
