/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: OrganManager.java,v 1.6 2007-05-17 12:25:34 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/21 13:38:40  georgeda
 * Cleanup
 *
 * Revision 1.4  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.3  2005/11/07 20:43:29  pandyas
 * modified getAll(), save(), rmove() and/or getByName (if applicable) to the current signature that throws exceptions
 *
 * Revision 1.2  2005/10/20 20:42:43  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Organ;

import java.util.List;

public interface OrganManager
{
    public List getAll() throws Exception;

    public Organ get(String id) throws Exception;

    public Organ getByName(String inName) throws Exception;
    
    public Organ getOrCreate(String inConceptCode, String inName) throws Exception;    

}
