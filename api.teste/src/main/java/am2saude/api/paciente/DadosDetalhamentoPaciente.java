package am2saude.api.paciente;




import am2saude.api.paciente.enderecopct.EnderecoPct;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String email,

        EnderecoPct endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getEndereco());
    }
}
