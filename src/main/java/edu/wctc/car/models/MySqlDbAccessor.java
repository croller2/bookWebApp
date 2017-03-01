/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import javax.sql.DataSource;

/**
 *
 * @author chris
 */
public class MySqlDbAccessor implements DbAccessor {
    
    private Connection conn;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private Statement stmt;
    private ResultSet rs;

    public final String getDriverClass() {
        return driverClass;
    }

    public final void setDriverClass(String driverClass) {
        if(driverClass != null){
            this.driverClass = driverClass;
        }
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        if(url != null){   
            this.url = url;
        }
    }

    public final String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        if(userName != null){
            this.userName = userName;    
        }
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        if(password != null){
            this.password = password;  
        }
    }
      
    // Consider creating a custom exception
    @Override
    public final void openConnection(String driverClass, String Url, String userName, String password) 
        throws ClassNotFoundException, SQLException{
       
        setDriverClass(driverClass);
        setUrl(Url);
        setUserName(userName);
        setPassword(password);
        
        Class.forName(this.driverClass);
        conn = DriverManager.getConnection(this.url, this.userName, this.password);
    }
    
    @Override
    public final void openConnection(DataSource ds) throws SQLException {
        conn = ds.getConnection();
    }
    
    @Override
    public final void closeConnection() throws SQLException{
        if(conn != null){
            conn.close(); 
        }
    }
    
    @Override
    public List<Map<String,Object>> findRecordsFor(String table, int maxRecords) throws SQLException{
        String sql = "";
        
        if(maxRecords > 0){
           sql = "SELECT * FROM " + table + " LIMIT " + maxRecords;
        }else{
            sql = "SELECT  * FROM " + table;
        }
                
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        List<Map<String,Object>> results = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        
        int colCount = rsmd.getColumnCount();
        Map<String,Object> record = null;
        while(rs.next()){
            record = new LinkedHashMap<>();
            for(int colNum = 1; colNum <= colCount; colNum++){
                String colName = rsmd.getColumnName(colNum);
                record.put(colName, rs.getObject(colNum));
            }   
            results.add(record);
        }
        return results;
        
    }
    
    @Override
    public void insertRecord(String tableName, List<String> columnNames, List columnValues) throws SQLException{
       StringJoiner sJoin = new StringJoiner("," , "(" , ")");
       // Initial query string
       String sql = "INSERT INTO " + tableName + " ";
       
       // Add column names, comma separated with "()"
       for(String col : columnNames){
           sJoin.add(col);
        }
       sql += sJoin.toString();
       sql += " VALUES ";
       sJoin = new StringJoiner("," , "(" , ")");
       // Add values with "?" as placeholders
       for(Object val : columnValues){
           sJoin.add("?");
       }
       sql += sJoin.toString();
       
       PreparedStatement pstmt = conn.prepareStatement(sql);
       for(int i = 0 ; i < columnValues.size(); i++){
        pstmt.setObject(i+1, columnValues.get(i));   
       }

       int records = pstmt.executeUpdate();
    }
    
    @Override
     public int updateRecord(String tableName, List<String> columnNames, List colValues,
            String whereField, Object whereValue) throws SQLException{
        
        PreparedStatement pstmt = null;
        int recsUpdated = 0;
        
        StringBuffer sql = new StringBuffer("UPDATE ");
        (sql.append(tableName)).append(" SET ");
        for(Object column : columnNames){
            sql.append((String) column).append(" = ?, ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")));
        
        ((sql.append(" WHERE ")).append(whereField)).append(" = ?");
        
        String finishedSQL = sql.toString();
            
        pstmt = conn.prepareStatement(finishedSQL);
        int indexOfWhere = 1;
        for(int i = 0 ; i < colValues.size(); i++){
            pstmt.setObject(i+1, colValues.get(i));   
            indexOfWhere++;
        }
        pstmt.setObject(indexOfWhere, whereValue);
        recsUpdated = pstmt.executeUpdate();
        return recsUpdated;
    }

    @Override
    public int deleteRecordById(String table, String primaryKeyFieldName, Object Id) throws SQLException {       
        
        String sql = "DELETE FROM " + table + " WHERE " + primaryKeyFieldName + " = ?";
        PreparedStatement stmt = null;
        int recordsAffected;
        try{
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, Id);
            System.out.println(stmt);
            recordsAffected = stmt.executeUpdate();
        }catch(SQLException e){
             throw e;
        }
        return recordsAffected;
    }
    
    public static void main(String[] args) throws Exception {
        DbAccessor db = new MySqlDbAccessor();
        db.openConnection("com.mysql.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/book", 
                "root", "08rollec!");
        
        List<String> columnNames = new ArrayList<>(Arrays.asList("author_name", "date_added"));
        List<Object> colValues = new ArrayList<>(Arrays.asList("Joseph Heller 10" , new Date()));
       
        db.updateRecord("author", columnNames, colValues, "date_added", "2017-02-20");
  
        List<Map<String, Object>> records = db.findRecordsFor("author", 50);
        db.closeConnection();
        
       for(Map<String, Object> record : records){
            System.out.println(record);
        } 
    

    }


}
