package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Filme f SET f.produtor = :produtor WHERE f.id = :filmeId")
    void updateProdutorById(Long filmeId, Produtor produtor);

    @Query("SELECT f FROM Filme f WHERE f.titulo LIKE %:titulo%")
    List<Filme> buscarPorTitulo(@Param("titulo") String titulo);

    //List<Filme> findByTitulo(String titulo) faz a mesma coisa do de cima, mas em queries complexas acaba ficando muito extenso
    //List<Filme> findByTituloAndAnoLancamento(String titulo, int ano)

    @Query("SELECT f FROM Filme f WHERE f.anoLancamento = :ano")
    List<Filme> buscarPorAno(@Param("ano") int ano);

    @Query("SELECT f FROM Filme f ORDER BY f.anoLancamento")
    List<Filme> ordenarPorAno();
}
