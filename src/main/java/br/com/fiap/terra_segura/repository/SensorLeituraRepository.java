package br.com.fiap.terra_segura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.terra_segura.Model.SensorLeitura;

public interface SensorLeituraRepository extends JpaRepository<SensorLeitura, Long>, JpaSpecificationExecutor<SensorLeitura> {

}
