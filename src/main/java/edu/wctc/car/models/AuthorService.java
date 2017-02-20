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

/**
 *
 * @author chris
 */
public class AuthorService {
    private ArrayList<Author> listOfAuthors  = new ArrayList<>();
    private IAuthorDao dao;
    
    public AuthorService(IAuthorDao dao){
        setDao(dao);
    }

    public AuthorService() {
        
    }
    
    public final IAuthorDao getDao() {
        return dao;
    }

    public final void setDao(IAuthorDao dao) {
        if(dao != null){
            this.dao = dao;            
        }
    }
     
    public final void removeAuthor(String tableName, String columnName, int id) throws ClassNotFoundException, SQLException{
        dao.removeAuthorById(tableName, columnName, id);
    }
    
    public final List<Author> getListOfAuthors(String tableName, int maxRecords) throws ClassNotFoundException, SQLException {
        return dao.getAuthorList(tableName, maxRecords);
    }
    
    public final void addAuthor(String tableName, List<String> columnNames, List columnValues) throws ClassNotFoundException, SQLException{
        dao.addAuthor(tableName, columnNames, columnValues);
    }

    public final int updateAuthor(String tableName, List<String> columnNames, List colValues,
            String whereField, Object whereValue) throws SQLException, ClassNotFoundException{
        return dao.updateAuthorRecord(tableName, columnNames, colValues, whereField, whereValue);
    }
 
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService authServ = new AuthorService(
                new AuthorDao(
                    new MySqlDbAccessor(), 
                    "com.mysql.jdbc.Driver", 
                    "jdbc:mysql://localhost:3306/book",
                    "root",
                    "08rollec!")
        );
                List<String> columnNames = new ArrayList<>(Arrays.asList("author_name", "date_added"));
        List<Object> colValues = new ArrayList<>(Arrays.asList("Joseph Heller 8" , new Date()));
        authServ.updateAuthor("author", columnNames, colValues, "author_id", 5);
        List<Author> authors = authServ.getListOfAuthors("author" , 50);
        for(Author author : authors){
            System.out.println(author);
        }
    }
}
