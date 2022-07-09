package com.papler.controlefinanceiro.infraestructure.entities;

import com.papler.controlefinanceiro.infraestructure.enums.StatusPagamento;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "VALORES_A_PAGAR")
public class ValoresPagarEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private Date dataVencimento;

    @Column(name = "TIPO_CONTA", nullable = false, unique = true)
    private String tipoConta;

    @Column(name = "VALOR_CONTA", nullable = false)
    private BigDecimal valorConta;

    @Column(name = "DATA_LANÇAMENTO", nullable = false)
    private LocalDateTime dataLancamento;

    @Column(name = "STATUS_PAGAMENTO")
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @PrePersist
    public void preInclusao() {
        this.dataLancamento = LocalDateTime.now();
    }
    
}
