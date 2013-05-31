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
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InducedMutationForm extends BaseForm implements Serializable, InducedMutationData {
    
    private static final long serialVersionUID = 3257175453799404851L;
    
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public InducedMutationForm() {}
	
	protected String type;
	protected String otherType;
	protected String CASNumber;
	protected String geneId;
	protected String name;
	protected String description;
	protected String observation;
	protected String methodOfObservation;
    protected String comments;
	protected String numberMGI;
	
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */	
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the otherType.
	 */
	public String getOtherType() {
		return otherType;
	}
	/**
	 * @param otherType The otherType to set.
	 */	
	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}	
	/**
	 * @return Returns the CASNumber.
	 */
	public String getCASNumber() {
		return CASNumber;
	}
	/**
	 * @param CASNumber The CASNumber to set.
	 */	
	public void setCASNumber(String CASNumber) {
		this.CASNumber = CASNumber;
	}	
	/**
	 * @return Returns the geneId.
	 */
	public String getGeneId() {
		return geneId;
	}
	/**
	 * @param geneId The geneId to set.
	 */
	public void setGeneId(String geneId) {
		this.geneId = geneId;
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
	 * @return Returns the observation.
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * @param observation The observation to set.
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * @return Returns the methodOfObservation.
	 */
	public String getMethodOfObservation() {
		return methodOfObservation;
	}
	/**
	 * @param methodOfObservation The methodOfObservation to set.
	 */
	public void setMethodOfObservation(String methodOfObservation) {
		this.methodOfObservation = methodOfObservation;
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
}
