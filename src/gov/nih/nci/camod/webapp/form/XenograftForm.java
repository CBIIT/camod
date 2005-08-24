/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class XenograftForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public XenograftForm() {}
	
	// This form does not include properties from the parent class of Xenograft,
	// AbstractCancerModel
	protected String name;
	protected String ATTCNumber;
	protected String parentalCellLineName;
	protected String ageAtTreatment;
	protected String cellAmount;
	protected String harvestDate;
	protected String modificationDescription;	
	protected String geneticManipulation;	
	protected String administrativeSite;
	protected String graftType;
	protected String otherGraftType;

	
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
	 * @return Returns the ATTCNumber.
	 */
	public String getATTCNumber() {
		return ATTCNumber;
	}
	/**
	 * @param ATTCNumber The ATTCNumber to set.
	 */
	public void setATTCNumber(String ATTCNumber) {
		this.ATTCNumber = ATTCNumber;
	}	
	/**
	 * @return Returns the parentalCellLineName.
	 */
	public String getParentalCellLineName() {
		return parentalCellLineName;
	}
	/**
	 * @param parentalCellLineName The parentalCellLineName to set.
	 */
	public void setParentalCellLineName(String parentalCellLineName) {
		this.parentalCellLineName = parentalCellLineName;
	}
	/**
	 * @return Returns the ageAtTreatment.
	 */
	public String getAgeAtTreatment() {
		return ageAtTreatment;
	}
	/**
	 * @param ageAtTreatment The ageAtTreatment to set.
	 */
	public void setAgeAtTreatment(String ageAtTreatment) {
		this.ageAtTreatment = ageAtTreatment;
	}
	/**
	 * @return Returns the cellAmount.
	 */
	public String getCellAmount() {
		return cellAmount;
	}
	/**
	 * @param cellAmount The cellAmount to set.
	 */
	public void setCellAmount(String cellAmount) {
		this.cellAmount = cellAmount;
	}	
	/**
	 * @return Returns the harvestDate.
	 */
	public String getHarvestDate() {
		return harvestDate;
	}
	/**
	 * @param harvestDate The harvestDate to set.
	 */
	public void setHarvestDate(String harvestDate) {
		this.harvestDate = harvestDate;
	}	
	/**
	 * @return Returns the modificationDescription.
	 */
	public String getModificationDescription() {
		return modificationDescription;
	}
	/**
	 * @param modificationDescription The modificationDescription to set.
	 */
	public void setModificationDescription(String modificationDescription) {
		this.modificationDescription = modificationDescription;
	}	
	/**
	 * @return Returns the geneticManipulation.
	 */
	public String getGeneticManipulation() {
		return geneticManipulation;
	}
	/**
	 * @param geneticManipulation The geneticManipulation to set.
	 */
	public void setGeneticManipulation(String geneticManipulation) {
		this.geneticManipulation = geneticManipulation;
	}	
	/**
	 * @return Returns the administrativeSite.
	 */
	public String getAdministrativeSite() {
		return administrativeSite;
	}
	/**
	 * @param administrativeSite The administrativeSite to set.
	 */
	public void setAdministrativeSite(String administrativeSite) {
		this.administrativeSite = administrativeSite;
	}
	/**
	 * @return Returns the graftType.
	 */
	public String getGraftType() {
		return graftType;
	}
	/**
	 * @param graftType The graftType to set.
	 */
	public void setGraftType(String graftType) {
		this.graftType = graftType;
	}
	/**
	 * @return Returns the otherGraftType.
	 */
	public String getOtherGraftType() {
		return otherGraftType;
	}
	/**
	 * @param otherGraftType The otherGraftType to set.
	 */
	public void setOtherGraftType(String otherGraftType) {
		this.otherGraftType = otherGraftType;
	}	
}
