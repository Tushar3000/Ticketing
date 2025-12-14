package com.tushar.ticket.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tushar.ticket.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,UUID>{

    
}