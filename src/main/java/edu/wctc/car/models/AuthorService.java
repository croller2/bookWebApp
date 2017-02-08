/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chris
 */
public class AuthorService {
    private ArrayList<Author> listOfAuthors  = new ArrayList<>();
    
    public AuthorService(){
        //Doing this the long way so that I can easily change
        
        //Create Authors
        Author author1 = new Author(123 , "Joseph Heller", Calendar.getInstance());
        Author author2 = new Author(456, "John Scazi", Calendar.getInstance());
        Author author3 = new Author(678 , "Phillip Pullman" , Calendar.getInstance());
        
        //Add them to the list
        listOfAuthors.add(author1);
        listOfAuthors.add(author2);
        listOfAuthors.add(author3);

    }
    
    
    public final ArrayList<Author> getAuthors(){
        return listOfAuthors;
    }
}
