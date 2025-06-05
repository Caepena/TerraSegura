package br.com.fiap.terra_segura.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.terra_segura.Model.RegiaoMonitorada;
import br.com.fiap.terra_segura.repository.RegiaoMonitoradaRepository;
import br.com.fiap.terra_segura.specification.RegiaoMonitoradaSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/regiao")
@Slf4j
public class RegiaoMonitoradaController {

    public record RegiaoFilter(Long id, String nome, String descricao, BigDecimal latitude, BigDecimal longitude,
            String nivelRisco) {
    }

    @Autowired
    private RegiaoMonitoradaRepository repository;

    @GetMapping
    @Cacheable("regioes")
    @Operation(description = "Listar regiões monitoradas com filtros ou não", summary = "List all monitored regions", tags = "Região Monitorada", responses = {
            @ApiResponse(responseCode = "200", description = "Regiões monitoradas encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma região monitorada encontrada")
    })
    public Page<RegiaoMonitorada> index(RegiaoFilter filter,
            @PageableDefault(size = 2, sort = "nome", direction = Direction.ASC) Pageable pageable) {
        var specification = RegiaoMonitoradaSpecification.withFilter(filter);
        return repository.findAll(specification, pageable);
    }

    @PostMapping
    @CacheEvict(value = "regioes", allEntries = true)
    @Operation(description = "Criar uma nova região monitorada", summary = "Create a new monitored region", tags = "Região Monitorada", responses = {
            @ApiResponse(responseCode = "201", description = "Região monitorada criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação da região monitorada")
    })
    public RegiaoMonitorada create(@RequestBody @Valid RegiaoMonitorada regiaoMonitorada) {
        log.info("Criando nova região monitorada: {}", regiaoMonitorada);
        return repository.save(regiaoMonitorada);
    }

    @GetMapping("{id}")
    @Operation(description = "Buscar uma região monitorada por ID", summary = "Get a monitored region by ID", tags = "Região Monitorada", responses = {
            @ApiResponse(responseCode = "200", description = "Região monitorada encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Região monitorada não encontrada")
    })
    public RegiaoMonitorada get(Long id) {
        log.info("Buscando região monitorada com ID: {}", id);
        return getRegiao(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @CacheEvict(value = "regioes", allEntries = true)
    @Operation(description = "Excluir uma região monitorada por ID", summary = "Delete a monitored region by ID", tags = "Região Monitorada", responses = {
            @ApiResponse(responseCode = "204", description = "Região monitorada excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Região monitorada não encontrada")
    })
    public void delete(@PathVariable Long id) {
        log.info("Excluindo região monitorada com ID: {}", id);
        repository.delete(getRegiao(id));
    }

    @PutMapping("{id}")
    @CacheEvict(value = "regioes", allEntries = true)
    @Operation(description = "Atualizar uma região monitorada por ID", summary = "Update a monitored region by ID", tags = "Região Monitorada", responses = {
            @ApiResponse(responseCode = "200", description = "Região monitorada atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Região monitorada não encontrada")
    })
    public RegiaoMonitorada update(@PathVariable Long id, @RequestBody @Valid RegiaoMonitorada regiaoMonitorada) {
        log.info("Atualizando região monitorada com ID: {}", id);
        getRegiao(id);
        regiaoMonitorada.setId(id);
        return repository.save(regiaoMonitorada);
    }

    private RegiaoMonitorada getRegiao(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Região monitorada não encontrada com ID: " + id));
    }
}
