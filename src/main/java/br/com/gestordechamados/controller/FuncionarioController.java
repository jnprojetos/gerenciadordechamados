package br.com.gestordechamados.controller;

import br.com.gestordechamados.converter.ConverterClasses;
import br.com.gestordechamados.model.Funcionario;
import br.com.gestordechamados.dto.FuncionarioDTO;
import br.com.gestordechamados.service.FuncionarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class FuncionarioController {

    private FuncionarioService funcionarioService;
    private ConverterClasses converter;

    @GetMapping(value = "/funcionarios")
    public ResponseEntity<List<FuncionarioDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(converter.toCollectionModelFuncionarioDTO(funcionarioService.findAll()));
    }

    @GetMapping(value = "/funcionarios/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(converter.toModelFuncionarioDTO(funcionarioService.findById(id)));
    }

    @PostMapping(value = "/funcionarios")
    public ResponseEntity<FuncionarioDTO> add(@Valid @RequestBody FuncionarioDTO funcionarioDTO){
        var funcionario = new Funcionario();
        funcionario = converter.toModelFuncionarioDTO(funcionarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.toModelFuncionarioDTO(funcionarioService.add(funcionario)));
    }
}
