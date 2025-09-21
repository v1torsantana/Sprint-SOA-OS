package br.com.fiap.betadvisor.controller;

import br.com.fiap.betadvisor.Aposta;
import br.com.fiap.betadvisor.dto.ApostaRequestDTO;
import br.com.fiap.betadvisor.dto.ApostaResponseDTO;
import br.com.fiap.betadvisor.exception.ApostaNotFoundException;
import br.com.fiap.betadvisor.service.ApostaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/apostas")
@RequiredArgsConstructor
public class ApostaController {

    private final ApostaService apostaService;

    @PostMapping
    public ResponseEntity<ApostaResponseDTO> create(@RequestBody @Valid ApostaRequestDTO apostaRequestDTO) {
        Aposta aposta = apostaService.save(apostaRequestDTO);
        ApostaResponseDTO responseDTO = new ApostaResponseDTO(
                aposta.getId(),
                aposta.getTime(),
                aposta.getValorAposta(),
                aposta.getOdd(),
                aposta.getDataAposta()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ApostaResponseDTO>> getAll() {
        List<Aposta> apostas = apostaService.findAll();
        List<ApostaResponseDTO> responseDTOs = apostas.stream()
                .map(aposta -> new ApostaResponseDTO(
                        aposta.getId(),
                        aposta.getTime(),
                        aposta.getValorAposta(),
                        aposta.getOdd(),
                        aposta.getDataAposta()))
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApostaResponseDTO> getById(@PathVariable Long id) {
        Aposta aposta = apostaService.findById(id);
        ApostaResponseDTO responseDTO = new ApostaResponseDTO(
                aposta.getId(),
                aposta.getTime(),
                aposta.getValorAposta(),
                aposta.getOdd(),
                aposta.getDataAposta()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApostaResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ApostaRequestDTO apostaRequestDTO) {
        Aposta apostaAtualizada = apostaService.update(id, apostaRequestDTO);
        ApostaResponseDTO responseDTO = new ApostaResponseDTO(
                apostaAtualizada.getId(),
                apostaAtualizada.getTime(),
                apostaAtualizada.getValorAposta(),
                apostaAtualizada.getOdd(),
                apostaAtualizada.getDataAposta()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        apostaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}