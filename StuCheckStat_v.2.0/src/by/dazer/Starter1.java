package by.dazer;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Starter1 {
    static StringBuilder res = new StringBuilder();
    static String queryWhere = null;

    public Starter1() {
    }

    public static void main(String[] args) throws IOException {
        Calendar curDate = new GregorianCalendar();
        curDate.add(Calendar.DATE, -1);
        Calendar curDate1 = new GregorianCalendar();
        curDate1.add(Calendar.DATE, -2);
        Calendar curDate2 = new GregorianCalendar();
        curDate2.add(Calendar.DATE, -3);
        SimpleDateFormat fd = new SimpleDateFormat("yyyy.MM.dd");


        makeQuery_MoreBest(IncomingData.metricIdThroughput, IncomingData.cIdDL, curDate, curDate1, curDate2, fd, IncomingData.pathFileDL);
        makeQuery_MoreBest(IncomingData.metricIdThroughput, IncomingData.cIdUL, curDate, curDate1, curDate2, fd, IncomingData.pathFileUL);
        makeQuery_LessBest(IncomingData.metricIdPing, IncomingData.cIdPing, curDate, curDate1, curDate2, fd, IncomingData.pathFilePing);
    }

    private static StringBuilder writeResult(SimpleDateFormat fd, StringBuilder res, FileWriter fw, Calendar date, double avDay, double avMonth) {
        res.append(fd.format(date.getTime()));
        res.append("  ---  ");
        res.append(String.valueOf(String.format("%.2f", avDay)));
        res.append("  ---  ");
        res.append(String.valueOf(String.format("%.2f", (1.0D - avDay / avMonth) * 100.0D)));
        res.append("% decreasing");
        res.append("\n");
        return res;
    }

    private static void makeQuery_MoreBest(int metricId, int cId, Calendar curDate, Calendar curDate1, Calendar curDate2, SimpleDateFormat fd, String pathFile) {
        double avMonth = 0.0D;
        double avDay = 0.0D;
        double avDay_1 = 0.0D;
        double avDay_2 = 0.0D;
        String StuCity = null;

        try {
            Driver driver = (Driver)Class.forName(IncomingData.driverMsSql).newInstance();
            Connection con = DriverManager.getConnection(IncomingData.StuServer);
            Statement statement = con.createStatement();
            Driver driver1 = (Driver)Class.forName("org.sqlite.JDBC").newInstance();
            Connection con1 = DriverManager.getConnection(IncomingData.StuBase);
            Statement statement1 = con1.createStatement();
            FileWriter fw1 = new FileWriter(pathFile, true);
            res.append("\n");
            res.append("        ==  ");
            res.append(fd.format(curDate.getTime()));
            res.append(" ==");
            res.append("\n");
            fw1.append(res);
            res.delete(0, res.length());
            fw1.close();

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

                if ((avMonth - avDay) / avMonth > IncomingData.rate && (avMonth - avDay_1) / avMonth > IncomingData.rate && (avMonth - avDay_2) / avMonth > IncomingData.rate && avMonth != 0.0D && avDay_1 != 0.0D && avDay_2 != 0.0D && avDay != 0.0D) {
                    FileWriter fw = new FileWriter(pathFile, true);
                    res.append("\n");
                    res.append("  ****  ");
                    res.append(String.valueOf(j));
                    res.append(" --- ");
                    res.append(StuCity);
                    res.append("  ****  ");
                    res.append("  avMonth = " + String.format("%.2f", avMonth));
                    res.append("  ****  ");
                    res.append("\n");
                    writeResult(fd, res, fw, curDate2, avDay_2, avMonth);
                    writeResult(fd, res, fw, curDate1, avDay_1, avMonth);
                    writeResult(fd, res, fw, curDate, avDay, avMonth);
                    avMonth = 0.0D;
                    avDay = 0.0D;
                    avDay_1 = 0.0D;
                    avDay_2 = 0.0D;
                    StuCity = null;
                    rs4.close();
                    rs5.close();
                    fw.append(res);
                    fw.close();
                    res.delete(0, res.length());
                } else {
                    res.delete(0, res.length());
                    avMonth = 0.0D;
                    avDay = 0.0D;
                    avDay_1 = 0.0D;
                    avDay_2 = 0.0D;
                }
            }

            FileWriter fw2 = new FileWriter(pathFile, true);
            res.append("-----------------------------------------");
            res.append("\n");
            fw2.append(res);
            fw2.close();
            res.delete(0, res.length());
        } catch (InstantiationException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
        } catch (IllegalAccessException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
        } catch (SQLException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
        } catch (ClassNotFoundException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
        } catch (IOException var32) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var32);
        }

    }

    private static void makeQuery_LessBest(int metricId, int cId, Calendar curDate, Calendar curDate1, Calendar curDate2, SimpleDateFormat fd, String pathFile) {
        double avMonth = 0.0D;
        double avDay = 0.0D;
        double avDay_1 = 0.0D;
        double avDay_2 = 0.0D;
        String StuCity = null;

        try {
            Driver driver = (Driver)Class.forName(IncomingData.driverMsSql).newInstance();
            Connection con = DriverManager.getConnection(IncomingData.StuServer);
            Statement statement = con.createStatement();
            Driver driver1 = (Driver)Class.forName("org.sqlite.JDBC").newInstance();
            Connection con1 = DriverManager.getConnection(IncomingData.StuBase);
            Statement statement1 = con1.createStatement();
            FileWriter fw1 = new FileWriter(pathFile, true);
            res.append("\n");
            res.append("        ==  ");
            res.append(fd.format(curDate.getTime()));
            res.append(" ==");
            res.append("\n");
            fw1.append(res);
            res.delete(0, res.length());
            fw1.close();

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
                    FileWriter fw = new FileWriter(pathFile, true);
                    res.append("\n");
                    res.append("  ****  ");
                    res.append(String.valueOf(j));
                    res.append(" --- ");
                    res.append(StuCity);
                    res.append("  ****  ");
                    res.append("  avMonth = " + String.format("%.2f", avMonth));
                    res.append("  ****  ");
                    res.append("\n");
                    writeResult(fd, res, fw, curDate2, avDay_2, avMonth);
                    writeResult(fd, res, fw, curDate1, avDay_1, avMonth);
                    writeResult(fd, res, fw, curDate, avDay, avMonth);
                    avMonth = 0.0D;
                    avDay = 0.0D;
                    avDay_1 = 0.0D;
                    avDay_2 = 0.0D;
                    StuCity = null;
                    rs4.close();
                    rs5.close();
                    fw.append(res);
                    fw.close();
                    res.delete(0, res.length());
                } else {
                    res.delete(0, res.length());
                    avMonth = 0.0D;
                    avDay = 0.0D;
                    avDay_1 = 0.0D;
                    avDay_2 = 0.0D;
                }
            }

            FileWriter fw2 = new FileWriter(pathFile, true);
            res.append("-----------------------------------------");
            res.append("\n");
            fw2.append(res);
            fw2.close();
            res.delete(0, res.length());
        } catch (InstantiationException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
        } catch (IllegalAccessException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
        } catch (SQLException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
        } catch (ClassNotFoundException var31) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var31);
        } catch (IOException var32) {
            Logger.getLogger(Starter1.class.getName()).log(Level.SEVERE, (String)null, var32);
        }

    }
}
