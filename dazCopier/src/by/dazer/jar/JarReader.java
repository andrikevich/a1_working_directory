package by.dazer.jar;

import org.apache.commons.io.IOUtils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarReader {

    public static String readZipFile(String zipOrJarFilePath, String relativeFilePath) {
        try {
            ZipFile zipFile = new ZipFile(zipOrJarFilePath);
            Enumeration<? extends ZipEntry> e = zipFile.entries();

            while (((Enumeration<?>) e).hasMoreElements()) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                // if the entry is not directory and matches relative file then extract it
                if (!entry.isDirectory() && entry.getName().equals(relativeFilePath)) {
                    BufferedInputStream bis = new BufferedInputStream(
                            zipFile.getInputStream(entry));
                    // Read the file
                    // With Apache Commons I/O
                    String fileContentsStr = IOUtils.toString(bis, "UTF-8");

                    // With Guava
                    //String fileContentsStr = new String(ByteStreams.toByteArray(bis),Charsets.UTF_8);
                    // close the input stream.
                    bis.close();
                    return fileContentsStr;
                } else {
                    continue;
                }
            }
        } catch ( IOException e) {
            System.err.println("IOError :" + e);
        }
        return null;
    }
}
