package com.papler.controlefinanceiro.business.service.BO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TotalValoresDebitoBO {

    private BigDecimal totalValoresPagos;
    private BigDecimal totalValoresDevidos;
}
