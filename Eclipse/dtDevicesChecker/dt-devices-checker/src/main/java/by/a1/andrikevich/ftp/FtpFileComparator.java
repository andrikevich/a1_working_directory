package by.a1.andrikevich.ftp;

import java.util.Comparator;

import org.apache.commons.net.ftp.FTPFile;

public class FtpFileComparator implements Comparator<FTPFile> {

	@Override
	public int compare(FTPFile fileOne, FTPFile fileTwo) {
		
		Long timeCreationFileTwo = fileTwo.getTimestamp().getTimeInMillis();
		Long timeCreationFileOne = fileOne.getTimestamp().getTimeInMillis();
		
		return   timeCreationFileTwo.compareTo(timeCreationFileOne);
	}

}
