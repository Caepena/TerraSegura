package br.com.fiap.terra_segura.Model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegiaoMonitorada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da região é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "A latitude é obrigatória.")
    @DecimalMin(value = "-90.0", message = "Latitude mínima é -90.")
    @DecimalMax(value = "90.0", message = "Latitude máxima é 90.")
    private BigDecimal latitude;

    @NotBlank(message = "A longitude é obrigatória.")
    @DecimalMin(value = "-180.0", message = "Longitude mínima é -180.")
    @DecimalMax(value = "180.0", message = "Longitude máxima é 180.")
    private BigDecimal longitude;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotBlank(message = "O nível de risco é obrigatório.")
    @Enumerated(EnumType.STRING)
    private NivelRisco nivelRisco;
}
