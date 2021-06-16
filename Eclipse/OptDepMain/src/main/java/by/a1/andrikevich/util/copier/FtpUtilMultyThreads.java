package by.a1.andrikevich.util.copier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

import by.a1.andrikevich.entity.Folder;

public class FtpUtilMultyThreads extends FtpUtil {
	
	
	public List<Thread> lstThread = new ArrayList<Thread>();

	@Override
	public void doMovingFilesFromFtpToLocalDisk(Folder folder) throws IOException {

		FTPClient ftpClient = new FTPClient();

		// create folder for saving files in local drive if it is not exist
		if (Files.notExists(Paths.get(folder.getFolderNameForSaving()))) {
			Files.createDirectories(Paths.get(folder.getFolderNameForSaving()));
		}

		// Iterate through list of checked by user folders (check boxes)
		//Create for every folder it's own Thread
		for (String ftpFolderName : folder.getCheckedFolders()) {
			String sourseFolderInFtp = super.getFtpUrl()+ ftpFolderName + "/";
			String folderDest = folder.getFolderNameForSaving();
			CopierRunnable copierRunnable = new CopierRunnable(sourseFolderInFtp, folderDest);
			Thread thread = new Thread (copierRunnable);
			lstThread.add(thread);


		}
		
		for(Thread thrd : lstThread) {
			thrd.start();
			
		}
		for(Thread thrd : lstThread) {
			try {
				thrd.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		// for statistics of number of logfiles in folder
		folder.setQuatityOfFiles(ResultOfCopingSingleton.getInstance().getMapStatOfFolderFiles());

	}
	


	  
	  

}
