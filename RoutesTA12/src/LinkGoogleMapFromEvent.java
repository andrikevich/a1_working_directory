

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;







class LinkGoogleMapFromEvent {


    public static void main(String[] args) {

        // Open standart filechooser
        JFileChooser fileopen = new JFileChooser("\\\\srv-logsblr-003\\f$\\Documents\\_Events\\");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT & *.LOG & *.ENT files", "txt", "log", "ENT");
        fileopen.setFileFilter(filter);

        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            String filename = fileopen.getSelectedFile().getName(); // получаем имя выбранного файла
            try {
                BufferedReader br =new BufferedReader (new InputStreamReader (new FileInputStream(fileopen.getSelectedFile())));

                String startUrl = "https://www.google.com/maps/dir/";
                String strLine, finishUrl = "13.37z/data=!4m2!4m1!3e0?hl=ru";//finish of link for browser
                StringBuilder strBuildFull = new StringBuilder (startUrl);
                StringBuilder strBuild33 = new StringBuilder (startUrl);
                StringBuilder strBuild25 = new StringBuilder (startUrl);
                int countStr = 0;
                    if (filename.endsWith(".log")){             //checking for TA 12, files have been saved as *.log
                        while ((strLine = br.readLine()) != null) {


                            if (strLine.contains("Latitude")) {
                                countStr++;
                                strBuildFull.append(strLine.substring(strLine.lastIndexOf("Latitude:") + 10, strLine.lastIndexOf("Longitude") - 2)).append(",");

                                if (countStr % 3 != 0) {
                                    strBuild33.append(strLine.substring(strLine.lastIndexOf("Latitude:") + 10, strLine.lastIndexOf("Longitude") - 2)).append(",");
                                } else if (countStr % 4 != 0) {
                                    strBuild25.append(strLine.substring(strLine.lastIndexOf("Latitude:") + 10, strLine.lastIndexOf("Longitude") - 2)).append(",");
                                }
                            }

                            if (strLine.contains("Longitude")) {
                                strBuildFull.append(strLine.substring(strLine.lastIndexOf("Longitude") + 11, strLine.lastIndexOf("TimeStamp") - 2)).append("/");

                                if (countStr % 3 != 0) {
                                    strBuild33.append(strLine.substring(strLine.lastIndexOf("Longitude") + 11, strLine.lastIndexOf("TimeStamp") - 2)).append("/");
                                } else if (countStr % 4 != 0) {
                                    strBuild25.append(strLine.substring(strLine.lastIndexOf("Longitude:") + 11, strLine.lastIndexOf("TimeStamp") - 2)).append("/");
                                }

                            }

                        }
                    } else if (filename.endsWith(".txt")){    // fot TA 9.2
                        while ((strLine = br.readLine()) != null){


                            if (strLine.contains("LatestLatitude") ){
                                countStr ++;
                                strBuildFull.append(strLine.substring(strLine.lastIndexOf("=")+1, strLine.length())).append(",");

                                if(countStr % 3 !=0){
                                    strBuild33.append(strLine.substring(strLine.lastIndexOf("=")+1, strLine.length())).append(",");
                                } else if (countStr % 4 !=0){
                                    strBuild25.append(strLine.substring(strLine.lastIndexOf("=")+1, strLine.length())).append(",");
                                }
                            }

                            if (strLine.contains("LatestLongitude") ){
                                strBuildFull.append(strLine.substring(strLine.lastIndexOf("=")+1, strLine.length())).append("/");

                                if(countStr % 3 !=0){
                                    strBuild33.append(strLine.substring(strLine.lastIndexOf("=")+1, strLine.length())).append("/");
                                } else if (countStr % 4 !=0){
                                    strBuild25.append(strLine.substring(strLine.lastIndexOf("=")+1, strLine.length())).append("/");
                                }

                            }

                        }
                            }
                strBuildFull.append(finishUrl);
                strBuild33.append(finishUrl);
                strBuild25.append(finishUrl);
                br.close();


                //Getting results
                JFrame frame = new JFrame("Results");
                frame.setSize(300, 300);
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.CENTER));
                panel.setSize(100, 100);
                frame.getContentPane().add(panel);
                frame.setVisible(true);
                JTextArea textField = new JTextArea(50,50);
                if (strBuildFull.length()<10000){
                    textField.append(String.valueOf(strBuildFull));
                } else if (strBuild33.length()<10000){
                    textField.append(String.valueOf(strBuild33));
                } else textField.append(String.valueOf(strBuild25));
                JScrollPane scrollPane = new JScrollPane(textField);

                scrollPane.setViewportView(textField);



                textField.setSize(50, 50);
                panel.add(textField);
                panel.setVisible(true);
                textField.setLineWrap(true);
                textField.setWrapStyleWord(true);


                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.dispose();
                if (Desktop.isDesktopSupported() && (Desktop.getDesktop()).isSupported(Desktop.Action.BROWSE)) {
                    URI myUrl = new URI(textField.getText());
                    Desktop.getDesktop().browse(myUrl);
                }


            } catch (FileNotFoundException ex) {
                Logger.getLogger(LinkGoogleMapFromEvent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LinkGoogleMapFromEvent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(LinkGoogleMapFromEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }






    }
}

