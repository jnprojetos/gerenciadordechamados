package br.com.gestordechamados.model.input;

import br.com.gestordechamados.dto.ClienteDTO;
import br.com.gestordechamados.model.Cliente;
import br.com.gestordechamados.model.Funcionario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ChamadoInput {

    private Cliente cliente;
    private Funcionario funcionario;

    @NotBlank
    private String descricao;

    private String observacao;
}
