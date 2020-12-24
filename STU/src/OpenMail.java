/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Dmitry_An
 */
public class OpenMail {
  public static void mail (String address,String subject,String body) {
        if (Desktop.isDesktopSupported() && (Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
            try {
                try {
                    String html_br = "&lt;br&gt;";
                    String mailToString = "mailto:" + address + "?subject=" + subject + "&body=" + body;

                    URI mailto = new URI(mailToString);
                    Desktop.getDesktop().mail(mailto);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
            }
        } else {

        }
    }  
}
