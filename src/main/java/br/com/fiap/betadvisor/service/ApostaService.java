package br.com.fiap.betadvisor.service;

import br.com.fiap.betadvisor.Aposta;
import br.com.fiap.betadvisor.dto.ApostaRequestDTO;
import java.util.List;

public interface ApostaService {

    List<Aposta> findAll();

    Aposta findById(Long id);

    Aposta save(ApostaRequestDTO apostaDTO);

    Aposta update(Long id, ApostaRequestDTO apostaDTO);

    void deleteById(Long id);
}