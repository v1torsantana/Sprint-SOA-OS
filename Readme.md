# Projeto de Arquitetura Orientada a Serviços e Web Services

## Descrição do Projeto

Este projeto é uma aplicação de back-end desenvolvida com **Spring Boot** para a disciplina de Arquitetura Orientada a Serviços e Web Services. A aplicação implementa uma API RESTful completa para gerenciar um recurso de apostas (`Aposta`).

A arquitetura do projeto segue o padrão de camadas (Controller, Service, Repository) para garantir a separação de responsabilidades, alta coesão e baixo acoplamento. A API inclui funcionalidades de CRUD (Create, Read, Update, Delete), validações de dados e tratamento de erros centralizado.

## Tecnologias Utilizadas

* **Linguagem de Programação:** Java
* **Framework:** Spring Boot 3.x
* **Gerenciador de Dependências:** Maven
* **Banco de Dados:** Oracle (ou outro, dependendo da sua configuração)
* **ORM:** Spring Data JPA / Hibernate
* **Validação de Dados:** Jakarta Validation (com a biblioteca `spring-boot-starter-validation`)
* **Lombok:** Para reduzir a verbosidade do código (getters, setters, construtores).
* **Ferramentas de Teste da API:** Postman / Insomnia

## Estrutura do Projeto

O projeto é organizado em pacotes que representam as camadas da arquitetura:

* **`br.com.fiap.betadvisor.controller`**: Contém o `ApostaController`, responsável por receber as requisições HTTP e retornar as respostas.
* **`br.com.fiap.betadvisor.service`**: Contém o `ApostaService`, onde a lógica de negócio é implementada.
* **`br.com.fiap.betadvisor.repository`**: Contém o `ApostaRepository`, que interage com o banco de dados usando Spring Data JPA.
* **`br.com.fiap.betadvisor.dto`**: Contém os DTOs (`ApostaRequestDTO` e `ApostaResponseDTO`), que são objetos de transferência de dados para padronizar as requisições e respostas.
* **`br.com.fiap.betadvisor.exception`**: Contém a exceção personalizada `ApostaNotFoundException` e o `GlobalExceptionHandler` para tratar erros globalmente.

## Como Configurar e Executar o Projeto

**Pré-requisitos:**
* JDK 17 ou superior
* Maven 3.6+
* Um banco de dados Oracle (ou outro, configurado no `application.properties`)

1.  **Clone o repositório:**
    ```sh
    git clone <URL_DO_SEU_REPOSITORIO>
    ```

2.  **Configurar o Banco de Dados:**
    * Abra o arquivo `src/main/resources/application.properties`.
    * Preencha as informações de conexão do seu banco de dados:
        ```properties
        spring.datasource.url=<SUA_URL>
        spring.datasource.username=<SEU_USUARIO>
        spring.datasource.password=<SUA_SENHA>
        ```
    * (Se você não usou Flyway) Certifique-se de que a propriedade `spring.jpa.hibernate.ddl-auto` está configurada como `update` para que o Hibernate crie a tabela automaticamente.

3.  **Executar a aplicação:**
    * No terminal, navegue até a pasta raiz do projeto.
    * Execute a aplicação com o Maven:
        ```sh
        ./mvnw spring-boot:run
        ```
    * A aplicação estará disponível em `http://localhost:8080`.

## Exemplos de Requisições da API

Utilize uma ferramenta como o Postman ou Insomnia para testar os endpoints.

### **1. Criar uma Aposta (POST)**

* **URL:** `http://localhost:8080/api/apostas`
* **Método:** `POST`
* **Body (JSON):**
    ```json
    {
        "time": "Flamengo",
        "valorAposta": 50.00,
        "odd": 1.55
    }
    ```
* **Resposta Esperada (Status 201 Created):**
    ```json
    {
        "id": 1,
        "time": "Flamengo",
        "valorAposta": 50.0,
        "odd": 1.55,
        "dataAposta": "2025-09-21T10:00:00.000+00:00"
    }
    ```

### **2. Consultar Todas as Apostas (GET)**

* **URL:** `http://localhost:8080/api/apostas`
* **Método:** `GET`
* **Resposta Esperada (Status 200 OK):**
    ```json
    [
        {
            "id": 1,
            "time": "Flamengo",
            "valorAposta": 50.0,
            "odd": 1.55,
            "dataAposta": "2025-09-21T10:00:00.000+00:00"
        }
    ]
    ```

### **3. Consultar uma Aposta por ID (GET)**

* **URL:** `http://localhost:8080/api/apostas/1`
* **Método:** `GET`
* **Resposta Esperada (Status 200 OK):**
    ```json
    {
        "id": 1,
        "time": "Flamengo",
        "valorAposta": 50.0,
        "odd": 1.55,
        "dataAposta": "2025-09-21T10:00:00.000+00:00"
    }
    ```

### **4. Atualizar uma Aposta (PUT)**

* **URL:** `http://localhost:8080/api/apostas/1`
* **Método:** `PUT`
* **Body (JSON):**
    ```json
    {
        "time": "Vasco",
        "valorAposta": 60.00,
        "odd": 1.80
    }
    ```
* **Resposta Esperada (Status 200 OK):**
    ```json
    {
        "id": 1,
        "time": "Vasco",
        "valorAposta": 60.0,
        "odd": 1.80,
        "dataAposta": "2025-09-21T10:00:00.000+00:00"
    }
    ```

### **5. Deletar uma Aposta (DELETE)**

* **URL:** `http://localhost:8080/api/apostas/1`
* **Método:** `DELETE`
* **Resposta Esperada (Status 204 No Content):**
    * Não há corpo de resposta.