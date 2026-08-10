/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

public class CRUDController {
    CRUDModel model;

    CRUDController(CRUDModel m) {
        model = m;
    }

    public void modidicarPaciente(String cpf, String novoEmail, String novaSenha, String novoNome,
            String novaDataNascimento, String novoCpf, String novoSexo) {
        model.modificarPaciente(cpf, novoEmail, novaSenha, novoNome, novaDataNascimento, novoCpf, novoSexo);
    }

    public void buscarPaciente(String cpf) {
        model.buscarPaciente(cpf);
    }

    public void excluirPaciente(String cpf) {
        model.excluirPaciente(cpf);
    }

    public void buscarAtendente(String email) {
        model.buscarAtendente(email);
    }

    public void modificarAtendente(String emailBusca, String novoEmail, String novaSenha) {
        model.modificarAtendente(emailBusca, novoEmail, novaSenha);
    }

    public void excluirAtendente(String cpf) {
        model.excluirAtendente(cpf);
    }

    public void buscarConsulta(String id) {
        model.buscarConsulta(id);
    }

    public void modificarConsulta(String idBusca, String novaData, String novaHora, Paciente novoPaciente, Medico novoMedico) {
        model.modificarConsulta(idBusca, novaData, novaHora, novoPaciente, novoMedico);
    }

    public void excluirConsulta(String id) {
        model.excluirConsulta(id);
    }

    public void buscarMedico(String crm) {
        model.buscarMedico(crm);
    }

    public void modificarMedico(String crm, String novoCrm, String novoNomeMedico, String novaEspecialidade) {
        model.modificarMedico(crm, novoCrm, novoNomeMedico, novaEspecialidade);
    }

    public void excluirMedico(String crm) {
        model.excluirMedico(crm);
    }

    public void validarLogin(String email, String senha) {
        model.validarLogin(email, senha);
    }

    public void removerObservador(Observador o) {
        model.removerObservador(o);
    }

    public void adicionarObservador(Observador o) {
        model.adicionarObservador(o);
    }

    public void escreverPacientes() {
        model.escreverPacientes();
    }

    public void escreverUsuarios() {
        model.escreverUsuarios();
    }

    public void escreverConsultas() {
        model.escreverConsultas();
    }

    public void escreverMedicos() {
        model.escreverMedicos();
    }

    public void buscarConsulta1(String data, String paciente, String medico) {
        model.buscarConsulta1(data, paciente, medico);
    }

    public boolean IncluirConsulta(String cpf, String crm, String data, String hora) {
        return model.IncluirConsulta(cpf, crm, data, hora);
    }

    public boolean IncluirAtendente(String email, String senha) {
        return model.IncluirAtendente(email, senha);
    }

    public boolean IncluirPaciente(String cpf, String email, String senha, String nome, String dataNasc, String sexo) {
        return model.IncluirPaciente(cpf, email, senha, nome, dataNasc, sexo);
    }

    public Paciente buscarPacienteEmail(String email) {
        return model.buscarPacienteEmail(email);
    }

    public boolean IncluirMedico(String nome, String crm, String especialidade) {
        return model.IncluirMedico(nome, crm, especialidade);
    }
}
