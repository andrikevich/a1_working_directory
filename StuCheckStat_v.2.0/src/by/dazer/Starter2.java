package by.dazer;

import by.dazer.DAO.HandlerOfA1StuHardCode;
import by.dazer.DAO.HandlerOfSqlQuery;
import by.dazer.DAO.SqlConnector;
import by.dazer.IO.ReaderInitialData;
import by.dazer.IO.SaverResultsToDisk;


import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import static by.dazer.IncomingData.*;


public class Starter2 {
    public static void main(String[] args) {

        SqlConnector connecToStuDb = new HandlerOfSqlQuery(driverMsSql,StuServer);
        ReaderInitialData readerNotWorkongStu = new ReaderInitialData();
        String forWhereNotWorkingStu = readerNotWorkongStu.readStringFromFile(notWorkingStu);

        ResultSet rsOfNotWorkongStu = connecToStuDb.getResultSet(queryForNotWorkingStu1 + forWhereNotWorkingStu + queryForNotWorkingStu2);
        Map<Integer, List<String>> resultFromStuDb =  ((HandlerOfSqlQuery)connecToStuDb).infoOfNotWorkingStu(rsOfNotWorkongStu);

        SaverResultsToDisk saverResult = new SaverResultsToDisk();
        saverResult.saveNotWorkingStuStatToDisk(pathFileWorkAbility,resultFromStuDb);

        HandlerOfA1StuHardCode hdl = new HandlerOfA1StuHardCode();


        saverResult.saveToDiskStatisticsFromStu(pathFileDL,hdl.makeQuery_MoreBest(metricIdThroughput, cIdDL));
        saverResult.saveToDiskStatisticsFromStu(pathFileUL,hdl.makeQuery_MoreBest(metricIdThroughput, cIdUL));
        saverResult.saveToDiskStatisticsFromStu(pathFilePing,hdl.makeQuery_MoreBest(metricIdPing, cIdPing));
      





    }
}
