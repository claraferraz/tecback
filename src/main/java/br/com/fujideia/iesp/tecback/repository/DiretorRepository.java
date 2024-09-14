package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    @Query("SELECT d FROM Diretor d WHERE d.nome LIKE %:nome%")
    List<Diretor> buscarPorNome(@Param("nome") String nome);
}
