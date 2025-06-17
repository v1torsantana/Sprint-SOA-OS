package br.com.fiap.betadvisor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "apostas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aposta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apostas_seq_gen")
    @SequenceGenerator(name = "apostas_seq_gen", sequenceName = "APOSTAS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "time", nullable = false, length = 50)
    private String time;

    @Column(name = "valor_aposta", nullable = false)
    private Double valorAposta;

    @Column(name = "odd", nullable = false)
    private Double odd;

    @CreationTimestamp // Mapeia o campo para ser preenchido na criação
    @Column(name = "data_aposta", nullable = false, updatable = false)
    private Instant dataAposta;
}