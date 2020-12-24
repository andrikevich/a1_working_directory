package by.dazer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class CellActix {

    private String cell, nodeB, lat, lon, mCC, mNC, lAC, cI, rAC, antDir, antBeamWidth,antType, antHeight, antTilt,
            cellType, aFRCN, bSIC, uAFRCN, sC, freqBand, rncId, cId, cpichPower, netwCellId;

    // For WCDMA part
    public CellActix( String nodeB, String cell, String antDir, String antBeamWidth, String sC, String mCC, String mNC, String lAC,
                      String cId, String uAFRCN, String antHeight, String antTilt) {
        this.cell = cell;
        this.nodeB = nodeB;
        this.lAC = lAC;
        this.antBeamWidth = antBeamWidth;
        this.antHeight = antHeight;
        this.antTilt = antTilt;
        this.uAFRCN = uAFRCN;
        this.sC = sC;
        this.cId = cId;
        this.antDir = antDir;
        this.mCC = mCC;
        this.mNC = mNC;
    }


    //for GSM part
    public CellActix( String nodeB, String cell, String antDir, String antBeamWidth, String bSIC, String mCC, String mNC, String lAC,
                      String cId, String aFRCN, String antHeight, String antTilt, String netwCellId) {
        this.cell = cell;
        this.nodeB = nodeB;
        this.lAC = lAC;
        this.antBeamWidth = antBeamWidth;
        this.antHeight = antHeight;
        this.antTilt = antTilt;
        this.aFRCN = aFRCN;
        this.bSIC = bSIC;
        this.cId = cId;
        this.antDir = antDir;
        this.mCC = mCC;
        this.mNC = mNC;
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

    public static void actixCellFileWcdma(List<CellActix> actixCellFileWcdma, Set<SiteActix> actixSiteWcdma, List<CellActix> actixCellFileGsm, Set<SiteActix> actixSiteGsm,  String outPath) {
        try (FileWriter fr = new FileWriter(outPath,false)) {
            StringBuilder sB = new StringBuilder();
            sB.append("; #NetworkData - datafile\n");
            //First part of table with nodeB WCDMA
            for (SiteActix lstSite : actixSiteWcdma){
                sB.append("WCDMA_Site").append("\t").append(lstSite.getNodeB()).append("\t").append(lstSite.getNodeB()).append("\t").
                        append(lstSite.getLat()).append("\t").append(lstSite.getLon()).append("\n");
            }

            //First part of table with nodeB GSM
            for (SiteActix lstSite : actixSiteGsm){
                sB.append("GSM_Site").append("\t").append(lstSite.getNodeB()).append("\t").append(lstSite.getNodeB()).append("\t").
                        append(lstSite.getLat()).append("\t").append(lstSite.getLon()).append("\n");
            }

            //second part of table with Cells WCDMA
            for (CellActix lst : actixCellFileWcdma){
                sB.append("WCDMA_Cell").append("\t").append(lst.getNodeB()).append("\t").append(lst.getCell()).append("\t").
                        append(lst.getAntDir()).append("\t").append(lst.getAntBeamWidth()).append("\t").append("\t").append(lst.getsC()).
                        append("\t").append(lst.getmCC()).append("\t").append(lst.getmNC()).append("\t").
                        append(lst.getlAC()).append("\t").append(lst.getcId()).append("\t").append("\t").append("{}").append("\t").
                        append(lst.getuAFRCN()).
                        append("\t").append("{}").append("\t").append(lst.getuAFRCN()).append("\t").append(lst.getAntHeight()).
                        append("\t").append(lst.getAntTilt()).append("\t").append("\n");

            }

            //second part of table with Cells GSM
            for (CellActix lst : actixCellFileGsm){
                sB.append("GSM_Cell").append("\t").append(lst.getNodeB()).append("\t").append(lst.getCell()).append("\t").
                        append(lst.getAntDir()).append("\t").append(lst.getAntBeamWidth()).append("\t").append("").append("\t").append(lst.getaFRCN())
                        .append("\t").append(lst.getmCC()).append("\t").append(lst.getmNC()).append("\t").
                        append(lst.getlAC()).append("\t").append(lst.getcId()).append("\t").append(lst.getbSIC()).append("\t").append("").
                        append("\t").append("").append("\t").append("").append("\t").append("").append("\t")
                        .append("").append("\t").append("\t").append(lst.getAntHeight()).
                        append("\t").append(lst.getAntTilt()).append("\t").append("").append("\t").append("").append("\t").append("\n");

            }

            fr.append(sB);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//    public static void actixCellFileWcdma(List<by.dazer.CellActix> actixCellFileWcdma, Set<by.dazer.SiteActix> actixSiteWcdma,  String outPath) {
//        try (FileWriter fr = new FileWriter(outPath,false)) {
//            StringBuilder sB = new StringBuilder();
//            sB.append("; #NetworkData - datafile\n");
//            //First part of table with nodeB
//            for (by.dazer.SiteActix lstSite : actixSiteWcdma){
//                sB.append("WCDMA_Site").append("\t").append(lstSite.getNodeB()).append("\t").append(lstSite.getNodeB()).append("\t").
//                        append(lstSite.getLat()).append("\t").append(lstSite.getLon()).append("\n");
//            }
//
//            //second part of table with Cells
//            for (by.dazer.CellActix lst : actixCellFileWcdma){
//                sB.append("WCDMA_Cell").append("\t").append(lst.getNodeB()).append("\t").append(lst.getCell()).append("\t").
//                        append(lst.getAntDir()).append("\t").append(lst.getAntBeamWidth()).append("\t").append("\t").append(lst.getsC()).
//                        append("\t").append(lst.getmCC()).append("\t").append(lst.getmNC()).append("\t").
//                        append(lst.getlAC()).append("\t").append(lst.getcId()).append("\t").append("\t").append("{}").append("\t").
//                        append("\t").append("{}").append("\t").append(lst.getuAFRCN()).append("\t").append(lst.getAntHeight()).
//                        append("\t").append(lst.getAntTilt()).append("\t").append("\n");
//
//            }
//            fr.append(sB);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
