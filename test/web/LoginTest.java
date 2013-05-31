/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package web;

import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseHttpTest;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class LoginTest extends BaseHttpTest {

    public LoginTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
        logoutOfApplication();
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(LoginTest.class);
        return suite;
    }

    public void testLogin() throws Exception {
        loginToApplication("georgeda", "Dg1894!");
        assertCurrentPageContains("Submit and Edit Models");
        logoutOfApplication();
    }

    public void testBadPassword() throws Exception {
        loginToApplication("georgeda", "badpassword");
        assertCurrentPageContains("Invalid username and/or password"); 
    }
}
