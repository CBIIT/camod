/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *  @author dgeorge
 *  
 *  $Id: CurationManager.java,v 1.6 2005-09-19 13:06:32 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Curateable;

import java.util.List;
import java.util.Map;

/**
 * This interface describes a realized/implementing CurationManager.
 */
public interface CurationManager {

    public String getDefaultState();

    public List getAllStateNames();

    public void changeState(Curateable inCuratableObject, String inEvent);

    public void applyActionsForState(Curateable inCuratableObject, Map inArgs);
}
