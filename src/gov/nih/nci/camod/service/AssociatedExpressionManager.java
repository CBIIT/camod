/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.ExpressionFeature;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionData;

import java.util.List;

public interface AssociatedExpressionManager {
	
	public List getAll() throws Exception;
	
	public ExpressionFeature get(String id) throws Exception;

	public void save(ExpressionFeature expressionFeature) throws Exception;

	public void remove(String id, EngineeredGene inTransgene) throws Exception;
	
	public ExpressionFeature create( AssociatedExpressionData inAssociatedExpressionData ) throws Exception;
	
	public void update( AssociatedExpressionData inAssociatedExpressionData, ExpressionFeature inExpressionFeature ) throws Exception;
}
