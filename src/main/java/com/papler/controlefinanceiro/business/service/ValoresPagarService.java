package com.papler.controlefinanceiro.business.service;

import com.papler.controlefinanceiro.api.dto.*;
import com.papler.controlefinanceiro.business.converter.ValoresPagarConverter;
import com.papler.controlefinanceiro.business.service.BO.TotalValoresCreditoBO;
import com.papler.controlefinanceiro.business.service.BO.TotalValoresDebitoBO;
import com.papler.controlefinanceiro.infraestructure.entities.ValoresPagarEntity;
import com.papler.controlefinanceiro.infraestructure.entities.ValoresReceberEntity;
import com.papler.controlefinanceiro.infraestructure.enums.StatusPagamento;
import com.papler.controlefinanceiro.infraestructure.enums.StatusRecebimento;
import com.papler.controlefinanceiro.infraestructure.repositories.ValoresPagarRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ValoresPagarService {

    private final ValoresPagarRepository repository;
    private final ValoresPagarConverter converter;

    public ValoresPagarService(ValoresPagarRepository repository, ValoresPagarConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    public ValoresPagarOutDTO cadastrarValores(ValoresPagarInDTO dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException();
        }
        ValoresPagarEntity entity = converter.paraEntity(dto);
        repository.save(entity);
        buscarValores();
        return converter.paraDTO(entity, buscarTotalValoresAPagar(), buscarTotalValoresPagos());
    }

    public TotalValoresDebitoBO buscarValores() {
        return converter.paraBO(buscarTotalValoresAPagar(), buscarTotalValoresPagos());
    }

    private BigDecimal buscarTotalValoresAPagar() {

        List<ValoresPagarEntity> listEntity = (List<ValoresPagarEntity>) repository.findAll();

        return listEntity.stream()
                .filter(entity -> entity.getStatusPagamento().equals(StatusPagamento.PENDENTE))
                .map(ValoresPagarEntity::getValorConta)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private BigDecimal buscarTotalValoresPagos() {

        List<ValoresPagarEntity> listEntity = (List<ValoresPagarEntity>) repository.findAll();

        return listEntity.stream()
                .filter(entity -> entity.getStatusPagamento().equals(StatusPagamento.PAGO))
                .map(ValoresPagarEntity::getValorConta)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
    public ValoresPagarOutDTO alterarStatus(String tipoConta) {

        ValoresPagarEntity entity = repository.findByTipoConta(tipoConta);

        if (entity.getStatusPagamento().equals(StatusPagamento.PENDENTE)) {
            entity.setStatusPagamento(StatusPagamento.PAGO);
            repository.save(entity);
            return converter.paraDTO(entity, buscarTotalValoresAPagar(), buscarTotalValoresPagos());

        }
        entity.getStatusPagamento().equals(StatusPagamento.PAGO);
            entity.setStatusPagamento(StatusPagamento.PENDENTE);
            repository.save(entity);
            return converter.paraDTO(entity, buscarTotalValoresAPagar(), buscarTotalValoresPagos());
    }

    public List<ValoresPagarTodosOutDTO> buscarTodos() {
        List<ValoresPagarEntity> todos = (List<ValoresPagarEntity>) repository.findAll();
        return converter.paraListaValoresaPagarOutDTO(todos);
    }



}
