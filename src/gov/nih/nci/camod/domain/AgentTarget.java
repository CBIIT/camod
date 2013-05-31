/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Log: not supported by cvs2svn $
 * 
 * $Id: AgentTarget.java,v 1.6 2005-11-14 14:16:51 georgeda Exp $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;
import gov.nih.nci.camod.util.HashCodeUtil;

public class AgentTarget extends BaseObject implements Serializable, Comparable {

    private static final long serialVersionUID = 4259725453799404851L;
    
    private String targetName;
    private List agentCollection = new ArrayList();

    /**
     * @return Returns the agentCollection.
     */
    public List getAgentCollection() {
        return agentCollection;
    }
    
    public List getAgentCollectionSorted() {      
      if (agentCollection != null) return new ArrayList(new TreeSet(agentCollection));
      return null;
    }    

    /**
     * @param agentCollection
     *            The agentCollection to set.
     */
    public void setAgentCollection(List agentCollection) {
        this.agentCollection = agentCollection;
    }

    /**
     * @return Returns the targetName.
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * @param targetName
     *            The targetName to set.
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
  
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getTargetName();                
      return result;
    }           
    
     public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final AgentTarget obj = (AgentTarget) o;
      if (HashCodeUtil.notEqual(this.getTargetName(), obj.getTargetName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getTargetName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof AgentTarget) && (this.getTargetName() != null) && (((AgentTarget)o).getTargetName() != null)) {   
        int result = this.getTargetName().compareTo( ((AgentTarget)o).getTargetName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }      

}
