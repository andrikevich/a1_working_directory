package by.dazer;

public class IncomingData {
    public static String StuServer = "jdbc:sqlserver://10.236.116.121:2245;user=vel_read;password=Pwhsds%y";
    public static String driverMsSql = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static int metricIdThroughput = 5230;
    public static int cIdDL = 5315;
    public static int cIdUL = 5316;
    public static int metricIdPing = 2003;
    public static int cIdPing = 5200;
    public static String commonDestinationFolder = "\\\\srv-logsblr-003\\f$\\Documents\\STU_check\\";
    //public static String commonDestinationFolder = "i:\\Download\\";
    public static String pathFileDL = commonDestinationFolder + "DL.txt";
    public static String pathFileUL = commonDestinationFolder + "UL.txt";
    public static String pathFilePing = commonDestinationFolder + "Ping.txt";
    public static String pathFileWorkAbility = commonDestinationFolder + "WorkAbilityOfStu.txt";
    public static String query1 = "Select  avg(m.metric) from  vel.f_ticket t full join vel.f_metric m    ON m.ticket_id = t.ticket_id  Where m.metric_id = ";
    public static String query2 = "    and t.c_id= ";
    public static String query3 = " ) group by t.agent_id   order by t.agent_id";
    public static String month = "   and mtime>= DATEADD(day,-63,GETDATE()) and mtime< DATEADD(day,-3,GETDATE()) and t.agent_id in ( ";
    public static String day0 = "   and mtime>= DATEADD(day,-1,GETDATE()) and mtime< DATEADD(day,0,GETDATE()) and t.agent_id in ( ";
    public static String day1 = "   and mtime>= DATEADD(day,-2,GETDATE()) and mtime< DATEADD(day,-1,GETDATE()) and t.agent_id in ( ";
    public static String day2 = "   and mtime>= DATEADD(day,-3,GETDATE()) and mtime< DATEADD(day,-2,GETDATE()) and t.agent_id in ( ";
    public static double rate = 0.1D;
    public static String driverSqlLite = "org.sqlite.JDBC";
    //public static String StuBase = "jdbc:sqlite:C:\\PC-tech-084\\Andrikevich\\Bat\\dist\\Stu_Base.db";
    public static String StuBase = "jdbc:sqlite:\\\\pc-vel-408062\\\\PC-tech-084\\\\Andrikevich\\\\Bat\\\\dist\\\\Stu_Base.db";
    public static String notWorkingStu = "\\\\pc-vel-408062\\c$\\PC-tech-084\\Andrikevich\\Bat\\dist\\ListOfExcludingStu\\ExStu.txt";
    public static String StuCityQuery1 = "select City from stu WHERE stu_num in (";
    public static String StuCityQuery2 = ")";
    public static String queryForNotWorkingStu1 = "select distinct  agent_id, DAY (SYSDATETIME() - (max(mtime))) as daysOfOff, max(mtime) as lastTimeOn \n" +
            "from vel.f_ticket\n" +
            "where agent_id NOT in (";
    public static String queryForNotWorkingStu2 =  ")\n" +
            "GROUP BY agent_id  \n" +
            "having (DAY (SYSDATETIME() - (max(mtime)))) > 1  \n" +
            "order by daysOfOff desc;";
    public IncomingData() {
    }
}