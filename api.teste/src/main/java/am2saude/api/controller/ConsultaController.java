package am2saude.api.controller;

import am2saude.api.Infra.ValidacaoException;
import am2saude.api.consultas.DTOs.DadosAgendamentoConsulta;
import am2saude.api.consultas.DTOs.DadosDetalhamentoConsulta;
import am2saude.api.consultas.DTOs.ExclusaoDeConsulta.DadosCancelamentoConsulta;
import am2saude.api.consultas.DTOs.ExclusaoDeConsulta.DetalheExclusaoConsulta;
import am2saude.api.consultas.DTOs.Service.AgendaConsultas;
import am2saude.api.consultas.DTOs.Service.ExcluiConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private AgendaConsultas agenda;
    @Autowired
    private ExcluiConsulta excluiConsulta;
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
       var dto = agenda.agendar(dados);


        return ResponseEntity.ok(dto);
    }
    @DeleteMapping
    @Transactional
    public  ResponseEntity excluir(@RequestBody @Valid DadosCancelamentoConsulta dados){

        excluiConsulta.cancelarConsulta(dados);
        return ResponseEntity.ok(new DetalheExclusaoConsulta("Consulta Cancelada.", LocalDateTime.now()));



    }
}
