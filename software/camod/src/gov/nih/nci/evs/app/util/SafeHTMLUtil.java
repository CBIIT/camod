/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id:$
 *
 * $Log:$
 */

package gov.nih.nci.evs.app.util;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.util.Translate; 

public class SafeHTMLUtil {
	
    public static String clean(String s)    {
        String clean = Translate.decode(s).replace("<", "").replace(">", "");
        clean = StringUtils.replace(clean, "!", "");
        clean = StringUtils.replace(clean, "@", ""); 
        clean = StringUtils.replace(clean, "#", "");
        clean = StringUtils.replace(clean, "$", "");        
        clean = StringUtils.replace(clean, "%", ""); 
        clean = StringUtils.replace(clean, "^", "");
        clean = StringUtils.replace(clean, "&", "");
        clean = StringUtils.replace(clean, "*", "");
        clean = StringUtils.replace(clean, "(", "");
        clean = StringUtils.replace(clean, ")", "");        
        clean = StringUtils.replace(clean, "-", "");
        clean = StringUtils.replace(clean, "_", "");
        clean = StringUtils.replace(clean, "=", "");
        clean = StringUtils.replace(clean, "\"", "");
        clean = StringUtils.replace(clean, ";", "");
        clean = StringUtils.replace(clean, "'", "");        
        clean = StringUtils.replace(clean, "<", "");
        clean = StringUtils.replace(clean, ">", "");
        clean = StringUtils.replace(clean, "/", "");   
        clean = StringUtils.replace(clean, "\\", "");
        clean = StringUtils.replace(clean, "CR", "");
        clean = StringUtils.replace(clean, "LF", "");
       
        if(clean.length()==0){
                clean = "empty";
        }
        return clean;       
    }

}
