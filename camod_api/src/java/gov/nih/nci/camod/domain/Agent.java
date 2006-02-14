

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * A therapeutic agent (drug, intervention therapy) used in a clinical trail protocol. 
   * 
   */

public  class Agent 
    extends EnvironmentalFactor
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public Long id;
	   public Long getId(){
	      return id;
	   }
	   public void setId(Long id){
	      this.id = id;
	   }
	
	   
	   public Long NSCNumber;
	   public Long getNSCNumber(){
	      return NSCNumber;
	   }
	   public void setNSCNumber(Long NSCNumber){
	      this.NSCNumber = NSCNumber;
	   }
	
	   
	   public Boolean isCMAPAgent;
	   public Boolean getIsCMAPAgent(){
	      return isCMAPAgent;
	   }
	   public void setIsCMAPAgent(Boolean isCMAPAgent){
	      this.isCMAPAgent = isCMAPAgent;
	   }
	
	   
	   public String EVSId;
	   public String getEVSId(){
	      return EVSId;
	   }
	   public void setEVSId(String EVSId){
	      this.EVSId = EVSId;
	   }
	
	   
	   public String comment;
	   public String getComment(){
	      return comment;
	   }
	   public void setComment(String comment){
	      this.comment = comment;
	   }
	
	   
	   public String source;
	   public String getSource(){
	      return source;
	   }
	   public void setSource(String source){
	      this.source = source;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      

			private java.util.Collection chemicalClassCollection = new java.util.HashSet();
			public java.util.Collection getChemicalClassCollection(){
			try{
			   if(chemicalClassCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Agent thisIdSet = new gov.nih.nci.camod.domain.Agent();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.ChemicalClass", thisIdSet);				 
				 	chemicalClassCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Agent:getChemicalClassCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return chemicalClassCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setChemicalClassCollection(java.util.Collection chemicalClassCollection){
	   		this.chemicalClassCollection = chemicalClassCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      

			private java.util.Collection biologicalProcessCollection = new java.util.HashSet();
			public java.util.Collection getBiologicalProcessCollection(){
			try{
			   if(biologicalProcessCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Agent thisIdSet = new gov.nih.nci.camod.domain.Agent();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.BiologicalProcess", thisIdSet);				 
				 	biologicalProcessCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Agent:getBiologicalProcessCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return biologicalProcessCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setBiologicalProcessCollection(java.util.Collection biologicalProcessCollection){
	   		this.biologicalProcessCollection = biologicalProcessCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection agentTargetCollection = new java.util.HashSet();
			public java.util.Collection getAgentTargetCollection(){
			try{
			   if(agentTargetCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Agent thisIdSet = new gov.nih.nci.camod.domain.Agent();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.AgentTarget", thisIdSet);				 
				 	agentTargetCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Agent:getAgentTargetCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return agentTargetCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setAgentTargetCollection(java.util.Collection agentTargetCollection){
	   		this.agentTargetCollection = agentTargetCollection;
	        }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Agent) {
				Agent c =(Agent)obj; 			 
				Long thisId = getId();		
				
					if(thisId != null && thisId.equals(c.getId())) {
					   eq = true;
				    }		
				
			}
			return eq;
		}
 	
		public int hashCode(){
			int h = 0;
			
			if(getId() != null) {
				h += getId().hashCode();
			}
			
			return h;
	}
	
	   
			   
	
}