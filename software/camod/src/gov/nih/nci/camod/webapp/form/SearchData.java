/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 *   
 * $Id: SearchData.java,v 1.19 2009-03-25 16:25:13 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.18  2008/08/12 20:14:28  pandyas
 * Code was rolled back to continue work on security scan fixes.  Code added back in jsp again.  Originally From:
 * Revision 1.15  2008/07/11 17:18:19  schroedn
 * Bug 11007
 * Added search for PMID numbers
 *
 * Revision 1.17  2008/08/12 19:40:32  pandyas
 * Fixed #15053  	Search for models with transgenic or targeted modification on advanced search page confusing
 *
 * Revision 1.16  2008/07/17 17:54:25  pandyas
 * Reverted code back to version for security scan fixes
 *
 * Revision 1.14  2008/01/16 18:29:46  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.13  2007/10/31 17:40:09  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.12  2007/07/31 12:01:59  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.11  2007/03/28 18:13:40  pandyas
 * Modified for the following Test Track items:
 * #462 - Customized search for carcinogens for Jackson Lab data
 * #494 - Advanced search for Carcinogens for Jackson Lab data
 *
 * Revision 1.10  2006/12/28 16:03:41  pandyas
 * Reverted to previous version - changed CE on adv search page
 *
 * Revision 1.8  2006/10/17 16:10:47  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.7  2006/05/10 12:02:12  georgeda
 * Changes for searching on transient interfaces
 *
 * Revision 1.6  2006/04/28 19:30:51  schroedn
 * Defect # 261
 * Added Tumor Classification, so to save the organ properly
 *
 * Revision 1.5  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.webapp.form;

public interface SearchData {

	public String getKeyword();

	public void setKeyword(String k);

	public void setPiName(String p);

	public String getPiName();

	public String getModelDescriptor();

	public void setModelDescriptor(String m);

	public String getPmid();
	
	public void setPmid(String p);
	
	public String getOrgan();

	public void setOrgan(String o);

	public String getDisease();

	public void setDisease(String o);

	public String getSpecies();

	public void setSpecies(String s);

	public String getPhenotype();

	public void setPhenotype(String s);

	public String getCellLine();

	public void setCellLine(String s);

	// Carciogenic interventions
	public String getChemicalDrug();

	public void setChemicalDrug(String s);

	public String getGrowthFactor();

	public void setGrowthFactor(String s);

	public String getHormone();

	public void setHormone(String s);

	public String getRadiation();

	public void setRadiation(String s);

	public String getViral();

	public void setViral(String s);

	public String getSurgery();

	public void setSurgery(String s);

	public void setOrganTissueCode(String s);

	public String getOrganTissueCode();

	public void setOrganTissueName(String s);

	public String getOrganTissueName();

	public void setDiagnosisCode(String s);

	public String getDiagnosisCode();

	public void setDiagnosisName(String s);

	public String getDiagnosisName();

	// Carciogenic interventions
    public String getCarcinogenicIntervention();

    public void setCarcinogenicIntervention(String carcinogenicIntervention);
    
    public String getAgentName();

    public void setAgentName(String agentName); 

	public void setInducedMutationAgent(String s);

	public String getInducedMutationAgent();

	public void setGeneName(String s);

	public String getGeneName();
	
	public String getTransgeneName(); 

	public void setTransgeneName(String transgeneName);	

	public void setGenomicSegDesignator(String s);

	public String getGenomicSegDesignator();
	
	public boolean isSearchEngineeredTransgene();
	
	public void setSearchEngineeredTransgene(boolean searchEngineeredTransgene);

	public boolean isSearchTargetedModification(); 

	public void setSearchTargetedModification(boolean searchTargetedModification);	

	public boolean isSearchTherapeuticApproaches();

	public void setSearchTherapeuticApproaches(boolean b);

	public void setTherapeuticApproach(String s);

	public String getTherapeuticApproach();

	public boolean isSearchHistoMetastasis();

	public void setSearchHistoMetastasis(boolean b);

	public boolean isSearchMicroArrayData();

	public void setSearchMicroArrayData(boolean b);
	
	public boolean isSearchImageData();

	public void setSearchImageData(boolean b);	

	public boolean isSearchToolStrain();

	public void setSearchToolStrain(boolean searchToolStrain);

	public String getExternalSource();

	public void setExternalSource(String externalSource);

	public boolean isSearchTransplantation();

	public void setSearchTransplantation(boolean b);

	public String getTumorClassification();

	public void setTumorClassification(String tumorClassification);

	public boolean isSearchTransientInterference();

	public void setSearchTransientInterference(
			boolean searchTransientInterference);
	
}
