package br.com.fiap.betadvisor.service;

import br.com.fiap.betadvisor.Aposta;
import br.com.fiap.betadvisor.dto.ApostaRequestDTO;
import br.com.fiap.betadvisor.exception.ApostaNotFoundException;
import br.com.fiap.betadvisor.repository.ApostaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApostaServiceTest {

    @Mock
    private ApostaRepository apostaRepository;

    @InjectMocks
    private ApostaServiceImpl apostaService;

    @Test
    @DisplayName("Deve retornar a aposta quando o ID existir")
    void findById_QuandoIdExiste_DeveRetornarAposta() {

        long idExistente = 1L;
        Aposta apostaMock = new Aposta(idExistente, "Palmeiras", 100.0, 1.8, null);
        when(apostaRepository.findById(idExistente)).thenReturn(Optional.of(apostaMock));

        Aposta apostaEncontrada = apostaService.findById(idExistente);

        assertNotNull(apostaEncontrada);
        assertEquals(idExistente, apostaEncontrada.getId());
        assertEquals("Palmeiras", apostaEncontrada.getTime());
        verify(apostaRepository, times(1)).findById(idExistente);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o ID não existir")
    void findById_QuandoIdNaoExiste_DeveLancarExcecao() {

        long idNaoExistente = 99L;
        when(apostaRepository.findById(idNaoExistente)).thenReturn(Optional.empty());

        assertThrows(ApostaNotFoundException.class, () -> {
            apostaService.findById(idNaoExistente);
        });

        verify(apostaRepository, times(1)).findById(idNaoExistente);
    }

    @Test
    @DisplayName("Deve salvar e retornar uma nova aposta com sucesso")
    void save_ComDadosValidos_DeveSalvarERetornarAposta() {
        ApostaRequestDTO requestDTO = new ApostaRequestDTO("Palmeiras", 50.0, 2.0, "2025-10-19");
        Aposta apostaSalvaMock = new Aposta(1L, "Palmeiras", 50.0, 2.0, null);

        when(apostaRepository.save(any(Aposta.class))).thenReturn(apostaSalvaMock);

        Aposta apostaSalva = apostaService.save(requestDTO);

        assertNotNull(apostaSalva);
        assertEquals(1L, apostaSalva.getId());
        verify(apostaRepository, times(1)).save(any(Aposta.class));
    }
}
