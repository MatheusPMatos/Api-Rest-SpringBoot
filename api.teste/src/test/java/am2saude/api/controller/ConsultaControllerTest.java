package am2saude.api.controller;

import am2saude.api.consultas.DTOs.DadosAgendamentoConsulta;
import am2saude.api.consultas.DTOs.DadosDetalhamentoConsulta;
import am2saude.api.consultas.DTOs.Service.AgendaConsultas;
import am2saude.api.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosResponseJson;

    @Autowired
    private AgendaConsultas agenda;



    @Test
    @DisplayName("Devolve erro 400 qdo recebe dados invalidos ")
    @WithMockUser
    void agendarCenario1()throws  Exception {
       var response =  mvc.perform(post(  "/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

}