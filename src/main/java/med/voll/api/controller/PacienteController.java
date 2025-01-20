package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.PacienteDTO;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDTO pacienteDTO) {
        pacienteRepository.save(new Paciente(pacienteDTO));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarPacientes(@PageableDefault(size=10, sort = {"nome"}) Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(DadosListagemPaciente::new);
    }
}
