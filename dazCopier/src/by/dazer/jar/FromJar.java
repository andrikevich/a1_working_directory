package by.dazer.jar;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FromJar {


    public static  void copyFileFromJarToFolder(String pathToJarFile, String relativePathToFile, String folderDest) {

        try {
            ZipFile zipFile = new ZipFile(pathToJarFile);
            Enumeration<? extends ZipEntry> e = zipFile.entries();

            InputStream is = null;
            OutputStream os = null;

            while (((Enumeration<?>) e).hasMoreElements()) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                if (!entry.isDirectory() && entry.getName().equals(relativePathToFile)) {
                    is = new BufferedInputStream(zipFile.getInputStream(entry));
                    File dest = new File(folderDest);
                    String fileName = entry.getName().substring(entry.getName().lastIndexOf("/")+1);
                    os = new FileOutputStream(dest+"\\"+fileName);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }

                }

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}
