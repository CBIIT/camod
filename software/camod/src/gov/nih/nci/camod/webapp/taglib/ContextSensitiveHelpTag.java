/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: ContextSensitiveHelpTag.java,v 1.12 2008-08-14 17:08:49 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2006/11/09 18:44:36  pandyas
 * Commented out debug code
 *
 * Revision 1.10  2006/10/27 13:04:13  pandyas
 * Added Constants.OnlineHelp.SKIP to remove onclick from ToolTips
 *
 * Revision 1.9  2006/08/13 17:40:51  pandyas
 * Updated online help - redefined camod tag by substituting mapId for topic (ePublisher changes)
 *
 * Revision 1.8  2006/04/28 19:31:20  schroedn
 * Added cvs log to file
 *
 *
 */

package gov.nih.nci.camod.webapp.taglib;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.servlet.AutocompleteServlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Custom tag for context sensitive help.
 */
public class ContextSensitiveHelpTag implements Tag, Serializable {

	private static final long serialVersionUID = 5618297483211863400L;
	
	static private final Log log = LogFactory.getLog(ContextSensitiveHelpTag.class);	

	private PageContext myPageContext = null;

	private Tag myParent = null;

	// Tag attributes
	private String myKey = null;

	private String myImage = null;

	private String myLabelName = null;

	private String myHref = null;

	private String myTopic = null;

	private String myBundle = "ContextSensitiveHelp";

	private String myStyleClass = "style_0";

	private String myJavascriptKey = "help_javascript";
	
	private String wikiSiteBeginKey = "wiki_help_main";

	public void setPageContext(PageContext inPageContext) {
		myPageContext = inPageContext;
	}

	public void setParent(Tag inParent) {
		myParent = inParent;
	}

	public Tag getParent() {
		return myParent;
	}

	public void setTopic(String inTopic) {
		myTopic = inTopic;
	}

	public String getTopic() {
		return myTopic;
	}

	/**
	 * Sets the key attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="The key attribute used to look up the value
	 *                in the properties file"
	 * 
	 * required="true"
	 * 
	 * rtexprvalue="false"
	 */
	public void setKey(String inKey) {
		myKey = inKey;
	}

	public String getKey() {
		return myKey;
	}

	/**
	 * Sets the Image attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="The key attribute used to look up the value
	 *                in the properties file"
	 * 
	 * required="false"
	 * 
	 * rtexprvalue="false"
	 */
	public void setImage(String inKey) {
		myImage = inKey;
	}

	public String getImage() {
		return myImage;
	}

	/**
	 * Sets the text attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="The text the CS help will be for"
	 * 
	 * required="true"
	 * 
	 * rtexprvalue="false"
	 */
	public void setText(String inLabelName) {
		myLabelName = inLabelName;
	}

	public String getText() {
		return myLabelName;
	}

	public String getHref() {
		return myHref;
	}

	/**
	 * Sets the href attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="Where to go when the text is clicked.
	 *                Currently not implemented"
	 * 
	 * required="false"
	 * 
	 * rtexprvalue="false"
	 */
	public void setHref(String inHref) {
		myHref = inHref;
	}

	public String getBundle() {
		return myBundle;
	}

	/**
	 * Sets the bundle attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="What bundle to use for the key lookup.
	 *                Currently defaults to ContextSensitiveHelp.properties"
	 * 
	 * required="false"
	 * 
	 * rtexprvalue="false"
	 */
	public void setBundle(String inBundle) {
		myBundle = inBundle;
	}

	public String getStyleClass() {
		return myStyleClass;
	}

	/**
	 * Sets the styleClass. This is included in the tld file.
	 * 
	 * @jsp.attribute description="What style to use for the popup. Currently
	 *                defaults to style_0"
	 * 
	 * required="false"
	 * 
	 * rtexprvalue="false"
	 */
	public void setStyleClass(String inStyleClass) {
		myStyleClass = inStyleClass;
	}

	public int doStartTag() throws JspException {

		try {
			String theHref = "";
			String wikiSiteBegin = "";
			try {
				// Get the text
				ResourceBundle theBundle = ResourceBundle.getBundle(myBundle);

				// Process optional attributes
				if (myTopic != null) {
					// if topic=tooltip makes this not an onclick - used for tool tips
					if (!myTopic.equals(Constants.OnlineHelp.SKIP)) {
						String theTopic = "";
//						try {
//							theTopic = theBundle.getString(myTopic);
//						} catch (Exception e) {
//							theTopic = myTopic;
//						}
						// swap theMapId (RoboHelp) for the topic (ePublisher)
						// here
						String theJavascript = theBundle.getString(myJavascriptKey);
						
						Properties wikihelpProperties = new Properties();
						try {

							String wikihelpPropertiesFileName = null;

							wikihelpPropertiesFileName = System.getProperty("gov.nih.nci.camod.wikihelpProperties");
							
							try {
							
							FileInputStream in = new FileInputStream(wikihelpPropertiesFileName);
							wikihelpProperties.load(in);
					
							} 
							catch (FileNotFoundException e) {
								log.error("Caught exception finding file for properties: ", e);
								e.printStackTrace();			
							} catch (IOException e) {
								log.error("Caught exception finding file for properties: ", e);
								e.printStackTrace();			
							}
							wikiSiteBegin =  wikihelpProperties.getProperty(wikiSiteBeginKey);
							theTopic = wikihelpProperties.getProperty(myTopic);
						}

						// Default to 100 on an exception
						catch (Exception e) {
							System.err.println("Error loading system.properties file");
							e.printStackTrace();
						}
						theHref = "href=\"" + theJavascript + wikiSiteBegin + theTopic + "')\"";
					}
				} else if (myHref != null) {
					 theHref = "href=\"" + myHref + "\"";
				}

				String theText = theBundle.getString(myKey);
				String theStyleClass = theBundle.getString(myStyleClass);

				if (myImage != null) {
					myPageContext.getOut().write(
							"<a " + theHref + " onMouseOver=\"stm(" + theText
									+ "," + theStyleClass
									+ ")\" onMouseOut=\"htm();\"><img alt=\"Help\" src=\""
									+ myImage + "\" border=\"0\"/>" + "</a>");

				} else {
					myPageContext.getOut().write(
							"<a " + theHref + " onMouseOver=\"stm(" + theText
									+ "," + theStyleClass
									+ ")\" onMouseOut=\"htm();\">"
									+ myLabelName + "</a>");
				}

			} catch (Exception e) {
				e.printStackTrace();

				System.out.println("Can't get bundle. Ignore tooltip");
				// Can't get bundle. Ignore tooltip
				myPageContext.getOut().write(
						"<a " + theHref + " \">" + myLabelName + "</a>");
			}

		} catch (IOException e) {
			throw new JspTagException("An IOException occurred.");
		} catch (Exception e) {
			throw new JspTagException("An unknown exception occurred.");
		}

		return SKIP_BODY;

	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void release() {
		myPageContext = null;
		myParent = null;
		myKey = null;
	}
}
