/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

/*
 * Created on Jun 17, 2005
 *
 * @author pandyas
 *
 * $Id: AgentTargetManagerImpl.java,v 1.2 2005-11-07 20:43:07 pandyas Exp $
 *
 *  $Log: not supported by cvs2svn $
 */

import gov.nih.nci.camod.domain.AgentTarget;
import gov.nih.nci.camod.service.AgentTargetManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;


public class AgentTargetManagerImpl extends BaseManager implements AgentTargetManager {

    /**
     * Get the AgentTarget by it's name
     * 
     * @param inName
     *            the name of the AgentTarget
     * 
     * @return the AgentTarget that matches the name
     */
    public AgentTarget getByName(String inName) throws Exception{

    	AgentTarget theAgentTarget = null;

        if (inName != null && inName.length() > 0) {
        	try {            	
        		// The following two objects are needed for eQBE.
        		AgentTarget theQueryObj = new AgentTarget();
        		theQueryObj.setTargetName(inName);

        		// Apply evaluators to object properties
        		Evaluation theEvaluation = new Evaluation();
        		theEvaluation.addEvaluator("chemicalClass.chemicalClassName", Evaluator.EQUAL);

        		List theList = Search.query(theQueryObj, theEvaluation);

        		if (theList != null && theList.size() > 0) {
        			theAgentTarget = (AgentTarget) theList.get(0);
            }

            } catch (PersistenceException pe) {
                log.error("PersistenceException in getByName", pe);
                throw pe;
            } catch (Exception e) {
                log.error("Exception in getByName", e);
                throw e;
            }
        }
        return theAgentTarget;
    }	

}

