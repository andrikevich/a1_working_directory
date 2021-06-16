import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Start {

    StringBuilder res1 = new StringBuilder();
    static String queryWhere=null;
    static String emailList=null;
    public static void main(String[] args) {

        try {
            Driver driver = (Driver)Class.forName(IncomingData.driverMsSql).newInstance();
            Connection con = DriverManager.getConnection(IncomingData.StuServer);
            Statement statement = con.createStatement();
            ArrayList<Integer> list = new ArrayList<>();

            ResultSet rs = statement.executeQuery(IncomingData.NeRabStu);
            StringBuilder strStu=new StringBuilder();
            while (rs.next()) {
                list.add(rs.getInt("agent_id"));
            }

            for (int i:list){

                strStu.append(String.valueOf(i)).append(",");
            }
            queryWhere=" WHERE STU in ("+strStu.substring(0, strStu.length()-1)+");";
            System.out.println(queryWhere);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }


        //STU base на локальном компе
        try {

            Driver driver = (Driver) Class.forName("net.ucanaccess.jdbc.UcanaccessDriver").newInstance();
            Connection con = DriverManager.getConnection(IncomingData.StuBase);

//            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
//            Connection con = DriverManager.getConnection(IncomingData.StuBase);
            Statement statement = con.createStatement();
            ArrayList<String> list_2 = new ArrayList<>();
            String queryStuEmail=IncomingData.EmailQuery+queryWhere;
            ResultSet rs = statement.executeQuery(queryStuEmail);
            StringBuilder strStuEmail=new StringBuilder();
            while (rs.next()) {
                list_2.add(rs.getString("email"));
            }

            for (String i:list_2){

                strStuEmail.append(String.valueOf(i)).append(";");
            }

            emailList=strStuEmail.substring(0, strStuEmail.length()-1);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("list of emails: "+emailList);

        OpenMail.mail(emailList,IncomingData.mailSubject,IncomingData.mailBody);


    }
}
