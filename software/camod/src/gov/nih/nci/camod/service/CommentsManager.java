/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: CommentsManager.java,v 1.3 2006-05-08 13:31:29 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/10/11 18:12:29  georgeda
 * More comment changes
 *
 * Revision 1.1  2005/10/10 14:06:56  georgeda
 * Initial revision
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.webapp.form.CommentsData;

import java.util.List;

/**
 * Interface for the CommentManager class. See implementing classes for details.
 */
public interface CommentsManager {

    public Comments create(CommentsData inCommentsData) throws Exception;
    
	public List getAll() throws Exception;

	public List getAllByStateForPerson(String inState, Person inPerson) throws Exception;
	
	public List<Comments> getAllBySection(String inSection, Person inPerson, AnimalModel inModel) throws Exception;

	public Comments get(String id) throws Exception;
}
