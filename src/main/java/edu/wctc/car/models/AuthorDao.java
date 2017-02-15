/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chris
 */
public class AuthorDao implements IAuthorDao {
    private DbAccessor db;
    private String driverClass;
    private String url;
    private String username;
    private String password;

    public AuthorDao(DbAccessor db, String driverClass, String url, String username, String password) {
        setDb(db);
        setDriverClass(driverClass);
        setUrl(url);
        setUsername(username);
        setPassword(password);

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

    @Override
    public final String getDriverClass() {
        return driverClass;
    }

    @Override
    public final void setDriverClass(String driverClass) {
        if(driverClass != null){
            this.driverClass = driverClass;
        }
    }

    @Override
    public final String getUrl() {
        return url;
    }

    @Override
    public final void setUrl(String url) {
        if(url != null || url != ""){
            this.url = url;
        }
    }

    @Override
    public final String getUsername() {
        return username;
    }

    @Override
    public final void setUsername(String username) {
        if(!username.equals("") || username != null){
            this.username = username;
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public final void setPassword(String password) {
        if(password != null || !password.equals("")){
            this.password = password;            
        }
    }
    
    //This is written in terms of business not programming
    @Override
    public final List<Author> getAuthorList(String tableName, int maxRecords) throws ClassNotFoundException, SQLException{
        List<Author> authorList = new ArrayList<>();
        db.openConnection(driverClass, url, username, password);
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
        db.openConnection(driverClass, url, username, password);
        db.deleteRecordById(tableName, columnName, authorId);
        db.closeConnection();
    }    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorDao authorTest = new AuthorDao(
                new MySqlDbAccessor(), 
                "com.mysql.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/book",
                "root",
                "08rollec!");
        System.out.println("List Of Authors: ");
        authorTest.removeAuthorById("author", "author_id", 1);
        List<Author> authors = authorTest.getAuthorList("author", 50);
        for(Author author : authors){
            System.out.println(author);
        }
    }  
        


   
}
