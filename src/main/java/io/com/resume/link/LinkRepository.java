package io.com.resume.link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query("SELECT new Link(l.id,l.label,l.url) FROM Link l WHERE l.id= :userId")
    List<Link> findByUserId(@Param("userId") Long userId);

}
