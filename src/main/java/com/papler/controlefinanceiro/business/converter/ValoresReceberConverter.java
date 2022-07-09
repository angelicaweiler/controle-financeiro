package com.papler.controlefinanceiro.business.converter;

import com.papler.controlefinanceiro.api.dto.ValoresReceberInDTO;
import com.papler.controlefinanceiro.api.dto.ValoresReceberOutDTO;
import com.papler.controlefinanceiro.api.dto.ValoresReceberTotalOutDTO;
import com.papler.controlefinanceiro.business.service.BO.TotalValoresCreditoBO;
import com.papler.controlefinanceiro.infraestructure.entities.ValoresReceberEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ValoresReceberConverter {

    public ValoresReceberEntity paraEntity(ValoresReceberInDTO dto) {
        return ValoresReceberEntity.builder()
                .dataRecebimento(dto.getDataRecebimento())
                .tipoRecebimento(dto.getTipoRecebimento().toUpperCase())
                .valorRecebido(dto.getValorRecebido())
                .statusRecebimento(dto.getStatusRecebimento())
                .build();
    }

    public ValoresReceberOutDTO paraDTO(ValoresReceberEntity entity, BigDecimal valorTotal, BigDecimal valoresAReceber) {
        return ValoresReceberOutDTO.builder()
                .dataRecebimento(entity.getDataRecebimento())
                .tipoRecebimento(entity.getTipoRecebimento().toUpperCase())
                .valorRecebido(entity.getValorRecebido())
                .statusRecebimento(entity.getStatusRecebimento())
                .valorTotalRecebido(valorTotal)
                .valoresReceber(valoresAReceber)
                .build();
    }

    public ValoresReceberTotalOutDTO paraValoresReceberDTO(ValoresReceberEntity entity) {
        return ValoresReceberTotalOutDTO.builder()
                .dataRecebimento(entity.getDataRecebimento())
                .tipoRecebimento(entity.getTipoRecebimento().toUpperCase())
                .valorRecebido(entity.getValorRecebido())
                .statusRecebimento(entity.getStatusRecebimento())
                .build();
    }


    public List<ValoresReceberTotalOutDTO> paraListaValoresaReceberOutDTO(List<ValoresReceberEntity> entity) {

        return entity.stream().map((this::paraValoresReceberDTO)).collect(Collectors.toList());
    }


    public TotalValoresCreditoBO paraBO (BigDecimal totalRecebido, BigDecimal totalAReceber) {
        return TotalValoresCreditoBO.builder()
                .totalValoresRecebidos(totalRecebido)
                .totalValoresAReceber(totalAReceber)
                .build();
    }

}
