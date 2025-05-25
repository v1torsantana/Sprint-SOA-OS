package br.com.fiap.betadvisor.data;

import br.com.fiap.betadvisor.processes.ExposeAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;

@RestController
public class ReadJSON {
    private final ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiGatewayUrl = "";
    private final String apiKey = "";
    private final ExposeAPI exposeAPI;

    public ReadJSON(ExposeAPI exposeAPI) {
        this.exposeAPI = exposeAPI;
    }

    @GetMapping("/cloud-it")
    public String getBet() {
        try {
            File file = new File("data.json");
            if (!file.exists()) return "Arquivo data.json não encontrado.";

            List<Bet> bets = mapper.readValue(file, new TypeReference<>() {
            });
            if (bets.isEmpty()) return "Nenhuma aposta encontrada no JSON.";

            for (Bet bet : bets) {
                exposeAPI.exposeAPI(bet);
            }

            return "Apostas enviadas com sucesso.";

        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o JSON: " + e.getMessage());
        }
    }
}

