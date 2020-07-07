package com.guilherme.minhasfinancas.model.entity;

import lombok.*;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Builder
@Data
@Table(name = "lancamento")
public class Lancamento {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private Integer descricao;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_cadastro")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataCadastro;

    @Column(name = "tipo")
    @Enumerated(value = EnumType.STRING )
    private TipoLancamento tipoLancamento;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING )
    private StatusLancamento statusLancamento;


    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
