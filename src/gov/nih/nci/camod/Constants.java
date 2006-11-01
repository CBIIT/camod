/**
 * 
 * $Id: Constants.java,v 1.93 2006-11-01 21:19:14 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.92  2006/10/27 13:02:24  pandyas
 * added constant used for online help - removed the onclick for ToolTips
 *
 * Revision 1.91  2006/10/17 16:14:49  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.90  2006/09/20 16:13:03  pandyas
 * updated with a few caMOD 2.2 constants - will not hurt if unused on dev server
 *
 * Revision 1.88  2006/08/17 17:45:03  pandyas
 * Defect# 410: Deleted constants no longer used due to externalized property  files
 *
 * Revision 1.87  2006/05/24 16:45:55  pandyas
 * Converted StainingMethod to lookup - modified code to pull dropdown list from DB
 *
 * Revision 1.86  2006/05/17 16:12:34  pandyas
 * added better comments
 *
 * Revision 1.85  2006/05/10 15:37:36  schroedn
 * Fixed Dup_Name bug
 *
 * Revision 1.84  2006/05/10 13:28:51  schroedn
 * New Features - Code Review changes
 *
 * Revision 1.83  2006/05/09 18:45:47  georgeda
 * Changes for searching on transient interfaces
 *
 * Revision 1.82  2006/05/04 14:15:33  pandyas
 * Modified/Added to support Morpholino object data in the application
 *
 * Revision 1.81  2006/04/28 19:03:57  schroedn
 * Defect # 238, 261, 55
 * Added Constants used Saving/Editing Queries, Keyword Highlighting and Configuring search result columns
 *
 * Revision 1.80  2006/04/20 19:45:31  pandyas
 * Added constant for otherStrainName used on submitXenograftTransplant.jsp
 *
 * Revision 1.79  2006/04/17 19:15:36  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.78  2005/12/06 19:51:25  georgeda
 * Defect #255 - add SSL
 *
 * Revision 1.77  2005/11/28 18:02:10  georgeda
 * Defect #182.  Get unique set of organs and only display metas. next to the originating organ
 *
 * Revision 1.76  2005/11/28 13:41:35  georgeda
 * Defect #192, handle back arrow for curation changes
 *
 * Revision 1.75  2005/11/18 21:04:54  georgeda
 * Defect #130, added superuser
 *
 * Revision 1.74  2005/11/17 20:42:03  schroedn
 * Defect #93
 *
 * Fixed CAIMAGEGENCONSERVERVIEW variable to point to correct property in camod.properties. This allows for GeneticConstruts to be saved in correct directory on caImage server.
 *
 * Revision 1.73  2005/11/14 14:15:37  georgeda
 * Cleanup
 *
 * Revision 1.72  2005/11/11 21:24:19  georgeda
 * Defect #29.  Separate drug screening and animal model search results.
 *
 * Revision 1.71  2005/11/11 15:37:57  georgeda
 * Fixed IE error with action name
 *
 * Revision 1.70  2005/11/08 21:59:31  georgeda
 * LDAP code
 *
 * Revision 1.69  2005/11/08 17:46:20  pandyas
 * added for Xenograft
 *
 * Revision 1.68  2005/11/08 16:46:33  georgeda
 * Changes for images
 *
 * Revision 1.67  2005/11/07 21:55:10  georgeda
 * Changes for images
 *
 * Revision 1.66  2005/11/03 13:57:58  georgeda
 * Delete functionality changes
 *
 * Revision 1.65  2005/11/02 20:56:04  schroedn
 * Added Staining to Image submission
 *
 * Revision 1.64  2005/11/02 20:28:59  pandyas
 * modified GeneDelivery dropdown source
 *
 * Revision 1.63  2005/11/02 17:15:58  schroedn
 * Updated Image viewer, added constants and properties to camod.properties, merged code to ease changes later
 *
 * Revision 1.62  2005/11/02 16:33:41  georgeda
 * Misc fixes
 *
 * Revision 1.61  2005/10/28 12:47:11  georgeda
 * Action constant
 *
 * Revision 1.60  2005/10/27 18:31:50  georgeda
 * New dropdown options
 *
 * Revision 1.59  2005/10/27 17:13:19  guruswas
 * added publications to capture all publications
 *
 * Revision 1.58  2005/10/27 15:29:59  georgeda
 * Cleanup
 *
 * Revision 1.57  2005/10/26 20:40:30  schroedn
 * Added AssocExpression to EngineeredTransgene submission page
 *
 * Revision 1.56  2005/10/24 22:00:48  pandyas
 * added back availability_list so it doesn't break everyone else
 *
 * Revision 1.55  2005/10/24 21:16:59  pandyas
 * added availability constants
 *
 * Revision 1.54  2005/10/24 21:04:03  schroedn
 * Added Image to submission
 *
 * Revision 1.53  2005/10/24 18:44:41  georgeda
 * Do species from dropdown
 *
 * Revision 1.52  2005/10/24 13:26:28  georgeda
 * Cleanup changes
 *
 * Revision 1.51  2005/10/21 20:46:21  georgeda
 * Added user registration settings
 *
 * Revision 1.50  2005/10/21 19:36:56  schroedn
 * Added Constants for Image upload and retrieval
 *
 * Revision 1.49  2005/10/20 21:35:22  georgeda
 * Added xenograft constant
 *
 * Revision 1.48  2005/10/20 21:20:17  pandyas
 * add animal availability list
 *
 * Revision 1.47  2005/10/20 21:14:15  stewardd
 * added constants used in e-mail generation of InducedMutationManagerImpl and TargetedModificationManagerImpl classe.
 *
 * Revision 1.46  2005/10/20 19:28:28  georgeda
 * Added TOC constants
 *
 * Revision 1.45  2005/10/19 18:56:26  guruswas
 * implemented invivo details page
 *
 * Revision 1.44  2005/10/17 13:25:17  georgeda
 * Work for comments/users
 *
 * Revision 1.43  2005/10/13 16:18:51  pandyas
 * added constant for growth factor dose units
 *
 * Revision 1.42  2005/10/11 20:51:12  schroedn
 * Added constant for ENGINEEREDTRANSGENE_LIST
 *
 * Revision 1.41  2005/10/11 19:56:19  pandyas
 * added constant for assc met list
 *
 * Revision 1.40  2005/10/11 18:12:08  georgeda
 * More comment changes
 *
 * Revision 1.39  2005/10/10 14:05:38  georgeda
 * Cleanup and additions for comment curation
 *
 * Revision 1.37  2005/10/05 20:27:59  guruswas
 * implementation of drug screening search page
 *
 * Revision 1.36  2005/10/05 19:24:14  pandyas
 * added clinical marker list
 *
 * Revision 1.35  2005/10/05 16:21:50  pandyas
 * added histopthology and therapy lists
 *
 * Revision 1.34  2005/10/04 20:18:48  georgeda
 * Updates from search changes
 *
 * Revision 1.33  2005/10/04 20:09:41  schroedn
 * Added Spontaneous Mutation, InducedMutation, Histopathology, TargetedModification and GenomicSegment
 *
 * Revision 1.32  2005/10/03 16:07:39  pandyas
 * modified histopathology constant name to reflect contents
 *
 * Revision 1.31  2005/10/03 15:31:00  pandyas
 * added clinical marker and histopathology constants
 *
 * Revision 1.30  2005/10/03 13:04:19  georgeda
 * Updates from search changes
 *
 * Revision 1.29  2005/09/30 18:47:46  pandyas
 * added all differences to my copy before uploading
 *
 * Revision 1.25  2005/09/27 16:34:31  georgeda
 * Changed administravive route drop down
 *
 *
 */
package gov.nih.nci.camod;


/**
 * Constant values used throughout the application.
 * 
 * <p>
 * <a href="Constants.java.html"><i>View Source</i></a>
 * </p>
 */
public class Constants {
	

    /** The name of the ResourceBundle used in this application */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /** The application scoped attribute for persistence engine used */
    public static final String DAO_TYPE = "daoType";

    public static final String DAO_TYPE_HIBERNATE = "hibernate";

    /** Application scoped attributes for SSL Switching */
    public static final String HTTP_PORT = "httpPort";

    public static final String HTTPS_PORT = "httpsPort";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "admin";

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    public static final String UPT_CONTEXT_NAME = "camod";
    
    public static final String CONDITIONAL = "Conditional";
    
    public static final String NOT_CONDITIONAL = "Not Conditional";
    
    public static final String SEARCHRESULTCOLUMNS = "SearchResultsColumns";        
      
    public static final String ITEMSPERPAGE = "ItemsPerPage";
    
    public static final int ITEMSPERPAGEDEFAULT = 15;
    
    public static final String[] SEARCHRESULTCOLUMNSDEFAULT = { "Model Descriptor", "Tumor Sites", "Species"  }; 
    
    public static final String SELECTEDSEARCHRESULTCOLUMNS = "selectedsearchresultcolumns";
    
    public static final String CRITERIATABLE = "criteriatable";
    
    public static final String NOSAVEOPTION = "nosaveoption";
        
    
    /**
     * Used to store list of models currently logged on user has previous
     * entered
     */
    public static final String USERMODELLIST = "usermodellist";
    
    /**
     * Used to store the species for the animal model 
     * used to disable the Organ/Disease tree for non-mouse models.
     */ 
    public static final String AMMODELSPECIES = "animalmodelspecies";    


    /**
     * Used to store lists for drop down menus
     */
    public interface CaArray {

        public static final String URI_START = "caarray.uri_start";

        public static final String URI_END = "caarray.uri_end";
    }

    /**
     * Used in table of contents searching
     */
    public interface TOCSearch {

        public static final String TOC_QUERY_FILE = "config/TOCQueryConfig.xml";

        public static final String TOC_QUERY_RESULTS = "TOC_QUERY_RESULTS";

    }

    /**
     * 
     * Used in AnimalModelSearchResult
     *
     */
    public interface ENVFactors {
        
        public static final String TRANSCRIPTIONAL1 = "Transcriptional 1"; 
        
        public static final String CHEMICAL_DRUG = "Chemical / Drug";
        
        public static final String HORMONE = "Hormone";
        
        public static final String GROWTH_FACTOR = "Growth Factor";
        
        public static final String VIRAL = "Viral";
        
        public static final String ENVIRONMOENT = "Environment";
        
        public static final String NUTRITION = "Nutrition";
        
        public static final String VIRUS = "Virus";
        
        public static final String OTHER = "Other";
        
    }
    
    /**
     * Used to store lists for drop down menus
     */
    public interface Dropdowns {

        public static final String ADD_BLANK = "ADD_BLANK";

        public static final String ADD_OTHER = "ADD_OTHER";

        public static final String ADD_BLANK_AND_OTHER = "ADD_BLANK_AND_OTHER";

        public static final String ADD_BLANK_OPTION = "ADD_BLANK_OPTION";

        public static final String ADD_OTHER_OPTION = "ADD_OTHER_OPTION";

        public static final String ADD_BLANK_AND_OTHER_OPTION = "ADD_BLANK_AND_OTHER_OPTION";

        public static final String OTHER_OPTION = "Other";
        
        public static final String NOT_SPECIFIED_OPTION = "Not specified";
        
        /* 3 species from config - still used */
        public static final String NEWSPECIESDROP = "ModelSpecies.txt";        

        /* 3 species from db for search screens - not implemented yet */
        public static final String SPECIESDROP = "speciesdrop.db";
        
        // All species from config - may not be used anymore
        public static final String HOSTSPECIESDROP = "HostSpecies.txt";        

        /* all species from DB -  used for various Screens */
        public static final String SPECIESQUERYDROP = "speciesquerydrop.db";        
        
        public static final String STRAINTEXTDROP = "StrainText.txt";
        
        /* all strains for a species from DB -  used for various Screens */
        public static final String STRAINDROP = "straindrop.db";  

        public static final String SEXDISTRIBUTIONDROP = "SexDistributions.txt";

        public static final String DOSAGEUNITSDROP = "DoseUnits.txt";

        public static final String ADMINISTRATIVEROUTEDROP = "AdministrativeRoutes.txt";

        public static final String AGEUNITSDROP = "AgeUnits.txt";

        public static final String PUBDROP = "PublicationStatus.txt";

        public static final String TOXICITYGRADESDROP = "ToxicityGrades.txt";

        public static final String CLINICALMARKERSDROP = "ClinicalMarkers.txt";



        // Various Dose Units
        public static final String CONCENTRATIONUNITSDROP = "ConcentrationUnits.txt";
        
        public static final String CHEMTHERAPYDOSEUNITSDROP = "ChemTherapyDoseUnits.txt";

        public static final String ENVFACTORUNITSDROP = "EnvFactorUnits.txt";

        public static final String GENOMESEGSIZEUNITSDROP = "GenomeSegSizeUnits.txt";

        public static final String HISTOPATHVOLUMEUNITSDROP = "HistopathVolumeUnits.txt";

        public static final String HISTOPATHWEIGHTUNITSDROP = "HistopathWeightUnits.txt";

        public static final String HORMONEUNITSDROP = "HormoneUnits.txt";

        public static final String NUTFACTORUNITSDROP = "NutFactorUnits.txt";

        public static final String RADIATIONUNITSDROP = "RadiationUnits.txt";

        public static final String VIRALTREATUNITSDROP = "ViralTreatUnits.txt";

        public static final String TARGETEDMODIFICATIONDROP = "TargetedModificationTypes.txt";

        public static final String GENOMICSEGMENTDROP = "SegmentTypes.txt";

        public static final String GROWTHFACTORDOSEUNITSDROP = "GrowthFactorDoseUnits.txt";

        public static final String STAININGDROP = "Staining.db";

        // Specific to a single screen
        public static final String PRINCIPALINVESTIGATORDROP = "principalinvestigatordrop.db";
        
        public static final String CHEMICALDRUGDROP = "chemdrugdrop.db";

        public static final String ENVIRONFACTORDROP = "envfactordrop.db";

        public static final String GROWTHFACTORDROP = "growfactordrop.db";

        public static final String HORMONEDROP = "hormonedrop.db";

        public static final String NUTRITIONFACTORDROP = "nutritionfactordrop.db";

        public static final String RADIATIONDROP = "radiationdrop.db";

        public static final String SURGERYDROP = "surgerydrop.db";

        public static final String VIRUSDROP = "virusdrop.db";

        public static final String VIRALVECTORDROP = "ViralVectors.txt";

        public static final String GRAFTTYPEDROP = "GraftTypes.txt";

        public static final String XENOGRAFTADMINSITESDROP = "XenograftAdministrativeSites.txt";

        public static final String INDUCEDMUTATIONDROP = "InducedMutations.txt";

        public static final String EXPRESSIONLEVELDROP = "expressionlevel.db";

        // Morpholino/siRNA screen dropdowns
        public static final String MORPHOSOURCEDROP = "MorpholinoSources.txt";
        public static final String SIRNASOURCEDROP = "sirnaSources.txt";
        
        public static final String MORPHOTYPEDROP = "MorpholinoTypes.txt";
        public static final String SIRNATYPEDROP = "sirnaTypes.txt";
        
        public static final String SEQUENCEDIRECTIONSDROP = "SequenceDirections.txt";
        
        public static final String DELIVERYMETHODDROP = "DeliveryMethods.txt";
        public static final String SIRNADELIVMETHODDROP = "sirnaDeliveryMethods.txt";
        
        public static final String VISUALLIGANDSDROP = "VisualLigands.txt";
        public static final String SIRNAVISUALLIGANDSDROP = "sirnaVisualLigands.txt";
        
        
        
        public static final String SEARCHRESULTCOLUMNSDROP = "SearchResultsColumns.txt";        
        
        public static final String ITEMSPERPAGEDROP = "ItemsPerPage.txt";
        
        // Query dropdowns
        public static final String PRINCIPALINVESTIGATORQUERYDROP = "principalinvestigatorquerydrop.db";
        
        public static final String CHEMICALDRUGQUERYDROP = "chemdrugquerydrop.db";

        public static final String GROWTHFACTORQUERYDROP = "growfactorquerydrop.db";

        public static final String HORMONEQUERYDROP = "hormonequerydrop.db";

        public static final String RADIATIONQUERYDROP = "radiationquerydrop.db";

        public static final String VIRUSQUERYDROP = "virusquerydrop.db";

        public static final String SURGERYQUERYDROP = "surgeryquerydrop.db";

        public static final String INDUCEDMUTATIONAGENTQUERYDROP = "inducedmutationagentquerydrop.db";
        
        public static final String EXTERNALSOURCEQUERYDROP = "externalsourcequerydrop.db";        

        // These two are used to display the species and strain currently in the
        // AnimalModelCharacteristics
        public static final String MODELSPECIES = "modelspecies";

        public static final String MODELSTRAIN = "modelstrain";
        
        public static final String OTHERMODELSTRAIN = "othermodelstrain";        

        public static final String CHEMICALCLASSESDROP = "ChemicalClasses.txt";

        public static final String BIOLOGICALPROCESSDROP = "BiologicalProcess.txt";

        public static final String THERAPEUTICTARGETSDROP = "TherapeuticTargets.txt";

        // Used for user management
        public static final String USERSDROP = "users.db";

        // Used for curation
        public static final String CURATIONSTATESDROP = "curationstates.db";

        // Used for curation
        public static final String USERSFORROLEDROP = "usersforrole.db";

        // Used for role assignment
        public static final String ROLESDROP = "roles.db";        
      
    }

    /**
     * Defines the global constants used as parameters for ftp requests
     */
    public interface CaImage {
        public static final String FTPSERVER = "caimage.ftp.server";
        public static final String FTPUSERNAME = "caimage.ftp.username";
        public static final String FTPPASSWORD = "caimage.ftp.password";
        public static final String FTPMODELSTORAGEDIRECTORY = "caimage.ftp.modelstoragedirectory";
        public static final String FTPGENCONSTORAGEDIRECTORY = "caimage.ftp.genconstoragedirectory";
        public static final String CAIMAGEMODELSERVERVIEW = "caimage.modelview.uri";
        public static final String CAIMAGEGENCONSERVERVIEW = "caimage.genconview.uri";
        public static final String CAIMAGESIDTHUMBVIEW = "caimage.sidthumbview.uri";
        public static final String CAIMAGESIDVIEWURISTART = "caimage.sidview.uri_start";
        public static final String CAIMAGESIDVIEWURIEND = "caimage.sidview.uri_end";
        public static final String CAIMAGEWINDOWSTART = "caimage.window.start";
        public static final String CAIMAGEWINDOWEND = "caimage.window.end";
        public static final String CAIMAGEMODEL = "caimage.model";
        public static final String CAIMAGEGENCON = "caimage.gencon";
        public static final String LEGACYJSP = "catalogviewtumors.jsp?";
        public static final String FILESEP = ";";
        public static final String IMGTAG = "img=";
    }

    public interface Ldap {
        public static final String INITIAL_CONTEXT_FACTORY_KEY = "ldap.initial.context.factory";
        public static final String PROVIDER_URL_KEY = "ldap.provider.url";
        public static final String SECURITY_AUTHENTICATION_KEY = "ldap.security.authentication";
        public static final String SECURITY_PROTOCOL_KEY = "ldap.security.protocol";
        public static final String CONTEXT_KEY = "ldap.context";
    }

    /**
     * Defines the global constants used as parameters to requests
     */
    public interface Parameters {

        public static final String ACTION = "submitAction";

        public static final String MODELID = "aModelID";
        
        public static final String PUBID = "APubID";        
        
        public static final String CELLID = "ACellID";         

        public static final String PERSONID = "aPersonID";

        public static final String QUERYID = "aQueryId";
        
        public static final String MODELSECTIONNAME = "aModelSectionName";

        public static final String MODELSECTIONVALUE = "modelSectionValue";

        public static final String COMMENTSID = "aCommentsID";

        public static final String COMMENTSLIST = "aCommentsList";

        public static final String TOCQUERYKEY = "aTOCQueryKey";

        public static final String EVENT = "aEvent";

        public static final String DELETED = "deleted";
    }

    public interface Pages {

        public static final String MODEL_CHARACTERISTICS = "General Information Page";

        public static final String CARCINOGENIC_INTERVENTION = "Carcinogenic Interventions Page";

        public static final String PUBLICATIONS = "Publications page";

        public static final String HISTOPATHOLOGY = "Histopathology Page";

        public static final String THERAPEUTIC_APPROACHES = "Therapeutic Approaches Page";

        public static final String CELL_LINES = "Cell Lines Page";

        public static final String IMAGES = "Images Page";

        public static final String MICROARRAY = "Microarray Page";

        public static final String GENETIC_DESCRIPTION = "Genetic Description Page";

        public static final String XENOGRAFT = "Xenograft Page";
        
        public static final String TRANSIENT_INTERFERENCE = "Transient Interference Page";
    }

    /**
     * Used to determine the current model to edit on submission/edit also used
     * to display the name of the model and it's current status
     */
    public static final String MODELID = "modelid";

    public static final String MODELDESCRIPTOR = "modeldescriptor";

    public static final String MODELSTATUS = "modelstatus";

    /**
     * Used to prepopulate forms
     */
    public static final String FORMDATA = "formdata";

    public static final String ANIMALMODEL = "animalmodel";

    public static final String XENOGRAFTMODEL = "xenograftmodel";

    public static final String XENOGRAFTRESULTLIST = "xenograftresultlist";

    /**
     * Used to store username for current user
     */
    public static final String CURRENTUSER = "camod.loggedon.username";

    public static final String CURRENTUSERROLES = "camod.loggedon.userroles";

    public static final String LOGINFAILED = "loginfailed";
    
    public static final String NOTLOGGEDIN = "notloggedin";

    /**
     * Used for search results
     */
    public static final String KEYWORD_HIGHLIGHT = "keywordhighlight";
    
    public static final String SEARCH_QUERY = "searchquery";
    
    public static final String SEARCH_RESULTS = "searchResults";
    
    public static final String DUP_NAME = "dupname";
    
    public static final String DUP_NAME_VALUE =  "dupnamevalue";
    
    public static final String QUERY_NAME = "queryname";
    
    public static final String ERRORMESSAGE = "errormessage";
    
    public static final String SEARCH_FORM = "searchform";
    
    public static final String EXECUTE_TIME = "execute_time";
    
    public static final String ELAPSED_TIME = "elapsed_time";
    
    public static final String RERUN_QUERY = "rerunquery";
    
    public static final String USERSAVEDQUERYLIST = "usersavedquerylist";
    
    public static final String USERSQUERYLIST = "userquerylist";
    
    public static final String QUERYHISTORYID = "queryHistoryId"; 
    
    public static final String NUMBEROFSAVEDQUERIES = "numberofsavedqueries";    
    
    public static final String AQUERYID = "aqueryid";
    
    public static final String ASAVEDQUERYID = "asavedqueryid";
    
    public static final String DRUG_SCREEN_SEARCH_RESULTS = "drugScreenSearchResults";

    public static final String ADMIN_COMMENTS_SEARCH_RESULTS = "adminCommentsSearchResults";

    public static final String ADMIN_MODEL_SEARCH_RESULTS = "adminModelSearchResults";

    public static final String ADMIN_ROLES_SEARCH_RESULTS = "adminRolesSearchResults";

    public static final String TRANSGENE_COLL = "transgeneColl";

    public static final String GENOMIC_SEG_COLL = "genomicSegColl";

    public static final String TARGETED_MOD_COLL = "targetedModColl";

    public static final String TARGETED_MOD_GENE_MAP = "targetedModGeneMap";

    public static final String INDUCED_MUT_COLL = "inducedMutColl";

    public static final String SPONTANEOUS_MUT_COLL = "spontaneousMutColl";
    
    public static final String TRANSGENE_CNT = "transgeneCnt";

    public static final String GENOMIC_SEG_CNT = "genomicSegCnt";

    public static final String TARGETED_MOD_CNT = "targetedModCnt";

    public static final String INDUCED_MUT_CNT = "inducedMutCnt";
    
    public static final String THERAPEUTIC_APPROACHES_COLL = "therapeuticApproachesColl";
    
    public static final String CLINICAL_PROTOCOLS = "clinProtocols";
    
    public static final String YEAST_DATA = "yeastData";
    
    public static final String INVIVO_DATA = "invivoData";
    
    public static final String PRECLINICAL_MODELS = "preClinicalModels";
    
    public static final String PUBLICATIONS = "publications";
    
    public static final String CARCINOGENIC_INTERVENTIONS_COLL = "carcinogenicInterventionColl";
    
    public static final String DRUG_SCREEN_OPTIONS = "drugScreenSearchOptions";
    
    public static final String NSC_NUMBER = "nsc";
    

    // /////////////////////////////////////////////////////////////
    // Submission specific constants
    // /////////////////////////////////////////////////////////////

    public interface Submit {

        /**
         * Used to store required lists for the cardiogentic intervention
         * section of the sidebar menu of the submission section
         */    
        public static final String CHEMICALDRUG_LIST = "chemicaldrug_list";

        public static final String ENVIRONMENTALFACTOR_LIST = "environmentalfactor_list";

        public static final String GENEDELIVERY_LIST = "genedelivery_list";

        public static final String GROWTHFACTORS_LIST = "growthfactors_list";

        public static final String HORMONE_LIST = "hormone_list";

        public static final String NUTRITIONALFACTORS_LIST = "nutritionalfactors_list";

        public static final String RADIATION_LIST = "radiation_list";

        public static final String SURGERYOTHER_LIST = "surgeryother_list";

        public static final String VIRALTREATMENT_LIST = "viraltreatment_list";

        public static final String XENOGRAFT_LIST = "xenograft_list";

        public static final String SPONTANEOUSMUTATION_LIST = "spontaneousmutation_list";

        public static final String INDUCEDMUTATION_LIST = "inducedmutation_list";

        public static final String TARGETEDMODIFICATION_LIST = "targetedmodification_list";

        public static final String GENOMICSEGMENT_LIST = "genomicsegment_list";

        public static final String HISTOPATHOLOGY_LIST = "histopathology_list";

        public static final String ASSOCMETASTSIS_LIST = "associatedmetastatis_list";

        public static final String ENGINEEREDTRANSGENE_LIST = "engineeredtransgene_list";

        public static final String THERAPY_LIST = "therapy_list";

        public static final String CLINICALMARKER_LIST = "clinicalmarker_list";

        public static final String IMAGE_LIST = "image_list";

        public static final String ASSOCIATEDEXPRESSION_LIST = "associatedexpression_list";
        /**
         * Used to store a list of names for the Publication section of the
         * sidebar menu of the submission section
         */
        public static final String PUBLICATION_LIST = "publication_list";

        /**
         * Used to store a list of names for the Cell Line section of the
         * sidebar menu of the submission section
         */
        public static final String CELLLINE_LIST = "cellline_list";

        public static final String ANIMALAVAILABILITY_LIST = "availability_list";

        /**
         * Used to store animal model availability for the Model Availability
         * section of the sidebar menu of the submission section
         */
        public static final String INVESTIGATOR_LIST = "investigator_list";
        public static final String JACKSONLAB_LIST = "jacksonlab_list";
        public static final String MMHCC_LIST = "mmhcc_list";
        public static final String IMSR_LIST = "imsr_list";
        /**
         * Used to store animal model availability for the Transient Interference 
         * section of the sidebar menu of the submission section
         */        
        public static final String MORPHOLINO_LIST = "morpholino_list";
        public static final String SIRNA_LIST = "sirna_list";
    }

    // /////////////////////////////////////////////////////////////
    // Admin specific constants
    // /////////////////////////////////////////////////////////////

    public interface Admin {

        /**
         * Defines the different roles in the system
         */
        public interface Roles {

            /**
             * A constant that defines the submitter role
             */
            public static final String ALL = "All";

            /**
             * A constant that defines the submitter role
             */
            public static final String SUBMITTER = "Public Submitter";

            /**
             * A constant that defines the coordinator role
             */
            public static final String COORDINATOR = "MMHCC Coordinator";

            /**
             * A constant that defines the coordinator role
             */
            public static final String SUPER_USER = "MMHCC SuperUser";
            
            /**
             * A constant that defines the Editor role
             */
            public static final String EDITOR = "MMHCC Editor";

            /**
             * A constant that defines the screener role
             */
            public static final String SCREENER = "MMHCC Screener";
        }

        /**
         * Defines the different roles in the system
         */
        public interface Actions {
            /**
             * A constant that defines the text for the generic approved action
             */
            public static final String SCREENER_APPROVE = "screener_approve";

            /**
             * A constant that defines the text for the generic approved action
             */
            public static final String EDITOR_APPROVE = "editor_approve";
            
            /**
             * A constant that defines the text for the assign editor action
             */
            public static final String ASSIGN_EDITOR = "assign_editor";

            /**
             * A constant that defines the text for the assign screener action
             */
            public static final String ASSIGN_SCREENER = "assign_screener";

            /**
             * A constant that defines the text for the need more information
             * action
             */
            public static final String NEED_MORE_INFO = "need_more_info";

            /**
             * A constant that defines the text for the generic reject action
             */
            public static final String SCREENER_REJECT = "screener_reject";

            /**
             * A constant that defines the text for the complete action
             */
            public static final String COMPLETE = "complete";

            /**
             * A constant that defines the text for the complete action from submitOverview
             */
            public static final String BACK_TO_COMPLETE = "back_to_complete";

            /**
             * A constant that defines the text for the screener_approve action from submitOverview
             */            
            public static final String BACK_TO_SCREENER_APPROVE = "back_to_screener_approve";

            /**
             * A constant that defines the text for the inactive action
             */
            public static final String INACTIVATE = "inactivate";            
        }
        
        /**
         * Defines the different states for a model
         */
        public interface ModelState {
        	
            /**
             * A constant that defines the text for the inactive model state
             */
            public static final String INACTIVE = "Inactive";
            
            /**
             * A constant that defines the text for the Incomplete model state
             * used to set state after duplicating a model from adminEditModels and submitModels
             */
            public static final String INCOMPLETE = "Incomplete";            
        }

        /**
         * A constant that defines string used as a variable name in e-mail
         */
        public static final String INDUCED_MUTATION_AGENT_NAME = "inducedmutationagentname";

        /**
         * A constant that defines string used as a variable name in e-mail
         */
        public static final String INDUCED_MUTATION_AGENT_TYPE = "inducedmutationagenttype";

        /**
         * A constant that defines string used as key for e-mail content
         * associated with induced mutation agent additions
         */
        public static final String INDUCED_MUTATION_AGENT_ADDED = "inducedmutationagentadded";

        /**
         * A constant that defines string used as a variable name in e-mail
         */
        public static final String TARGETED_MODIFICATION_NAME = "targetedmodificationname";

        /**
         * A constant that defines string used as a variable name in e-mail
         */
        public static final String TARGETED_MODIFICATION_TYPE = "targetedmodificationtype";

        /**
         * A constant that defines string used as key for e-mail content
         * associated with targeted modification additions
         */
        public static final String TARGETED_MODIFICATION_ADDED = "targetedmodificationadded";

        /**
         * A constant that defines string used as key for e-mail content
         * associated with non-controlled vocabulary use
         */
        public static final String NONCONTROLLED_VOCABULARY = "noncontrolledvocab";

        /**
         * A constant that defines what file is used for the model curation
         * process
         */
        public static final String MODEL_CURATION_WORKFLOW = "config/CurationConfig.xml";

        /**
         * A constant that defines what file is used for the comment curation
         * process
         */
        public static final String COMMENT_CURATION_WORKFLOW = "config/CommentCurationConfig.xml";

        /**
         * Used to set/pull the objects needing to be reviewed out of the
         * request
         */
        public static final String COMMENTS_NEEDING_REVIEW = "commentsNeedingReview";

        /**
         * Used to set/pull the objects needing to be reviewed out of the
         * request
         */
        public static final String COMMENTS_NEEDING_ASSIGNMENT = "commentsNeedingAssignment";

        /** Used to set/pull the objects needing to be edited out of the request */
        public static final String MODELS_NEEDING_EDITING = "modelsNeedingEditing";

        /**
         * Used to set/pull the objects needing to be assigned an editor out of
         * the request
         */
        public static final String MODELS_NEEDING_EDITOR_ASSIGNMENT = "modelsNeedingEditorAssignment";

        /** Used to set/pull the objects needing to be edited out of the request */
        public static final String MODELS_NEEDING_MORE_INFO = "modelsNeedingMoreInfo";

        /**
         * Used to set/pull the objects needing to be screened out of the
         * request
         */
        public static final String MODELS_NEEDING_SCREENING = "modelsNeedingScreening";

        /**
         * Used to set/pull the objects needing to be assigned a screener out of
         * the request
         */
        public static final String MODELS_NEEDING_SCREENER_ASSIGNMENT = "modelsNeedingScreenerAssignment";
        /**
         * Used to set/pull the objects inactivated out of
         * the request
         */
        public static final String MODELS_INACTIVATED = "modelsInactivated";
        
    }

    public interface EmailMessage {

        public static final String SENDER = "email.sender";

        public static final String RECIPIENTS = "email.recipients";

        public static final String FROM = "email.from";

        public static final String MESSAGE = "email.message";

        public static final String SUBJECT = "email.subject";

    }

    /**
     * 
     * Constants used for fetching EVS data
     * 
     */
    public interface Evs {

        /**
         * The namespace to fetch the concepts from
         */
        public static final String NAMESPACE = "NCI_Thesaurus";

        /**
         * The tag used to get the display name
         */
        public static final String DISPLAY_NAME_TAG = "Display_Name";

        /**
         * The key for the URI in the camod.properties file
         */
        public static final String URI_KEY = "evs.uri";
    }
    
    /**
     * 
     * Constants used for fetching EVS data
     * 
     */
    public interface OnlineHelp {

        /**
         * The namespace to fetch the concepts from
         */
        public static final String SKIP = "skip";
    }
}
