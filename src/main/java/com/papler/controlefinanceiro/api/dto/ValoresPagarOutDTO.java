package com.papler.controlefinanceiro.api.dto;

import com.papler.controlefinanceiro.infraestructure.enums.StatusPagamento;
import com.papler.controlefinanceiro.infraestructure.enums.StatusRecebimento;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ValoresPagarOutDTO implements Serializable {

    private Date dataVencimento;
    private String tipoDebito;
    private BigDecimal valorDebito;
    private StatusPagamento statusPagamento;
    private BigDecimal valorTotalDevido;
    private BigDecimal valorTotalPago;


}
