package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.LogManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.Date;
import java.util.List;

/**
 * Implementation of the manager which stores/retrieves Log objects
 */
public class LogManagerImpl extends BaseManager implements LogManager {

    /**
     * Get the latest Log object that matches the state/user and model
     * 
     * @parameter inModelId is the animal model the Log is associated with
     * @parameter inState is the current state of the animal model
     * @parameter inAssignedUsername is the user that the model is assigned to
     * 
     * @return the latest matching Log object. null if not found
     */
    public Log getCurrentByModelAndAssigned(String inModelId, String inState, String inAssignedUsername) {

        log.info("Entering LogManagerImpl.getCurrentByModelAndAssigned");

        Log theLog = null;

        try {

            // The following objects are needed for eQBE.
            Log theQBELog = new Log();

            AnimalModel theQBEAnimalModel = new AnimalModel();
            theQBEAnimalModel.setId(Long.getLong(inModelId));

            // Find the Person it's assigned to
            Person theAssignedPerson = PersonManagerSingleton.instance().getByUsername(inAssignedUsername);

            // Build the log object
            theQBELog.setCancerModel(theQBEAnimalModel);
            theQBELog.setType(inState);

            // TODO: should work?
            // theQBELog.setSubmitter(theAssignedPerson);

            // Apply evaluators to object properties
            Evaluation theEvaluation = new Evaluation();
            theEvaluation.addEvaluator("log.type", Evaluator.EQUAL);
            theEvaluation.addEvaluator("log.cancerModel.id", Evaluator.EQUAL);

            // TODO: Should work
            // theEvaluation.addEvaluator("log.submitter.id", Evaluator.EQUAL);

            // List theLogs = Search.query(theLog, theEvaluation);
            List theLogs = Search.query(theQBELog, theEvaluation);

            Date theNewestDate = null;

            // Grab the latest record if the user matches
            for (int i = 0; i < theLogs.size(); i++) {
                if (theNewestDate == null) {
                    Log theCurrentLog = (Log) theLogs.get(i);
                    if (theCurrentLog.getSubmitter().getId() == theAssignedPerson.getId()) {
                        theLog = theCurrentLog;
                        theNewestDate = new Date(theLog.getTimestamp());
                    }
                } else {
                    Log theCurrentLog = (Log) theLogs.get(i);
                    if (theCurrentLog.getSubmitter().getId() == theAssignedPerson.getId()) {

                        Date theDate = new Date(theLog.getTimestamp());

                        if (theDate.after(theNewestDate)) {
                            theNewestDate = theDate;
                            theLog = theCurrentLog;
                        }
                    }
                }
            }

        } catch (Exception e) {
            log.error("Exception in LogManagerImpl.getCurrentByModelAndAssigned", e);
            System.out.println("Exception in LogManagerImpl.getCurrentByModelAndAssigned");
            e.printStackTrace();
        }

        log.info("Exiting LogManagerImpl.getCurrentByModelAndAssigned");

        return theLog;
    }

    /**
     * Get all the Log objects in the system
     * 
     * @return all the Log objects in the system
     */
    public List getAll() {

        log.info("Entering LogManagerImpl.getAll");

        List theLogs = null;

        try {
            theLogs = Search.query(Log.class);
        } catch (PersistenceException pe) {
            log.error("Exception in LogManagerImpl.getAll", pe);
            System.out.println("PersistenceException in LogManagerImpl.getAll");
            pe.printStackTrace();
        } catch (Exception e) {
            log.error("Exception in LogManagerImpl.getAll", e);
            System.out.println("Exception in LogManagerImpl.getAll");
            e.printStackTrace();
        }

        log.info("Exiting LogManagerImpl.getAll");

        return theLogs;
    }

    /**
     * Get a log object by unique ID
     * 
     * @parameter inId the unique ID for the log object to fetch
     * 
     * @return the specific log object
     */
    public Log get(String inId) {

        log.info("Entering LogManagerImpl.get");

        Log theLog = null;

        try {
            theLog = (Log) Search.queryById(Log.class, new Long(inId));
        } catch (PersistenceException pe) {
            log.error("Exception in LogManagerImpl.getAll", pe);
            System.out.println("PersistenceException in LogManagerImpl.get");
            pe.printStackTrace();
        } catch (Exception e) {
            log.error("Exception in LogManagerImpl.getAll", e);
            System.out.println("Exception in LogManagerImpl.get");
            e.printStackTrace();
        }

        log.info("Entering LogManagerImpl.get");

        return theLog;
    }

    /**
     * Save the log object
     * 
     * @parameter inLog is the log object to save
     */
    public void save(Log inLog) {

        log.info("Entering LogManagerImpl.save");

        try {
            Persist.save(inLog);
        } catch (PersistenceException pe) {
            log.error("Exception in LogManagerImpl.save", pe);
            System.out.println("PersistenceException in LogManagerImpl.save");
            pe.printStackTrace();
        } catch (Exception e) {
            log.error("Exception in LogManagerImpl.save", e);
            System.out.println("Exception in LogManagerImpl.save");
            e.printStackTrace();
        }

        log.info("Exiting LogManagerImpl.save");
    }

    /**
     * Save the log object
     * 
     * @parameter inAssignedPerson is the person who the model is assigned to
     * @parameter inModelId is the model assigned to the person
     * @parameter inState is the current state of the object
     * @parameter inNotes is any note(s) associated w/ the state transition
     */
    public void save(String inAssignedPerson, String inModelId, String inState, String inNotes) {

        log.info("Entering LogManagerImpl.save");

        try {

            log.debug("Person: " + inAssignedPerson);
            log.debug("Model: " + inModelId);
            log.debug("State: " + inState);
            log.debug("Notes: " + inNotes);

            Person theAssignedPerson = PersonManagerSingleton.instance().getByUsername(inAssignedPerson);
            AnimalModel theAnimalModel = AnimalModelManagerSingleton.instance().get(inModelId);

            Log theLog = new Log();

            theLog.setCancerModel(theAnimalModel);
            theLog.setSubmitter(theAssignedPerson);
            theLog.setTimestamp((new Date()).toString());
            theLog.setType(inState);
            theLog.setNotes(inNotes);

            Persist.save(theLog);

        } catch (PersistenceException pe) {
            log.error("Exception in LogManagerImpl.save", pe);
            System.out.println("PersistenceException in LogManagerImpl.save");
            pe.printStackTrace();
        } catch (Exception e) {
            log.error("Exception in LogManagerImpl.save", e);
            System.out.println("Exception in LogManagerImpl.save");
            e.printStackTrace();
        }

        log.info("Exiting LogManagerImpl.save");
    }

    /**
     * Remove the log object
     * 
     * @parameter inId the unique ID for the log object to remove
     */
    public void remove(String inId) {

        log.info("Entering LogManagerImpl.remove");

        try {
            log.debug("Removing id: " + inId);
            Persist.deleteById(Log.class, new Long(inId));
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in LogManagerImpl.remove");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in LogManagerImpl.remove");
            e.printStackTrace();
        }

        log.info("Exiting LogManagerImpl.remove");
    }
}