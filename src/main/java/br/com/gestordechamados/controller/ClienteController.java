package br.com.gestordechamados.controller;

import br.com.gestordechamados.dto.ClienteDTO;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/clientes")
@AllArgsConstructor
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody ClienteDTO clienteDTO){
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.add(cliente));
    }
}
