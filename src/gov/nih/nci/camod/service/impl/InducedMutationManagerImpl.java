/**
 * @author schroedln
 * 
 * $Id: InducedMutationManagerImpl.java,v 1.6 2005-10-20 21:12:53 stewardd Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/10/12 20:10:49  schroedn
 * Added Validation
 *
 * Revision 1.4  2005/10/06 20:41:49  schroedn
 * InducedMutation, TargetedMutation, GenomicSegment changes
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.domain.GeneticAlteration;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.service.InducedMutationManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.InducedMutationData;

import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InducedMutationManagerImpl extends BaseManager implements InducedMutationManager {
	
    public List getAll() throws Exception {
        log.trace("In InducedMutationManagerImpl.getAll");
        return super.getAll(InducedMutation.class);
    }

    public InducedMutation get(String id) throws Exception {
        log.trace("In InducedMutationManagerImpl.get");
        return (InducedMutation) super.get(id, InducedMutation.class);
    }

    public void save(InducedMutation InducedMutation) throws Exception {
        log.trace("In InducedMutationManagerImpl.save");
        super.save(InducedMutation);
    }

    public void remove(String id) throws Exception {
        log.trace("In InducedMutationManagerImpl.remove");
        super.remove(id, InducedMutation.class);
    }

    public InducedMutation create(InducedMutationData inInducedMutationData) throws Exception {

        log.trace("Entering InducedMutationManagerImpl.create");

        InducedMutation theInducedMutation = new InducedMutation();

        populateInducedMutation(inInducedMutationData, theInducedMutation);
       
        log.trace("Exiting InducedMutationManagerImpl.create");
        
        return theInducedMutation;
    }

    public void update(InducedMutationData inInducedMutationData, InducedMutation inInducedMutation)
            throws Exception {

        log.trace( "Entering InducedMutationManagerImpl.update" );
        log.debug( "Updating InducedMutationForm: " + inInducedMutation.getId() );

        // Populate w/ the new values and save
        populateInducedMutation(inInducedMutationData, inInducedMutation);
        save(inInducedMutation);

        log.trace("Exiting InducedMutationManagerImpl.update");
    }

    private void populateInducedMutation(InducedMutationData inInducedMutationData, InducedMutation inInducedMutation)
            throws Exception {
    	
        log.trace("Entering populateInducedMutation");
        
        EnvironmentalFactor inEnvironFactor = null;
        
        //Check to see if a Environmental Factor already exists, 
        //if it does edit it else create a new EnvironmentalFactor
        if ( inInducedMutation.getEnvironmentalFactorCollection().size() > 0 )
        	inEnvironFactor = (EnvironmentalFactor) inInducedMutation.getEnvironmentalFactorCollection().get(0);
        else
        	inEnvironFactor = new EnvironmentalFactor();
        
        //Inducing Agent Category type
        inEnvironFactor.setType( inInducedMutationData.getType() );
        
        //Other type
        if (  inInducedMutationData.getOtherType() != null ) {
	        if (!inInducedMutationData.getOtherType().equals("")) {
	
	            log.trace("Sending Notification eMail - new InducedMutation Agent added");
	            ResourceBundle theBundle = ResourceBundle.getBundle("camod");
	
	            //Iterate through all the reciepts in the config file
	            String recipients = theBundle.getString(Constants.EmailMessage.RECIPIENTS);
	            StringTokenizer st = new StringTokenizer(recipients, ",");
	            String inRecipients[] = new String[st.countTokens()];
	
	            for (int i = 0; i < inRecipients.length; i++)
	                inRecipients[i] = st.nextToken();
	
	            String inSubject = theBundle.getString(Constants.EmailMessage.SUBJECT);
	            String inMessage = theBundle.getString(Constants.EmailMessage.MESSAGE) + " Inducing Agent Type added ( "
	                    + inInducedMutationData.getOtherType() + " ) and is awaiting your approval.";
	            String inFrom = theBundle.getString(Constants.EmailMessage.FROM);
	            // theBundle.getString(Constants.EmailMessage.SENDER);
	
	            //Send the email
	            try {
	            	log.trace("Sending Notification eMail - new InducedMutation Agent added");

                    // gather message keys and variable values to build the e-mail content with
                    String[] messageKeys = {Constants.Admin.INDUCED_MUTATION_AGENT_ADDED};
                    TreeMap values = new TreeMap();
                    values.put(Constants.Admin.INDUCED_MUTATION_AGENT_NAME, inInducedMutationData.getName());
                    values.put(Constants.Admin.INDUCED_MUTATION_AGENT_TYPE,inInducedMutationData.getOtherType());

                    // Send the email
	                MailUtil.sendMail(inRecipients, inSubject, inMessage, inFrom, messageKeys, values);
	                log.trace("Notification eMail sent");
	            } catch (Exception e) {
	            	log.trace("Caught exception " + e);
	                //System.out.println("Caught exception" + e);
	                e.printStackTrace();
	            }
	
	            //2. Set flag, this Strain will need to be approved before  being added the list
	            inEnvironFactor.setTypeUnctrlVocab( inInducedMutationData.getOtherType() );
	        }
	    }
        
        //CAS Number
        inEnvironFactor.setCasNumber( inInducedMutationData.getCASNumber() );
                
        //Name of Inducing Agent
        inEnvironFactor.setName( inInducedMutationData.getName() );
                
        //inEnvironFactor.setNameUnctrlVocab( );
        if ( inInducedMutation.getEnvironmentalFactorCollection().size() < 1 )            
        	inInducedMutation.addEnvironmentalFactor( inEnvironFactor );

        // GeneID        
        inInducedMutation.setGeneId( inInducedMutationData.getGeneId() );
                        
        //Description
        inInducedMutation.setDescription( inInducedMutationData.getDescription() );
        
        //Check for exisiting GeneticAlteration
        GeneticAlteration inGeneticAlteration = null;
        System.out.println( "\tGeneticAlteration=" + inInducedMutation.getGeneticAlterationCollection().size() );        
        if ( inInducedMutation.getGeneticAlterationCollection().size() > 0 )
        	inGeneticAlteration = (GeneticAlteration) inInducedMutation.getGeneticAlterationCollection().get(0);
        else
        	inGeneticAlteration = new GeneticAlteration();
        
        //Observaton               
        //if ( inInducedMutationData.getObservation() != null )
        inGeneticAlteration.setObservation( inInducedMutationData.getObservation() );
        
        //Method of Observation
        //if ( inInducedMutationData.getMethodOfObservation() != null )
        inGeneticAlteration.setMethodOfObservation( inInducedMutationData.getMethodOfObservation() );
        
        System.out.println( "inGeneticAlteration method=" + inGeneticAlteration.getMethodOfObservation() + " obj=" + inGeneticAlteration.getObservation() );
        
        //Only save if saving a new GeneticAlteration
        if ( inInducedMutation.getGeneticAlterationCollection().size() == 0 )
        	inInducedMutation.addGeneticAlteration( inGeneticAlteration );
       
        //MGI Number                
        //Check for exisiting MutationIdentifier
        MutationIdentifier inMutationIdentifier = null;
        if ( inInducedMutation.getMutationIdentifier() != null )
        	inMutationIdentifier = inInducedMutation.getMutationIdentifier();
        else
        	inMutationIdentifier = new MutationIdentifier();
        
        String strNumberMGI = inInducedMutationData.getNumberMGI().trim();
        Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
		Matcher m = p.matcher( strNumberMGI );				
		if (m.matches() && strNumberMGI != null && ! strNumberMGI.equals("") ) {
			 inMutationIdentifier.setNumberMGI( Long.valueOf( strNumberMGI ) );
			 inInducedMutation.setMutationIdentifier( inMutationIdentifier );
		}
				
		log.trace("Exiting populateInducedMutation");
    }    
}
