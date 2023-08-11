package am2saude.api.consultas.DTOs.Service;

import am2saude.api.Infra.ValidacaoException;
import am2saude.api.consultas.DTOs.ConsultaRepository;
import am2saude.api.consultas.DTOs.ExclusaoDeConsulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ExcluiConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;


    public void cancelarConsulta(DadosCancelamentoConsulta dados){
        if (!consultaRepository.existsById(dados.idConsulta())){
           throw new ValidacaoException("Agendamento não encontrado.");
        }else {
            consultaRepository.deleteById(dados.idConsulta());
        }

    }
}
