# 🚚 API CALCULO_FRETE - v2.0

> **De algoritmo em Portugol para API REST profissional com Spring Boot**

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203-blue)
![H2](https://img.shields.io/badge/Database-H2-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📸 Demonstração (Prints do Projeto Funcionando)

### 1. API no Navegador
`GET http://localhost:8080/api/frete/calcular?nome=Joao&precoBase=100`
```json
{
  "id": 1,
  "nomeCliente": "Joao",
  "precoBase": 100.0,
  "taxaFixa": 10.0,
  "precoFinal": 110.0,
  "status": "CALCULADO"
}
```

### 2. Banco de Dados H2
`http://localhost:8080/h2-console`
```sql
SELECT * FROM TB_PRODUTO;

ID | NOME_CLIENTE | PRECO_BASE | PRECO_FINAL | STATUS    | TAXA_FIXA
1  | Joao         | 100.0      | 110.0       | CALCULADO | 10.0
```

### 3. Swagger UI - Documentação Interativa
`http://localhost:8080/swagger-ui.html`

## 🎯 Regra de Negócio

**Algoritmo original em Portugol:**
```
preco_final = preco_base + taxa_fixa
```

**Traduzido para Java + Spring Boot:**
```java
double precoFinal = precoBase + appConfig.getTaxaFixaFrete(); // R$ 10.00
```

## 🏗️ Arquitetura Completa

```
                     ┌─────────────────┐
Navegador/Postman -> │   Controller    │  @RestController - Recebe requisições HTTP
                     │ FreteController │
                     └────────┬────────┘
                              ↓
                     ┌─────────────────┐
                     │     Service     │  @Service - Regra: preco_final = preco_base + 10
                     │  FreteService   │  Usa AppConfig (taxa) e Repository
                     └────────┬────────┘
                              ↓
                     ┌─────────────────┐       ┌──────────────┐
                     │   Repository    │ ----> │  H2 Database │  Tabela tb_produto
                     │ FreteRepository │       │  (memória)   │
                     └─────────────────┘       └──────────────┘
                              ↓
                     ┌─────────────────┐
                     │     Config      │  AppConfig + SwaggerConfig + application.properties
                     └─────────────────┘
```

### Onde fica cada pasta? (Padrão Mercado)

| Pasta | Finalidade | O que tem |
|-------|------------|-----------|
| `config/` | Configuração centralizada | `AppConfig.java` (taxa), `SwaggerConfig.java`, `DatabaseConfig` (no Java puro) |
| `model/` | Entidade / Tabela do banco | `Produto.java` - @Entity |
| `repository/` | Acesso ao banco | `FreteRepository.java` - extends JpaRepository (não escreve SQL) |
| `service/` | Regra de negócio | `FreteService.java` - cálculo do frete |
| `controller/` | API REST | `FreteController.java` - endpoints HTTP |
| `resources/` | Configurações | `application.properties` |

## 🚀 Como Rodar o Projeto

### Pré-requisitos
- JDK 17 ou 21 (JDK 26 funciona mas com warnings - use 17 para melhor performance)
- Maven 3.8+
- IntelliJ IDEA ou Eclipse

### Passo a Passo

```bash
# 1. Clone ou baixe o zip
git clone https://github.com/seu-usuario/calculo-frete-api.git

# 2. Abra no IntelliJ como projeto Maven
File > Open > pasta calculo_frete_completo_v-2.0

# 3. Aguarde baixar dependências (Spring Web, JPA, H2, Swagger)

# 4. Rode a aplicação
# Clique no play em CalculoFreteApplication.java
# OU no terminal:
mvn spring-boot:run
```

A API sobe em: `http://localhost:8080`

## 📚 Como Usar a API

### 1. Calcular Frete (GET - Mais simples para testar no navegador)

```http
GET http://localhost:8080/api/frete/calcular?nome=Joao&precoBase=100
```

**Resposta:**
```json
{
  "id": 1,
  "nomeCliente": "Joao",
  "precoBase": 100.0,
  "taxaFixa": 10.0,
  "precoFinal": 110.0,
  "status": "CALCULADO"
}
```

### 2. Calcular Frete via POST JSON (Padrão para Frontend)

```http
POST http://localhost:8080/api/frete/calcular-json
Content-Type: application/json

{
  "nomeCliente": "Maria",
  "precoBase": 250.50
}
```

**Resposta:** `precoFinal: 260.50`

### 3. Listar Todos os Cálculos

```http
GET http://localhost:8080/api/frete/listar
```

Retorna array com todos salvos no H2.

### 4. Buscar por Nome

```http
GET http://localhost:8080/api/frete/buscar?nome=Joao
```

### 5. Cálculo Simples (sem salvar no banco)

```http
GET http://localhost:8080/api/frete/simples?precoBase=100
# Retorna: 110.0
```

## 🗄️ Banco de Dados H2

Banco em memória, não precisa instalar MySQL.

- **Console:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:mem:frete_db`
- **User:** `sa`
- **Password:** (vazio)
- **SQL para testar:**
```sql
SELECT * FROM TB_PRODUTO;
SELECT * FROM TB_PRODUTO WHERE NOME_CLIENTE = 'Joao';
```

## 📖 Swagger - Documentação Interativa

Acesse e teste todos endpoints sem Postman:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/api-docs

No Swagger você pode clicar em "Try it out" e testar direto.


## 📱 Como Usar no Celular

### Opção 1: Mesma rede Wi-Fi (Mais rápido - sem deploy)

Seu PC e celular estão na mesma Wi-Fi, você pode acessar a API pelo celular!

**1. Descubra o IP do seu PC:**
- Windows: abra CMD e digite `ipconfig` -> procure IPv4 (ex: 192.168.1.10)
- No terminal do IntelliJ, a API sobe em `localhost:8080`

**2. Libere o firewall (Windows):**
- Painel de Controle > Firewall > Permitir app > Java

**3. No celular, abra o navegador:**
```
http://192.168.1.10:8080/
```
(Substitua 192.168.1.10 pelo seu IP)

**O que abre:**
- Página mobile bonita que criamos em `src/main/resources/static/index.html`
- Calcula frete direto no celular
- `http://192.168.1.10:8080/swagger-ui.html` - Swagger no celular
- `http://192.168.1.10:8080/h2-console` - Ver banco no celular

**Vídeo teste:** Digite nome e preço base e clique Calcular - funciona igual app nativo!

### Opção 2: Deploy na nuvem (Acesso de qualquer lugar)

Coloque sua API na internet de graça:

**Render.com (recomendado):**
1. Crie conta no https://render.com
2. New + > Web Service > Conecte seu GitHub
3. Build: `mvn clean package -DskipTests`
4. Start: `java -jar target/calculo-frete-1.0.0.jar`
5. Pronto! URL tipo `https://calculo-frete.onrender.com`

**Railway.app:**
1. https://railway.app > Deploy from GitHub
2. Ele detecta Maven automaticamente

Depois do deploy, no celular é só abrir:
```
https://seu-app.onrender.com/
https://seu-app.onrender.com/swagger-ui.html
```

### Opção 3: APK com WebView (Transformar em app Android)

Se quiser um APK de verdade:

1. Use https://appsgeyser.com ou https://gonative.io
2. Coloque a URL da sua API deployada
3. Gera APK e instala no celular

---

## 🔧 Configuração (application.properties)

```properties
server.port=8080

# H2 Database
spring.datasource.url=jdbc:h2:mem:frete_db
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Regra de Negócio - Antes estava no AppConfig.java
frete.taxa.fixa=10.00
frete.moeda=R$
frete.preco.minimo=0.01
frete.preco.maximo=1000000.00

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
```

Para mudar a taxa de frete, basta mudar `frete.taxa.fixa=10.00` sem recompilar!

## 💻 Versão Java Puro (Sem Spring Boot)

Dentro da pasta `/calculo_frete_java_puro` está a versão original para faculdade:

```
com/calculo_frete/
├── config/
│   ├── AppConfig.java       # Taxa fixa, mensagens
│   └── DatabaseConfig.java  # Simula conexão com banco HashMap
├── model/Produto.java
├── repository/FreteRepository.java
├── service/FreteService.java
├── controller/FreteController.java
└── Main.java
```

**Rodar Java puro:**
```bash
javac com/calculo_frete/config/*.java com/calculo_frete/model/*.java com/calculo_frete/repository/*.java com/calculo_frete/service/*.java com/calculo_frete/controller/*.java com/calculo_frete/Main.java

java com.calculo_frete.Main
```

## 🔄 Evolução do Projeto

| Versão | O que foi feito |
|--------|-----------------|
| v1.0 - Portugol | `preco_final = preco_base + 10` no Portugol Studio |
| v1.5 - Java Puro | Arquitetura Controller-Service-Repository + Config com HashMap |
| v2.0 - Spring Boot | API REST + JPA + H2 + Swagger (este projeto) |

## 🛠️ Tecnologias

- **Java 17+** - Linguagem
- **Spring Boot 3.2.0** - Framework web
- **Spring Data JPA** - Acesso a dados sem SQL manual
- **H2 Database** - Banco em memória
- **Springdoc OpenAPI 2.3.0** - Swagger documentação
- **Maven** - Gerenciador de dependências

## 👨‍💻 Autor

Desenvolvido a partir do algoritmo CALCULO_FRETE em Portugol.

**GitHub:** coloque seu link aqui
**LinkedIn:** coloque seu link aqui

---

### 🎓 Para Recrutadores

Este projeto demonstra:
- ✅ Tradução de regra de negócio de Portugol para Java
- ✅ Arquitetura em camadas (Controller-Service-Repository-Config)
- ✅ API REST com Spring Boot
- ✅ Persistência com JPA/Hibernate
- ✅ Documentação com Swagger/OpenAPI
- ✅ Boas práticas: injeção de dependência, @Value, application.properties
