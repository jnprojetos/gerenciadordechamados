package br.com.gestordechamados.dto;

import br.com.gestordechamados.enums.SituacaoChamado;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.model.Funcionario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChamadoDTO {

    private Long numeroChamado;

    private JsonNode cliente;

    private Funcionario funcionario;

    private String descricao;

    private String observacao;

    private LocalDate dataAbertura;

    private SituacaoChamado situacao;

    private LocalDate dataFinalizacao;
}
