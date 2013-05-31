/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: AgentTargetManager.java,v 1.3 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AgentTarget;

/*
 *
 * @author pandyas
 *
 * $Id: AgentTargetManager.java,v 1.3 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/11/07 20:43:29  pandyas
 * modified getAll(), save(), rmove() and/or getByName (if applicable) to the current signature that throws exceptions
 *
 */
public interface AgentTargetManager {
    public AgentTarget getByName(String inName) throws Exception;	
	
}
