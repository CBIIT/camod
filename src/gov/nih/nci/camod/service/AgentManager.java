/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Agent;
import java.util.Collection;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface AgentManager {
	
	public Agent get(String id);
    public void save(Agent agent);
    public void remove(String id);
	public Collection getClinicalProtocols(Agent a);
	public List getYeastResults(Agent a);
}
