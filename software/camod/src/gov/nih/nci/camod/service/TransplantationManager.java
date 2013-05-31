/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: TransplantationManager.java,v 1.3 2009-03-25 16:21:25 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/01/16 18:30:30  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.1  2007/10/31 18:48:03  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.1  2007/07/31 12:03:05  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.7  2005/11/09 00:17:06  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.6  2005/10/20 20:43:54  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Transplantation;
import gov.nih.nci.camod.webapp.form.TransplantationData;

import java.util.List;


public interface TransplantationManager {

    public List getAll() throws Exception;

    public Transplantation get(String id) throws Exception;

    public void save(Transplantation transplant) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public Transplantation create(TransplantationData inTransplantationData, AnimalModel inAnimalModel) throws Exception;

    public void update(TransplantationData inTransplantData, Transplantation inTransplantation, AnimalModel inAnimalModel)
            throws Exception;
}
