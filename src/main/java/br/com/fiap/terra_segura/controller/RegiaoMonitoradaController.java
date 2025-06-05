package br.com.fiap.terra_segura.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.terra_segura.repository.RegiaoMonitoradaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/regiao")
@Slf4j
public class RegiaoMonitoradaController {

    public record RegiaoFilter(Long id, String nome, String descricao, BigDecimal latitude, BigDecimal longitude, String nivelRisco) {
    }

    @Autowired
    private RegiaoMonitoradaRepository repository;

    
}
