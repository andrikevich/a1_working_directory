package by.a1.andrikevich.util;


import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import by.a1.andrikevich.entity.SimCard;

public class JspPageUtilities {
	
	// encode String from ISO_8859_1 ==> to UTF8
	public static String jspPageTextEncoder (String msg) {
		byte[]  bytes = msg.getBytes(StandardCharsets.ISO_8859_1);
		String result = new String (bytes,StandardCharsets.UTF_8);
		 
		return result;
	}
	/**
	 For converting SimCard fields from charset ISO-8859-1 to charset UTF8
	 @String  device,  String description1,  String description2,	 String additionalInfo
	 */
	public static SimCard simCardFieldEncoding (SimCard theSimCard) {
		theSimCard.setDevice(jspPageTextEncoder(theSimCard.getDevice()));
		theSimCard.setDescription1(jspPageTextEncoder(theSimCard.getDescription1()));
		theSimCard.setDescription2(jspPageTextEncoder(theSimCard.getDescription2()));
		theSimCard.setAdditionalInfo(jspPageTextEncoder(theSimCard.getAdditionalInfo()));
		return theSimCard;
	}

	
	/** 
	 Check if inputed phone number is correct then 
	 @return  <b>true</b>
	 */
	public static boolean isMsisdnCorrect (String msisdn) {
		if (!msisdn.startsWith("375") || msisdn.isEmpty() || !isNumeric(msisdn) || (msisdn.length() != 12)) {
			return false;
		}
		return true;
	}
	
	public static boolean isIccidCorrect (String iccid) {
		if(iccid.length() < 19 || iccid.isEmpty()) {
			return false;
		}
		return  true;
	}
	
		 
	// for checking is String  is number
		private static boolean isNumeric(String strNum) {
			Pattern pattern = Pattern.compile("\\d+");
		    if (strNum == null) {
		        return false; 
		    }

		    return pattern.matcher(strNum).matches();
		}
	
	
}
