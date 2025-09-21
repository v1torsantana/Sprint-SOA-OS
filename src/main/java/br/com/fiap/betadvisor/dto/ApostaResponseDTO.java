package br.com.fiap.betadvisor.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApostaResponseDTO {

    private Long id;
    private String time;
    private Double valorAposta;
    private Double odd;
    private Instant dataAposta;

}
