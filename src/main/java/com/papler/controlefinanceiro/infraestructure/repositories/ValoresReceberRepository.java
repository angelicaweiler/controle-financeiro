package com.papler.controlefinanceiro.infraestructure.repositories;

import com.papler.controlefinanceiro.infraestructure.entities.ValoresReceberEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
public interface ValoresReceberRepository extends CrudRepository<ValoresReceberEntity, Long> {

        public ValoresReceberEntity findByTipoRecebimento(String tipoRecebimento);

}
