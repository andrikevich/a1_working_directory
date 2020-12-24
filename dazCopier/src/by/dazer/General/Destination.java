package by.dazer.General;

import java.io.File;
import java.nio.file.Files;

public interface Destination {

    void write(File[] files);

     String getDestinFld();
}
