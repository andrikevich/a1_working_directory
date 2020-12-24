package by.dazer;

import by.dazer.FTP.FTP;
import by.dazer.FTP.FromFTPtoLocalDisk;
import by.dazer.Filter.TimeFilter;
import by.dazer.General.Copier;
import by.dazer.LocalFolders.LocalFolder;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test1 {

    public static void main(String[] args) throws IOException {

       // ---------------------------
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // automatical download from FTP
//        FTPClient ftpClient  = new FTPClient();
//        FromFTPtoLocalDisk.dlDirAndFilesFromFTPtoLocalDisk(ftpClient,"77.74.36.238","tems","mkSmet_k6","For Dima lost/Drogichin","i:\\Download");
        //--------------------------------------

//        InputStream is = new BufferedInputStream(new FileInputStream("i:\\Download\\9.jpg"));
//        OutputStream os =new BufferedOutputStream(new FileOutputStream("i:\\Download\\123\\1.jpg"));
//        byte [] buff =  new byte[1024];
//        int tempRead;
//        is.r
//        while(( tempRead = is.read(buff)) !=-1){
//            os.write(buff);
//            System.out.println(tempRead);

//
//        }

//        System.out.println("strt");
//        Copier copier = new Copier();
//        FTP ftp1 = new FTP("185.36.170.87","velcom","velcom2016!","cellfile/");
//        FTP ftp2 = new FTP("77.74.36.238","tems","mkSmet_k6","For Dima lost/Drogichin");
//        LocalFolder localFolder1 = new LocalFolder("i:\\Download\\1.t");
//        LocalFolder localFolder2 = new LocalFolder("i:\\Download\\2222");
//        TimeFilter timeFilter = new TimeFilter();
//        boolean filter = timeFilter.isWeekDayNumber(2);
//        if (filter) {
//            copier.copyFld(localFolder1, ftp2);
//        }
//        System.out.println("fnsh");
//


//        Path path = FileSystems.getDefault().getPath("c:\\TempJava\\1105161051.trp");
//        Files.delete(path);
        //System.out.println(new File("c:\\TempJava\\IDLE_def_2018_09_25_124341_EQ114_FE0A.trp").delete());


//        Date calendar = new GregorianCalendar().getTime();
//        SimpleDateFormat sfd = new SimpleDateFormat("u");
//        String dayOfWeek = sfd.format(calendar);
//        System.out.println(dayOfWeek.equalsIgnoreCase("4"));
//        TimeFilter timeFilter =new TimeFilter();
//        System.out.println(timeFilter.isWeekDayNumber(4));


//--------------------------------------------------------
        while (true) {
            Copier copier = new Copier();
            FTP ftp1 = new FTP("185.36.170.87", "velcom", "velcom2016!", "cellfile/");
            FTP ftp2 = new FTP("77.74.36.238", "tems", "mkSmet_k6", "For Dima lost/Drogichin");
            LocalFolder localFolder1 = new LocalFolder("\\\\srv-logsblr-002\\d$\\Optima_server\\Comparative_DriveTests\\Quarterly\\2019\\Q2_2019\\");
            LocalFolder localFolder2 = new LocalFolder("i:\\Download\\2222");
            copier.copyFld(localFolder1, ftp2);
        }

    }
}
