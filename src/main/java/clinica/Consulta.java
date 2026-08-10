/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class Consulta {
    String id;
    String data;
    Paciente paciente;
    String hora;
    Medico medico;

    public Consulta(String id, String data, String hora, Paciente p, Medico medico) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.paciente = p;
        this.medico = medico;
    }

    // SETTERS
    public void setId(String id) {
        this.id = id;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    // GETTERS
    public String getId() {
        return this.id;
    }

    public String getData() {
        return this.data;
    }

    public String getHora() {
        return this.hora;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public Medico getMedico() {
        return this.medico;
    }

    // manipulaçao de memoria
    public void escreverConsulta(boolean b) {
        try {
            FileWriter fw = new FileWriter("./db/consultas.txt", b); // o segundo parâmetro define append (true = append)
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.getId());
            bw.newLine();
            bw.write(this.getData());
            bw.newLine();
            bw.write(this.getHora());
            bw.newLine();
            bw.write(this.paciente.getNome());
            bw.newLine();
            bw.write(this.paciente.getDataNascimento());
            bw.newLine();
            bw.write(this.paciente.getCpf());
            bw.newLine();
            bw.write(this.paciente.getSexo());
            bw.newLine();
            bw.write(this.paciente.getEmail());
            bw.newLine();
            bw.write(this.medico.getMedico());
            bw.newLine();
            bw.write(this.medico.getCRM());
            bw.newLine();
            bw.write(this.medico.getEspecialidade());
            bw.newLine();

            bw.close();
        } catch (Exception e) {
            System.out.println("Falha ao escrever no arquivo consultas.txt");
        }
    }
}
