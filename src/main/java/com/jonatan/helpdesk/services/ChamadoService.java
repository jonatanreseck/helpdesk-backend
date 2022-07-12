package com.jonatan.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatan.helpdesk.domain.Chamado;
import com.jonatan.helpdesk.repositories.ChamadoRepository;
import com.jonatan.helpdesk.services.Exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
    
    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }
}
