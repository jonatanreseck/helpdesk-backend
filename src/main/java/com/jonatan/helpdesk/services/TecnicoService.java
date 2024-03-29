package com.jonatan.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jonatan.helpdesk.domain.Pessoa;
import com.jonatan.helpdesk.domain.Tecnico;
import com.jonatan.helpdesk.dtos.TecnicoDto;
import com.jonatan.helpdesk.repositories.PessoaRepository;
import com.jonatan.helpdesk.repositories.TecnicoRepository;
import com.jonatan.helpdesk.services.Exceptions.DataIntegratyViolationException;
import com.jonatan.helpdesk.services.Exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;
    
    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDto objDto) {
        objDto.setId(null);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        validaPorCpfEEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, @Valid TecnicoDto objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);

        if(objDto.getSenha().equals(oldObj.getSenha())) {
        	objDto.setSenha(encoder.encode(objDto.getSenha()));
        }

        validaPorCpfEEmail(objDto);
        oldObj = new Tecnico(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getChamados().size()>0){
            throw new DataIntegratyViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDto objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegratyViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema!");
        }

        
    }

}
