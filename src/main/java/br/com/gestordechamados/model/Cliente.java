package br.com.gestordechamados.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @NotNull
    @Size(min = 5, max = 100)
    private String nome;

    @NotNull
    @CPF
    private String cpf;

    @NotNull
    @Size(max = 15)
    private String telefone;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    @Email
    private String email;

}
