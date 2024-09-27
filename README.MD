## Detalhamento do Time

Este projeto foi desenvolvido pela equipe, composta pelos seguintes membros:

- **Clara Souza Ferraz**

Cada membro contribuiu com a implementação de um ou mais endpoints, além dos requisitos discutidos em sala de aula. Cada um desenvolveu um CRUD complet, bem como os Services, Repositories e Models, anotados com @Entity.

### Detalhamento das Contribuições:

- **Clara Souza Ferraz**  
  Desenvolveu o CRUD completo para **Produtor**:
    - `GET /produtor`
    - `GET /produtor/{nome}`
    - `GET /produtor/id/{id}`
    - `POST /produtor`
    - `PUT /produtor/{id}`
    - `DELETE /produtor/{id}`
    - `PATCH /produtor/addFilme/{produtorID}/{filmeId}`

 Também criou o `ProdutorService`, `ProdutorRepository` e o model `Produtor` anotado com `@Entity`, além do `ProdutorDTO` e o `ProdutorSimplesDTO`.


  O `ProdutorDTO` possui a listagem de filmes e o `ProdutorSimplesDTO` não possui a listagem de filmes. Isso foi feito para solucionar o loop infinito que a listagem de filmes estava fazendo, pois foi adicionado o `Produtor` também ao model `Filmes`.


  Além disso, foi criado o `FilmeSimplesDTO` que não possui o `ProdutorSimplesDTO` e no `FilmeDTO` foi adicionado a variável para `ProdutorSimplesDTO`


  Desenvolveu o CRUD completo para **Ator**:
  - `GET /ator`
  - `GET /ator/{id}`
  - `POST /ator`
  - `PUT /ator/{id}`
  - `DELETE /ator/{id}`

  Também criou o `AtorService`, `AtorRepository` e o model `Ator` anotado com `@Entity`, além do `AtorDTO`.
 
Desenvolveu o CRUD completo para **Diretor**:
  - `GET /diretor`
  - `GET /diretor/{id}`
  - `POST /diretor`
  - `PUT /diretor/{id}`
  - `DELETE /diretor/{id}`

  Também criou o `DiretorService`, `DiretorRepository` e o model `Diretor` anotado com `@Entity`, além do `DiretorDTO`.

Desenvolveu o CRUD completo para **Gênero**:
  - `GET /genero`
  - `GET /genero/{id}`
  - `POST /genero`
  - `PUT /genero/{id}`
  - `DELETE /genero/{id}`


Também criou o `GeneroService`, `GeneroRepository` e o model `Genero` anotado com `@Entity`, além do `GeneroDTO`.




