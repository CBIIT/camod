/**
 *   The caMOD Software License, Version 1.0
 *
 *   Copyright 2005-2006 SAIC. This software was developed in conjunction with the National Cancer
 *   Institute, and so to the extent government employees are co-authors, any rights in such works
 *   shall be subject to Title 17 of the United States Code, section 105.
 *
 *   Redistribution and use in source and binary forms, with or without modification, are permitted
 *   provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, this list of conditions
 *   and the disclaimer of Article 3, below.  Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 *   2.  The end-user documentation included with the redistribution, if any, must include the
 *   following acknowledgment:
 *
 *   "This product includes software developed by the SAIC and the National Cancer
 *   Institute."
 *
 *   If no such end-user documentation is to be included, this acknowledgment shall appear in the
 *   software itself, wherever such third-party acknowledgments normally appear.
 *
 *   3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or
 *   promote products derived from this software.
 *
 *   4. This license does not authorize the incorporation of this software into any third party proprietary
 *   programs.  This license does not authorize the recipient to use any trademarks owned by either
 *   NCI or SAIC.
 *
 *
 *   5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED
 *   WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE
 *   DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR
 *   THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *   EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *   PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *   PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 *   OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *   NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *   
 * $Id: SearchForm.java,v 1.34 2008-05-21 19:07:33 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.33  2008/05/12 16:33:52  pandyas
 * REmoved test for letter or digit for organ and tumorClassification - EVS returns results with special characters therefore this can not be validated against malicious characters (special characters) for the security scan
 * We need to obtain an exception
 *
 * Revision 1.32  2008/05/05 15:07:00  pandyas
 * NCI security scan changes
 *
 * Revision 1.31  2008/02/21 21:52:26  pandyas
 * Final version for production
 *
 * Revision 1.30  2008/02/20 21:51:47  pandyas
 * Added code to eliminate blind SQL injection in carcinogenicIntervention parameter on Adv Search screen:
 * "Filter out hazardous characters from user input (High) Parameter: carcinogenicIntervention - Blind SQL Injection"
 *
 * Revision 1.29  2008/02/18 15:36:37  pandyas
 * Modified for annoying functionality with clear button not working
 *
 * Revision 1.28  2008/02/15 16:06:37  pandyas
 * Added code for SQL injection - App Scan report
 *
 * Revision 1.27  2008/01/22 17:21:24  pandyas
 * valid species - comment out
 *
 * Revision 1.26  2008/01/16 18:29:46  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.25  2007/10/31 17:42:28  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.24  2007/10/26 14:02:57  pandyas
 * ready for next build
 *
 * Revision 1.23  2007/10/25 16:16:13  pandyas
 * commented out system statments for build to stage
 *
 * Revision 1.22  2007/10/25 16:01:40  pandyas
 * Added validation for text fields to prevent Blind SQL injection attacks following the AppScan report findings
 *
 * Revision 1.21  2007/10/18 18:27:28  pandyas
 * Modified to prevent cross--site scripting attacks
 *
 * Revision 1.20  2007/10/17 18:29:28  pandyas
 * commented out PI until it works
 *
 * Revision 1.19  2007/10/17 18:23:40  pandyas
 * Modified to prevent cross--site scripting attacks - initial version
 *
 * Revision 1.18  2007/07/31 12:02:04  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.17  2007/03/28 18:16:23  pandyas
 * Modified for the following Test Track items:
 * #462 - Customized search for carcinogens for Jackson Lab data
 * #494 - Advanced search for Carcinogens for Jackson Lab data
 *
 * Revision 1.16  2006/12/28 16:04:00  pandyas
 * Reverted to previous version - changed CE on adv search page
 *
 * Revision 1.14  2006/11/13 16:51:59  pandyas
 * #467 - Clear button on advanced search page doesn't work
 *
 * Revision 1.13  2006/10/17 16:10:47  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.12  2006/05/10 14:25:10  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.11  2006/05/10 13:39:56  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.10  2006/05/10 12:02:12  georgeda
 * Changes for searching on transient interfaces
 *
 * Revision 1.9  2006/04/28 19:30:51  schroedn
 * Defect # 261
 * Added Tumor Classification, so to save the organ properly
 *
 * Revision 1.8  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
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

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.util.NameValue;
import gov.nih.nci.camod.util.NameValueList;
import gov.nih.nci.camod.util.SafeHTMLUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.Globals;
import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;


public class SearchForm extends ActionForm implements Serializable, SearchData
{
    private static final long serialVersionUID = 3257045453799404851L;
    
    protected String keyword;
    protected String piName;
    protected String modelDescriptor;
    protected String organ;
    protected String species;
    protected String externalSource;    
    protected String chemicalDrug;
    protected String comment;
    protected String hormone;
    protected String growthFactor;
    protected String radiation;
    protected String viral;
    protected String surgery;
    protected String phenotype;
    protected String disease;
    protected String tumorClassification;
    protected String cellLine;
    protected String organTissueCode;
    protected String organTissueName;
    protected String diagnosisCode;
    protected String diagnosisName;
    protected String inducedMutationAgent;
    protected String geneName;
    protected String genomicSegDesignator;
    protected String therapeuticApproach;
    protected String carcinogenicIntervention;
    protected String agentName;
    protected boolean searchTherapeuticApproaches = false;
    protected boolean engineeredTransgene = false;
    protected boolean targetedModification = false;
    protected boolean searchHistoMetastasis = false;
    protected boolean searchMicroArrayData = false;
    protected boolean searchImageData = false;    
    protected boolean searchTransplant = false;
    protected boolean searchTransientInterference = false;
    protected boolean searchToolStrain = false; 
    
    public SearchForm()
    {
    }    

    public void setHormone(String hormone)
    {
        this.hormone = hormone;
    }

    public void setGrowthFactor(String growthFactor)
    {
        this.growthFactor = growthFactor;
    }

    public String getChemicalDrug()
    {
        return chemicalDrug;
    }

    public void setChemicalDrug(String chemicalDrug)
    {
        this.chemicalDrug = chemicalDrug;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public String getModelDescriptor()
    {
        return modelDescriptor;
    }

    public void setModelDescriptor(String modelDescriptor)
    {
        this.modelDescriptor = modelDescriptor;
    }

    public String getOrgan()
    {
        return organ;
    }

    public void setOrgan(String organ)
    {
        this.organ = organ;
        // Clean the parameter
        if (this.organ != null && !this.organ.equals(""))  {
                this.organ = SafeHTMLUtil.clean(this.organ);
        }
    }
    
    public String getPiName() {
        return piName;
    }

    public void setPiName(String piName)
    {
        this.piName = piName;
    }

    public String getSpecies()
    {
        return species;
    }

    public void setSpecies(String species)
    {
        this.species = species;
    }
    
    public String getExternalSource()
    {
        return externalSource;
    }

    public void setExternalSource(String externalSource)
    {
        this.externalSource = externalSource;
    }    

    public String getGrowthFactor()
    {
        return growthFactor;
    }

    public String getHormone()
    {
        return hormone;
    }

    public String getRadiation()
    {
        return radiation;
    }

    public void setRadiation(String radiation)
    {
        this.radiation = radiation;
    }

    public String getSurgery()
    {
        return surgery;
    }

    public void setSurgery(String surgery)
    {
        this.surgery = surgery;
    }

    public String getViral()
    {
        return viral;
    }

    public void setViral(String viral)
    {
        this.viral = viral;
    }

    public String getPhenotype()
    {
        return phenotype;
    }

    public void setPhenotype(String phenotype)
    {
        this.phenotype = phenotype;
    }

    public String getDisease()
    {
        return disease;
    }

    public void setDisease(String disease)
    {
        this.disease = disease;
    }

    public String getCellLine()
    {
        return cellLine;
    }

    public void setCellLine(String cellLine)
    {
        this.cellLine = cellLine;
    }

    public String getOrganTissueCode()
    {
        return organTissueCode;
    }

    public void setOrganTissueCode(String organTissueCode)
    {
        this.organTissueCode = organTissueCode;
    }

    public String getOrganTissueName()
    {
        return organTissueName;
    }

    public void setOrganTissueName(String organTissueName)
    {
        this.organTissueName = organTissueName;
    }

    public String getDiagnosisCode()
    {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode)
    {
        this.diagnosisCode = diagnosisCode;
    }

    public String getDiagnosisName()
    {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName)
    {
        this.diagnosisName = diagnosisName;
    }

    public String getInducedMutationAgent()
    {
        return inducedMutationAgent;
    }

    public void setInducedMutationAgent(String inducedMutationAgent)
    {
        this.inducedMutationAgent = inducedMutationAgent;
    }

    public boolean isEngineeredTransgene()
    {
        return engineeredTransgene;
    }

    public void setEngineeredTransgene(boolean engineeredTransgene)
    {
        this.engineeredTransgene = engineeredTransgene;
    }

    public boolean isTargetedModification()
    {
        return targetedModification;
    }

    public void setTargetedModification(boolean targetedModification)
    {
        this.targetedModification = targetedModification;
    }

    public String getGeneName()
    {
        return geneName;
    }

    public void setGeneName(String geneName)
    {
        this.geneName = geneName;
    }

    public String getGenomicSegDesignator()
    {
        return genomicSegDesignator;
    }

    public void setGenomicSegDesignator(String genomicSegDesignator)
    {
        this.genomicSegDesignator = genomicSegDesignator;
    }

    public boolean isSearchTherapeuticApproaches()
    {
        return searchTherapeuticApproaches;
    }

    public void setSearchTherapeuticApproaches(boolean searchTherapeuticApproaches)
    {
        this.searchTherapeuticApproaches = searchTherapeuticApproaches;
    }

    public String getTherapeuticApproach()
    {
        return therapeuticApproach;
    }

    public void setTherapeuticApproach(String therapeuticApproach)
    {
        this.therapeuticApproach = therapeuticApproach;
    }

    public boolean isSearchHistoMetastasis()
    {
        return searchHistoMetastasis;
    }

    public void setSearchHistoMetastasis(boolean searchHistoMetastasis)
    {
        this.searchHistoMetastasis = searchHistoMetastasis;
    }

    public boolean isSearchMicroArrayData()
    {
        return searchMicroArrayData;
    }

    public void setSearchMicroArrayData(boolean searchMicroArrayData)
    {
        this.searchMicroArrayData = searchMicroArrayData;
    }
    
    public boolean isSearchImageData()
    {
        return searchImageData;
    }

    public void setSearchImageData(boolean searchImageData)
    {
        this.searchImageData = searchImageData;
    }    

    public boolean isSearchTransplant()
    {
        return searchTransplant;
    }

    public void setSearchTransplant(boolean searchTransplant)
    {
        this.searchTransplant = searchTransplant;
    }

    /**
     * @return Returns the searchTransientInterference.
     */
    public boolean isSearchTransientInterference()
    {
        return searchTransientInterference;
    }

    /**
     * @param searchTransientInterference The searchTransientInterference to set.
     */
    public void setSearchTransientInterference(boolean searchTransientInterference)
    {
        this.searchTransientInterference = searchTransientInterference;
    }
    
    /**
     * @return Returns the searchToolStrain.
     */
    public boolean isSearchToolStrain()
    {
        return searchToolStrain;
    }

    /**
     * @param searchToolStrain The searchToolStrain to set.
     */
    public void setSearchToolStrain(boolean searchToolStrain)
    {
        this.searchToolStrain = searchToolStrain;
    }    
    
    public String getTumorClassification()
    {
        return tumorClassification;
    }

    public void setTumorClassification(String tumorClassification)
    {
        this.tumorClassification = tumorClassification;
        // Clean the parameter
        if (this.tumorClassification != null && !this.tumorClassification.equals(""))  {
                this.tumorClassification = SafeHTMLUtil.clean(this.tumorClassification);
        }        
    }
    
    /**
     * Reset all fields that are not used in the simple search. Since the form
     * is used for both the simple and advanced search and is stored in the
     * session to allow users to quickly use the back arrow to refine their
     * search we need to make sure that when the user clicks on the simple
     * search page the options from the advanced search page are reset.
     * 
     */
    public void simpleSearchReset()
    {

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
        tumorClassification = null;
        organ = null;
        organTissueCode = null;
        organTissueName = null;
        inducedMutationAgent = null;
        geneName = null;
        genomicSegDesignator = null;
        therapeuticApproach = null;
        carcinogenicIntervention = null;
        agentName = null;
        searchTherapeuticApproaches = false;
        searchTransientInterference = false;
        engineeredTransgene = false;
        targetedModification = false;
        searchHistoMetastasis = false;
        searchMicroArrayData = false;
        searchTransplant = false;
        searchToolStrain = false;
        externalSource = null;
        searchImageData = false;
    }

    /**
     * Reset all fields.
     */
    public void allFieldsReset()
    {

        keyword = null;
        piName = null;
        modelDescriptor = null;
        species = null;
        
        simpleSearchReset();
    }

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getCarcinogenicIntervention() {
		return carcinogenicIntervention;
	}

	public void setCarcinogenicIntervention(String carcinogenicIntervention) {
		this.carcinogenicIntervention = carcinogenicIntervention;
	}
    
     /**
     * validate the search form
     * @param mapping
     * @param request
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
    {
        System.out.println("In SearchForm.validate");
        ActionErrors errors = new ActionErrors();
       
        // Identify the request parameter containing the method name
        String parameter = mapping.getParameter();
        

        // validate keyword against malicious characters to prevent blind SQl injection attacks
        if (keyword != null && keyword.length() > 0 )
        { 
            System.out.println("Enter validate for keyword loop");
            if (!SafeHTMLUtil.isLetterOrDigit(keyword))
            {
               // populate the validation message
               errors.add("keyword", new ActionMessage("error.keyword.validValue"));
            } 
            System.out.println("Exit validate for keyword loop");         
        }
        
        // validate modelDescriptor against malicious characters to prevent blind SQl injection attacks
        if (modelDescriptor != null  && modelDescriptor.length() > 0)
        { 
            System.out.println("Enter validate for modelDescriptor loop");
            if (!SafeHTMLUtil.isLetterOrDigit(modelDescriptor))
            {
               // populate the validation message
               errors.add("modelDescriptor", new ActionMessage("error.modelDescriptor.validValue"));
            } 
            System.out.println("Exit validate for modelDescriptor loop");
        }
        
        
        // validate for PI
        if (piName != null && piName.length() > 0 )
        {
            List piNameList = new ArrayList();
            piNameList = (List)request.getSession().getAttribute(Constants.Dropdowns.PRINCIPALINVESTIGATORQUERYDROP);
            request.getSession().setAttribute(Constants.Dropdowns.SEARCHPIDROP, piNameList);            
            
            if (!SafeHTMLUtil.isValidStringValue(piName,Constants.Dropdowns.SEARCHPIDROP,request))
            {
               // populate the validation message
               errors.add("piName", new ActionMessage("error.piName.validValue"));
            }
        }
        
        // validate for species
        if (species != null && species.length() > 0 )
        {
            System.out.println("Enter validate for species loop");            
            NameValueList.generateApprovedSpeciesList();
            request.getSession().setAttribute(Constants.Dropdowns.SEARCHSPECIESDROP, NameValueList.getApprovedSpeciesList());

            if (!SafeHTMLUtil.isValidValue(species,Constants.Dropdowns.SEARCHSPECIESDROP,request))
            {
                // populate the validation message
                errors.add("species", new ActionMessage("error.species.validValue"));               
            }
            System.out.println("Exit validate for species loop");             
        }        
        
        // validate organ; against malicious characters to prevent blind SQl injection attacks
        if (organ != null  )
        { 
            System.out.println("Enter validate for organ loop: " + organ);
            setOrgan(organ);
            System.out.println("Exit validate for organ loop: + organ");
        }
        
        // validate tumorClassification against malicious characters to prevent blind SQl injection attacks
        if (tumorClassification != null )
        { 
            System.out.println("Enter validate for tumorClassification loop: " + tumorClassification);
            setTumorClassification(tumorClassification);
            System.out.println("Exit validate for tumorClassification loop: " + tumorClassification);
        }        

        
        // validate phenotype against malicious characters to prevent blind SQl injection attacks
        if (phenotype != null  && phenotype.length() > 0)
        { 
            System.out.println("Enter validate for phenotype loop");
            if (!SafeHTMLUtil.isLetterOrDigit(phenotype))
            {
               // populate the validation message
               errors.add("phenotype", new ActionMessage("error.phenotype.validValue"));
            } 
            System.out.println("Exit validate for phenotype loop");
        }  
        
        
        // validate geneName against malicious characters to prevent blind SQl injection attacks
        if (geneName != null  )
        { 
            System.out.println("Enter validate for geneName loop");
            if (!SafeHTMLUtil.isLetterOrDigit(geneName))
            {
               // populate the validation message
               errors.add("geneName", new ActionMessage("error.geneName.validValue"));
            } 
            System.out.println("Exit validate for geneName loop");
        }  
        
        // validate genomicSegDesignator against malicious characters to prevent blind SQl injection attacks
        if (genomicSegDesignator != null && genomicSegDesignator.length() > 0 )
        {
            List genomicSegDesigList = new ArrayList();
            genomicSegDesigList = (List)request.getSession().getAttribute(Constants.Dropdowns.CLONEDESIGNATORQUERYDROP);
            request.getSession().setAttribute(Constants.Dropdowns.SEARCHGENOMICSEGMENT, genomicSegDesigList);            
            
            if (!SafeHTMLUtil.isValidStringValue(genomicSegDesignator,Constants.Dropdowns.SEARCHGENOMICSEGMENT,request))
            {
               // populate the validation message
               errors.add("genomicSegDesignator", new ActionMessage("error.genomicSegDesignator.validValue"));
               return errors;
            }
            System.out.println("Exit validate for genomicSegDesignator loop");                 
        }        
        
        
        
        // validate for inducedMutationAgent
        if (inducedMutationAgent != null && inducedMutationAgent.length() > 0 )
        {
            System.out.println("Enter validate for inducedMutationAgent loop");            
            NameValueList.generateInducedMutationAgentList();
            request.getSession().setAttribute(Constants.Dropdowns.SEARCHINDUCEDMUTATIONDROP, NameValueList.getInducedMutationAgentList());

            if (!SafeHTMLUtil.isValidValue(inducedMutationAgent,Constants.Dropdowns.SEARCHINDUCEDMUTATIONDROP,request) || !SafeHTMLUtil.isLetterOrDigit(inducedMutationAgent))
            {
                // populate the validation message
                errors.add("inducedMutationAgent", new ActionMessage("error.inducedMutationAgent.validValue"));
            }
            System.out.println("Exit validate for inducedMutationAgent loop");             
        }        
        
        // validate for carcinogenicIntervention
        if (carcinogenicIntervention != null && carcinogenicIntervention.length() > 0 )
        {
            System.out.println("Enter validate for carcinogenicIntervention loop");            
            NameValueList.generateCarcinogenicInterventionList();
            request.getSession().setAttribute(Constants.Dropdowns.SEARCHCARCINOGENEXPOSUREDROP, NameValueList.getCarcinogenicInterventionList());

	            if (!SafeHTMLUtil.isValidValue(carcinogenicIntervention,Constants.Dropdowns.SEARCHCARCINOGENEXPOSUREDROP,request) 
	            		|| !SafeHTMLUtil.isLetterOrDigitNotChemicalDrug(carcinogenicIntervention))
	            {
	                   // populate the validation message
	                   errors.add("carcinogenicIntervention", new ActionMessage("error.carcinogenicIntervention.validValue"));
	            } 
            System.out.println("Exit validate for carcinogenicIntervention loop");            
            
            
            // validate for agentName            
            if (agentName != null && agentName.length() > 0 )
            {
                List agentNameList = new ArrayList();
                agentNameList = (List)request.getSession().getAttribute(Constants.Dropdowns.ENVIRONMENTALFACTORNAMESDROP);
                request.getSession().setAttribute(Constants.Dropdowns.SEARCHENVIRONFACTORDROP, agentNameList);            
                
                if (!SafeHTMLUtil.isValidStringValue(agentName,Constants.Dropdowns.SEARCHENVIRONFACTORDROP,request))
                {
                   // populate the validation message
                   errors.add("agentName", new ActionMessage("error.agentName.validValue"));
                   return errors;
                }
                System.out.println("Exit validate for agentName loop");                 
            }
            
        }  

        
        // validate for cellLine            
        if (cellLine != null && cellLine.length() > 0 )
        {
            List cellLineList = new ArrayList();
            cellLineList = (List)request.getSession().getAttribute(Constants.Dropdowns.CELLLINENAMEQUERYDROP);
            request.getSession().setAttribute(Constants.Dropdowns.SEARCHCELLLINE, cellLineList);            
            
            if (!SafeHTMLUtil.isValidStringValue(cellLine,Constants.Dropdowns.SEARCHCELLLINE,request))
            {
               // populate the validation message
               errors.add("cellLine", new ActionMessage("error.cellLine.validValue"));
               return errors;
            }
            System.out.println("Exit validate for cellLine loop");                 
        }        
        
         
        
        // validate therpy compound/drug against malicious characters to prevent blind SQl injection attacks
        if (therapeuticApproach != null   )
        { 
            List drugNameList = new ArrayList();
            drugNameList = (List)request.getSession().getAttribute(Constants.Dropdowns.THERAPEUTICAPPROACHDRUGQUERYDROP);
            request.getSession().setAttribute(Constants.Dropdowns.SEARCHTHERAPEUTICDRUGNAME, drugNameList);            
            
            if (!SafeHTMLUtil.isValidStringValue(cellLine,Constants.Dropdowns.SEARCHTHERAPEUTICDRUGNAME,request))
            {
               // populate the validation message
               errors.add("therapeuticApproach", new ActionMessage("error.therapeuticApproach.validValue"));
               return errors;
            }
            System.out.println("Exit validate for therapeuticApproach loop");
        }
        
        // validate for externalSource
        if (externalSource != null && externalSource.length() > 0 )
        {
            System.out.println("Enter validate for externalSource loop");            
            NameValueList.generateExternalSourceList();
            request.getSession().setAttribute(Constants.Dropdowns.SEARCHEXTERNALSOURCEDROP, NameValueList.getExternalSourceList());

            if (!SafeHTMLUtil.isValidValue(externalSource,Constants.Dropdowns.SEARCHEXTERNALSOURCEDROP,request))
            {
                // populate the validation message
                errors.add("externalSource", new ActionMessage("error.externalSource.validValue"));
            }
            System.out.println("Exit validate for externalSource loop");             
        } 
        
        if (parameter != null) {
            // Identify the method name to be dispatched to.
            String method = request.getParameter(parameter);
            MessageResources resources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);

            // Identify the localized message for the clear button
            String clear = resources.getMessage("button.clear");

            // if message resource matches the clear button then no
            // need to validate
            if ((method != null) && (method.equalsIgnoreCase(clear))) {
                return null;
            }        
        }       
        return errors;               
    } 
    
  
}
