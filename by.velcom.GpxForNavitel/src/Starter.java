import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Starter {

    public static void main(String[] args) {
        JFileChooser fileopen = new JFileChooser("c:\\Andrikevich\\GDR\\MTU\\ДрайвТесты\\!!!Routes\\");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.GPX files", "GPX");
        fileopen.setFileFilter(filter);

        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            String filename = fileopen.getSelectedFile().getName(); // получаем имя выбранного файла

            List<String> lst = new ArrayList<>();

            SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
            int count = 0;

            Calendar c1 = new GregorianCalendar();



            String strLine, finishGpxFile = "</trkseg>\n" +
                    "  </trk>\n" +
                    "  </gpx>";
            //finish of link for browser

            String startGpxFile = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<gpx\n" +
                    " version=\"1.0\"\n" +
                    " creator=\"GPSMapEdit 2.1.78.8 - http://www.geopainting.com\"\n" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                    " xmlns=\"http://www.topografix.com/GPX/1/0\"\n" +
                    " xsi:schemaLocation=\"http://www.topografix.com/GPX/1/0 http://www.topografix.com/GPX/1/0/gpx.xsd\">\n" +
                    "<time>";
            StringBuilder strBuildFull = new StringBuilder(startGpxFile);
            strBuildFull.append(String.valueOf(fd.format(c1.getTime())) + "T" + ft.format(c1.getTime())).append("</time>\n" +
                    "<bounds minlat=\"52.611508\" minlon=\"22.729025\" maxlat=\"54.762958\" maxlon=\"24.918902\"/>\n" +
                    "<trk>\n" +
                    " <name></name>\n" +
                    " <trkseg>");


            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileopen.getSelectedFile())))) {
                String res;
                    while ((res = br.readLine())!=null){
                        if (res.contains("<trkpt")){
                            lst.add(res);

                        }

                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            for (String l: lst){
                c1.add(Calendar.SECOND,count);
            strBuildFull.append(l).append("<time>").append(String.valueOf(fd.format(c1.getTime())) + "T" + ft.format(c1.getTime()) + "Z").append("</time><sat>8</sat><fix>3d</fix></trkpt>\n");
                count ++;

            }
            strBuildFull.append(finishGpxFile);
            JFileChooser fileSave = new JFileChooser("i:\\Download\\");
            fileSave.setFileFilter(filter);


            int save = fileSave.showDialog(null, "Save");
            String saveFile = fileSave.getSelectedFile().getAbsolutePath();

            if (save == JFileChooser.APPROVE_OPTION) {
                try (FileWriter fW = new FileWriter(saveFile+".gpx",false)){
                    fW.append(strBuildFull);
                    fW.close();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }

        }


    }
}

