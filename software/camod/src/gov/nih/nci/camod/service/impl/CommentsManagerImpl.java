/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * @author dgeorge
 * 
 * $Id: CommentsManagerImpl.java,v 1.7 2007-07-31 12:02:28 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/05/08 13:32:52  georgeda
 * Clean up warnings
 *
 * Revision 1.5  2006/01/18 14:24:23  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.4  2005/10/27 19:42:05  georgeda
 * Cleanup
 *
 * Revision 1.3  2005/10/17 13:26:50  georgeda
 * Updates for curation
 *
 * Revision 1.2  2005/10/11 18:13:01  georgeda
 * More comment changes
 *
 * Revision 1.1  2005/10/10 14:07:31  georgeda
 * Initial revision
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Availability;
import gov.nih.nci.camod.domain.Comments;
import gov.nih.nci.camod.domain.ModelSection;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.CommentsManager;
import gov.nih.nci.camod.webapp.form.CommentsData;
import gov.nih.nci.common.persistence.Search;

import java.util.Date;
import java.util.List;

/**
 * Manages fetching/saving/updating of comments
 */
public class CommentsManagerImpl extends BaseManager implements CommentsManager
{

    /**
     * Get all of the comments in the DB
     * 
     * @return the list of all animal models
     * 
     * @exception throws
     *                an Exception if an error occurred
     */
    public Comments create(CommentsData inCommentsData) throws Exception
    {
        log.trace("In CommentsManagerImpl.create");

        Comments theComments = new Comments();

        String theSafeRemark = inCommentsData.getRemark();
        if (theSafeRemark.length() > 2000)
        {
            theSafeRemark.substring(0, 1999);
        }
        theComments.setRemark(theSafeRemark);

        // Set the model section
        ModelSection theQBEModelSection = new ModelSection();
        theQBEModelSection.setName(inCommentsData.getSectionName());

        List theList = Search.query(theQBEModelSection);

        if (theList.size() > 0)
        {
            ModelSection theModelSection = (ModelSection) theList.get(0);
            theComments.setModelSection(theModelSection);
        }
        else
        {
            throw new IllegalArgumentException("Unknown model section name: " + inCommentsData.getSectionName());
        }

        // Add the submitter
        Person theSubmitter = PersonManagerSingleton.instance().getByUsername(inCommentsData.getSubmitter());
        if (theSubmitter == null)
        {
            throw new IllegalArgumentException("Invalid submitter: " + inCommentsData.getSubmitter());
        }
        theComments.setSubmitter(theSubmitter);

        // Set the cancer model
        AnimalModel theAnimalModel = AnimalModelManagerSingleton.instance().get(inCommentsData.getModelId());

        if (theAnimalModel == null)
        {
            throw new IllegalArgumentException("Invalid model id: " + inCommentsData.getModelId());
        }
        theComments.setAbstractCancerModel(theAnimalModel);

        // Create an availability
        Availability theAvailability = new Availability();
        theAvailability.setEnteredDate(new Date());
        theAvailability.setReleaseDate(new Date());

        theComments.setAvailability(theAvailability);

        // Set the state
        theComments.setState(inCommentsData.getState());

        return theComments;
    }

    /**
     * Get all of the comments in the DB
     * 
     * @return the list of all animal models
     * 
     * @exception throws
     *                an Exception if an error occurred
     */
    public List<Comments> getAll() throws Exception
    {
        log.trace("In CommentsManagerImpl.getAll");
        return super.getAll(AnimalModel.class);
    }

    /**
     * Get all of the comments of a specific state
     * 
     * @param inState
     *            the state to query for
     * 
     * @return the list of comments
     * 
     * @exception Exception
     *                if an error occurred
     */
    public List<Comments> getAllByStateForPerson(String inState,
                                                 Person inPerson) throws Exception
    {
        log.trace("In CommentsManagerImpl.getAllByStateForPerson");
        return QueryManagerSingleton.instance().getCommentsByStateForPerson(inState, inPerson);
    }

    /**
     * Get all of the comments for a specifed section by person
     * 
     * @param inSection
     *            the section the comment is associated with
     * @param inPerson
     *            the person the comment is associated with (allows users to
     *            view unrelease comments if they are their own)
     * 
     * @return the list of comments
     * 
     * @exception Exception
     *                if an error occurred
     */
    public List<Comments> getAllBySection(String inState,
                                          Person inPerson,
                                          AnimalModel inModel) throws Exception
    {
        log.trace("In CommentsManagerImpl.getAllBySectionForPerson");
        return QueryManagerSingleton.instance().getCommentsBySection(inState, inPerson, inModel);
    }

    /**
     * Get a specific comment
     * 
     * @param id
     *            The unique id for the comments
     * 
     * @return the comment if found, null otherwise
     * @throws Exception
     * 
     * @exception Exception
     *                if an error occurred
     */
    public Comments get(String id) throws Exception
    {
        log.trace("In CommentsManagerImpl.get");
        return (Comments) super.get(id, Comments.class);
    }
}
