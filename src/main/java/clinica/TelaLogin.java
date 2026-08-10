/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

public class TelaLogin {
    public static void main (String args[]) {
        // Model
        CRUDModel model = new CRUDModel();

        // Controller
        CRUDController controller = new CRUDController(model);

        // View
        ViewLogin telaLogin = new ViewLogin(controller);
        model.adicionarObservador(telaLogin);
    }
}
