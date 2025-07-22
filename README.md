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
git clone https://github.com/mateus-mendess/product-api
cd https://github.com/mateus-mendess/product-api
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

## 📌 Observações
Projeto com foco no back-end e boas práticas.

Nenhum frontend foi implementado.

Toda a estrutura segue o padrão MVC (Model-View-Controller).

Código documentado, com DTOs separados para request/response.

## ✍️ Autor
Desenvolvido por **Mateus Mendes** – projeto de estudo pessoal.
