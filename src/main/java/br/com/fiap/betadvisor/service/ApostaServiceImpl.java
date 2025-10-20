package br.com.fiap.betadvisor.service;

import br.com.fiap.betadvisor.Aposta;
import br.com.fiap.betadvisor.dto.ApostaRequestDTO;
import br.com.fiap.betadvisor.exception.ApostaNotFoundException;
import br.com.fiap.betadvisor.repository.ApostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApostaServiceImpl implements ApostaService {

    private final ApostaRepository apostaRepository;

    @Override
    public List<Aposta> findAll() {
        return apostaRepository.findAll();
    }

    @Override
    public Aposta findById(Long id) {
        return apostaRepository.findById(id)
                .orElseThrow(() -> new ApostaNotFoundException("Aposta não encontrada com id: " + id));
    }

    @Override
    public Aposta save(ApostaRequestDTO apostaDTO) {
        Aposta novaAposta = new Aposta();
        novaAposta.setTime(apostaDTO.time());
        novaAposta.setValorAposta(apostaDTO.valorAposta());
        novaAposta.setOdd(apostaDTO.odd());
        return apostaRepository.save(novaAposta);
    }

    @Override
    public Aposta update(Long id, ApostaRequestDTO apostaDTO) {
        Aposta apostaExistente = this.findById(id);

        apostaExistente.setTime(apostaDTO.time());
        apostaExistente.setValorAposta(apostaDTO.valorAposta());
        apostaExistente.setOdd(apostaDTO.odd());
        return apostaRepository.save(apostaExistente);
    }

    @Override
    public void deleteById(Long id) {
        Aposta apostaParaDeletar = this.findById(id);
        apostaRepository.delete(apostaParaDeletar);
    }
}