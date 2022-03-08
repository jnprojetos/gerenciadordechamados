package br.com.gestordechamados.controller;

import br.com.gestordechamados.converter.ConverterClasses;
import br.com.gestordechamados.exceptions.ObjectBadRequestException;
import br.com.gestordechamados.model.Funcionario;
import br.com.gestordechamados.dto.FuncionarioDTO;
import br.com.gestordechamados.service.ChamadoService;
import br.com.gestordechamados.service.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/funcionarios")
@Api(tags = {"Funcionários"})
public class FuncionarioController {

    private FuncionarioService funcionarioService;
    private ConverterClasses converter;
    private ChamadoService chamadoService;

    @ApiOperation(value = "Cadastrar um novo funcionário")
    @PostMapping
    public ResponseEntity<FuncionarioDTO> add(@Valid @RequestBody FuncionarioDTO funcionarioDTO){
        var funcionario = new Funcionario();
        funcionario = converter.toModelFuncionarioDTO(funcionarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.toModelFuncionarioDTO(funcionarioService.add(funcionario)));
    }

    @ApiOperation(value = "Alterar um funcionário")
    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable(value = "id") Long id, @RequestBody FuncionarioDTO funcionarioDTO){
        var funcionario = funcionarioService.findById(id);
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setTelefone(funcionarioDTO.getTelefone());
        funcionario.setAtivo(funcionarioDTO.getAtivo());

        return ResponseEntity.status(HttpStatus.OK).body(converter.toModelFuncionarioDTO(funcionarioService.update(funcionario)));
    }

    @ApiOperation(value = "Listar todos os funcionários")
    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(converter.toCollectionModelFuncionarioDTO(funcionarioService.findAll()));
    }

    @ApiOperation(value = "Buscar um funcionário pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(converter.toModelFuncionarioDTO(funcionarioService.findById(id)));
    }

    @ApiOperation(value = "Excluir um funcionário")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        var funcionario = funcionarioService.findById(id);
        var chamado = chamadoService.findByFuncionario(funcionario);
        if (chamado != null){
            throw new ObjectBadRequestException("Funcionário não pode ser excluido.");
        }
        funcionarioService.delete(funcionario);
    }
}
