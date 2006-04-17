/**
 * 
 * @author pandyas
 * 
 * $Id: ClinicalMarkerForm.java,v 1.7 2006-04-17 19:09:19 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/11/07 19:13:54  pandyas
 * modified for clinical marker screen
 *
 * Revision 1.5  2005/11/03 18:52:44  pandyas
 * Modified for histopathology screens
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;


/**
 * @author pandyas
 * 
 */
public class ClinicalMarkerForm extends BaseForm implements Serializable, ClinicalMarkerData
{

    private static final long serialVersionUID = 3257325453799404851L;

    /**
     * Default empty constructor
     * 
     * @author pandyas
     */
    public ClinicalMarkerForm()
    {}

    protected String histopathologyID;

    protected String name;
    protected String otherName;
    protected String value;


    /**
     * @return Returns the parent histopathologyID.
     */
    public String getHistopathologyID()
    {
        return histopathologyID;
    }

    /**
     * @param parent histopathologyID The histopathologyID to set.
     */
    public void setHistopathologyID(String histopathologyID)
    {
        this.histopathologyID = histopathologyID;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return Returns the otherName.
     */
    public String getOtherName()
    {
        return otherName;
    }

    /**
     * @param otherName
     *            The otherName to set.
     */
    public void setOtherName(String otherName)
    {
        this.otherName = otherName;
    }    

    /**
     * @return Returns the value.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(String value)
    {
        this.value = value;
    }
}
