/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: GeneDelivery.java,v 1.14 2007-10-31 15:53:15 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.12  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.11  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.10  2005/11/28 20:20:46  pandyas
 * added java docs
 *
 * 
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;
import gov.nih.nci.camod.util.SafeHTMLUtil;

import java.io.Serializable;

/**
 * @author rajputs
 */
public class GeneDelivery extends BaseObject implements Comparable, Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259385453799404851L;
    
    private AbstractCancerModel cancerModel;
    private String geneInVirus;
    private String viralVector;
    private String viralVectorAlternEntry;    
    private Organ organ;
    private Treatment treatment;
    private String comments;    

    /**
     * @return Returns the cancerModel.
     */
    public AbstractCancerModel getCancerModel()
    {
        return cancerModel;
    }

    /**
     * @param cancerModel
     *            The cancerModel to set.
     */
    public void setCancerModel(AbstractCancerModel cancerModel)
    {
        this.cancerModel = cancerModel;
    }    


    /**
     * @return Returns the geneInVirus.
     */
    public String getGeneInVirus()
    {
        return SafeHTMLUtil.cleanMinimal(geneInVirus);
    }

    /**
     * @param geneInVirus
     *            The geneInVirus to set.
     */
    public void setGeneInVirus(String geneInVirus)
    {
        this.geneInVirus = geneInVirus;
    }

    /**
     * @return Returns the viralVector.
     */
    public String getViralVector()
    {
        return SafeHTMLUtil.cleanMinimal(viralVector);
    }

    /**
     * @param viralVector
     *            The viralVector to set.
     */
    public void setViralVector(String viralVector)
    {
        this.viralVector = viralVector;
    }


    /**
     * @return Returns the organ.
     */
    public Organ getOrgan()
    {
        return organ;
    }

    /**
     * @param organ
     *            The organ to set.
     */
    public void setOrgan(Organ organ)
    {
        this.organ = organ;
    }
    /**
     * @return Returns the treatment.
     */
    public Treatment getTreatment()
    {
        return treatment;
    }

    /**
     * @param treatment
     *            The treatment to set.
     */
    public void setTreatment(Treatment treatment)
    {
        this.treatment = treatment;
    }  
    
    /**
     * @return Returns the comments. Comment is a reserved word so 
     * we must use the plural form comments.
     */
    public String getComments() {
        return SafeHTMLUtil.cleanMinimal(comments);
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getViralVector() + " - " + this.getViralVectorAlternEntry() + " - " + this.getGeneInVirus();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final GeneDelivery obj = (GeneDelivery) o;
        if (HashCodeUtil.notEqual(this.getOrgan(), obj.getOrgan()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getOrgan());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof GeneDelivery) && (this.getOrgan() != null) && (((GeneDelivery) o).getOrgan() != null))
        {
            int result = this.getOrgan().compareTo(((GeneDelivery) o).getOrgan());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

	/**
	 * @return the viralVectorAlternEntry
	 */
	public String getViralVectorAlternEntry() {
		return SafeHTMLUtil.cleanMinimal(viralVectorAlternEntry);
	}

	/**
	 * @param viralVectorAlternEntry the viralVectorAlternEntry to set
	 */
	public void setViralVectorAlternEntry(String viralVectorAlternEntry) {
		this.viralVectorAlternEntry = viralVectorAlternEntry;
	}
	
    /**
     * @return Returns the display name.
     */
    public String getDisplayName()
    {
        String theDisplayName = viralVector;
        if (theDisplayName == null && viralVectorAlternEntry != null)
        {
            theDisplayName = "Other - " + viralVectorAlternEntry;
        }
        return SafeHTMLUtil.cleanMinimal(theDisplayName);
    }	

}
