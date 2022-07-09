package com.papler.controlefinanceiro.business.service.BO;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TotalValoresCreditoBO {

    private BigDecimal totalValoresRecebidos;
    private BigDecimal totalValoresAReceber;
}
