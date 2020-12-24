package by.a1.andrikevich.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class LocalFileCreator {

    private Properties prop = PropertiesUtil.getProperty(InitialData.propertyFile);

    private String tableHeaders = prop.getProperty("db.tableheader");
    private String localDiskUrl = prop.getProperty("localdisk.url");
    private String extension = prop.getProperty("element.extension");
    private String startFileName = prop.getProperty("start.of.filename");

    public  void createLocalFile (StringBuilder strBldrToSave){
        StringBuilder tmpStrBldforSave = new StringBuilder();
        tmpStrBldforSave.append(tableHeaders).append("\n").append(strBldrToSave);

        TimeDateHandler timeDateHandler = new TimeDateHandler();
        String curDate = timeDateHandler.getCurrentDate();

        try {
            new File(localDiskUrl).mkdir();
            FileOutputStream fileStream = new FileOutputStream(new File(localDiskUrl + startFileName + curDate + extension));
            OutputStreamWriter writer = new OutputStreamWriter(fileStream, StandardCharsets.US_ASCII);
            byte [] tmpByteArr = tmpStrBldforSave.toString().getBytes();
            String strAscii = new String(tmpByteArr,StandardCharsets.US_ASCII);
            writer.write(strAscii);
            writer.flush();
            writer.close();


        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
