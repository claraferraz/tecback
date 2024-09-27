package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Produtor;
import br.com.fujideia.iesp.tecback.model.dto.*;
import br.com.fujideia.iesp.tecback.repository.FilmeRepository;
import br.com.fujideia.iesp.tecback.repository.ProdutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutorService {

    private final ProdutorRepository produtorRepository;
    private final FilmeRepository filmeRepository;

    public List<ProdutorSimplesDTO> listarTodos() {
        return produtorRepository.findAll()
                .stream()
                .map(this::convertToProdutorSimplesDTO)
                .collect(Collectors.toList());
    }

    public List<ProdutorDTO> buscarPorNome(String nome) {
        return produtorRepository.findByNome(nome).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProdutorDTO buscarPorId(Long id){
         Produtor produtor = produtorRepository.findById(id).get();
         return convertToDTO(produtor);
    }

    public ProdutorSimplesDTO criarProdutor(ProdutorSimplesDTO produtorSimplesDTO) {
        Produtor produtor = convertToEntity(produtorSimplesDTO);
        return convertToProdutorSimplesDTO(produtorRepository.save(produtor));
    }

    public Optional<ProdutorSimplesDTO> atualizarProdutor(Long id, ProdutorSimplesDTO produtorSimplesDTO) {
        return produtorRepository.findById(id).map(produtor -> {
            produtor.setNome(produtorSimplesDTO.getNome());
            produtor.setIdade(produtorSimplesDTO.getIdade());
            produtor.setNacionalidade(produtorSimplesDTO.getNacionalidade());
            return convertToProdutorSimplesDTO(produtorRepository.save(produtor));
        });
    }

    public boolean deletarProdutor(Long id) {
        if (produtorRepository.existsById(id)) {
            produtorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProdutorDTO adicionarFilme(Long idProdutor, Long idFilme) {
        Optional<Produtor> optionalProdutor = produtorRepository.findById(idProdutor);
        Produtor produtor = optionalProdutor.get();
        filmeRepository.updateProdutorById(idFilme, produtor);
        return convertToDTO(produtor);
    }

    private ProdutorSimplesDTO convertToProdutorSimplesDTO(Produtor produtor) {
        return new ProdutorSimplesDTO(
                produtor.getId(),
                produtor.getNome(),
                produtor.getIdade(),
                produtor.getNacionalidade()
        );
    }

    private ProdutorDTO convertToDTO(Produtor produtor) {
        return new ProdutorDTO(
                produtor.getId(),
                produtor.getNome(),
                produtor.getIdade(),
                produtor.getNacionalidade(),
                produtor.getFilmesProduzidos().stream()
                        .map(f -> convertToFilmeSemProdutorDTO(f))
                        .collect(Collectors.toList())
                );
    }

    private Produtor convertToEntity(ProdutorSimplesDTO produtorSimplesDTO) {
        Produtor produtor = new Produtor();
        produtor.setNome(produtorSimplesDTO.getNome());
        produtor.setIdade(produtorSimplesDTO.getIdade());
        produtor.setNacionalidade(produtorSimplesDTO.getNacionalidade());
        return produtor;
    }


    private FilmeSimplesDTO convertToFilmeSemProdutorDTO(Filme filme) {
        return new FilmeSimplesDTO(
                filme.getId(),
                filme.getTitulo(),
                filme.getAnoLancamento(),
                filme.getDiretor() != null ? new DiretorDTO(filme.getDiretor().getId(), filme.getDiretor().getNome()) : null,
                filme.getAtores() != null ? filme.getAtores()
                        .stream()
                        .map(ator -> new AtorDTO(ator.getId(), ator.getNome()))
                        .collect(Collectors.toList()) : Collections.emptyList(),
                filme.getGeneros() != null ? filme.getGeneros()
                        .stream()
                        .map(genero -> new GeneroDTO(genero.getId(), genero.getNome()))
                        .collect(Collectors.toList()) : Collections.emptyList()
        );
    }
}
