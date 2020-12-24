package by.dazer.DAO;

import by.dazer.IncomingData;
import by.dazer.Starter1;
import by.dazer.Utilities.TimeDateHandler;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandlerOfA1StuHardCode {

   private String curDate = new TimeDateHandler().getDateWithOffset(0);
   private String curDateMinus1 = new TimeDateHandler().getDateWithOffset(-1);
   private String curDateMinus2 = new TimeDateHandler ().getDateWithOffset(-2);
   private String curDateMinus3 = new TimeDateHandler ().getDateWithOffset(-3);


    public StringBuilder makeQuery_MoreBest(int metricId, int cId) {
        StringBuilder res = new StringBuilder();

        double avMonth = 0.0D;
        double avDay = 0.0D;
        double avDay_1 = 0.0D;
        double avDay_2 = 0.0D;
        String StuCity = null;



        try {

            Statement statement = new ConnectToStuDb(IncomingData.driverMsSql,IncomingData.StuServer).getStatement();
            Statement statement1 = new ConnectToStuDb(IncomingData.driverSqlLite,IncomingData.StuBase).getStatement();




            res.append("\n").append("        ==  ").append(curDate).append(" ==").append("\n");

            for(int j = 2400; j <= 2470; ++j) {
                        ResultSet rs1;
                        for(rs1 = statement.executeQuery(IncomingData.query1 + metricId + IncomingData.query2 + cId + IncomingData.month + j + IncomingData.query3); rs1.next(); avMonth = rs1.getDouble(1)) {
                        }

                        rs1.close();

                        ResultSet rs2;
                        for(rs2 = statement.executeQuery(IncomingData.query1 + metricId + IncomingData.query2 + cId + IncomingData.day0 + j + IncomingData.query3); rs2.next(); avDay = rs2.getDouble(1)) {
                        }

                        rs2.close();

                        ResultSet rs3;
                        for(rs3 = statement.executeQuery(IncomingData.query1 + metricId + IncomingData.query2 + cId + IncomingData.day1 + j + IncomingData.query3); rs3.next(); avDay_1 = rs3.getDouble(1)) {
                        }

                        rs3.close();

                        ResultSet rs4;
                        for(rs4 = statement.executeQuery(IncomingData.query1 + metricId + IncomingData.query2 + cId + IncomingData.day2 + j + IncomingData.query3); rs4.next(); avDay_2 = rs4.getDouble(1)) {
                        }

                        String queryStuCity = IncomingData.StuCityQuery1 + j + IncomingData.StuCityQuery2;

                        ResultSet rs5;
                        for(rs5 = statement1.executeQuery(queryStuCity); rs5.next(); StuCity = rs5.getString(1)) {
                        }

                        if ((avMonth - avDay) / avMonth > IncomingData.rate
                                && (avMonth - avDay_1) / avMonth > IncomingData.rate
                                && (avMonth - avDay_2) / avMonth > IncomingData.rate
                                && avMonth != 0.0D && avDay_1 != 0.0D && avDay_2 != 0.0D && avDay != 0.0D) {

                                    res.append("\n").append("  ****  ").append(String.valueOf(j)).append(" --- ")
                                            .append(StuCity).append("  ****  ")
                                       .append("  avMonth = " + String.format("%,.2f", avMonth)).append("  ****  ").append("\n");

                                    writeResult( res,   avDay_2, avMonth, curDateMinus2);
                                    writeResult( res, avDay_1, avMonth, curDateMinus1);
                                    writeResult(res, avDay, avMonth, curDate);

                                    avMonth = 0.0D;
                                    avDay = 0.0D;
                                    avDay_1 = 0.0D;
                                    avDay_2 = 0.0D;
                                    StuCity = null;
                                    rs4.close();
                                    rs5.close();


                        } else {
                                    avMonth = 0.0D;
                                    avDay = 0.0D;
                                    avDay_1 = 0.0D;
                                    avDay_2 = 0.0D;
                        }

            }


            res.append("-----------------------------------------").append("\n");
            return res;
        } catch (SQLException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
    }
        return null;
    }

    public StringBuilder makeQuery_LessBest(int metricId, int cId) {
        StringBuilder res = new StringBuilder();

        double avMonth = 0.0D;
        double avDay = 0.0D;
        double avDay_1 = 0.0D;
        double avDay_2 = 0.0D;
        String StuCity = null;



        try {

            Statement statement = new ConnectToStuDb(IncomingData.driverMsSql,IncomingData.StuServer).getStatement();
            Statement statement1 = new ConnectToStuDb(IncomingData.driverSqlLite,IncomingData.StuBase).getStatement();




            res.append("\n").append("        ==  ").append(curDate).append(" ==").append("\n");

            for(int j = 2400; j <= 2470; ++j) {
                ResultSet rs1;
                for(rs1 = statement.executeQuery(IncomingData.query1 + metricId + IncomingData.query2 + cId + IncomingData.month + j + IncomingData.query3); rs1.next(); avMonth = rs1.getDouble(1)) {
                }

                rs1.close();

                ResultSet rs2;
                for(rs2 = statement.executeQuery(IncomingData.query1 + metricId + IncomingData.query2 + cId + IncomingData.day0 + j + IncomingData.query3); rs2.next(); avDay = rs2.getDouble(1)) {
                }

                rs2.close();

                ResultSet rs3;
                for(rs3 = statement.executeQuery(IncomingData.query1 + metricId + IncomingData.query2 + cId + IncomingData.day1 + j + IncomingData.query3); rs3.next(); avDay_1 = rs3.getDouble(1)) {
                }

                rs3.close();

                ResultSet rs4;
                for(rs4 = statement.executeQuery(IncomingData.query1 + metricId + IncomingData.query2 + cId + IncomingData.day2 + j + IncomingData.query3); rs4.next(); avDay_2 = rs4.getDouble(1)) {
                }

                String queryStuCity = IncomingData.StuCityQuery1 + j + IncomingData.StuCityQuery2;

                ResultSet rs5;
                for(rs5 = statement1.executeQuery(queryStuCity); rs5.next(); StuCity = rs5.getString(1)) {
                }

                if ((avDay - avMonth) / avMonth > IncomingData.rate && (avDay_1 - avMonth) / avMonth > IncomingData.rate && (avDay_2 - avMonth) / avMonth > IncomingData.rate && avMonth != 0.0D && avDay_1 != 0.0D && avDay_2 != 0.0D && avDay != 0.0D) {

                    res.append("\n").append("  ****  ").append(String.valueOf(j)).append(" --- ").append(StuCity).append("  ****  ")
                            .append("  avMonth = " + String.format("%,.2f", avMonth)).append("  ****  ").append("\n");

                    writeResult( res,   avDay_2, avMonth,curDateMinus2);
                    writeResult( res, avDay_1, avMonth, curDateMinus1);
                    writeResult(res, avDay, avMonth, curDate);

                    avMonth = 0.0D;
                    avDay = 0.0D;
                    avDay_1 = 0.0D;
                    avDay_2 = 0.0D;
                    StuCity = null;
                    rs4.close();
                    rs5.close();


                } else {
                    avMonth = 0.0D;
                    avDay = 0.0D;
                    avDay_1 = 0.0D;
                    avDay_2 = 0.0D;
                }
            }


            res.append("-----------------------------------------").append("\n");
            return res;
        } catch (SQLException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
        }
        return null;
    }


    private  StringBuilder writeResult(StringBuilder res,  double avDay, double avMonth, String date) {
        res.append(date).append("  ---  ").append(String.valueOf(String.format("%,.2f", avDay))).append("  ---  ")
           .append(String.valueOf(String.format("%,.2f", (1.0D - avDay / avMonth) * 100.0D))).append("% decreasing")
           .append("\n");
        return res;
    }

}
