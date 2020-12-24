package by.dazer.IO;



import java.io.*;

public class ReaderInitialData {

    public String readStringFromFile (String path){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String result = "", tmpStr;

            while ((tmpStr = bufferedReader.readLine()) != null){
                result += tmpStr.trim();
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}




