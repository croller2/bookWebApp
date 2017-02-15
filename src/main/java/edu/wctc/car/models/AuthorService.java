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

    public final void removeAuthor(String tableName, String columnName, int id) throws ClassNotFoundException, SQLException{
        dao.removeAuthorById(tableName, columnName, id);
    }
    public final List<Author> getListOfAuthors(String tableName, int maxRecords) throws ClassNotFoundException, SQLException {
        return dao.getAuthorList(tableName, maxRecords);
    }

    public final void setListOfAuthors(ArrayList<Author> listOfAuthors) {
        this.listOfAuthors = listOfAuthors;
    }

    public final IAuthorDao getDao() {
        return dao;
    }

    public final void setDao(IAuthorDao dao) {
        if(dao != null){
            this.dao = dao;            
        }
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
        List<Author> authors = authServ.getListOfAuthors("author" , 50);
        for(Author author : authors){
            System.out.println(author);
        }
    }
}
