package by.a1.andrikevich.util.copier;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class CopierRunnable implements Runnable {

	private String folderSourse;
	private String folderDest;
	private int countOfFilesInFldr;

	public CopierRunnable(String folderSourse, String folderDest) {
		this.folderSourse = folderSourse;
		this.folderDest = folderDest;
	}

	
	
	public String getFolderSourse() {
		return folderSourse;
	}



	public String getFolderDest() {
		return folderDest;
	}



	public int getCountOfFilesInFldr() {
		return countOfFilesInFldr;
	}



	public void setCountOfFilesInFldr(int countOfFilesInFldr) {
		this.countOfFilesInFldr = countOfFilesInFldr;
	}



	@Override
	public void run() {

		FtpUtil ftpUtil = new FtpUtil();
		FTPClient ftpClient = new FTPClient();
		dlDirAndFilesFromFTPtoLocalDisk(ftpClient,ftpUtil.getFtpIp(),ftpUtil.getFtpUser(),
				ftpUtil.getFtpPassword(),folderSourse,folderDest);
		
		ResultOfCopingSingleton.getInstance().putToMap(folderSourse, countOfFilesInFldr);

	}

	public void dlDirAndFilesFromFTPtoLocalDisk(FTPClient ftpClient, String host, String login, String pass,
			String sourseFolderInFtp, String destFolder) {
		try {
			ftpClient.disconnect();
			ftpClient.setAutodetectUTF8(true);
			ftpClient.connect(host);
			ftpClient.login(login, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			File destinationFld = new File(destFolder); // Define the folder for saving files in local drive
			FTPFile[] ftpFilesInitial = ftpClient.listFiles(sourseFolderInFtp); // initial list of files in folder in
																				// FTP
			if (ftpFilesInitial.length != 0 && ftpFilesInitial != null) { // if folder is not empty and exists

						// add to Folder Map <String, Integer> statistics of numbers of files
				this.setCountOfFilesInFldr(ftpFilesInitial.length);

				for (int i = 0; i < ftpFilesInitial.length; i++) {
					String fileName = new String(ftpFilesInitial[i].getName());
					if (ftpFilesInitial[i].isDirectory()) { // if element in FTP is a folder, will check: is Folder is
															// present in local disk, if not it will create it.
						if (Files.exists(Paths.get(destFolder + "\\" + fileName))) { // If there is not such folder in
																						// local drive it will be
																						// created for downloading there
							dlDirAndFilesFromFTPtoLocalDisk(ftpClient, host, login, pass,
									sourseFolderInFtp + "/" + fileName, destFolder + "\\" + fileName);
							ftpClient.removeDirectory(sourseFolderInFtp + "/" + fileName);

						} else if (!Files.exists(Paths.get(destFolder + "\\" + fileName))) { // if such directory there
																								// is in local drive
							Files.createDirectory(Paths.get(destFolder + "\\" + fileName));
							dlDirAndFilesFromFTPtoLocalDisk(ftpClient, host, login, pass,
									sourseFolderInFtp + "/" + fileName, destFolder + "\\" + fileName);

							ftpClient.removeDirectory(sourseFolderInFtp + "/" + fileName);
						}
					} else if (ftpFilesInitial[i].isFile()) { // if element in FTP is a file
						OutputStream outputStream1 = new BufferedOutputStream(
								new FileOutputStream(destinationFld + "\\" + fileName));
						boolean success = ftpClient.retrieveFile(sourseFolderInFtp + "/" + fileName, outputStream1);
						boolean deleted = ftpClient.deleteFile(sourseFolderInFtp + "/" + fileName);
						outputStream1.flush();
						outputStream1.close();
					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
