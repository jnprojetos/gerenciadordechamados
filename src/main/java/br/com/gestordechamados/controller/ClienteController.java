package br.com.gestordechamados.controller;

import br.com.gestordechamados.converter.ConverterClasses;
import br.com.gestordechamados.exceptions.ObjectBadRequestException;
import br.com.gestordechamados.exceptions.ObjectNotFoundException;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.dto.ClienteDTO;
import br.com.gestordechamados.service.ChamadoService;
import br.com.gestordechamados.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/clientes")
@AllArgsConstructor
@Api(value = "URI Clientes", tags = {"Clientes"})
public class ClienteController {

    private ClienteService clienteService;
    private ConverterClasses converter;
    private ChamadoService chamadoService;

    @ApiOperation(value = "Listar todos os clientes cadastrados.")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(converter.toCollectionModelClienteDTO(clienteService.findAll()));
    }

    @ApiOperation(value = "Buscar um cliente pelo id.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(converter.toModelClienteDTO(clienteService.findById(id)));
    }

    @ApiOperation(value = "Cadastrar um novo cliente.")
    @PostMapping
    public ResponseEntity<ClienteDTO> add(@Valid @RequestBody ClienteDTO clienteDTO){
        var cliente = new Cliente();
        cliente = converter.toModelCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.toModelClienteDTO(clienteService.add(cliente)));
    }

    @ApiOperation(value = "Alterar o um cliente")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ClienteDTO clienteDTO){
        var cliente = clienteService.findById(id);
        cliente.setNome(clienteDTO.getNome());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());

        return ResponseEntity.status(HttpStatus.OK).body(converter.toModelClienteDTO(clienteService.update(cliente)));
    }

    @ApiOperation(value = "Exclui um cliente")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        var cliente = clienteService.findById(id);
        var chamado = chamadoService.findByCliente(cliente);
        if (chamado != null){
            throw new ObjectBadRequestException("Cliente não pode ser excluido.");
        }
        clienteService.delete(cliente);
    }

}
