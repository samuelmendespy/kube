# kube - Social Media Feed

Simple fullstack social media feed.

## Tecnologias

### Backend - API (`my-api`)
- **Quarkus(Java)** + **MongoDB**: API para adicionar, listar, deletar e atualizar posts.
- **Swagger/OpenAPI** disponível em: [`http://localhost:8080/q/swagger-ui/`](http://localhost:8080/q/swagger-ui/)
- **Testes**: JUnit5 e REST Assured.

### Frontend - App (`my-feed`)
- **React Native**: Interface para exibir posts, adicionar novos, deletar ou editar posts existentes.

## Configuração e Execução
É necesário instalar Docker e Docker Compose na máquina. Navegue com o terminal para a pasta raíz do projeto onde está `docker-compose.yml` e execute o comando:

```sh
docker-compose up -d
```

Isso iniciará o MongoDB e a API Quarkus.

```
A API rodará em `http://localhost:8080`.

```

## Variáveis de Ambiente
O projeto usa `.env(dotenv)` na raiz para definir as variáveis de ambiente (senhas, nome do banco de dados e etc). Renomei o arquivo de .example.env para .env e faça mudanças necessárias.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença
Este projeto é distribuído sob a licença MIT.