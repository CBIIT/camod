/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: Transplantation.java,v 1.3 2009-03-25 16:19:39 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2008/11/05 04:57:02  schroedn
 * gForge #17515 Query for Transplant TOC not returning correct results, fix
 *
 * Revision 1.1  2008/01/16 18:30:38  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.1  2007/10/31 15:58:56  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.1  2007/07/31 12:03:28  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.14  2007/04/04 13:17:05  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.13  2007/03/19 18:56:11  pandyas
 * Object Model changes for caMOD 2.3 - dee design doc for details
 *
 * Revision 1.12  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.11  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.10  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.9  2005/11/11 16:27:26  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.service.impl.QueryManagerSingleton;
import gov.nih.nci.camod.util.HashCodeUtil;
import gov.nih.nci.camod.util.SafeHTMLUtil;

import java.util.*;

/**
 * @author rajputs
 */
public class Transplantation extends AbstractCancerModel implements Comparable
{
    private static final long serialVersionUID = 3257445453799404851L;

    private String name;    
    private String geneticManipulation;
    private String modificationDescription;
    private String parentalCellLineName;
    private String atccNumber;
    private String cellAmount;
    private Long parAbsCanModelId;
    private String growthPeriod;
    private String sourceType;
    private String sourceTypeAlternEntry;
    private String administrativeSite;
    private String adminSiteAlternEntry;  
    private String conditioningRegimen;
    private String condRegimenAlternEntry;      
    private TumorCode tumorCode;
    private Set<InvivoResult> invivoResultCollection = new TreeSet<InvivoResult>();
    private Species donorSpecies;
    private Organ organ;
    private String comments;    

    
    /**
     * @return Returns the administrativeSite.
     */
    public String getAdministrativeSite()
    {
        return SafeHTMLUtil.cleanMinimal(administrativeSite);
    }

    /**
     * @param administrativeSite The administrativeSite to set.
     */
    public void setAdministrativeSite(String administrativeSite)
    {
        this.administrativeSite = administrativeSite;
    }
    
    /**
     * @return Returns the invivoResultCollection.
     * @throws Exception 
     */
    public List getInvivoResultCollectionByNSC(String inNSCNumber) throws Exception
    {
        return QueryManagerSingleton.instance().getInvivoResultCollectionByNSC(inNSCNumber, getId().toString());
    }

    /**
     * @return Returns the organ.
     */
    public Organ getOrgan()
    {
        return organ;
    }

    /**
     * @param organ The organ to set.
     */
    public void setOrgan(Organ organ)
    {
        this.organ = organ;
    }

    /**
     * @return Returns the donorSpecies.
     */
    public Species getDonorSpecies()
    {
        return donorSpecies;
    }

    /**
     * @param donorSpecies The donorSpecies to set.
     */
    public void setDonorSpecies(Species donorSpecies)
    {
        this.donorSpecies = donorSpecies;
    }

    /**
     * @return Returns the atccNumber.
     */
    public String getAtccNumber()
    {
        return SafeHTMLUtil.cleanMinimal(atccNumber);
    }

    /**
     * @param atccNumber The atccNumber to set.
     */
    public void setAtccNumber(String atccNumber)
    {
        this.atccNumber = atccNumber;
    }

    /**
     * @return Returns the cellAmount.
     */
    public String getCellAmount()
    {
        return SafeHTMLUtil.cleanMinimal(cellAmount);
    }

    /**
     * @param cellAmount The cellAmount to set.
     */
    public void setCellAmount(String cellAmount)
    {
        this.cellAmount = cellAmount;
    }

    /**
     * @return Returns the growthPeriod.
     */
    public String getGrowthPeriod()
    {
        return SafeHTMLUtil.cleanMinimal(growthPeriod);
    }

    /**
     * @param growthPeriod The growthPeriod to set.
     */
    public void setGrowthPeriod(String growthPeriod)
    {
        this.growthPeriod = growthPeriod;
    }

    /**
     * @return Returns the invivoResultCollection.
     */
    public Set getInvivoResultCollection()
    {
        return invivoResultCollection;
    }

    /**
     * @param invivoResultCollection The invivoResultCollection to set.
     */
    public void setInvivoResultCollection(Set<InvivoResult> invivoResultCollection)
    {
        this.invivoResultCollection = invivoResultCollection;
    }

    public void addInvivoResult(InvivoResult invivoResult)
    {
        invivoResultCollection.add(invivoResult);
    }

    /**
     * @return Returns the geneticManipulation.
     */
    public String getGeneticManipulation()
    {
        return SafeHTMLUtil.cleanMinimal(geneticManipulation);
    }

    /**
     * @param geneticManipulation The geneticManipulation to set.
     */
    public void setGeneticManipulation(String geneticManipulation)
    {
        this.geneticManipulation = geneticManipulation;
    }

    /**
     * @return Returns the modificationDescription.
     */
    public String getModificationDescription()
    {
        return SafeHTMLUtil.cleanMinimal(modificationDescription);
    }

    /**
     * @param modificationDescription The modificationDescription to set.
     */
    public void setModificationDescription(String modificationDescription)
    {
        this.modificationDescription = modificationDescription;
    }


    /**
     * @return Returns the parentalCellLineName.
     */
    public String getParentalCellLineName()
    {
        return SafeHTMLUtil.cleanMinimal(parentalCellLineName);
    }

    /**
     * @param parentalCellLineName The parentalCellLineName to set.
     */
    public void setParentalCellLineName(String parentalCellLineName)
    {
        this.parentalCellLineName = parentalCellLineName;
    }

    /**
     * @return Returns the tumorCode.
     */
    public TumorCode getTumorCode()
    {
        return tumorCode;
    }

    /**
     * @param tumorCode The tumorCode to set.
     */
    public void setTumorCode(TumorCode tumorCode)
    {
        this.tumorCode = tumorCode;
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
        final Transplantation obj = (Transplantation) o;
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
        if ((o instanceof Transplantation) && (this.getName() != null) && (((Transplantation) o).getName() != null))
        {
            int result = this.getName().compareTo(((Transplantation) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

    /**
     * @return Returns the conditioningRegimen.
     */
    public String getConditioningRegimen()
    {
        return SafeHTMLUtil.cleanMinimal(conditioningRegimen);
    }

    /**
     * @param conditioningRegimen The conditioningRegimen to set.
     */
    public void setConditioningRegimen(String conditioningRegimen)
    {
        this.conditioningRegimen = conditioningRegimen;
    }

	public String getName() {
		return SafeHTMLUtil.cleanMinimal(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceType() {
		return SafeHTMLUtil.cleanMinimal(sourceType);
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return SafeHTMLUtil.cleanMinimal(comments);
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the sourceTypeAlternEntry
	 */
	public String getSourceTypeAlternEntry() {
		return SafeHTMLUtil.cleanMinimal(sourceTypeAlternEntry);
	}

	/**
	 * @param sourceTypeAlternEntry the sourceTypeAlternEntry to set
	 */
	public void setSourceTypeAlternEntry(String sourceTypeAlternEntry) {
		this.sourceTypeAlternEntry = sourceTypeAlternEntry;
	}

	/**
	 * @return the adminSiteAlternEntry
	 */
	public String getAdminSiteAlternEntry() {
		return SafeHTMLUtil.cleanMinimal(adminSiteAlternEntry);
	}

	/**
	 * @param adminSiteAlternEntry the adminSiteAlternEntry to set
	 */
	public void setAdminSiteAlternEntry(String adminSiteAlternEntry) {
		this.adminSiteAlternEntry = adminSiteAlternEntry;
	}

	/**
	 * @return the condRegimenAlternEntry
	 */
	public String getCondRegimenAlternEntry() {
		return SafeHTMLUtil.cleanMinimal(condRegimenAlternEntry);
	}

	/**
	 * @param condRegimenAlternEntry the condRegimenAlternEntry to set
	 */
	public void setCondRegimenAlternEntry(String condRegimenAlternEntry) {
		this.condRegimenAlternEntry = condRegimenAlternEntry;
	}

	/**
	 * @return the parAbsCanModelId
	 */
	public Long getParAbsCanModelId() {
		return parAbsCanModelId;
	}

	/**
	 * @param parAbsCanModelId the parAbsCanModelId to set
	 */
	public void setParAbsCanModelId(Long parAbsCanModelId) {
		this.parAbsCanModelId = parAbsCanModelId;
	}


}
