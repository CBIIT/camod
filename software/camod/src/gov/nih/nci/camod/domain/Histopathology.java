/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Id: Histopathology.java,v 1.13 2008-10-16 13:54:39 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2006/10/17 16:14:36  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.11  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;
import gov.nih.nci.camod.util.SafeHTMLUtil;

import java.io.Serializable;
import java.util.*;

public class Histopathology extends BaseObject implements Comparable, Serializable, Duplicatable
{

    private static final long serialVersionUID = 3259275453799404851L;

    private String comments;
    private String grossDescription;
    private String relationalOperation;
    private String tumorIncidenceRate;
    private Long absCancerModelId;
    private Long parentHistopathologyId;
    private String survivalInfo;
    private String ageOfOnset;
    private String ageOfOnsetUnit;
    private String ageOfDetection;
    private String ageOfDetectionUnit;    
    private String microscopicDescription;
    private String weightOfTumor;
    private String volumeOfTumor;
    private String comparativeData;
    private AbstractCancerModel cancerModel;    
    private Disease disease;
    private GeneticAlteration geneticAlteration;
    private Organ organ;
    private Set<ClinicalMarker> clinicalMarkerCollection = new TreeSet<ClinicalMarker>();    
    private Set<Histopathology> metastasisCollection = new TreeSet<Histopathology>();


    
    /**
     * @return Returns the metastasisCollection.
     */
    public Set<Histopathology> getMetastasisCollection()
    {
        return metastasisCollection;
    }
    /**
     * @param metastasisCollection
     *            The metastasisCollection to set.
     */
    public void setMetastasisCollection(Set<Histopathology> metastasisCollection)
    {
        this.metastasisCollection = metastasisCollection;
    }

    /**
     * @param histopathology
     *            The histopathology to add.
     */
    public void addMetastasis(Histopathology metastasis)
    {
        metastasisCollection.add(metastasis);
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
     * @return Returns the geneticAlteration.
     */
    public GeneticAlteration getGeneticAlteration()
    {
        return geneticAlteration;
    }

    /**
     * @param geneticAlteration
     *            The geneticAlteration to set.
     */
    public void setGeneticAlteration(GeneticAlteration geneticAlteration)
    {
        this.geneticAlteration = geneticAlteration;
    }

    /**
     * @return Returns the disease.
     */
    public Disease getDisease()
    {
        return disease;
    }

    public void setDisease(Disease disease)
    {
        this.disease = disease;
    }

    /**
     * @return Returns the clinicalMarkerCollection.
     */
    public Set<ClinicalMarker> getClinicalMarkerCollection()
    {
        return clinicalMarkerCollection;
    }

    /**
     * @param clinicalMarkerCollection
     *            The clinicalMarkerCollection to set.
     */
    public void setClinicalMarkerCollection(Set<ClinicalMarker> clinicalMarkerCollection)
    {
        this.clinicalMarkerCollection = clinicalMarkerCollection;
    }

    /**
     * @param clinicalMarker
     *            The clinicalMarker to add.
     */
    public void addClinicalMarker(ClinicalMarker clinicalMarker)
    {
        clinicalMarkerCollection.add(clinicalMarker);
    }

    /**
     * @return Returns the ageOfOnset.
     */
    public String getAgeOfOnset()
    {
        return SafeHTMLUtil.cleanMinimal(ageOfOnset);
    }

    /**
     * @param ageOfOnset
     *            The ageOfOnset to set.
     */
    public void setAgeOfOnset(String ageOfOnset)
    {
        this.ageOfOnset = ageOfOnset;
    }
    /**
     * @return Returns the ageOfOnsetUnit.
     */
    public String getAgeOfOnsetUnit()
    {
        return SafeHTMLUtil.cleanMinimal(ageOfOnsetUnit);
    }

    /**
     * @param ageOfOnsetUnit
     *            The ageOfOnsetUnit to set.
     */
    public void setAgeOfOnsetUnit(String ageOfOnsetUnit)
    {
        this.ageOfOnsetUnit = ageOfOnsetUnit;
    }
    
    /**
     * @return Returns the ageOfDetection.
     */
    public String getAgeOfDetection()
    {
        return SafeHTMLUtil.cleanMinimal(ageOfDetection);
    }

    /**
     * @param ageOfDetection
     *            The ageOfDetection to set.
     */
    public void setAgeOfDetection(String ageOfDetection)
    {
        this.ageOfDetection = ageOfDetection;
    }
    /**
     * @return Returns the ageOfDetectionUnit.
     */
    public String getAgeOfDetectionUnit()
    {
        return SafeHTMLUtil.cleanMinimal(ageOfDetectionUnit);
    }

    /**
     * @param ageOfDetectionUnit
     *            The ageOfDetectionUnit to set.
     */
    public void setAgeOfDetectionUnit(String ageOfDetectionUnit)
    {
        this.ageOfDetectionUnit = ageOfDetectionUnit;
    }   
    /**
     * @return Returns the comments.  Comment is a reserved word so 
     * we must use the plural form comments.
     */
    public String getComments()
    {
        return SafeHTMLUtil.cleanMinimal(comments);
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /**
     * @return Returns the comparativeData.
     */
    public String getComparativeData()
    {
        return SafeHTMLUtil.cleanMinimal(comparativeData);
    }

    /**
     * @param comparativeData
     *            The comparativeData to set.
     */
    public void setComparativeData(String comparativeData)
    {
        this.comparativeData = comparativeData;
    }

    /**
     * @return Returns the grossDescription.
     */
    public String getGrossDescription()
    {
        return SafeHTMLUtil.cleanMinimal(grossDescription);
    }

    /**
     * @param grossDescription
     *            The grossDescription to set.
     */
    public void setGrossDescription(String grossDescription)
    {
        this.grossDescription = grossDescription;
    }


    /**
     * @return Returns the microscopicDescription.
     */
    public String getMicroscopicDescription()
    {
        return SafeHTMLUtil.cleanMinimal(microscopicDescription);
    }

    /**
     * @param microscopicDescription
     *            The microscopicDescription to set.
     */
    public void setMicroscopicDescription(String microscopicDescription)
    {
        this.microscopicDescription = microscopicDescription;
    }

    /**
     * @return Returns the relationalOperation.
     */
    public String getRelationalOperation()
    {
        return SafeHTMLUtil.cleanMinimal(relationalOperation);
    }

    /**
     * @param relationalOperation
     *            The relationalOperation to set.
     */
    public void setRelationalOperation(String relationalOperation)
    {
        this.relationalOperation = relationalOperation;
    }

    /**
     * @return Returns the survivalInfo.
     */
    public String getSurvivalInfo()
    {
        return SafeHTMLUtil.cleanMinimal(survivalInfo);
    }

    /**
     * @param survivalInfo
     *            The survivalInfo to set.
     */
    public void setSurvivalInfo(String survivalInfo)
    {
        this.survivalInfo = survivalInfo;
    }

    /**
     * @return Returns the tumorIncidenceRate.
     */
    public String getTumorIncidenceRate()
    {
        return SafeHTMLUtil.cleanMinimal(tumorIncidenceRate);
    }

    /**
     * @param tumorIncidenceRate
     *            The tumorIncidenceRate to set.
     */
    public void setTumorIncidenceRate(String tumorIncidenceRate)
    {
        this.tumorIncidenceRate = tumorIncidenceRate;
    }

    /**
     * @return Returns the volumeOfTumor.
     */
    public String getVolumeOfTumor()
    {
        return SafeHTMLUtil.cleanMinimal(volumeOfTumor);
    }

    /**
     * @param volumeOfTumor
     *            The volumeOfTumor to set.
     */
    public void setVolumeOfTumor(String volumeOfTumor)
    {
        this.volumeOfTumor = volumeOfTumor;
    }

    /**
     * @return Returns the weightOfTumor.
     */
    public String getWeightOfTumor()
    {
        return SafeHTMLUtil.cleanMinimal(weightOfTumor);
    }

    /**
     * @param weightOfTumor
     *            The weightOfTumor to set.
     */
    public void setWeightOfTumor(String weightOfTumor)
    {
        this.weightOfTumor = weightOfTumor;
    }

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
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getGrossDescription() + " - " + this.getComments();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final Histopathology obj = (Histopathology) o;
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
        if ((o instanceof Histopathology) && (this.getOrgan() != null) && (((Histopathology) o).getOrgan() != null))
        {
            int result = this.getOrgan().compareTo(((Histopathology) o).getOrgan());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }
	/**
	 * @return the absCancerModelId
	 */
	public Long getAbsCancerModelId() {
		return absCancerModelId;
	}
	/**
	 * @param absCancerModelId the absCancerModelId to set
	 */
	public void setAbsCancerModelId(Long absCancerModelId) {
		this.absCancerModelId = absCancerModelId;
	}
	/**
	 * @return the parentHistopathologyId
	 */
	public Long getParentHistopathologyId() {
		return parentHistopathologyId;
	}
	/**
	 * @param parentHistopathologyId the parentHistopathologyId to set
	 */
	public void setParentHistopathologyId(Long parentHistopathologyId) {
		this.parentHistopathologyId = parentHistopathologyId;
	}


}
