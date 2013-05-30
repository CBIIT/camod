/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Id: TargetedModificationForm.java,v 1.15 2007-10-31 18:01:31 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2007/07/31 12:02:03  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.13  2007/04/04 13:23:37  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.12  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.11  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

/**
 * 
 * $Id: TargetedModificationForm.java,v 1.15 2007-10-31 18:01:31 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2007/07/31 12:02:03  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.13  2007/04/04 13:23:37  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.12  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.11  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */
public class TargetedModificationForm extends BaseForm implements Serializable, TargetedModificationData
{

    private static final long serialVersionUID = 3257085453799404851L;

    protected String name;
    protected String[] modificationType;
    protected String otherModificationType;
	protected String geneIdentifier;
    protected String esCellLineName;
    protected String blastocystName;
    protected String conditionedBy;
    protected String description;
    protected String comments;
    protected String mgiId;
	protected String zfinId;
	protected String rgdId;     
    protected String url;
    protected FormFile fileLocation;
    protected String title;
    protected String descriptionOfConstruct;
    protected String modificationId;
    protected String imageUrl;
    protected String thumbUrl;
    protected String constructSequence;

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the modificationType.
     */
    public String[] getModificationType()
    {
        return modificationType;
    }

    /**
     * @param modificationType The modificationType to set.
     */
    public void setModificationType(String[] modificationType)
    {
        this.modificationType = modificationType;
    }

    /**
     * @return Returns the otherModificationType.
     */
    public String getOtherModificationType()
    {
        return otherModificationType;
    }

    /**
     * @param otherModificationType The otherModificationType to set.
     */
    public void setOtherModificationType(String otherModificationType)
    {
        this.otherModificationType = otherModificationType;
    }

    /**
     * @return Returns the esCellLineName.
     */
    public String getEsCellLineName()
    {
        return esCellLineName;
    }

    /**
     * @param esCellLineName The esCellLineName to set.
     */
    public void setEsCellLineName(String esCellLineName)
    {
        this.esCellLineName = esCellLineName;
    }

    /**
     * @return Returns the blastocystName.
     */
    public String getBlastocystName()
    {
        return blastocystName;
    }

    /**
     * @param blastocystName The blastocystName to set.
     */
    public void setBlastocystName(String blastocystName)
    {
        this.blastocystName = blastocystName;
    }

    /**
     * @return Returns the conditionedBy.
     */
    public String getConditionedBy()
    {
        return conditionedBy;
    }

    /**
     * @param conditionedBy The conditionedBy to set.
     */
    public void setConditionedBy(String conditionedBy)
    {
        this.conditionedBy = conditionedBy;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * @param comments The comments to set.
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescriptionOfConstruct()
    {
        return descriptionOfConstruct;
    }

    public void setDescriptionOfConstruct(String descriptionOfConstruct)
    {
        this.descriptionOfConstruct = descriptionOfConstruct;
    }

    public FormFile getFileLocation()
    {
        return fileLocation;
    }

    public void setFileLocation(FormFile fileLocation)
    {
        this.fileLocation = fileLocation;
    }

    public String getModificationId()
    {
        return modificationId;
    }

    public void setModificationId(String modificationId)
    {
        this.modificationId = modificationId;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getThumbUrl()
    {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl)
    {
        this.thumbUrl = thumbUrl;
    }

    /**
     * @return Returns the constructSequence.
     */
    public String getConstructSequence()
    {
        return constructSequence;
    }

    /**
     * @param constructSequence The constructSequence to set.
     */
    public void setConstructSequence(String constructSequence)
    {
        this.constructSequence = constructSequence;
    }

	public String getMgiId() {
		return mgiId;
	}

	public void setMgiId(String mgiId) {
		this.mgiId = mgiId;
	}

	public String getRgdId() {
		return rgdId;
	}

	public void setRgdId(String rgdId) {
		this.rgdId = rgdId;
	}

	public String getZfinId() {
		return zfinId;
	}

	public void setZfinId(String zfinId) {
		this.zfinId = zfinId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the geneIdentifier
	 */
	public String getGeneIdentifier() {
		return geneIdentifier;
	}

	/**
	 * @param geneIdentifier the geneIdentifier to set
	 */
	public void setGeneIdentifier(String geneIdentifier) {
		this.geneIdentifier = geneIdentifier;
	}
}
