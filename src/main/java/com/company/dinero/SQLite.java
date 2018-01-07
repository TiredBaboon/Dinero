package com.company.dinero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.sql.PreparedStatement;

public class SQLite {
    private String dbName;
    private String dbIdentifier;
    private String tableName;
    private Connection connection = null;
    private static final Pattern REGEX_TABLE_EXISTS = Pattern.compile("table .* already exists");
    
    public SQLite(String dbName, String tableName) throws ClassNotFoundException, SQLException {
        this.dbName = dbName;
        this.dbIdentifier = "jdbc:sqlite:" + this.dbName + ".db";
        this.tableName = tableName;
        
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection(this.dbIdentifier);
        Statement stat = this.connection.createStatement();
        //stat.executeUpdate("drop table if exists " + this.tableName + ";");
        
        try {
            stat.executeUpdate("create table " + this.tableName + " (date varchar(255), company varchar(255), charge double(99, 4));");
        } catch (SQLException e) {
            Matcher match = REGEX_TABLE_EXISTS.matcher(e.toString());
            
            if (!match.find()) {
                throw new SQLException(e);
            }
        }
    }
    
    public void insert(String date, String company, double charge) throws SQLException, ClassNotFoundException {                  
        Statement stmt = this.connection.createStatement();
        
        System.out.println(date + ", " + company + ", " + charge);
        
        stmt.executeUpdate("insert into " + this.tableName + " (date, company, charge) values ('" + date + "', '" + company + "', '" + charge + "')");
    }
    
    public void selectAll() throws SQLException {
        ResultSet rs = null;
        Statement stmt = this.connection.createStatement();
        
        rs = stmt.executeQuery("select * from " + this.tableName + ";");

        while (rs.next()) {
            System.out.println(rs.getString("date") + ", " + rs.getString("company") + ", " + rs.getString("charge"));
        }
    }
    
    public void closeConnection() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }
}