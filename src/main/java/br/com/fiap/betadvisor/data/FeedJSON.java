package br.com.fiap.betadvisor.data;

import br.com.fiap.betadvisor.processes.ExposeAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedJSON {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("data.json");

    private final ExposeAPI expose;

    @Autowired
    public FeedJSON(ExposeAPI expose) {
        this.expose = expose;
    }
    @PostMapping
    public String FeedJson(@RequestBody Bet a) {
        try {
            List<Bet> bets;
            if (file.exists()) {
                bets = mapper.readValue(file, new TypeReference<List<Bet>>() {
                });
            } else {
                bets = new ArrayList<>();
            }
            bets.add(a);
            mapper.writeValue(file, bets);

            expose.exposeAPI(a);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar JSON: " + e.getMessage());
        }
        return "Adicionado ao JSON!";
    }

}

