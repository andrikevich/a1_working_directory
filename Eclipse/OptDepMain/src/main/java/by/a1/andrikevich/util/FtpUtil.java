package by.a1.andrikevich.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import by.a1.andrikevich.entity.Folder;

public class FtpUtil {
	
	public FtpUtil() {}
	
	private Properties prop = PropertiesUtil.getProperty("config.properties");
	private String ftpIp = prop.getProperty("ftp.ip");
	private String ftpUser = prop.getProperty("ftp.user");
	private String ftpPassword = prop.getProperty("ftp.password");
	private String ftpUrl = prop.getProperty("ftp.url");
	
	// map for statistics of files in each folder
	Map <String, Integer> mapStatOfFolderFiles = new HashMap<>();
	
	//retrieve list of folders on FTP 
	 public List<String> retrieveListOfFolderNames( ){
	     List<String> result = new LinkedList<String>();   
		 FTPClient ftpClient = new FTPClient();
	        try {
	            ftpClient.disconnect();
	            ftpClient.setAutodetectUTF8( true );
	            ftpClient.connect(ftpIp);
	            ftpClient.login(ftpUser,ftpPassword);
	            ftpClient.enterLocalPassiveMode();
	            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
	            
	            FTPFile[] ftpFilesInitial = ftpClient.listFiles(ftpUrl); //initial list of files in folder in FTP
	            if(ftpFilesInitial.length !=0 && ftpFilesInitial != null){     //if folder is not empty and exists
	                for(int i = 0; i < ftpFilesInitial.length; i++){
	                    String folderName = new String(ftpFilesInitial[i].getName());
	                    if(ftpFilesInitial[i].isDirectory()){
	                    	result.add(folderName);
	                    	}	
	                   }
	              }  
	        }
	        
	        catch (Exception e) {
				e.printStackTrace();
			}

	        return result;
}
	 
	 

	    public  void doMovingFilesFromFtpToLocalDisk(Folder folder) throws IOException {

	            FTPClient ftpClient = new FTPClient ();
	            
	            //create folder for saving files in local drive if it is not exist
	            if(Files.notExists(Paths.get(folder.getFolderNameForSaving()))) {
	            Files.createDirectories(Paths.get(folder.getFolderNameForSaving()));
	            }
	            
	            //Iterate through list of checked by user folders (check boxes)
	            for(String ftpFolderName : folder.getCheckedFolders()) {
	            		//Creating full path (adding FolderName to main path 
	            		String sourseFolderInFtp = ftpUrl + ftpFolderName + "/";
	            		

	            		
	            		dlDirAndFilesFromFTPtoLocalDisk(ftpClient, ftpIp, ftpUser, ftpPassword, sourseFolderInFtp, folder.getFolderNameForSaving());
	            		
	            		
	                    }
	            folder.setQuatityOfFiles(mapStatOfFolderFiles); //for statistics of number of logfiles in folder
	    }

	    

	       public  void dlDirAndFilesFromFTPtoLocalDisk(FTPClient ftpClient, String host, 
	    		   							String login, String pass, String sourseFolderInFtp, String destFolder){
	       try {
	           ftpClient.disconnect();
	           ftpClient.setAutodetectUTF8( true );
	           ftpClient.connect(host);
	           ftpClient.login(login,pass);
	           ftpClient.enterLocalPassiveMode();
	           ftpClient.setFileType(FTP.BINARY_FILE_TYPE);           	
	           	
	           File destinationFld = new File(destFolder); //Define the folder for saving files in local drive
	           FTPFile[] ftpFilesInitial = ftpClient.listFiles(sourseFolderInFtp); //initial list of files in folder in FTP
	           if(ftpFilesInitial.length !=0 && ftpFilesInitial != null){     //if folder is not empty and exists
	               
	        	// add to Folder Map <String, Integer> statistics of numbers of files
	        	   mapStatOfFolderFiles.put(sourseFolderInFtp, ftpFilesInitial.length);
	        	   
	        	   for(int i = 0; i < ftpFilesInitial.length; i++){
	                   String fileName = new String(ftpFilesInitial[i].getName());
	                   if(ftpFilesInitial[i].isDirectory()){     // if element in FTP is a folder, will check: is Folder is present in local disk, if not it will create it.
	                       if (Files.exists(Paths.get(destFolder + "\\" + fileName ))){     //If there is not  such folder in local drive it will be created for downloading there
	                           dlDirAndFilesFromFTPtoLocalDisk(ftpClient,host, login, pass,sourseFolderInFtp+ "/" + fileName,destFolder+ "\\" + fileName);
	                           ftpClient.removeDirectory (sourseFolderInFtp + "/" + fileName);

	                       }else if(!Files.exists(Paths.get(destFolder + "\\" + fileName ))){       //if such directory there is in local drive
	                           Files.createDirectory(Paths.get(destFolder + "\\" + fileName));
	                           dlDirAndFilesFromFTPtoLocalDisk(ftpClient,host, login, pass,sourseFolderInFtp+ "/" +fileName, destFolder+ "\\" + fileName);

	                           ftpClient.removeDirectory(sourseFolderInFtp + "/" + fileName);
	                       }
	                   }else if (ftpFilesInitial[i].isFile()){   // if element in FTP is a file
	                       OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(destinationFld + "\\" + fileName));
	                       boolean success = ftpClient.retrieveFile(sourseFolderInFtp + "/" + fileName, outputStream1);
	                       boolean deleted = ftpClient.deleteFile (sourseFolderInFtp + "/" + fileName);

	                       outputStream1.close();
	                   }


	               }
	           }

	       } catch (IOException e) {
	           e.printStackTrace();
	       }

	   }

		  public int retrieveNumberOfDownloadedFiles (Map <String, Integer> statMapOfFiles) {
		 		  int countOfFiles = 0;
		 		  for(Integer tmpCount : statMapOfFiles.values()) {
		 			  countOfFiles += tmpCount;
		 		  }
				  return countOfFiles;
			  }

}
