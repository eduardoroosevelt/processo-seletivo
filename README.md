# Inscrição do candidato:

### Número da inscrição: 8781
### CPF: 05921855162
### Perfil: DESENVOLVEDOR JAVA (BACK-END) - PLENO
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
## Como Testar

### MinIO
2. Acesse   `MiniIO `
```bash
http://localhost:9001/
```
      2.1 - Usuário de acesso: admin123
      2.2 - Senha de acesso: strongpassword123
      2.3 - Verifique que foi criado um bucket com nome fotos
### Api

3. Acesse  `Swagger `

```shellscript
http://localhost:8083/apiseletivo/swagger-ui/index.html#/
```

4. Realizar AUTENTICAÇÃO
  - 4.1 - Execute o serviço **POST /auth/login** que está na **AUTENTICAÇÃO**. Para autenticar utilize o **usuário: admin**, **senha: password**
  - 4.2 - Copie o acessToken e insira na variável **Authorize** (canto superior direito).
  - 4.3 - Após esta ação você estará autenticado. Tempo token: **5 minutos**.
  - 4.4 - É possível utilizar o refresh-token, através do serviço **POST /auth/refresh-token**. Neste serviço cole o refreshToken gerado no **/auth/login** e gere um novo token. Cole este token variável authorize.

5. CRUD de CIDADE **(Recurso opcional)**
  - 5.1 - Utilize o serviço **POST /cidades** para criar uma nova cidade. Caso não queira criar cidade, já foram inseridas 05 cidades no start da aplicação.
  - 5.2 - Verifique as cidades existentes: Utilize o serviço **GET /cidades** para verificar as cidades que já existem de forma paginado e o serviço **GET /cidades/{id}** para buscar uma cidade específica.
  - 5.3 - Utilize o serviço **PUT /cidades/{id}** para atualizar uma cidade.
  - 5.4 - Utilize o serviço **DELETE /cidades/{id}** para excluir uma cidade.

6. CRUD de UNIDADE
  - 6.1 - Utilize o serviço **POST /unidades** para criar uma nova unidade.
  - 6.2 - Verifique as unidades existentes: Utilize o serviço **GET /unidades** para verificar as unidades que já existem de forma paginado e o serviço **GET /unidades/{id}** para buscar uma unidade específica.
  - 6.3 - Utilize o serviço **PUT /unidades/{id}** para atualizar uma unidade.
  - 6.4 - Utilize o serviço **DELETE /unidades/{id}** para excluir uma unidade.

7. CRUD de Servidor Efetivo
   - 7.1 - Utilize o serviço **POST /servidor-efetivo** para criar um novo servidor efetivo.
        - 7.1.1- Na criação de um servidor efetivo é necessário informar a pessoa completa.
        - 7.1.2- Na criação de um servidor efetivo é necessário passar uma lista de endereços completa
   - 7.2 - Verifique os servidores efetivos existentes: Utilize o serviço **GET /servidor-efetivo/** para verificar as servidores efetivos que já existem de forma paginado e o serviço **GET servidor-efetivo/{pesId}** para buscar uma servidor efetivo específico.
   - 7.3 - Utilize o serviço **PUT /servidor-efetivo/{pesId}** para atualizar um servidor
   - 7.4 - Utilize o serviço **POST /servidor-efetivo/upload** para inserir uma ou mais fotos em um servidor efetivo
   - 7.5 - Utilize o serviço **DELETE /servidor-efetivo/{pesId}** para excluir uma servidor efetivo.
   - 7.6 - Utilize o serviço **GET /servidor-efetivo/endereco-funcional/**,que tem como parâmetro a variável nome, para buscar o endereco funcional a partir de uma parte do nome, apenas para servidores efetivos.


8. CRUD de Servidor Temporário
   - 8.1 - Utilize o serviço **POST /servidor-temporario/** para criar um novo servidor temporário.
        - 8.1.1- Na criação de um servidor temporário é necessário informar a pessoa completa.
        - 8.1.2- Na criação de um servidor temporário é necessário passar uma lista de endereços completa
   - 8.2 - Verifique os servidores temporários existentes: Utilize o serviço **GET /servidor-temporario/** para verificar as servidores temporários que já existem de forma paginado e o serviço **GET /servidor-temporario/{pesId}** para buscar uma servidor temporário específico.
   - 8.3 - Utilize o serviço **PUT /servidor-temporario/{pesId}** para atualizar um servidor temporário.
   - 8.4 - Utilize o serviço **POST /servidor-efetivo/upload** para inserir uma ou mais fotos em um servidor temporário.
   - 8.5 -  Utilize o serviço **DELETE /servidor-temporario/{pesId}** para excluir um servidor temporário.

9. CRUD de Lotação
   - 9.1 - Utilize o serviço **POST /lotacao/** para criar uma nova lotação.
        - 9.1.1- Na criação de uma lotação não é necessário informar a pessoa completa, somente o id da pessoa.
        - 9.1.2- Na criação de uma lotação não é necessário informar a unidade completa, somente o id da unidade.
   - 9.2 - Verifique as lotações existentes: Utilize o serviço **GET /lotacao** para verificar as lotações que já existem de forma paginado e o serviço **GET /lotacao/{lotId}** para buscar uma lotação específica.
   - 9.3 - Utilize o serviço **PUT /lotacao/{lotId}** para atualizar uma lotação.
   - 9.4 - Utilize o serviço **DELETE /lotacao/{lotId}** para excluir uma lotação.

10. CRUD de Lotação
  - 10.1 - Utilize o serviço **GET /consultas** para buscar os servidores efetivos lotados em determinada unidade. Para ter resutados neste serviço primeiro é necessário criar alguma lotação **9. CRUD de Lotação**
  - 10.2 - Utilize o serviço **GET /consultas/buscar-por-endereco-funcional** para buscar o endereço funcional (da unidade onde o servidor é lotado) a partir de uma parte do nome do servidor efetivo. 



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


