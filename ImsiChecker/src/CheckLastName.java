import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckLastName {
    public static void main(String[] args) throws IOException {


        //TEST
        StringBuilder result = new StringBuilder();
        String mSISDN = "375291155365";
        URL url = new URL("http://srv-complains-001:81/complainsBSCSprod/search.jsp?id=" + mSISDN + "&msisdn=1&telnum=" + mSISDN);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            //System.out.println(line);
            if (line.startsWith("      <td><input id=\"fioOrg\" class=\"required\" name=\"fioOrg\" type=\"text\"  value=\"")){
                System.out.println(line.substring((line.indexOf("text\"  value=")+14),line.indexOf("\" style=\"width:350px\"  /></td>")));
            }
            //<input id="clubid" name="clubid"
            if (line.contains("<input id=\"clubid\" name=\"clubid\"")){
                System.out.println(line.substring((line.indexOf("\"clubid\"  readonly type=\"text\" size=\"15\"  value=\"")+49),line.indexOf("\"  style=\" background-color:")));
            }
        }
        rd.close();
        //System.out.println(result.toString());
    }

    }

