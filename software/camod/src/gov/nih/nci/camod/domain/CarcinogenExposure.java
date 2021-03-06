/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import java.io.Serializable;

public class CarcinogenExposure extends BaseObject implements Comparable, Serializable, Duplicatable
{
    private static final long serialVersionUID = 3258525453799404851L;

    private long absCancerModelId;
    private EnvironmentalFactor environmentalFactor;
    private Treatment treatment;
    private AbstractCancerModel cancerModel;

    /**
     * @return Returns the environmentalFactor.
     */
    public EnvironmentalFactor getEnvironmentalFactor()
    {
        return environmentalFactor;
    }

    /**
     * @param environmentalFactor
     *            The environmentalFactor to set.
     */
    public void setEnvironmentalFactor(EnvironmentalFactor environmentalFactor)
    {
        this.environmentalFactor = environmentalFactor;
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

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        return true;
    }

	/**
	 * @return the absCancerModelId
	 */
	public long getAbsCancerModelId() {
		return absCancerModelId;
	}

	/**
	 * @param absCancerModelId the absCancerModelId to set
	 */
	public void setAbsCancerModelId(long absCancerModelId) {
		this.absCancerModelId = absCancerModelId;
	}



}
