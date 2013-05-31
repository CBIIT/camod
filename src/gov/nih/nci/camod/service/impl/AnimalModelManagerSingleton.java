/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.AnimalModelManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the AnimalModelManager
 */
public class AnimalModelManagerSingleton {

    private static AnimalModelManager ourManager = new AnimalModelManagerImpl();

    /**
     * @return the global instance of the AnimalModelManager
     */
    public static synchronized AnimalModelManager instance() {
        return ourManager;
    }
}

/*
 * $Log: not supported by cvs2svn $
 */
