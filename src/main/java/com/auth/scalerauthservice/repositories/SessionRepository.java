package com.auth.scalerauthservice.repositories;

import com.auth.scalerauthservice.models.Session;
import com.auth.scalerauthservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByUser(User user);
}
