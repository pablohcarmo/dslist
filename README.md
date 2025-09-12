# Intensivão Java Spring
## Sobre o Projeto
Este projeto é uma aplicação backend desenvolvida durante o **Intensivão Java Spring** ministrado pelo professor **Nélio Alves** da [**DevSuperior**](http://devsuperior.com.br/). O objetivo principal é a construção de uma API REST para gerenciamento de lista de jogos, utilizando tecnologias modernas como **Java, Spring Boot, JPA/Hibernate** e **PostgreSQL**.

A aplicação permite:
* Consultar jogos cadastrados no sistema
* Organizar jogos em listas temáticas
* Retornar dados otimizados via projeção e DTOs
* Realizar ordenação personalizada dos jogos em cada lista


## Tabelas
### tb_game
| id | title                    | score | year | genre                          | platforms              | img_url | short_description |
|----|--------------------------|-------|------|--------------------------------|------------------------|---------|-------------------|
| 1  | Mass Effect Trilogy      | 4.8   | 2012 | Role-playing (RPG), Shooter    | XBox, Playstation, PC | [img] | Lorem ipsum... |
| 2  | Red Dead Redemption 2    | 4.7   | 2018 | Role-playing (RPG), Adventure  | XBox, Playstation, PC | [img] | Lorem ipsum... |
| 3  | The Witcher 3: Wild Hunt | 4.7   | 2014 | Role-playing (RPG), Adventure  | XBox, Playstation, PC | [img] | Lorem ipsum... |
| 4  | Sekiro: Shadows Die Twice| 3.8   | 2019 | Role-playing (RPG), Adventure  | XBox, Playstation, PC | [img] | Lorem ipsum... |
| 5  | Ghost of Tsushima        | 4.6   | 2012 | Role-playing (RPG), Adventure  | XBox, Playstation, PC | [img] | Lorem ipsum... |
| 6  | Super Mario World        | 4.7   | 1990 | Platform                       | Super Ness, PC        | [img] | Lorem ipsum... |
| 7  | Hollow Knight            | 4.6   | 2017 | Platform                       | XBox, Playstation, PC | [img] | Lorem ipsum... |
| 8  | Ori and the Blind Forest | 4.0   | 2015 | Platform                       | XBox, Playstation, PC | [img] | Lorem ipsum... |
| 9  | Cuphead                  | 4.6   | 2017 | Platform                       | XBox, Playstation, PC | [img] | Lorem ipsum... |
| 10 | Sonic CD                 | 4.0   | 1993 | Platform                       | Sega CD, PC           | [img] | Lorem ipsum... |

### tb_game_list
| id | name                |
|----|---------------------|
| 1  | Aventura e RPG      |
| 2  | Jogos de plataforma |

### tb_belonging
| list_id | game_id | position |
|---------|---------|----------|
| 1       | 1       | 0        |
| 1       | 2       | 1        |
| 1       | 3       | 2        |
| 1       | 4       | 3        |
| 1       | 5       | 4        |
| 2       | 6       | 0        |
| 2       | 7       | 1        |
| 2       | 8       | 2        |
| 2       | 9       | 3        |
| 2       | 10      | 4        |

## Funcionamento das classes
### Entidades
#### Game
Representa o modelo de dados de um jogo. Mapeada como uma entidade JPA, corresponde à tabela `tb_game` no banco de dados. Contém atributos como `title`, `score`, `year`, `genre`, `platforms`, `imgUrl`, `shortDescription` e `longDescription`.

#### GameList
Define a lista de jogos, como "Aventura e RPG" ou "Jogos de plataforma". Mapeada para a tabela `tb_game_list`, serve como agrupador lógico para os jogos.

#### Belonging
Representa o relacionamento entre um jogo e uma lista. É uma entidade de associação que contém o campo `position`, usado para ordenar os jogos dentro de cada lista.
Mapeada para `tb_belonging`.

### DTO
#### GameMinDTO
Objeto de transferência de dados que carrega apenas os campos mínimos de um jogo: `id`, `title`, `year`, `imgUrl`, `shortDescription`. Usado para otimizar a resposta dos endpoints que listam jogos.

### Projeção
#### GameMinProjection
Interface que define os campos retornados por uma _native query_. Permite que o Spring Data JPA mapeia os resultados diretamente para uma estrutura leve, sem precisar carregar a entidade completa.

### Repositórios
#### GameRepository
Interface que estente `JpaRepository` e fornece acesso aos dados da entidade `Game`. Inclui uma query customizada (`searchByList`) que retorna os jogos de uma lista específica, ordenados pela posição.

#### GameListRepository
Interface padrão que estende `JpaRepository` para a entidade `GameList`. Usada para buscar listas de jogos.

### Serviço
#### GameService
Camada de serviço responsável por encapsular a lógica de negócio relacionada aos jogos. Chama o repositório e transforma os dados em DTOs para enviar ao controlador.

#### GameListService
Camada de serviço que gerencia as listas e jogos. Pode ser expandida para incluir funcionalidades como reordenar jogos ou adicionar novos.

### Controlador REST
#### GameControler
Exposição dos endpoints relacionados aos jogos. Define rotas como `/games` e `/list/{listId}/games`, retornando os dados em formato JSON.

#### GameListController
Exposição dos endpoints relacionados às listas de jogos. Define a rota `/lists`, retornando todas as listas disponíveis.