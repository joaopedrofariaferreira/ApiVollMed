package med.voll.api.domain.consultas;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id , Long idMedico, Long idPaciente, LocalDateTime dataAgendamento) {
}
