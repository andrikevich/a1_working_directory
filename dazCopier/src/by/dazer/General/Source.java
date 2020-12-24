package by.dazer.General;

import java.io.File;

public interface Source {
    /**
     * <b>read()</b> retrives File[] of files that are in source folder
     * If choose FTP like a child of Sourse,
     * <br> <b>read()</b> copy files to TMP Folder Copier.TEMP_PATH and retrives
     * files from it.
     */

    File[] read();

    /**
     * <b>fldDest</b> is a folder where files are going to be downloaded in (exept TMP folder which is using in method <b>read()</b>)
     * <br>
     *
     * @param fldDest is a forder path
     * @return File[]
     */
    File[] readWithoutTmpFld(String fldDest);
}
