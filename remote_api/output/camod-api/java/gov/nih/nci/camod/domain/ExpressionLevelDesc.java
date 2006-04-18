

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * This class encapsulates the properties of an caBIO ExpressionLevel object. A client can retrieve 
   * information pertaining to a ExpressionLevel. 
   * 
   */

public  class ExpressionLevelDesc 
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public String expressionLevel;
	   public String getExpressionLevel(){
	      return expressionLevel;
	   }
	   public void setExpressionLevel(String expressionLevel){
	      this.expressionLevel = expressionLevel;
	   }
	

	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ExpressionLevelDesc) {
				ExpressionLevelDesc c =(ExpressionLevelDesc)obj; 			 
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