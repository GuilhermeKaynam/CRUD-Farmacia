# CRUD Farmácia — API REST (em desenvolvimento)

Estrutura inicial de uma API REST para uma farmácia, gerada com Spring Initializr durante o bootcamp Generation Brasil. Até o momento contém apenas a configuração do projeto (dependências e classe principal); entidades, repositórios e controllers ainda não foram implementados.

## Dependências configuradas

- Java 17
- Spring Boot (Web, Data JPA, Validation, DevTools)
- MySQL Connector
- Maven

## Planejado

- Entidades `Produto` e `Categoria` com relacionamento um-para-muitos
- CRUD de produtos e categorias
- Consultas por nome e por faixa de preço
- Integração com o front-end [ProjetoFinalBloco3](https://github.com/GuilhermeKaynam/ProjetoFinalBloco3)

## Como executar

```bash
git clone https://github.com/GuilhermeKaynam/CRUD-Farmacia.git
cd CRUD-Farmacia
./mvnw spring-boot:run
```
