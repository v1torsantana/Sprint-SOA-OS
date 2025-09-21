package br.com.fiap.betadvisor.service;

import br.com.fiap.betadvisor.Aposta;
import br.com.fiap.betadvisor.dto.ApostaRequestDTO;
import br.com.fiap.betadvisor.exception.ApostaNotFoundException; // Importe a nova exceção
import br.com.fiap.betadvisor.repository.ApostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApostaService {

    private final ApostaRepository apostaRepository;

    public List<Aposta> findAll() {
        return apostaRepository.findAll();
    }

    public Aposta findById(Long id) {
        return apostaRepository.findById(id)
                .orElseThrow(() -> new ApostaNotFoundException("Aposta não encontrada com id: " + id));
    }

    public Aposta save(ApostaRequestDTO apostaDTO) {
        Aposta novaAposta = new Aposta();
        novaAposta.setTime(apostaDTO.getTime());
        novaAposta.setValorAposta(apostaDTO.getValorAposta());
        novaAposta.setOdd(apostaDTO.getOdd());

        return apostaRepository.save(novaAposta);
    }

    public Aposta update(Long id, ApostaRequestDTO apostaDTO) {
        Aposta apostaExistente = this.findById(id);

        apostaExistente.setTime(apostaDTO.getTime());
        apostaExistente.setValorAposta(apostaDTO.getValorAposta());
        apostaExistente.setOdd(apostaDTO.getOdd());

        return apostaRepository.save(apostaExistente);
    }

    public void deleteById(Long id) {
        Aposta apostaParaDeletar = this.findById(id);
        apostaRepository.delete(apostaParaDeletar);
    }
}