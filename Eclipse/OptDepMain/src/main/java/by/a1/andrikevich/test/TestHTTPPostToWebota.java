package by.a1.andrikevich.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class TestHTTPPostToWebota {
	private static final String POST_PARAMS = "msisdn=375291155365";

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url;
			DataOutputStream printout;
			DataInputStream input;
			url = new URL("http://10.131.8.210:8080/webota/");
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
			printout.write(POST_PARAMS.getBytes());
			printout.flush();
			printout.close();

			int HttpResult = urlConnection.getResponseCode();

			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
				String line = null;

				while ((line = br.readLine()) != null) {
					
					sb.append(line + "\n");
				}
				
				br.close();

				// System.out.println(""+sb.toString());

			} else {
				System.out.println(urlConnection.getResponseMessage());
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
//		        finally{  
//		        if(urlConnection!=null)  
//		           urlConnection.disconnect();  
//		    } 
		System.out.println(sb);
	}
	
	
	
}
