package br.com.gestordechamados.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FuncionarioDTO {

    private long id;

    private long matricula;

    @NotBlank
    private String nome_func;

    @NotBlank
    private String email;

    @NotBlank(message = "Telefone não pode ser vazio.")
    private String telefone;

    private boolean ativo;
}
