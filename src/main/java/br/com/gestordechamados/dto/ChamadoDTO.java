package br.com.gestordechamados.dto;

import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.model.Funcionario;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChamadoDTO {

    @NotBlank
    private String observacao;

    private Cliente cliente;

    private Funcionario funcionario;
}
