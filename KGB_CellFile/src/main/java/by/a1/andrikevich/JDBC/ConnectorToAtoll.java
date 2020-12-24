package by.a1.andrikevich.JDBC;

import by.a1.andrikevich.util.InitialData;
import by.a1.andrikevich.util.PropertiesUtil;

import java.sql.*;
import java.util.Properties;

public class ConnectorToAtoll {
    private Properties prop = PropertiesUtil.getProperty(InitialData.propertyFile);


    private String dBUser = prop.getProperty("db.user");
    private String dBPassword = prop.getProperty("db.password");
    private String dBUrl = prop.getProperty("db.url");
    private String dbQuery = prop.getProperty("db.query");
    private String separator = prop.getProperty("element.separator");
    private int dbcolumnNumber = Integer.parseInt(prop.getProperty("db.column.number"));



    public StringBuilder getResultFromDB (){

        //count number of rows in result file
        int countOfRow = 0;

        Connection connection;
        ResultSet rs;

        StringBuilder result = new StringBuilder();

        try {
            connection = DriverManager.getConnection(dBUrl,dBUser,dBPassword);
            Statement statement = connection.createStatement();
            statement.executeQuery(dbQuery);
            rs = statement.getResultSet();

            while (rs.next()) {

                for (int i = 0; i < dbcolumnNumber; i++) {
                    String temsTempStr = rs.getString(i + 1);
                    String resStr = (temsTempStr == null) ? "" : temsTempStr;
                    result.append(resStr).append(separator);
                }
                result.append("\n");
                countOfRow++;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.append("EOF;"+countOfRow);
        return result;
    }




    }
