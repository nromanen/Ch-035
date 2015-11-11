package com.crsms.util;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Component("stringUtil")
public class StringUtil {

	/**
	 * Below is a method to trim long strings in webapp. 
	 * The "lastWord" boolean as you put it, if set to true will preserve the last word.
	 * @param lastWord - true will preserve the last word
	 * @return trimmed String
	 */
	public String trimString(String string, int length, boolean lastWord) {
	    if(string == null || string.trim().isEmpty()){
	        return string;
	    }

	    StringBuffer sb = new StringBuffer(string);
	    int actualLength = length - 3;
	    if(sb.length() > actualLength){
	        // -3 because we add 3 dots at the end. Returned string length has to be length including the dots.
	        if(!lastWord)
	            return sb.insert(actualLength, "...").substring(0, actualLength+3);
	        else {
	            int endIndex = sb.indexOf(" ",actualLength);
	            if (endIndex == -1) {
	            	return string;
	            }
	            return sb.insert(endIndex,"...").substring(0, endIndex+3);
	        }
	    }
	    return string;
	}
}
