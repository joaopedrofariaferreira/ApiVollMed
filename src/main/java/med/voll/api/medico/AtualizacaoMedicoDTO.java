package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record AtualizacaoMedicoDTO(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {


}
