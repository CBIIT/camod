

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class ExpressionFeature 
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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection organCollection = new java.util.HashSet();
			public java.util.Collection getOrganCollection(){
	              return organCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setOrganCollection(java.util.Collection organCollection){
	   		this.organCollection = organCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.ExpressionLevelDescImpl expressionLevelDesc;
			public gov.nih.nci.camod.domain.ws.ExpressionLevelDescImpl getExpressionLevelDesc(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setExpressionLevelDesc(gov.nih.nci.camod.domain.ws.ExpressionLevelDescImpl expressionLevelDesc){
		this.expressionLevelDesc = expressionLevelDesc;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ExpressionFeature) {
				ExpressionFeature c =(ExpressionFeature)obj; 			 
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
