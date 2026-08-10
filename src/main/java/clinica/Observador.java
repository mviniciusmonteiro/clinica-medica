/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.util.HashSet;

public interface Observador {
    public void update(String resposta);

    public void update(Paciente p, String senha);

    public void update(String email, String senha);

    public void update(Consulta c);

    public void update(Medico m);

    public void update(String email, String senha, String perfil);

    public void update(HashSet<Consulta>  DadosConsulta);
}