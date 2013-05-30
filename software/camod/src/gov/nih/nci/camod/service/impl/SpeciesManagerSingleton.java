/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: SpeciesManagerSingleton.java,v 1.1 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.SpeciesManager;

public class SpeciesManagerSingleton
{
    private static SpeciesManager ourManager = new SpeciesManagerImpl();

    /**
     * @return the global instance of the OrganManager
     */
    public static synchronized SpeciesManager instance()
    {
        return ourManager;
    }
  
}
