package br.com.gestordechamados.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_func;

    @NotNull
    private Long matricula;

    @NotNull
    @Size(min = 5, max = 100)
    private String nome_func;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(max = 15)
    private String telefone;

    @NotNull
    private boolean ativo;

}
