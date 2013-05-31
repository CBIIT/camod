/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.webapp.form.PublicationData;

import java.util.List;

public interface PublicationManager {

	public Publication create(PublicationData inPublicationData) throws Exception;

	public void update(PublicationData inPublicationData, Publication inPublication) throws Exception;

	public void addCellLinePublication( PublicationData inPublicationData, CellLine inCellLine ) throws Exception;

	public void addTherapyPublication( PublicationData inPublicationData, Therapy inTherapy ) throws Exception;
	
	public void removeCellLinePublication( String id, CellLine inCellLine ) throws Exception;

	public void removeTherapyPublication(String id, Therapy inTherapy ) throws Exception;
	
	public List getAll() throws Exception;

	public Publication get(String id) throws Exception;

	public void save(Publication publication) throws Exception;

	public void remove(String id, AnimalModel inAnimalModel) throws Exception;

	public PublicationStatus getPublicationStatusByName(String inName) throws Exception;
	
}
