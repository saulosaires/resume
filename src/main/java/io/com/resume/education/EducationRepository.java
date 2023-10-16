package io.com.resume.education;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {


    @Query("""
            SELECT new Education(e.id,  e.school,  e.degree,  e.city,  e.description,  e.startDate,  e.endDate, e.language)\s
            FROM Education e\s
            WHERE e.user.id= :userId
            """)
    List<Education> findByUserId(@Param("userId") Long userId);

}
