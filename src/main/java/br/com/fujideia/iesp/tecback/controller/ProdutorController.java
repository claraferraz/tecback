package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Produtor;
import br.com.fujideia.iesp.tecback.model.dto.ProdutorDTO;
import br.com.fujideia.iesp.tecback.service.FilmeService;
import br.com.fujideia.iesp.tecback.service.ProdutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produtor")
@RequiredArgsConstructor
@Slf4j
public class ProdutorController {
    private final ProdutorService produtorService;
    private final FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<ProdutorDTO>> listarTodos() {
        log.info("Chamando listarTodos no ProdutorController");
        List<ProdutorDTO> produtores = produtorService.listarTodos();
        return ResponseEntity.ok(produtores);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Produtor>> buscarPorNome(@PathVariable String nome) {
        log.info("Chamando buscarPorNome no ProdutorController com id: {}", nome);
        List<Produtor> produtor = produtorService.buscarPorNome(nome);
        return ResponseEntity.ok(produtor);
    }

    @PostMapping
    public ResponseEntity<ProdutorDTO> criarProdutor(@RequestBody ProdutorDTO produtorDTO) {
        log.info("Chamando criarProdutor no ProdutorController com dados: {}", produtorDTO);
        ProdutorDTO produtorCriado = produtorService.criarProdutor(produtorDTO);
        return ResponseEntity.ok(produtorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutorDTO> atualizarProdutor(@PathVariable Long id, @RequestBody ProdutorDTO produtorDTO) {
        log.info("Chamando atualizarProdutor no ProdutorController com id: {} e dados: {}", id, produtorDTO);
        Optional<ProdutorDTO> produtorAtualizado = produtorService.atualizarProdutor(id, produtorDTO);
        return produtorAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutor(@PathVariable Long id) {
        log.info("Chamando deletarProdutor no ProdutorController com id: {}", id);
        boolean deletado = produtorService.deletarProdutor(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
