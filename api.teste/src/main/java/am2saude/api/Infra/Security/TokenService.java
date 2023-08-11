package am2saude.api.Infra.Security;

import am2saude.api.user.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.toke.secret}")
    private String secret;
    public String gerarToken(Usuario user){
        try {
            Algorithm algorithm = Algorithm.HMAC256( secret);
            return  JWT.create()
                    .withIssuer("API api-teste")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenjwt){

        try {
            Algorithm algorithm =  Algorithm.HMAC256( secret);
            return JWT.require(algorithm)
                    .withIssuer("API api-teste")
                    .build()
                    .verify(tokenjwt)
                    .getSubject();
        } catch (JWTVerificationException exception){
         throw  new RuntimeException("Token Jwt Invalido ou Expirado");
        }
    }


    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
