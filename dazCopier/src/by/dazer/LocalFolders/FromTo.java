package by.dazer.LocalFolders;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FromTo {

    /**
     * File fileSourse ---file that you want to download;
     * folderDest --- folder where sourse file will be downloaded;
     */
    public static  void copyFileToFolder(String fileSourse, String folderDest) throws IOException {
        File source = new File (fileSourse);
        File dest = new File(folderDest);



        InputStream is = null;
        OutputStream os = null;
        if (source.isFile()) {
            try {
                is = new FileInputStream(source);
                os = new FileOutputStream(dest+"\\"+source.getName());

                byte[] buffer = new byte[1024];
                int length;


                while ((length = is.read(buffer)) > 0) {

                    os.write(buffer, 0, length);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                is.close();
                os.close();
            }
        }
    }

    /**
     * Folder folderSourse ---all files in these folder that you want to download;
     * folderDest --- folder where sourse file will be downloaded;
     */
    public static  void copyFolderToFolder(String folderSource, String folderDest) throws IOException {
        File source = new File(folderSource);
        File dest = new File(folderDest);


        InputStream is = null;
        OutputStream os = null;
        File[] allFilesInSourseFld = source.listFiles();
        for (File file : allFilesInSourseFld) {
            if (file.isFile()) {
                try {
                    is = new FileInputStream(file);
                    os = new FileOutputStream(dest + "\\" + file.getName());

                    byte[] buffer = new byte[1024];
                    int length;


                    while ((length = is.read(buffer)) > 0) {

                        os.write(buffer, 0, length);
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    is.close();
                    os.close();
                }
            }
        }
    }



    //copy Backup files
    // Copy back up files from source to destination folder aтв archive it to ZIP
    private static void copyFileUsingStream(File source, File zipFileDest) throws IOException {
        InputStream is = null;
        ZipOutputStream os = null;
        if (source.isFile()) {
            try {
                is = new FileInputStream(source);
                os = new ZipOutputStream(new FileOutputStream(zipFileDest));
                byte[] buffer = new byte[1024];
                int length;
                ZipEntry ze = new ZipEntry(source.getName());
                os.putNextEntry(ze);

                while ((length = is.read(buffer)) > 0) {

                    os.write(buffer, 0, length);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                is.close();
                os.close();
            }
        }
    }
}
