
public class IncomingData {
    //Setting for Austrian STU server
    public static String StuServer="jdbc:sqlserver://10.236.116.121:2245;user=vel_read;password=Pwhsds%y";
    public static String NeRabStu="select distinct  agent_id, DAY (SYSDATETIME())-DAY (max(mtime)) as NERAB from vel.f_ticket where agent_id NOT in("
            + ImportExStu.exStuStr() + ") GROUP BY agent_id  having (DAY (SYSDATETIME())-DAY (max(mtime)))!=0  order by agent_id";
    public static String driverMsSql="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Setting for STU database

//FOR request from SQLite
//  public static String StuBase="jdbc:sqlite://c:/Andrikevich/GDR/STU/Auto_mail/Stu_Base.db";
//  public static String EmailQuery="select email from stu";


    // For Base from Ms Access
    public static String StuBase="jdbc:ucanaccess://c:/Install/Database3.accdb";
    public static String EmailQuery="SELECT Email FROM STU";

    public static String mailSubject="ПЕРЕЗАГРУЗКА%20прибора%20STU%20установленного%20в%20вашем%20ЦПО";
    public static String mailBody="Коллеги%20добрый%20день.%0D%0A%0D%0AПроверьте,%20пожалуйста,%20по-возможности,%20включен%20ли%20наш%20тестовый%20прибор.%0D%0A%20%0D%0AЕсли%20нет%20включите%20его,%20пожалуйста,%20в%20розетку%20и%20нажмите%20на%20нем%20сверху%20от%20модема%20на%20кнопку%20включения.%0D%0A%0D%0AЗаранее%20огромное%20спасибо!%0D%0A%0D%0ABest%20regards,%20Сергей%20Чижик.";


}
