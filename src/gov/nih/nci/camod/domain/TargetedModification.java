/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TargetedModification extends EngineeredGene {

    private static final long serialVersionUID = 3258565453799404851L;

    private String esCellLineName;
    private String blastocystName;
    private String geneId;
    private List modificationTypeCollection = new ArrayList();
    private String modTypeUnctrlVocab;

    /**
     * @return Returns the modificationTypeCollection.
     */
    public List getModificationTypeCollection() {        
        return modificationTypeCollection;           
    }

    public List getModificationTypeCollectionSorted() {      
      if (modificationTypeCollection != null) return new ArrayList(new TreeSet(modificationTypeCollection));
      return null;
    }   
    
    /**
     * @param modificationTypeCollection
     *            The modificationTypeCollection to set.
     */
    public void setModificationTypeCollection(List modificationTypeCollection) {
        this.modificationTypeCollection = modificationTypeCollection;
    }
    
    /**
     * @param modificationType
     *            The modificationType to add.
     */
    public void addModificationType(ModificationType modificationType) {
        modificationType.getTargetedModificationCollection().add(this);
        modificationTypeCollection.add(modificationType);
    }

    /**
     * @return Returns the modTypeUnctrlVocab.
     */
    public String getModTypeUnctrlVocab() {
        return modTypeUnctrlVocab;
    }

    /**
     * @param modTypeUnctrlVocab
     *            The modTypeUnctrlVocab to set.
     */
    public void setModTypeUnctrlVocab(String modTypeUnctrlVocab) {
        this.modTypeUnctrlVocab = modTypeUnctrlVocab;
    }

    /**
     * @return Returns the geneId.
     */
    public String getGeneId() {
        return geneId;
    }

    /**
     * @param geneId
     *            The geneId to set.
     */
    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    /**
     * @return Returns the blastocystName.
     */
    public String getBlastocystName() {
        return blastocystName;
    }

    /**
     * @param blastocystName
     *            The blastocystName to set.
     */
    public void setBlastocystName(String blastocystName) {
        this.blastocystName = blastocystName;
    }

    /**
     * @return Returns the esCellLineName.
     */
    public String getEsCellLineName() {
        return esCellLineName;
    }

    /**
     * @param esCellLineName
     *            The esCellLineName to set.
     */
    public void setEsCellLineName(String esCellLineName) {
        this.esCellLineName = esCellLineName;
    }

    /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getGeneId();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
