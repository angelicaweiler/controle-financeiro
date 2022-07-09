package com.papler.controlefinanceiro.infraestructure.entities;

import com.papler.controlefinanceiro.infraestructure.enums.StatusRecebimento;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "VALORES_A_RECEBER")
public class ValoresReceberEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_RECEBIMENTO", nullable = false)
    private Date dataRecebimento;

    @Column(name = "TIPO_RECEBIMENTO", nullable = false, unique = true)
    private String tipoRecebimento;

    @Column(name = "VALOR_RECEBIDO", nullable = false)
    private BigDecimal valorRecebido;

    @Column(name = "DATA_LANÇAMENTO", nullable = false)
    private LocalDateTime dataLancamento;

    @Column(name = "STATUS_RECEBIMENTO")
    @Enumerated(EnumType.STRING)
    private StatusRecebimento statusRecebimento;

    @PrePersist
    public void preInclusao() {
        this.dataLancamento = LocalDateTime.now();
    }

}
