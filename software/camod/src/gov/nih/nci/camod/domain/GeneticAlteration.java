/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Id: GeneticAlteration.java,v 1.8 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 */
public class GeneticAlteration extends BaseObject implements Serializable, Duplicatable, Comparable
{
    private static final long serialVersionUID = 3259335453799404851L;

    private String observation;
    private String methodOfObservation;

    /**
     * @return Returns the methodOfObservation.
     */
    public String getMethodOfObservation()
    {
        return methodOfObservation;
    }

    /**
     * @param methodOfObservation
     *            The methodOfObservation to set.
     */
    public void setMethodOfObservation(String methodOfObservation)
    {
        this.methodOfObservation = methodOfObservation;
    }

    /**
     * @return Returns the observation.
     */
    public String getObservation()
    {
        return observation;
    }

    /**
     * @param observation
     *            The observation to set.
     */
    public void setObservation(String observation)
    {
        this.observation = observation;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getObservation() + " - " + this.getMethodOfObservation();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final GeneticAlteration obj = (GeneticAlteration) o;
        if (HashCodeUtil.notEqual(this.getObservation(), obj.getObservation()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getObservation());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof GeneticAlteration) && (this.getObservation() != null) && (((GeneticAlteration) o).getObservation() != null))
        {
            int result = this.getObservation().compareTo(((GeneticAlteration) o).getObservation());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }
}
