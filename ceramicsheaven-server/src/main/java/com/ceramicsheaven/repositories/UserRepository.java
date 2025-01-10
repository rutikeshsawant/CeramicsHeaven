package com.ceramicsheaven.repositories;

import com.ceramicsheaven.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findByEmail(String email);

     @Query("SELECT u FROM User u WHERE u.id = :id")
     User findUserById(@Param("id") Long id);


}
