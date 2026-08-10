/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class Paciente {
    String nome, dataNascimento, cpf, sexo, email;

    public Paciente() {}
    public Paciente(String nome, String dataNascimento, String cpf, String sexo, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
    }

    public void escreverPaciente(boolean b) {
        try {
            FileWriter fw = new FileWriter("./db/pacientes.txt", b); // o segundo parâmetro define append (true = append)
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.getNome());
            bw.newLine();
            bw.write(this.getDataNascimento());
            bw.newLine();
            bw.write(this.getCpf());
            bw.newLine();
            bw.write(this.getSexo());
            bw.newLine();
            bw.write(this.getEmail());
            bw.newLine();

            bw.close();
        } catch (Exception e) {
            System.out.println("Falha na escrita do arquivo pacientes.txt");
        }
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo.toUpperCase();
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return nome.toUpperCase();
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public String getCpf() {
        return cpf;
    }
    public String getSexo() {
        return sexo.toUpperCase();
    }
    public String getEmail() {
        return email.toUpperCase();
    }
}
