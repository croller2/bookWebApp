/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.models;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chris
 */
public interface DbAccessor {

    void closeConnection() throws SQLException;

    List<Map<String, Object>> findRecordsFor(String table, int maxRecords) throws SQLException;
    
    int deleteRecordById(String table, String columnName, Object Id) throws SQLException;

    void insertRecord(String tableName, List<String> columnNames, List columnValues) throws SQLException;

    // Consider creating a custom exception
    void openConnection(String driverClass, String Url, String userName, String password) throws ClassNotFoundException, SQLException;
    
    int updateRecords(String tableName, List columnNames, List colValues,
            String whereField, Object whereValue)throws SQLException;
    
}
