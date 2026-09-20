/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class Atendente {
    String email;
    String senha;

    public Atendente() {}
    public Atendente(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    //public void lerAtendente(){ } -> implementação do método no model

    public void escreverAtendente( ){
        try {
            FileWriter fw = new FileWriter("./db/atendentes.txt", true); // true ativa append
            BufferedWriter bw = new BufferedWriter( fw );
            bw.write(this.getEmail());
            bw.newLine();
            bw.write(this.getSenha());
            bw.newLine();
            

            bw.close();
        } catch (Exception e) {
            System.out.println("Problema ao adicionar informacao no arquivo atendentes!");
        }
    }

    public void setEmail(String email){
        this.email = email;
    };

    public void setSenha(String senha){
        this.senha = senha;
    };

    public String getEmail(){
        return email;
    };

    public String getSenha(){
        return senha;
    };
}
