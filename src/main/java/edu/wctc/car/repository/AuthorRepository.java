package edu.wctc.car.repository;

import edu.wctc.car.entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable  {
    
}
