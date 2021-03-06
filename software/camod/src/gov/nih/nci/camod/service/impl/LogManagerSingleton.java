/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: LogManagerSingleton.java,v 1.3 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.LogManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the LogManager
 */
public class LogManagerSingleton
{
    private static LogManager ourManager = new LogManagerImpl();

    /**
     * @return the global instance of the LogManager
     */
    public static synchronized LogManager instance()
    {
        return ourManager;
    }
}

/*
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/01/18 14:24:24  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.1  2005/09/12 18:22:10  georgeda
 * Curation changes and addition of e-mail
 * Revision 1.1 2005/09/08 17:37:10
 * georgeda Initial revision
 * 
 */
