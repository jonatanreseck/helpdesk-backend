package com.jonatan.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jonatan.helpdesk.domain.Tecnico;
import com.jonatan.helpdesk.dtos.TecnicoDto;
import com.jonatan.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
    
    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDto(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDto>> findAll(){
        List<Tecnico> list = service.findAll();
        List<TecnicoDto> listDto = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok(listDto);
    }

    @PostMapping
    public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto objDto){
        Tecnico newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDto> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDto objDto){
        Tecnico obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDto(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
