package com.jonatan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonatan.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
