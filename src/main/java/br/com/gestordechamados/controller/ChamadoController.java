package br.com.gestordechamados.controller;

import br.com.gestordechamados.dto.ChamadoDTO;
import br.com.gestordechamados.model.Chamado;
import br.com.gestordechamados.service.ChamadoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class ChamadoController {

    private ChamadoService chamadoService;

    @ApiOperation(value = "Recurso para registrar um chamado.")
    @PostMapping(value = "/chamados")
    public ResponseEntity<Object> add(@Valid @RequestBody ChamadoDTO chamadoDTO){
        var chamado = new Chamado();
        BeanUtils.copyProperties(chamadoDTO, chamado);
        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoService.add(chamado));
    }

    @ApiOperation(value = "Recurso atualiza a situação de um chamado.")
    @PutMapping(value = "/chamados/{numero_chamado}/atualizar")
    public ResponseEntity<Object> updateSituation(@PathVariable(value = ("numero_chamado")) Long numeroChamado){
        var chamado = chamadoService.findById(numeroChamado);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(chamadoService.updateSituation(chamado));
    }

    @ApiOperation(value = "Recurso para listar todos dos chamados.")
    @GetMapping(value = "/chamados")
    public ResponseEntity<List<Chamado>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(chamadoService.findAll());
    }
}
