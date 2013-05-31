/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.domain.Taxon;
import java.util.List;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TaxonManager {

    public Taxon getOrCreate(String inScientificName, String inStrain, String inOtherStrain);
    
    public List getAll() throws Exception;

    public Taxon get(String id) throws Exception;

    public void save(Taxon taxon) throws Exception;

    public void remove(String id) throws Exception;

    public List getStrains(Species species) throws Exception;
}
