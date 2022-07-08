package com.jonatan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonatan.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    
}
