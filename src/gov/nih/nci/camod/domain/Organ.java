/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.EvsTreeUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Organ extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3259095453799404851L;
    
    private String name;
    private String conceptCode;
    private List histopathologyCollection = new ArrayList();

    /**
     * @return Returns the histopathologyCollection.
     */
    public List getHistopathologyCollection() {
        return histopathologyCollection;
    }

    /**
     * @param histopathologyCollection
     *            The histopathologyCollection to set.
     */
    public void setHistopathologyCollection(List histopathologyCollection) {
        this.histopathologyCollection = histopathologyCollection;
    }

    /**
     * @param histopatholgy
     *            The histopathology to add.
     */
    public void addHistopathology(Histopathology histopathology) {
        histopathology.setOrgan(this);
        histopathologyCollection.add(histopathology);
    }

    /**
     * @return Returns the conceptCode.
     */
    public String getConceptCode() {
        return conceptCode;
    }

    /**
     * @return Returns the EVS Preferred displayName
     */
    public String getEVSPreferredDescription() {
        return EvsTreeUtil.getEVSPreferedOrganDescription(conceptCode);
    }

    /**
     * @param conceptCode
     *            The conceptCode to set.
     */
    public void setConceptCode(String conceptCode) {
        this.conceptCode = conceptCode;
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
      return true;
    }
}
