/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.util;

import gov.nih.nci.camod.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * This class contains a set of utilities for converting Strings to other
 * formats or converting other formats to String.
 *
 * @author pansu
 *
 */

public class StringUtils {
	public static String join(String[] stringArray, String delimiter) {
		String joinedStr = "";
		if (stringArray == null || stringArray.length == 0) {
			return joinedStr;
		}
		List<String> stringList = Arrays.asList(stringArray);
		return join(stringList, delimiter);
	}

	public static String join(Collection<String> stringList, String delimiter) {
		String joinedStr = "";
		if (stringList == null || stringList.isEmpty()) {
			return joinedStr;
		}
		// remove empty items
		Collection<String> modList = new ArrayList<String>(stringList);
		for (String str : modList) {
			if (isEmpty(str)) {
				stringList.remove(str);
			}
		}

		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (String str : stringList) {
			if (i < stringList.size() - 1) {
				sb.append(str);
				sb.append(delimiter);
			} else {
				sb.append(str);
			}
			i++;
		}
		joinedStr = sb.toString();
		return joinedStr;
	}

	/*
	 * empty string of the collection will be included in the joined string and
	 * null item in the collection will be converted to an empty string
	 */
	public static String joinEmptyItemIncluded(
			Collection<String> stringCollection, String delimiter) {
		StringBuffer buffer = new StringBuffer();
		if (stringCollection == null || stringCollection.isEmpty()) {
			return "";
		}

		Iterator iter = stringCollection.iterator();
		while (iter.hasNext()) {
			String item = (String) iter.next();
			if (item == null)
				item = "";
			buffer.append(item);
			if (iter.hasNext()) {
				buffer.append(delimiter);
			}
		}
		return buffer.toString();
	}

	/**
	 * Escape HTML but keep line breaks, useful in preserving line breaks in
	 * descriptions
	 *
	 * @param text
	 * @return
	 */
	public static String escapeXmlButPreserveLineBreaks(String text) {
		if (isEmpty(text)) {
			return "";
		}

		String[] words = text.trim().split("\r\n");
		List<String> lines = new ArrayList<String>();
		for (String word : words) {
			lines.add(word.trim());
		}

		StringBuffer newText = new StringBuffer();
		int i = 0;
		if (lines != null) {
			for (String line : lines) {
				String escapedLine = StringEscapeUtils.escapeXml(line);
				newText.append(escapedLine);
				if (i < lines.size() - 1) {
					newText.append("<br>");
				}
				i++;
			}
			return newText.toString();
		} else {
			return "";
		}
	}

	public static Float convertToFloat(String floatStr) {
		if (isEmpty(floatStr)) {
			return null;
		}
		try {
			Float floatNum = Float.parseFloat(floatStr);
			return floatNum;
		} catch (NumberFormatException e) {
			throw new RuntimeException(
					"Can't convert the given string to a float number: "
							+ floatStr);
		}
	}

	public static Long convertToLong(String longStr) {
		if (isEmpty(longStr)) {
			return null;
		}
		try {
			Long longNum = Long.parseLong(longStr);
			return longNum;
		} catch (NumberFormatException e) {
			throw new RuntimeException(
					"Can't convert the given string to a long number: "
							+ longStr);
		}
	}

	public static String convertToString(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	public static boolean isInteger(String theStr) {
		if (isEmpty(theStr)) {
			return false;
		} else {
			for (int i = 0; i < theStr.length(); i++) {
				if (!Character.isDigit(theStr.charAt(i))) {
					return false;
				}
			}
			return true;
		}
	}

	public static boolean isDouble(String theStr) {
		int decimalCount = 0;

		if (isEmpty(theStr)) {
			return false;
		} else {
			for (int i = 0; i < theStr.length(); i++) {
				if (!Character.isDigit(theStr.charAt(i))) {
					if (theStr.charAt(i) == ('.')) {
						decimalCount++;
						continue;
					} else {
						return false;
					}
				}
			}

			if (decimalCount == 1)
				return true;
			else
				return false;
		}
	}

	public static boolean contains(String[] array, String aString,
			boolean ignoreCase) {
		boolean containsString = false;

		for (int i = 0; i < array.length; i++) {
			if (ignoreCase) {
				if (array[i].equalsIgnoreCase(aString))
					containsString = true;
			} else {
				if (array[i].equals(aString))
					containsString = true;
			}
		}

		return containsString;
	}

	public static String[] add(String[] x, String aString) {
		String[] result = new String[x.length + 1];
		for (int i = 0; i < x.length; i++) {
			result[i] = x[i];
		}
		result[x.length] = aString;

		return result;
	}

	/**
	 * Convert a string with multiple words separated by space to one word, with
	 * first letter as lower case.
	 *
	 * @param words
	 * @return
	 */
	public static String getOneWordLowerCaseFirstLetter(String words) {
		// remove space in words and make the first letter lower case.
		String oneWord = words;
		if (!isEmpty(words)) {
			String firstLetter = words.substring(0, 1);
			oneWord = words
					.replaceFirst(firstLetter, firstLetter.toLowerCase())
					.replace(" ", "");
		}
		return oneWord;
	}

	/**
	 * Convert a string with multiple words separated by space to one word, with
	 * first letter as upper case.
	 *
	 * @param words
	 * @return
	 */
	public static String getOneWordUpperCaseFirstLetter(String words) {
		// remove space in words and make the first letter lower case.
		String firstLetter = words.substring(0, 1);
		String oneWord = words.replaceFirst(firstLetter,
				firstLetter.toUpperCase()).replace(" ", "");
		return oneWord;
	}

	/**
	 * Parse the text into an array of words using white space as delimiter.
	 * Keeping words in quotes together.
	 *
	 * @param texts
	 * @return
	 */
	public static List<String> parseToWords(String text) {
		if (isEmpty(text)) {
			return null;
		}
		SortedSet<String> wordList = new TreeSet<String>();

		// extract words in quotes first
		String patternStr = "\\B[\"']([^\"']*)[\"']\\B";
		String[] nonQuotedTexts = text.split(patternStr);
		for (String txt : nonQuotedTexts) {
			String[] nonQuotedWords = txt.split("\\s");
			wordList.addAll(Arrays.asList(nonQuotedWords));
		}
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(text);

		List<String> quotedWords = new ArrayList<String>();
		int start = 0;
		while (matcher.find(start)) {
			String quotedWord = matcher.group(1).trim();
			quotedWords.add(quotedWord);
			start = matcher.end(1);
		}
		wordList.addAll(quotedWords);

		return new ArrayList<String>(wordList);
	}

	public static List<String> parseToWords(String text, String delimiter) {
		if (isEmpty(text)) {
			return null;
		}
		String[] words = text.trim().split(delimiter);
		List<String> wordList = new ArrayList<String>();
		for (String word : words) {
			if (!isEmpty(word)) {
				wordList.add(word.trim());
			}
		}
		return wordList;
	}

	public static Boolean containsIgnoreCase(Collection<String> collection,
			String match) {
		for (String str : collection) {
			if (str.trim().equalsIgnoreCase(match.trim())) {
				return true;
			}
		}
		return false;
	}

	public static String stripWildcards(String searchString) {
		// strip start from either ends of the search string
		String newString = searchString.trim();
		newString = newString.replaceAll("^(\\*)(.*)", "$2");
		newString = newString.replaceAll("(.*)(\\*)$", "$1");
		return newString;
	}

	public static Boolean xssValidate(String inputString) {
		// the input string can't contain patterns like <script>, javascript:
		// and their HEX representation and unicode version, HTML entity
		// encoded version, UTF-7 encoded version
		String[] scriptPatterns = new String[] {
				"\\<script",
				"\\<\\%00script",
				"\\%3C\\%73\\%63\\%72\\%69\\%70\\%74", // hex encoded for
				// URL for <script
				"&#x3C;&#x73;&#x63;&#x72;&#x69;&#x70;&#x74;", // hex
				// encoded
				// version
				// of
				// <script
				"&#60;&#115;&#99;&#114;&#105;&#112;&#116;", // unicode
				// version
				// of
				// <script
				"&#60;&#37;&#48;&#48;&#115;&#99;&#114;&#105;&#112;&#116;", // html
				// entity
				// encoded
				// version
				// of
				// <%00script
				"\\%uff1cscript\\%uff1e",
				"\\%BC\\%F3\\%E3\\%F2\\%E9\\%F0\\%F4", // hex version of
				// <script
				"\\+ADw\\-SCRIPT\\+AD4", "\\\\u003Cscript" }; // <script

		String[] javascriptPatterns = new String[] { "javascript\\:",
				"\\%6A\\%61\\%76\\%61\\%73\\%63%\\%72\\%69\\%70\\%74\\%3A", // hex
				// version
				// of
				// javascript:
				"&#x6a;&#x61;&#x76;&#x61;&#x73;&#x63;&#x72;&#x69;&#x70;&#x74;&#x3a;", // hex
				// encoded
				// version
				// of
				// javascript:
				"&#106;&#97;&#118;&#97;&#115;&#99;&#114;&#105;&#112;&#116;&#58;" }; // HTML
		// entity
		// encoded
		// version
		// of
		// javascript:

		String[] otherPatterns = new String[] { "etc/passwd", "/bin/id",
				"\\.ini", ";vol\\|", "id\\|",
				"AVAK\\$\\(RETURN_CODE\\)OS", "sys\\.dba_user", "\\+select\\+",
				"\\+and\\+", "WFXSSProbe", "TEXT/VBSCRIPT", "=\"", "\\.\\./",
				"\\.\\.\\\\", "\\\\\'", "\\\\\\\"", "background\\:", "\\'\\+",
				"\\\"\\+", "%\\d+"};

		String patternStr = StringUtils.join(scriptPatterns, "|") + "|"
				+ StringUtils.join(javascriptPatterns, "|") + "|"
				+ StringUtils.join(otherPatterns, "|");

		String regex = "^(?!.*(" + patternStr + ")).*$";
		// String regex =
		// "^(?!.*(TEXT\\/VBSCRIPT|%uff1cscript%uff1e|WFXSSProbe|\\=\"|\\+and\\+|\\+select\\+|sys\\.dba\\_user|AVAK\\$\\(RETURN\\_CODE\\)OS|id\\||;vol\\||&#|%\\d+|\\>|\\<|\\.\\.\\\\|\\.\\.\\/|\\.ini|javascript\\:|\\/etc\\/passwd|\\/bin\\/id|\\\'|\\\"|background\\:expression)).*$";

//		System.out.println(regex);
		if (inputString.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Return true for Null or empty string, false otherwise.
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	public static void main(String[] args) {
		try {
			String text = "this is 'a test' of \"parsing words\"";
			System.out.println(text);
			List<String> words = StringUtils.parseToWords(text);
			for (String word : words) {
				System.out.println(word);
			}

			String text2 = "thomas\r\nshukla";
			System.out.println(text2);
			List<String> words2 = StringUtils.parseToWords(text2, "\r\n");
			for (String word : words2) {
				System.out.println(word);
			}

			String text3 = "*NCL-100**";
			System.out.println(stripWildcards(text3));

			String text4 = "this is a test \\u003Cscript\\u003Ealert\\u0028613067\\u0029\u003C/script\u003E in a sentence";

			if (StringUtils.xssValidate(text4)) {
				System.out.println("pass xss validation");
			} else {
				System.out.println("didn't pass xss validation");
			}
		} catch (Exception e) {
			
		}
	}
	
	public static boolean isImgFileExt(String fileName) {
		if (isEmpty(fileName)) {
			return false;
		}

		boolean isImgFileExt = false;
		for (int i = 0; i < Constants.IMAGE_FILE_EXTENSIONS.length; i++) {
			if (fileName.toUpperCase().endsWith(
					"." + Constants.IMAGE_FILE_EXTENSIONS[i])) {
				isImgFileExt = true;
				break;
			}
		}

		return isImgFileExt;
	}


	public static String[] removeFromArray(String[] oldArray,
			String stringToRemove) {
		List<String> list = new ArrayList<String>(Arrays.asList(oldArray));
		list.remove(stringToRemove);
		return list.toArray(new String[0]);
	}
}

