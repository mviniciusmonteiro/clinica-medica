/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.util.HashSet;

public interface ObservableCRUD {
    public void adicionarObservador(Observador o);

    public void removerObservador(Observador o);

    public void notificarObservadores(String resposta);

    public void notificarObservadores(String email, String senha);

    public void notificarObservadores(Paciente p, String senha);

    public void notificarObservadores(Consulta c);

    public void notificarObservadores(Medico m);

    public void notificarObservadores(String email, String senha, String perfil);

    public void notificarObservadores(HashSet<Consulta> DadosConsulta);

}