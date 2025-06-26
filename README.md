# Loja AV2 - API Spring Boot com Autenticação JWT

Este projeto é uma API desenvolvida em Spring Boot com autenticação baseada em tokens JWT, utilizando Spring Security e banco de dados em memória H2. A aplicação contém endpoints para autenticação de usuários e acesso protegido a recursos.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- H2 Database
- Lombok
- Docker (opcional para deploy)

---

## 📦 Instalação

### Pré-requisitos

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- (Opcional) [Docker](https://www.docker.com/)

### Clonar o repositório

```bash
git clone https://github.com/seu-usuario/loja-av2.git
cd loja-av2/loja-av2
```

### Instalar dependências

```bash
mvn clean install
```

---

## ▶️ Execução Local

### Rodando com Maven

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:  
`http://localhost:8080`

### Console do H2 (banco de dados em memória)

`http://localhost:8080/h2-console`  
- JDBC URL: `jdbc:h2:mem:loja-db`  
- Usuário: `vini`  
- Senha: *(1212)*

---

## 🔐 Autenticação JWT

### Endpoints disponíveis

- `POST /auth/login`  
  - Requer `username` e `password` no body.
  - Retorna um token JWT válido.

- `POST /auth/register`  
  - Cria um novo usuário (username, password, roles).

- `GET /usuario`  
  - Requer token JWT no cabeçalho `Authorization: Bearer {token}`.
  - Retorna dados do usuário autenticado.

---

## 🚢 Deploy com Docker

### Construir a imagem

```bash
docker build -t loja-av2 .
```

### Executar o container

```bash
docker run -p 8080:8080 loja-av2
```

---

## ✅ Testes

O projeto inclui testes automatizados utilizando JUnit. Para rodá-los:

```bash
mvn test
```

---

## 📁 Estrutura de Diretórios

```
Av2-av2/
├── Dockerfile
├── pom.xml
├── src/
│   └── main/
│       └── java/com/example/loja/
│           ├── controller/
│           ├── config/
│           ├── model/
│           ├── repository/
│           ├── service/
│           ├── filter/
│           └── LojaApplication.java
│   └── resources/
│       └── application.yml
```

---

## ✍️ Autor
Vinicius Alexandre Sousa de Oliveira  


---

## 📜 Licença

Este projeto é livre para uso educacional.
