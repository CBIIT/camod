/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.SexDistributionManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the SexDistributionManager
 */
public class SexDistributionManagerSingleton {

    private static SexDistributionManager ourManager = new SexDistributionManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized SexDistributionManager instance() {
        return ourManager;
    }
}
