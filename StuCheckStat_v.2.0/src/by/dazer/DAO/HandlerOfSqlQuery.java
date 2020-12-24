package by.dazer.DAO;

import java.sql.*;
import java.util.*;

public class HandlerOfSqlQuery extends ConnectToStuDb {


    public HandlerOfSqlQuery(String driverMsSql, String stuServer) {
        super(driverMsSql,stuServer);
    }



    public Map<Integer, List<String>> infoOfNotWorkingStu (ResultSet results){
      Map<Integer, List<String>> mapOfInfo = new LinkedHashMap<Integer, List<String>>();
      try {
          while (results.next()){
              mapOfInfo.put (results.getInt(1), Arrays.asList(results.getString(2),results.getString(3)));
          }
      } catch (SQLException ex){

      }
     return mapOfInfo;
    }




}
