package com.papler.controlefinanceiro.business.service;

import com.papler.controlefinanceiro.api.dto.ValoresReceberInDTO;
import com.papler.controlefinanceiro.api.dto.ValoresReceberOutDTO;
import com.papler.controlefinanceiro.api.dto.ValoresReceberTotalOutDTO;
import com.papler.controlefinanceiro.business.converter.ValoresReceberConverter;
import com.papler.controlefinanceiro.business.service.BO.TotalValoresCreditoBO;
import com.papler.controlefinanceiro.infraestructure.entities.ValoresReceberEntity;
import com.papler.controlefinanceiro.infraestructure.enums.StatusRecebimento;
import com.papler.controlefinanceiro.infraestructure.repositories.ValoresReceberRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ValoresReceberService {

    private final ValoresReceberRepository repository;
    private final ValoresReceberConverter converter;

    public ValoresReceberService(ValoresReceberRepository repository, ValoresReceberConverter converter) {
        this.repository = repository;
        this.converter = converter;

    }

    public ValoresReceberOutDTO cadastrarValores(ValoresReceberInDTO dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException();
        }
        ValoresReceberEntity entity = converter.paraEntity(dto);
        repository.save(entity);
        buscarValores();
        return converter.paraDTO(entity, buscarValoresRecebidos(), buscarValoresAReceber());
    }

    public TotalValoresCreditoBO buscarValores() {
        return converter.paraBO(buscarValoresRecebidos(), buscarValoresAReceber());
    }

    private BigDecimal buscarValoresRecebidos() {

        List<ValoresReceberEntity> listEntity = (List<ValoresReceberEntity>) repository.findAll();

        return listEntity.stream()
                .filter(entity -> entity.getStatusRecebimento().equals(StatusRecebimento.RECEBIDO))
                .map(ValoresReceberEntity::getValorRecebido)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private BigDecimal buscarValoresAReceber() {

        List<ValoresReceberEntity> listEntity = (List<ValoresReceberEntity>) repository.findAll();

        return listEntity.stream()
                .filter(entity -> entity.getStatusRecebimento().equals(StatusRecebimento.PENDENTE))
                .map(ValoresReceberEntity::getValorRecebido)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public ValoresReceberOutDTO alterarStatus(String tipoRecebimento) {

        ValoresReceberEntity entity = repository.findByTipoRecebimento(tipoRecebimento.toUpperCase());

        if (entity.getStatusRecebimento().equals(StatusRecebimento.PENDENTE)) {
            entity.setStatusRecebimento(StatusRecebimento.RECEBIDO);
            repository.save(entity);
            return converter.paraDTO(entity, buscarValoresRecebidos(), buscarValoresAReceber());

        }
        entity.getStatusRecebimento().equals(StatusRecebimento.RECEBIDO);
        entity.setStatusRecebimento(StatusRecebimento.PENDENTE);
        repository.save(entity);
        return converter.paraDTO(entity, buscarValoresRecebidos(), buscarValoresAReceber());
    }

    public List<ValoresReceberTotalOutDTO> buscarTodos() {
        List<ValoresReceberEntity> todos = (List<ValoresReceberEntity>) repository.findAll();
        return converter.paraListaValoresaReceberOutDTO(todos);
    }
}



