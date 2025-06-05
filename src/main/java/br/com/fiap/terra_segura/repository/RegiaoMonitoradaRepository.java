package br.com.fiap.terra_segura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.terra_segura.Model.RegiaoMonitorada;

public interface RegiaoMonitoradaRepository
        extends JpaRepository<RegiaoMonitorada, Long>, JpaSpecificationExecutor<RegiaoMonitorada> {

}
