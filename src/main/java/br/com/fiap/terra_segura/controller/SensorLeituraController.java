package br.com.fiap.terra_segura.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sensor")
@Slf4j
public class SensorLeituraController {
    
    public record SensorFilter(Long id, Long regiaoMonitoradaId, String tipoSensor, BigDecimal valor, LocalDateTime dataHoraLeitura) {}
}
