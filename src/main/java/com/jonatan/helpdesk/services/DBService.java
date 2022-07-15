package com.jonatan.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "767.410.020-06", "valdir@email.com", encoder.encode("123"));
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "926.765.840-90", "ltorvads@email.com", encoder.encode("123"));
        cli1.addPerfil(Perfil.CLIENTE);

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
