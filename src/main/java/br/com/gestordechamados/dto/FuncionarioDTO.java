package br.com.gestordechamados.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FuncionarioDTO {

    private long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank(message = "Telefone não pode ser vazio.")
    private String telefone;

    private Boolean ativo;
}
