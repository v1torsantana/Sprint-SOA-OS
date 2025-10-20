# Projeto de Arquitetura Orientada a Serviços e Web Services

## Descrição do Projeto

Este projeto é uma aplicação de back-end desenvolvida com **Spring Boot** para a disciplina de Arquitetura Orientada a Serviços e Web Services. A aplicação implementa uma API RESTful completa para gerenciar um recurso de apostas (`Aposta`).

A arquitetura do projeto segue o padrão de camadas (Controller, Service, Repository), utilizando interfaces para a camada de serviço para garantir a separação de responsabilidades e aderir aos princípios SOLID. A API é protegida com **Spring Security e JWT**, e toda a sua funcionalidade é documentada de forma interativa com **SpringDoc (Swagger)**.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.3.0
* **Gerenciador de Dependências:** Maven
* **Banco de Dados:** Oracle (ambiente FIAP)
* **ORM:** Spring Data JPA / Hibernate
* **Segurança:** Spring Security, JSON Web Token (JWT) com a biblioteca `java-jwt` da Auth0
* **Documentação:** SpringDoc (Swagger/OpenAPI)
* **Testes:** JUnit 5, Mockito & Spring Test (MockMvc)
* **Validação:** Jakarta Validation
* **Utilitários:** Lombok

## Estrutura do Projeto

O projeto é organizado em pacotes que representam as camadas da arquitetura:

* `br.com.fiap.betadvisor.config`**: Contém as configurações de segurança (`SecurityConfig`) e o filtro JWT (`SecurityFilter`).
* `br.com.fiap.betadvisor.controller`**: Responsável por expor os endpoints da API (`ApostaController`, `UsuarioController`).
* `br.com.fiap.betadvisor.dto`**: Objetos de Transferência de Dados para as requisições e respostas.
* `br.com.fiap.betadvisor.entity`**: Entidades JPA que modelam as tabelas do banco de dados (`Aposta`, `Usuario`).
* `br.com.fiap.betadvisor.exception`**: Tratamento global de exceções.
* `br.com.fiap.betadvisor.repository`**: Interfaces que estendem `JpaRepository` para a comunicação com o banco.
* `br.com.fiap.betadvisor.service`**: Contém as interfaces e implementações da lógica de negócio (`ApostaService`, `TokenService`, `AuthenticationService`).

## ⚙️ Como Executar a Aplicação

**Pré-requisitos:**
* JDK 17
* Acesso à rede da FIAP (via VPN, se necessário, pois o projeto aponta para o Oracle da instituição).

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/v1torsantana/Sprint-SOA-OS.git](https://github.com/v1torsantana/Sprint-SOA-OS.git)
    cd Sprint-SOA-OS
    ```

2.  **Execute a aplicação com o Maven Wrapper:**
    O uso do Maven Wrapper (`mvnw`) é recomendado para garantir a consistência do ambiente de build.
    ```bash
    # No Windows (CMD ou PowerShell)
    ./mvnw.cmd spring-boot:run

    # No Linux ou macOS
    ./mvnw spring-boot:run
    ```
    A aplicação estará disponível em `http://localhost:8080`.

## 📖 Acessando a Documentação da API (Swagger)

Com a aplicação em execução, acesse a documentação interativa do Swagger no seu navegador. É a forma mais fácil de testar a API.

**[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

## 🧪 Testando a API (Fluxo com Autenticação)

Os endpoints de apostas estão protegidos. Para acessá-los, siga o fluxo abaixo (pode ser feito pelo Swagger ou Postman).

#### 1. Registrar um Usuário

* **Endpoint:** `POST /api/usuarios/registrar`
* **Body (JSON):**
    ```json
    {
      "login": "fiap_user",
      "senha": "password123"
    }
    ```
* **Resposta:** `200 OK`

#### 2. Fazer Login para Obter um Token

* **Endpoint:** `POST /api/usuarios/login`
* **Body (JSON):**
    ```json
    {
      "login": "fiap_user",
      "senha": "password123"
    }
    ```
* **Resposta (200 OK):**
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJCZXRBZHZpc29yIEFQSSIsInN1YiI6ImZpYXBfdXNlciIsImV4cCI6MTc2MDk5OTk5OX0.TOKEN_EXEMPLO"
    }
    ```
**➡️ Copie o valor do token gerado.**

#### 3. Acessar um Endpoint Protegido

* **Endpoint:** `GET /api/apostas`
* **Autenticação:** Na sua ferramenta de teste (Postman ou Swagger), adicione um cabeçalho `Authorization` com o valor `Bearer SEU_TOKEN_COPIADO_AQUI`.
    * No Swagger, clique no botão "Authorize" no canto superior direito e cole o token lá.
* **Resposta (200 OK):** Uma lista com as apostas cadastradas.

## 🏃 Como Rodar os Testes Automatizados

Para executar todos os testes unitários e de integração, utilize o seguinte comando na raiz do projeto:

```bash
# No Windows
./mvnw.cmd clean test

# No Linux ou macOS
./mvnw clean test
 ``` 

## 👨‍💻 Autores

- **João Saborido** - RM 98184
- **Lucca Alexandre** - RM 99700
- **Matheus Haruo** - RM 97663
- **Victor Wittner** - RM 98667