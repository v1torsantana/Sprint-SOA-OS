package br.com.fiap.betadvisor.controller;

import br.com.fiap.betadvisor.dto.UsuarioLoginDTO;
import br.com.fiap.betadvisor.entity.Usuario;
import br.com.fiap.betadvisor.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ApostaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String token;

    @BeforeEach // Este método roda ANTES de cada teste
    void setUp() throws Exception {
        usuarioRepository.deleteAll();
        Usuario usuario = new Usuario(null, "testuser", passwordEncoder.encode("password"));
        usuarioRepository.save(usuario);

        UsuarioLoginDTO loginDTO = new UsuarioLoginDTO("testuser", "password");
        String loginJson = objectMapper.writeValueAsString(loginDTO);

        String responseString = mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(responseString);
        this.token = jsonNode.get("token").asText();
    }

    @Test
    @DisplayName("Deve retornar 403 Forbidden ao tentar listar apostas sem token")
    void getAll_SemToken_DeveRetornar403() throws Exception {
        mockMvc.perform(get("/api/apostas"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Deve retornar 200 OK e uma lista de apostas ao listar com token válido")
    void getAll_ComTokenValido_DeveRetornar200() throws Exception {
        mockMvc.perform(get("/api/apostas")
                        .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
