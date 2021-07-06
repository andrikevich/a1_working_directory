package by.a1.andrikevich.checker;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.net.ftp.FTPFile;

public class FileCheckerCondition {

	public static boolean isScannerFileCorrect(FTPFile file) {
		if (file.getSize() > 4_500_000 ) {
			return true;
		} else if (!isExtOfFileTrp(file)){
			return true;
		} else {
			return  false;
		}
	}

	public static boolean isFileUpToDate(FTPFile file) {

		new GregorianCalendar();
		long now = Calendar.getInstance().getTimeInMillis();
		// time in minutes including different of Server time in 3 hours (-180 sec)
		long timeMinAgoFileModified = ((now - file.getTimestamp().getTimeInMillis()) / 1000 / 60) - 180;
		if (timeMinAgoFileModified < 10) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isVolteFileCorrect(FTPFile file) {

		if (file.getSize() > 1_000_000) {
			return true;
		} else {
			if (isExtOfFileTrp(file)) {
				return false;
			} else
				return true;
		}

	}

	private static boolean isExtOfFileTrp (FTPFile theFile){
		String fileName = theFile.getName();
		String extOfFile = fileName.substring(fileName.length()-3, fileName.length());
		return extOfFile.equals("trp");
	}

}
