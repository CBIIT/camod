/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.webapp.form;

public interface AssociatedExpressionData {

	public String getName();

	public void setName(String name);
	
	public String getExpressionLevel();
	
	public void setExpressionLevel(String expressionLevel);

	public String getEngineeredGeneID();
	
	public void setEngineeredGeneID(String engineeredGeneID);
	
	public String getOrgan();
	
	public void setOrgan(String organ);
	
	public String getOrganTissueCode();
	
	public void setOrganTissueCode(String organTissueCode);
	
	public String getOrganTissueName();
	
	public void setOrganTissueName(String organTissueName);
	
}
