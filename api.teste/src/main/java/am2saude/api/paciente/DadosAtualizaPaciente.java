package am2saude.api.paciente;

import am2saude.api.medico.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPaciente(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco) {}

