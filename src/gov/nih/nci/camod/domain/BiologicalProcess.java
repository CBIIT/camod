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
 * $Id: BiologicalProcess.java,v 1.6 2005-11-14 14:16:51 georgeda Exp $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import gov.nih.nci.camod.util.HashCodeUtil;

public class BiologicalProcess extends BaseObject implements Serializable, Comparable {

    private static final long serialVersionUID = 3259655453799404851L;
    
    private String processName;
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
     * @return Returns the processName.
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * @param processName
     *            The processName to set.
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    } 
 
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getProcessName();           
      return result;
    }     
          
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final BiologicalProcess obj = (BiologicalProcess) o;
      if (HashCodeUtil.notEqual(this.getProcessName(), obj.getProcessName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getProcessName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof BiologicalProcess) && (this.getProcessName() != null) && (((BiologicalProcess)o).getProcessName() != null)) {   
        int result = this.getProcessName().compareTo( ((BiologicalProcess)o).getProcessName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }      

}
