package br.com.fujideia.iesp.tecback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutorSimplesDTO {
    private Long id;
    private String nome;
    private int idade;
    private String nacionalidade;
}
