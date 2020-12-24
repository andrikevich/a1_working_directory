package by.dazer;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CellTemsXml {


    InitialData inData = new InitialData();
    //connection to IOSS
    Connection conOss = DriverManager.getConnection(inData.getiOssIpConnection(), inData.getiOssLogin(), inData.getiOssPwd());
    Statement statementOss = conOss.createStatement();




    public CellTemsXml() throws SQLException {
    }


    public  void temsCellFileXml(List<CellTems> temsCellFile, String outPathG,String outPathW_neigh, String outPathW ) {
        try (FileWriter fRG = new FileWriter(outPathG,false);
        FileWriter fRW_neigh = new FileWriter(outPathW_neigh,false);
             FileWriter fRW = new FileWriter(outPathW,false)) {
            StringBuilder sBGsm = new StringBuilder();
            StringBuilder sBWCDMA = new StringBuilder();
            StringBuilder sBResultG = new StringBuilder();
            StringBuilder sBResultW_neigh = new StringBuilder();

            StringBuilder temp3GXmlwoNeigh = new StringBuilder();
            StringBuilder sBResultW = new StringBuilder();

            String startXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!-- Sample XML file: TEMS XML Cell Export File Format v. 1.2 -->\n" +
                    "<TEMS_CELL_EXPORT xmlns:dataType=\"http://www.ascom.com/networktesting/dataTypes\" xmlns:xsi= \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"TEMSCell.xsd\" VERSION=\"1.2\">\n" +
                    "   <GENERATED_DATE>2018-05-07</GENERATED_DATE>";



            sBGsm.append("  <GSM VERSION=\"1.1\">\n" +
                    "    <CELL_LIST NET_OPERATOR=\"velcom\">\n");

            sBWCDMA.append("<WCDMA VERSION=\"1.1\">\n" +
                    "    <CELL_LIST NET_OPERATOR=\"velcom\">\n");

            temp3GXmlwoNeigh.append("<WCDMA VERSION=\"1.1\">\n" +
                    "    <CELL_LIST NET_OPERATOR=\"velcom\">\n");

            for (CellTems lst : temsCellFile){


                    //GSM part
                if(lst.getuAFRCN() ==""){
                  sBGsm.append("      <GSM_CELL>\n" +
                          "        <CELLNAME SYSTEM_TYPE=\"GSM\">").append(lst.getCell()).append("</CELLNAME>\n" +
                          "        <CELL_TYPE>").append(lst.getCellType()).append("</CELL_TYPE>\n" +
//                          "        <LOCALCELLID>").append(lst.getNetwCellId()).append("</LOCALCELLID>\n").append(

                                  "        <SITE>")
                          .append(lst.getNodeB()).append("</SITE>\n").append(
                          "        <RAC>").append(lst.getrAC()).append("</RAC>\n" +
                          "        <BSIC>\n" +
                          "          <NCC>").append(lst.getbSIC().substring(0,1)).append("</NCC>\n" +
                          "          <BCC>").append(lst.getbSIC().substring(1)).append("</BCC>\n" +
                          "        </BSIC>\n" +
                          "        <CHANNEL_INFO>\n" +
                          "          <BCCH>\n" +
                          "            <ARFCN>").append(lst.getaFRCN()).append("</ARFCN>\n" +
                          "            <BAND>GSM ").append(lst.getFreqBand()).append("</BAND>\n" +
                          "          </BCCH>\n" +
                          "        </CHANNEL_INFO>\n" +
                          "        <POSITION>\n" +
                          "          <GEODETIC_DATUM>WGS84</GEODETIC_DATUM>\n" +
                          "          <LATITUDE>").append(lst.getLat()).append("</LATITUDE>\n" +
                          "          <LONGITUDE>").append(lst.getLon()).append("</LONGITUDE>\n" +
                          "        </POSITION>\n" +
                          "        <CGI>\n" +
                          "          <MCC>").append(lst.getmCC()).append("</MCC>\n" +
                          "          <MNC>").append(lst.getmNC()).append("</MNC>\n" +
                          "          <LAC>").append(lst.getlAC()).append("</LAC>\n" +
                          "          <CI>").append(lst.getcI()).append("</CI>\n" +
                          "        </CGI>\n" +
                          "        <ANTENNA>\n" +
                          "          <DIRECTION>").append(lst.getAntDir()).append("</DIRECTION>\n" +
                          "          <BEAM_WIDTH>").append(lst.getAntBeamWidth()).append("</BEAM_WIDTH>\n" +
                          "          <HEIGHT>").append(lst.getAntHeight()).append("</HEIGHT>\n" +
                          "          <MECHANICAL_TILT>").append(lst.getAntTilt()).append("</MECHANICAL_TILT>\n" +
                          "          <TYPE>").append(lst.getAntType()).append("</TYPE>\n" +
                          "        </ANTENNA>\n" +
                          "      </GSM_CELL>\n");

                }
                    //WCDMA part
                if(lst.getuAFRCN() !=""){
                    ResultSet rsOss = statementOss.executeQuery("SELECT USERLABEL FROM IOSS_CM.ZTE3G_UUTRANRELATION where DS8_CELL_NAME = '"+lst.getCell()+ "'");

                    //list for WCDMA Neighbours
                    List <String> neighWcdmaList = new ArrayList<>();

                    while (rsOss.next()){
                        neighWcdmaList.add(rsOss.getString(1));
                    }

// part for cell file with neighbours
                    sBWCDMA.append("      <WCDMA_CELL>\n" +
                            "        <CELLNAME SYSTEM_TYPE=\"WCDMA\">").append(lst.getCell()).append("</CELLNAME>\n" +
                            "        <CELL_TYPE>").append(lst.getCellType()).append("</CELL_TYPE>\n" +
                                  "        <NODE_B>")
                            .append(lst.getNodeB()).append("</NODE_B>\n"+"        <SC>").append(lst.getsC()).append("</SC>\n" +
                            "        <RAC>").append(lst.getrAC()).append("</RAC>\n" +
                            "        <UARFCN_DL>").append(lst.getuAFRCN()).append("</UARFCN_DL>\n" +
                            "        <RNC_ID>").append(lst.getRncId()).append("</RNC_ID>\n" +
                            "        <CPICH_POWER>").append(lst.getCpichPower()).append("</CPICH_POWER>\n" +
                            "        <POSITION>\n" +
                            "          <GEODETIC_DATUM>WGS84</GEODETIC_DATUM>\n" +
                            "          <LATITUDE>").append(lst.getLat()).append("</LATITUDE>\n" +
                            "          <LONGITUDE>").append(lst.getLon()).append("</LONGITUDE>\n" +
                            "        </POSITION>\n" +
                            "        <CGI>\n" +
                            "          <MCC>").append(lst.getmCC()).append("</MCC>\n" +
                            "          <MNC>").append(lst.getmNC()).append("</MNC>\n" +
                            "          <LAC>").append(lst.getlAC()).append("</LAC>\n" +
                            "          <CI>").append(lst.getcI()).append("</CI>\n" +
                            "        </CGI>\n" +
                            "        <ANTENNA>\n" +
                            "          <DIRECTION>").append(lst.getAntDir()).append("</DIRECTION>\n" +
                            "          <BEAM_WIDTH>").append(lst.getAntBeamWidth()).append("</BEAM_WIDTH>\n" +
                            "          <HEIGHT>").append(lst.getAntHeight()).append("</HEIGHT>\n" +
                            "          <MECHANICAL_TILT>").append(lst.getAntTilt()).append("</MECHANICAL_TILT>\n" +
                            "          <TYPE>").append(lst.getAntType()).append("</TYPE>\n" +
                            "        </ANTENNA>\n");

                    //simple WCDMA cell file without neighbours

                    temp3GXmlwoNeigh.append("      <WCDMA_CELL>\n" +
                            "        <CELLNAME SYSTEM_TYPE=\"WCDMA\">").append(lst.getCell()).append("</CELLNAME>\n" +
                            "        <CELL_TYPE>").append(lst.getCellType()).append("</CELL_TYPE>\n" +
                            "        <NODE_B>")
                            .append(lst.getNodeB()).append("</NODE_B>\n"+"        <SC>").append(lst.getsC()).append("</SC>\n" +
                            "        <RAC>").append(lst.getrAC()).append("</RAC>\n" +
                            "        <UARFCN_DL>").append(lst.getuAFRCN()).append("</UARFCN_DL>\n" +
                            "        <RNC_ID>").append(lst.getRncId()).append("</RNC_ID>\n" +
                            "        <CPICH_POWER>").append(lst.getCpichPower()).append("</CPICH_POWER>\n" +
                            "        <POSITION>\n" +
                            "          <GEODETIC_DATUM>WGS84</GEODETIC_DATUM>\n" +
                            "          <LATITUDE>").append(lst.getLat()).append("</LATITUDE>\n" +
                            "          <LONGITUDE>").append(lst.getLon()).append("</LONGITUDE>\n" +
                            "        </POSITION>\n" +
                            "        <CGI>\n" +
                            "          <MCC>").append(lst.getmCC()).append("</MCC>\n" +
                            "          <MNC>").append(lst.getmNC()).append("</MNC>\n" +
                            "          <LAC>").append(lst.getlAC()).append("</LAC>\n" +
                            "          <CI>").append(lst.getcI()).append("</CI>\n" +
                            "        </CGI>\n" +
                            "        <ANTENNA>\n" +
                            "          <DIRECTION>").append(lst.getAntDir()).append("</DIRECTION>\n" +
                            "          <BEAM_WIDTH>").append(lst.getAntBeamWidth()).append("</BEAM_WIDTH>\n" +
                            "          <HEIGHT>").append(lst.getAntHeight()).append("</HEIGHT>\n" +
                            "          <MECHANICAL_TILT>").append(lst.getAntTilt()).append("</MECHANICAL_TILT>\n" +
                            "          <TYPE>").append(lst.getAntType()).append("</TYPE>\n" +
                            "        </ANTENNA>\n      </WCDMA_CELL>\n");;

                    sBWCDMA.append(       "        <NEIGHBOR_LIST>\n");
                    for(String neigh : neighWcdmaList){
                        sBWCDMA.append("               <CELLNAME SYSTEM_TYPE=\"WCDMA\">"+neigh+"</CELLNAME>\n");
                    }
                    sBWCDMA.append("        </NEIGHBOR_LIST>\n"+"      </WCDMA_CELL>\n");
                }



            }
            sBResultG.append(startXml).append(sBGsm).append("    </CELL_LIST>\n</GSM>\n").append("</TEMS_CELL_EXPORT>");
            sBResultW_neigh.append(startXml).append(sBWCDMA).append("     </CELL_LIST>\n</WCDMA>").append("</TEMS_CELL_EXPORT>");
            sBResultW.append(startXml).append(temp3GXmlwoNeigh).append("     </CELL_LIST>\n</WCDMA>").append("</TEMS_CELL_EXPORT>");
            fRG.append(sBResultG);
            fRW_neigh.append(sBResultW_neigh);
            fRW.append(sBResultW);


            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
