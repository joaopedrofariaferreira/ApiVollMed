package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid MedicoDTO medicoDTO){
        repository.save(new Medico(medicoDTO));
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page =  repository.findAll(pageable).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoMedicoDTO atualizacaoMedicoDTO){
        var medico = repository.getReferenceById(atualizacaoMedicoDTO.id());
        medico.atualizarInformacoes(atualizacaoMedicoDTO);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.status(200).build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalheMedico(@PathVariable Long id) {

        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemMedico(medico));
    }
}
