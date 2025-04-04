<div align="center" width="100%">
    <img src="../asserts/logo-wos.jpg" alt="logo" width="200" height="auto" />
</div>

<br>

<div align="center">

![](https://img.shields.io/badge/Status-Em%20Desenvolvimento-orange)
</div>

<div align="center">

![](https://img.shields.io/badge/Autor-Wesley%20Oliveira%20Santos-brightgreen)
![](https://img.shields.io/badge/Language-java-brightgreen)
![](https://img.shields.io/badge/Framework-springboot-brightgreen)

</div>

<div align="center">

  ## Desafio Backend do PicPay
  <img src="../asserts/picpay-logo.jpg" alt="logo" width="200" height="auto" />

  Confira o enunciado completo, [clicando aqui](./problem.md).

</div>

<div align="center">

  ## Arquitetura
  <img src="../asserts/em-construcao.jpg" alt="logo" width="200" height="auto" />

  ### Modelo de banco de dados
  <img src="../asserts/db-transactions.png" alt="logo" width="200" height="auto" />


</div>

##  Pré - requisitos

- [ `Java 21+` ](https://www.oracle.com/java/technologies/downloads/#java21)
- [ `Apache Maven`](https://maven.apache.org/download.cgi)
- [ `Docker` ](https://www.docker.com/)
- [ `Docker-Compose` ](https://docs.docker.com/compose/install/)
- [ `Grafana k6` ](https://grafana.com/docs/k6/latest/set-up/install-k6/)

## Stack Utilizada
- **Java 21**
- **Spring Boot 3**
- **Spring Data**
- **Spring Docs**
- **Flyway**
- **Mysql**
- **Datadog**
- **Grafana k6 (Load testing for engineering teams)**

## Aplicações

### app-transaction-api
| Nome                | Descrição |
|---------------------|-------|
| app-transaction-api | Esse microserviço tem a reposnsabilidade de tratar todo core do contexto de transferencia. |

#### Portas
| Aplicação          | Porta |
|--------------------|-------|
| app-transaction-api| 8080  |

#### Setup

- ##### Variáveis de ambiente

| Variável de Ambiente  | Descrição                                                                      |
|-----------------------|--------------------------------------------------------------------------------|
| `DATABASE_HOST`       | Especifique o host do banco de dados `MySQL` a ser usado (padrão `localhost` ) |
| `DATABASE_PORT`       | Especifique a porta do banco de dados `MySQL` a ser usada (padrão `3306` )     |
| `DATABASE_USERNAME`   | Especifique o user do `MySQL` a ser usado (padrão `app` )                      |
| `DATABASE_PASSWORD`   | Especifique a password do `MySQL` a ser usado (padrão `app` )                  |
| `DATADOG_API_KEY`     | Especifique o api key do `DATADOG`  a ser usado (obtido no portal do datadog)  |
| `DATADOG_APP_KEY`     | Especifique o app key do `DATADOG`  a ser usado (obtido no portal do datadog)  |
| `DATADOG_URI`         | Especifique o uri do `DATADOG`  a ser usado (obtido no portal do datadog)      |

#### Links Úteis: Ferramentas e Plataformas

- OpenAPI 
  - http://localhost:8080/swagger-ui/index.html#/
#### Automação de Testes

- ##### Teste de Unitários
  ```
  ./mvnw test
  ```

- ##### Teste de Integração
  ```
  ./mvnw verify -Dskip.ut=true -Dskip.it=false
  ```

- ##### Teste de Mutação
  ```
  ./mvnw clean test-compile org.pitest:pitest-maven:mutationCoverage
  ```

- ##### Teste de Smock
  ```
  K6_WEB_DASHBOARD=true K6_WEB_DASHBORAD_EXPORT=report.hml k6 run -e BASE_URL=http://localhost:8080 src/main/resources/k6/smock-testing/test_api_users_get_paginated_smock.js
  ```

- ##### Teste de Performance
  ```
  K6_WEB_DASHBOARD=true K6_WEB_DASHBORAD_EXPORT=report.hml k6 run -e BASE_URL=http://localhost:8080 src/main/resources/k6/load-testing/test_api_users_get_paginated_permormance.js
  ```

- ##### Teste de Stress
  ```
  K6_WEB_DASHBOARD=true K6_WEB_DASHBORAD_EXPORT=report.hml k6 run -e BASE_URL=http://localhost:8080 src/main/resources/k6/stress-testing/test_api_users_get_paginated_stress.js
  ```

</br>
<a href="https://www.linkedin.com/in/wesleyosantos91/" target="_blank">
  <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank" />
</a>

</br>
<b>Developed by Wesley Oliveira Santos</b>