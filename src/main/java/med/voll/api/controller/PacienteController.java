package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    /*@PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDTO pacienteDTO) {
        pacienteRepository.save(new Paciente(pacienteDTO));
    }*/
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid List<PacienteDTO> pacienteDTOs) {
        // Converte a lista de PacienteDTO para uma lista de Paciente
        List<Paciente> pacientes = pacienteDTOs.stream()
                .map(Paciente::new) // Mapeia de PacienteDTO para Paciente
                .collect(Collectors.toList());

        // Salva todos os pacientes no banco de dados
        pacienteRepository.saveAll(pacientes);
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarPacientes(@PageableDefault(size=10, sort = {"nome"}) Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizaPaciente(@RequestBody @Valid AtualizaPacienteDTO  atualizaPacienteDTO) {
        var paciente = pacienteRepository.getReferenceById(atualizaPacienteDTO.id());
        paciente.atualizarInformacoes(atualizaPacienteDTO);
    }

   @DeleteMapping("{id}")
   @Transactional
   public void excluirPaciente(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
   }

}
