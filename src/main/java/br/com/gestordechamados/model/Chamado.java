package br.com.gestordechamados.model;

import br.com.gestordechamados.CustomLocalDateSerializer;
import br.com.gestordechamados.enums.SituacaoChamado;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    private String descricao;

    private String observacao;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    @ManyToOne
    private Funcionario funcionario;

    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate dataAbertura;

    private SituacaoChamado situacao;

    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate dataFinalizacao;

}
