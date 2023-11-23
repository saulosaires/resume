package io.com.resume.experience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    @Query("""
            SELECT new Experience(e.id,e.title,e.companyName, e.startDate, e.endDate, e.description,e.url, e.country, e.language)\s
            FROM Experience e\s
            WHERE e.user.id= :userId""")
    List<Experience> findByUser_Id(@Param("userId") Long userId);

}
