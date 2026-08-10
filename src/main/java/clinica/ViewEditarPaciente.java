/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.awt.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class ViewEditarPaciente extends JFrame implements ActionListener, KeyListener, WindowListener, Observador {
    JFormattedTextField txtNovoCpfPaciente, txtCpfBusca, txtNovaDataNascimentoPaciente; // Para ficar 'visível' entre os dois painéis (busca e resultado)                                                                                    
    JTextField txtNovoEmail, txtNovaSenha, txtNovoMedico, txtNovoNomePaciente;
    JComboBox<String> txtNovoSexoPaciente;
    JButton btnBuscar, btnSalvar, btnExcluir, btnCancelar;
    CRUDController controller;

    public ViewEditarPaciente(CRUDController controller) {
        super("Editar Paciente");
        addWindowListener(this);
        this.controller = controller; // Seta controlador

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

    public void setPnlBusca(JPanel pnlPrincipal, JPanel pnlBusca) {
        JLabel lblBusca = new JLabel("Buscar Paciente");
        lblBusca.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pnlContainerBusca = new JPanel(new BorderLayout(0, 3));
        pnlContainerBusca.setBackground(Color.WHITE);

        JPanel pnlDadosBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlDadosBusca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlDadosBusca.setBackground(Color.WHITE);

        JLabel lblDadosBusca = new JLabel("CPF do (a) Paciente");
        lblDadosBusca.setFont(new Font("Arial", Font.BOLD, 14));
        try {
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            JFormattedTextField txtCpfBusca = new JFormattedTextField(mascaraCpf);
            txtCpfBusca.setFont(new Font("Arial", Font.PLAIN, 14));
            txtCpfBusca.setPreferredSize(new Dimension(120, 25));
            this.txtCpfBusca = txtCpfBusca;
        } catch (Exception e) {
            System.out.println("Falha na formatação.");
        }
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(55, 50, 170));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.setFocusPainted(false);
        this.btnBuscar = btnBuscar;

        pnlDadosBusca.add(lblDadosBusca);
        pnlDadosBusca.add(txtCpfBusca);
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

    public void setTratadores() {
        btnBuscar.addActionListener(this);
        btnSalvar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnCancelar.addActionListener(this);

        txtCpfBusca.addKeyListener(this);
        txtNovoEmail.addKeyListener(this);
        txtNovaSenha.addKeyListener(this);
        txtNovoNomePaciente.addKeyListener(this);
        txtNovaDataNascimentoPaciente.addKeyListener(this);
        txtNovoCpfPaciente.addKeyListener(this);
        txtNovoCpfPaciente.addKeyListener(this);
    }

    public void setPnlResultado(JPanel pnlBusca, JPanel pnlResultado) {
        JPanel pnlDadosResultado = new JPanel(new BorderLayout(0, 5));
        pnlDadosResultado.setBackground(Color.WHITE);
        JLabel lblResultado = new JLabel("Dados do (a) Paciente");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pnlDadosResultadoCentral = new JPanel(new GridLayout(4, 4, 1, 2));
        pnlDadosResultadoCentral.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlDadosResultadoCentral.setBackground(Color.WHITE);

        JLabel lblNovoEmail = new JLabel("Email");
        lblNovoEmail.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovoEmail.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovaSenha = new JLabel("Senha");
        lblNovaSenha.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovaSenha.setVerticalAlignment(JLabel.BOTTOM);

        JLabel lblNovoNomePaciente = new JLabel("Nome do (a) Paciente");
        lblNovoNomePaciente.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovoNomePaciente.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovaDataNascimentoPaciente = new JLabel("Data de Nascimento");
        lblNovaDataNascimentoPaciente.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovaDataNascimentoPaciente.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovoCpfPaciente = new JLabel("CPF");
        lblNovoCpfPaciente.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovoCpfPaciente.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovoSexoPaciente = new JLabel("Sexo");
        lblNovoSexoPaciente.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovoSexoPaciente.setVerticalAlignment(JLabel.BOTTOM);

        JLabel lblPonto = new JLabel("."); // Apenas para ocupar posição (1, 3) do grid
        lblPonto.setForeground(Color.WHITE);
        JLabel lblPonto1 = new JLabel(".");
        lblPonto1.setForeground(Color.WHITE);

        JLabel lblPonto2 = new JLabel("."); // Apenas para ocupar posição (1, 4) do grid
        lblPonto2.setForeground(Color.WHITE);
        JLabel lblPonto3 = new JLabel(".");
        lblPonto3.setForeground(Color.WHITE);

        JTextField txtNovoEmail = new JTextField(20);
        txtNovoEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNovoEmail.setEnabled(false); // Habilitado apenas após busca
        this.txtNovoEmail = txtNovoEmail;

        JTextField txtNovaSenha = new JTextField(20);
        txtNovaSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNovaSenha.setEnabled(false); // Habilitado apenas após busca
        this.txtNovaSenha = txtNovaSenha;

        JTextField txtNovoNomePaciente = new JTextField(20);
        txtNovoNomePaciente.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNovoNomePaciente.setEnabled(false); // Habilitado apenas após busca
        this.txtNovoNomePaciente = txtNovoNomePaciente;

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            JFormattedTextField txtNovaDataNascimentoPaciente = new JFormattedTextField(mascaraData);
            txtNovaDataNascimentoPaciente.setFont(new Font("Arial", Font.PLAIN, 14));
            txtNovaDataNascimentoPaciente.setEnabled(false); // Habilitado apenas após busca
            this.txtNovaDataNascimentoPaciente = txtNovaDataNascimentoPaciente;
        } catch (Exception e) {
            System.out.println("Falha na formatação.");
        }

        try {
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            JFormattedTextField txtNovoCpfPaciente = new JFormattedTextField(mascaraCpf);
            txtNovoCpfPaciente.setFont(new Font("Arial", Font.PLAIN, 14));
            txtNovoCpfPaciente.setEnabled(false); // Habilitado apenas após busca
            this.txtNovoCpfPaciente = txtNovoCpfPaciente;
        } catch (Exception e) {
            System.out.println("Falha na formatação.");
        }
        String opcoesSexo[] = { "MASCULINO", "FEMININO" };
        JComboBox<String> txtNovoSexoPaciente = new JComboBox<>(opcoesSexo);
        txtNovoSexoPaciente.setBackground(Color.WHITE);
        txtNovoSexoPaciente.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNovoSexoPaciente.setEnabled(false); // Habilitado apenas após busca
        this.txtNovoSexoPaciente = txtNovoSexoPaciente;

        pnlDadosResultadoCentral.add(lblNovoEmail);
        pnlDadosResultadoCentral.add(lblNovaSenha);
        pnlDadosResultadoCentral.add(lblPonto);
        pnlDadosResultadoCentral.add(lblPonto1);
        pnlDadosResultadoCentral.add(txtNovoEmail);
        pnlDadosResultadoCentral.add(txtNovaSenha);
        pnlDadosResultadoCentral.add(lblPonto2);
        pnlDadosResultadoCentral.add(lblPonto3);

        pnlDadosResultadoCentral.add(lblNovoNomePaciente);
        pnlDadosResultadoCentral.add(lblNovaDataNascimentoPaciente);
        pnlDadosResultadoCentral.add(lblNovoCpfPaciente);
        pnlDadosResultadoCentral.add(lblNovoSexoPaciente);
        pnlDadosResultadoCentral.add(txtNovoNomePaciente);
        pnlDadosResultadoCentral.add(txtNovaDataNascimentoPaciente);
        pnlDadosResultadoCentral.add(txtNovoCpfPaciente);
        pnlDadosResultadoCentral.add(txtNovoSexoPaciente);

        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBotoes.setBackground(Color.WHITE);

        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.setBackground(new Color(52, 102, 0));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalvar.setFocusPainted(false); // Botão deixa de ficar 'marcado' após clique
        btnSalvar.setEnabled(false); // Habilitado apenas após consulta
        this.btnSalvar = btnSalvar;

        JButton btnExcluir = new JButton("Excluir paciente");
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

    public void setNovoEmail(String novoEmail) {
        this.txtNovoEmail.setText(novoEmail);
    }

    public void setNovaSenha(String novaSenha) {
        this.txtNovaSenha.setText(novaSenha);
    }

    public void setNovoNomePaciente(String novoNomePaciente) {
        this.txtNovoNomePaciente.setText(novoNomePaciente);
    }

    public void setNovaDataNascimentoPaciente(String novaDataNascimento) {
        this.txtNovaDataNascimentoPaciente.setText(novaDataNascimento);
    }

    public void setNovoCpfPaciente(String novoCpfPaciente) {
        this.txtNovoCpfPaciente.setText(novoCpfPaciente);
    }

    public void setNovoSexoPaciente(String sexo) {
        this.txtNovoSexoPaciente.setSelectedItem(sexo);
    }

    public String getCpfBusca() {
        return txtCpfBusca.getText();
    }

    public String getNovoEmail() {
        return txtNovoEmail.getText().toUpperCase();
    }

    public String getNovaSenha() {
        return txtNovaSenha.getText();
    }

    public String getNovoNomePaciente() {
        return txtNovoNomePaciente.getText().toUpperCase();
    }

    public String getNovaDataNascimentoPaciente() {
        return txtNovaDataNascimentoPaciente.getText();
    }

    public String getNovoCpfPaciente() {
        return txtNovoCpfPaciente.getText();
    }

    public String getNovoSexoPaciente() {
        return txtNovoSexoPaciente.getSelectedItem().toString().toUpperCase();
    }

    public void setEnabledTxt(boolean b) {
        this.txtNovoEmail.setEnabled(b);
        this.txtNovaSenha.setEnabled(b);
        this.txtNovoNomePaciente.setEnabled(b);
        this.txtNovaDataNascimentoPaciente.setEnabled(b);
        this.txtNovoCpfPaciente.setEnabled(b);
        this.txtNovoSexoPaciente.setEnabled(b);
        this.btnSalvar.setEnabled(b);
        this.btnExcluir.setEnabled(b);
        if (b) {
            this.txtNovoEmail.grabFocus(); // Coloca o foco na caixa de texto da data
        } else {
            this.txtCpfBusca.grabFocus(); // Coloca o foco na caixa de texto do cpf
        }
    }

    public void limpar() {
        setNovoEmail("");
        setNovaSenha("");
        setNovoNomePaciente("");
        setNovaDataNascimentoPaciente("");
        setNovoCpfPaciente("");
    }

    // TRATADORES DOS EVENTOS DE BOTÕES E CAIXAS DE TEXTO
    // Tratatamento dos botões
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            controller.buscarPaciente(getCpfBusca());
        } else if (e.getSource() == btnSalvar) {
            controller.modidicarPaciente(getCpfBusca(), getNovoEmail(), getNovaSenha(), getNovoNomePaciente(), getNovaDataNascimentoPaciente(), getNovoCpfPaciente(), getNovoSexoPaciente());
        } else if (e.getSource() == btnExcluir) {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o (a) paciente? Essa ação é irreversível!", "Confirmar exclusão", JOptionPane.OK_CANCEL_OPTION);
            if (resposta == 0) {
                controller.excluirPaciente(getCpfBusca());
            } else {
                txtCpfBusca.grabFocus();
            }
        } else {
            controller.removerObservador(this); // remove a janela dos observadore do model
            dispose(); // Fecha apenas esta tela
        }
    }

    // Tratamento das caixas de texto
    public void keyPressed(KeyEvent e) { // Invocado quando uma tecla é pressionada
    }

    public void keyReleased(KeyEvent e) { // Invocado quando uma tecla é liberada
        if (this.getNovoEmail().equals("") || this.getNovaSenha().equals("") || this.getNovoNomePaciente().equals("")
                || this.getNovaDataNascimentoPaciente().contains("_")
                || this.getNovoCpfPaciente().contains("_")) {
            btnSalvar.setEnabled(false);
        } else {
            btnSalvar.setEnabled(true);
        }

        if (e.getSource() == txtCpfBusca) {
            this.setEnabledTxt(false);
            limpar();
        }
    }

    public void keyTyped(KeyEvent e) { // Invocado quando uma chave foi digitada
    }

    // Implementação dos métodos da interface Observador
    // POLIMORFISMO DE SOBRECARGA AQUI
    public void update(String resposta) {
        if (resposta.contains("sucesso")) {
            JOptionPane.showMessageDialog(null, resposta, "SUCESSO!", 1);
        } else {
            JOptionPane.showMessageDialog(null, resposta, "ERRO!", 0);
        }

        limpar();
        setEnabledTxt(false);
        txtCpfBusca.grabFocus();
    }

    public void update(String email, String senha) {
    }

    public void update(Paciente p, String senha) {
        if (!p.getNome().equals("")) {
            // Atribui os dados de p às caixas de texto
            setNovoEmail(p.getEmail());
            setNovaSenha(senha);
            setNovoNomePaciente(p.getNome());
            setNovoCpfPaciente(p.getCpf());
            setNovaDataNascimentoPaciente(p.getDataNascimento());
            setNovoSexoPaciente(p.getSexo());

            btnExcluir.setEnabled(true);
            setEnabledTxt(true);
        } else {
            JOptionPane.showMessageDialog(null, "Paciente não encontrado (a). Tente novamente.", "ERRO!", 0);
            limpar(); // limpa as caixas
            btnExcluir.setEnabled(false);
            setEnabledTxt(false);
            txtCpfBusca.grabFocus();
        }
    }

    public void update(Consulta c) {}

    public void update(Medico c) {}

    public void update(String email, String senha, String perfil) {}

    public void update(HashSet<Consulta>  DadosConsulta) {}
	
    // Tratadores da janela
    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) { // Chamado quando clica no X da janela
        controller.removerObservador(this); // remove a janela dos observadore do model
        this.dispose(); // Fecha apenas esta tela
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
