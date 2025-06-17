package br.com.fiap.betadvisor.service;

import br.com.fiap.betadvisor.Aposta;
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

    public Aposta save(Aposta aposta) {
        return apostaRepository.save(aposta);
    }

    public Aposta update(Aposta apostaComNovosDados) {
        Long id = apostaComNovosDados.getId();
        if (id == null) {
            throw new IllegalArgumentException("Para atualizar, o 'id' da aposta deve ser fornecido.");
        }
        Aposta apostaExistente = this.findById(id);

        apostaExistente.setTime(apostaComNovosDados.getTime());
        apostaExistente.setValorAposta(apostaComNovosDados.getValorAposta());
        apostaExistente.setOdd(apostaComNovosDados.getOdd());

        return apostaRepository.save(apostaExistente);
    }

    public void deleteById(Long id) {
        Aposta apostaParaDeletar = this.findById(id);
        apostaRepository.delete(apostaParaDeletar);
    }
}