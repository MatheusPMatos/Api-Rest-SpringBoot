package am2saude.api.consultas.DTOs.Validacoes;

import am2saude.api.Infra.ValidacaoException;
import am2saude.api.consultas.DTOs.DadosAgendamentoConsulta;
import am2saude.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoAtivo implements ValidaAgendamentoConsulta {
    @Autowired
    private MedicoRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idMedico()==null){
            return;
        }
        var medicoAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoAtivo){
            throw new ValidacaoException("Profissional se encontra inativo na base de dados.");
        }
    }
}
