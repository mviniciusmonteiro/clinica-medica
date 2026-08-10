/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;//Inc()
import java.util.HashSet;

public class ViewLogin extends JFrame implements ActionListener, KeyListener, Observador, WindowListener {
    JTextField txtUsuario;
    JPasswordField txtSenha;
    JButton btnEntrar;
    CRUDController controller;

	public ViewLogin(CRUDController controller) {
		super("Login"); // Chama construtor da superclasse
		setMinimumSize(new Dimension(500, 400));
		setResizable(false);

        addWindowListener(this);

        this.controller = controller; // seta controlador

        // Painel principal da janela (abrigará todos os demais componentes)
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setLayout(new BorderLayout());
		pnlPrincipal.setBackground(Color.WHITE);
        
        JPanel pnlCabecalho = new JPanel(); // Painel que ocupará a parte superior do painel principal
        pnlCabecalho.setBackground(Color.WHITE);
        pnlPrincipal.add(pnlCabecalho, BorderLayout.NORTH);
        
        JPanel pnlCentral = new JPanel(); // Painel que ocupará a parte central do painel principal
        pnlCentral.setLayout(new FlowLayout());
        pnlCentral.setBackground(Color.WHITE);

        JPanel pnlLogin = new JPanel(); // Painel que conterá os elementos necessários para o login (label, TextField...)
        pnlLogin.setLayout(new GridLayout(6, 1));

        JLabel lblUsuario = new JLabel("Email");
        lblUsuario.setVerticalAlignment(JLabel.BOTTOM);
        lblUsuario.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlLogin.add(lblUsuario);
        
        JTextField txtUsuario = new JTextField(25);
        txtUsuario.setFont(new Font("Arial", 0, 16));
        txtUsuario.setAlignmentY(JTextField.BOTTOM_ALIGNMENT);
        this.txtUsuario = txtUsuario;
        pnlLogin.add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setVerticalAlignment(JLabel.BOTTOM);
        lblSenha.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlLogin.add(lblSenha);
        
        JPasswordField txtSenha = new JPasswordField(25);
        txtSenha.setFont(new Font("Arial", 0, 16));
        this.txtSenha = txtSenha;
        pnlLogin.add(txtSenha);

        JPanel pnlBotao = new JPanel(); // Painel do botão (necessário para garantir melhor disposição do botão na tela)
        pnlBotao.setBackground(Color.WHITE);
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEntrar.setBackground(new Color(52, 102, 0));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setEnabled(false); // Habilitado apenas quando as duas caixas de texto estiverem preencidas
        this.btnEntrar = btnEntrar;
        pnlBotao.add(btnEntrar);

        btnEntrar.addActionListener(this);
        txtUsuario.addKeyListener(this);
        txtSenha.addKeyListener(this);

        // Adiciona os painéis internos dentro dos painéis externos
        pnlLogin.add(pnlBotao); 
        pnlLogin.setBackground(Color.WHITE);

        pnlCentral.add(pnlLogin);

        pnlPrincipal.add(pnlCentral, BorderLayout.CENTER);

        JPanel pnlRodape = new JPanel();
        pnlRodape.setBackground(Color.WHITE);
        pnlPrincipal.add(pnlRodape, BorderLayout.SOUTH);

		setContentPane(pnlPrincipal); // Define o painel de conteúdo
		pack(); // Ajusta o tamanho da tela aos componentes (respeitando limite mínimo estabelecido acima)
        setLocationRelativeTo(null); // Posiciona o JFrame no centro da tela
        setVisible(true);
    }

    // Métodos getters (retornam usuário e senha)
    String getUsername() {
        return txtUsuario.getText().toUpperCase();
    }

    String getPassword() {
        String senha = new String(txtSenha.getPassword());
        return senha;
    }

    // Implementação dos métodos da interface Observador (polimorfismo de sobrecarga aqui)
    public void update(String resposta) {}

    public void update(Paciente p, String senha) {}

    public void update(String email, String senha) {}

    public void update(Consulta c) {}

    public void update(Medico m) {}

    public void update(String email, String senha, String perfil) {
        if (perfil.equals("DADOS INCORRETOS")) {
            JOptionPane.showMessageDialog(null, "Senha incorreta. Tente novamente!", "ERRO!", 0);
            this.txtSenha.grabFocus(); // Retorna o foco para a caixa de texto da senha
        } else if (perfil.equals("NÃO CADASTRADO")) {
            JOptionPane.showMessageDialog(null, "Usuário não cadastrado. Tente novamente!", "ERRO!", 0);
            this.txtUsuario.grabFocus(); // Retorna o foco para a caixa de texto do usuário
        } else {
            // Mostra a tela inicial, passando o perfil retornando para que ela atualize as permissões do usuário
            dispose();
            if (!perfil.equals("PACIENTE")) {
                email = "";
            }
            ViewTelaInicial telaInicial = new ViewTelaInicial(controller, perfil, email);
            controller.adicionarObservador(telaInicial);
        }
    }

    
    public void update(HashSet<Consulta>  DadosConsulta) {}

    // Tratador do botão
    public void actionPerformed (ActionEvent e) { 
        controller.validarLogin(this.getUsername(), this.getPassword());
    }

    // Tratamento das caixas de texto
    public void keyPressed(KeyEvent e) { // Invocado quando uma tecla é pressionada
    }

    public void	keyReleased(KeyEvent e) { // Invocado quando uma tecla é liberada
        if (this.getUsername().equals("") || this.getPassword().equals("")) {
            btnEntrar.setEnabled(false);
        } else {
            btnEntrar.setEnabled(true);
        }
    }
    
    public void keyTyped(KeyEvent e) { // Invocado quando uma chave foi digitada
    }

    // Tratadores de Janela
    public void windowActivated(WindowEvent e) {
    }
    public void windowClosed(WindowEvent e) {
    }
    public void windowClosing(WindowEvent e) { // Chamado quando clica no X da janela
        controller.removerObservador(this);
        // Salva as informações das Coleções nos respectivos arquivos txt
        controller.escreverPacientes();
        controller.escreverUsuarios();
        controller.escreverConsultas();
        controller.escreverMedicos();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha todo o sistema
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