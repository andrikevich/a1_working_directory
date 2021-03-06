package by.a1.andrikevich.runner;

import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTPFile;

import by.a1.andrikevich.checker.Alarm;
import by.a1.andrikevich.checker.FileCheckerCondition;
import by.a1.andrikevich.ftp.FtpFileComparator;

public class UsualFldrCheckebaleRunner implements Runnable {
	Logger logger = Logger.getLogger(UsualFldrCheckebaleRunner.class.getSimpleName());
	
	private FTPFile[] files;
	private String folderName;
	
	

	public UsualFldrCheckebaleRunner(FTPFile[] files, String folderName) {
		this.files = files;
		this.folderName=folderName;
	}
	
	
	@Override
	public void run() {
		
			Arrays.sort(files,new FtpFileComparator());
			if(FileCheckerCondition.isFileUpToDate(files[0])) {
				logger.info("Everything is good now with folder: <<< " + folderName + " >>>");
			}else {
				new Alarm("There was no NEW log-files on FTP folder: " + folderName).doAlarm();
			}
			
		

	}

}
