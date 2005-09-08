/*
 * Created on Aug 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.service.SexDistributionManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;

import java.util.List;

import org.hibernate.eqbe.Evaluation;
import org.hibernate.eqbe.Evaluator;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SexDistributionManagerImpl extends BaseManager implements SexDistributionManager {

    public List getAll() {
        List sexDistributions = null;

        try {
            sexDistributions = Search.query(SexDistribution.class);
        } catch (Exception e) {
            System.out.println("Exception in SexDistributionManagerImpl.getAll");
            e.printStackTrace();
        }

        return sexDistributions;
    }

    public SexDistribution get(String id) {
        SexDistribution sexDistribution = null;

        try {
            sexDistribution = (SexDistribution) Search.queryById(SexDistribution.class, new Long(id));
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in SexDistributionManagerImpl.get");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in SexDistributionManagerImpl.get");
            e.printStackTrace();
        }

        return sexDistribution;
    }

    public SexDistribution getByType(String inType) {

        SexDistribution sexDistribution = null;

        try {

            // The following two objects are needed for eQBE.
            SexDistribution theSexDistribution = new SexDistribution();
            theSexDistribution.setType(inType);

            // Apply evaluators to object properties
            Evaluation theEvaluation = new Evaluation();
            theEvaluation.addEvaluator("sexDistribution.type", Evaluator.EQUAL);

            List theList = Search.query(theSexDistribution, theEvaluation);

            if (theList != null && theList.size() > 0) {
                sexDistribution = (SexDistribution) theList.get(0);
            }

        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in PersonManagerImpl.getByType");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in PersonManagerImpl.getByType");
            e.printStackTrace();
        }

        return sexDistribution;
    }

    public void save(SexDistribution sexDistribution) {
        try {
            Persist.save(sexDistribution);
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in SexDistributionManagerImpl.save");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in SexDistributionManagerImpl.save");
            e.printStackTrace();
        }
    }

    public void remove(String id) {
        try {
            Persist.deleteById(SexDistribution.class, new Long(id));
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in SexDistributionManagerImpl.remove");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in SexDistributionManagerImpl.remove");
            e.printStackTrace();
        }
    }
}
