package com.example.demo.repository;

import com.example.demo.entity.Users;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, String> {

    @Query("SELECT * FROM USERS WHERE LOGIN_ID = :loginId"
    )
    Optional<Users> findByLoginId(String loginId);
}
