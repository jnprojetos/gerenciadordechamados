package br.com.gestordechamados.model;

import br.com.gestordechamados.enums.SituacaoChamado;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroChamado;

    @Size(max = 255)
    private String observacao;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    @ManyToOne
    private Funcionario funcionario;

    @NotNull
    private LocalDate dataAbertura;

    @NotNull
    private SituacaoChamado situacao;

    @NotNull
    private LocalDate dataFinalizacao;

}
