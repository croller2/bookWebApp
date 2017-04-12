/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.models;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author chris
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "edu.wctc.car_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }

    public int deleteByID(String id) {
        try {
            String jpql = "DELETE FROM book b WHERE b.bookId = :id";
            Query query = this.getEntityManager().createQuery(jpql);
            query.setParameter("id", Integer.parseInt(id));
            return query.executeUpdate();
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    public void updateBook(String id, String title, Author author, String ISBN) {
        Book b = this.find(Integer.parseInt(id));
        b.setIsbn(ISBN);
        b.setTitle(title);
        b.setAuthor(author);
        this.edit(b);
    }

    public void addNewBook(String bookName, String ISBN, Author author) {
        Book b = new Book();
        b.setTitle(bookName);
        b.setIsbn(ISBN);
        b.setAuthor(author);
        this.create(b);
    }
}
