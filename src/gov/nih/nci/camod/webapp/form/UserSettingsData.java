/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.webapp.form;

public interface UserSettingsData {

	/**
	 * @return
	 */
	public String getUsername();

	/**
	 * @param username
	 */
	public void setUsername(String username);

	/**
	 * @return
	 */
	public String getAffiliation();

	/**
	 * @param affiliation
	 */
	public void setAffiliation(String affiliation);

	/**
	 * @return
	 */
	public String getEmail();

	public void setEmail(String emailAddress);

	public String getFirstName();

	public void setFirstName(String firstName);

	public boolean isPrincipalInvestigator();

	public void setPrincipalInvestigator(boolean isPrincipalInvestigator);

	public String getLastName();

	public void setLastName(String lastName);

	public String getPhone();

	public void setPhone(String phoneNumber);

	public String getPiFirstName();

	public void setPiFirstName(String piFirstName);

	public String getPiLastName();

	public void setPiLastName(String piLastName);

	public String getPiUsername();

	public void setPiUsername(String piEmail);
    
    public String getPiEmail();

    public void setPiEmail(String piEmail);
}
