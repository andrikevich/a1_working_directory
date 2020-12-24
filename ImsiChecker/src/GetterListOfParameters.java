
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetterListOfParameters {
    private List <String> listOfParameters = new ArrayList<String>();
    private String urlToFile;
    public GetterListOfParameters (String urlToFile){
        this.urlToFile = urlToFile;
    }

    public List <String> getListOfParameters() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(urlToFile));
            String str;
            List <String> tempLst = new ArrayList<String>();


            while ((str = br.readLine())!= null){
                tempLst.addAll(Arrays.asList(str.split(";")));
            }

            // checking of all parameters for sorting MSISDN, IMSI and IMEI etc
            for( String singlParameter : tempLst){

                //Checking of MSISDN
                if (singlParameter.startsWith("+")) {
                    singlParameter = singlParameter.substring(1, singlParameter.length());
                    if (singlParameter.startsWith("375") && singlParameter.length()== 12) {
                        listOfParameters.add(singlParameter.trim());

                    }
                } else if (singlParameter.startsWith("375") && singlParameter.length()== 12) {
                      listOfParameters.add(singlParameter.trim());

                      // Checking IMSI
                        } else if (singlParameter.startsWith("25701") && singlParameter.length()== 15) {
                    listOfParameters.add("IMSI "+singlParameter.trim());

                    //Checking IMEI
                                } else if(singlParameter.length()== 16){
                                    listOfParameters.add("IMEI "+singlParameter.trim());                }
                                        else {
                                            listOfParameters.add("Invalid number");
                                        }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return listOfParameters;
    }
}
