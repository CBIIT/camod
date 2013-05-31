/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.AgentTargetManager;

/**
 * @author pandyas
 * 
 * Singleton class for the AgentTargetManager
 */
public class AgentTargetManagerSingleton {

    private static AgentTargetManager ourManager = new AgentTargetManagerImpl();

    /**
     * @return the global instance of the AgentTargetManager
     */
    public static synchronized AgentTargetManager instance() {
        return ourManager;
    }	
}
