/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: EngineeredTransgeneManagerSingleton.java,v 1.3 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

public class EngineeredTransgeneManagerSingleton
{
    private static EngineeredTransgeneManagerImpl ourManager = new EngineeredTransgeneManagerImpl();

    /**
     * @return the global instance of the GeneDelivery
     */
    public static synchronized EngineeredTransgeneManagerImpl instance()
    {
        return ourManager;
    }
}
