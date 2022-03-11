package br.com.gestordechamados.controller;

import br.com.gestordechamados.converter.ConverterClasses;
import br.com.gestordechamados.mensageria.constants.RabbitConstants;
import br.com.gestordechamados.model.Chamado;
import br.com.gestordechamados.model.input.ChamadoInput;
import br.com.gestordechamados.dto.ChamadoDTO;
import br.com.gestordechamados.service.ChamadoService;
import br.com.gestordechamados.service.ClienteService;
import br.com.gestordechamados.service.RabbitmqService;
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
    private RabbitmqService rabbitmqService;
    private ClienteService clienteService;

    @ApiOperation(value = "Abrir um novo chamado")
    @PostMapping
    public ResponseEntity<ChamadoDTO> add(@Valid @RequestBody ChamadoInput chamadoInput){
        var chamado = new Chamado();
        chamado = converter.toModelChamado(chamadoInput);
        var chamadoCommit = chamadoService.add(chamado);
        rabbitmqService.enviaMensagem(RabbitConstants.FILA_CHAMADO,chamadoCommit);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.toModelChamadoDTO(chamadoCommit));
    }

    @ApiOperation(value = "Finalizar um chamado")
    @PutMapping(value = "/finalizar")
    public ResponseEntity<ChamadoDTO> finalize(@RequestBody ChamadoDTO chamadoDTO){
        var chamadoFinalizar = chamadoService.finalize(chamadoDTO);
        rabbitmqService.enviaMensagem(RabbitConstants.FILA_CHAMADO,chamadoFinalizar);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(converter.toModelChamadoDTO(chamadoFinalizar));
    }

    @ApiOperation(value = "Listar todos dos chamados.")
    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(converter.toCollectionModelChamadoDTO(chamadoService.findAll()));
    }

    @ApiOperation(value = "Buscar chamado por numero")
    @GetMapping(value = "/{numero_chamado}")
    public ResponseEntity<ChamadoDTO> findByNumeroChamado(@PathVariable(value = "numero_chamado") Long numero_chamado){
        return ResponseEntity.status(HttpStatus.OK).body(converter.toModelChamadoDTO(chamadoService.findByNumeroChamado(numero_chamado)));
    }

    @ApiOperation(value = "Lista chamados por cliente")
    @GetMapping(value = "/cliente/{id_cliente}")
    public ResponseEntity<List<ChamadoDTO>> findByCliente(@PathVariable(value = "id_cliente") Long idCliente){
        var cliente = clienteService.findById(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(converter.toCollectionModelChamadoDTO(chamadoService.findByCliente(cliente)));
    }
}
