package by.dazer;

import java.sql.*;
import java.util.*;

public class Test2 {

    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap ();

        String[] array1 =  {"T1","T2","T3"};
        String[] array2 =  {"Q1","Q2","Q3"};
        String[] array3=  {"A1","A2","A3"};
        String[] array4 =  {"V4","V4","V4"};
        map.put("Temp1", new HashSet<>(Arrays.asList(array1)));
        map.put("Temp2", new HashSet<>(Arrays.asList(array2)));
        map.put("Temp3", new HashSet<>(Arrays.asList(array3)));
        map.put("Temp4", new HashSet<>(Arrays.asList(array4)));
        map.get("Temp2").add("Fourth");

        System.out.println(map);



        InitialData inData = new InitialData();
        Map<String, Set<String>> mapOfNeighbour = new HashMap <String, Set<String>>();

        //connection to IOSS
        try {
            Connection conOss = DriverManager.getConnection(inData.getiOssIpConnection(), inData.getiOssLogin(), inData.getiOssPwd());
            Statement statementOss = conOss.createStatement();


            ResultSet rsOss = statementOss.executeQuery("SELECT DS8_CELL_NAME, USERLABEL FROM IOSS_CM.ZTE3G_UUTRANRELATION");
            while (rsOss.next()) {

                if (mapOfNeighbour.containsKey(rsOss.getString(1))) {
                    mapOfNeighbour.get(rsOss.getString(1)).add(rsOss.getString(2));

                }else {
                    mapOfNeighbour.put(rsOss.getString(1),new HashSet<String>(Arrays.asList(rsOss.getString(2))));
                }
            }



        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(mapOfNeighbour);
    }
}
