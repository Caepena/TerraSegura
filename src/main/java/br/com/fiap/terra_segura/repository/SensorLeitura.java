package br.com.fiap.terra_segura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SensorLeitura extends JpaRepository<SensorLeitura, Long>, JpaSpecificationExecutor<SensorLeitura> {

}
