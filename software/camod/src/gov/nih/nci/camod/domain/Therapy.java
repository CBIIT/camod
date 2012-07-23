/*
 * $Id: Therapy.java,v 1.16 2008-10-16 13:56:32 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.15  2007/05/16 12:28:43  pandyas
 * Added developmental stage evs tree to Therapy when species is Zebrafsih
 *
 * Revision 1.14  2007/03/19 18:56:11  pandyas
 * Object Model changes for caMOD 2.3 - dee design doc for details
 *
 * Revision 1.13  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.12  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;
import gov.nih.nci.camod.util.SafeHTMLUtil;

import java.io.Serializable;
import java.util.*;

/**
 * @author rajputs
 */
public class Therapy extends BaseObject implements Comparable, Serializable, Duplicatable
{
    private static final long serialVersionUID = 3258525453799404851L;

    private Agent agent;
    private Long absCancerModelId;
    private String experiment;
    private String results;
    private String toxicityGrade;
    private String biomarker;
    private String tumorResponse;
    private String comments;
    private DevelopmentalStage developmentalStage;      
    private Treatment treatment;
    private Set<Publication> publicationCollection = new TreeSet<Publication>();
    private AbstractCancerModel cancerModel;

    /**
     * @return Returns the agent.
     */
    public Agent getAgent()
    {
        return agent;
    }

    /**
     * @param agent
     *            The agent to set.
     */
    public void setAgent(Agent agent)
    {
        this.agent = agent;
    }

    /**
     * @return Returns the experiment.
     */
    public String getExperiment()
    {
        return SafeHTMLUtil.cleanMinimal(experiment);
    }

    /**
     * @param experiment
     *            The experiment to set.
     */
    public void setExperiment(String experiment)
    {
        this.experiment = experiment;
    }

    /**
     * @return Returns the biomarker.
     */
    public String getBiomarker()
    {
        return SafeHTMLUtil.cleanMinimal(biomarker);
    }

    /**
     * @param biomarker
     *            The biomarker to set.
     */
    public void setBiomarker(String biomarker)
    {
        this.biomarker = biomarker;
    }

    /**
     * @return Returns the toxicityGrade.
     */
    public String getToxicityGrade()
    {
        return SafeHTMLUtil.cleanMinimal(toxicityGrade);
    }

    /**
     * @param toxicityGrade
     *            The toxicityGrade to set.
     */
    public void setToxicityGrade(String toxicityGrade)
    {
        this.toxicityGrade = toxicityGrade;
    }

    /**
     * @return Returns the tumorResponse.
     */
    public String getTumorResponse()
    {
        return SafeHTMLUtil.cleanMinimal(tumorResponse);
    }

    /**
     * @param tumorResponse
     *            The tumorResponse to set.
     */
    public void setTumorResponse(String tumorResponse)
    {
        this.tumorResponse = tumorResponse;
    }

    /**
     * @return Returns the publicationCollection.
     */
    public Set getPublicationCollection()
    {
        return publicationCollection;
    }

    /**
     * @param publicationCollection
     *            The publicationCollection to set.
     */
    public void setPublicationCollection(Set<Publication> publicationCollection)
    {
        this.publicationCollection = publicationCollection;
    }

    /**
     * @param publication
     *            The publication to add.
     */
    public void addPublication(Publication publication)
    {
        publicationCollection.add(publication);
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
     * @return Returns the developmentalStage.
     */
    public DevelopmentalStage getDevelopmentalStage() {
        return developmentalStage;
    }

    /**
     * @param developmentalStage
     *            The developmentalStage to set.
     */
    public void setDevelopmentalStage(DevelopmentalStage developmentalStage) {
        this.developmentalStage = developmentalStage;
    } 
    
    /**
     * @return Returns the results.
     */
    public String getResults()
    {
        return SafeHTMLUtil.cleanMinimal(results);
    }

    /**
     * @param results
     *            The results to set.
     */
    public void setResults(String results)
    {
        this.results = results;
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
        result += this.getExperiment();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final Therapy obj = (Therapy) o;
        if (HashCodeUtil.notEqual(this.getAgent(), obj.getAgent()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getAgent());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof Therapy) && (this.getAgent() != null) && (((Therapy) o).getAgent() != null))
        {
            int result = this.getAgent().compareTo(((Therapy) o).getAgent());
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


}
