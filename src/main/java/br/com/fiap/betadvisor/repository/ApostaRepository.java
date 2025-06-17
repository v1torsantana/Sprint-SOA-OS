package br.com.fiap.betadvisor.repository;

import br.com.fiap.betadvisor.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long> {

}