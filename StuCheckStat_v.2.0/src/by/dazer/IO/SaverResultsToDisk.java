package by.dazer.IO;

import by.dazer.Utilities.TimeDateHandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class SaverResultsToDisk {
    public void saveNotWorkingStuStatToDisk (String pathForSaving, Map <Integer, List<String>> mapForSaving){
        try ( BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathForSaving,true)))) {
            StringBuilder strBld = new StringBuilder();
            String date = new TimeDateHandler().getCurrentDate();
            strBld.append("\n        ---===   Results of not working STU   " + date + "   ===--- \n\n");
            for (Map.Entry <Integer, List<String>> tmpMap : mapForSaving.entrySet()){
                strBld.append(tmpMap.getKey()).append(";");
                int counter = 0;

                for (String tmpStr  : tmpMap.getValue()){

                    strBld.append(tmpStr);
                    counter ++;

                    if (counter != tmpMap.getValue().size()) {
                        strBld.append(";");
                    }
                }
                strBld.append("\n");
            }
            strBld.append("**********************************\n");
            bw.write(strBld.toString());
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveToDiskStatisticsFromStu (String pathForSaving, StringBuilder sourceForSaving){
        try {
            FileWriter fileWriter = new FileWriter(pathForSaving,true);
            fileWriter.write(sourceForSaving.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
