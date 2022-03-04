package br.com.gestordechamados.controller;

import br.com.gestordechamados.converter.ConverterClasses;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.dto.ClienteDTO;
import br.com.gestordechamados.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
@Api(value = "API Rest Gerenciador de Chamados")
public class ClienteController {

    private ClienteService clienteService;
    private ConverterClasses converter;

    @ApiOperation(value = "Recurso para listar todos os clientes cadastrados.")
    @GetMapping(value = "/clientes")
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(converter.toCollectionModelClienteDTO(clienteService.findAll()));
    }

    @ApiOperation(value = "Recurso para buscar um cliente pelo id.")
    @GetMapping(value = "/clientes/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(converter.toModelClienteDTO(clienteService.findById(id)));
    }

    @ApiOperation(value = "Recurso para cadastrar um novo cliente.")
    @PostMapping(value = "/clientes")
    public ResponseEntity<ClienteDTO> add(@Valid @RequestBody ClienteDTO clienteDTO){
        var cliente = new Cliente();
        cliente = converter.toModelCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.toModelClienteDTO(clienteService.add(cliente)));
    }

}
