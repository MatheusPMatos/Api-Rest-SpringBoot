package am2saude.api.consultas.DTOs.Validacoes;

import am2saude.api.Infra.ValidacaoException;
import am2saude.api.consultas.DTOs.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidaHorarioFuncionamentoClinica implements ValidaAgendamentoConsulta{
    public void validar(DadosAgendamentoConsulta dados){

       var dataConsulta =  dados.data();
       var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
       var antes07 = dataConsulta.getHour()<7;
       var apos19 = dataConsulta.getHour()>18;
       if (domingo || antes07 || apos19){
           throw  new ValidacaoException("Consulta fora do horario de atendimento.");
       }
    }
}
