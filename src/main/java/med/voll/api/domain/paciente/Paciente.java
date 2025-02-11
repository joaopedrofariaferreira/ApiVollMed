package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import med.voll.api.domain.endereco.Endereco;

//@Getter
@EqualsAndHashCode(of = "id")
//@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pacientes")
@Table(name = "Pacientes")
public class Paciente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Paciente(PacienteDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(AtualizaPacienteDTO atualizaPacienteDTO) {
        if (atualizaPacienteDTO.nome() != null)
            this.nome = atualizaPacienteDTO.nome();
        if (atualizaPacienteDTO.telefone() != null)
            this.telefone = atualizaPacienteDTO.telefone();
        if (atualizaPacienteDTO.endereco()!= null)
            this.endereco.atualizarInformacoes(atualizaPacienteDTO.endereco());
    }
//-------------------------------------------------------------------------------------------------------------
    public Paciente(){

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}