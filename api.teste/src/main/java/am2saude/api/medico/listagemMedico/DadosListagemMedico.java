package am2saude.api.medico.listagemMedico;


import am2saude.api.medico.Especialidade;
import am2saude.api.medico.Medico;


/**
 * DadosListagemMedico
 */

public record DadosListagemMedico(Long id,String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getCrm(),medico.getEmail(), medico.getEspecialidade());
    }
}
