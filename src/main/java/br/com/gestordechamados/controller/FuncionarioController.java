package br.com.gestordechamados.controller;

import br.com.gestordechamados.dto.FuncionarioDTO;
import br.com.gestordechamados.model.Funcionario;
import br.com.gestordechamados.service.FuncionarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    @GetMapping(value = "/funcionarios")
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll());
    }

    @GetMapping(value = "/funcionarios/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findById(id));
    }

    @PostMapping(value = "/funcionarios")
    public ResponseEntity<Object> add(@Valid @RequestBody FuncionarioDTO funcionarioDTO){
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.add(funcionario));
    }
}
