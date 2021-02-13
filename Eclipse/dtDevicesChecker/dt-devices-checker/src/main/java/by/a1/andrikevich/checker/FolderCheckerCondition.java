package by.a1.andrikevich.checker;


import org.apache.commons.net.ftp.FTPFile;

public class FolderCheckerCondition {
	
	
	// if there is a voLTE file in a folder, if true return index of this file in array
	public static int isFolderHasVoLteFile (FTPFile[] files) {
		for(int i = 0; i < files.length; i++) {
			if (files[i].getName().toLowerCase().contains("volte")) {
				return i;
			}
		}
		return -1;
	}
	
	// if there is a scan file in a folder, if true return index of this file in array
	public static int isFolderHasScanFile (FTPFile[] files) {
		for(int i = 0; i < files.length; i++) {
			if (files[i].getName().toLowerCase().contains("scan")) {
				return i;
			}
		}
		return -1;
	}
	
	
	

}
