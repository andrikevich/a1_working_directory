package by.dazer;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;


public class FromFTPtoLocalDisk {



    public static void ftpConn(String host, String log, String pass, String sourseFolderInFtp, String destFolder) throws IOException {
        FTPClient ftpClient = new FTPClient();

        try
        {
            ftpClient.connect(host);
            ftpClient.login(log, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);


//            String sourseFolderInFtp = "/MTULOG/"; //folder in Ftp from that you want to download
            File destinationFld = new File(destFolder); //folder where you want to download

            FTPFile[] ftpFiles = ftpClient.listFiles(sourseFolderInFtp);



            for (FTPFile filesInFolderInFtp : ftpFiles) {

                if (filesInFolderInFtp.isFile()) {
                    OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(destinationFld + "\\" + filesInFolderInFtp.getName()));
                    boolean success = ftpClient.retrieveFile(sourseFolderInFtp + "/" + filesInFolderInFtp.getName(), outputStream1);
                    boolean deleted = ftpClient.deleteFile(sourseFolderInFtp + "/" + filesInFolderInFtp.getName());
                    outputStream1.close();
                }
            }





        }
        catch (IOException ex)
        {
            System.err.print(ex);
        }
    }



}