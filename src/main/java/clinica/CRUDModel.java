/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.Iterator;
import java.io.FileReader;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CRUDModel implements ObservableCRUD {
    HashSet<Consulta> consultas = new HashSet<>();
    HashSet<Paciente> pacientes = new HashSet<>();
    HashSet<Medico> medicos = new HashSet<>();
    HashMap<String, UserData> usuarios = new HashMap<>();
    HashSet<Observador> observadores = new HashSet<>();
    String EmailDoPerfil;

    int id;

    public CRUDModel() {
        lerPacientes(); // Carrega pacientes ao instanciar classe
        lerUsuarios();
        lerConsultas();
        lerMedicos();
    }

   // Função que irá ler todos os pacientes do arquivo pacientes.txt e salvar no HashSet pacientes IncluirCOnsulta
    public void lerPacientes() {
        int i = 0;
        String nome = "", dataNascimento = "", cpf = "", sexo = "", email = "";
        try {
            Scanner in = new Scanner(new FileReader("./db/pacientes.txt"));
            while (in.hasNextLine()) {
                String linha = in.nextLine(); // Lê uma linha e passa para próxima
                if (i == 0) {
                    nome = linha;
                } else if (i == 1) {
                    dataNascimento = linha;
                } else if (i == 2) {
                    cpf = linha;
                } else if (i == 3) {
                    sexo = linha;
                } else {
                    email = linha;
                    // Adiciona paciente no HashSet
                    pacientes.add(new Paciente(nome, dataNascimento, cpf, sexo, email));
                    i = -1;
                }
                i++;
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Falha na leitura do arquivo pacientes.txt");
        }
    }

    // Função que irá escrever todos os paciente do HashSet no arquivo pacientes.txt
    public void escreverPacientes() {
        int c = 0;
        Iterator<Paciente> i = pacientes.iterator();
        Paciente p;

        if (pacientes.size() == 0) { // Apenas abre no modo escrita e depois fecha (isso limpa o arquivo)
            try {
                FileWriter fw = new FileWriter("./db/pacientes.txt");
                fw.close();
            } catch (Exception e) {
                System.out.println("Falha na escrita (apagar todos os dados) do arquivo pacientes.txt");
            }
        }
        while (i.hasNext()) {
            p = i.next();
            if (c == 0) {
                p.escreverPaciente(false);
            } else {
                p.escreverPaciente(true);
            }
            c++;
        }
    }
    
    // Função que irá buscar um usuário pelo nome e o retornar, caso ele esteja na coleção. Senão, retorna um paciente sem dados
    public Paciente buscarPaciente(String cpfBusca) {
        Iterator<Paciente> i = pacientes.iterator();
        Paciente p;

        while (i.hasNext()) {
            p = i.next();
            if (p.getCpf().equals(cpfBusca)) {
                if (usuarios.containsKey(p.getEmail().toUpperCase())
                        && usuarios.get(p.getEmail().toUpperCase()).getProfile().equals("PACIENTE")) {
                    notificarObservadores(p, usuarios.get(p.getEmail().toUpperCase()).getPassword());
                    return p;
                } else {
                    p = new Paciente("", "", "", "", "");
                    notificarObservadores(p, "");
                    return p;
                }
            }
        }
        p = new Paciente("", "", "", "", "");
        notificarObservadores(p, "");
        return p;
    }

    // Função que irá editar os dados de um paciente
    public boolean modificarPaciente(String cpfBusca, String novoEmail, String novaSenha, String novoNome, String novaDataNascimento, String novoCpf, String novoSexo) {
        Iterator<Paciente> i = pacientes.iterator();
        Paciente p;
        String perfilAntigo;

        while (i.hasNext()) {
            p = i.next();
            if (p.getCpf().equals(cpfBusca)) {
                // Altera primeiro os dados de acesso
                if (usuarios.containsKey(p.getEmail().toUpperCase()) && p.getEmail().toUpperCase() != novoEmail.toUpperCase()) {
                    perfilAntigo = usuarios.get(p.getEmail().toUpperCase()).getProfile();
                    usuarios.remove(p.getEmail().toUpperCase()); // Remove email, senha e perfil antigos
                    usuarios.put(novoEmail, new UserData(novaSenha, perfilAntigo)); // Adiciona os novos dados de acesso
                }
                p.setEmail(novoEmail);
                p.setNome(novoNome);
                p.setDataNascimento(novaDataNascimento);
                p.setCpf(novoCpf);
                p.setSexo(novoSexo);

                notificarObservadores("Os dados do (a) paciente foram atualizados com sucesso!");
                return true;
            }
        }
        notificarObservadores("Paciente não encontrado (a)!");
        return false;
    }

    // Função que irá excluir um paciente, caso ele estja cadastrado
    public boolean excluirPaciente(String cpf) {
        Iterator<Paciente> i = pacientes.iterator();
        Paciente p;
        while (i.hasNext()) {
            p = i.next();
            if (p.getCpf().equals(cpf)) {
                // Remove primeiro dos usuarios, se estiver lá
                if (usuarios.containsKey(p.getEmail().toUpperCase()) && usuarios.get(p.getEmail().toUpperCase()).getProfile().equals("PACIENTE")) {
                    usuarios.remove(p.getEmail());
                }

                pacientes.remove(p); // Remove da coleção de pacientes
                notificarObservadores("Paciente excluído (a) com sucesso!");
                return true;
            }
        }
        notificarObservadores("Paciente não econtrado. Tente novamente!");
        return false; // Paciente não está cadastrado
    }
    
    public boolean IncluirPaciente(String cpf, String email, String senha, String nome, String dataNasc, String sexo) {
        Iterator<Paciente> i = pacientes.iterator();
        while(i.hasNext()) {
            if (i.next().getCpf().equals(cpf.toUpperCase())) {
                notificarObservadores("CPF ja cadastrado");
                return false;
            }
        }

        pacientes.add(new Paciente(nome, dataNasc, cpf, sexo, email));
        usuarios.put(email, new UserData(senha, "PACIENTE"));
        notificarObservadores("Paciente cadastrado com sucesso");
        return true;
    }

    public boolean IncluirAtendente(String email, String senha) {
        if (usuarios.containsKey(email)) {
            notificarObservadores("Atendente ja incluido");
            return false;
        } else {
            usuarios.put(email.toUpperCase(), new UserData(senha, "ATENDENTE"));
            notificarObservadores("Atendente incluido");
        }

        return true;
    }

    public Paciente buscarCpf(String cpf) {
        Iterator<Paciente> i = pacientes.iterator();
        Paciente aux;

        while (i.hasNext()) {
            aux = i.next();

            if (aux.getCpf().equals(cpf)) {
                return aux;
            }
        }
        
        return null;
    }
    
    public boolean IncluirConsulta(String cpfPac, String crm, String data, String hora) {
        Paciente p;
        Medico m = new Medico("", "", ""), aux;
        String idd;
        int k;

        try {
            Iterator<Consulta> ite = consultas.iterator();
            while(ite.hasNext()) {
                k = Integer.parseInt(ite.next().getId());
                
                if (k > id) {
                    id = k;
                }
            }
    
        } catch (Exception e) {
            System.out.println("Erro parse int id");
        }



        Iterator<Medico> i = medicos.iterator();
        while(i.hasNext()) {
            aux = i.next();
            if (aux.getCRM().equals(crm)) {
                m = aux;
            }
        }

        p = this.buscarCpf(cpfPac);
        
        if (p == null) {
            notificarObservadores("Paciente não existe");
            return false;
        }
        
        if (agendamentoeValido(data, hora, crm, p.getCpf())) {
            id++;
            idd = this.id + "";
            consultas.add(new Consulta(idd , data, hora, p, m));
            notificarObservadores("Consulta agendada");
        } else {
            notificarObservadores("Consula não agendada");
            return false;
        }

        return true;
    }

    public boolean agendamentoeValido(String data, String hora, String crm, String cpf) {
        Iterator<Consulta> i = consultas.iterator();
        Consulta c;
      
        while (i.hasNext()) {
          c = i.next();
          if ((c.getData().equals(data) && c.getHora().equals(hora) && c.getMedico().getCRM().equals(crm)) || ( c.getPaciente().getCpf().equals(cpf) && c.getHora().equals(hora) && c.getData().equals(data)) || ((c.getMedico().getCRM().equals(crm)) && c.getHora().equals(hora) && c.getData().equals(data)) ) {
                return false;
            }
        }
        
        return true;
    }

    public void setEmailDoPerfil (String email) {
        this.EmailDoPerfil = email;
    }

    public String getEmailDoPerfil() {
        return EmailDoPerfil;
    }

    public boolean IncluirMedico(String nome, String crm, String especialidade) {
        Iterator<Medico> i = medicos.iterator();
        while(i.hasNext()) {
            if (i.next().getCRM().equals(crm)) {
                notificarObservadores("Medico ja cadastrado");
                return false;
            }
        }
        
        Medico m = new Medico(nome, crm, especialidade);

        this.medicos.add(m);
        notificarObservadores("Medico cadastrado");
        return true;
    }

    // Função que irá buscar um usuário pelo nome e o retornar, caso ele esteja na coleção. Senão, retorna null
    public boolean buscarAtendente(String emailBusca) {
        if (usuarios.containsKey(emailBusca.toUpperCase())
                && usuarios.get(emailBusca.toUpperCase()).getProfile().equals("ATENDENTE")) {
            notificarObservadores(emailBusca, usuarios.get(emailBusca.toUpperCase()).getPassword());
            return true;
        }
        notificarObservadores("", "");
        return false;
    }

    // Função que irá editar os dados de um atendente
    public boolean modificarAtendente(String emailBusca, String novoEmail, String novaSenha) {
        String perfilAntigo;
        // Altera primeiro os dados de acesso
        if (usuarios.containsKey(emailBusca.toUpperCase()) && emailBusca.toUpperCase() != novoEmail.toUpperCase()) {
            perfilAntigo = usuarios.get(emailBusca.toUpperCase()).getProfile().toUpperCase();

            usuarios.remove(emailBusca.toUpperCase()); // Remove email, senha e perfil antigos
            usuarios.put(novoEmail.toUpperCase(), new UserData(novaSenha, perfilAntigo)); // Adiciona os novos dados de acesso
            notificarObservadores("Os dados do (a) atendente foram atualizados com sucesso!");
            return true;
        }
        notificarObservadores("Email não encontrado. Tente novamente!");
        return false;
    }

    // Função que irá excluir um atendente, caso ele esteja na coleção. Senão, retorna null
    public boolean excluirAtendente(String emailBusca) {
        if (usuarios.containsKey(emailBusca.toUpperCase())
                && usuarios.get(emailBusca.toUpperCase()).getProfile().equals("ATENDENTE")) {
            usuarios.remove(emailBusca.toUpperCase());
            notificarObservadores("Atendente excluído (a) com sucesso!");
            return true;
        }
        notificarObservadores("Atendente não encontrado (a). Tente novamente!");
        return false;
    }

    // Método que irá ler todos os usuários salvos em 'usuarios.txt' e inserí-los no HashSet users
    public void lerUsuarios() {
        int i = 0;
        String email = "", senha = "", perfil = "";
        try {
            Scanner in = new Scanner(new FileReader("./db/usuarios.txt"));
            while (in.hasNextLine()) {
                String linha = in.nextLine();
                if (i == 0) {
                    email = linha.toUpperCase();
                    i++;
                } else if (i == 1) {
                    senha = linha;
                    i++;
                } else {
                    perfil = linha.toUpperCase();
                    // Salva no HashMap:
                    this.usuarios.put(email, new UserData(senha, perfil));
                    i = 0;
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Falha na abertura do arquivo usuarios.txt!");
        }
    }

    public void escreverUsuarios() {
        FileWriter fw;
        int c = 0;
        if (usuarios.size() == 0) { // Apenas abre no modo escrita e depois fecha (isso limpa o arquivo)
            try {
                fw = new FileWriter("./db/usuarios.txt");
                fw.close();
            } catch (Exception e) {
                System.out.println("Falha na escrita (apagar todos os dados) do arquivo usuarios.txt");
            }
        }
        try {
            for (Map.Entry<String, UserData> entry : usuarios.entrySet()) {
                if (c == 0) {
                    fw = new FileWriter("./db/usuarios.txt", false); // append = false
                } else {
                    fw = new FileWriter("./db/usuarios.txt", true); // append = false
                }
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(entry.getKey().toUpperCase());
                bf.newLine();
                bf.write(entry.getValue().getPassword());
                bf.newLine();
                bf.write(entry.getValue().getProfile().toUpperCase());
                bf.newLine();

                bf.close();
                c++;
            }
        } catch (Exception e) {
            System.out.println("Falha na escrita do arquivo usuarios.txt");
        }
    }

    // Função que irá ler todos os pacientes do arquivo pacientes.txt e salvar no HashSet consultas
    public void lerConsultas() {
        int i = 0;
        String id = "", data = "", hora = "", nomePaciente = "", dataNascimentoPaciente = "", cpfPaciente = "", sexoPaciente = "", emailPaciente = "";
        String nomeMedico = "", crm = "", especialidade;
        Paciente p;
        Medico m;
        try {
            Scanner in = new Scanner(new FileReader("./db/consultas.txt"));
            while (in.hasNextLine()) {
                String linha = in.nextLine(); // Lê uma linha e passa para próxima
                if (i == 0) {
                    id = linha;
                } else if (i == 1) {
                    data = linha;
                } else if (i == 2) {
                    hora = linha;
                } else if (i == 3) {
                    nomePaciente = linha;
                } else if (i == 4) {
                    dataNascimentoPaciente = linha;
                } else if (i == 5) {
                    cpfPaciente = linha;
                } else if (i == 6) {
                    sexoPaciente = linha;
                } else if (i == 7) {
                    emailPaciente = linha;
                } else if (i == 8) {
                    nomeMedico = linha;
                } else if (i == 9) {
                    crm = linha;
                } else {
                    especialidade = linha;
                    p = new Paciente(nomePaciente, dataNascimentoPaciente, cpfPaciente, sexoPaciente, emailPaciente);
                    m = new Medico(nomeMedico, crm, especialidade);

                    // Adiciona consulta no HashSet
                    consultas.add(new Consulta(id, data, hora, p, m));
                    i = -1;
                }
                i++;
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Falha na leitura do arquivo consultas.txt");
        }
    }

    // Função que irá escrever todas as consultas do HashSet no arquivo consultas.txt
    public void escreverConsultas() {
        int cont = 0;
        Iterator<Consulta> i = consultas.iterator();
        Consulta c;

        if (consultas.size() == 0) { // Apenas abre no modo escrita e depois fecha (isso limpa o arquivo)
            try {
                FileWriter fw = new FileWriter("./db/consultas.txt");
                fw.close();
            } catch (Exception e) {
                System.out.println("Falha na escrita (apagar todos os dados) do arquivo consultas.txt");
            }
        }

        while(i.hasNext()) {
            c = i.next();
            if (cont == 0) {
                c.escreverConsulta(false);
            } else {
                c.escreverConsulta(true);
            }
            cont++;
        }
    }

    // Função que irá buscar uma consulta pelo id e a retornar, caso ela esteja na coleção. Senão, retorna uma consulta sem dados
    public boolean buscarConsulta(String idBusca) { 
        Iterator<Consulta> i = consultas.iterator();
        Consulta c;

        while (i.hasNext()) {
            c = i.next();
            if (c.getId().equals(idBusca)) {
                notificarObservadores(c);
                return true;
            }
        }
        notificarObservadores(new Consulta("", "", "", null, null));
        return false;
    }

    // Função que irá editar os dados de uma consulta
    public boolean modificarConsulta(String idBusca, String novaData, String novaHora, Paciente novoPaciente, Medico novoMedico) {
        Iterator<Consulta> i = consultas.iterator();
        Consulta c;

        while (i.hasNext()) {
            c = i.next();

            if (c.getId().equals(idBusca)) {
                // Removo a consulta antiga
                consultas.remove(c);

                // Adiciono uma consulta com os novos dados
                novoPaciente.setEmail(c.paciente.getEmail());
                consultas.add(new Consulta(idBusca, novaData, novaHora, novoPaciente, novoMedico));
                notificarObservadores("Consulta atualizada com sucesso!");
                return true;
            }
        }
        notificarObservadores("Consulta não encontrada. Tente novamente!");
        return false;
    }

    // Função que irá exluir uma consulta da Coleção, caso ela esteja lá
    public boolean excluirConsulta(String id) {
        Iterator<Consulta> i = consultas.iterator();
        Consulta c;

        while (i.hasNext()) {
            c = i.next();

            if (c.getId().equals(id)) {
                // Removo a consulta antiga
                consultas.remove(c);
                notificarObservadores("Consulta excluída com sucesso!");
                return true;
            }
        }
        notificarObservadores("Consulta não encontrada. Tente novamente!");
        return false;
    }

    public Paciente buscarPacienteEmail(String email) {
        Iterator<Paciente> i = pacientes.iterator();
        Paciente p;

        while (i.hasNext()) {
            p = i.next();
            if (p.getEmail().equals(email) || p.getEmail().toLowerCase().equals(email)) {
                return p;
            }
        }

        return null;
    }

    // Função que irá ler todos os médicos do arquivo txt e salva no hashset
    public void lerMedicos() {
        int i = 0;
        String crm = "", nomeMedico = "", especialidade = "";
        Medico m;

        try {
            Scanner in = new Scanner(new FileReader("./db/medicos.txt"));
            while (in.hasNextLine()) {
                String linha = in.nextLine(); // Lê uma linha e passa para próxima
                if (i == 0) {
                    nomeMedico = linha;
                } else if (i == 1) {
                    crm = linha;
                } else {
                    especialidade = linha;
                    // Adiciona medico no HashSet
                    medicos.add(new Medico(nomeMedico, crm, especialidade));
                    i = -1;
                }
                i++;
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Falha na leitura do arquivo consultas.txt");
        }
    }

    // Função que irá escrever todos os médicos do HashSet no arquivo medicos.txt
    public void escreverMedicos() {
        int cont = 0;
        Iterator<Medico> i = medicos.iterator();
        Medico m;

        if (medicos.size() == 0) { // Apenas abre no modo escrita e depois fecha (isso limpa o arquivo)
            try {
                FileWriter fw = new FileWriter("./db/medicos.txt");
                fw.close();
            } catch (Exception e) {
                System.out.println("Falha na escrita (apagar todos os dados) do arquivo medicos.txt");
            }
        }

        while(i.hasNext()) {
            m = i.next();
            if (cont == 0) {
                m.escreverMedico(false);
            } else {
                m.escreverMedico(true);
            }
            cont++;
        }
    }

    // Função que irá buscar um médico pelo crm e retorná-lo se ele estiver no hashset. Retorna um médico sem dados, c.c.
    public boolean buscarMedico(String crmBusca) {
        Iterator<Medico> i = medicos.iterator();
        Medico m;

        while(i.hasNext()) {
            m = i.next();

            if (m.getCRM().equals(crmBusca)) {
                notificarObservadores(m);
                return true;
            }
        }
        notificarObservadores(new Medico("", "________-_", ""));
        return false;
    }

    public Medico buscarMedicoNome(String nome) {
        Iterator<Medico> i = medicos.iterator();

        Medico aux;

        while(i.hasNext()) {
            aux = i.next();
            if (aux.getMedico().equals(nome)) {
                return aux;
            }
        }

        return null;
    }

    // Função que irá editar os dados de um médico
    public boolean modificarMedico(String crmBusca, String novoCrm, String novoNomeMedico, String novaEspecialidade) {
        Iterator<Medico> i = medicos.iterator();
        Medico m;

        while (i.hasNext()) {
            m = i.next();

            if (m.getCRM().equals(crmBusca)) {
                // Removo o médico antiga
                medicos.remove(m);
                // Adiciono um médico com os novos dados
                medicos.add(new Medico(novoNomeMedico, novoCrm, novaEspecialidade));
                notificarObservadores("Médico (a) atualizado (a) com sucesso!");
                return true;
            }
        }
        notificarObservadores("CRM não encontrado. Tente novamente!");
        return false;
    }

    // Função que irá exluir um médico da Coleção, caso ele esteja lá
    public boolean excluirMedico(String crm) {
        Iterator<Medico> i = medicos.iterator();
        Medico m;

        while (i.hasNext()) {
            m = i.next();

            if (m.getCRM().equals(crm)) {
                // Removo o médico
                medicos.remove(m);
                notificarObservadores("Médico (a) excluído (a) com sucesso!");
                return true;
            }
        }
        notificarObservadores("CRM não encontrado. Tente novamente!");
        return false;
    }

    // Método que irá validar o login (a partir dos dados recebidos do controlador):
    public void validarLogin(String email, String senha) {
        String nomePaciente;
        if (usuarios.containsKey(email.toUpperCase())) {
            if ((usuarios.get(email.toUpperCase()).getPassword()).equals(senha)) {
                if ((usuarios.get(email.toUpperCase()).getProfile()).equals("PACIENTE")) {
                    Iterator<Paciente> i = pacientes.iterator();
                    Paciente p;

                    while(i.hasNext()) {
                        p = i.next();
                        if (p.getEmail().toUpperCase().equals(email.toUpperCase())) {
                            nomePaciente = p.getNome();
                            notificarObservadores(nomePaciente, senha, usuarios.get(email.toUpperCase()).getProfile()); // Usuário e senha corretos: retorna dados do usuário
                            setEmailDoPerfil(email.toUpperCase());
                            break;
                        }
                    }
                } else {
                    notificarObservadores(email.toUpperCase(), senha, usuarios.get(email.toUpperCase()).getProfile()); // Usuário e senha corretos: retorna dados do usuário
                    setEmailDoPerfil(email);
                }
            } else {
               notificarObservadores("", "", "DADOS INCORRETOS"); // Usuário e senha corretos: retorna dados do usuário 'nulo' com perfil 'DADOS INCORRETOS'
            }
        } else {
            notificarObservadores("", "", "NÃO CADASTRADO"); // Usuário e senha corretos: retorna dados do usuário 'nulo' com perfil 'não cadastrado'
        }
    }
    // Funcao que irá percorrer o hashset consultas para buscar consultas, por diferentes parametros
    public boolean buscarConsulta1(String data, String paciente, String medico) {
        Iterator<Consulta> p = consultas.iterator();
        Consulta c;
        HashSet<Consulta> DadosConsulta = new HashSet<>();

        while (p.hasNext()){
            c = p.next();
            boolean adicionar = true;

            if ((!data.equals("__/__/____") && !c.getData().equals(data)) || (!paciente.toUpperCase().equals("") && !c.paciente.getNome().toUpperCase().equals(paciente.toUpperCase())) || (!medico.equals("")&& !c.medico.getMedico().toUpperCase().equals(medico.toUpperCase()))){
                adicionar = false;
            }
            if (adicionar){
                DadosConsulta.add(c);
            }
        }
        notificarObservadores(DadosConsulta);
        return false;
    }

    public void adicionarObservador(Observador o) {
        observadores.add(o);
    }
    
    public void removerObservador(Observador o) {
        observadores.remove(o);
    }
    
    // Aqui será usado polimorfismo de sobrecarga
    public void notificarObservadores(String resposta) {
        observadores.forEach((obs) -> obs.update(resposta));
    }

    public void notificarObservadores(String email, String senha) {
        observadores.forEach((obs) -> obs.update(email, senha));
    }

    public void notificarObservadores(Paciente p, String senha) {
        observadores.forEach((obs) -> obs.update(p, senha));
    }

    public void notificarObservadores(Consulta c) {
        observadores.forEach((obs) -> obs.update(c));
    }

    public void notificarObservadores(Medico m) {
        observadores.forEach((obs) -> obs.update(m));
    }


    public void notificarObservadores(String email, String senha, String perfil) {
        observadores.forEach((obs) -> obs.update(email, senha, perfil));
    }

    public void notificarObservadores(HashSet<Consulta>  DadosConsulta) {
        observadores.forEach((obs) -> obs.update(DadosConsulta));
    }

}
