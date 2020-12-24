package by.dazer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CellTems_LTE {
    private String cellName, siteName, channel, pCI,ECI,tAC,azimut, lat, lon, power, beamWidth, height;


    public CellTems_LTE(String cellName, String channel, String lat, String lon, String power, String siteName,  String pCI, String ECI, String tAC, String azimut,String beamWidth, String height) {
        this.cellName = cellName;
        this.siteName = siteName;
        this.channel = channel;
        this.pCI = pCI;
        this.ECI = ECI;
        this.tAC = tAC;
        this.azimut = azimut;
        this.lat = lat;
        this.lon = lon;
        this.power = power;
        this.beamWidth = beamWidth;
        this.height = height;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getpCI() {
        return pCI;
    }

    public void setpCI(String pCI) {
        this.pCI = pCI;
    }

    public String getECI() {
        return ECI;
    }

    public void setECI(String ECI) {
        this.ECI = ECI;
    }

    public String gettAC() {
        return tAC;
    }

    public void settAC(String tAC) {
        this.tAC = tAC;
    }

    public String getAzimut() {
        return azimut;
    }

    public void setAzimut(String azimut) {
        this.azimut = azimut;
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

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getBeamWidth() {
        return beamWidth;
    }

    public void setBeamWidth(String beamWidth) {
        this.beamWidth = beamWidth;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


    public static  void temsCellFileLteBeCloudXml(List<CellTems_LTE> temsCellFile, String outPath) {
        try (FileWriter fW = new FileWriter(outPath,false)) {
            StringBuilder sbLteBecloud = new StringBuilder();
            StringBuilder sBResultLteBecloud = new StringBuilder();

            String startXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!-- Sample XML file: TEMS XML Cell Export File Format v. 1.2 -->\n" +
                    "<TEMS_CELL_EXPORT xmlns:dataType=\"http://www.ascom.com/networktesting/dataTypes\" xmlns:xsi= \"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"TEMSCell.xsd\" VERSION=\"1.2\">\n" +
                    "   <GENERATED_DATE>2018-10-16</GENERATED_DATE>";



            sbLteBecloud.append("<LTE VERSION=\"1.0\">\n" +
                    "    <CELL_LIST NET_OPERATOR=\"velcom\">\n");

            for (CellTems_LTE lst : temsCellFile){


                //LTE beCloud part

                sbLteBecloud.append("      <LTE_CELL>\n" +
                        "        <CELLNAME SYSTEM_TYPE=\"LTE\">").append(lst.getCellName()).append("</CELLNAME>\n" +
                        "\n" +
                        "        <ENODE_B>").append(lst.getSiteName()).append("</ENODE_B>\n" +
                        "            <EARFCN_DL>").append(lst.getChannel().substring(lst.getChannel().length()-5,lst.getChannel().length()-1)).append("</EARFCN_DL>\n"+
                        "            <POSITION>\n" +
                        "               <GEODETIC_DATUM>WGS84</GEODETIC_DATUM>\n" +
                        "               <LATITUDE>").append(lst.getLat()).append("</LATITUDE>\n" +
                        "               <LONGITUDE>").append(lst.getLon()).append("</LONGITUDE>\n" +
                        "            </POSITION>\n" +
                        "            <PCI>").append(lst.getpCI()).append("</PCI>\n" +
                        "            <PCIG>").append("0").append("</PCIG>\n" +
                        //"            <PHYSICAL_LAYER_CELL_ID>").append(lst.getpCIG()).append("</PHYSICAL_LAYER_CELL_ID>\n" +
                        "            <LTE_CGI>\n" +
                        "               <MCC>").append("257").append("</MCC>\n" +
                        "               <MNC>").append("01").append("</MNC>\n" +
                        "               <TAC>").append(lst.gettAC()).append("</TAC>\n" +
                        "               <CI>").append(lst.getECI()).append("</CI>\n" +
                        "            </LTE_CGI>       \n" +
                        "             <ANTENNA>\n" +
                        "               <DIRECTION>").append(lst.getAzimut()).append("</DIRECTION>\n" +
                        "               <BEAM_WIDTH>").append(lst.getBeamWidth()).append("</BEAM_WIDTH>\n" +
                        "               <HEIGHT>").append(lst.getHeight()).append("</HEIGHT>\n" +
//                        "               <TYPE>").append(lst.getAntType()).append("</TYPE>\n" +
                        "            </ANTENNA>\n"+
                        "      </LTE_CELL>\n");






            }
            sBResultLteBecloud.append(startXml).append(sbLteBecloud).append("    </CELL_LIST>\n</LTE>\n").append("</TEMS_CELL_EXPORT>");
            fW.append(sBResultLteBecloud);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
