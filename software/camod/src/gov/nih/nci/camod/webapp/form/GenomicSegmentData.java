/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: GenomicSegmentData.java,v 1.9 2007-07-31 12:01:44 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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

public interface GenomicSegmentData extends TransgeneData {
	
	public String getLocationOfIntegration();
	
	public void setLocationOfIntegration(String locationOfIntegration);
	
    public String getIsRandom();
	
    public void setIsRandom(String isRandom);
	
	public String getSegmentName();
	
	public void setSegmentName(String segmentName);
	
	public String getOtherSegmentName();
	
	public void setOtherSegmentName(String otherSegmentName);
	
	public String getSegmentSize();
	
	public void setSegmentSize(String segmentSize);
	
	public String getCloneDesignator();
	
	public void setCloneDesignator(String cloneDesignator);
	
	public String getComments();
	
	public void setComments(String comments);
	
	public FormFile getFileLocation();
	
	public void setFileLocation(FormFile fileLocation);
	
	public String getUrl();

	public void setUrl(String url);
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public String getGenes();
	
	public void setGenes(String genes);
	
	public String getMarkers();
	
	public void setMarkers(String markers) ;
	
	public String getDescriptionOfConstruct();
	
	public void setDescriptionOfConstruct(String descriptionOfConstruct);
    
    public String getSegmentId();
    
    public void setSegmentId(String segmentId);
    
    public String getImageUrl();
    
    public void setImageUrl(String imageUrl);
    
    public String getThumbUrl();
    
    public void setThumbUrl(String thumbUrl);
}
