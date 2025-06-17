package br.com.fiap.betadvisor.controller;

import br.com.fiap.betadvisor.Aposta;
import br.com.fiap.betadvisor.exception.ApostaNotFoundException;
import br.com.fiap.betadvisor.service.ApostaService;
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

    @PostMapping("/add")
    public ResponseEntity<String> create(@RequestBody Aposta aposta) {
        Aposta apostaSalva = apostaService.save(aposta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aposta criada com sucesso! ID: " + apostaSalva.getId());
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Aposta>> getAll() {
        return ResponseEntity.ok(apostaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Aposta aposta = apostaService.findById(id);
            return ResponseEntity.ok(aposta);
        } catch (ApostaNotFoundException e) {
            Map<String, Object> corpoErro = Map.of("message", e.getMessage());
            return new ResponseEntity<>(corpoErro, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Aposta aposta) {
        try {
            apostaService.update(aposta);
            return ResponseEntity.ok("Aposta com ID " + aposta.getId() + " atualizada com sucesso!");
        } catch (ApostaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            apostaService.deleteById(id);
            return ResponseEntity.ok("Aposta com ID " + id + " removida com sucesso.");
        } catch (ApostaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}