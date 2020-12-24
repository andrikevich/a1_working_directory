package by.dazer.LocalFolders;

import by.dazer.General.Destination;
import by.dazer.General.Source;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

public class LocalFolder implements Source, Destination {

    private String folder;


    public LocalFolder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    @Override
    public void write(File[] files) {
        try  {
            for (File filesList : files){
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(folder + filesList.getName()));
                byte[] buffer = Files.readAllBytes(filesList.toPath());
                outputStream.write(buffer);
                outputStream.close();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getDestinFld() {
        return folder;
    }

    @Override
    public File[] read() {
        File fileOrFolder = new File (folder);
        if(fileOrFolder.isDirectory()){
            return fileOrFolder.listFiles();
        } else {
            return new File[]{fileOrFolder}; //if copied iteam is file
        }

    }

    @Override
    public File[] readWithoutTmpFld(String fldDest) {
        return read();
    }
}
