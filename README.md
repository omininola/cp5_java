# CP5 Java - RSA (Parte 1) & Spring Security (Parte 2)

## Integrantes

| Nome |  RM  |
| ---- | :--: |
| Otavio Miklos Nogueira | 554513 |
| Luciayla Yumi Kawakami | 557987 |
| Sofia Andrade Petruk | 556585 |
| Lucas de Assis Fialho | 557884 |
| Luiz Sadao Kamada | 557652 |
| Raphaela de Oliveira Tatto | 554983 |

## IDE Utilizadas

- Visual Studio Code
- IntelliJ

## Geral

### Inicialização

```bash
# Clonar o repositório
git clone https://github.com/omininola/cp5_java

# Mudar o diretório
cd cp5_java
```

## Parte 1

### Inicialização

```bash
# Mudar o diretório para a parte 1
cd parte1
```

Abra 2 terminais dentro da pasta do projeto

```bash
# Execute o Servidor
mvn exec:java -Dexec.mainClass="br.com.fiap.cp5.socket.Server"

# Execute o Cliente
mvn exec:java -Dexec.mainClass="br.com.fiap.cp5.socket.Client"

# Digite qualquer mensagem no Cliente ou Servidor
# A mensagem é encriptografada usando RSA
# A mensagem deve ser enviada para o outro terminal já descriptografada
Ex: Olá!
```

## Parte 2

### Tecnologias Utilizadas

- **Java 17** - Linguagem de programação principal
- **Spring Boot 3.5.5** - Framework para desenvolvimento de aplicações Java
- **Spring Data JPA** - Abstração para persistência de dados
- **Spring Web MVC** - Framework web para APIs REST e páginas web
- **Thymeleaf** - Engine de templates para renderização de páginas HTML
- **Oracle Database** - Sistema de gerenciamento de banco de dados
- **Lombok** - Biblioteca para redução de código boilerplate
- **Maven** - Ferramenta de automação e gerenciamento de dependências
- **HTML5/CSS3** - Tecnologias front-end para interface do usuário

### Inicialização

```bash
# Mudar o diretório para a parte 2
cd parte2
```

## Parte 2

### 2. Configuração do Banco de Dados
Edite o arquivo `src/main/resources/application.properties` com suas credenciais:
```properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3. Compilação e Execução
```bash
# Compilar o projeto
mvn clean compile

# Executar testes
mvn test

# Executar a aplicação
mvn spring-boot:run
```

### 4. Acesso à Aplicação
Abra o navegador e acesse: `http://localhost:8080/login`
    - Você pode tanto criar seu usuário ou usar o usuário ADMIN
      - email: admin@email.com
      - senha: admin123

## Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/products` | Lista todos os produtos |
| GET | `/products/new` | Formulário de novo produto |
| POST | `/products` | Cria um novo produto |
| GET | `/products/{id}/edit` | Formulário de edição |
| PUT | `/products/{id}` | Atualiza um produto |
| DELETE | `/products/{id}` | Remove um produto |
| GET | `/login` | Fazer o login da página |
| GET | `/home` | Página principal com os botões para outras páginas |
| GET | `/access-denied` | Página que mostra que você não tem autorização para entrar na página|
| GET | `/users` | Listar usuários de acordo com seu ROLE|
| GET | `/products/new` | Formulário de novo usuário |
| POST | `/products` | Cria um novo usuário |

## Depedências usadas no projeto

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>annotationProcessor</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc11</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-database-oracle</artifactId>
    </dependency>

</dependencies>
```
