/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CadAten implements ActionListener, MouseListener, KeyListener, Observador, WindowListener {
    private static JLabel lblemail;
    private static JTextField emailTF;
    private static JLabel lblsenha;
    private static JPasswordField senhaTF;
    private static JPasswordField senhanovTF;
    private static JFrame janela;
    private static JPanel painel;
    private static JLabel lblsenhanov;
    private static JLabel lblcadastro;
    private static JButton btnmarcar;
    private static JLabel lblcancelar;
    private static JPanel borda;
    private static JLabel dsenhas;

    CRUDController controller;
    
    ImageIcon icone;
    ImageIcon iconee;

    public CadAten (CRUDController controller) {
        this.controller = controller;
        Inc();
    }

    public void Inc() {
        janela = new JFrame();
        painel = new JPanel();

        janela.setSize(450, 350);
        janela.setResizable(false);
        janela.setTitle("Cadastrar atendente");
        janela.setLocationRelativeTo(null);

        this.janela = janela;

		janela.add(painel);
        
        painel.setLayout(null); 
        painel.setBackground(Color.WHITE);

        lblcadastro = new JLabel("Cadastro");
        lblcadastro.setFont(new Font("Serif", Font.BOLD, 20));
        lblcadastro.setBounds(30, 37, 200, 40);
        painel.add(lblcadastro);

        lblemail = new JLabel("Email do(a) atendedente");
        lblemail.setBounds(65, 70, 180, 25);
        painel.add(lblemail);

        emailTF = new JTextField();
        emailTF.setBounds(65, 90, 307, 25);
        emailTF.addKeyListener((KeyListener) this);
        painel.add(emailTF);

        lblsenha = new JLabel("Senha");
        lblsenha.setBounds(65, 119, 100, 25);
        painel.add(lblsenha);

        senhaTF = new JPasswordField();
        senhaTF.setBounds(65, 139, 307, 25);
        senhaTF.addKeyListener((KeyListener) this);
        painel.add(senhaTF);
    
        lblsenhanov = new JLabel("Senha novamente");
        lblsenhanov.setBounds(65, 159, 150, 25);
        painel.add(lblsenhanov);

        senhanovTF = new JPasswordField();
        senhanovTF.setBounds(65, 179, 307, 25);
        senhanovTF.addKeyListener((KeyListener) this);
        painel.add(senhanovTF);

        dsenhas = new JLabel("*As senhas não correspondem, por favor digite novamente");
        dsenhas.setBounds(65, 215, 400, 25);
        dsenhas.setFont(new Font("Serif", Font.BOLD, 10));
        dsenhas.setForeground(new Color(250,0,0));
        dsenhas.setVisible(false);
        painel.add(dsenhas);

        btnmarcar = new JButton("Cadastrar");
        btnmarcar.setForeground(Color.WHITE);
        btnmarcar.setBackground(new Color(52,102,0));
        btnmarcar.setBounds(330, 270, 105, 25);
        btnmarcar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnmarcar.addActionListener((ActionListener) this);
        btnmarcar.setEnabled(false);
        painel.add(btnmarcar);

        lblcancelar = new JLabel("cancelar");
        lblcancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblcancelar.addMouseListener(this);
        lblcancelar.setBounds(250, 270, 80, 25);
        lblcancelar.setForeground(new Color(205,0,0));
        painel.add(lblcancelar);

        borda = new JPanel();
		borda.setBounds(30, 70, 390, 146);
        borda.setBackground(Color.WHITE);
		borda.setBorder(BorderFactory.createLineBorder(Color.black));  
        painel.add(borda);

        janela.setVisible(true);
    }



    // ACTION LISTENERS

    @Override
    public void actionPerformed(ActionEvent e) {

        int resposta;
        icone = new ImageIcon("success.png");
        iconee = new ImageIcon("failed.png");
        
        if (e.getSource() == btnmarcar) {
            resposta = JOptionPane.showConfirmDialog(null,"Deseja realmente adicionar " + emailTF.getText()+ " ?" , "Confirmar informações", JOptionPane.OK_CANCEL_OPTION);
            if (resposta == 0)
                if(controller.IncluirAtendente(emailTF.getText(), senhaTF.getText())) {
                    
                    JOptionPane.showMessageDialog(null, emailTF.getText() + " foi adicionado adiconado com sucesso.", "Confirmado",JOptionPane.NO_OPTION , icone);
                    janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
                }   
                else {
                    JOptionPane.showMessageDialog(null, "Não foi posivel adicionar " + emailTF.getText(), "Não confirmado",JOptionPane.NO_OPTION , iconee);
                }
            }
    }

    // MOUSE LISTENERS

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // Se clicar no cancelar a janela fecha
        if (arg0.getSource() == lblcancelar) {
            janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
        }
    }
        
    @Override

    public void mouseEntered(MouseEvent arg0) {
        //muda a cor do cancelar quando o mouse entra nele
        lblcancelar.setForeground(new Color(250,0,0));
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        lblcancelar.setForeground(new Color(205,0,0));
    }

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}




    // KEY LISTERNERS

    
    @Override
    public void keyReleased(KeyEvent arg0) {
        // se pelo menos um textfield estiver vazio então salvar fica disabilitado
        if (emailTF.getText().equals("") || senhaTF.getText().equals("") || senhanovTF.getText().equals("")) {
            btnmarcar.setEnabled(false);
        } else {
            btnmarcar.setEnabled(true);
        }
    }
    @Override
    public void keyPressed(KeyEvent arg0) {}
    
    @Override
    public void keyTyped(KeyEvent arg0) {}


    @Override
    public void update(String resposta) {}

    @Override
    public void update(Paciente p, String senha) {}

    @Override

    public void update(String email, String senha) {}

    public void update(Consulta c) {}

    public void update(Medico m) {}

    public void update(String email, String senha, String perfil) {}

    public void update(HashSet<Consulta>  DadosConsulta) {}

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) { // Chamado quando clica no X da janela
        controller.removerObservador(this); // remove a janela dos observadore do model
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }
    
}
