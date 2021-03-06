    var skin = 'evsMOD';   

    windowTitle = "NCI Center for Bioinformatics";    

    // show tissue tree with fieldToBlank parameter
    function showMouseTissueTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)
    {
      var paramsHT = {};

      paramsHT.treeNameKey = 'MouseTissue';
      paramsHT.formName = form;
      paramsHT.conceptCode = inConceptCode;
      paramsHT.conceptName = inConceptName;
      paramsHT.displayName = inDisplayName;		
      paramsHT.postMsg = false;
      paramsHT.onlyLeaf = onlyLeaf;   
      if (fieldsToBlank != undefined) paramsHT.fieldsToBlank = fieldsToBlank;

      paramsHT.windowTitle = 'Tissue Select';

      showTree(paramsHT);
    }

    // show diagnosis tree with rootConceptNode parameter
    function showMouseDiagnosisTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)
    {
      var paramsHT = {};

      paramsHT.treeNameKey = 'MouseDiagnosis';        
      paramsHT.formName = form;
      paramsHT.conceptCode = inConceptCode;
      paramsHT.conceptName = inConceptName;
      paramsHT.displayName = inDisplayName;
      paramsHT.onlyLeaf = onlyLeaf;           
      paramsHT.windowTitle = 'Diagnosis Select';        
      paramsHT.postMsg = true;
      if (fieldsToBlank != undefined) paramsHT.fieldsToBlank = fieldsToBlank;

      showTree(paramsHT);		
     }

    function showHumanTissueTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)	
    {      
      var paramsHT = {};
     
      paramsHT.treeNameKey = 'HumanTissue';
      paramsHT.formName = form;
      paramsHT.conceptCode = inConceptCode;
      paramsHT.conceptName = inConceptName;
      paramsHT.displayName = inDisplayName;        	
      paramsHT.postMsg = false;
      paramsHT.onlyLeaf = onlyLeaf;        
      if (fieldsToBlank != undefined) paramsHT.fieldsToBlank = fieldsToBlank;

      paramsHT.windowTitle = 'Human Tissue Select';

      showTree(paramsHT);
    }

    function showHumanDiagnosisTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)	
    {      
      var paramsHT = {};
     
      paramsHT.treeNameKey = 'HumanDiagnosis';
      paramsHT.formName = form;
      paramsHT.conceptCode = inConceptCode;
      paramsHT.conceptName = inConceptName;
      paramsHT.displayName = inDisplayName;        	
      paramsHT.postMsg = false;
      paramsHT.onlyLeaf = onlyLeaf;       
      if (fieldsToBlank != undefined) paramsHT.fieldsToBlank = fieldsToBlank;

      paramsHT.windowTitle = 'Human Diagnosis Select';

      showTree(paramsHT);
    }
    
    function showRatTissueTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)	
    {  
        
      var paramsHT = {};
     
      paramsHT.treeNameKey = 'RatTissue';
      paramsHT.formName = form;
      paramsHT.conceptCode = inConceptCode;
      paramsHT.conceptName = inConceptName;
      paramsHT.displayName = inDisplayName;        	
      paramsHT.postMsg = false;
      paramsHT.onlyLeaf = onlyLeaf;       
      if (fieldsToBlank != undefined) paramsHT.fieldsToBlank = fieldsToBlank;

      paramsHT.windowTitle = 'Rat Tissue Select';

      showTree(paramsHT);
    }
    
    function showRatDiagnosisTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)	
    {  
        
      var paramsHT = {};
     
      paramsHT.treeNameKey = 'RatDiagnosis';
      paramsHT.formName = form;
      paramsHT.conceptCode = inConceptCode;
      paramsHT.conceptName = inConceptName;
      paramsHT.displayName = inDisplayName;        	
      paramsHT.postMsg = true;
      paramsHT.onlyLeaf = onlyLeaf;       
      if (fieldsToBlank != undefined) paramsHT.fieldsToBlank = fieldsToBlank;

      paramsHT.windowTitle = 'Rat Diagnosis Select';

      showTree(paramsHT);
    } 
    
    function showStainingMethodTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)	
    {  
        
      var paramsHT = {};
     
      paramsHT.treeNameKey = 'StainingMethod';
      paramsHT.formName = form;
      paramsHT.conceptCode = inConceptCode;
      paramsHT.conceptName = inConceptName;
      paramsHT.displayName = inDisplayName;        	
      paramsHT.postMsg = false;
      paramsHT.onlyLeaf = onlyLeaf;       
      if (fieldsToBlank != undefined) paramsHT.fieldsToBlank = fieldsToBlank;

      paramsHT.windowTitle = 'Staining Method Select';

      showTree(paramsHT);
    }      
    
    function showZebrafishTissueTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)	
    {      
      var paramsHT = {};
     
      paramsHT.treeNameKey = 'ZebrafishTissue';
      paramsHT.formName = form;
      paramsHT.conceptCode = inConceptCode;
      paramsHT.conceptName = inConceptName;
      paramsHT.displayName = inDisplayName;        	
      paramsHT.postMsg = false;
      paramsHT.onlyLeaf = onlyLeaf;        
      if (fieldsToBlank != undefined) paramsHT.fieldsToBlank = fieldsToBlank;

      paramsHT.windowTitle = 'Zebrafish Tissue Select';

      showTree(paramsHT);
    }    

    
    function showZebrafishStageTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)	
    {      
      var paramsHT = {};
     
      paramsHT.treeNameKey = 'ZebrafishStages';
      paramsHT.formName = form;
      paramsHT.conceptCode = inConceptCode;
      paramsHT.conceptName = inConceptName;
      paramsHT.displayName = inDisplayName;        	
      paramsHT.postMsg = false;
      paramsHT.onlyLeaf = onlyLeaf;        
      if (fieldsToBlank != undefined) paramsHT.fieldsToBlank = fieldsToBlank;

      paramsHT.windowTitle = 'Zebrafish Stage Select';

      showTree(paramsHT);
    } 
                      

    
    // new showTree function (dynamically builds name=value parameter string for url from hashtable)
    function showTree(paramsHT) 
    { 
      var now = new Date();
      var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();
      paramsHT.rand = glob;
        
      var treeParams = "";
      for (var paramName in paramsHT) {
         treeParams += paramName+"="+paramsHT[paramName]+";";
      }
      
      targetURL = '/camod/webtree/WebTreeMain.jsp?treeParams='+treeParams+'&skin='+skin;

      // open target window
      windowOpen(targetURL, 1000, 1000, paramsHT.windowTitle);
    }


    // pop-up window utilitiy function
    function windowOpen(url,w,h,title)
    {
      window.name = 'root';
      remote = window.open(url,'popup','width= '+w+',height='+h+', resizable=yes,scrollbars=yes,status=no');
      //remote = window.open(url);
      if (remote != null) {
        remote.title = title;        
        if (remote.opener == null) {        
          remote.opener = self;          
        }
      }
      remote.focus();
    }

    // wrapper functions for caImage project
   function showTissueTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank) {
       showMouseTissueTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)  
   }

   function showDiagnosisTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank) {
     showMouseDiagnosisTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)
   }
