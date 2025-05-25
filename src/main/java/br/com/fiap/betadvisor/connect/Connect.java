package br.com.fiap.betadvisor.connect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Connect implements CommandLineRunner {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void run(String... args) throws Exception {
        try {
            em.createNativeQuery("SELECT 1 FROM DUAL").getSingleResult();
            System.out.println("Conexão com o banco OK!");
        } catch (Exception e) {
            System.err.println("Falha na conexão: " + e.getMessage());
        }
    }
}
