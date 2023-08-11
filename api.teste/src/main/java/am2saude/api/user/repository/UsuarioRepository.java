package am2saude.api.user.repository;

import am2saude.api.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public UserDetails findByLogin(String username);
    
   
}
