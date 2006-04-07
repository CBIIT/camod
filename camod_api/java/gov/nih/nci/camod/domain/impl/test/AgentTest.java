
package gov.nih.nci.camod.domain.impl.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import gov.nih.nci.common.util.HibernateUtil;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
public class AgentTest extends TestCase{

    private static Logger _logger = Logger.getLogger(AgentTest.class);
    
	public static final Class CLASS = AgentTest.class;

    public AgentTest(String testName) {
        super(testName);
    }

	public static void main(String[] args) {
		try {
			junit.textui.TestRunner.main(new String[] { CLASS.getName() });
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static junit.framework.Test suite() {
		junit.framework.TestSuite suite = new junit.framework.TestSuite();

		//suite.addTest(new AgentTest("testPopulate"));
		suite.addTest(new AgentTest("testRetrieve"));
		
		return suite;
	} 

	public void testPopulate(){
		try{
		        gov.nih.nci.camod.domain.ws.Agent agent = new gov.nih.nci.camod.domain.ws.Agent() ;
			save(agent);
		}catch(Exception ex){
		    ex.printStackTrace();
		    fail("Error retrieving Agent. Got " + ex.getClass().getName() + ": " + ex.getMessage());
		
		}
	}
	
	public void testRetrieve(){
		try{
			Session sess = gov.nih.nci.common.util.HibernateUtil.currentSession();
			Transaction tx = sess.beginTransaction();
			
			sess.createCriteria(gov.nih.nci.camod.domain.ws.Agent.class).list();
			tx.commit();
			gov.nih.nci.common.util.HibernateUtil.closeSession();
		}catch(Exception ex){
		    ex.printStackTrace();
		    fail("Error retrieving Agent. Got " + ex.getClass().getName() + ": " + ex.getMessage());
		}
	}

    public void save(Object obj) {

        Transaction tx = null;
        Session sess = null;
        boolean success = true;


	((gov.nih.nci.camod.domain.ws.Agent)obj).setId(new java.lang.Long("1"));

        try {
            sess = gov.nih.nci.common.util.HibernateUtil.currentSession();
            tx = sess.beginTransaction();
            sess.saveOrUpdate(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
            try {
                tx.rollback();
            } catch (Exception ex2) {
                throw new RuntimeException("Error rolling back.", ex2);
            }
        } finally {
            try {
                if (success && tx != null) {
                    tx.commit();
                }
                gov.nih.nci.common.util.HibernateUtil.closeSession();
            } catch (Exception ex) {
                throw new RuntimeException("Error cleaning up", ex);
            }
        }
    }
}
   



