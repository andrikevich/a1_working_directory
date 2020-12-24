package a1.by.andrikevich.MainApp;

import by.a1.andrikevich.JDBC.ConnectorToAtoll;
import by.a1.andrikevich.util.FtpUtil;
import by.a1.andrikevich.util.LocalFileCreator;

public class Starter {

    public static void main(String[] args) {
        //connect to Atoll DB and making SQL query
        ConnectorToAtoll conToAtll = new ConnectorToAtoll();

        // Save data from SQL query to localFile
        LocalFileCreator localFile = new LocalFileCreator();
        localFile.createLocalFile(conToAtll.getResultFromDB());

        //Send saved file from local disk to FTP
        FtpUtil ftpUtil = new FtpUtil();
        ftpUtil.saveFileToFtp();
        }


}
