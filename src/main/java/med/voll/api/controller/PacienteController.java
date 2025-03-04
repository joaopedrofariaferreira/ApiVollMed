package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid List<PacienteDTO> pacienteDTOs) {
        List<Paciente> pacientes = pacienteDTOs.stream()
                .map(Paciente::new)
                .collect(Collectors.toList());
        pacienteRepository.saveAll(pacientes);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarPacientes(@PageableDefault(size=10, sort = {"nome"}) Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizaPaciente(@RequestBody @Valid AtualizaPacienteDTO atualizaPacienteDTO) {
        var paciente = pacienteRepository.getReferenceById(atualizaPacienteDTO.id());
        paciente.atualizarInformacoes(atualizaPacienteDTO);
        return ResponseEntity.status(200).build();
    }

   @DeleteMapping("{id}")
   @Transactional
   public ResponseEntity excluirPaciente(@PathVariable Long id) {

       pacienteRepository.deleteById(id);
       return ResponseEntity.status(201).build();
   }



}
