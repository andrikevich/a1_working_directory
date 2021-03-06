package by.a1.andrikevich.runner;

import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTPFile;

import by.a1.andrikevich.checker.Alarm;
import by.a1.andrikevich.checker.FileCheckerCondition;
import by.a1.andrikevich.checker.FolderCheckerCondition;
import by.a1.andrikevich.ftp.FtpFileComparator;

public class ScanFldrCheckebaleRunner implements Runnable {
	
	private FTPFile[] files;
	private String folderName;
	Logger logger = Logger.getLogger(ScanFldrCheckebaleRunner.class.getSimpleName());
	

	public ScanFldrCheckebaleRunner(FTPFile[] files, String folderName) {
		this.folderName = folderName;
		this.files = files;
	}

	@Override
	public void run() {
		
				int index = FolderCheckerCondition.isFolderHasScanFile(files);
				if(index >= 0) {
					FTPFile scanFile = files[index]; 
					if(FileCheckerCondition.isScannerFileCorrect(scanFile)) {
						Arrays.sort(files,new FtpFileComparator());
						if(FileCheckerCondition.isFileUpToDate(files[0])) {
								logger.info("Everything is good now with folder: <<< " + folderName + " >>>");
								
								
						}else {
							new Alarm("There was no NEW log-files on FTP folder: " + folderName).doAlarm();
						}	
						
					}else {
						new Alarm("The scanner log files is too small! " + folderName + "---" + scanFile.getName()).doAlarm();
					}	
					
					
					
					
				} else {
					new Alarm("There is **** NO SCAN **** log-file in a monitored folder! " + folderName).doAlarm();
				}
			
	}

}
