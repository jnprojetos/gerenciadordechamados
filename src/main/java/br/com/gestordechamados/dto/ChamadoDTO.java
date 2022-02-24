package br.com.gestordechamados.dto;

import br.com.gestordechamados.enums.SituacaoChamado;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.model.Funcionario;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class ChamadoDTO {

    private Long numeroChamado;

    @NotBlank
    private String observacao;

    @NotBlank
    private Cliente cliente;

    @NotBlank
    private Funcionario funcionario;

    @NotBlank
    private LocalDate dataAbertura;

    @NotBlank
    private SituacaoChamado situacao;

    private LocalDate dataFinalizacao;
}
