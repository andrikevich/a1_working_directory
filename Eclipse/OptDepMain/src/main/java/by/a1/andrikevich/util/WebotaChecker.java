package by.a1.andrikevich.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class WebotaChecker {
/**	
	@param urlForHttp -- url to Webota
	(e.g. "http://10.131.8.210:8080/webota/")
	<br>
	
 @param listOfParameters -- list of any parameter:
 *   <ul>
 *      <li>MSISDN (e.g 375296000144)</li>
 *   </ul>*/	
	public StringBuilder retriveDataFromWebota (String urlForHttp, String singleSimParameter) {
		StringBuilder resultStrBld = new StringBuilder();
        String iMSI = "";
        String mISDN = "";
        String iMEI = "";
        String device = "";
        String creationDate = "";
        String aPN = null;
        String httpParameter = "msisdn=" + singleSimParameter;
		try {
			URL url;
			DataOutputStream printout;
			url = new URL(urlForHttp);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);

			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);

			//urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.connect();

			printout = new DataOutputStream(urlConnection.getOutputStream());
			printout.write(httpParameter.getBytes());
			printout.flush();
			printout.close();

			int HttpResult = urlConnection.getResponseCode();

			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
				String tempStr;
                boolean flagForSaveMsisdn = false;
                boolean flagForSaveImsi = false;
                boolean flagForSaveImei = false;
                boolean flagForSaveDevice = false;
                boolean flagForSaveCreationDate = false;
                boolean flagForSaveApn = false;

				while ((tempStr = br.readLine()) != null) {
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
				
				br.close();

			} else {
				System.out.println(urlConnection.getResponseMessage());
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
        resultStrBld.append("<b>MSISDN:</b> " + mISDN).append(";<br>").append("<b>IMSI:</b> " + iMSI).append(";<br>")
		.append("<b>IMEI:</b> " + iMEI).append(";<br>").append("<b>LastDevice:</b> " + device).append(";<br>")
		.append("<b>CreationDate:</b> " + creationDate).append(";<br>")
		.append("<b>APN:</b> " + aPN).append(";").append("\n");
        
		
        return resultStrBld;
	}
}
