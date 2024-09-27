package br.com.fujideia.iesp.tecback.model;

import br.com.fujideia.iesp.tecback.model.dto.FilmeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Produtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int idade;
    private String nacionalidade;

    @OneToMany(mappedBy = "produtor")
    private List<Filme> filmesProduzidos;
}
