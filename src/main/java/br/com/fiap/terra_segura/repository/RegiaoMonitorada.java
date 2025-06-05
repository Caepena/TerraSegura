package br.com.fiap.terra_segura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RegiaoMonitorada
        extends JpaRepository<RegiaoMonitorada, Long>, JpaSpecificationExecutor<RegiaoMonitorada> {

}
