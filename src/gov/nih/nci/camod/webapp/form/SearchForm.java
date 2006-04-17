/**
 * $Id: SearchForm.java,v 1.8 2006-04-17 19:09:19 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/11/16 19:43:30  georgeda
 * Added clear to search forms
 *
 * Revision 1.6  2005/11/07 16:54:51  georgeda
 * Fixed problem w/ advanced search options being used in simple search
 *
 * Revision 1.5  2005/10/20 19:29:32  georgeda
 * Added xenograft search functionality
 *
 * Revision 1.4  2005/10/19 20:19:26  georgeda
 * Cleanup
 *
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class SearchForm extends BaseForm implements Serializable, SearchData {

    private static final long serialVersionUID = 3257045453799404851L;

    protected String keyword;
    protected String piName;
    protected String modelDescriptor;
    protected String organ;
    protected String species;
    protected String chemicalDrug;
    protected String comment;
    protected String hormone;
    protected String growthFactor;
    protected String radiation;
    protected String viral;
    protected String surgery;
    protected String phenotype;
    protected String disease;
    protected String cellLine;
    protected String organTissueCode;
    protected String organTissueName;
    protected String diagnosisCode;
    protected String diagnosisName;
    protected String inducedMutationAgent;
    protected String geneName;
    protected String genomicSegDesignator;
    protected String therapeuticApproach;
    protected boolean searchCarcinogenicInterventions = false;
    protected boolean searchTherapeuticApproaches = false;
    protected boolean engineeredTransgene = false;
    protected boolean targetedModification = false;
    protected boolean searchHistoMetastasis = false;
    protected boolean searchMicroArrayData = false;
    protected boolean searchXenograft = false;

    public void setHormone(String hormone) {
        this.hormone = hormone;
    }

    public void setGrowthFactor(String growthFactor) {
        this.growthFactor = growthFactor;
    }

    public String getChemicalDrug() {
        return chemicalDrug;
    }

    public void setChemicalDrug(String chemicalDrug) {
        this.chemicalDrug = chemicalDrug;
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }    

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getModelDescriptor() {
        return modelDescriptor;
    }

    public void setModelDescriptor(String modelDescriptor) {
        this.modelDescriptor = modelDescriptor;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getPiName() {
        return piName;
    }

    public void setPiName(String piName) {
        this.piName = piName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGrowthFactor() {
        return growthFactor;
    }

    public String getHormone() {
        return hormone;
    }

    public String getRadiation() {
        return radiation;
    }

    public void setRadiation(String radiation) {
        this.radiation = radiation;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getViral() {
        return viral;
    }

    public void setViral(String viral) {
        this.viral = viral;
    }

    public String getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(String phenotype) {
        this.phenotype = phenotype;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getCellLine() {
        return cellLine;
    }

    public void setCellLine(String cellLine) {
        this.cellLine = cellLine;
    }

    public String getOrganTissueCode() {
        return organTissueCode;
    }

    public void setOrganTissueCode(String organTissueCode) {
        this.organTissueCode = organTissueCode;
    }

    public String getOrganTissueName() {
        return organTissueName;
    }

    public void setOrganTissueName(String organTissueName) {
        this.organTissueName = organTissueName;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public boolean isSearchCarcinogenicInterventions() {
        return searchCarcinogenicInterventions;
    }

    public void setSearchCarcinogenicInterventions(boolean searchCarcinogenicInterventions) {
        this.searchCarcinogenicInterventions = searchCarcinogenicInterventions;
    }

    public String getInducedMutationAgent() {
        return inducedMutationAgent;
    }

    public void setInducedMutationAgent(String inducedMutationAgent) {
        this.inducedMutationAgent = inducedMutationAgent;
    }

    public boolean isEngineeredTransgene() {
        return engineeredTransgene;
    }

    public void setEngineeredTransgene(boolean engineeredTransgene) {
        this.engineeredTransgene = engineeredTransgene;
    }

    public boolean isTargetedModification() {
        return targetedModification;
    }

    public void setTargetedModification(boolean targetedModification) {
        this.targetedModification = targetedModification;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public String getGenomicSegDesignator() {
        return genomicSegDesignator;
    }

    public void setGenomicSegDesignator(String genomicSegDesignator) {
        this.genomicSegDesignator = genomicSegDesignator;
    }

    public boolean isSearchTherapeuticApproaches() {
        return searchTherapeuticApproaches;
    }

    public void setSearchTherapeuticApproaches(boolean searchTherapeuticApproaches) {
        this.searchTherapeuticApproaches = searchTherapeuticApproaches;
    }

    public String getTherapeuticApproach() {
        return therapeuticApproach;
    }

    public void setTherapeuticApproach(String therapeuticApproach) {
        this.therapeuticApproach = therapeuticApproach;
    }

    public boolean isSearchHistoMetastasis() {
        return searchHistoMetastasis;
    }

    public void setSearchHistoMetastasis(boolean searchHistoMetastasis) {
        this.searchHistoMetastasis = searchHistoMetastasis;
    }

    public boolean isSearchMicroArrayData() {
        return searchMicroArrayData;
    }

    public void setSearchMicroArrayData(boolean searchMicroArrayData) {
        this.searchMicroArrayData = searchMicroArrayData;
    }

    public boolean isSearchXenograft() {
        return searchXenograft;
    }

    public void setSearchXenograft(boolean searchXenograft) {
        this.searchXenograft = searchXenograft;
    }

    /**
     * Reset all fields that are not used in the simple search. Since the form
     * is used for both the simple and advanced search and is stored in the
     * session to allow users to quickly use the back arrow to refine their
     * search we need to make sure that when the user clicks on the simple
     * search page the options from the advanced search page are reset.
     * 
     */
    public void simpleSearchReset() {

        chemicalDrug = null;
        hormone = null;
        growthFactor = null;
        radiation = null;
        viral = null;
        surgery = null;
        phenotype = null;
        disease = null;
        cellLine = null;
        diagnosisCode = null;
        diagnosisName = null;
        inducedMutationAgent = null;
        geneName = null;
        genomicSegDesignator = null;
        therapeuticApproach = null;
        searchCarcinogenicInterventions = false;
        searchTherapeuticApproaches = false;
        engineeredTransgene = false;
        targetedModification = false;
        searchHistoMetastasis = false;
        searchMicroArrayData = false;
        searchXenograft = false;
    }
    
    /**
     * Reset all fields.
     */
    public void allFieldsReset() {

        keyword = null;
        piName = null;
        modelDescriptor = null;
        organ = null;
        species = null;
        
        simpleSearchReset();
    }
}
