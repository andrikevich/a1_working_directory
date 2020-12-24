package by.dazer.FTP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class FromFTPtoLocalDisk {
   // public static String host1, log1, pass1,sourseFolderInFtp1,destFolder1;
    //public static FTPFile tempFile;





    public static void ftpConn(String host, String log, String pass, String sourseFolderInFtp, String destFolder) throws IOException {
        FTPClient ftpClient = new FTPClient();

        try
        {   ftpClient.setAutodetectUTF8( true );
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

                    outputStream1.close();
                }
            }





        }
        catch (IOException ex)
        {
            System.err.print(ex);
        }
    }
/**

 */    public static void dlDirAndFilesFromFTPtoLocalDisk(FTPClient ftpClient, String host, String login, String pass, String sourseFolderInFtp, String destFolder){
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



    public static void autoDlFromFtp (FTPClient ftpClient, String host, String login, String pass, String sourseFolderInFtp, String destFolder) {

        try
        {
            ftpClient.disconnect();
            ftpClient.setAutodetectUTF8( true );
            ftpClient.connect(host);
            ftpClient.login(login, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);



            File destinationFld = new File(destFolder); // forder in local drive where files will be downloaded
            FTPFile[] ftpFilesInitial = ftpClient.listFiles(sourseFolderInFtp); //list of FTP files that there are in the FTP server now, as initial for further comparing

            Map<String, Long> ftpFilesOld = new HashMap<>(); // Map contains file name and size
            for (FTPFile filesInFolderInFtp : ftpFilesInitial) {
                ftpFilesOld.put (filesInFolderInFtp.getName(), filesInFolderInFtp.getSize());
            }



            while(true) {
                Thread.sleep(5_000); //period of time for monitoring FTP folder

                ftpClient.disconnect();
                ftpClient.setAutodetectUTF8( true );
                ftpClient.connect(host);
                ftpClient.login(login, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                FTPFile[] ftpFilesNew = ftpClient.listFiles(sourseFolderInFtp);  //new list of current files in FTP server
                if (ftpFilesNew.length != 0) {

                    for (int countNewFile = 0; countNewFile < ftpFilesNew.length; countNewFile++) {
                        String fileName2 = ftpFilesNew[countNewFile].getName();

                        for (Map.Entry<String, Long> oldMapOfFiles : ftpFilesOld.entrySet()) {

                            if (oldMapOfFiles.getKey().equals(fileName2)) {  //имя из старого списка совпадает с новым
                                if (oldMapOfFiles.getValue() == ftpFilesNew[countNewFile].getSize()) {//+ проверка к имени размера файла
                                    if(ftpFilesNew[countNewFile].isFile()){
                                        dlDirAndFilesFromFTPtoLocalDisk(ftpClient, host, login, pass, sourseFolderInFtp , destFolder);
                                    }
                                    else if(ftpFilesNew[countNewFile].isDirectory()){ // check is a directory
                                        if(!Files.exists(Paths.get(destFolder + "\\" + fileName2 ))) {
                                            Files.createDirectory(Paths.get(destFolder + "\\" + fileName2));
                                        }
                                        dlDirAndFilesFromFTPtoLocalDisk(ftpClient, host, login, pass, sourseFolderInFtp + "/" + fileName2, destFolder + "\\" + fileName2);
                                    }
                                }
                            }
                        }


                    }
                    //creating new OldMap for next iteration
                    ftpFilesNew = ftpClient.listFiles(sourseFolderInFtp);
                    ftpFilesOld = new HashMap<>();
                    for (FTPFile filesInFolderInFtp : ftpFilesNew) {

                        String  fimeNameOldMap =filesInFolderInFtp.getName();
                        if (filesInFolderInFtp.isDirectory() && (ftpClient.listFiles(sourseFolderInFtp + "/" + fimeNameOldMap).length == 0)){
                            String folderName = sourseFolderInFtp + "/" + filesInFolderInFtp.getName();
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

}
