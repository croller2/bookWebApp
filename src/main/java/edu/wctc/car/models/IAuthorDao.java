/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.models;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author chris
 */
public interface IAuthorDao {

    //This is written in terms of business not programming
    List<Author> getAuthorList(String tableName, int maxRecords) throws ClassNotFoundException, SQLException;

    void removeAuthorById(String tableName, String columnName , int authorId)throws ClassNotFoundException, SQLException;
    
    int updateAuthorRecord(String tableName, List<String> columnNames, List colValues,
            String whereField, Object whereValue)throws ClassNotFoundException, SQLException;
    
    void addAuthor(String tableName, List<String> columnNames, List columnValues) throws ClassNotFoundException, SQLException;
    
    DbAccessor getDb();

    String getDriverClass();

    String getPassword();

    String getUrl();

    String getUsername();

    void setDb(DbAccessor db);

    void setDriverClass(String driverClass);

    void setPassword(String password);

    void setUrl(String url);

    void setUsername(String username);
    
}
