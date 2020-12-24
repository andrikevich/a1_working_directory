package by.dazer;

public class InitialData {
        public static String startOfDestRoute = "\\\\pc-vel-408062\\PC-tech-100\\Optima_server\\DRIVE_TESTS\\Cell_file\\";
    // path for saving finished cell files
    private String sourceNemoCellWCDMA = startOfDestRoute + "Nemo\\NemoCells_WCDMA.nbf";
    private String sourceNemoCellGSM = startOfDestRoute + "Nemo\\NemoCells_GSM.nbf";
    private String sourceActixCell = startOfDestRoute + "Actix\\cellref.txt";
    private String sourceInfoVistaCel = startOfDestRoute + "Discovery\\Scr\\CellFile.cel";
    private String sourceInfoVistaXmlG = startOfDestRoute + "Discovery\\Scr\\CellFileG.xml";
    private String sourceInfoVistaXmlW_neigh = startOfDestRoute + "Discovery\\Scr\\CellFileW(neigh).xml";
    private String sourceInfoVistaXmlW = startOfDestRoute + "Discovery\\Scr\\CellFileW.xml";
    private String sourceTemsNbIoT = startOfDestRoute + "Discovery\\Scr\\NbIoT.xml";
    private String sourceTemsLteBeCloud = startOfDestRoute + "Discovery\\Scr\\LteBecloud.xml";


    public String getSourceInfoVistaXmlW() {
        return sourceInfoVistaXmlW;
    }

    public void setSourceInfoVistaXmlW(String sourceInfoVistaXmlW) {
        this.sourceInfoVistaXmlW = sourceInfoVistaXmlW;
    }
    // ARFCN of NbIoT velcom
    private String eafrcnNbIoT = "3602";

    //Path for reserved copy of all cell files for all vendors
    private String sourceArchive = startOfDestRoute + "Archive\\";



    //-----------------------------------------
    //Atoll Data Base connection options
    private  String atollIpConnection =  "jdbc:oracle:thin:@10.254.12.216:1521:atoll";
    //for superuser
//    private String atollLogin = "system";
//    private  String atollPwd = "sys";

    //for read only user
    private String atollLogin = "telegis_ro";
    private  String atollPwd = "telegis_ro";

    //SQL Query from Atoll DataBAse
        // for common Cel file from Vitya view
    private  String atollSqlQueryCommonCell = "SELECT CELL,NODE_B,LAT,LON,MCC,MNC,LAC,CI,RAC,ANT_DIRECTION,ANT_BEAM_WIDTH,ANT_TYPE,ANT_HEIGHT,ANT_TILT,CELL_TYPE,\n" +
            "ARFCN,BSIC,UARFCN,SC,\"FreqBand\",\"RNC-ID\",\"C-ID\",CPICH_POWER,NETWORK_CELLID,RET FROM ATOLL_3GPP_V.CELLFILE";
    //  WHERE node_b in ('offic2','min107','kirme1')
    //where CELL like 'NOV%'

      // for NbIoT cell file
    private String  atollSqlQueryNbiotCell = "SELECT \n" +
              "ATOLL_3GPP_V.XGCELLSNBIOT.Tx_ID,\n" +
              "ATOLL_3GPP_V.XGCELLSNBIOT.CARRIER,\n" +
              "ATOLL_3GPP_V.SITES.LAT_WGS84,\n" +
              "ATOLL_3GPP_V.SITES.LONG_WGS84,\n" +
              "ATOLL_3GPP_V.XGCELLSNBIOT.MAX_POWER,\n" +
              "ATOLL_3GPP_V.xgTRANSMITTERS.SITE_NAME,\n" +
              "ATOLL_3GPP_V.XGCELLSNBIOT.NPCI, \n" +
              "256*TO_NUMBER(ATOLL_3GPP_V.SITES.BTSID_NBIOT)+ ATOLL_3GPP_V.XGCELLSNBIOT.CI as ECI,\n" +
              "ATOLL_3GPP_V.xgTRANSMITTERS.TAC,\n" +
              "ATOLL_3GPP_V.xgTRANSMITTERS.AZIMUT,\n" +
              "ATOLL_3GPP_V.ANTENNAS.BEAMWIDTH,\n" +
              "ATOLL_3GPP_V.xgTRANSMITTERS.HEIGHT\n" +
              "from ATOLL_3GPP_V.XGCELLSNBIOT\n" +
              "INNER join ATOLL_3GPP_V.XGTRANSMITTERS \n" +
              "ON ATOLL_3GPP_V.XGCELLSNBIOT.TX_ID = ATOLL_3GPP_V.xgTRANSMITTERS.TX_ID \n" +
              "LEFT JOIN ATOLL_3GPP_V.SITES \n" +
              "ON ATOLL_3GPP_V.xgTRANSMITTERS.site_name = ATOLL_3GPP_V.SITES.name\n" +
              "LEFT JOIN ATOLL_3GPP_V.ANTENNAS \n" +
              "ON ATOLL_3GPP_V.xgTRANSMITTERS.ANTENNA_NAME = ATOLL_3GPP_V.ANTENNAS.NAME\n" +
              "where ATOLL_3GPP_V.XGCELLSNBIOT.CARRIER like '%3602%' and \n" +
              "ATOLL_3GPP_V.XGTRANSMITTERS.FBAND = 'E-UTRA Band 8 (900)' \n" +
              "and ATOLL_3GPP_V.xgTRANSMITTERS.TAC not like 'null' and ATOLL_3GPP_V.SITES.BTSID_NBIOT not like 'null'\n" +
              "and ATOLL_3GPP_V.XGCELLSNBIOT.NPCI not like 'null'\n";


    private String atollSqlQueryLteBeCloud = "SELECT \n" +
            "ATOLL_3GPP_V.XGCELLSLTE.Tx_ID,\n" +
            "ATOLL_3GPP_V.XGCELLSLTE.CARRIER,\n" +
            "ATOLL_3GPP_V.SITES.LAT_WGS84,\n" +
            "ATOLL_3GPP_V.SITES.LONG_WGS84,\n" +
            "ATOLL_3GPP_V.XGCELLSLTE.MAX_POWER,\n" +
            "ATOLL_3GPP_V.XGTRANSMITTERS.SITE_NAME,\n" +
            "ATOLL_3GPP_V.XGCELLSLTE.PCI,\n" +
            "(256*TO_NUMBER(ATOLL_3GPP_V.SITES.name)+ ATOLL_3GPP_V.XGCELLSLTE.CI) as ECI,\n" +
            "ATOLL_3GPP_V.XGTRANSMITTERS.TAC,\n" +
            "ATOLL_3GPP_V.XGTRANSMITTERS.AZIMUT,\n" +
            "ATOLL_3GPP_V.ANTENNAS.BEAMWIDTH,\n" +
            "ATOLL_3GPP_V.XGTRANSMITTERS.HEIGHT\n" +
            "from ATOLL_3GPP_V.XGCELLSLTE  \n" +
            "INNER join ATOLL_3GPP_V.XGTRANSMITTERS \n" +
            "ON ATOLL_3GPP_V.XGCELLSLTE.TX_ID = ATOLL_3GPP_V.XGTRANSMITTERS.TX_ID\n" +
            "LEFT JOIN ATOLL_3GPP_V.SITES \n" +
            "ON ATOLL_3GPP_V.XGTRANSMITTERS.site_name = ATOLL_3GPP_V.SITES.name\n" +
            "LEFT JOIN ATOLL_3GPP_V.ANTENNAS \n" +
            "ON ATOLL_3GPP_V.XGTRANSMITTERS.ANTENNA_NAME = ATOLL_3GPP_V.ANTENNAS.NAME\n" +
            "where COMPANY like '%ecloud%' \n" +
            "AND (ATOLL_3GPP_V.XGCELLSLTE.CARRIER like '%E-UTRA Band 3%' OR ATOLL_3GPP_V.XGCELLSLTE.CARRIER like '%E-UTRA Band 20%' " +
            " OR ATOLL_3GPP_V.XGCELLSLTE.CARRIER like '%E-UTRA Band 7%')\n";

    //--------------------------------------------------


    //connection to IOSS
    private  String iOssIpConnection =  "jdbc:oracle:thin:@10.254.12.163:1521:CMDB";
    private String iOssLogin = "velcom";
    private  String iOssPwd = "f2xiVHjyTsEP6CMab8DLgnpu";

    //----------------------------------------------------


    public String getSourceTemsLteBeCloud() {
        return sourceTemsLteBeCloud;
    }

    public String getAtollSqlQueryLteBeCloud() {
        return atollSqlQueryLteBeCloud;
    }

    public String getiOssIpConnection() {
        return iOssIpConnection;
    }

    public void setiOssIpConnection(String iOssIpConnection) {
        this.iOssIpConnection = iOssIpConnection;
    }

    public String getiOssLogin() {
        return iOssLogin;
    }

    public void setiOssLogin(String iOssLogin) {
        this.iOssLogin = iOssLogin;
    }

    public String getiOssPwd() {
        return iOssPwd;
    }

    public void setiOssPwd(String iOssPwd) {
        this.iOssPwd = iOssPwd;
    }

    public String getAtollSqlQueryNbiotCell() {
        return atollSqlQueryNbiotCell;
    }

    public void setAtollSqlQueryNbiotCell(String atollSqlQueryNbiotCell) {
        this.atollSqlQueryNbiotCell = atollSqlQueryNbiotCell;
    }

    public String getSourceNemoCellWCDMA() {
        return sourceNemoCellWCDMA;
    }

    public void setSourceNemoCellWCDMA(String sourceNemoCellWCDMA) {
        this.sourceNemoCellWCDMA = sourceNemoCellWCDMA;
    }

    public String getSourceNemoCellGSM() {
        return sourceNemoCellGSM;
    }

    public void setSourceNemoCellGSM(String sourceNemoCellGSM) {
        this.sourceNemoCellGSM = sourceNemoCellGSM;
    }

    public String getSourceActixCell() {
        return sourceActixCell;
    }

    public void setSourceActixCell(String sourceActixCell) {
        this.sourceActixCell = sourceActixCell;
    }

    public String getSourceInfoVistaCel() {
        return sourceInfoVistaCel;
    }

    public void setSourceInfoVistaCel(String sourceInfoVistaCel) {
        this.sourceInfoVistaCel = sourceInfoVistaCel;
    }

    public String getSourceInfoVistaXmlG() {
        return sourceInfoVistaXmlG;
    }

    public void setSourceInfoVistaXmlG(String sourceInfoVistaXmlG) {
        this.sourceInfoVistaXmlG = sourceInfoVistaXmlG;
    }

    public String getSourceInfoVistaXmlW_neigh() {
        return sourceInfoVistaXmlW_neigh;
    }

    public void setSourceInfoVistaXmlW_neigh(String sourceInfoVistaXmlW_neigh) {
        this.sourceInfoVistaXmlW_neigh = sourceInfoVistaXmlW_neigh;
    }

    public String getSourceTemsNbIoT() {
        return sourceTemsNbIoT;
    }

    public void setSourceTemsNbIoT(String sourceTemsNbIoT) {
        this.sourceTemsNbIoT = sourceTemsNbIoT;
    }

    public String getEafrcnNbIoT() {
        return eafrcnNbIoT;
    }

    public void setEafrcnNbIoT(String eafrcnNbIoT) {
        this.eafrcnNbIoT = eafrcnNbIoT;
    }

    public String getSourceArchive() {
        return sourceArchive;
    }

    public void setSourceArchive(String sourceArchive) {
        this.sourceArchive = sourceArchive;
    }

    public String getAtollIpConnection() {
        return atollIpConnection;
    }

    public void setAtollIpConnection(String atollIpConnection) {
        this.atollIpConnection = atollIpConnection;
    }

    public String getAtollLogin() {
        return atollLogin;
    }

    public void setAtollLogin(String atollLogin) {
        this.atollLogin = atollLogin;
    }

    public String getAtollPwd() {
        return atollPwd;
    }

    public void setAtollPwd(String atollPwd) {
        this.atollPwd = atollPwd;
    }

    public String getAtollSqlQueryCommonCell() {
        return atollSqlQueryCommonCell;
    }

    public void setAtollSqlQueryCommonCell(String atollSqlQueryCommonCell) {
        this.atollSqlQueryCommonCell = atollSqlQueryCommonCell;
    }
}

