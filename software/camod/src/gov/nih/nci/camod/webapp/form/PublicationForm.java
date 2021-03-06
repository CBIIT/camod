/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Id: PublicationForm.java,v 1.13 2008-08-14 19:00:18 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2008/08/14 06:29:54  schroedn
 * publication rgd number for rat feature added
 *
 * Revision 1.11  2007/10/31 17:25:02  pandyas
 * Fixed #8355 	Add comments field to every submission page
 *
 * Revision 1.10  2007/05/17 17:58:04  pandyas
 * Updated reset method in form to include new attrubutes
 *
 * Revision 1.9  2007/05/07 16:52:07  pandyas
 * Added code to save, edit and populate zfinPubId from Publication object for pulications from zfin.org
 *
 * Revision 1.8  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * 
 * $Id: PublicationForm.java,v 1.13 2008-08-14 19:00:18 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2008/08/14 06:29:54  schroedn
 * publication rgd number for rat feature added
 *
 * Revision 1.11  2007/10/31 17:25:02  pandyas
 * Fixed #8355 	Add comments field to every submission page
 *
 * Revision 1.10  2007/05/17 17:58:04  pandyas
 * Updated reset method in form to include new attrubutes
 *
 * Revision 1.9  2007/05/07 16:52:07  pandyas
 * Added code to save, edit and populate zfinPubId from Publication object for pulications from zfin.org
 *
 * Revision 1.8  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */
public class PublicationForm extends BaseForm implements PublicationData, Serializable
{

    private static final long serialVersionUID = 3257155453799404851L;

    /**
     * Default empty constructor
     * @author rajputs
     */
    public PublicationForm()
    {}

    protected String authors;
    protected String name;
    protected String pmid;
    protected String title;
    protected String year;
    protected String journal;
    protected String volume;
    protected String startPage;
    protected String endPage;
    protected String firstTimeReported;
    protected String jaxJNumber;
    protected String rgdPubId;
    protected String zfinPubId;    
    protected String aPubID;
    protected String aCellID;
    protected String aTherapyID;
	protected String comments;


    /**
     * @return Returns the authors.
     */
    public String getAuthors()
    {
        return authors;
    }

    /**
     * @param authors The authors to set.
     */
    public void setAuthors(String authors)
    {
        this.authors = authors;
    }

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
     * @return Returns the pmid.
     */
    public String getPmid()
    {
        return pmid;
    }

    /**
     * @param pmid The pmid to set.
     */
    public void setPmid(String pmid)
    {
        this.pmid = pmid;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return Returns the year.
     */
    public String getYear()
    {
        return year;
    }

    /**
     * @param year The year to set.
     */
    public void setYear(String year)
    {
        this.year = year;
    }

    /**
     * @return Returns the journal.
     */
    public String getJournal()
    {
        return journal;
    }

    /**
     * @param journal The journal to set.
     */
    public void setJournal(String journal)
    {
        this.journal = journal;
    }

    /**
     * @return Returns the volume.
     */
    public String getVolume()
    {
        return volume;
    }

    /**
     * @param volume The volume to set.
     */
    public void setVolume(String volume)
    {
        this.volume = volume;
    }

    /**
     * @return Returns the startPage.
     */
    public String getStartPage()
    {
        return startPage;
    }

    /**
     * @param startPage The startPage to set.
     */
    public void setStartPage(String startPage)
    {
        this.startPage = startPage;
    }

    /**
     * @return Returns the endPage.
     */
    public String getEndPage()
    {
        return endPage;
    }

    /**
     * @param endPage The endPage to set.
     */
    public void setEndPage(String endPage)
    {
        this.endPage = endPage;
    }

    /**
     * @return Returns the authors.
     */
    public String getFirstTimeReported()
    {
        return firstTimeReported;
    }

    /**
     * @param authors The authors to set.
     */
    public void setFirstTimeReported(String firstTimeReported)
    {
        this.firstTimeReported = firstTimeReported;
    }

    /**
     * @return Returns the jaxJNumber.
     */
    public String getJaxJNumber()
    {
        return jaxJNumber;
    }

    /**
     * @param jaxJNumber The jaxJNumber to set.
     */
    public void setJaxJNumber(String jaxJNumber)
    {
        this.jaxJNumber = jaxJNumber;
    }


    public String getACellID()
    {
        return aCellID;
    }

    public void setACellID(String ACellID)
    {
        aCellID = ACellID;
    }

    public String getAPubID()
    {
        return aPubID;
    }

    public void setAPubID(String APubID)
    {
        aPubID = APubID;
    }

    public String getATherapyID()
    {
        return aTherapyID;
    }

    public void setATherapyID(String ATherapyID)
    {
        aTherapyID = ATherapyID;
    }

    public void reset(ActionMapping mapping,
                      HttpServletRequest request)
    {
        authors = null;
        name = null;
        pmid = null;
        title = null;
        year = null;
        journal = null;
        volume = null;
        startPage = null;
        endPage = null;
        firstTimeReported = null;
        jaxJNumber = null;
        zfinPubId = null;   
        rgdPubId = null;
    }

	public String getZfinPubId() {
		return zfinPubId;
	}

	public void setZfinPubId(String zfinPubId) {
		this.zfinPubId = zfinPubId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRgdPubId() {
		return rgdPubId;
	}

	public void setRgdPubId(String rgdPubId) {
		this.rgdPubId = rgdPubId;
	}
}
