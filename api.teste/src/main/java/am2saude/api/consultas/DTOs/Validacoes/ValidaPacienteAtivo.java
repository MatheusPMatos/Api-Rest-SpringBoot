package am2saude.api.consultas.DTOs.Validacoes;

import am2saude.api.Infra.ValidacaoException;
import am2saude.api.consultas.DTOs.DadosAgendamentoConsulta;
import am2saude.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteAtivo implements ValidaAgendamentoConsulta{
    @Autowired
    private PacienteRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteAtivo){
            throw new ValidacaoException("Consulta nao pode ser agendada com paciente excluido");
        }
    }
}
