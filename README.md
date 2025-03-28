# Inscrição do candidato:

### Número da inscrição: 8209
### CPF: 05921855162
### Perfil: DESENVOLVEDOR JAVA (BACK-END) - SÊNIOR
### Contato: (65) 9 9229-0278

# Projeto: Sistema de Gestão com Clean Architecture

Este projeto é uma aplicação desenvolvida com **Spring Boot** utilizando **Java 17**, seguindo os princípios de **Clean Architecture** e **DDD**. Ele integra tecnologias modernas como **MinIO**, **PostgreSQL** e **NGINX** para oferecer uma solução robusta e escalável.

---

## Tecnologias Utilizadas

- **Spring Boot** com Java 17
- **MinIO**:latest
- **PostgreSQL**:latest
- **NGINX**:latest

---

## Como Executar

1. Navegue até a pasta `sandbox` no projeto.
2. Execute o seguinte comando no terminal dentro dessa pasta:

   ```bash
   docker-compose up
   ```

   Este comando iniciará os seguintes containers:
   - MinIO
   - PostgreSQL
   - NGINX
   - Aplicação Spring Boot

---

## Arquitetura da Solução

A aplicação utiliza **NGINX** como proxy reverso para resolver problemas de geração de links temporários no **MinIO**. 

### Problema
O Spring Boot gera links temporários utilizando o nome do container do MinIO, que não é acessível externamente. Alterar a URL para `localhost:9000` causa problemas de assinatura.

### Solução
Antes de enviar o link ao cliente, a URL é alterada para `http://localhost:9003` (URL do NGINX). O NGINX redireciona a requisição para o MinIO utilizando o nome do container, resolvendo o problema de acessibilidade.

**Sugestão:** Adicione um diagrama para ilustrar o fluxo de redirecionamento entre os componentes.

---

## Arquitetura do Projeto

O projeto segue os princípios de **Clean Architecture** e está dividido em três módulos principais:

### 1. Domain
- **Responsabilidade:** Contém as regras de negócio puras, sem dependências externas.
- **Exemplo:** 
  - Classe `Endereco.java`: Validações como nome não vazio e limite de caracteres.
  - Interface `EnderecoGateway.java`: Define métodos para operações no banco de dados, como:
    ```java
    Endereco save(Endereco endereco);
    ```

### 2. Application
- **Responsabilidade:** Implementa os casos de uso do sistema.
- **Exemplo:**
  - Classe abstrata `CreateEnderecoUseCase.java`: Define entradas e saídas do caso de uso.
  - Implementação concreta `DefaultCreateEnderecoUseCase.java`: Depende de `EnderecoGateway` para persistência.

### 3. Infrastructure
- **Responsabilidade:** Configura dependências e implementa os gateways.
- **Exemplo:**
  - Integração com **Spring Web**, **Spring Data JPA** e **PostgreSQL**.
  - Implementação da interface `EnderecoGateway`.

**Fluxo Geral:**  
`Domain` → `Application` → `Infrastructure`

**Sugestão:** Adicione um diagrama para ilustrar a interação entre os módulos.

---

## Exemplo de Caso de Uso: Criação de Endereço

1. **Domain:**
   - Validações na classe `Endereco.java`:
     - Nome não pode ser vazio ou maior que 200 caracteres.
     - Cidade não pode ser vazia ou nula.

2. **Application:**
   - Caso de uso `CreateEnderecoUseCase.java` verifica se a cidade existe no banco.
   - Lança exceção caso a cidade não exista.

3. **Infrastructure:**
   - Implementa `EnderecoGateway` com **Spring Data JPA** para persistência no PostgreSQL.

---

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

---

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

**Sugestão:** Inclua imagens ou diagramas para ilustrar a arquitetura e o fluxo de execução. Por exemplo:
- Diagrama de containers (Docker Compose).
- Fluxo de redirecionamento de URLs com NGINX.
- Estrutura modular do projeto (Domain, Application, Infrastructure).


