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
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.TransgeneManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TransgeneManagerImpl extends BaseManager implements TransgeneManager {
	
    /**
     * Get all Transgene by id
     * 
     * 
     * @return the matching Transgene objects, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public List getAll() throws Exception {
        log.trace("In TransgeneManagerImpl.getAll");
        return super.getAll(Transgene.class);
    }
	
    /**
     * Get a specific Transgene by id
     * 
     * @param id
     *            the unique id for a Transgene
     * 
     * @return the matching Therapy object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Transgene get(String id) throws Exception {
        log.trace("In TransgeneManagerImpl.get");
        return (Transgene) super.get(id, Transgene.class);
    }



    /**
     * Save Transgene
     * 
     * @param Transgene
     *            the Transgene to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(Transgene transgene) throws Exception {
        log.debug("In TransgeneManagerImpl.save");
        super.save(transgene);
    }

    /**
     * Remove a specific Transgene by id
     * 
     * @param id
     *            the unique id for a Transgene
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void remove(String id) throws Exception {
        log.debug("In TransgeneManagerImpl.save");
        super.remove(id, Transgene.class);
    }
}
