package by.dazer;

import by.dazer.FTP.FTP;
import by.dazer.Filter.TimeFilter;
import by.dazer.LocalFolders.LocalFolder;
import by.dazer.General.Copier;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class StarterSql {

    //copy Backup files
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        ZipOutputStream os = null;
        if (source.isFile()) {
            try {
                is = new FileInputStream(source);
                os = new ZipOutputStream(new FileOutputStream(dest));
                byte[] buffer = new byte[1024];
                int length;
                ZipEntry ze = new ZipEntry(source.getName());
                os.putNextEntry(ze);

                while ((length = is.read(buffer)) > 0) {

                    os.write(buffer, 0, length);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                is.close();
                os.close();
            }
        }
    }

    public static void main(String[] args) {

        InitialData inData = new InitialData();

        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
        String tempCurDate = String.valueOf(fd.format(calendar.getTime()));


        // Backup copy for previous cell file, with date when copy was
        try {
            copyFileUsingStream(new File(inData.getSourceNemoCellWCDMA()), new File(inData.getSourceArchive() + tempCurDate + "_NemoCells_WCDMA" + ".zip"));
            copyFileUsingStream(new File(inData.getSourceNemoCellGSM()), new File(inData.getSourceArchive() + tempCurDate + "_NemoCells_GSM" + ".zip"));
            copyFileUsingStream(new File(inData.getSourceActixCell()), new File(inData.getSourceArchive() + tempCurDate + "_Actix_cellref" + ".zip"));
            copyFileUsingStream(new File(inData.getSourceInfoVistaCel()), new File(inData.getSourceArchive() + tempCurDate + "_InfoVistaCel" + ".zip"));
            copyFileUsingStream(new File(inData.getSourceInfoVistaXmlG()), new File(inData.getSourceArchive() + tempCurDate + "_InfoVistaXmlG" + ".zip"));
            copyFileUsingStream(new File(inData.getSourceInfoVistaXmlW_neigh()), new File(inData.getSourceArchive() + tempCurDate + "_InfoVistaXmlW_neigh" + ".zip"));
            copyFileUsingStream(new File(inData.getSourceInfoVistaXmlW()), new File(inData.getSourceArchive() + tempCurDate + "_InfoVistaXmlW" + ".zip"));

            copyFileUsingStream(new File(inData.getSourceTemsNbIoT()), new File(inData.getSourceArchive() + tempCurDate + "_INbIoT" + ".zip"));
            copyFileUsingStream(new File(inData.getSourceTemsLteBeCloud()), new File(inData.getSourceArchive() + tempCurDate + "_LTEbeCloud" + ".zip"));


        } catch (IOException e) {
            e.printStackTrace();

           }


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //connection to Oracle Database
            //Changing for timezone was requirement for Atoll in new version of Oracle 12c
            TimeZone timeZone = TimeZone.getTimeZone("Europe/Minsk");
            TimeZone.setDefault(timeZone);

            Connection con = DriverManager.getConnection(inData.getAtollIpConnection(), inData.getAtollLogin(), inData.getAtollPwd());
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(inData.getAtollSqlQueryCommonCell());


            List<CellTems> temsCellFileList = new ArrayList<CellTems>();


            List<CellNemo> nemoCellFileWcdma = new ArrayList<CellNemo>();
            List<CellNemo> nemoCellFileGsm = new ArrayList<CellNemo>();

            List<CellActix> actixCellFileWcdmaList = new ArrayList<CellActix>();
            List<CellActix> actixCellFileGsmList = new ArrayList<CellActix>();
            Set<SiteActix> actixSiteSetWcdma = new HashSet<SiteActix>();
            Set<SiteActix> actixSiteSetGsm = new HashSet<SiteActix>();


            while (rs.next()) {
                String[] tempArr = new String[24];
                for (int i = 0; i < 24; i++) {
                    String temsTempStr = rs.getString(i + 1);
                    tempArr[i] = (temsTempStr == null) ? "" : temsTempStr;
                }
                //for TEMS cell files
                temsCellFileList.add(new CellTems(tempArr[0], tempArr[1], tempArr[2], tempArr[3], tempArr[4], tempArr[5],
                        tempArr[6], tempArr[7], tempArr[8], tempArr[9], tempArr[10], tempArr[11], tempArr[12], tempArr[13],
                        tempArr[14], tempArr[15], tempArr[16], tempArr[17], tempArr[18], tempArr[19], tempArr[20], tempArr[21],
                        tempArr[22], tempArr[23]));


                // For WCDMA part of network
                if (tempArr[17] != "") { //checking cell belongs to WCDMA Network, UAFRCN is not null

                    //Adding to Nemo Cell List WCDMA
                    nemoCellFileWcdma.add(new CellNemo(tempArr[0], tempArr[1], tempArr[2], tempArr[3], tempArr[12],
                            tempArr[13], tempArr[10], tempArr[9], tempArr[17], tempArr[6], tempArr[8], tempArr[18], tempArr[21], tempArr[20]));

                    //Adding to Actix Site Set
                    actixSiteSetWcdma.add(new SiteActix(tempArr[1], tempArr[2], tempArr[3]));

                    //Adding to Actix Cell WCDMA
                    actixCellFileWcdmaList.add(new CellActix(tempArr[1], tempArr[0], tempArr[9], tempArr[10], tempArr[18],
                            tempArr[4], tempArr[5], tempArr[6], tempArr[7], tempArr[17], tempArr[12], tempArr[13]));
                }

                // For GSM
                if (tempArr[17] == "") {

                    //Adding to Nemo Cell List GSM
                    nemoCellFileGsm.add(new CellNemo(tempArr[0], tempArr[1], tempArr[2], tempArr[3], tempArr[12],
                            tempArr[13], tempArr[10], tempArr[9], tempArr[15], tempArr[21], tempArr[6], tempArr[8], tempArr[16]));

                    //Adding to Actix Site Set GSM
                    actixSiteSetGsm.add(new SiteActix(tempArr[1], tempArr[2], tempArr[3]));

                    //Adding to Actix GSM part
                    actixCellFileGsmList.add(new CellActix(tempArr[1], tempArr[0], tempArr[9], tempArr[10], tempArr[16],
                            tempArr[4], tempArr[5], tempArr[6], tempArr[7], tempArr[15], tempArr[12], tempArr[13], tempArr[23]));

                }
            }


            by.dazer.CellNemo.nemoCellFileWcdma(nemoCellFileWcdma, inData.getSourceNemoCellWCDMA());
            by.dazer.CellNemo.nemoCellFileGsm(nemoCellFileGsm, inData.getSourceNemoCellGSM());
            by.dazer.CellActix.actixCellFileWcdma(actixCellFileWcdmaList, actixSiteSetWcdma, actixCellFileGsmList, actixSiteSetGsm, inData.getSourceActixCell());
            by.dazer.CellTems.temsCellFile(temsCellFileList, inData.getSourceInfoVistaCel());

 //           by.dazer.CellTemsXml cellXml = new by.dazer.CellTemsXml();
//            cellXml.temsCellFileXml(temsCellFileList, inData.getSourceInfoVistaXmlG(), inData.getSourceInfoVistaXmlW_neigh(),inData.getSourceInfoVistaXmlW());

            by.dazer.CellTemsXmlDbInMemory cellXml = new by.dazer.CellTemsXmlDbInMemory();
            cellXml.temsCellFileXml(temsCellFileList, inData.getSourceInfoVistaXmlG(), inData.getSourceInfoVistaXmlW_neigh(),inData.getSourceInfoVistaXmlW());


            //BackUp *.cel to FTP, ex. for N-HUB using every Monday
            TimeFilter timeFilter = new TimeFilter();
            if (timeFilter.isWeekDayNumber(1)) {
                FTP ftp = new FTP("185.36.170.87", "velcom", "velcom2016!", "cellfile/");
                LocalFolder folder = new LocalFolder(InitialData.startOfDestRoute + "Discovery\\Scr\\CellFile.cel");
                Copier copier = new Copier();
                copier.copyFld(folder, ftp);
            }

            //NbIoT cell file

            //connection to Atoll joined with IOSS
            Connection conAtol = DriverManager.getConnection(inData.getAtollIpConnection(), inData.getAtollLogin(), inData.getAtollPwd());
            Statement statementToAtoll = conAtol.createStatement();

            //NbIoT
                    ResultSet rsNbIoT = statementToAtoll.executeQuery(inData.getAtollSqlQueryNbiotCell());
                    List<CellTems_LTE> temsNbIoT = new ArrayList<CellTems_LTE>();
                    int numberOfFildsInQuery = 12;

                    try {
                        while (rsNbIoT.next()) {
                            String[] tempArr = new String[numberOfFildsInQuery];
                            for (int i = 0; i < numberOfFildsInQuery; i++) {
                                String temsTempStr = rsNbIoT.getString(i + 1);
                                tempArr[i] = (temsTempStr == null) ? "" : temsTempStr;
                            }

                            temsNbIoT.add(new CellTems_LTE(tempArr[0],tempArr[1], tempArr[2], tempArr[3], tempArr[4], tempArr[5],
                                    tempArr[6], tempArr[7], tempArr[8], tempArr[9], tempArr[10], tempArr[11]));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    CellTems_LTE.temsCellFileLteBeCloudXml(temsNbIoT,inData.getSourceTemsNbIoT());



                    //LTE from beCloud

            ResultSet rsLTEBeCloud = statementToAtoll.executeQuery(inData.getAtollSqlQueryLteBeCloud());
            List <CellTems_LTE> lstTemsLteBecloud = new ArrayList<CellTems_LTE>();
                try {
                    while (rsLTEBeCloud.next()){
                      String [] tempLteBecloud  = new String [numberOfFildsInQuery];
                      for (int i =0; i < tempLteBecloud.length; i++){
                           tempLteBecloud [i] = (rsLTEBeCloud.getString(i+1) == null) ? "" : (rsLTEBeCloud.getString(i+1));
                        }
                        lstTemsLteBecloud.add(new CellTems_LTE(tempLteBecloud[0], tempLteBecloud[1], tempLteBecloud[2], tempLteBecloud[3], tempLteBecloud[4], tempLteBecloud[5], tempLteBecloud[6],                                                 tempLteBecloud[7], tempLteBecloud[8], tempLteBecloud[9], tempLteBecloud[10], tempLteBecloud[11]));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            CellTems_LTE.temsCellFileLteBeCloudXml(lstTemsLteBecloud,inData.getSourceTemsLteBeCloud());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
