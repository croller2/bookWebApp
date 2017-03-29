
package edu.wctc.car.models;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author chris
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "edu.wctc.car_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
    public int deleteByID(String id){
        try{  
            String jpql = "DELETE a FROM Author a WHERE a.authorId = :id";
            Query query = this.getEntityManager().createQuery(jpql);
            query.setParameter("id", Integer.parseInt(id));
            return query.executeUpdate();    
        }catch(NumberFormatException ex){
            return -1;
        }
    }
    
    public void addNewAuthor(String name){
        Author a = new Author();
        a.setAuthorName(name);
        a.setDateAdded(new Date());
        this.create(a);
    }
    
    public void update(String id, String name){
        Author a = this.find(Integer.parseInt(id));
        a.setAuthorName(name);
        this.edit(a);
    }
    
}
