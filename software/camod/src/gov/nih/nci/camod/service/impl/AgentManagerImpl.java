/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.7  2006/01/18 14:24:23  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.6  2005/11/15 22:13:46  georgeda
 * Cleanup of drug screening
 *
 * Revision 1.5  2005/11/10 22:07:36  georgeda
 * Fixed part of bug #21
 *
 * 
 * $Id: AgentManagerImpl.java,v 1.9 2009-05-20 17:22:07 pandyas Exp $
 * 
 */
package gov.nih.nci.camod.service.impl;


import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.camod.util.DrugScreenResult;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.system.applicationservice.CaBioApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AgentManagerImpl extends BaseManager implements AgentManager
{

    /**
     * Get a specific Agent by id
     * 
     * @param id
     *            the unique id for a Agent
     * 
     * @return the matching Agent object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Agent get(String id) throws Exception
    {
        log.trace("In AgentManagerImpl.get");
        return (Agent) super.get(id, Agent.class);
    }

    /**
     * Save Agent
     * 
     * @param Agent
     *            the Agent to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(Agent agent) throws Exception
    {
        log.debug("In AgentManagerImpl.save");
        super.save(agent);
    }

    /**
     * Remove a specific Agent by id
     * 
     * @param id
     *            the unique id for a Agent
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void remove(String id) throws Exception
    {
        log.debug("In AgentManagerImpl.save");
        super.remove(id, Agent.class);
    }

    public Collection getClinicalProtocols(Agent a)
    {
    	CaBioApplicationService appService = null;
    	try {
    	appService = (CaBioApplicationService)ApplicationServiceProvider.getApplicationService();
        }
        catch (Exception e)
        {
        	log.error("AgentManagerImpl Unable to get information from caBIO", e);
        }   	
        Collection protocols = null;
        if (a != null)
        {
            Long nscNumber = a.getNscNumber();
            if (nscNumber != null)
            {
                // get clinical protocols from caBIO
                gov.nih.nci.cabio.domain.Agent agt = new gov.nih.nci.cabio.domain.Agent();
                agt.setNSCNumber(nscNumber);
                try
                {
                    List resultList = appService.search(gov.nih.nci.cabio.domain.Agent.class, agt);
                    final int resultCount = (resultList != null) ? resultList.size() : 0;
                    log.debug("Got " + resultCount + " results....");
                    for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();)
                    {
                        gov.nih.nci.cabio.domain.Agent returnedAgt = (gov.nih.nci.cabio.domain.Agent) resultsIterator.next();
                        log.debug("Returned Agent: " + returnedAgt.getNSCNumber());
                        protocols = returnedAgt.getClinicalTrialProtocolCollection();
                        if (protocols != null)
                        {
                            log.debug("Agent:" + returnedAgt.getName() + "Protocols.size()" + protocols.size());
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return protocols;
    }

    public List<DrugScreenResult> getYeastResults(Agent a,
                                                  boolean useNscNumber)
    {
        // get yeast results
        ArrayList<DrugScreenResult> yeastStages = new ArrayList<DrugScreenResult>();

        boolean foundResults = false;

        for (int k = 0; k <= 2; k++)
        {
            // do the query
            String stg = String.valueOf(k);
            log.debug("Calling getYeastScreenResults:" + a.getNscNumber() + " stage=" + stg);
            DrugScreenResult dsr = null;
            try
            {
                dsr = QueryManagerSingleton.instance().getYeastScreenResults(a, stg, useNscNumber);
            }
            catch (PersistenceException e)
            {
                e.printStackTrace();
            }
            if (dsr.strainCount > 0)
            {
                foundResults = true;
            }
            yeastStages.add(dsr);
        }

        // Only return a list if some data was found
        if (foundResults == false)
        {
            yeastStages = new ArrayList<DrugScreenResult>();
        }
        return yeastStages;
    }
}
