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
 * $Id: GeneDeliveryData.java,v 1.3 2005-10-20 20:02:59 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/10/19 18:07:56  pandyas
 * added age and gender to genedelivery
 *
 * Revision 1.1  2005/09/28 21:20:24  georgeda
 * Finished up converting to new manager
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.AgeGenderData;
import gov.nih.nci.camod.webapp.form.cibase.TreatmentData;

/**
 * Gene delivery interface for CI screen
 */
public interface GeneDeliveryData  extends TreatmentData, AgeGenderData {

	public String getViralVector();
    
	public void setViralVector(String viralVector);
	
	public String getOtherViralVector();    
	
	public void setOtherViralVector(String otherViralVector);
    
	public String getGeneInVirus();
    
	public void setGeneInVirus(String geneInVirus);
	
	public String getOrgan();
	
	public void setOrgan( String organ );
	
	public String getOrganTissueName();
	
	public void setOrganTissueName( String organTissueName );
	
	public String getOrganTissueCode();
	
	public void setOrganTissueCode( String organTissueCode );	
    
}
