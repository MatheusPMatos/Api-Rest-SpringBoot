package am2saude.api.consultas.DTOs.ExclusaoDeConsulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,
         @NotNull
         MotivoCancelamentoEnum motivo
) {



}
