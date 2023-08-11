package am2saude.api.consultas.DTOs.ExclusaoDeConsulta;

import java.time.LocalDateTime;

public record DetalheExclusaoConsulta(
        String message,
        LocalDateTime data
) {
}
