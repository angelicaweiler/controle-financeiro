package com.papler.controlefinanceiro.infraestructure.repositories;

import com.papler.controlefinanceiro.infraestructure.entities.ValoresPagarEntity;
import com.papler.controlefinanceiro.infraestructure.entities.ValoresReceberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValoresPagarRepository extends CrudRepository<ValoresPagarEntity, Long> {
    public ValoresPagarEntity findByTipoConta(String tipoConta);

}
