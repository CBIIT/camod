/**
 * @author dgeorge
 * 
 * $Id: AnimalModelSearchResult.java,v 1.13 2006-05-10 17:35:51 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2006/05/10 14:13:51  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.11  2006/04/28 19:05:47  schroedn
 * Defect # 238
 * Added methods to display data on search result pages for configurable search result columns
 *
 * Revision 1.10  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.9  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.8  2005/11/28 18:02:10  georgeda
 * Defect #182.  Get unique set of organs and only display metas. next to the originating organ
 *
 * Revision 1.7  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.6  2005/11/15 19:10:23  schroedn
 * Defect #49
 *
 * Fixed misspelling of Metastasis in getMetastatisSites
 *
 * Revision 1.5  2005/10/17 18:19:28  georgeda
 * Added ability to sort
 *
 * Revision 1.4  2005/10/17 13:27:54  georgeda
 * Updates
 *
 * Revision 1.3  2005/10/12 18:18:48  georgeda
 * Small fix
 *
 * Revision 1.2  2005/10/12 18:09:29  georgeda
 * Update for metastatis organs
 *
 * Revision 1.1  2005/10/07 16:27:50  georgeda
 * Implemented paganation
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.service.impl.AnimalModelManagerSingleton;
import gov.nih.nci.camod.Constants;
import java.util.*;

/**
 * Used as wrapper around animal model for speedy display during paganation.
 */
public class AnimalModelSearchResult implements Comparable
{

    private String myAnimalModelId;
    private String myTumorSites = null;
    private String mySpecies = null;
    private String myModelDescriptor = null;
    private String mySubmitterName = null;
    private String mySubmittedDate = null;
    private AnimalModel myAnimalModel = null;
    private String myStrain = null;
    private String myModelId = null;
    private String myPrincipalInvestigatorName = null;
    private String myGender = null;
    private String myTransgene = null;
    private String myTranscriptional1 = null;
    private String mySegmentType = null;
    private String myDesignator = null;
    private String myTargetedGeneLocus = null;
    private String myTypeOfModification = null;
    private String myNameOfInducingAgent = null;
    private String myGeneName = null;
    private String myCarcinogen = null;
    private String myViralVector = null;
    private String myGene = null;
    private String myMicroarray = null;
    private String myYearOfPublication = null;
    private String myJournal = null;
    private String myPMIDNumber = null;
    private String myPublications = null;
    private String mySiteOfLesionTumor = null;
    private String myDiagnosis = null;
    private String myAgeOfOnset = null;
    private String myTumorIncidenceOverLifetime = null;
    private String mySiteAndDiagnosisOfMetastasis = null;
    private String myDrugCompoundName = null;
    private String myNameOfCellLine = null;
    private String myOrganTissue = null;
    private String myImageTitle = null;
    private String myDistributor = null;
    private String myCellLine = null;
    private String myDonorSpecies = null;
    private String myGraftType = null;

    /**
     * Create the wraper object
     * 
     * @param inAnimalModel
     *            the animal model we will be wrapping. Saves only the id.
     */
    public AnimalModelSearchResult(AnimalModel inAnimalModel)
    {
        myAnimalModelId = inAnimalModel.getId().toString();
        myModelDescriptor = inAnimalModel.getModelDescriptor();
    }

    /**
     * Return the id for the associated model
     * 
     * @return the id for the model
     */
    public String getId()
    {

        return myAnimalModelId;
    }

    /**
     * Return the model descriptor. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the model descriptor for the associated model
     * 
     * @throws Exception
     */
    public String getModelDescriptor() throws Exception
    {
        return myModelDescriptor;
    }

    /**
     * Return the model id. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the model id for the associated model
     * 
     * @throws Exception
     */
    public String getModelId() throws Exception
    {
        if (myModelId == null)
        {
            fetchAnimalModel();
            myModelId = myAnimalModel.getId().toString();
        }
        return myModelId;
    }

    /**
     * Return the Principal Investigator as a mailto: link. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the principal Investigator as a http mail link
     * 
     * @throws Exception
     */
    public String getPrincipalInvestigatorName() throws Exception
    {
        if (myPrincipalInvestigatorName == null)
        {
            fetchAnimalModel();

            // Shouldn't happen, but older models seem to have a missing PI sometimes
            if (myAnimalModel.getPrincipalInvestigator() != null)
            {
                String theEmailAddress = myAnimalModel.getPrincipalInvestigator().getEmailAddress();

                if (theEmailAddress.length() > 0)
                {
                    myPrincipalInvestigatorName = "<a href=\"mailto:" + theEmailAddress + "\"/>" + myAnimalModel.getPrincipalInvestigator().getDisplayName();
                }
                else
                {
                    myPrincipalInvestigatorName = myAnimalModel.getPrincipalInvestigator().getDisplayName();
                }
            }
        }
        return myPrincipalInvestigatorName;
    }

    /**
     * Return the Gender. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the gender for the associated model
     * 
     * @throws Exception
     */
    public String getGender() throws Exception
    {
        if (myGender == null)
        {
            fetchAnimalModel();
            if (myAnimalModel.getPhenotype().getSexDistribution() != null)
            {
                myGender = myAnimalModel.getPhenotype().getSexDistribution().getType();
            }
            else
            {
                myGender = "";
            }
        }
        return myGender;
    }

    /**
     * Return the Transgenes. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Trangenese for the associated model
     * 
     * @throws Exception
     */
    public String getTransgene() throws Exception
    {
        if (myTransgene == null)
        {
            fetchAnimalModel();
            if (myAnimalModel.getEngineeredGeneCollection() != null)
            {
                Set<EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myTransgene = "";

                while (engineeredListIter.hasNext())
                {
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if (eg instanceof Transgene)
                    {
                        Transgene t = (Transgene) eg;
                        myTransgene += t.getName() + "<br>";
                    }
                }
            }
        }
        return myTransgene;
    }

    /**
     * Return the Transcriptional 1 name. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Transcriptional 1 name for the associated model
     * 
     * @throws Exception
     */
    public String getTranscriptional1() throws Exception
    {
        if (myTranscriptional1 == null)
        {
            fetchAnimalModel();
            if (myAnimalModel.getEngineeredGeneCollection() != null)
            {
                Set<EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myTranscriptional1 = "";

                while (engineeredListIter.hasNext())
                {
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if (eg instanceof Transgene)
                    {
                        Transgene t = (Transgene) eg;

                        Set<RegulatoryElement> regElementList = t.getRegulatoryElementCollection();
                        Iterator<RegulatoryElement> regListIter = regElementList.iterator();

                        while (regListIter.hasNext())
                        {
                            RegulatoryElement re = (RegulatoryElement) regListIter.next();
                            if (re.getRegulatoryElementType().getName().equals(Constants.ENVFactors.TRANSCRIPTIONAL1))
                            {
                                myTranscriptional1 += re.getName() + "<br>";
                            }
                        }
                    }

                }
            }
        }
        return myTranscriptional1;
    }

    /**
     * Return the Segment Types. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Segment types for the associated model
     * 
     * @throws Exception
     */
    public String getSegmentType() throws Exception
    {
        if (mySegmentType == null)
        {
            fetchAnimalModel();
            if (myAnimalModel.getEngineeredGeneCollection() != null)
            {
                Set<EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                mySegmentType = "";

                while (engineeredListIter.hasNext())
                {
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if (eg instanceof GenomicSegment)
                    {
                        GenomicSegment theGenomicSegment = (GenomicSegment) eg;

                        if (theGenomicSegment.getSegmentType().getNameUnctrlVocab() == null || theGenomicSegment.getSegmentType().getNameUnctrlVocab().equals(
                                                                                                                                                              ""))
                        {
                            mySegmentType += theGenomicSegment.getSegmentType().getName() + "<br>";
                        }
                        else
                        {
                            mySegmentType += theGenomicSegment.getSegmentType().getNameUnctrlVocab() + "<br>";
                        }
                    }
                }
            }
        }
        return mySegmentType;
    }

    /**
     * Return the Designator for the genomic segment. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Designator for the associated model
     * 
     * @throws Exception
     */
    public String getDesignator() throws Exception
    {
        if (myDesignator == null)
        {
            fetchAnimalModel();
            if (myAnimalModel.getEngineeredGeneCollection() != null)
            {
                Set<EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myDesignator = "";

                while (engineeredListIter.hasNext())
                {
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if (eg instanceof GenomicSegment)
                    {
                        GenomicSegment theGenomicSegment = (GenomicSegment) eg;
                        myDesignator += theGenomicSegment.getCloneDesignator() + "<br>";
                    }
                }
            }
        }
        return myDesignator;
    }

    /**
     * Return the Targeted Gene Locus. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Targeted Gene Locus for the associated model
     * 
     * @throws Exception
     */
    public String getTargetedGeneLocus() throws Exception
    {
        if (myTargetedGeneLocus == null)
        {
            fetchAnimalModel();
            if (myAnimalModel.getEngineeredGeneCollection() != null)
            {
                Set<EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myTargetedGeneLocus = "";

                while (engineeredListIter.hasNext())
                {
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if (eg instanceof TargetedModification)
                    {
                        TargetedModification tm = (TargetedModification) eg;
                        myTargetedGeneLocus += tm.getName() + "<br>";
                    }
                }
            }
        }
        return myTargetedGeneLocus;
    }

    /**
     * Return the Type of Modification. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Type of Modification for the associated model
     * 
     * @throws Exception
     */
    public String getTypeOfModification() throws Exception
    {
        if (myTypeOfModification == null)
        {
            fetchAnimalModel();
            if (myAnimalModel.getEngineeredGeneCollection() != null)
            {
                Set<EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myTypeOfModification = "";

                while (engineeredListIter.hasNext())
                {
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if (eg instanceof TargetedModification)
                    {
                        TargetedModification tm = (TargetedModification) eg;
                        myTypeOfModification += tm.getModificationTypeCollection() + "<br>";

                        Set<ModificationType> modTypeList = tm.getModificationTypeCollection();
                        Iterator<ModificationType> modIter = modTypeList.iterator();

                        while (modIter.hasNext())
                        {
                            ModificationType mt = (ModificationType) modIter.next();

                            if (mt.getNameUnctrlVocab() == null || mt.getNameUnctrlVocab().equals(""))
                            {
                                myTypeOfModification += mt.getName() + "<br>";
                            }
                            else
                            {
                                myTypeOfModification += mt.getNameUnctrlVocab() + "<br>";
                            }
                        }
                    }
                }
            }
        }
        return myTypeOfModification;
    }

    /**
     * Return the Name of the Inducing Agent. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Name of the Inducing Agent for the associated model
     * 
     * @throws Exception
     */
    public String getNameOfInducingAgent() throws Exception
    {
        if (myNameOfInducingAgent == null)
        {
            fetchAnimalModel();
            if (myAnimalModel.getEngineeredGeneCollection() != null)
            {
                Set<EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myNameOfInducingAgent = "";

                while (engineeredListIter.hasNext())
                {
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if (eg instanceof InducedMutation)
                    {
                        InducedMutation im = (InducedMutation) eg;
                        if (im.getEnvironmentalFactor().getName() != null)
                        {
                            myNameOfInducingAgent += im.getEnvironmentalFactor().getName() + "<br>";
                        }
                        else
                        {
                            myNameOfInducingAgent += im.getEnvironmentalFactor().getNameUnctrlVocab() + "<br>";
                        }
                    }
                }
            }
        }
        return myNameOfInducingAgent;
    }

    /**
     * Return the Gene Name. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Gene Name for the associated model
     * 
     * @throws Exception
     */
    public String getGeneName() throws Exception
    {
        if (myGeneName == null)
        {
            fetchAnimalModel();

            Set<SpontaneousMutation> smSet = myAnimalModel.getSpontaneousMutationCollection();
            Iterator<SpontaneousMutation> smIter = smSet.iterator();
            myGeneName = "";

            while (smIter.hasNext())
            {
                SpontaneousMutation sm = (SpontaneousMutation) smIter.next();
                myGeneName += sm.getName() + "<br>";
            }
        }
        return myGeneName;
    }

    /**
     * Return the Carcinogenetic Interventions. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Carcinogenetic Interventions for the associated model
     * 
     * @throws Exception
     */
    public String getCarcinogen() throws Exception
    {
        if (myCarcinogen == null)
        {
            fetchAnimalModel();

            Set<CarcinogenExposure> ceSet = myAnimalModel.getCarcinogenExposureCollection();
            Iterator<CarcinogenExposure> ceIter = ceSet.iterator();
            myCarcinogen = "";

            while (ceIter.hasNext())
            {
                CarcinogenExposure ce = (CarcinogenExposure) ceIter.next();
                EnvironmentalFactor ef = ce.getEnvironmentalFactor();

                if (ef != null)
                {
                    if (ef.getType().equals(Constants.ENVFactors.CHEMICAL_DRUG))
                    {
                        if (ef.getName() != null)
                        {
                            myCarcinogen += ef.getName() + " (" + Constants.ENVFactors.CHEMICAL_DRUG + ") <br>";
                        }
                        else
                        {
                            myCarcinogen += ef.getNameUnctrlVocab() + " (" + Constants.ENVFactors.CHEMICAL_DRUG + ") <br>";
                        }
                    }
                    if (ef.getType().equals(Constants.ENVFactors.HORMONE))
                    {
                        if (ef.getName() != null)
                        {
                            myCarcinogen += ef.getName() + " (" + Constants.ENVFactors.HORMONE + ") <br>";
                        }
                        else
                        {
                            myCarcinogen += ef.getNameUnctrlVocab() + " (" + Constants.ENVFactors.HORMONE + ") <br>";
                        }
                    }
                    if (ef.getType().equals(Constants.ENVFactors.GROWTH_FACTOR))
                    {
                        if (ef.getName() != null)
                        {
                            myCarcinogen += ef.getName() + " (" + Constants.ENVFactors.GROWTH_FACTOR + ") <br>";
                        }
                        else
                        {
                            myCarcinogen += ef.getNameUnctrlVocab() + " (" + Constants.ENVFactors.GROWTH_FACTOR + ") <br>";
                        }
                    }
                    if (ef.getType().equals(Constants.ENVFactors.VIRAL))
                    {
                        if (ef.getName() != null)
                        {
                            myCarcinogen += ef.getName() + " (" + Constants.ENVFactors.VIRAL + ") <br>";
                            ;
                        }
                        else
                        {
                            myCarcinogen += ef.getNameUnctrlVocab() + " (" + Constants.ENVFactors.VIRAL + ") <br>";
                        }
                    }
                    if (ef.getType().equals(Constants.ENVFactors.ENVIRONMOENT))
                    {
                        if (ef.getName() != null)
                        {
                            myCarcinogen += ef.getName() + " (" + Constants.ENVFactors.ENVIRONMOENT + ") <br>";
                        }
                        else
                        {
                            myCarcinogen += ef.getNameUnctrlVocab() + " (" + Constants.ENVFactors.ENVIRONMOENT + ") <br>";
                        }

                    }
                    if (ef.getType().equals(Constants.ENVFactors.NUTRITION))
                    {
                        if (ef.getName() != null)
                        {
                            myCarcinogen += ef.getName() + " (" + Constants.ENVFactors.NUTRITION + ") <br>";
                        }
                        else
                        {
                            myCarcinogen += ef.getNameUnctrlVocab() + " (" + Constants.ENVFactors.NUTRITION + ") <br>";
                        }
                    }
                    if (ef.getType().equals(Constants.ENVFactors.VIRUS))
                    {
                        if (ef.getName() != null)
                        {
                            myCarcinogen += ef.getName() + " (" + Constants.ENVFactors.VIRUS + ") <br>";
                        }
                        else
                        {
                            myCarcinogen += ef.getNameUnctrlVocab() + " (" + Constants.ENVFactors.VIRUS + ") <br>";
                        }
                    }
                    if (ef.getType().equals(Constants.ENVFactors.OTHER))
                    {
                        if (ef.getName() != null)
                        {
                            myCarcinogen += ef.getName() + " (" + Constants.ENVFactors.OTHER + ") <br>";
                        }
                        else
                        {
                            myCarcinogen += ef.getNameUnctrlVocab() + " (" + Constants.ENVFactors.OTHER + ")<br>";
                        }
                    }
                }
            }
        }
        return myCarcinogen;
    }

    /**
     * Return the Viral Vector. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Viral Vector for the associated model
     * 
     * @throws Exception
     */
    public String getViralVector() throws Exception
    {
        if (myViralVector == null)
        {
            fetchAnimalModel();
            Set<GeneDelivery> geneDeliverySet = myAnimalModel.getGeneDeliveryCollection();
            Iterator<GeneDelivery> geneDeliveryIter = geneDeliverySet.iterator();
            myViralVector = "";

            while (geneDeliveryIter.hasNext())
            {
                GeneDelivery geneDelivery = (GeneDelivery) geneDeliveryIter.next();

                if (geneDelivery.getViralVector() != null)
                {
                    myViralVector += geneDelivery.getViralVector() + "<br>";
                }
                else
                {
                    myViralVector += geneDelivery.getViralVectorUnctrlVocab() + "<br>";
                }
            }

        }
        return myViralVector;
    }

    /**
     * Return the Gene. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Gene for the associated model
     * 
     * @throws Exception
     */
    public String getGene() throws Exception
    {
        if (myGene == null)
        {
            fetchAnimalModel();

            Set<GeneDelivery> geneDeliverySet = myAnimalModel.getGeneDeliveryCollection();
            Iterator<GeneDelivery> geneDeliveryIter = geneDeliverySet.iterator();
            myGene = "";

            while (geneDeliveryIter.hasNext())
            {
                GeneDelivery geneDelivery = (GeneDelivery) geneDeliveryIter.next();
                myGene += geneDelivery.getDisplayName() + "<br>";

            }
        }
        return myGene;
    }

    /**
     * Return the Years of Publication for all publications. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Years of Publication for all publications for the associated model
     * 
     * @throws Exception
     */
    public String getYearOfPublication() throws Exception
    {
        if (myYearOfPublication == null)
        {
            fetchAnimalModel();

            Set pubSet = myAnimalModel.getPublicationCollection();
            Iterator pubSetIter = pubSet.iterator();
            myYearOfPublication = "";

            while (pubSetIter.hasNext())
            {
                Publication pub = (Publication) pubSetIter.next();
                myYearOfPublication += pub.getYear() + "<br>";
            }
        }
        return myYearOfPublication;
    }

    /**
     * Return the Journal names of Publication for all publications. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Journal names of Publication for all publications for the associated model
     * 
     * @throws Exception
     */
    public String getJournal() throws Exception
    {
        if (myJournal == null)
        {
            fetchAnimalModel();

            Set pubSet = myAnimalModel.getPublicationCollection();
            Iterator pubSetIter = pubSet.iterator();
            myJournal = "";

            while (pubSetIter.hasNext())
            {
                Publication pub = (Publication) pubSetIter.next();
                myJournal += pub.getJournal() + "<br>";
            }
        }
        return myJournal;
    }

    /**
     * Return the links to the PMID Numbers of Publication for all publications. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the links to the PMID Numbers of Publication for all publications for the associated model
     * 
     * @throws Exception
     */
    public String getPMIDNumber() throws Exception
    {
        if (myPMIDNumber == null)
        {
            fetchAnimalModel();

            Set pubSet = myAnimalModel.getPublicationCollection();
            Iterator pubSetIter = pubSet.iterator();
            myPMIDNumber = "";

            while (pubSetIter.hasNext())
            {
                Publication pub = (Publication) pubSetIter.next();
                myPMIDNumber += "<a target=\"_pubmed\" href=\" http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=retrieve&db=pubmed&dopt=abstract&list_uids=" + pub.getPmid() + "\">" + pub.getPmid() + "</a><br>";
            }
        }
        return myPMIDNumber;
    }

    /**
     * Return the All details for all publications. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the All details for all publications for the associated model
     * 
     * @throws Exception
     */
    public String getPublications() throws Exception
    {
        if (myPublications == null)
        {
            fetchAnimalModel();

            Set pubSet = myAnimalModel.getPublicationCollection();
            Iterator pubSetIter = pubSet.iterator();
            myPublications = "";

            while (pubSetIter.hasNext())
            {
                Publication pub = (Publication) pubSetIter.next();
                myPublications += pub.getJournal() + "(" + pub.getYear() + ") " + "<a target=\"_pubmed\" href=\" http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=retrieve&db=pubmed&dopt=abstract&list_uids=" + pub.getPmid() + "\">" + pub.getPmid() + "</a><br>";
            }
        }
        return myPublications;
    }

    /**
     * Return the Site of Lesion Tumor. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Site of Lesion Tumor for the associated model
     * 
     * @throws Exception
     */
    public String getSiteOfLesionTumor() throws Exception
    {
        if (mySiteOfLesionTumor == null)
        {
            fetchAnimalModel();

            Set<Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator<Histopathology> hisSetIter = hisSet.iterator();
            mySiteOfLesionTumor = "";

            while (hisSetIter.hasNext())
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                mySiteOfLesionTumor += his.getOrgan().getName() + "<br>";
            }
        }
        return mySiteOfLesionTumor;
    }

    /**
     * Return the Diagnosis. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Diagnosis for the associated model
     * 
     * @throws Exception
     */
    public String getDiagnosis() throws Exception
    {
        if (myDiagnosis == null)
        {
            fetchAnimalModel();

            Set<Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator<Histopathology> hisSetIter = hisSet.iterator();
            myDiagnosis = "";

            while (hisSetIter.hasNext())
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                myDiagnosis += his.getDisease().getName() + "<br>";
            }
        }
        return myDiagnosis;
    }

    /**
     * Return the Age of Onset. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Age of Onset for the associated model
     * 
     * @throws Exception
     */
    public String getAgeOfOnset() throws Exception
    {
        if (myAgeOfOnset == null)
        {
            fetchAnimalModel();
            Set<Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator<Histopathology> hisSetIter = hisSet.iterator();
            myAgeOfOnset = "";

            while (hisSetIter.hasNext())
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                if (his.getAgeOfOnset() != null)
                {
                    myAgeOfOnset += his.getAgeOfOnset();
                }
                if (his.getAgeOfOnsetUnit() != null)
                {
                    myAgeOfOnset += " " + his.getAgeOfOnsetUnit();
                }

                myAgeOfOnset += "<br>";
            }
        }
        return myAgeOfOnset;
    }

    /**
     * Return the Tumor Incidence Over Lifetime. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Tumor Incidence over lifetime for the associated model
     * 
     * @throws Exception
     */
    public String getTumorIncidenceOverLifetime() throws Exception
    {
        if (myTumorIncidenceOverLifetime == null)
        {
            fetchAnimalModel();
            Set<Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator<Histopathology> hisSetIter = hisSet.iterator();
            myTumorIncidenceOverLifetime = "";

            while (hisSetIter.hasNext())
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                if (his.getTumorIncidenceRate() != null)
                {
                    myTumorIncidenceOverLifetime += his.getTumorIncidenceRate() + "<br>";
                }
            }
        }
        return myTumorIncidenceOverLifetime;
    }

    /**
     * Return the Site and diagnosis of metastasis. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Site and diagnosis of metastasis for the associated model
     * 
     * @throws Exception
     */
    public String getSiteAndDiagnosisOfMetastasis() throws Exception
    {
        if (mySiteAndDiagnosisOfMetastasis == null)
        {
            fetchAnimalModel();
            Set<Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator<Histopathology> hisSetIter = hisSet.iterator();
            mySiteAndDiagnosisOfMetastasis = "";

            while (hisSetIter.hasNext())
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                Set<Histopathology> metSet = his.getMetastasisCollection();
                Iterator<Histopathology> metSetIter = metSet.iterator();

                while (metSetIter.hasNext())
                {
                    Histopathology met = (Histopathology) metSetIter.next();
                    mySiteAndDiagnosisOfMetastasis += met.getDisease().getName() + " (" + met.getOrgan().getName() + ")" + "<br>";
                }
            }
        }
        return mySiteAndDiagnosisOfMetastasis;
    }

    /**
     * Return the drug compound name. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the drug compund name for the associated model
     * 
     * @throws Exception
     */
    public String getDrugCompoundName() throws Exception
    {
        if (myDrugCompoundName == null)
        {
            fetchAnimalModel();
            Set<Therapy> set = myAnimalModel.getTherapyCollection();
            Iterator<Therapy> setIter = set.iterator();
            myDrugCompoundName = "";

            while (setIter.hasNext())
            {
                Therapy it = (Therapy) setIter.next();
                if (it.getTreatment() != null)
                {
                    if (it.getTreatment().getAdministrativeRoute() != null)
                    {
                        myDrugCompoundName += it.getTreatment().getAdministrativeRoute() + "<br>";
                    }
                    else
                    {
                        myDrugCompoundName += it.getTreatment().getAdminRouteUnctrlVocab() + "<br>";
                    }
                }
            }
        }
        return myDrugCompoundName;
    }

    /**
     * Return the Name of cell line. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Name of cell line for the associated model
     * 
     * @throws Exception
     */
    public String getNameOfCellLine() throws Exception
    {
        if (myNameOfCellLine == null)
        {
            fetchAnimalModel();
            Set<CellLine> set = myAnimalModel.getCellLineCollection();
            Iterator<CellLine> setIter = set.iterator();
            myNameOfCellLine = "";

            while (setIter.hasNext())
            {
                CellLine it = (CellLine) setIter.next();
                if (it.getName() != null)
                {
                    myNameOfCellLine += it.getName() + "<br>";
                }
            }
        }
        return myNameOfCellLine;
    }

    /**
     * Return the Organ Tissue. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Organ Tissue for the associated model
     * 
     * @throws Exception
     */
    public String getOrganTissue() throws Exception
    {
        if (myOrganTissue == null)
        {
            fetchAnimalModel();
            Set<CellLine> set = myAnimalModel.getCellLineCollection();
            Iterator<CellLine> setIter = set.iterator();
            myOrganTissue = "";

            while (setIter.hasNext())
            {
                CellLine it = (CellLine) setIter.next();
                if (it.getOrgan() != null)
                {
                    myOrganTissue += it.getOrgan().getName() + "<br>";
                }
            }
        }
        return myOrganTissue;
    }

    /**
     * Return the Image Titles. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the image titles for the associated model
     * 
     * @throws Exception
     */
    public String getImageTitle() throws Exception
    {
        if (myImageTitle == null)
        {
            fetchAnimalModel();
            Set<Image> set = myAnimalModel.getImageCollection();
            Iterator<Image> setIter = set.iterator();
            myImageTitle = "";

            while (setIter.hasNext())
            {
                Image it = (Image) setIter.next();

                myImageTitle += "<IMG src=\"/camod/images/image.gif\"> ";
                if (it.getTitle() != null)
                {
                    myImageTitle += it.getTitle();
                }

                myImageTitle += "<br>";
            }
        }
        return myImageTitle;
    }

    /**
     * Return the distributors. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the distributors for the associated model
     * 
     * @throws Exception
     */
    public String getDistributor() throws Exception
    {
        if (myDistributor == null)
        {
            fetchAnimalModel();
            Set<AnimalAvailability> set = myAnimalModel.getAnimalAvailabilityCollection();
            Iterator<AnimalAvailability> setIter = set.iterator();
            myDistributor = "";

            while (setIter.hasNext())
            {
                AnimalAvailability it = (AnimalAvailability) setIter.next();
                if (it.getAnimalDistributor() != null)
                {
                    myDistributor += it.getAnimalDistributor().getName() + "<br>";
                }
            }
        }
        return myDistributor;
    }

    /**
     * Return the Cell Lines. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Cell lines for the associated model
     * 
     * @throws Exception
     */
    public String getCellLine() throws Exception
    {
        if (myCellLine == null)
        {
            fetchAnimalModel();
            Set<Xenograft> set = myAnimalModel.getXenograftCollection();
            Iterator<Xenograft> setIter = set.iterator();
            myCellLine = "";

            while (setIter.hasNext())
            {
                Xenograft it = (Xenograft) setIter.next();
                if (it.getParentalCellLineName() != null)
                {
                    myCellLine += it.getParentalCellLineName() + "<br>";
                }
            }
        }
        return myCellLine;
    }

    /**
     * Return the Donor species. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Donor Species for the associated model
     * 
     * @throws Exception
     */
    public String getDonorSpecies() throws Exception
    {
        if (myDonorSpecies == null)
        {
            fetchAnimalModel();
            Set<Xenograft> set = myAnimalModel.getXenograftCollection();
            Iterator<Xenograft> setIter = set.iterator();
            myDonorSpecies = "";

            while (setIter.hasNext())
            {
                Xenograft it = (Xenograft) setIter.next();
                if (it.getDonorSpecies() != null)
                {
                    if (it.getDonorSpecies().getDisplayName() != null)
                    {
                        myDonorSpecies += it.getDonorSpecies().getDisplayName() + "<br>";
                    }
                }
            }
        }
        return myDonorSpecies;
    }

    /**
     * Return the Graft Types. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Graft Types for the associated model
     * 
     * @throws Exception
     */
    public String getGraftType() throws Exception
    {
        if (myGraftType == null)
        {
            fetchAnimalModel();
            Set<Xenograft> set = myAnimalModel.getXenograftCollection();
            Iterator<Xenograft> setIter = set.iterator();
            myGraftType = "";

            while (setIter.hasNext())
            {
                Xenograft it = (Xenograft) setIter.next();
                if (it.getGraftType() != null)
                {
                    myGraftType += it.getGraftType() + "<br>";
                }

                if (it.getGraftTypeUnctrlVocab() != null)
                {
                    myGraftType += it.getGraftTypeUnctrlVocab() + "<br>";
                }
            }
        }
        return myGraftType;
    }

    /**
     * Return the Microarray details. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the Microarray details for the associated model
     * 
     * @throws Exception
     */
    public String getMicroarray() throws Exception
    {
        if (myMicroarray == null)
        {
            fetchAnimalModel();
            Set<MicroArrayData> set = myAnimalModel.getMicroArrayDataCollection();
            Iterator<MicroArrayData> setIter = set.iterator();
            myMicroarray = "";

            while (setIter.hasNext())
            {
                MicroArrayData it = (MicroArrayData) setIter.next();
                myMicroarray += "<IMG src=\"/camod/images/table_multiple.png\"> ";
                if (it.getExperimentName() != null)
                {
                    myMicroarray += it.getExperimentName() + "<br>";
                }

            }
        }
        return myMicroarray;
    }

    /**
     * Return the species. It will fetch the animal model from the DB if it
     * hasn't already happened.
     * 
     * @return the species for the associated model
     * @throws Exception
     */
    public String getSpecies() throws Exception
    {
        if (mySpecies == null)
        {
            fetchAnimalModel();
            mySpecies = myAnimalModel.getStrain().getSpecies().getDisplayName();
        }
        return mySpecies;
    }

    /**
     * Return the strain. It will fetch the animal model from the DB if it
     * hasn't already happened.
     * 
     * @return the strain for the associated model
     * @throws Exception
     */
    public String getStrain() throws Exception
    {
        if (myStrain == null)
        {
            fetchAnimalModel();
            myStrain = myAnimalModel.getStrain().getName();
        }
        return myStrain;
    }

    /**
     * Return the list of tumor sites. It will fetch the animal model from the
     * DB if it hasn't already happened.
     * 
     * @return the list of tumor sites for the associated model
     * @throws Exception
     */
    public String getTumorSites() throws Exception
    {

        Set<String> theOrgans = new TreeSet<String>();
        Hashtable<String, TreeSet<String>> theMetas = new Hashtable<String, TreeSet<String>>();

        if (myTumorSites == null)
        {
            fetchAnimalModel();

            myTumorSites = "";
            Set<Histopathology> theHistopathologySet = myAnimalModel.getHistopathologyCollection();
            Iterator it = theHistopathologySet.iterator();

            while (it.hasNext())
            {
                Histopathology theHistopathology = (Histopathology) it.next();
                String theOrgan = theHistopathology.getOrgan().getEVSPreferredDescription();

                if (!theOrgans.contains(theOrgan))
                {
                    theOrgans.add(theOrgan);
                    theMetas.put(theOrgan, new TreeSet<String>());
                }

                TreeSet<String> theMetaSet = theMetas.get(theOrgan);
                Set theMetastasisSet = theHistopathology.getMetastasisCollection();
                it = theMetastasisSet.iterator();

                while (it.hasNext())
                {
                    Histopathology theMetastasis = (Histopathology) it.next();
                    String theMetaOrgan = theMetastasis.getOrgan().getEVSPreferredDescription();
                    if (!theMetaSet.contains(theMetaOrgan))
                    {
                        theMetaSet.add(theMetaOrgan);
                    }
                }

            }

        }


        Iterator theOrganIterator = theOrgans.iterator();

        while (theOrganIterator.hasNext())
        {

            String theOrgan = (String) theOrganIterator.next();

            myTumorSites += "<b>" + theOrgan + "</b><br/>";

            TreeSet theMetaSet = (TreeSet) theMetas.get(theOrgan);

            Iterator theMetaIterator = theMetaSet.iterator();

            while (theMetaIterator.hasNext())
            {
                String theMetaOrgan = (String) theMetaIterator.next();

                myTumorSites += theMetaOrgan + " (Metastasis)<br>";
            }

        }

        return myTumorSites;
    }

    /**
     * Gets the display name of the submitter in an html linked format
     * 
     * @return the display name of the submitter
     * @throws Exception
     */
    public String getSubmitterName() throws Exception
    {

        if (mySubmitterName == null)
        {
            fetchAnimalModel();

            String theEmailAddress = myAnimalModel.getSubmitter().getEmailAddress();

            if (theEmailAddress.length() > 0)
            {
                mySubmitterName = "<a href=\"mailto:" + theEmailAddress + "\"/>" + myAnimalModel.getSubmitter().getDisplayName();
            }
            else
            {
                mySubmitterName = myAnimalModel.getSubmitter().getDisplayName();
            }
        }
        return mySubmitterName;
    }

    /**
     * Gets the date for which the model was submitted
     * 
     * @return the date the model was submitted
     * @throws Exception
     */
    public String getSubmittedDate() throws Exception
    {

        if (mySubmittedDate == null)
        {
            fetchAnimalModel();

            mySubmittedDate = myAnimalModel.getAvailability().getEnteredDate().toString();
        }
        return mySubmittedDate;
    }

    public int compareTo(Object inObject)
    {

        AnimalModelSearchResult theResult = (AnimalModelSearchResult) inObject;

        return this.myModelDescriptor.compareTo(theResult.myModelDescriptor);
    }

    // Fetch the animal model from the DB
    private void fetchAnimalModel() throws Exception
    {
        if (myAnimalModel == null)
        {
            myAnimalModel = AnimalModelManagerSingleton.instance().get(myAnimalModelId);
        }
    }
}
