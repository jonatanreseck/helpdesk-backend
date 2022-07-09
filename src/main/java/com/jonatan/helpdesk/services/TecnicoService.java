package com.jonatan.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatan.helpdesk.domain.Tecnico;
import com.jonatan.helpdesk.repositories.TecnicoRepository;
import com.jonatan.helpdesk.services.Exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }
}
