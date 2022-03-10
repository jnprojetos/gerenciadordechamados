package br.com.gestordechamados.controller;

import br.com.gestordechamados.converter.ConverterClasses;
import br.com.gestordechamados.model.Chamado;
import br.com.gestordechamados.model.input.ChamadoInput;
import br.com.gestordechamados.dto.ChamadoDTO;
import br.com.gestordechamados.service.ChamadoService;
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
@RequestMapping(value = "/api/v1/chamados")
@Api(tags = {"Chamados"})
public class ChamadoController {

    private ChamadoService chamadoService;
    private ConverterClasses converter;


    @ApiOperation(value = "Abrir um novo chamado")
    @PostMapping
    public ResponseEntity<ChamadoDTO> add(@Valid @RequestBody ChamadoInput chamadoInput){
        var chamado = new Chamado();
        chamado = converter.toModelChamado(chamadoInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.toModelChamadoDTO(chamadoService.add(chamado)));
    }

    @ApiOperation(value = "Finalizar um chamado")
    @PutMapping(value = "/finalizar")
    public ResponseEntity<ChamadoDTO> finalize(@RequestBody ChamadoDTO chamadoDTO){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(converter.toModelChamadoDTO(chamadoService.finalize(chamadoDTO)));
    }

    @ApiOperation(value = "Listar todos dos chamados.")
    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(converter.toCollectionModelChamadoDTO(chamadoService.findAll()));
    }


}
