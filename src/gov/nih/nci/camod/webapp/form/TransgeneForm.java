/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * @author pandyas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TransgeneForm extends BaseForm implements Serializable {
    
    private static final long serialVersionUID = 3257105453799404851L;
    
	/**
	 * Default empty constructor
	 * @author pandyas
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public TransgeneForm() {}
	
	// engineeredGeneId is of type String since it comes from the presentation layer
	protected String engineeredGeneId;
	protected String locationOfIntegration;
	protected String name;
	protected String scientificName;
	protected String numberMGI;
	protected String function;
	protected String conditionedBy;
	protected String description;
	protected String comments;
	protected String fileServerLocation;
	protected String title;
	protected String segmentSize;
	protected String cloneDesignator;
	
	/**
	 * @return Returns the engineeredGeneId.
	 */
	public String getEngineeredGeneId() {
		return engineeredGeneId;
	}
	/**
	 * @param engineeredGeneId The engineeredGeneId to set.
	 */
	public void setEngineeredGeneId(String engineeredGeneId) {
		this.engineeredGeneId = engineeredGeneId;
	}
	/**
	 * @return Returns the locationOfIntegration.
	 */
	public String getLocationOfIntegration() {
		return locationOfIntegration;
	}
	/**
	 * @param locationOfIntegration The locationOfIntegration to set.
	 */
	public void setLocationOfIntegration(String locationOfIntegration) {
		this.locationOfIntegration = locationOfIntegration;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the scientificName.
	 */
	public String getScientificName() {
		return scientificName;
	}
	/**
	 * @param scientificName The scientificName to set.
	 */
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}	
	/**
	 * @return Returns the numberMGI.
	 */
	public String getNumberMGI() {
		return numberMGI;
	}
	/**
	 * @param numberMGI The numberMGI to set.
	 */
	public void setNumberMGI(String numberMGI) {
		this.numberMGI = numberMGI;
	}
	/**
	 * @return Returns the function.
	 */
	public String getFunction() {
		return function;
	}
	/**
	 * @param function The function to set.
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	/**
	 * @return Returns the conditionedBy.
	 */
	public String getConditionedBy() {
		return conditionedBy;
	}
	/**
	 * @param conditionedBy The conditionedBy to set.
	 */
	public void setConditionedBy(String conditionedBy) {
		this.conditionedBy = conditionedBy;
	}	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the fileServerLocation.
	 */
	public String getFileServerLocation() {
		return fileServerLocation;
	}
	/**
	 * @param fileServerLocation The fileServerLocation to set.
	 */
	public void setFileServerLocation(String fileServerLocation) {
		this.fileServerLocation = fileServerLocation;
	}	
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the segmentSize.
	 */
	public String getSegmentSize() {
		return segmentSize;
	}
	/**
	 * @param segmentSize The segmentSize to set.
	 */
	public void setSegmentSize(String segmentSize) {
		this.segmentSize = segmentSize;
	}
	/**
	 * @return Returns the cloneDesignator.
	 */
	public String getCloneDesignator() {
		return cloneDesignator;
	}
	/**
	 * @param cloneDesignator The cloneDesignator to set.
	 */
	public void setCloneDesignator(String cloneDesignator) {
		this.cloneDesignator = cloneDesignator;
	}	
	

}
