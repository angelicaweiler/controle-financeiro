package com.papler.controlefinanceiro.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.papler.controlefinanceiro.infraestructure.enums.StatusRecebimento;
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
public class ValoresReceberInDTO implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataRecebimento;
    private String tipoRecebimento;
    private BigDecimal valorRecebido;
    private StatusRecebimento statusRecebimento;

}
