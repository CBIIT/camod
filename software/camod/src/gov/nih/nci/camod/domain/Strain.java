/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Id: Strain.java,v 1.8 2007-10-31 15:39:32 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2007/05/16 12:31:31  pandyas
 * Cleaned up unused code
 *
 * Revision 1.6  2006/05/23 18:15:58  georgeda
 * Added/cleaned up display name
 *
 * Revision 1.5  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.4  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.HashCodeUtil;
import java.io.Serializable;


public class Strain extends BaseObject implements Serializable
{

    private static final long serialVersionUID = 3258615453799404851L;

    private String name;
    private String nameAlternEntry;
    private String abbreviation;
    private String conceptCode;
    private MutationIdentifier mutationIdentifier;
    private Species species;


    /**
     * method to return the display name
     * @return Returns the display name
     */
    public String getDisplayName()
    {
        String theDisplayName = "";
        if (name != null)
        {
            theDisplayName = name.trim();
        }
        else if (nameAlternEntry != null)
        {
            theDisplayName = nameAlternEntry.trim();
        }
        return theDisplayName;
    }

    /**
     * @return Returns the species.
     */
    public Species getSpecies()
    {
        return species;
    }

    /**
     * @param species
     *            The species to set.
     */
    public void setSpecies(Species species)
    {
        this.species = species;
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
     * @return Returns the mutationIdentifier.
     */
    public MutationIdentifier getMutationIdentifier()
    {
        return mutationIdentifier;
    }

    /**
     * @param mutationIdentifier
     *            The mutationIdentifier to set.
     */
    public void setMutationIdentifier(MutationIdentifier mutationIdentifier)
    {
        this.mutationIdentifier = mutationIdentifier;
    }

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
        final Strain obj = (Strain) o;
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
        // compare by evs concept code
        if ((o instanceof Strain) && (this.conceptCode != null) && (((Strain) o).conceptCode != null))
        {
            int result = this.conceptCode.compareTo(((Strain) o).conceptCode);
            if (result != 0)
            {
                return result;
            }
        }
        else if ((o instanceof Strain) && (this.getName() != null) && (((Strain) o).getName() != null))
        {
            int result = this.getName().compareTo(((Strain) o).getName());
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
