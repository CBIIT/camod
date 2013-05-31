/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.util.PopulatePubMedUtil;
import gov.nih.nci.camod.webapp.form.PublicationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class PubMedPopulateAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        System.out.println("<PubMedPopulateAction> Entering...");
        
        PublicationForm pubForm = (PublicationForm) form;
        String pmid = pubForm.getPmid(); 
        
        System.out.println( "pmid=" + pmid );
        
        // Validate the input
        Pattern p = Pattern.compile("[0-9]{" + pmid.length() + "}");
        Matcher m = p.matcher(pmid);

        if (m.matches() && pmid != null && !pmid.equals("")) {
            Publication pub = new Publication();
            PopulatePubMedUtil.populatePumMedRecord(Long.valueOf(pmid.trim()), pub);

            pubForm.setJournal(pub.getJournal());
            pubForm.setYear("" + pub.getYear());
            pubForm.setVolume(pub.getVolume());
            pubForm.setTitle(pub.getTitle());
            pubForm.setStartPage("" + pub.getStartPage());
            pubForm.setEndPage("" + pub.getEndPage());

        } else {

            System.out.println("<PubMedPopulateAction> Not a valid number!");

            pubForm.setJournal("");
            pubForm.setYear("");
            pubForm.setVolume("");
            pubForm.setTitle("");
            pubForm.setStartPage("");
            pubForm.setEndPage("");

            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("publication.incorrect.pmid"));
            saveErrors(request, msg);
        }

        try {
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PUBDROP, "");
        } catch (Exception e) {
            log.error("Unable to get dropdown for " + Constants.Dropdowns.PUBDROP);

            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, theMsg);
        }
        request.getSession().setAttribute(Constants.FORMDATA, pubForm);

        // Forward control to the specified success URI
        return mapping.findForward("submitPublications");
    }
}
