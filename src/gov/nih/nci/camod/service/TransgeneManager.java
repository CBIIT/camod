/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Transgene;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TransgeneManager {
	public List getTransgenes();
	public Transgene getTransgene(String id);
    public void saveTransgene(Transgene transgene);
    public void removeTransgene(String id);
}
