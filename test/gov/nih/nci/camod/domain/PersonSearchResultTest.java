/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * PersonSearchResultTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 1:19 PM
 */

package gov.nih.nci.camod.domain;

import junit.framework.*;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.service.impl.UserManagerSingleton;
import java.util.List;

/**
 *
 * @author piparom
 */
public class PersonSearchResultTest extends TestCase {
  
  public PersonSearchResultTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(PersonSearchResultTest.class);
    
    return suite;
  }

  /**
   * Test of getId method, of class gov.nih.nci.camod.domain.PersonSearchResult.
   */
  public void testGetId() {
    System.out.println("testGetId");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getDisplayName method, of class gov.nih.nci.camod.domain.PersonSearchResult.
   */
  public void testGetDisplayName() {
    System.out.println("testGetDisplayName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getContactInfo method, of class gov.nih.nci.camod.domain.PersonSearchResult.
   */
  public void testGetContactInfo() {
    System.out.println("testGetContactInfo");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getRoles method, of class gov.nih.nci.camod.domain.PersonSearchResult.
   */
  public void testGetRoles() {
    System.out.println("testGetRoles");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
