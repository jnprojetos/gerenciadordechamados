package br.com.gestordechamados.dto;

import br.com.gestordechamados.model.Cliente;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class ClienteDTO {
    private long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String telefone;

    private LocalDate dataNascimento;

    @NotBlank
    private String email;

}
