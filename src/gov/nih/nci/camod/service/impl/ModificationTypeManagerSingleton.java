/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.ModificationTypeManager;

/**
 * 
 * Singleton class for the ModificationTypeManager
 */
public class ModificationTypeManagerSingleton {

	
    private static ModificationTypeManager ourManager = new ModificationTypeManagerImpl();

    /**
     * @return the global instance of the ModificationTypeManager
     */
    public static synchronized ModificationTypeManager instance() {
        return ourManager;
    }	
}
