package com.papler.controlefinanceiro.api.dto;

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
public class ValoresReceberTotalOutDTO implements Serializable {

    private Date dataRecebimento;
    private String tipoRecebimento;
    private BigDecimal valorRecebido;
    private StatusRecebimento statusRecebimento;
}
