package com.crsms.util;

/**
 * 
 * @author Valerii Motresku
 *
 */

public class StringUtil {

	static final String DOTS = "...";
    static final String TRAILING_SPACES_REGEX = "\\s{2,}";
    static final String SPACE = "\u0020";
	
	/**
	 * Below is a method to trim long strings.
	 * The "lastWord" boolean as you put it, if set to true will preserve the last word.
	 * @param lastWord - true will preserve the last word
	 * @return trimmed String
	 */
	public static String trimString(String string, int length, boolean lastWord) {
	    if (string == null || string.trim().isEmpty()) {
	        return string;
	    }
	    StringBuffer sb = new StringBuffer(string);
	    // - dotsCount because adds 3 DOTS at the end.
	    int actualRequriedLength = length - DOTS.length();
	    // Returned string length has to be length including the DOTS.
	    if (sb.length() <= actualRequriedLength) {
	    	return string;
	    }
        if (!lastWord) {
            return sb.insert(actualRequriedLength, DOTS).substring(0, actualRequriedLength + DOTS.length());
        }
        int endIndex = sb.indexOf(" ", actualRequriedLength);
        // if the last word it the last in the whole incoming string than returns incoming string
        if (endIndex == -1) {
        	return string;
        }
        return sb.insert(endIndex, DOTS).substring(0, endIndex + DOTS.length());
	}
	
	public static boolean isEmptyOrNull(String str) {
		return (str == null || str.isEmpty()) ? true : false; 
	}
	
	public static String trimTrailingSpaces(String str) {
		return str.trim().replaceAll(TRAILING_SPACES_REGEX, SPACE);
	}
	
}
