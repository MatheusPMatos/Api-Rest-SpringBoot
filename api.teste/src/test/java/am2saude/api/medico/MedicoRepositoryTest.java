package am2saude.api.medico;

import am2saude.api.consultas.DTOs.Consulta;
import am2saude.api.medico.endereco.DadosEndereco;
import am2saude.api.paciente.DadosCadastroPaciente;
import am2saude.api.paciente.Paciente;
import org.assertj.core.api.FactoryBasedNavigableIterableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;



    @Test
    @DisplayName("devolve medico quando  esta disponivel na data")
    void medicoAleatorioLivrenaDataCenario02() {
        var proximaSegunda =
                LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        var medico = cadastrarMedico("medicoeste", "medico@medicoapi.com", "123415", Especialidade.CARDIOLOGIA);

        var medicolivre =
                medicoRepository.medicoAleatorioLivrenaData(Especialidade.CARDIOLOGIA, proximaSegunda);
        assertThat(medicolivre).isEqualTo(medico);
    }
    @Test
    @DisplayName("devolve  null  quando solicita medico nao cadastrado")
    void medicoAleatorioLivrenaDataCenario03() {
        var proximaSegundaas10 =
                LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                        .withHour(12).withMinute(0).withSecond(0);

        var medico = cadastrarMedico("medicoeste", "medico@medicoapi.com", "123415", Especialidade.CARDIOLOGIA);

        var medicolivre =
                medicoRepository.medicoAleatorioLivrenaData(Especialidade.GINECOLOGIA, proximaSegundaas10);
        assertThat(medicolivre).isNull();
    }


    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));

    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}