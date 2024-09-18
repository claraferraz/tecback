package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.*;
import br.com.fujideia.iesp.tecback.model.dto.*;
import br.com.fujideia.iesp.tecback.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public List<FilmeDTO> listarTodos() {
        return filmeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FilmeDTO> buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .map(this::convertToDTO);
    }

    public FilmeDTO criarFilme(FilmeDTO filmeDTO) {
        Filme filme = convertToEntity(filmeDTO);
        return convertToDTO(filmeRepository.save(filme));
    }

    public Optional<FilmeDTO> atualizarFilme(Long id, FilmeDTO filmeDTO) {
        return filmeRepository.findById(id).map(filme -> {
            filme.setTitulo(filmeDTO.getTitulo());
            filme.setAnoLancamento(filmeDTO.getAnoLancamento());
            filme.setDiretor(convertToEntity(filmeDTO.getDiretor()));
            filme.setProdutor(convertToEntity(filmeDTO.getProdutor()));
            filme.setAtores(filmeDTO.getAtores().stream().map(this::convertToEntity).collect(Collectors.toList()));
            filme.setGeneros(filmeDTO.getGeneros().stream().map(this::convertToEntity).collect(Collectors.toList()));
            return convertToDTO(filmeRepository.save(filme));
        });
    }

    public boolean deletarFilme(Long id) {
        if (filmeRepository.existsById(id)) {
            filmeRepository.deleteById(id);
            return true;
        }
        return false;
    }

  public List<FilmeDTO> listarPorAno(int ano) {
        return filmeRepository.buscarPorAno(ano)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FilmeDTO> listarOrdenadoPorAno() {
        return filmeRepository.ordenarPorAno()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FilmeDTO convertToDTO(Filme filme) {
        return new FilmeDTO(
                filme.getId(),
                filme.getTitulo(),
                filme.getAnoLancamento(),
                filme.getDiretor() != null ? new DiretorDTO(filme.getDiretor().getId(), filme.getDiretor().getNome()) : null,
                filme.getProdutor() != null ? new ProdutorDTO(filme.getProdutor().getId(), filme.getProdutor().getNome(), filme.getProdutor().getIdade(), filme.getProdutor().getNacionalidade()) : null,
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


    private Filme convertToEntity(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setAnoLancamento(filmeDTO.getAnoLancamento());
        filme.setDiretor(filmeDTO.getDiretor() != null ? convertToEntity(filmeDTO.getDiretor()) : null);
        filme.setProdutor(filmeDTO.getProdutor() != null ? convertToEntity(filmeDTO.getProdutor()) : null);
        filme.setAtores(filmeDTO.getAtores() != null ? filmeDTO.getAtores()
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()) : new ArrayList<>());
        filme.setGeneros(filmeDTO.getGeneros() != null ? filmeDTO.getGeneros()
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()): new ArrayList<>());
        return filme;

    }

    private Diretor convertToEntity(DiretorDTO diretorDTO) {
        if (diretorDTO == null) {
            return null; // Retorne null caso não haja Diretor
        }
        Diretor diretor = new Diretor();
        diretor.setId(diretorDTO.getId());
        diretor.setNome(diretorDTO.getNome());
        return diretor;
    }
    private Produtor convertToEntity(ProdutorDTO produtorDTO) {
        if (produtorDTO == null) {
            return null;
        }
        Produtor produtor = new Produtor();
        produtor.setId(produtorDTO.getId());
        produtor.setNome(produtorDTO.getNome());
        produtor.setIdade(produtorDTO.getIdade());
        produtor.setNacionalidade(produtorDTO.getNacionalidade());
        return produtor;
    }

    private Ator convertToEntity(AtorDTO atorDTO) {
        Ator ator = new Ator();
        ator.setId(atorDTO.getId());
        ator.setNome(atorDTO.getNome());
        return ator;
    }

    private Genero convertToEntity(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setId(generoDTO.getId());
        genero.setNome(generoDTO.getNome());
        return genero;
    }
}
