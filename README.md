# Aplicativo de Evacuação e Rotas Seguras - API Back-end

Bem-vindo ao repositório da API Back-end do nosso Aplicativo de Evacuação e Rotas Seguras. Esta API é o componente central da nossa solução, responsável pelo gerenciamento de dados, lógica de negócios e por servir as informações para a interface do usuário.

## Desenvolvedores

* **Gustavo** - RM: 561055
* **Arthur** - RM: 560820

## Sobre Este Back-end

No desenvolvimento desta API, nosso foco foi criar um serviço robusto, eficiente e escalável para o Aplicativo de Evacuação e Rotas Seguras. Utilizamos o framework Quarkus para otimizar a performance e o consumo de recursos, integrando com um banco de dados Oracle para persistência dos dados. A API gerencia informações vitais como alertas, áreas de risco, abrigos, ocorrências, campanhas e relatos de usuários.

## Funcionalidades da API

* Gerenciamento completo (CRUD) para as entidades: Usuários, Alertas, Áreas de Risco, Abrigos Seguros, Ocorrências, Campanhas e Relatos.
* Exposição de endpoints RESTful seguindo as melhores práticas para consumo pelo front-end.
* Implementação da lógica de negócios e validações pertinentes a cada funcionalidade.

## Arquitetura e Implementação do Back-end

Para o desenvolvimento do nosso back-end, adotamos uma arquitetura em camadas com Java e Quarkus, visando organização e manutenibilidade[cite: 8, 32]:

* **Entidades (`entity`):** Realizamos o mapeamento das tabelas do banco de dados Oracle utilizando JPA (Java Persistence API)[cite: 32, 33]. Cada classe nesta camada representa uma tabela e suas colunas[cite: 33, 34, 49].
* **Repositórios (`repository`):** Para o acesso aos dados, utilizamos o padrão Repository com o Hibernate ORM with Panache do Quarkus[cite: 2, 35, 36]. Isso simplifica as operações de banco de dados (consultas, inserts, deletes)[cite: 35, 37, 55].
* **Serviços (`service`):** Esta camada (também conhecida como Camada BO - Business Object) contém a lógica de negócios da nossa aplicação[cite: 32, 39]. Os serviços orquestram as operações, chamando os repositórios e aplicando regras de negócio antes de expor os dados para os controllers[cite: 39, 40, 41].
* **Controllers (`controller` / Resource):** São responsáveis por receber as requisições HTTP (REST), validar dados iniciais e invocar os métodos apropriados na camada de serviço[cite: 32, 42, 43, 44]. Utilizamos JAX-RS para definir os endpoints, e Jackson para a serialização/desserialização de JSON[cite: 4, 26].

### Configuração e Conexão com Banco de Dados

* As configurações da aplicação, incluindo a conexão com o banco de dados Oracle da FIAP, são gerenciadas no arquivo `src/main/resources/application.properties`[cite: 8, 12, 13, 30].
* Para o ambiente de produção no Railway, as credenciais do banco (`DB_USER`, `DB_PASS`, `DB_URL`) são lidas de variáveis de ambiente, conforme configurado no `application.properties` (`quarkus.datasource.username=${DB_USER}`, etc.)[cite: 78].
* A propriedade `quarkus.hibernate-orm.database.generation=update` foi utilizada durante o desenvolvimento para auxiliar na criação/atualização do schema do banco com base nas entidades[cite: 31].

### Ambiente de Desenvolvimento e Execução Local

* Para o desenvolvimento, utilizamos JDK 21 e Maven (através do Maven Wrapper `mvnw` [cite: 9, 19]).
* A API é iniciada localmente com o comando `./mvnw quarkus:dev` e fica disponível, por padrão, em `http://localhost:8080`[cite: 27].

## Tecnologias Chave do Back-end

* Java 21, Quarkus, Maven
* Hibernate ORM with Panache, JPA
* JAX-RS, RESTful Webservices, Jackson
* Oracle Database

## Deployment (API na Nuvem)

* A API foi conteinerizada utilizando um `Dockerfile`[cite: 75, 76].
* O deploy foi realizado na plataforma **Railway**.
* **URL da API em Produção:** `https://app-evacuacao-segura-api-production.up.railway.app/`

---
