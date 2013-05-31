/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AgentTarget;

/*
 *
 * @author pandyas
 *
 * $Id: AgentTargetManager.java,v 1.2 2005-11-07 20:43:29 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
public interface AgentTargetManager {
    public AgentTarget getByName(String inName) throws Exception;	
	
}
