package am2saude.api.medico;


import am2saude.api.medico.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDados(
    
    @NotNull
    Long id, 

    String nome, 
    String telefone, 
    DadosEndereco endereco) {}
