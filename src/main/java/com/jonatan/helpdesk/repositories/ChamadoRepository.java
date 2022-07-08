package com.jonatan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonatan.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
    
}
