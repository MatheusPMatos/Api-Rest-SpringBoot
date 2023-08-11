package am2saude.api.consultas.DTOs.Validacoes;

import am2saude.api.Infra.ValidacaoException;
import am2saude.api.consultas.DTOs.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidaAntecedenciaConsulta implements ValidaAgendamentoConsulta{
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
        if (diferencaEmMinutos <30){
            throw  new ValidacaoException("Consulta deve ser agendada com no minimo 30 minutos de antecedencia");
        }
    }
}
