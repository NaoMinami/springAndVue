package com.example.demo.repository;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    @Query(value ="SELECT * FROM USERS WHERE LOGIN_ID = :login_id"
        ,nativeQuery = true
    )
    Optional<Users> findByLoginId(@Param("login_id") String loginId);
}
