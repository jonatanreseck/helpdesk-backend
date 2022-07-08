package com.jonatan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonatan.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    
}
