/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Id: Conditionality.java,v 1.9 2006-10-17 16:14:36 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2006/04/19 17:37:37  pandyas
 * Removed text
 *
 * Revision 1.7  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 */
public class Conditionality extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259575453799404851L;

    private String conditionedBy;
    private String description;

    /**
     * @return Returns the conditionedBy.
     */
    public String getConditionedBy()
    {
        return conditionedBy;
    }

    /**
     * @param conditionedBy
     *            The conditionedBy to set.
     */
    public void setConditionedBy(String conditionedBy)
    {
        this.conditionedBy = conditionedBy;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getDescription();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        return true;
    }
}
