/**
 * @pandyas
 * 
 * $Id: ClinicalMarkerManagerImpl.java,v 1.3 2005-11-07 19:15:17 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.ClinicalMarker;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.service.ClinicalMarkerManager;
import gov.nih.nci.camod.webapp.form.ClinicalMarkerData;

/**
 * Manager provides get method
 */
public class ClinicalMarkerManagerImpl extends BaseManager implements ClinicalMarkerManager {

    public ClinicalMarker get(String id) throws Exception {
        log.trace("In ClinicalMarkerManagerImpl.get");
        return (ClinicalMarker) super.get(id, ClinicalMarker.class);
    }
    
    public void save(ClinicalMarker clinicalMarker) throws Exception {
        log.trace("In ClinicalMarkerManagerImpl.save");
        super.save(clinicalMarker);
    }
    

    public void remove(String id) throws Exception {
        log.trace("In ClinicalMarkerManagerImpl.remove");
        super.remove(id, ClinicalMarker.class);
    }
   
    
    public void create(ClinicalMarkerData inClinicalMarkerData, Histopathology inHistopathology) throws Exception {

        log.info("Entering HistopathologyManagerImpl.createClinicalMarker");

        ClinicalMarker theClinicalMarker = new ClinicalMarker();
        populateClinicalMarker(inClinicalMarkerData, theClinicalMarker);

        inHistopathology.addClinicalMarker(theClinicalMarker);        

        log.info("Exiting HistopathologyManagerImpl.createClinicalMarker");
    }
    
    
    public void update(ClinicalMarkerData inClinicalMarkerData, ClinicalMarker inClinicalMarker)
    throws Exception {

    	log.info("Entering ClinicalMarkerManagerImpl.update");
    	log.info("Updating ClinicalMarkerData: " + inClinicalMarker.getId());

    	// Populate w/ the new values and save
    	populateClinicalMarker(inClinicalMarkerData, inClinicalMarker);
    	save(inClinicalMarker);

    	log.info("Exiting ClinicalMarkerManagerImpl.update");
    }
    
    private void populateClinicalMarker(ClinicalMarkerData inClinicalMarkerData, ClinicalMarker inClinicalMarker) {

        log.info("<ClinicalMarkerManagerImpl> Entering populateClinicalMarker");
        
        inClinicalMarker.setName(inClinicalMarkerData.getName());
        
        if (inClinicalMarkerData.getValue() != null && inClinicalMarkerData.getValue().length() > 0) {        
        	inClinicalMarker.setValue(inClinicalMarkerData.getValue());
        }
        log.info("<ClinicalMarkerManagerImpl> Exiting populateClinicalMarker");
    } 
	
}	
