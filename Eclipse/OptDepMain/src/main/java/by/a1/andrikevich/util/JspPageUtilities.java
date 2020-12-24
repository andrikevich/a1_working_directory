package by.a1.andrikevich.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

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

}
