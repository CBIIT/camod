/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.6  2005/11/14 14:16:51  georgeda
 * Cleanup
 *
 * 
 * $Id: ChemicalClass.java,v 1.8 2006-04-17 19:13:46 pandyas Exp $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.*;
import gov.nih.nci.camod.util.HashCodeUtil;

public class ChemicalClass extends BaseObject implements Serializable, Comparable {

    private static final long serialVersionUID = 3259635453799404851L;

    private String name;
    private Set<Agent> agentCollection = new TreeSet<Agent>();

    /**
     * @return Returns the agentCollection.
     */
    public Set<Agent> getAgentCollection() {
        return agentCollection;
    }
    /**
     * @param agentCollection
     *            The agentCollection to set.
     */
    public void setAgentCollection(Set<Agent> agentCollection) {
        this.agentCollection = agentCollection;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
 
  
    /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getName();           
       return result;
     }     
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final ChemicalClass obj = (ChemicalClass) o;
      if (HashCodeUtil.notEqual(this.getName(), obj.getName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof ChemicalClass) && (this.getName() != null) && (((ChemicalClass)o).getName() != null)) {   
        int result = this.getName().compareTo( ((ChemicalClass)o).getName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }      

}
