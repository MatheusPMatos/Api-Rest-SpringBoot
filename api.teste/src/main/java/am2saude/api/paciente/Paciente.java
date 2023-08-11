package am2saude.api.paciente;

import am2saude.api.medico.DadosAtualizacaoDados;
import am2saude.api.paciente.enderecopct.EnderecoPct;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private Boolean ativo;


    @Embedded
    private EnderecoPct endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.telefone=dados.telefone();
        this.email = dados.email();
        this.endereco = new EnderecoPct(dados );
    }

    public void atualizaCadastro(@Valid DadosAtualizaPaciente dados) {

        if(dados.nome()!= null){
            this.nome = dados.nome();
        }
        if(dados.telefone()!= null){
            this.telefone = dados.telefone();
        }

        if(dados.endereco() != null){
            this.endereco.atualizaEndereco(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }

}
