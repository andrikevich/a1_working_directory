package by.dazer.DAO;

import java.sql.ResultSet;
import java.sql.Statement;

public interface SqlConnector {
    public ResultSet getResultSet(String querySql);
    public Statement getStatement();
}
