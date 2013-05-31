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
public class MicroArrayDataForm extends BaseForm implements Serializable {
    
    private static final long serialVersionUID = 3257015453799404851L;
    
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public MicroArrayDataForm() {}
	
	protected String experimentName;
	protected String experimentId;
	protected String otherLocationURL;
	
	/**
	 * @return Returns the experimentName.
	 */
	public String getExperimentName() {
		return experimentName;
	}
	/**
	 * @param experimentName The experimentName to set.
	 */
	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}	
	/**
	 * @return Returns the experimentId.
	 */
	public String getExperimentId() {
		return experimentId;
	}
	/**
	 * @param experimentId The experimentId to set.
	 */
	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}
	/**
	 * @return Returns the otherLocationURL.
	 */
	public String getOtherLocationURL() {
		return otherLocationURL;
	}
	/**
	 * @param otherLocationURL The otherLocationURL to set.
	 */
	public void setOtherLocationURL(String otherLocationURL) {
		this.otherLocationURL = otherLocationURL;
	}

}
