package am2saude.api.consultas.DTOs.Service;

import am2saude.api.Infra.ValidacaoException;
import am2saude.api.consultas.DTOs.Consulta;
import am2saude.api.consultas.DTOs.ConsultaRepository;
import am2saude.api.consultas.DTOs.DadosAgendamentoConsulta;
import am2saude.api.consultas.DTOs.DadosDetalhamentoConsulta;
import am2saude.api.consultas.DTOs.Validacoes.ValidaAgendamentoConsulta;
import am2saude.api.medico.Medico;
import am2saude.api.medico.MedicoRepository;
import am2saude.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultas {
    @Autowired
    private ConsultaRepository agendaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidaAgendamentoConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id Paciente não existe");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id Medico não existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());


        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidacaoException("Não há medicos disponiveis na Data.");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data());
        agendaRepository.save(consulta);

            return  new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException(
                    "Especialidade não preenchida. Especialidade ou Medico devem ser preenchidos.");
        }

        var medico = medicoRepository.medicoAleatorioLivrenaData(dados.especialidade(), dados.data());
        System.out.println(medico);
        return medico;
    }
}
