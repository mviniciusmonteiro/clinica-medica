/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class IncluirMed extends JFrame implements ActionListener, MouseListener, KeyListener, Observador, WindowListener {
    private static JFrame janela;
    private static JPanel painel;
    private static JLabel lblNome, lblespeci;
    private static JTextField nomeTF, especiTF;
    private static JLabel lblCRM;
    private static JFormattedTextField crmFTF;
    private static JButton btnsalvar;
    private static JLabel lblcancelar;
    private static JPanel borda;
    private static JLabel lblcadastro;
    CRUDController controller;

    ImageIcon icone;
    ImageIcon iconee;
    
    public IncluirMed(CRUDController controller) {
        this.controller = controller;
        Inc();
    }

    public void Inc() {
        janela = new JFrame();
        painel = new JPanel();

        janela.setSize(450, 350);
        janela.setResizable(false);
        janela.setTitle("Cadastrar médico");
        janela.setLocationRelativeTo(null);

        this.janela = janela;

		janela.add(painel);
        
        painel.setLayout(null); 
        painel.setBackground(Color.WHITE);
        
        lblcadastro = new JLabel("Cadastro");
        lblcadastro.setFont(new Font("Serif", Font.BOLD, 20));
        lblcadastro.setBounds(30, 77, 200, 40);
        painel.add(lblcadastro);

        lblNome = new JLabel("Nome");
        lblNome.setBounds(100, 120, 80, 25);
        painel.add(lblNome);
        
        nomeTF = new JTextField(20);
        nomeTF.setBounds(142, 120, 165, 25);
        nomeTF.addKeyListener((KeyListener) this);
        painel.add(nomeTF);

        lblCRM = new JLabel("CRM");
        lblCRM.setBounds(110, 150, 80, 25);
        painel.add(lblCRM);
        
        try {
            MaskFormatter mascaraCrm = new MaskFormatter("########-#");
            mascaraCrm.setPlaceholderCharacter('_');
            crmFTF = new JFormattedTextField(mascaraCrm);
            crmFTF.setPreferredSize(new Dimension(120, 25));
            crmFTF.setBounds(142, 150, 85, 25);
            crmFTF.addKeyListener((KeyListener) this);
        } catch (Exception e) {
            System.out.println("Falha na formatação.");
        }
        painel.add(crmFTF);

        lblespeci = new JLabel("Especialidade");
        lblespeci.setBounds(35, 180, 180, 25);
        painel.add(lblespeci);
        
        especiTF = new JTextField(20);
        especiTF.setBounds(142, 180, 165, 25);
        especiTF.addKeyListener((KeyListener) this);
        painel.add(especiTF);
        
        btnsalvar = new JButton("Salvar");
        btnsalvar.setForeground(Color.WHITE);
        btnsalvar.setBackground(new Color(52,102,0));
        btnsalvar.setBounds(350, 270, 80, 25);
        btnsalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnsalvar.addActionListener((ActionListener) this);
        btnsalvar.setEnabled(false);
        painel.add(btnsalvar);

        lblcancelar = new JLabel("Cancelar");
        lblcancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblcancelar.addMouseListener(this);
        lblcancelar.setBounds(270, 270, 130, 25);
        lblcancelar.setForeground(new Color(205,0,0));
        painel.add(lblcancelar);

        borda = new JPanel();
		borda.setBounds(30, 110, 390, 103);
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

        if (e.getSource() == btnsalvar) {

            resposta = JOptionPane.showConfirmDialog(null,"Deseja realmente adicionar o Médico " + nomeTF.getText() +  "(" + especiTF.getText() +")" + " CRM "+ crmFTF.getText() +" ?", "Confirmar informações", JOptionPane.OK_CANCEL_OPTION);
        if (resposta == 0)
            if(controller.IncluirMedico(nomeTF.getText(), crmFTF.getText(),especiTF.getText())) {
                
                JOptionPane.showMessageDialog(null, nomeTF.getText() + " CRM " + crmFTF.getText() + " adiconado com sucesso.", "Confirmado",JOptionPane.NO_OPTION , icone);
                janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));    
            }   
            else {
                JOptionPane.showMessageDialog(null, "Não foi possível adicionar o médico.", "Não confirmado",JOptionPane.NO_OPTION , iconee);
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
        //retorna a cor normal do canecelar quando o mouse sai dele
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

        if (nomeTF.getText().equals("")|| crmFTF.getText().equals("________-_")  ||  especiTF.getText().equals("")) {
            btnsalvar.setEnabled(false);
        } else {
            btnsalvar.setEnabled(true);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent arg0) {}
    
    @Override
    public void keyTyped(KeyEvent arg0) {}


    public void update(String resposta) {}

    public void update(Paciente p, String senha) {}

    public void update(String email, String senha) {}

    public void update(Consulta c) {}

    public void update(Medico m) {}

    public void update(String email, String senha, String perfil) {}

    public void update(HashSet<Consulta> DadosConsultas){}

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