package br.com.fiap.terra_segura.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.terra_segura.Model.NivelRisco;
import br.com.fiap.terra_segura.Model.RegiaoMonitorada;
import br.com.fiap.terra_segura.controller.RegiaoMonitoradaController.RegiaoFilter;
import jakarta.persistence.criteria.Predicate;

public class RegiaoMonitoradaSpecification {

    public static Specification<RegiaoMonitorada> withFilter(RegiaoFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.id() != null) {
                predicates.add(cb.equal(root.get("id"), filter.id()));
            }

            if (filter.nome() != null && !filter.nome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }

            if (filter.latitude() != null) {
                predicates.add(cb.equal(root.get("latitude"), filter.latitude()));
            }

            if (filter.longitude() != null) {
                predicates.add(cb.equal(root.get("longitude"), filter.longitude()));
            }

            if (filter.descricao() != null && !filter.descricao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("descricao")), "%" + filter.descricao().toLowerCase() + "%"));
            }

            if (filter.nivelRisco() != null && !filter.nivelRisco().isBlank()) {
                try {
                    NivelRisco nivel = NivelRisco.valueOf(filter.nivelRisco().toUpperCase());
                    predicates.add(cb.equal(root.get("nivelRisco"), nivel));
                } catch (IllegalArgumentException ignored) {

                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}