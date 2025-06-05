package br.com.fiap.terra_segura.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

import br.com.fiap.terra_segura.Model.SensorLeitura;
import br.com.fiap.terra_segura.repository.SensorLeituraRepository;
import br.com.fiap.terra_segura.specification.SensorLeituraSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sensor")
@Slf4j
public class SensorLeituraController {

    public record SensorFilter(Long id, Long regiaoMonitoradaId, String tipoSensor, BigDecimal valor, LocalDateTime dataHoraLeitura) {
    }

    @Autowired
    private SensorLeituraRepository repository;

    @GetMapping
    @Cacheable("leituras")
    @Operation(description = "Listar leituras de sensores com filtros ou não", summary = "List all sensor readings", tags = "Sensor Leitura", responses = {
            @ApiResponse(responseCode = "200", description = "Leituras encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma leitura encontrada")
    })
    public Page<SensorLeitura> index(SensorFilter filter,
            @PageableDefault(size = 10, sort = "dataHoraLeitura", direction = Direction.DESC) Pageable pageable) {
        var specification = SensorLeituraSpecification.withFilter(filter);
        return repository.findAll(specification, pageable);
    }

    @PostMapping
    @CacheEvict(value = "leituras", allEntries = true)
    @Operation(description = "Criar uma nova leitura de sensor", summary = "Create a new sensor reading", tags = "Sensor Leitura", responses = {
            @ApiResponse(responseCode = "201", description = "Leitura criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação da leitura")
    })
    public SensorLeitura create(@RequestBody @Valid SensorLeitura sensorLeitura) {
        log.info("Criando nova leitura de sensor: {}", sensorLeitura);
        return repository.save(sensorLeitura);
    }

    @GetMapping("{id}")
    @Operation(description = "Buscar uma leitura de sensor por ID", summary = "Get a sensor reading by ID", tags = "Sensor Leitura", responses = {
            @ApiResponse(responseCode = "200", description = "Leitura encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Leitura não encontrada")
    })
    public SensorLeitura get(@PathVariable Long id) {
        log.info("Buscando leitura de sensor com ID: {}", id);
        return getLeitura(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @CacheEvict(value = "leituras", allEntries = true)
    @Operation(description = "Excluir uma leitura de sensor por ID", summary = "Delete a sensor reading by ID", tags = "Sensor Leitura", responses = {
            @ApiResponse(responseCode = "204", description = "Leitura excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Leitura não encontrada")
    })
    public void delete(@PathVariable Long id) {
        log.info("Excluindo leitura de sensor com ID: {}", id);
        repository.delete(getLeitura(id));
    }

    @PutMapping("{id}")
    @CacheEvict(value = "leituras", allEntries = true)
    @Operation(description = "Atualizar uma leitura de sensor por ID", summary = "Update a sensor reading by ID", tags = "Sensor Leitura", responses = {
            @ApiResponse(responseCode = "200", description = "Leitura atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Leitura não encontrada")
    })
    public SensorLeitura update(@PathVariable Long id, @RequestBody @Valid SensorLeitura sensorLeitura) {
        log.info("Atualizando leitura de sensor com ID: {}", id);
        getLeitura(id);
        sensorLeitura.setId(id);
        return repository.save(sensorLeitura);
    }

    private SensorLeitura getLeitura(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leitura de sensor não encontrada com ID: " + id));
    }
}
