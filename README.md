# Simulado - Alocação de Recursos de Cluster com Spring Boot

Este projeto é um **simulado** para a disciplina de Programação Web.  
O objetivo é implementar um backend em **Spring Boot** para gerenciar operações de alocação e liberação de recursos em um cluster computacional.  
Durante a avaliação, não será permitido o uso de ferramentas externas ou automação de código.  

---

## Objetivo
A aplicação deve gerenciar operações sobre recursos do cluster (CPU e memória), garantindo que as regras de alocação e liberação sejam respeitadas.  
As operações são registradas em banco de dados e o estado atual do cluster é calculado a cada requisição.

---
## Banco de Dados

O projeto utiliza **MySQL**.
A configuração padrão está no arquivo `application.properties`:

```properties
spring.application.name=simulado-pw

spring.datasource.url=jdbc:mysql://localhost:3306/simulado
spring.datasource.username=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
```

Altere o usuário, senha ou nome do banco conforme o seu ambiente.

---

## Como Executar o Backend

1. Crie o banco de dados no MySQL:

2. Configure `application.properties` caso necessário (usuário e senha do MySQL).

3. Compile e execute o projeto Spring Boot:

   ```bash
   ./mvnw spring-boot:run
   ```
---

## Critérios de Avaliação
* O projeto deve ser apresentado em sala, em pleno funcionamento.
* Não será permitido uso de ferramentas de automação de código ou consulta a fontes externas durante a avaliação.
* Códigos sobrando no projeto resultarão em penalização.
* Apenas projetos apresentados em sala e funcionais serão avaliados.

---

## Observações

Este projeto faz parte de um **simulado** e não corresponde a uma aplicação final de produção.
Antes da entrega, é obrigatório apresentar o sistema funcionando ao professor para validação.

