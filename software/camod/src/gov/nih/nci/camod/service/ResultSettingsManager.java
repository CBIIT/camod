/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.ResultSettings;

/**
 * Implementation of the ResultSettingsManager interface. Creates/saves/updates the
 * ResultSettings based on the specific interface passed in
 */
public interface ResultSettingsManager
{
    public void save(ResultSettings inResultSettings) throws Exception;
    
    public void remove(String id) throws Exception;
    
    public ResultSettings get(String id) throws Exception;
    
    public ResultSettings getByUsername(String username) throws Exception;
    
    public void update(ResultSettings inResultSettings) throws Exception;
    
}
