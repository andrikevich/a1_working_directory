package by.dazer;

import java.sql.*;
import java.util.*;

public class NeighbourList {
    private static NeighbourList ourInstance = new NeighbourList();

    public static NeighbourList getInstance() {
        return ourInstance;
    }

    private NeighbourList() {

    }

    /**
     *
     * @param
     * @return
     */
        public Map<String, Set<String>> getNeighBourList (){
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

            return  mapOfNeighbour;
    }

}
