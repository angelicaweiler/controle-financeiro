package com.papler.controlefinanceiro.business.converter;

import com.papler.controlefinanceiro.api.dto.*;
import com.papler.controlefinanceiro.business.service.BO.TotalValoresCreditoBO;
import com.papler.controlefinanceiro.business.service.BO.TotalValoresDebitoBO;
import com.papler.controlefinanceiro.infraestructure.entities.ValoresPagarEntity;
import com.papler.controlefinanceiro.infraestructure.entities.ValoresReceberEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ValoresPagarConverter {

    public ValoresPagarEntity paraEntity(ValoresPagarInDTO dto) {
        return ValoresPagarEntity.builder()
                .dataVencimento(dto.getDataVencimento())
                .valorConta(dto.getValorDebito())
                .tipoConta(dto.getTipoDebito().toUpperCase())
                .statusPagamento(dto.getStatusPagamento())
                .build();
    }

    public ValoresPagarOutDTO paraDTO(ValoresPagarEntity entity, BigDecimal valorTotalDevido, BigDecimal valorTotalPago) {
        return ValoresPagarOutDTO.builder()
                .dataVencimento(entity.getDataVencimento())
                .valorDebito(entity.getValorConta())
                .tipoDebito(entity.getTipoConta().toUpperCase())
                .statusPagamento(entity.getStatusPagamento())
                .valorTotalDevido(valorTotalDevido)
                .valorTotalPago(valorTotalPago)
                .build();
    }

    public ValoresPagarTodosOutDTO paraValoresPagarTodosOutDto(ValoresPagarEntity entity) {
        return ValoresPagarTodosOutDTO.builder()
                .dataVencimento(entity.getDataVencimento())
                .valorDebito(entity.getValorConta())
                .tipoDebito(entity.getTipoConta().toUpperCase())
                .statusPagamento(entity.getStatusPagamento())
                .build();
    }


    public List<ValoresPagarTodosOutDTO> paraListaValoresaPagarOutDTO(List<ValoresPagarEntity> entity) {

        return entity.stream().map((this::paraValoresPagarTodosOutDto)).collect(Collectors.toList());
    }

    public TotalValoresDebitoBO paraBO (BigDecimal totalDevido, BigDecimal totalPago) {
        return TotalValoresDebitoBO.builder()
                .totalValoresDevidos(totalDevido)
                .totalValoresPagos(totalPago)
                .build();
    }


}
