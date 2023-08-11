package am2saude.api.controller;



import am2saude.api.paciente.*;
import am2saude.api.paciente.listagemPacientes.DadosListaPacientes;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacientesController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {

        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));

    }

    @GetMapping("/{id}")
    public ResponseEntity getMedico(@PathVariable long id) {

        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(medico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListaPacientes>> listar(

            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListaPacientes::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaPaciente dados) {

        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaCadastro(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable long id) {

        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();

    }
}
