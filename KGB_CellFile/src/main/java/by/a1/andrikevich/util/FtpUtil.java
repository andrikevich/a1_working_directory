package by.a1.andrikevich.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Properties;

public class FtpUtil {

    private Properties prop = PropertiesUtil.getProperty(InitialData.propertyFile);

    private String ftpIp = prop.getProperty("ftp.ip");
    private String ftpUser = prop.getProperty("ftp.user");
    private String ftpPassword = prop.getProperty("ftp.password");
    private String ftpUrl = prop.getProperty("ftp.url");
    private String localDiskUrl = prop.getProperty("localdisk.url");
    private String extension = prop.getProperty("element.extension");
    private String startFileName = prop.getProperty("start.of.filename");

    public void saveFileToFtp() {


        TimeDateHandler timeDateHandler = new TimeDateHandler();
        String curDate = timeDateHandler.getCurrentDate();
        String dateWeekBefore = timeDateHandler.getDateWithOffset(-7);

        String fileNameWithDate = localDiskUrl + startFileName + curDate + extension;
        String fileNameWithDateMinus7 = new File (localDiskUrl + startFileName + dateWeekBefore + extension).getName();


        FTPClient ftpClient = this.getFtpConnection(ftpIp, ftpUser, ftpPassword);
        try {

            File fileForUpload = new File(fileNameWithDate);

            InputStream inputStream = new BufferedInputStream(new FileInputStream(fileForUpload));
            ftpClient.storeFile(ftpUrl + "/" + fileForUpload.getName(), inputStream);
            inputStream.close();

            //delete copy of file in local directory
            LocalFileCreator localFileCreator = new LocalFileCreator();
            localFileCreator.deleteDirectory(new File(localDiskUrl));

           //Deleting an old file from FTP. There are only 6 last file on FTP server
            ftpClient.deleteFile(ftpUrl + "/" + fileNameWithDateMinus7);


        } catch (IOException ex) {
            ex.printStackTrace();

        }
        finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    private FTPClient getFtpConnection(String ftpIp, String ftpUser, String ftpPassword) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.disconnect();
            ftpClient.setAutodetectUTF8(true);
            ftpClient.connect(ftpIp);
            ftpClient.login(ftpUser, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }



}
