/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.InducedMutationManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the SexDistributionManager
 */
public class InducedMutationManagerSingleton {

    private static InducedMutationManager ourManager = new InducedMutationManagerImpl();

    /**
     * @return the global instance of the InducedMutationManager
     */
    public static synchronized InducedMutationManager instance() {
        return ourManager;
    }
}
