# 📚 API RestFull – Estudo com Spring Boot

Este projeto foi desenvolvido como **caso de estudo** para consolidar conhecimentos com as principais ferramentas do ecossistema Spring. Ele oferece uma API RESTful com autenticação stateless usando **JWT**, além de CRUD completo para usuários e produtos.

---

## 🚀 Funcionalidades

- ✅ Cadastro, listagem, atualização e exclusão de usuários
- ✅ Cadastro, listagem, atualização e exclusão de produtos
- ✅ Autenticação de usuários com **token JWT**
- ✅ Proteção de endpoints com **Spring Security**
- ✅ Separação clara entre DTOs e entidades usando **MapStruct**
- ✅ Versionamento de banco de dados com **Flyway**

---

## 🛠️ Tecnologias utilizadas

| Tecnologia        | Descrição                                  |
|-------------------|--------------------------------------------|
| Java 17           | Linguagem principal                        |
| Spring Boot       | Framework principal da aplicação           |
| Spring Security   | Autenticação e autorização de endpoints    |
| JWT               | Geração e validação de token de acesso     |
| JPA + Hibernate   | Persistência de dados                      |
| MapStruct         | Conversão entre DTOs e Entidades           |
| PostgreSQL        | Banco de dados relacional                  |
| Flyway            | Versionamento e migração do banco de dados |
| Maven             | Gerenciador de dependências e builds       |

---

## ⚙️ Como executar localmente

1. **Clone o repositório**
```bash
git clone https://github.com/mateus-mendess/api-restful-springboot
cd https://github.com/mateus-mendess/api-restful-springboot
```
2. **Configure o banco de dados PostgreSQL**

Ajuste o arquivo `application.properties` com os dados do seu banco:

```bash
spring.application.name=ApiRestFull

#DATABASE
spring.datasource.url= ${DB_URL}
spring.datasource.username= ${DB_USER}
spring.datasource.password= ${DB_PASSWORD}
spring.datasource.driver-class-name= org.postgresql.Driver

#JPA + HIBERNATE
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto= none

#JWT
api.security.token.secret = ${JWT_SECRET}
```

3. **Execute a aplicação**

A aplicação estará disponível em: `http://localhost:8080`

## 🔐 Autenticação com JWT

A autenticação segue o padrão stateless com token JWT. Para acessar os endpoints protegidos:

1. **Faça login com seu usuário no endpoint `/auth/login`**

```bash
{
  "email": "mateus.mendes@example.com",
  "password": "mendes123@"
}
```
2. **Receba o token JWT**

```bash
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvLnNpbHZhQGV4YW1wbG
  UuY29tIiwiaXNzIjoiYXBpLXJlc3QtZnVsbCIsImV4cCI6MTc1MzE0OD
  MzNH0.wZYbziDON-aSm45WOJnLdsNog9pt0Cg3wfY9UX860qM"
}
```
3. **Envie o token no cabeçalho Authorization em cada requisição:**

```bash
  Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvLnNpbHZhQGV4YW1wbG
  UuY29tIiwiaXNzIjoiYXBpLXJlc3QtZnVsbCIsImV4cCI6MTc1MzE0OD
  MzNH0.wZYbziDON-aSm45WOJnLdsNog9pt0Cg3wfY9UX860qM
```

## 🧠 Sobre o Projeto:

**🧱 Arquitetura Utilizada**

A aplicação segue a arquitetura MVC (Model-View-Controller) com camadas bem definidas. Abaixo está a estrutura de pacotes do projeto:
```bash
src/
├── config             # Configurações gerais (segurança, CORS, JWT, etc)
├── controller         # Responsável pelas requisições HTTP e regras de roteamento
├── domain             # Domínio principal do sistema
│   ├── entity         # Entidades do banco de dados (JPA Entities)
│   ├── enums          # Enumerações utilizadas no sistema
│   ├── repository     # Interfaces JPA para acesso aos dados
│   └── service        # Regras de negócio relacionadas ao domínio
├── dto                # Data Transfer Objects (request/response)
│   ├── request        # Objetos usados para entrada de dados (POST, PUT)
│   └── response       # Objetos usados para saída de dados (GET)
├── exception          # Definições de exceções customizadas
├── handler            # Manipuladores globais de exceções (ControllerAdvice)
├── mapper             # Interfaces MapStruct para conversão entre DTO e Entity
├── security           # Filtros, tokens, autenticação e autorização

```

**📐 Diagrama de Entidade e Relacionamento (ERD)**

A base do projeto é composta pelas entidades `User`, `Roles` e `Product`, com o relacionamento mais relevante sendo:

```bash
User (N) --- (1) Roles
```

Cada usuário está associado a um papel (role) que define suas permissões no sistema. Por outro lado, um único papel pode estar vinculado a vários usuários.

**🧾 Separação de DTOs**

Para garantir clareza e segurança na troca de dados com a API, o projeto separa os DTOs em duas categorias:

`Request DTOs` – usados para entrada de dados (evitam que campos desnecessários ou sensíveis sejam enviados)

`Response DTOs` – usados para saída de dados (evitam expor senhas, IDs técnicos ou atributos internos)

Exemplos:

```bash
UserRequestDTO / UserResponseDTO

ProductRequestDTO / ProductResponseDTO
```
A conversão entre DTOs e entidades é feita com `MapStruct`, o que garante código limpo, rápido e sem boilerplate.

**🔐 Configuração de Segurança (Spring Security)**

A segurança da API é baseada no padrão stateless, utilizando tokens JWT (JSON Web Token) para autenticação e autorização. Todas as configurações de segurança são centralizadas no método `securityFilterChain(HttpSecurity httpSecurity)`.

```bash
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/product/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/product/{id}").hasRole("ADMIN")
                    .anyRequest().authenticated())
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}
```
Observação:
As autorizações configuradas nas rotas, bem como os papéis atribuídos a cada operação, não seguem necessariamente um padrão de projeto ideal. Essa estrutura foi definida apenas para fins didáticos, com o objetivo de estudar o funcionamento do Spring Security, controle de acesso com base em papéis e autenticação via JWT.


## 📌 Observações
-  Projeto com foco no back-end e boas práticas.
-  Nenhum frontend foi implementado.
-  Para testar a API, utilize o Postman, Insomnia ou qualquer outro software similar de client HTTP.


## ✍️ Autor
Desenvolvido por **Mateus Mendes** – projeto de estudo pessoal.
