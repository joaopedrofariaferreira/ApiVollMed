package med.voll.api.endereco;

public record Endereco(String logradouro,
                       String bairro,
                       String cidade,
                       String uf,
                       String complemento,
                       String numero) {
}
