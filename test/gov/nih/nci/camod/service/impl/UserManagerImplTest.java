/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * UserManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:38 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.UserManager;
import gov.nih.nci.camod.util.LDAPUtil;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author piparom
 */
public class UserManagerImplTest extends TestCase {
  
  public UserManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(UserManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getRolesForUser method, of class gov.nih.nci.camod.service.impl.UserManagerImpl.
   */
  public void testGetRolesForUser() {
    System.out.println("testGetRolesForUser");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getUsersForRole method, of class gov.nih.nci.camod.service.impl.UserManagerImpl.
   */
  public void testGetUsersForRole() {
    System.out.println("testGetUsersForRole");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getEmailForUser method, of class gov.nih.nci.camod.service.impl.UserManagerImpl.
   */
  public void testGetEmailForUser() {
    System.out.println("testGetEmailForUser");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of updateCurrentUserRoles method, of class gov.nih.nci.camod.service.impl.UserManagerImpl.
   */
  public void testUpdateCurrentUserRoles() {
    System.out.println("testUpdateCurrentUserRoles");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getContactInformationForUser method, of class gov.nih.nci.camod.service.impl.UserManagerImpl.
   */
  public void testGetContactInformationForUser() {
    System.out.println("testGetContactInformationForUser");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getEmailForCoordinator method, of class gov.nih.nci.camod.service.impl.UserManagerImpl.
   */
  public void testGetEmailForCoordinator() {
    System.out.println("testGetEmailForCoordinator");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of login method, of class gov.nih.nci.camod.service.impl.UserManagerImpl.
   */
  public void testLogin() {
    System.out.println("testLogin");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
