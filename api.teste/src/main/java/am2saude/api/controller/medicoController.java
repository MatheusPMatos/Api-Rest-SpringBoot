package am2saude.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import am2saude.api.medico.DadosAtualizacaoDados;
import am2saude.api.medico.DadosCadastroMedico;
import am2saude.api.medico.DadosDetalhamentoMedico;
import am2saude.api.medico.Medico;
import am2saude.api.medico.MedicoRepository;
import am2saude.api.medico.listagemMedico.DadosListagemMedico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class medicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

    }

    @GetMapping("/{id}")
    public ResponseEntity getMedico(@PathVariable long id) {
       
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(
       
        @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoDados dados) {

        var medico = repository.getReferenceById(dados.id());
        medico.atualizaCadastro(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable long id) {
        
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();

    }
}
