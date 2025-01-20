package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoDTO;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DadosListagemMedico> listarMedicos() {
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }


}
