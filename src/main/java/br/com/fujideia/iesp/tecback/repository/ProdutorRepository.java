package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Produtor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutorRepository  extends JpaRepository<Produtor, Long> {

    List<Produtor> findByNome(String nome);

    @Query("SELECT p FROM Produtor p WHERE p.nacionalidade LIKE %:nacionalidade%")
    List<Produtor> buscarPorNacionalidade(@Param("nacionalidade") String nacionalidade);
}
