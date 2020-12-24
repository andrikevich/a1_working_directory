import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 2017-03-12.
 */
public class Starter {

    static String url = "http://10.131.8.210:8080/webota/";
    public static void main(String[] args) throws IOException {
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse httpGet = client.execute(new HttpGet(url))

        ) {
            HttpPost httpPost = new HttpPost(url);
            // Request parameters and other properties.
            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("msisdn", "375447100828"));
            params.add(new BasicNameValuePair("msisdn", "375291155365"));
            params.add(new BasicNameValuePair("msisdn", "IMSI 257010030369921"));




                httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


//Execute and get the response.

                HttpResponse response = client.execute(httpPost);

                HttpEntity entity = response.getEntity();



                StringBuilder strBld = new StringBuilder();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    try {
                        String data = IOUtils.toString(entity.getContent(), "cp1251");
                        System.out.println("Data: " + data);
                        BufferedReader bufferedReader = new BufferedReader(new StringReader(data));
                        String tempStr;
                        boolean flagForSaveMsisdn = false;
                        boolean flagForSaveImsi = false;
                        boolean flagForSaveImei = false;
                        while ((tempStr = bufferedReader.readLine()) != null) {
                            if (flagForSaveMsisdn) {
                                flagForSaveMsisdn = false;
                                strBld.append(tempStr.substring(4, tempStr.length() - 10)).append("\n");
                            }
                            if (tempStr.equals("<td>MSISDN</td>")) {
                                flagForSaveMsisdn = true;
                            }


                        }


                        System.out.println(strBld);

                    } finally {
                        instream.close();
                    }
                }




//--------------
/*

            params.clear();
            params.add(new BasicNameValuePair("msisdn", "IMSI 257010030369921"));

            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


//Execute and get the response.

            response = client.execute(httpPost);

            entity = response.getEntity();



            strBld = new StringBuilder();
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    String data = IOUtils.toString(entity.getContent(), "cp1251");
                    //System.out.println("Data: " + data);
                    BufferedReader bufferedReader = new BufferedReader(new StringReader(data));
                    String tempStr;
                    boolean flagForSaveMsisdn = false;
                    boolean flagForSaveImsi = false;
                    boolean flagForSaveImei = false;
                    while ((tempStr = bufferedReader.readLine()) != null) {
                        if (flagForSaveMsisdn) {
                            flagForSaveMsisdn = false;
                            strBld.append(tempStr.substring(4, tempStr.length() - 10)).append("\n");
                        }
                        if (tempStr.equals("<td>MSISDN</td>")) {
                            flagForSaveMsisdn = true;
                        }


                    }

                    System.out.println("*******************************");
                    System.out.println(strBld);

                } finally {
                    instream.close();
                }
            }
//----------
// */








        } catch (Throwable cause) {
            cause.printStackTrace();
        }




    }
}

