import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class HttpClient {
    private CloseableHttpClient client;
    private CloseableHttpResponse httpGet;
    private HttpPost httpPost;

    public StringBuilder getResultStrBld() {
        return resultStrBld;
    }

    private  StringBuilder resultStrBld = new StringBuilder();

    public HttpClient(String urlForHttp, List <String> listOfParameters){
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse httpGet = client.execute(new HttpGet(urlForHttp))

        ) {
            HttpPost httpPost = new HttpPost(urlForHttp);
            resultStrBld.append("MSISDN;IMSI;IMEI;Device;CreationDate;APNs;Club;Name\n");
            String iMSI = null;
            String mISDN = null;
            String iMEI = null;
            String device = null;
            String creationDate = null;
            String aPN = null;


            // Request parameters and other properties.
            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            for (String singleParam : listOfParameters) {
                iMSI = "";
                mISDN = "";
                iMEI = "";
                device = "";
                creationDate = "";
                aPN = "";

                if(!singleParam.equals("Invalid number")){
                    params.add(new BasicNameValuePair("msisdn", singleParam));
                    httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                    //Execute and get the response.
                    HttpResponse response = client.execute(httpPost);
                    HttpEntity entity = response.getEntity();

                    if (entity != null) {
                        InputStream instream = entity.getContent();
                        try {

                            String data = IOUtils.toString(instream, "cp1251");
                            BufferedReader bufferedReader = new BufferedReader(new StringReader(data));
                            String tempStr;
                            boolean flagForSaveMsisdn = false;
                            boolean flagForSaveImsi = false;
                            boolean flagForSaveImei = false;
                            boolean flagForSaveDevice = false;
                            boolean flagForSaveCreationDate = false;
                            boolean flagForSaveApn = false;

                            while ((tempStr = bufferedReader.readLine()) != null) {
                                if (flagForSaveMsisdn) {
                                    flagForSaveMsisdn = false;
                                    mISDN = tempStr.substring(4, tempStr.length() - 10);
                                }
                                if (tempStr.equals("<td>MSISDN</td>")) {
                                    flagForSaveMsisdn = true;
                                }

                                if (flagForSaveImsi) {
                                    flagForSaveImsi = false;
                                    iMSI = tempStr.substring(4, tempStr.length() - 10);
                                }
                                if (tempStr.equals("<td>IMSI</td>")) {
                                    flagForSaveImsi = true;
                                }

                                if (flagForSaveImei) {
                                    flagForSaveImei = false;
                                    iMEI = tempStr.substring(4, tempStr.length() - 10);
                                }
                                if (tempStr.equals("<td>IMEI</td>")) {
                                    flagForSaveImei = true;
                                }

                                if (flagForSaveDevice) {
                                    flagForSaveDevice = false;
                                    device = tempStr.substring(4, tempStr.length() - 10);
                                }
                                if (tempStr.equals("<td>Device</td>")) {
                                    flagForSaveDevice = true;
                                }

                                if (flagForSaveCreationDate) {
                                    flagForSaveCreationDate = false;
                                    creationDate = tempStr.substring(4, tempStr.length() - 10);
                                }
                                if (tempStr.equals("<td>Creation</td>")) {
                                    flagForSaveCreationDate = true;
                                }

                                if (flagForSaveApn) {
                                    flagForSaveApn = false;
                                    aPN = tempStr.substring(4, tempStr.length() - 10);
                                }
                                if (tempStr.equals("<td>APNs</td>")) {
                                    flagForSaveApn = true;
                                }

                            }


                        } finally {
                            instream.close();
                        }
                    }

                    resultStrBld.append(mISDN).append(";").append(iMSI).append(";").append(iMEI).append(";").append(device).append(";").append(creationDate).append(";")
                            .append(aPN).append(";");
                    params.clear();
                    WixChecker wixChecker = new WixChecker(mISDN);
                    resultStrBld.append(wixChecker.infoFromWix()).append("\n");

            }
        }
            System.out.println(resultStrBld);
        }
        catch ( Throwable cause) {
            cause.printStackTrace();
        }
    }

}
