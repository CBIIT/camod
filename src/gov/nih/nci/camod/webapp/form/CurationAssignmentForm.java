/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * @author dgeorge
 * 
 * $Id: CurationAssignmentForm.java,v 1.2 2005-10-24 13:28:30 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/17 13:23:22  georgeda
 * Initial revision
 *
 * Revision 1.1  2005/09/19 19:54:06  georgeda
 * New model assignment functionality
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form for querying for model assignment
 *
 */
public class CurationAssignmentForm extends ValidatorForm implements Serializable {

    private static final long serialVersionUID = 6227851969634170134L;

    protected String myCurrentState;

    public String getCurrentState() {
        return myCurrentState;
    }

    public void setCurrentState(String inCurrentState) {
        myCurrentState = inCurrentState;
    }
}
