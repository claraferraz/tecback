package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Produtor;
import br.com.fujideia.iesp.tecback.model.dto.ProdutorDTO;
import br.com.fujideia.iesp.tecback.repository.ProdutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutorService {

    private final ProdutorRepository produtorRepository;

    public List<ProdutorDTO> listarTodos() {
        return produtorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<Produtor> buscarPorNome(String nome) {
        return produtorRepository.buscarPorNome(nome);
    }

    public ProdutorDTO criarProdutor(ProdutorDTO produtorDTO) {
        Produtor produtor = convertToEntity(produtorDTO);
        return convertToDTO(produtorRepository.save(produtor));
    }

    public Optional<ProdutorDTO> atualizarProdutor(Long id, ProdutorDTO produtorDTO) {
        return produtorRepository.findById(id).map(produtor -> {
            produtor.setNome(produtorDTO.getNome());
            return convertToDTO(produtorRepository.save(produtor));
        });
    }

    public boolean deletarProdutor(Long id) {
        if (produtorRepository.existsById(id)) {
            produtorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProdutorDTO convertToDTO(Produtor produtor) {
        return new ProdutorDTO(
                produtor.getId(),
                produtor.getNome(),
                produtor.getIdade(),
                produtor.getNacionalidade()
        );
    }

    private Produtor convertToEntity(ProdutorDTO produtorDTO) {
        Produtor produtor = new Produtor();
        produtor.setNome(produtorDTO.getNome());
        produtor.setIdade(produtorDTO.getIdade());
        produtor.setNacionalidade(produtorDTO.getNacionalidade());
        produtor.getFilmesProduzidos();
        return produtor;
    }
}
