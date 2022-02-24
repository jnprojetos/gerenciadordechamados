package br.com.gestordechamados.controller;

import br.com.gestordechamados.dto.ClienteDTO;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Tag(name = "Clientes", description = "Clientes")
@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
@Api(value = "API Rest Gerenciador de Chamados")

public class ClienteController {

    private ClienteService clienteService;

    @ApiOperation(value = "Recurso para listar todos os clientes cadastrados.")
    @GetMapping(value = "/clientes")
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @ApiOperation(value = "Recurso para buscar um cliente pelo id.")
    @GetMapping(value = "/clientes/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @ApiOperation(value = "Recurso para cadastrar um novo cliente.")
    @PostMapping(value = "/clientes")
    public ResponseEntity<Object> add(@Valid @RequestBody ClienteDTO clienteDTO){
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.add(cliente));
    }
}
