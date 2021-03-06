package by.a1.andrikevich.runner;

import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTPFile;

import by.a1.andrikevich.checker.Alarm;
import by.a1.andrikevich.checker.FileCheckerCondition;
import by.a1.andrikevich.checker.FolderCheckerCondition;
import by.a1.andrikevich.ftp.FtpFileComparator;

public class VolteFldrCheckebaleRunner implements Runnable {
	
	private FTPFile[] files;
	private String folderName;
	
	Logger logger = Logger.getLogger(VolteFldrCheckebaleRunner.class.getSimpleName());

	public VolteFldrCheckebaleRunner(FTPFile[] files, String folderName) {
		this.files = files;
		this.folderName = folderName;
	}

	@Override
	public void run() {
		
				int index = FolderCheckerCondition.isFolderHasVoLteOrVoicePrgnFile(files);
				if(index >= 0) {
					FTPFile volteFile = files[index]; 
					if(FileCheckerCondition.isVolteFileCorrect(volteFile)) {
						Arrays.sort(files,new FtpFileComparator());
						if(FileCheckerCondition.isFileUpToDate(files[0])) {
							logger.info("Everything is good now with folder: <<< " + folderName + " >>>");
						}else {
							new Alarm("There was no NEW log-files on FTP folder: " + folderName).doAlarm();
						}	
						
					}else {
						new Alarm("The volte or voicePRGN log files is too small!  " + folderName + "---" + volteFile.getName()).doAlarm();
					}	
					
					
					
					
				} else {
					new Alarm("There is now VOLTE log-file in a monitored folder!  " + folderName).doAlarm();
				}
		}	
	

}
