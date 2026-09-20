/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

public class CadPaciente implements ActionListener, MouseListener, KeyListener, Observador, WindowListener {
    private static JLabel lblnome, lblcpf, lblcadastro, lbldifsen,lbldata, lblgenero, lblcancelar, lblemail, lblsenha, lblsenhanov;
    private static JTextField nomeTF, emailTF;
    private static JFrame janela;
    private static JFormattedTextField cpfFTF;
    private static JPanel painel, borda;
    private static JComboBox<String> generos;
    private static JButton btnmarcar;
    private static JDateChooser escData;
    private static JPasswordField senhaPF, senhanovPF;

    CRUDController controller;

    ImageIcon icone;
    ImageIcon iconee;
    
    String data;    
    String genero[];
    
    CadPaciente  (CRUDController controller) {
        this.controller = controller;
        Inc();
    }

    public void Inc() {
        janela = new JFrame();
        painel = new JPanel();

        janela.setSize(450, 450);
        janela.setResizable(false);
        janela.setTitle("Cadastrar paciente");
        janela.addMouseListener(this);
        janela.setLocationRelativeTo(null);

        this.janela = janela;

		janela.add(painel);
        
        painel.setLayout(null); 
        painel.setBackground(Color.WHITE);

        lblcadastro = new JLabel("Cadastro");
        lblcadastro.setFont(new Font("Serif", Font.BOLD, 20));
        lblcadastro.setBounds(30, 22, 200, 40);
        painel.add(lblcadastro);

        lblnome = new JLabel("Nome do(a) paciente");
        lblnome.setBounds(65, 55, 180, 25);
        painel.add(lblnome);

        nomeTF = new JTextField();
        nomeTF.setBounds(65, 75, 307, 25);
        painel.add(nomeTF);

        lblcpf = new JLabel("CPF");
        lblcpf.setBounds(65, 104, 30, 25);
        painel.add(lblcpf);


        try {
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            cpfFTF = new JFormattedTextField(mascaraCpf);
            cpfFTF.setPreferredSize(new Dimension(120, 25));
            cpfFTF.setBounds(94, 104, 110, 25);
        } catch (Exception e) {
            System.out.println("Falha na formatação.");
        }
        painel.add(cpfFTF);

        lbldata = new JLabel("Data de Nascimento");
        lbldata.setBounds(65, 135, 190, 25);
        painel.add(lbldata);

        escData = new JDateChooser();
        escData.setBounds(210, 135, 160, 25);
        escData.setLocale(new Locale("pt", "BR"));
        painel.add(escData);
    

        lblgenero = new JLabel("Sexo");
        lblgenero.setBounds(65, 165, 110, 25);
        painel.add(lblgenero);

        String genero[] = {"Masculino", "Feminino"};
        generos = new JComboBox<>(genero);
        generos.setBounds(102, 165, 268, 25);
        painel.add(generos);


        lblemail = new JLabel("Email");
        lblemail.setBounds(65, 185, 268, 25);
        painel.add(lblemail);

        emailTF = new JTextField();
        emailTF.setBounds(65, 205, 307, 25);
        painel.add(emailTF);

        lblsenha = new JLabel("senha");
        lblsenha.setBounds(65, 225, 180, 25);
        painel.add(lblsenha);

        senhaPF = new JPasswordField();
        senhaPF.setBounds(65, 245, 307, 25);
        painel.add(senhaPF);

        lblsenhanov = new JLabel("senha novamente");
        lblsenhanov.setBounds(65, 265, 180, 25);
        painel.add(lblsenhanov);

        senhanovPF = new JPasswordField();
        senhanovPF.setBounds(65, 285, 307, 25);
        painel.add(senhanovPF);

        lbldifsen = new JLabel("*As senhas não correspondem, por favor digite novamente");
        lbldifsen.setBounds(65, 314, 400, 25);
        lbldifsen.setFont(new Font("Serif", Font.BOLD, 10));
        lbldifsen.setForeground(new Color(250,0,0));
        lbldifsen.setVisible(false);
        painel.add(lbldifsen);

        btnmarcar = new JButton("Cadastrar");
        btnmarcar.setForeground(Color.WHITE);
        btnmarcar.setBackground(new Color(52,102,0));
        btnmarcar.setBounds(330, 380, 105, 25);
        btnmarcar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnmarcar.addActionListener((ActionListener) this);
        btnmarcar.setEnabled(false);
        painel.add(btnmarcar);

        lblcancelar = new JLabel("cancelar");
        lblcancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblcancelar.addMouseListener(this);
        lblcancelar.setBounds(250, 380, 80, 25);
        lblcancelar.setForeground(new Color(205,0,0));
        painel.add(lblcancelar);

        borda = new JPanel();
		borda.setBounds(30, 55, 390, 264);
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
            resposta = JOptionPane.showConfirmDialog(null,"Deseja realmente adicionar " + nomeTF.getText()+ " ?" , "Confirmar informações", JOptionPane.OK_CANCEL_OPTION);
            if (resposta == 0)
                if(controller.IncluirPaciente(cpfFTF.getText(), emailTF.getText(), senhaPF.getText(), nomeTF.getText(), getDate(), generos.getSelectedItem().toString())) {
                    
                    JOptionPane.showMessageDialog(null, nomeTF.getText() + " foi adicionado adiconado com sucesso.", "Confirmado",JOptionPane.NO_OPTION , icone);
                    janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));    
                }   
                else {
                    JOptionPane.showMessageDialog(null, "Não foi posivel adicionar " + nomeTF.getText(), "Não confirmado",JOptionPane.NO_OPTION , iconee);
                    
                }
            }


        if (e.getSource() == btnmarcar) {
            if (senhaPF.getText().equals(senhanovPF.getText())) {
                lbldifsen.setVisible(false);
                controller.IncluirPaciente(cpfFTF.getText(), emailTF.getText(), senhaPF.getText(), nomeTF.getText(), getDate(), generos.getSelectedItem().toString());
            } else {
                lbldifsen.setVisible(true);
            }
        }
    }

    
    public String getDate() {
        String delim; 
        int cont = 0;
        String dat[] = {"", "" , "" , "", "", ""};

        delim = " ";
        StringTokenizer st = new StringTokenizer(escData.getDate().toString(), delim);

        for(cont = 0; st.hasMoreTokens(); cont++) {
            dat[cont] = st.nextToken();
        }

        return dat[2] + "/" + MonthtoNmbr(dat[1].toLowerCase()) + "/" + dat[5];
    }

    public String MonthtoNmbr(String month) {
        if (month.equals("jan")){
            return "01";
        }
        if (month.equals("feb")){
            return "02";
        }
        if (month.equals("mar")){
            return "03";
        }
        if (month.equals("apr")){
            return "04";
        }
        if (month.equals("may")){
            return "05";
        }
        if (month.equals("jun")){
            return "06";
        }
        if (month.equals("jul")){
            return "07";
        }
        if (month.equals("aug")){
            return "08";
        }
        if (month.equals("sep")){
            return "09";
        }
        if (month.equals("oct")){
            return "10";
        }
        if (month.equals("nov")){
            return "11";
        }
        return "12";
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
        data = ((JTextField)escData.getDateEditor().getUiComponent()).getText();
        if (arg0.getSource() == janela) {
            //checa se os Tfields nome ou data ou cpf estão vazios se sim salvar Disabade
            if (emailTF.getText().equals("") || nomeTF.getText().equals("") || senhanovPF.getText().equals("") || cpfFTF.getText().equals("___.___.___-__") || senhaPF.getText().equals("") || data.equals("")) {
                btnmarcar.setEnabled(false);
            } else {
                btnmarcar.setEnabled(true);
            }
        } else {
            //retorna a cor normal do canecelar quando o mouse sai dele
            lblcancelar.setForeground(new Color(205,0,0));
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}




    // KEY LISTERNERS

    
    @Override
    public void keyReleased(KeyEvent arg0) {
        // se pelo menos um textfield estiver vazio então salvar fica disabilitado
        data = ((JTextField)escData.getDateEditor().getUiComponent()).getText();

        if (emailTF.getText().equals("") || nomeTF.getText().equals("") || senhanovPF.getText().equals("") || cpfFTF.getText().equals("") || senhaPF.getText().equals("") || data.equals("")) {
            btnmarcar.setEnabled(false);
        } else {
            btnmarcar.setEnabled(true);
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

    // Tratadores da janela
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


