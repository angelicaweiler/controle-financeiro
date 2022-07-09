package com.papler.controlefinanceiro.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ValoresPagarInDTO implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataVencimento;
    private String tipoDebito;
    private BigDecimal valorDebito;
    private StatusPagamento statusPagamento;

}
