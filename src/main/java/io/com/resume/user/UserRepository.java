package io.com.resume.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT new User(u.id,u.name,u.photoUrl,u.summary,u.email,u.telephone,u.website,u.instantMessaging,u.birthDate,u.country)\s
            FROM User u\s
            WHERE u.id= :userId""")
    Optional<User> findThinUser(@Param("userId") Long userId);


    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
