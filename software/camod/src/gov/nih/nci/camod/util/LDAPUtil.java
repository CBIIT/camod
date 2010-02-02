/**
 * 
 * $Id: LDAPUtil.java,v 1.13 2008-08-14 17:14:26 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2007/12/17 18:03:53  pandyas
 * Removed * in searchFilter used for getting e-mail from LDAP
 * Apps Support ticket was submitted (31169 - incorrect e-mail associated with my caMOD account) stating:
 *
 * Cheryl Marks submitted a ticket to NCICB Application Support in which she requested that the e-mail address associated with her account in the "User Settings" screen in caMOD be corrected. She has attempted to correct it herself, but because the program queries the LDAP Server for the e-mail address, her corrections were not retained.
 *
 * Revision 1.11  2007/08/07 15:01:00  pandyas
 * replace log statements
 *
 * Revision 1.10  2007/04/17 17:32:15  pandyas
 * Added debug for e-mail from LDAP - testing bug for PM e-mail
 *
 * Revision 1.9  2006/09/18 16:31:10  georgeda
 * Externalized properties - back to v 1.6
 *
 * Revision 1.6  2006/08/17 18:00:02  pandyas
 * Defect# 410: Externalize properties files - Code changes to get properties
 *
 * Revision 1.5  2006/05/24 14:08:54  georgeda
 * Backed out static changes.
 *
 * Revision 1.3  2006/04/17 19:10:50  pandyas
 * Added $Id: LDAPUtil.java,v 1.13 2008-08-14 17:14:26 pandyas Exp $ and $log:$
 *
 * 
 */


package gov.nih.nci.camod.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LDAPUtil {
    static private final Log log = LogFactory.getLog(LDAPUtil.class);
	
    static public String getEmailAddressForUser(String inUsername) {
        String theSearchFilter = "(" + "cn" + "=" + inUsername + ")";

        String theEmailAddress = "";
        
        try {
    		// Get the e-mail resource
    		Properties camodProperties = new Properties();
    		String camodPropertiesFileName = null;

    		camodPropertiesFileName = System.getProperty("gov.nih.nci.camod.camodProperties");

    		
    		try {
			
    		FileInputStream in = new FileInputStream(camodPropertiesFileName);
    		camodProperties.load(in);
	
    		} 
    		catch (FileNotFoundException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		} catch (IOException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		}
            
    		camodProperties.getProperty("ldap.initial.context.factory");
            
            //String theInitialContextFactory = 
            Hashtable environment = new Hashtable();
            environment.clear();
            environment.put(Context.INITIAL_CONTEXT_FACTORY, camodProperties.getProperty("ldap.initial.context.factory"));
            environment.put(Context.PROVIDER_URL, camodProperties.getProperty("ldap.provider.url"));
            environment.put(Context.SECURITY_AUTHENTICATION, camodProperties.getProperty("ldap.security.authentication"));
            environment.put(Context.SECURITY_PROTOCOL, camodProperties.getProperty("ldap.security.protocol"));
            DirContext dirContext = new InitialDirContext(environment);

            SearchControls searchControls = new SearchControls();
            searchControls.setReturningAttributes(null);
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            NamingEnumeration searchEnum = dirContext.search(camodProperties.getProperty("ldap.context"), theSearchFilter, searchControls);
            dirContext.close();
            
            while (searchEnum.hasMore()) {
                
                SearchResult searchResult = (SearchResult) searchEnum.next();
              
                Attributes theAttributes = searchResult.getAttributes();

                Enumeration theEnum = theAttributes.getAll();

                while (theEnum.hasMoreElements()) {
                    Attribute theAttribute = (Attribute) theEnum.nextElement();

                    if (theAttribute.getID().equals("mail"))
                    {
                        theEmailAddress = theAttribute.get().toString();
                        log.info("theEmailAddress as mail from LDAP: " + theEmailAddress);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("User Name does not exist + " + inUsername);
        }
        
        return theEmailAddress;
    }
    
    public static void main(String[] args) {
        //System.out.println("Email address: " + LDAPUtil.getEmailAddressForUser("georgeda"));
    }
}
