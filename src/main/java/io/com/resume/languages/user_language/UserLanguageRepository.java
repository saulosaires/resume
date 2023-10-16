package io.com.resume.languages.user_language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {

    @Query("""
            SELECT new UserLanguage(ul.id, ul.language, ul.level)\s
            FROM UserLanguage ul\s
            WHERE ul.id= :userId
            """)
    List<UserLanguage> findByUserId(@Param("userId") Long userId);

}
