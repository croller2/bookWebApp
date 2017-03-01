/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author chris
 */
public class ConnPoolAuthorDao implements IAuthorDao {
    private DataSource ds;
    private DbAccessor db;

    public ConnPoolAuthorDao(DataSource ds, DbAccessor db) {
        this.db = db;
        this.ds = ds;

    }

    @Override
    public final DbAccessor getDb() {
        return db;
    }

    @Override
    public final void setDb(DbAccessor db) {
        if(db != null){
            this.db = db;
        }
    }

    //This is written in terms of business not programming
    @Override
    public final List<Author> getAuthorList(String tableName, int maxRecords) throws ClassNotFoundException, SQLException{
        List<Author> authorList = new ArrayList<>();
        db.openConnection(ds);
        List<Map<String,Object>> rawData = db.findRecordsFor(tableName, maxRecords);
        db.closeConnection();
            
        for(Map<String,Object> recData : rawData){
            authorList.add(new Author( 
                    (Integer)recData.get("author_id"), 
                    (String)recData.get("author_name") , 
                    (Date)recData.get("date_added")));
            }    
        return authorList; 
    }
    
    @Override
    public void removeAuthorById(String tableName, String columnName, int authorId) throws ClassNotFoundException, SQLException {
        db.openConnection(ds);
        db.deleteRecordById(tableName, columnName, authorId);
        db.closeConnection();
    }    
    
    @Override
    public int updateAuthorRecord(String tableName, List<String> columnNames, List colValues, String whereField, Object whereValue) throws ClassNotFoundException, SQLException {
        db.openConnection(ds);
        int recordsUpdated = db.updateRecord(tableName, columnNames, colValues, whereField, whereValue);
        db.closeConnection();
        return recordsUpdated;
    }
    
    @Override
    public void addAuthor(String tableName, List<String> columnNames, List columnValues) throws ClassNotFoundException, SQLException {
        db.openConnection(ds);
        db.insertRecord(tableName, columnNames, columnValues);
        db.closeConnection();

    }

}
