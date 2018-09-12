package com.jimmy.webapi;

/**
 * Created by jimmy on 18/09/11
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;

@Path("/hello")
public class Hello {
    Connection conn = null;
    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() throws SQLException {

        return "Hello Jersey \n You are using TEXT_PLAIN GET.";
    }
    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey <br> You are using TEXT_XML GET." + "</hello>";
    }
    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello(){
        Connection orclDbConn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //orclDbConn = new OracleDBConnection().getDBConnection();
            orclDbConn = new OracleDBConnection("localhost","1521","orcl","JimmyUser","jim930527","oracle.jdbc.driver.OracleDriver").getDBConnection();
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "JimmyUser", "jim930527");
            //ps = conn.prepareStatement("select oga01 from oga_file where oga01 = 'FS581-1710130001'");
            ps = orclDbConn.prepareStatement("select oga01 from oga_file where oga01 = 'FS581-1710130001'");
            rs = ps.executeQuery();
            while(rs.next())
            {
                System.out.println(rs.getString("OGA01"));
                return "<html> " +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"+
                        "<title>" + "Hello Jersey" + "</title>" +
                            "<body><h1>" +
                                "Hello Jersey <br> You are using TEXT_HTML GET.<br> 出貨單號" + rs.getString("OGA01") +
                            "</body></h1>" +
                        "</html> ";
            }
        } catch (Exception e) {
            System.out.print("ERROR: "+e.getMessage());
        }
        finally
        {
            if (rs != null) {try {rs.close();} catch (SQLException e) {}}
            if (ps != null) {try {ps.close();} catch (SQLException e) {}}
            //if (conn != null) {try {conn.close();} catch (SQLException e) {}}
            if (orclDbConn != null) {try {orclDbConn.close();} catch (SQLException e) {}}
        }


        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey <br> You are using TEXT_HTML GET. NULL!!!!!!!"  + "</body></h1>" + "</html> ";

    }


}