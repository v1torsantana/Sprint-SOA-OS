package br.com.fiap.betadvisor.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApostaRequestDTO {

    @NotBlank(message = "O campo 'time' não pode estar em branco.")
    private String time;

    @NotNull(message = "O campo 'valorAposta' é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor da aposta deve ser maior que zero.")
    private Double valorAposta;

    @NotNull(message = "O campo 'odd' é obrigatório.")
    @DecimalMin(value = "1.0", message = "A odd deve ser maior ou igual a 1.")
    private Double odd;

}
