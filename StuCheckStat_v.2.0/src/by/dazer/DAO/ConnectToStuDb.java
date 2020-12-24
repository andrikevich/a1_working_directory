package by.dazer.DAO;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConnectToStuDb implements  SqlConnector{
    private Connection connection;

    protected ConnectToStuDb() {

    }

    public ConnectToStuDb(String dbDriverName, String conToDbInfo) {
        try {
            Class.forName(dbDriverName);
            Connection tmpConnection = DriverManager.getConnection(conToDbInfo);
            this.connection = tmpConnection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public ResultSet getResultSet(String querySql) {
        try {
            Statement statement = this.getStatement();
            return statement.executeQuery(querySql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Statement getStatement() {

        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
