package med.voll.api.medico;


import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;



    public Medico(MedicoDTO dados) {

        this.endereco = new Endereco(dados.endereco());
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.ativo = true;
    }

    //-------------------------------------------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCrm() {
        return crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public boolean isAtivo() {return ativo;}


    public Medico() {
    }

    public void atualizarInformacoes(AtualizacaoMedicoDTO atualizacaoMedicoDTO) {
        if (atualizacaoMedicoDTO.nome() != null)
            this.nome = atualizacaoMedicoDTO.nome();
        if (atualizacaoMedicoDTO.telefone() != null)
            this.telefone = atualizacaoMedicoDTO.telefone();
        if (atualizacaoMedicoDTO.endereco()!= null)
            this.endereco.atualizarInformacoes(atualizacaoMedicoDTO.endereco());
    }


    public void excluir() {
        this.ativo = false;
    }
}
