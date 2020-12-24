import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WixChecker {
    private String mSISDN;

    public WixChecker(String mSISDN) {
        this.mSISDN = mSISDN;
    }

    private String infoByMsisdn () {

        StringBuilder result = new StringBuilder();
        URL url = null;
        try {
            url = new URL("http://srv-complains-001:81/complainsBSCSprod/search.jsp?id=" + mSISDN + "&msisdn=1&telnum=" + mSISDN);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
        e.printStackTrace();
    }
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                //System.out.println(line);
                if (line.startsWith("      <td><input id=\"fioOrg\" class=\"required\" name=\"fioOrg\" type=\"text\"  value=\"")){
                    result.append(line.substring((line.indexOf("text\"  value=")+14),line.indexOf("\" style=\"width:350px\"  /></td>"))).append(";");
                }
                //<input id="clubid" name="clubid"
                if (line.contains("<input id=\"clubid\" name=\"clubid\"")){
                    result.append(line.substring((line.indexOf("\"clubid\"  readonly type=\"text\" size=\"15\"  value=\"")+49),line.indexOf("\"  style=\" background-color:"))).append(";");
                }
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return result.toString();
    }

    public String infoFromWix (){
        if (mSISDN.startsWith("375296") || mSISDN.startsWith("375291") ||mSISDN.startsWith("375293") ||
                mSISDN.startsWith("375299") ||mSISDN.startsWith("37544")) {
            return this.infoByMsisdn();
        } else return "";
    }
}
