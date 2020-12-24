package by.dazer.General;


import by.dazer.FTP.FTP;
import by.dazer.LocalFolders.LocalFolder;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.File;

public class Copier {

     public static final String TEMP_PATH = "c:\\TempJava";
     private File tempFolder = new File(TEMP_PATH);

     public void copyFld(Source sourceFolder, Destination destFolder) {
         if (sourceFolder instanceof FTP && destFolder instanceof FTP) {
             tempFolder.mkdirs();
             destFolder.write(sourceFolder.read());
             deleteTempDir();
         } else if(destFolder instanceof LocalFolder) {
             destFolder.write(sourceFolder.readWithoutTmpFld(((LocalFolder) destFolder).getFolder()));
         } destFolder.write(sourceFolder.read());
     }

     //Delete TEMP folder
      private void deleteTempDir () {
             File[] filesInTempDir = tempFolder.listFiles();
             for (File lstFiles : filesInTempDir) {
                 if (lstFiles.delete()) {
                     System.out.println("File was deleted");
                 } else {
                     System.out.println("Can't delete file");
                 }
             }
          tempFolder.delete();
         }




}
