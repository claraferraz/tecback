package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Produtor;
import br.com.fujideia.iesp.tecback.model.dto.ProdutorDTO;
import br.com.fujideia.iesp.tecback.model.dto.ProdutorSimplesDTO;
import br.com.fujideia.iesp.tecback.repository.ProdutorRepository;
import br.com.fujideia.iesp.tecback.service.ProdutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produtor")
@RequiredArgsConstructor
@Slf4j
public class ProdutorController {
    private final ProdutorService produtorService;
    private final ProdutorRepository produtorRepository;

    @GetMapping
    public ResponseEntity<List<ProdutorSimplesDTO>> listarTodos() {
        log.info("Chamando listarTodos no ProdutorController");
        List<ProdutorSimplesDTO> produtores = produtorService.listarTodos();
        return ResponseEntity.ok(produtores);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<ProdutorDTO>> buscarPorNome(@PathVariable String nome) {
        log.info("Chamando buscarPorNome no ProdutorController com id: {}", nome);
        List<ProdutorDTO> produtor = produtorService.buscarPorNome(nome);
        return ResponseEntity.ok(produtor);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutorDTO> buscarPorId(@PathVariable Long id) {
        log.info("Chamando buscarPorNome no ProdutorController com id: {}", id);
        ProdutorDTO produtor = produtorService.buscarPorId(id);
        return ResponseEntity.ok(produtor);
    }


    @PostMapping
    public ResponseEntity<ProdutorSimplesDTO> criarProdutor(@RequestBody ProdutorSimplesDTO produtorSimplesDTO) {
        log.info("Chamando criarProdutor no ProdutorController com dados: {}", produtorSimplesDTO);
        ProdutorSimplesDTO produtorCriado = produtorService.criarProdutor(produtorSimplesDTO);
        return ResponseEntity.ok(produtorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutorSimplesDTO> atualizarProdutor(@PathVariable Long id, @RequestBody ProdutorSimplesDTO produtorSimplesDTO) {
        log.info("Chamando atualizarProdutor no ProdutorController com id: {} e dados: {}", id, produtorSimplesDTO);
        Optional<ProdutorSimplesDTO> produtorAtualizado = produtorService.atualizarProdutor(id, produtorSimplesDTO);
        return produtorAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PatchExchange("/addFilme/{produtorID}/{filmeID}")
    public ResponseEntity<ProdutorDTO> adicionarFilme(@PathVariable Long produtorID, @PathVariable Long filmeID ){
        log.info("Chamando adicionarFilme no ProdutorController com nomeProdutor: {} e tituloFilme: {}", produtorID, filmeID);
        ProdutorDTO produtor = produtorService.adicionarFilme(produtorID, filmeID);
        return ResponseEntity.ok(produtor);
    }


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
