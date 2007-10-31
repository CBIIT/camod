/*
 * $Id: Disease.java,v 1.18 2007-10-31 15:33:31 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.17  2007/04/30 20:06:55  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 * Added uncontrolled vocab for disease name
 *
 * Revision 1.16  2006/10/17 16:14:36  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.15  2006/04/19 17:37:37  pandyas
 * Removed text
 *
 * Revision 1.14  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.*;
import java.io.Serializable;

/**
 * @author rajputs 
 */
public class Disease extends BaseObject implements Comparable, Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259515453799404851L;

    private String name;
    private String nameAlternEntry;    
    private String conceptCode;

    
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
     * @return Returns the conceptCode.
     */
    public String getConceptCode()
    {
        return conceptCode;
    }

    /**
     * @return Returns the EVS Preferred displayName
     * 		if the conceptCode = 000000, then return the name
     */
    public String getEVSPreferredDescription()
    {
        String thePreferedDesc = null;
        if ("000000".equals(conceptCode) || "C000000".equals(conceptCode))
        {
            thePreferedDesc = name;
        }
        else
        {
            thePreferedDesc = EvsTreeUtil.getEVSPreferedDescription(conceptCode);
        }
        return thePreferedDesc;
    }

    /**
     * @param conceptCode
     *            The conceptCode to set.
     */
    public void setConceptCode(String conceptCode)
    {
        this.conceptCode = conceptCode;
    }



    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getName();
        return result;
    }


    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final Disease obj = (Disease) o;
        if (HashCodeUtil.notEqual(this.getName(), obj.getName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getName());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof Disease) && (this.getName() != null) && (((Disease) o).getName() != null))
        {
            int result = this.getName().compareTo(((Disease) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }


	/**
	 * @return the nameAlternEntry
	 */
	public String getNameAlternEntry() {
		return nameAlternEntry;
	}

	/**
	 * @param nameAlternEntry the nameAlternEntry to set
	 */
	public void setNameAlternEntry(String nameAlternEntry) {
		this.nameAlternEntry = nameAlternEntry;
	}

}
