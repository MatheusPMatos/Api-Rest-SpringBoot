package am2saude.api.consultas.DTOs.Validacoes;

import am2saude.api.Infra.ValidacaoException;
import am2saude.api.consultas.DTOs.ConsultaRepository;
import am2saude.api.consultas.DTOs.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaConsultaSimultanea implements ValidaAgendamentoConsulta{
    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiCosultaSimultanea = repository.existsByMedicoIdAndData(dados.idMedico(),dados.data());
        if (medicoPossuiCosultaSimultanea) {
            throw new ValidacaoException("Médico ja possui consulta neste horario");
        }
    }
}
