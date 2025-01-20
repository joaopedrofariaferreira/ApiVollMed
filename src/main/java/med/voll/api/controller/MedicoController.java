package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoDTO;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid MedicoDTO medicoDTO){
        repository.save(new Medico(medicoDTO));
    }

    @GetMapping
    public Page<DadosListagemMedico> listarMedicos( @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return repository.findAll(pageable).map(DadosListagemMedico::new);
    }


}
