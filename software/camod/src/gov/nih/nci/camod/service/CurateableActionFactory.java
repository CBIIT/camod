/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: CurateableActionFactory.java,v 1.2 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

public interface CurateableActionFactory {
    public CurateableAction getAction(String inActionName);
    public void registerAction(String inActionName, CurateableAction inAction);
}
