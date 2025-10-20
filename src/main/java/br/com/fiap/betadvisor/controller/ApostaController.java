package br.com.fiap.betadvisor.controller;

import br.com.fiap.betadvisor.Aposta;
import br.com.fiap.betadvisor.dto.ApostaRequestDTO;
import br.com.fiap.betadvisor.dto.ApostaResponseDTO;
import br.com.fiap.betadvisor.service.ApostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apostas")
@RequiredArgsConstructor
@Tag(name = "Apostas", description = "Endpoints para o gerenciamento de apostas.")
public class ApostaController {

    private final ApostaService apostaService;

    @PostMapping
    @Operation(summary = "Cria uma nova aposta", description = "Registra uma nova aposta no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aposta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    public ResponseEntity<ApostaResponseDTO> create(@RequestBody @Valid ApostaRequestDTO apostaRequestDTO) {
        Aposta aposta = apostaService.save(apostaRequestDTO);
        ApostaResponseDTO responseDTO = new ApostaResponseDTO(
                aposta.getId(), aposta.getTime(), aposta.getValorAposta(), aposta.getOdd(), aposta.getDataAposta()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    @Operation(summary = "Lista todas as apostas", description = "Retorna uma lista com todas as apostas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de apostas retornada com sucesso")
    public ResponseEntity<List<ApostaResponseDTO>> getAll() {
        List<Aposta> apostas = apostaService.findAll();
        List<ApostaResponseDTO> responseDTOs = apostas.stream()
                .map(aposta -> new ApostaResponseDTO(
                        aposta.getId(), aposta.getTime(), aposta.getValorAposta(), aposta.getOdd(), aposta.getDataAposta()))
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca aposta por ID", description = "Retorna os detalhes de uma aposta específica pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aposta encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aposta não encontrada para o ID informado")
    })
    public ResponseEntity<ApostaResponseDTO> getById(@PathVariable Long id) {
        Aposta aposta = apostaService.findById(id);
        ApostaResponseDTO responseDTO = new ApostaResponseDTO(
                aposta.getId(), aposta.getTime(), aposta.getValorAposta(), aposta.getOdd(), aposta.getDataAposta()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma aposta", description = "Atualiza os dados de uma aposta existente a partir do seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aposta atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Aposta não encontrada para o ID informado")
    })
    public ResponseEntity<ApostaResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ApostaRequestDTO apostaRequestDTO) {
        Aposta apostaAtualizada = apostaService.update(id, apostaRequestDTO);
        ApostaResponseDTO responseDTO = new ApostaResponseDTO(
                apostaAtualizada.getId(), apostaAtualizada.getTime(), apostaAtualizada.getValorAposta(), apostaAtualizada.getOdd(), apostaAtualizada.getDataAposta()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma aposta", description = "Remove uma aposta do sistema pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aposta deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aposta não encontrada para o ID informado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        apostaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}