package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DetalhamentoConsultaDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime dataConsulta) {
}
