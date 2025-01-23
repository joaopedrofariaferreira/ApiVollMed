package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record AtualizaPacienteDTO(@NotNull Long id, String nome, String telefone, String email ,DadosEndereco endereco) {
}

