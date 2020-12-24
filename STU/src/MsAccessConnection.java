

   
    import java.sql.*;
 
public class MsAccessConnection {
 
    
    static public void selectFromDb(String dbURL, String sqlQuery){
    
         try{
                   
     Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");      
     
     Connection conn=DriverManager.getConnection("jdbc:ucanaccess://" + dbURL);
            
     Statement st=conn.createStatement();
     
     String sql=sqlQuery;
     
     ResultSet rs=st.executeQuery(sql);
     while(rs.next()){
         
         System.out.println("\n"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
     }
     
    }catch(Exception e){
    
    System.out.println(e);
        
    }
    }
  }
    

