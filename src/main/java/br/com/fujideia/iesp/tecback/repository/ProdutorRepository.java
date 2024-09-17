package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutorRepository  extends JpaRepository<Produtor, Long> {
    @Query("SELECT p FROM Produtor p WHERE p.nome LIKE %:nome%")
    List<Produtor> buscarPorNome(@Param("nome") String nome);

    @Query("SELECT p FROM Produtor p WHERE p.nacionalidade LIKE %:nacionalidade%")
    List<Produtor> buscarPorNacionalidade(@Param("nacionalidade") String nacionalidade);
}
