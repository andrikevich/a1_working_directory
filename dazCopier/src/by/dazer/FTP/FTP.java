package by.dazer.FTP;

import by.dazer.General.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPHTTPClient;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class FTP implements Source, Destination {

    private String ipAdress;
    private String loginName;
    private String loginPwd;

    public FTP(String ipAdress, String loginName, String loginPwd) {
        this.ipAdress = ipAdress;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
    }

    public FTP(String ipAdress, String loginName, String loginPwd, String destFtpFolder) {
        this.ipAdress = ipAdress;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.destFtpFolder = destFtpFolder;
    }

    private String destFtpFolder;

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getDestFtpFolder() {
        return destFtpFolder;
    }

    public void setDestFtpFolder(String destFtpFolder) {
        this.destFtpFolder = destFtpFolder;
    }



    @Override
    public File [] read() {

        return readWithoutTmpFld (Copier.TEMP_PATH);
    }



    @Override
    /**
     * <b> fldDest</b> <i>folder where files from FTP will be downloaded </i>
     */
    public File[] readWithoutTmpFld(String fldDest) {
        FTPClient ftpClient = new FTPClient();
        File [] sourceFiles = null;
        try {
            ftpClient.disconnect();
            ftpClient.setAutodetectUTF8(true);
            ftpClient.connect(ipAdress);
            ftpClient.login(loginName, loginPwd);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
            File destinationFld = new File(destFtpFolder); //folder where you want to download
            FTPFile[] ftpFiles = ftpClient.listFiles(destFtpFolder);
            sourceFiles = new File[ftpFiles.length];

            if (destinationFld.isDirectory()) {

                for (int i = 0; i < ftpFiles.length; i++) {
                    String filename = new String(ftpFiles[i].getName());
                    try {
                        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fldDest+ "\\" + filename));
                        boolean success = ftpClient.retrieveFile(destFtpFolder + "/" + filename, outputStream);
                        outputStream.flush();
                        outputStream.close();
                        sourceFiles = new File[]{destinationFld};
                    } catch (IOException ex) {
                        System.err.print(ex);
                    }
                }

            } else {                //destinationFld is file
                try {
                    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fldDest + "\\" + destinationFld.getName()));
                    boolean success = ftpClient.retrieveFile(destFtpFolder , outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException ex) {
                    System.err.print(ex);
                }
                File [] outFile = new File [1];
                outFile[0] = new File(fldDest + "\\" + destinationFld.getName());
                sourceFiles = outFile;

            }
            ftpClient.disconnect();
        }
        catch (IOException ex)
        {
            System.err.print(ex);
        }
        return sourceFiles;
    }

    @Override

    public void write(File[] files) {
        //new FTPHTTPClient("http://srv-wsgp-cluster.main.velcom.by", 8080, "dmitry_an", "07032015Ann(");
        FTPClient ftpClient = new FTPClient();
        try
        {   ftpClient.disconnect();
            ftpClient.setAutodetectUTF8( true );
            ftpClient.connect(ipAdress);
            ftpClient.login(loginName, loginPwd);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);

            for (File fileForUpload : files) {

                    InputStream inputStream = new BufferedInputStream(new FileInputStream(fileForUpload));
                   boolean irr =  ftpClient.storeFile(destFtpFolder + "/" + fileForUpload.getName(), inputStream);
                    inputStream.close();
            }

        }
        catch (IOException ex)
        {
            System.err.print(ex);
        }
        finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getDestinFld() {
        return destFtpFolder;
    }

    public void dlDirAndFilesFromFTPtoLocalDisk( String destFolder){
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.disconnect();
            ftpClient.setAutodetectUTF8( true );
            ftpClient.connect(ipAdress);
            ftpClient.login(loginName,loginPwd);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);

            File destinationFld = new File(destFolder); //Define the folder for saving files in local drive
            FTPFile[] ftpFilesInitial = ftpClient.listFiles(destFtpFolder); //initial list of files in folder in FTP
            if(ftpFilesInitial.length !=0 && ftpFilesInitial != null){     //if folder is not empty and exists
                for(int i = 0; i < ftpFilesInitial.length; i++){
                    String fileName = new String(ftpFilesInitial[i].getName());
                    if(ftpFilesInitial[i].isDirectory()){     // if element in FTP is a folder, will check: is Folder is present in local disk, if not it will create it.
                        if (Files.exists(Paths.get(destFolder + "\\" + fileName ))){     //If there is not  such folder in local drive it will be created for downloading there
                            dlDirAndFilesFromFTPtoLocalDisk(destFolder+ "\\" + fileName);
                            ftpClient.removeDirectory (destFtpFolder + "/" + fileName);

                        }else if(!Files.exists(Paths.get(destFolder + "\\" + fileName ))){       //if such directory there is in local drive
                            Files.createDirectory(Paths.get(destFolder + "\\" + fileName));
                            dlDirAndFilesFromFTPtoLocalDisk(destFolder+ "\\" + fileName);

                            ftpClient.removeDirectory(destFtpFolder + "/" + fileName);
                        }
                    }else if (ftpFilesInitial[i].isFile()){   // if element in FTP is a file
                        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(destinationFld + "\\" + fileName));
                        boolean success = ftpClient.retrieveFile(destFtpFolder + "/" + fileName, outputStream1);
                        boolean deleted = ftpClient.deleteFile (destFtpFolder + "/" + fileName);

                        outputStream1.close();
                    }


                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void autoDlFromFtpToLocalDisk (String destFolder) {
        FTPClient ftpClient = new FTPClient();
        try
        {
            ftpClient.disconnect();
            ftpClient.setAutodetectUTF8( true );
            ftpClient.connect(ipAdress);
            ftpClient.login(loginName, loginPwd);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);



            File destinationFld = new File(destFolder); // forder in local drive where files will be downloaded
            FTPFile[] ftpFilesInitial = ftpClient.listFiles(destFtpFolder); //list of FTP files that there are in the FTP server now, as initial for further comparing

            Map<String, Long> ftpFilesOld = new HashMap<>(); // Map contains file name and size
            for (FTPFile filesInFolderInFtp : ftpFilesInitial) {
                ftpFilesOld.put (filesInFolderInFtp.getName(), filesInFolderInFtp.getSize());
            }



            while(true) {
                Thread.sleep(5_000); //period of time for monitoring FTP folder

                ftpClient.disconnect();
                ftpClient.setAutodetectUTF8( true );
                ftpClient.connect(ipAdress);
                ftpClient.login(loginName, loginPwd);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);

                FTPFile[] ftpFilesNew = ftpClient.listFiles(destFtpFolder);  //new list of current files in FTP server
                if (ftpFilesNew.length != 0) {

                    for (int countNewFile = 0; countNewFile < ftpFilesNew.length; countNewFile++) {
                        String fileName2 = ftpFilesNew[countNewFile].getName();

                        for (Map.Entry<String, Long> oldMapOfFiles : ftpFilesOld.entrySet()) {

                            if (oldMapOfFiles.getKey().equals(fileName2)) {  //имя из старого списка совпадает с новым
                                if (oldMapOfFiles.getValue() == ftpFilesNew[countNewFile].getSize()) {//+ проверка к имени размера файла
                                    if(ftpFilesNew[countNewFile].isFile()){
                                        dlDirAndFilesFromFTPtoLocalDisk(destFolder);
                                    }
                                    else if(ftpFilesNew[countNewFile].isDirectory()){ // check is a directory
                                        if(!Files.exists(Paths.get(destFolder + "\\" + fileName2 ))) {
                                            Files.createDirectory(Paths.get(destFolder + "\\" + fileName2));
                                        }
                                        dlDirAndFilesFromFTPtoLocalDisk(destFolder + "\\" + fileName2);
                                    }
                                }
                            }
                        }


                    }
                    //creating new OldMap for next iteration
                    ftpFilesNew = ftpClient.listFiles(destFtpFolder);
                    ftpFilesOld = new HashMap<>();
                    for (FTPFile filesInFolderInFtp : ftpFilesNew) {

                        String  fimeNameOldMap =filesInFolderInFtp.getName();
                        if (filesInFolderInFtp.isDirectory() && (ftpClient.listFiles(destFtpFolder + "/" + fimeNameOldMap).length == 0)){
                            String folderName = destFtpFolder + "/" + filesInFolderInFtp.getName();
                            ftpClient.removeDirectory(folderName);
                            continue;
                        }
                        ftpFilesOld.put (filesInFolderInFtp.getName(),  filesInFolderInFtp.getSize());
                    }
                    ftpClient.disconnect();
                }
            }




        }
        catch (IOException ex)
        {
            System.err.print(ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public FTPFile[]  getArrayOfFolder (String folderPath){

        FTPClient ftpClient = new FTPClient();
        FTPFile[] ftpFiles = null;
        try {
            ftpClient.disconnect();
            ftpClient.setAutodetectUTF8(true);
            ftpClient.connect(ipAdress);
            ftpClient.login(loginName, loginPwd);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
            File destinationFld = new File(folderPath); //folder where you want to download
            ftpFiles = ftpClient.listDirectories(folderPath);

         }catch (IOException ex){
            ex.printStackTrace();
        }
        return ftpFiles;
    }
}
