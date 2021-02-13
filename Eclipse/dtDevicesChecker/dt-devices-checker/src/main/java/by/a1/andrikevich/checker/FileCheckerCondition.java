package by.a1.andrikevich.checker;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.net.ftp.FTPFile;

public class FileCheckerCondition {

	public static boolean isScannerFileCorrect(FTPFile file) {
		if (file.getSize() > 4_500_000) {
			return true;
		} else {
			return false;
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

		if (file.getSize() > 1_500_000) {
			return true;
		} else {
			if (file.getName().endsWith("trp")) {
				return false;
			} else
				return true;
		}

	}

}
