package am2saude.api.paciente.listagemPacientes;


import am2saude.api.paciente.Paciente;

public record DadosListaPacientes(Long id, String nome, String email) {
    public DadosListaPacientes(Paciente paciente){
        this(paciente.getId(),paciente.getNome(), paciente.getEmail());
    }
}
