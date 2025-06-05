package br.com.fiap.terra_segura.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.terra_segura.Model.SensorLeitura;
import br.com.fiap.terra_segura.controller.SensorLeituraController.SensorFilter;
import jakarta.persistence.criteria.Predicate;

public class SensorLeituraSpecification {
    
    public static Specification<SensorLeitura> withFilter(SensorFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.id() != null) {
                predicates.add(cb.equal(root.get("id"), filter.id()));
            }

            if (filter.regiaoMonitoradaId() != null) {
                predicates.add(cb.equal(root.get("regiaoMonitoradaId").get("id"), filter.regiaoMonitoradaId()));
            }

            if (filter.tipoSensor() != null) {
                predicates.add(cb.equal(root.get("tipoSensor"), filter.tipoSensor()));
            }

            if (filter.valor() != null) {
                predicates.add(cb.equal(root.get("valor"), filter.valor()));
            }

            if (filter.dataHoraLeitura() != null) {
                predicates.add(cb.equal(root.get("dataHoraLeitura"), filter.dataHoraLeitura()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
