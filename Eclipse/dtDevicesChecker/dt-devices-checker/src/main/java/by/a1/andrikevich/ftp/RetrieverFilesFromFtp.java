package by.a1.andrikevich.ftp;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class RetrieverFilesFromFtp {
	private String ipAdress;
	private String loginName;
	private String loginPwd;
	
	Logger logger = Logger.getLogger(RetrieverFilesFromFtp.class.getName());
	


	public RetrieverFilesFromFtp(String ipAdress, String loginName, String loginPwd) {
		this.ipAdress = ipAdress;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
	}

	public FTPFile[] retrieveFilesArrFromFolder (String folderNameInFtp) {
		FTPFile[] result = null;
		try {
			FTPClient ftpClient = retrieveFtpClient(ipAdress, loginName, loginPwd);
			FTPFile[] ftpFiles = ftpClient.listFiles(folderNameInFtp);

			//remain only 10 files from folder
			if(ftpFiles.length>10) {
				result=	Arrays.stream(ftpFiles)
									.sorted(new FtpFileComparator())
									.limit(10)
									.toArray(FTPFile[]::new);
			} else {
				result=ftpFiles;
			} 
			logger.info("Retrieved from FTP folders number:" + ftpFiles.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	private FTPClient retrieveFtpClient (String ipAdress, String loginName, String loginPwd) {
		FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.disconnect();
            ftpClient.setAutodetectUTF8(true);
            ftpClient.connect(ipAdress);
            ftpClient.login(loginName, loginPwd);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
            logger.info("Connected to FTP: "+ ipAdress);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ftpClient;
	}
	


}
