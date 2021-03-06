/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: FtpUtil.java,v 1.4 2008-08-18 13:54:43 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2007/08/01 18:04:22  pandyas
 * minor format change in println
 *
 * Revision 1.2  2006/04/17 19:10:50  pandyas
 * Added $Id: FtpUtil.java,v 1.4 2008-08-18 13:54:43 pandyas Exp $ and $log:$
 *
 * 
 */


package gov.nih.nci.camod.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FtpUtil {

    /**
     * Upload a file to a FTP server. A FTP URL is generated with the following syntax:
     * <code>ftp://user:password@host:port/filePath;type=i</code>.
     * 
     * @param ftpServer FTP server address (incl. optional port ':portNumber').
     * @param user Optional user name to login.
     * @param pwd Optional password for <i>user</i>.
     * @param fileName Destination file name on FTP server (with optional preceeding relative path, e.g. "one/two/three.txt").
     * @param source Source file to upload.
     * @throws MalformedURLException, IOException on error.
     */
    public void upload(String ftpServer, String user, String pwd, String fileName, File source) throws MalformedURLException, IOException {
    	System.out.println("FtpUtil.upload method");
        if (ftpServer != null && fileName != null && source != null) {
        	System.out.println("ftpServer= " + ftpServer);
        	System.out.println("fileName= " + fileName);
        	System.out.println("source= " + source);
        	
            StringBuffer sb = new StringBuffer("ftp://");
            if (user != null && pwd != null) { //need authentication?
                sb.append(user);
                sb.append(':');
                sb.append(pwd);
                sb.append('@');
            }//else: anonymous access
            sb.append(ftpServer);
            // caIMAGE upgrade - change to append /Images/djatoka after ftpServer
            sb.append('/');
            sb.append("Images");           
            sb.append('/');
            sb.append("djatoka");
            sb.append('/');
            sb.append(fileName);
            sb.append(";type=i"); //a=ASCII mode, i=image (binary) mode, d= file directory listing
            System.out.println("sb= " + sb.toString());

            
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                URL url = new URL(sb.toString());
                System.out.println("FtpUtil url= " + url);
                
                URLConnection urlc = url.openConnection();
    
                bos = new BufferedOutputStream(urlc.getOutputStream());
                bis = new BufferedInputStream(new FileInputStream( source.getPath() ) );
                
                int i;
                while ((i = bis.read()) != -1) { //read next byte until end of stream
                    bos.write(i);
                }//next byte
            } finally {
                if (bis != null) try { bis.close(); } catch (IOException ioe) { /* ignore*/ }
                if (bos != null) try { bos.close(); } catch (IOException ioe) { /* ignore*/ }
            }
        } //else: input unavailable
    } //upload()
}
