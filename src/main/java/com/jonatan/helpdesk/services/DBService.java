package com.jonatan.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatan.helpdesk.domain.Chamado;
import com.jonatan.helpdesk.domain.Cliente;
import com.jonatan.helpdesk.domain.Tecnico;
import com.jonatan.helpdesk.domain.enums.Perfil;
import com.jonatan.helpdesk.domain.enums.Prioridade;
import com.jonatan.helpdesk.domain.enums.Status;
import com.jonatan.helpdesk.repositories.ChamadoRepository;
import com.jonatan.helpdesk.repositories.ClienteRepository;
import com.jonatan.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "63653230268", "valdir@email.com", "senha123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "80527947580", "ltorvads@email.com", "senha123");
        cli1.addPerfil(Perfil.CLIENTE);

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
