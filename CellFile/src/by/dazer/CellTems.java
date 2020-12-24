package by.dazer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CellTems {
    private String cell, nodeB, lat, lon, mCC, mNC, lAC, cI, rAC, antDir, antBeamWidth,antType, antHeight, antTilt,
            cellType, aFRCN, bSIC, uAFRCN, sC, freqBand, rncId, cId, cpichPower, netwCellId;


    public CellTems(String cell,String nodeB,String  lat,String  lon,String  mCC,String  mNC,String  lAC,String  cI,String rAC,
                    String antDir, String antBeamWidth,String antType,String  antHeight, String  antTilt,
                    String cellType,String  aFRCN,String  bSIC,String  uAFRCN, String sC, String freqBand,String  rncId,
                    String cId,String  cpichPower,String  netwCellId) {
        this.cell = cell;
        this.nodeB = nodeB;
        this.lat = lat;
        this.lon = lon;
        this.mCC = mCC;
        this.mNC = mNC;
        this.lAC = lAC;
        this.cI = cI;
        this.rAC = rAC;
        this.antDir = antDir;
        this.antBeamWidth = antBeamWidth;
        this.antType = antType;
        this.antHeight = antHeight;
        this.antTilt = antTilt;
        this.cellType = cellType;
        this.aFRCN = aFRCN;
        this.bSIC = bSIC;
        this.uAFRCN = uAFRCN;
        this.sC = sC;
        this.freqBand = freqBand;
        this.rncId = rncId;
        this.cId = cId;
        this.cpichPower = cpichPower;
        this.netwCellId = netwCellId;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNodeB() {
        return nodeB;
    }

    public void setNodeB(String nodeB) {
        this.nodeB = nodeB;
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

    public String getlAC() {
        return lAC;
    }

    public void setlAC(String lAC) {
        this.lAC = lAC;
    }

    public String getcI() {
        return cI;
    }

    public void setcI(String cI) {
        this.cI = cI;
    }

    public String getrAC() {
        return rAC;
    }

    public void setrAC(String rAC) {
        this.rAC = rAC;
    }

    public String getAntDir() {
        return antDir;
    }

    public void setAntDir(String antDir) {
        this.antDir = antDir;
    }

    public String getAntBeamWidth() {
        return antBeamWidth;
    }

    public void setAntBeamWidth(String antBeamWidth) {
        this.antBeamWidth = antBeamWidth;
    }

    public String getAntType() {
        return antType;
    }

    public void setAntType(String antType) {
        this.antType = antType;
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

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public String getaFRCN() {
        return aFRCN;
    }

    public void setaFRCN(String aFRCN) {
        this.aFRCN = aFRCN;
    }

    public String getbSIC() {
        return bSIC;
    }

    public void setbSIC(String bSIC) {
        this.bSIC = bSIC;
    }

    public String getuAFRCN() {
        return uAFRCN;
    }

    public void setuAFRCN(String uAFRCN) {
        this.uAFRCN = uAFRCN;
    }

    public String getsC() {
        return sC;
    }

    public void setsC(String sC) {
        this.sC = sC;
    }

    public String getFreqBand() {
        return freqBand;
    }

    public void setFreqBand(String freqBand) {
        this.freqBand = freqBand;
    }

    public String getRncId() {
        return rncId;
    }

    public void setRncId(String rncId) {
        this.rncId = rncId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getCpichPower() {
        return cpichPower;
    }

    public void setCpichPower(String cpichPower) {
        this.cpichPower = cpichPower;
    }

    public String getNetwCellId() {
        return netwCellId;
    }

    public void setNetwCellId(String netwCellId) {
        this.netwCellId = netwCellId;
    }


    public static void temsCellFile(List<CellTems> temsCellFile,  String outPath) {
        try (FileWriter fr = new FileWriter(outPath,false)) {
            StringBuilder sB = new StringBuilder();
            sB.append("55 TEMS_-_Cell_names\n").append("CELL\tNODE_B\tLAT\tLON\tMCC\tMNC\tLAC\tCI\tRAC\tANT_DIRECTION\t" +
                    "ANT_BEAM_WIDTH\tANT_TYPE\tANT_HEIGHT\tANT_TILT\tCELL_TYPE\tARFCN\tBSIC\tUARFCN\tSC\tFreqBand" +
                    "\tRNC-ID\tC-ID\tCPICH_POWER\tNETWORK_CELLID\n");

            for (CellTems lst : temsCellFile){
                sB.append(lst.getCell()).append("\t").append(lst.getNodeB()).append("\t").append(lst.getLat()).append("\t").append(lst.getLon()).append("\t")
                   .append(lst.getmCC()).append("\t").append(lst.getmNC()).append("\t").append(lst.getlAC()).append("\t").append(lst.getcI()).append("\t")
                   .append(lst.getrAC()).append("\t").append(lst.getAntDir()).append("\t").append(lst.getAntBeamWidth()).append("\t").append(lst.getAntType())
                   .append("\t").append(lst.getAntHeight()).append("\t").append(lst.getAntTilt()).append("\t").append(lst.getCellType()).append("\t")
                   .append(lst.getaFRCN()).append("\t").append(lst.getbSIC()).append("\t").append(lst.getuAFRCN()).append("\t")
                   .append(lst.getsC()).append("\t").append(lst.getFreqBand()).append("\t").append(lst.getRncId()).append("\t").append(lst.getcId())
                   .append("\t").append(lst.getCpichPower()).append("\t").append(lst.getNetwCellId()).append("\n");

            }
            fr.append(sB);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
