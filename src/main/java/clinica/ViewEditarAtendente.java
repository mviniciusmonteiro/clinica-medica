/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.HashSet;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class ViewEditarAtendente extends JFrame implements ActionListener, KeyListener, WindowListener, Observador {
    JTextField txtEmailBusca; // Para ficar 'visível' entre os dois painéis (busca e resultado)
    JButton btnBuscar;
    JTextField txtNovoEmail;
    JTextField txtNovaSenha;
    JButton btnSalvar, btnExcluir, btnCancelar;
    CRUDController controller;

    public ViewEditarAtendente(CRUDController controller) {
        super("Editar Atendente");
        addWindowListener(this);
        this.controller = controller; // seta o controlador

        JPanel pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.setBackground(Color.WHITE);
        JPanel pnlBusca = new JPanel(new BorderLayout(0, 20)); // espaço 0 na horizontal e 20 na vertical
        pnlBusca.setBackground(Color.WHITE);
        JPanel pnlResultado = new JPanel(new BorderLayout());
        pnlResultado.setBackground(Color.WHITE);

        setPnlBusca(pnlPrincipal, pnlBusca); // seta os componentes do painel de busca e o insere no painel principal
        setPnlResultado(pnlBusca, pnlResultado); // seta os componetes do painel de resultado e o insere no painel principal
        
        setTratadores(); // seta os tratadores

        setContentPane(pnlPrincipal);
        pack(); // Ajusta tamanho da tela aos elementos
        setLocationRelativeTo(null); // Posiciona JFrame no centro da tela
        setVisible(true);
    }

    private void setPnlBusca(JPanel pnlPrincipal, JPanel pnlBusca) {      
        JLabel lblBusca = new JLabel("Buscar Atendente");
        lblBusca.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pnlContainerBusca = new JPanel(new BorderLayout(0, 3));
        pnlContainerBusca.setBackground(Color.WHITE);

        JPanel pnlDadosBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlDadosBusca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlDadosBusca.setBackground(Color.WHITE);

        JLabel lblDadosBusca = new JLabel("Email");
        lblDadosBusca.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField txtEmailBusca = new JTextField(25);
        txtEmailBusca.setFont(new Font("Arial", Font.PLAIN, 14));
        this.txtEmailBusca = txtEmailBusca;
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(55, 50, 170));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.setFocusPainted(false);
        this.btnBuscar = btnBuscar;

        pnlDadosBusca.add(lblDadosBusca);
        pnlDadosBusca.add(txtEmailBusca);
        pnlDadosBusca.add(btnBuscar);

        pnlContainerBusca.add(lblBusca, BorderLayout.NORTH);
        pnlContainerBusca.add(pnlDadosBusca, BorderLayout.SOUTH);

        pnlBusca.add(pnlContainerBusca, BorderLayout.NORTH);
        
        JLabel lblEsq = new JLabel("ESQ");
        lblEsq.setForeground(Color.WHITE);
        JLabel lblDir = new JLabel("DIR");
        lblDir.setForeground(Color.WHITE);
        JLabel lblInf = new JLabel("I");
        lblInf.setForeground(Color.WHITE);

        pnlPrincipal.add(pnlBusca, BorderLayout.CENTER);
        pnlPrincipal.add(lblEsq, BorderLayout.WEST);
        pnlPrincipal.add(lblDir, BorderLayout.EAST);
        pnlPrincipal.add(lblInf, BorderLayout.SOUTH);
    }
    
    private void setPnlResultado(JPanel pnlBusca, JPanel pnlResultado) {
        JPanel pnlDadosResultado = new JPanel(new BorderLayout(0, 5));
        pnlDadosResultado.setBackground(Color.WHITE);
        JLabel lblResultado = new JLabel("Dados do (a) Atendente");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pnlDadosResultadoCentral = new JPanel(new GridLayout(2, 2, 2, 2));
        pnlDadosResultadoCentral.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlDadosResultadoCentral.setBackground(Color.WHITE);

        JLabel lblNovoEmail = new JLabel("Email");
        lblNovoEmail.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblNovaSenha = new JLabel("Senha");
        lblNovaSenha.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField txtNovoEmail = new JTextField(20);
        txtNovoEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNovoEmail.setEnabled(false); // Habilitado apenas após busca
        this.txtNovoEmail = txtNovoEmail;
        JTextField txtNovaSenha = new JTextField(20);
        txtNovaSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNovaSenha.setEnabled(false); // Habilitado apenas após busca
        this.txtNovaSenha = txtNovaSenha;

        pnlDadosResultadoCentral.add(lblNovoEmail);
        pnlDadosResultadoCentral.add(lblNovaSenha);
        pnlDadosResultadoCentral.add(txtNovoEmail);
        pnlDadosResultadoCentral.add(txtNovaSenha);

        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBotoes.setBackground(Color.WHITE);

        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.setBackground(new Color(52, 102, 0));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalvar.setFocusPainted(false); // Botão deixa de ficar 'marcado' após clique
        btnSalvar.setEnabled(false); // Habilitado apenas após consulta
        this.btnSalvar = btnSalvar;

        JButton btnExcluir = new JButton("Excluir Atendente");
        btnExcluir.setBackground(new Color(205, 0, 0));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExcluir.setFocusPainted(false); // Botão deixa de ficar 'marcado' após clique
        btnExcluir.setEnabled(false); // Habilitado apenas após consulta
        this.btnExcluir = btnExcluir;

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(212, 149, 40));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setFocusPainted(false);
        this.btnCancelar = btnCancelar;

        pnlBotoes.add(btnCancelar);
        pnlBotoes.add(btnExcluir);
        pnlBotoes.add(btnSalvar);
        
        pnlDadosResultado.add(lblResultado, BorderLayout.NORTH);
        pnlDadosResultado.add(pnlDadosResultadoCentral, BorderLayout.CENTER);
        pnlDadosResultado.add(pnlBotoes, BorderLayout.SOUTH);
        pnlResultado.add(pnlDadosResultado, BorderLayout.NORTH);

        pnlBusca.add(pnlResultado, BorderLayout.CENTER);
    }

    private void setTratadores() {
        txtEmailBusca.addKeyListener(this);
        txtNovoEmail.addKeyListener(this);
        txtNovaSenha.addKeyListener(this);
        
        btnBuscar.addActionListener(this);
        btnSalvar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnCancelar.addActionListener(this);
    }
    
    public String getEmailBusca() {
        return (new String(txtEmailBusca.getText().toUpperCase()));
    }

    public String getNovoEmail() {
        return (new String(txtNovoEmail.getText().toUpperCase()));
    }

    public String getNovaSenha() {
        return (new String(txtNovaSenha.getText()));
    }

    public void setNovoEmail(String novoEmail) {
        this.txtNovoEmail.setText(novoEmail);
    }

    public void setNovaSenha(String novaSenha) {
        this.txtNovaSenha.setText(novaSenha);
    }

    public void setEnabledTxt(boolean e) {
        this.txtNovoEmail.setEnabled(e);
        this.txtNovaSenha.setEnabled(e);
        this.txtNovoEmail.grabFocus(); // Coloca o foco na caixa de texto de novo email
    }
    public void limpar() {
        setNovoEmail("");
        setNovaSenha("");
    }

    // TRATADORES DOS BOTÕES E CAIXAS DE TEXTO
    // Tratador dos botões
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            controller.buscarAtendente(getEmailBusca());
        } else if (e.getSource() == btnSalvar) {
            controller.modificarAtendente(getEmailBusca(), getNovoEmail(), getNovaSenha());
        } else if (e.getSource() == btnExcluir) {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o (a) atendente? Essa ação é irreversível!", "Confirmar exclusão", JOptionPane.OK_CANCEL_OPTION);
            if (resposta == 0) {
                controller.excluirAtendente(getEmailBusca());
            } else {
                txtEmailBusca.grabFocus();
            }
        } else {
            controller.removerObservador(this); // Remove esta tela dos observadores do model
            dispose();
        }
    }

    // Tratador das caixas de texto
    public void keyPressed(KeyEvent e) { // Invocado quando uma tecla é pressionada
    }

    public void	keyReleased(KeyEvent e) { // Invocado quando uma tecla é liberada
        if (this.getNovoEmail().equals("") || this.getNovaSenha().equals("")) {
            btnSalvar.setEnabled(false);
        } else {
            btnSalvar.setEnabled(true);
        }

        if (e.getSource() == txtEmailBusca) {
            btnSalvar.setEnabled(false);
            limpar();
            btnExcluir.setEnabled(false);
        }
    }
    
    public void keyTyped(KeyEvent e) { // Invocado quando uma chave foi digitada
    }

    // Implementação dos métodos da interface Observador
    // Polimorfismo de sobrecarga aqui
    public void update(String resposta) {
        if (resposta.contains("sucesso")) {
            JOptionPane.showMessageDialog(null, resposta, "SUCESSO!", 1);
        } else {
            JOptionPane.showMessageDialog(null, resposta, "ERRO!", 0);
        }
        
        limpar();
        setEnabledTxt(false);
        txtEmailBusca.grabFocus();
    }

    public void update(String email, String senha) {
        if (!email.equals("")) {
            setNovoEmail(email);
            setNovaSenha(senha);
            btnExcluir.setEnabled(true);
            setEnabledTxt(true);
        } else {
            JOptionPane.showMessageDialog(null, "Atendente não encontrado (a). Tente novamente.", "ERRO!", 0);
            limpar(); // limpa as caixas
            btnExcluir.setEnabled(false);
            setEnabledTxt(false);
            txtEmailBusca.grabFocus();
        }
    }
    public void update(Paciente p, String senha) {}

    public void update(Consulta c) {}

    public void update(Medico c) {}

    public void update(String email, String senha, String perfil) {}

    public void update(HashSet<Consulta>  DadosConsulta) {}
	
    // Tratadores da janela
    public void	windowActivated(WindowEvent e) {}
    public void	windowClosed(WindowEvent e) {}
    public void	windowClosing(WindowEvent e) { // Chamado quando clica no X da janela
        controller.removerObservador(this); // remove a janela dos observadore do model
        this.dispose(); // Fecha apenas esta tela
    }
    public void	windowDeactivated(WindowEvent e) {}
    public void	windowDeiconified(WindowEvent e) {}
    public void	windowIconified(WindowEvent e) {}
    public void	windowOpened(WindowEvent e) {}
}
