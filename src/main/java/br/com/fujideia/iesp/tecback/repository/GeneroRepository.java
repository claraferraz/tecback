package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    @Query("SELECT g FROM Genero g WHERE g.descricao LIKE %:descricao%")
    List<Genero> buscarPorDescricao(@Param("descricao") String descricao);
}
