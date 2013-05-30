/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Id: Species.java,v 1.8 2007-10-31 15:37:34 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2007/05/16 12:31:30  pandyas
 * Cleaned up unused code
 *
 * Revision 1.6  2006/05/23 18:15:58  georgeda
 * Added/cleaned up display name
 *
 * Revision 1.5  2006/05/10 14:13:51  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.4  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.HashCodeUtil;
import java.io.Serializable;
import java.util.*;


public class Species extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 3258615453799404851L;
    private String scientificName;
    private String scientificNameAlternEntry;
    private String commonName;
    private String commonNameAlternEntry;
    private String abbreviation;
    private String conceptCode;
    private Set<Strain> strainCollection = new HashSet<Strain>();


    /**
     * method to return the display name used in dropdown lists
     * @return Returns the display name
     */
    public String getDisplayName()
    {
        String theDisplayName = "";
        if (scientificName != null || commonName != null)
        {
            if (scientificName != null && commonName != null)
            {
                theDisplayName = commonName.trim() + " (" + scientificName.trim() + ")";
            }
            else if (scientificName != null)
            {
                theDisplayName = scientificName.trim();
            }
            else
            {
                theDisplayName = commonName.trim();
            }
        }
        else if (scientificNameAlternEntry != null || commonNameAlternEntry != null)
        {
            if (scientificNameAlternEntry != null)
            {
                theDisplayName = scientificNameAlternEntry.trim();
            }
            else
            {
                theDisplayName = commonNameAlternEntry.trim();
            }
        }
        return theDisplayName;
    }

    /**
     * @return Returns the scientificName.
     */
    public String getScientificName()
    {
        return scientificName;
    }

    /**
     * @param scientificName
     *            The scientificName to set.
     */
    public void setScientificName(String scientificName)
    {
        this.scientificName = scientificName;
    }

    /**
     * @return Returns the commonName.
     */
    public String getCommonName()
    {
        return commonName;
    }

    /**
     * @param commonName
     *            The commonName to set.
     */
    public void setCommonName(String commonName)
    {
        this.commonName = commonName;
    }

    /**
     * @return Returns the abbreviation.
     */
    public String getAbbreviation()
    {
        return abbreviation;
    }

    /**
     * @param abbreviation
     *            The abbreviation to set.
     */
    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }

    /**
     * @return Returns the conceptCode.
     */
    public String getConceptCode()
    {
        return conceptCode;
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
     * @return Returns the strainCollection.
     */
    public Set<Strain> getStrainCollection()
    {
        return strainCollection;
    }

    /**
     * @param strainCollection
     *            The strainCollection to set.
     */
    public void setStrainCollection(Set<Strain> strainCollection)
    {
        this.strainCollection = strainCollection;
    }

    public void addStrain(Strain strain)
    {
        strainCollection.add(strain);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getScientificName() + " - " + this.getCommonName();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final Species obj = (Species) o;
        if (HashCodeUtil.notEqual(this.getScientificName(), obj.getScientificName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getScientificName());
        return result + super.hashCode();
    }
    
    public int compareTo(Object o)
    {
        // compare by evs concept code
        if ((o instanceof Species) && (this.conceptCode != null) && (((Species) o).conceptCode != null))
        {
            int result = this.conceptCode.compareTo(((Species) o).conceptCode);
            if (result != 0)
            {
                return result;
            }
        }
        else if ((o instanceof Species) && (this.getScientificName() != null) && (((Species) o).getScientificName() != null))
        {
            int result = this.getScientificName().compareTo(((Species) o).getScientificName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }
    
    /**
	 * @return the scientificNameAlternEntry
	 */
	public String getScientificNameAlternEntry() {
		return scientificNameAlternEntry;
	}

	/**
	 * @param scientificNameAlternEntry the scientificNameAlternEntry to set
	 */
	public void setScientificNameAlternEntry(String scientificNameAlternEntry) {
		this.scientificNameAlternEntry = scientificNameAlternEntry;
	}

	/**
	 * @return the commonNameAlternEntry
	 */
	public String getCommonNameAlternEntry() {
		return commonNameAlternEntry;
	}

	/**
	 * @param commonNameAlternEntry the commonNameAlternEntry to set
	 */
	public void setCommonNameAlternEntry(String commonNameAlternEntry) {
		this.commonNameAlternEntry = commonNameAlternEntry;
	}
}
