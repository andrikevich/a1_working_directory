package by.dazer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CellNemo {
    private String cell, nodeB, lat, lon, mCC, mNC, lAC, cI, rAC, antDir, antBeamWidth,antType, antHeight, antTilt,
            cellType, aFRCN, bSIC, uAFRCN, sC, freqBand, rncId, cId, cpichPower, netwCellId;

// Constructor for WCDMA Cell
    public CellNemo(String cell, String nodeB, String lat, String lon, String antHeight, String antTilt, String antBeamWidth, String antDir,
                    String uAFRCN, String lAC, String rAC, String sC, String cId, String rncId) {
        this.cell = cell;
        this.nodeB = nodeB;
        this.lat = lat;
        this.lon = lon;
        this.lAC = lAC;
        this.rAC = rAC;
        this.antBeamWidth = antBeamWidth;
        this.antHeight = antHeight;
        this.antTilt = antTilt;
        this.uAFRCN = uAFRCN;
        this.sC = sC;
        this.cId = cId;
        this.antDir = antDir;
        this.rncId = rncId;
    }

    // Constructor for GSM
    public CellNemo(String cell, String nodeB, String lat, String lon, String antHeight, String antTilt, String antBeamWidth, String antDir,
                    String aFRCN, String cId, String lAC, String rAC, String bSIC ) {
        this.cell = cell;
        this.nodeB = nodeB;
        this.lat = lat;
        this.lon = lon;
        this.lAC = lAC;
        this.rAC = rAC;
        this.antBeamWidth = antBeamWidth;
        this.antHeight = antHeight;
        this.antTilt = antTilt;
        this.aFRCN = aFRCN;
        this.cId = cId;
        this.antDir = antDir;
        this.bSIC = bSIC;
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


        //write to file WCDMA part
    public static void nemoCellFileWcdma(List<CellNemo> nemoCellFileWcdma,  String outPath) {
        try (FileWriter fr = new FileWriter(outPath,false)) {
            StringBuilder sB = new StringBuilder();
            sB.append("SYSTEM;CELL;SITE;LAT;LON;HEIGHT;TILT;BEAM;DIR;CH;LAC;RAC;SCR;CID;RNCID\n");
            for (CellNemo lst : nemoCellFileWcdma){
                sB.append("UMTS").append(";").append(lst.getCell()).append(";").append(lst.getNodeB()).append(";").
                        append(lst.getLat()).append(";").append(lst.getLon()).append(";")
                        .append(lst.getAntHeight()).append(";").append(lst.getAntTilt()).append(";").
                        append(lst.getAntBeamWidth()).append(";").append(lst.getAntDir()).append(";").
                        append(lst.getuAFRCN()).append(";").append(lst.getlAC()).append(";").
                        append(lst.getrAC()).append(";").append(lst.getsC()).append(";").append(lst.getcId()).append(";").append(lst.getRncId()).append("\n");
            }
            fr.append(sB);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //write to file GSM part
    public static void nemoCellFileGsm(List<CellNemo> nemoCellFileGsm,  String outPath) {
        try (FileWriter fr = new FileWriter(outPath,false)) {
            StringBuilder sB = new StringBuilder();
            sB.append("SYSTEM;CELL;SITE;LAT;LON;HEIGHT;TILT;BEAM;DIR;CH;CID;LAC;RAC;BSIC\n");
            for (CellNemo lst : nemoCellFileGsm){
                sB.append("GSM").append(";").append(lst.getCell()).append(";").append(lst.getNodeB()).append(";").
                        append(lst.getLat()).append(";").append(lst.getLon()).append(";")
                        .append(lst.getAntHeight()).append(";").append(lst.getAntTilt()).append(";").
                        append(lst.getAntBeamWidth()).append(";").append(lst.getAntDir()).append(";").
                        append(lst.getaFRCN()).append(";").append(lst.getcId()).append(";").append(lst.getlAC()).append(";").
                        append(lst.getrAC()).append(";").append(lst.getbSIC()).append("\n");
            }
            fr.append(sB);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}