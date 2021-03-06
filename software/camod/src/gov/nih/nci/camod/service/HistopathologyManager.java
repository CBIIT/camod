/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyManager.java,v 1.6 2007-04-30 20:07:51 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/20 19:18:24  pandyas
 * Moved save Assoc Met from AnimalModel to the Histopathology
 *
 * Revision 1.4  2005/11/09 00:17:06  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.3  2005/11/07 19:15:33  pandyas
 * modified for clinical marker screen
 *
 * Revision 1.2  2005/11/04 14:44:25  georgeda
 * Cleaned up histopathology/assoc metastasis
 *
 * Revision 1.1  2005/11/03 18:54:43  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.webapp.form.AssociatedMetastasisData;
import gov.nih.nci.camod.webapp.form.HistopathologyData;

import java.util.List;

public interface HistopathologyManager {

    public List getAll() throws Exception;
    
    public Histopathology get(String id) throws Exception;
    
    public Histopathology createHistopathology(AnimalModel inAnimalModel, HistopathologyData inHistopathologyData)throws Exception;
    
    public void createAssociatedMetastasis(AnimalModel inAnimalModel, AssociatedMetastasisData inAssociatedMetastasisData, Histopathology inHistopathology)throws Exception;
    
    public void updateHistopathology(AnimalModel inAnimalModel, HistopathologyData inHistopathologyData, Histopathology theHistopathology) throws Exception;
    
    public void updateAssociatedMetastasis(AnimalModel inAnimalModel, AssociatedMetastasisData inAssociatedMetastasisData, Histopathology inAssociatedMetastasis) throws Exception;    

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;
    
    public void addAssociatedMetastasis(AnimalModel inAnimalModel,Histopathology inHistopathology, AssociatedMetastasisData inAssociatedMetastasisData) throws Exception;      
    
    public void removeAssociatedMetastasis(String id, Histopathology inHistopathology) throws Exception; 
    
    public void save(Histopathology histopathology) throws Exception;

}
