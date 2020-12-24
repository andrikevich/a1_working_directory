package by.dazer;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class CellTems_NbIoT {

    private String lteCellName, eAFRCN, lat, lon, pCI, pCIG, mCC, mNC, tAC, cI, antDirect, antBeam, antHeight, antTilt,
                    antType,site, sssId;




    public String getSssId() {
        return sssId;
    }

    public void setSssId(String sssId) {
        this.sssId = sssId;
    }

    public CellTems_NbIoT(String lteCellName, String eAFRCN, String lat, String lon, String pCI, String pCIG, String mCC, String mNC, String tAC, String cI, String antDirect, String antBeam, String antHeight, String antTilt, String antType, String site, String sssId) throws SQLException {
        this.lteCellName = lteCellName;
        this.eAFRCN = eAFRCN;
        this.lat = lat;
        this.lon = lon;
        this.pCI = pCI;
        this.pCIG = pCIG;
        this.mCC = mCC;
        this.mNC = mNC;
        this.tAC = tAC;
        this.cI = cI;
        this.antDirect = antDirect;
        this.antBeam = antBeam;
        this.antHeight = antHeight;
        this.antTilt = antTilt;
        this.antType = antType;
        this.site = site;
        this.sssId = sssId;
    }

    public String getLteCellName() {
        return lteCellName;
    }

    public void setLteCellName(String lteCellName) {
        this.lteCellName = lteCellName;
    }

    public String geteAFRCN() {
        return eAFRCN;
    }

    public void seteAFRCN(String eAFRCN) {
        this.eAFRCN = eAFRCN;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getpCI() {
        return pCI;
    }

    public void setpCI(String pCI) {
        this.pCI = pCI;
    }

    public String getpCIG() {
        return pCIG;
    }

    public void setpCIG(String pCIG) {
        this.pCIG = pCIG;
    }

    public String getmCC() {
        return mCC;
    }

    public void setmCC(String mCC) {
        this.mCC = mCC;
    }

    public String getmNC() {
        return mNC;
    }

    public void setmNC(String mNC) {
        this.mNC = mNC;
    }

    public String gettAC() {
        return tAC;
    }

    public void settAC(String tAC) {
        this.tAC = tAC;
    }

    public String getcI() {
        return cI;
    }

    public void setcI(String cI) {
        this.cI = cI;
    }

    public String getAntDirect() {
        return antDirect;
    }

    public void setAntDirect(String antDirect) {
        this.antDirect = antDirect;
    }

    public String getAntBeam() {
        return antBeam;
    }

    public void setAntBeam(String antBeam) {
        this.antBeam = antBeam;
    }

    public String getAntHeight() {
        return antHeight;
    }

    public void setAntHeight(String antHeight) {
        this.antHeight = antHeight;
    }

    public String getAntTilt() {
        return antTilt;
    }

    public void setAntTilt(String antTilt) {
        this.antTilt = antTilt;
    }

    public String getAntType() {
        return antType;
    }

    public void setAntType(String antType) {
        this.antType = antType;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }


    public static  void temsCellFileNbIoTXml(List<CellTems_NbIoT> temsCellFile, String outPath) {
        try (FileWriter fW = new FileWriter(outPath,false)) {
            StringBuilder sBNbIoT = new StringBuilder();
            StringBuilder sBResultNbIoT = new StringBuilder();

            String startXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!-- Sample XML file: TEMS XML Cell Export File Format v. 1.2 -->\n" +
                    "<TEMS_CELL_EXPORT xmlns:dataType=\"http://www.ascom.com/networktesting/dataTypes\" xmlns:xsi= \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"TEMSCell.xsd\" VERSION=\"1.2\">\n" +
                    "   <GENERATED_DATE>2018-05-07</GENERATED_DATE>";



            sBNbIoT.append("<LTE VERSION=\"1.0\">\n" +
                    "    <CELL_LIST NET_OPERATOR=\"velcom\">\n");

            for (CellTems_NbIoT lst : temsCellFile){


                //NbIoT part

                    sBNbIoT.append("      <LTE_CELL>\n" +
                            "        <CELLNAME SYSTEM_TYPE=\"LTE\">").append(lst.getLteCellName()).append("</CELLNAME>\n" +
                            "\n" +
                                    "        <ENODE_B>").append(lst.getSite()).append("</ENODE_B>\n" +
                            "            <EARFCN_DL>").append(lst.geteAFRCN()).append("</EARFCN_DL>\n"+
                    "            <POSITION>\n" +
                            "               <GEODETIC_DATUM>WGS84</GEODETIC_DATUM>\n" +
                            "               <LATITUDE>").append(lst.getLat()).append("</LATITUDE>\n" +
                            "               <LONGITUDE>").append(lst.getLon()).append("</LONGITUDE>\n" +
                            "            </POSITION>\n" +
                            "            <PCI>").append(lst.getpCI()).append("</PCI>\n" +
                            "            <PCIG>").append(lst.getpCIG()).append("</PCIG>\n" +
                            "            <PHYSICAL_LAYER_CELL_ID>").append(lst.getpCIG()).append("</PHYSICAL_LAYER_CELL_ID>\n" +
                            "            <LTE_CGI>\n" +
                            "               <MCC>").append(lst.getmCC()).append("</MCC>\n" +
                            "               <MNC>").append(lst.getmNC()).append("</MNC>\n" +
                            "               <TAC>").append(lst.gettAC()).append("</TAC>\n" +
                            "               <CI>").append(lst.getpCIG()).append("</CI>\n" +
                            "            </LTE_CGI>       \n" +
                            "             <ANTENNA>\n" +
                            "               <DIRECTION>").append(lst.getAntDirect()).append("</DIRECTION>\n" +
                            "               <BEAM_WIDTH>").append(lst.getAntBeam()).append("</BEAM_WIDTH>\n" +
                            "               <HEIGHT>").append(lst.getAntHeight()).append("</HEIGHT>\n" +
                            "               <MECHANICAL_TILT>").append(lst.getAntTilt()).append("</MECHANICAL_TILT>\n" +
                            "               <TYPE>").append(lst.getAntType()).append("</TYPE>\n" +
                            "            </ANTENNA>\n"+
                            "      </LTE_CELL>\n");






            }
            sBResultNbIoT.append(startXml).append(sBNbIoT).append("    </CELL_LIST>\n</LTE>\n").append("</TEMS_CELL_EXPORT>");
            fW.append(sBResultNbIoT);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
