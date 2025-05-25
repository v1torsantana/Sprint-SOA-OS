package br.com.fiap.betadvisor.processes;


import br.com.fiap.betadvisor.data.Bet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ExposeAPI{
    ObjectMapper mapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();

    public void exposeAPI(@RequestBody Bet a){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", "");
            headers.setContentType(MediaType.APPLICATION_JSON);

            String json = mapper.writeValueAsString(a);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            String apiGatewayUrl = "";

            ResponseEntity<String> response = restTemplate.postForEntity(apiGatewayUrl, entity, String.class);

            System.out.println("Resposta da nuvem: " + response.getBody());
        } catch (Exception ex) {
            System.err.println("Erro ao enviar pro API Gateway: " + ex.getMessage());
        }
    }
}
