/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.io.*;

public class Medico {

    String medico;
    String crm;
    String especialidade;

    public Medico(String medico, String crm, String especialidade) {
        this.medico = medico;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public void escreverMedico(boolean b) {
        try {
            FileWriter arq = new FileWriter("./db/medicos.txt", b);

            BufferedWriter cp = new BufferedWriter(arq);

            cp.write(this.getMedico());
            cp.newLine();
            cp.write(this.getCRM());
            cp.newLine();
            cp.write(this.getEspecialidade());
            cp.newLine();

            cp.close();

        } catch (Exception e) {
            System.out.println("Falha ao abrir o arquivo medicos.txt");
        }

    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setCRM(String crm) {
        this.crm = crm;
    }

    public String getMedico() {
        return medico;
    }

    public String getCRM() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }
}