package br.com.fiap.terra_segura.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorLeitura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @NotNull(message = "A região monitorada é obrigatória.")
    @JoinColumn(name = "id")
    private RegiaoMonitorada regiaoMonitoradaId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo de sensor é obrigatório.")
    private TipoSensor tipoSensor;

    @NotNull(message = "Valor da medição é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = true, message = "O valor deve ser maior ou igual a zero.")
    private BigDecimal valor;

    @NotNull(message = "A data e hora da leitura são obrigatórias e em formato YYYY-MM-DDTHH:MM:SS.")
    private LocalDateTime dataHoraLeitura;
}
