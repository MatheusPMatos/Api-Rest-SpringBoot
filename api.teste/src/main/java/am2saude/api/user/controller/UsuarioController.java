package am2saude.api.user.controller;

import am2saude.api.Infra.Security.DadosTokenJwt;
import am2saude.api.Infra.Security.TokenService;
import am2saude.api.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import am2saude.api.user.DadosAutenticacao;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
     private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

       var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
       var authentication =  manager.authenticate(token);
       var tkservice = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJwt(tkservice));
    }
}
