/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: TargetedModificationData.java,v 1.10 2007-10-31 17:59:14 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2007/07/31 12:01:41  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.8  2007/04/04 13:23:37  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.7  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.6  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.form;

import org.apache.struts.upload.FormFile;

public interface TargetedModificationData extends TransgeneData {
    
	public String getName();
	
	public void setName(String name);
	
	public String[] getModificationType();
	
	public void setModificationType(String[] modificationType);
	
	public String getOtherModificationType();
	
	public void setOtherModificationType(String otherModificationType);
	
	public String getGeneIdentifier();
	
	public void setGeneIdentifier(String geneIdentifier);
	
	public String getEsCellLineName();
	
	public void setEsCellLineName(String esCellLineName);
	
	public String getBlastocystName();
	
	public void setBlastocystName(String blastocystName);
	
	public String getConditionedBy();
	
	public void setConditionedBy(String conditionedBy);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public String getComments();
	
	public void setComments(String comments);
	
	public String getUrl();

	public void setUrl(String url);
	
	public FormFile getFileLocation();
	
	public void setFileLocation(FormFile fileLocation);
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public String getDescriptionOfConstruct();
	
	public void setDescriptionOfConstruct(String descriptionOfConstruct);
    
    public String getModificationId();
    
    public void setModificationId(String modificationId);
    
    public String getImageUrl();
    
    public void setImageUrl(String imageUrl);
    
    public String getThumbUrl();
    
    public void setThumbUrl(String thumbUrl);
}
