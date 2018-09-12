package com.jimmy.webapi;

import java.sql.*;

public class OracleDBConnection {
    private String ip = "localhost";
    private String port = "1521";
    private String db = "orcl";
    private String dbUser = "JimmyUser";
    private String dbpassword = "jim930527";
    private String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" +db;
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private Connection conn = null;
//"localhost","1521","orcl","JimmyUser","jim930527","oracle.jdbc.driver.OracleDriver"
    public OracleDBConnection(String ip,String port,String dbName,String dbUser,String dbpassword,String driver) throws SQLException {
        this.ip = ip;
        this.port = port;
        this.db = dbName;
        this.dbUser = dbUser;
        this.dbpassword = dbpassword;
        this.driver = driver;
        setDriver(this.driver);
        this.conn = DriverManager.getConnection(this.url, this.dbUser , this.dbpassword);
    }
    public OracleDBConnection() throws SQLException {
        setDriver(this.driver);
        this.conn = DriverManager.getConnection(this.url, this.dbUser , this.dbpassword);
    }

    public void setDBUrl(String ip,String port,String dbName){
        this.ip = ip;
        this.port = port;
        this.db = dbName;
    }
    public void setDBConnection(String dbUser,String dbpassword) throws SQLException {
        this.dbUser = dbUser;
        this.dbpassword = dbpassword;
        this.conn = DriverManager.getConnection(this.url, this.dbUser , this.dbpassword);
    }
    public void setDriver(String driver){
        this.driver = driver;
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getDBConnection(){
        return this.conn;
    }
}
