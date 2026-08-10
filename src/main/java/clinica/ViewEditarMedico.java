/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.HashSet;
import javax.swing.text.MaskFormatter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class ViewEditarMedico extends JFrame implements ActionListener, KeyListener, WindowListener, Observador {
    JButton btnBuscar;  // Para ficar 'visível' entre os dois painéis (busca e resultado)
    JFormattedTextField txtNovoCrm, txtCrmBusca;
    JTextField txtNovoNomeMedico;
    JComboBox txtNovaEspecialidade;
    JButton btnSalvar, btnExcluir, btnCancelar;
    CRUDController controller;

    public ViewEditarMedico(CRUDController controller) {
        super("Editar Médico");
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
        
        setTratadores(); // seta tratadores

        setContentPane(pnlPrincipal);
        pack(); // Ajusta tamanho da tela aos elementos
        setLocationRelativeTo(null); // Posiciona JFrame no centro da tela
        setVisible(true);
    }

    private void setPnlBusca(JPanel pnlPrincipal, JPanel pnlBusca) {      
        JLabel lblBusca = new JLabel("Buscar Médico");
        lblBusca.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pnlContainerBusca = new JPanel(new BorderLayout(0, 3));
        pnlContainerBusca.setBackground(Color.WHITE);

        JPanel pnlDadosBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlDadosBusca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlDadosBusca.setBackground(Color.WHITE);

        JLabel lblDadosBusca = new JLabel("CRM");
        lblDadosBusca.setFont(new Font("Arial", Font.BOLD, 14));

        try {
            MaskFormatter mascaraCrm = new MaskFormatter("########-#");
            mascaraCrm.setPlaceholderCharacter('_');
            JFormattedTextField txtCrmBusca = new JFormattedTextField(mascaraCrm);
            txtCrmBusca.setFont(new Font("Arial", Font.PLAIN, 14));
            txtCrmBusca.setPreferredSize(new Dimension(110, 25));
            this.txtCrmBusca = txtCrmBusca;
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
        pnlDadosBusca.add(txtCrmBusca);
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
        JLabel lblResultado = new JLabel("Dados do (a) Médico");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pnlDadosResultadoCentral = new JPanel(new GridLayout(2, 3, 2, 2));
        pnlDadosResultadoCentral.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlDadosResultadoCentral.setBackground(Color.WHITE);

        JLabel lblNovoCrm = new JLabel("CRM");
        lblNovoCrm.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblNovoNomeMedico = new JLabel("Nome");
        lblNovoNomeMedico.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblNovaEspecialidade = new JLabel("Especialidade");
        lblNovaEspecialidade.setFont(new Font("Arial", Font.BOLD, 14));

        try {
            MaskFormatter mascaraCrm = new MaskFormatter("########-#");
            mascaraCrm.setPlaceholderCharacter('_');
            JFormattedTextField txtNovoCrm = new JFormattedTextField(mascaraCrm);
            txtNovoCrm.setFont(new Font("Arial", Font.PLAIN, 16));
            txtNovoCrm.setPreferredSize(new Dimension(110, 25));
            txtNovoCrm.setEnabled(false); // Habilitado apenas após busca
            this.txtNovoCrm = txtNovoCrm;
        } catch (Exception e) {
            System.out.println("Falha na formatação.");
        }

        JTextField txtNovoNomeMedico = new JTextField(25);
        txtNovoNomeMedico.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNovoNomeMedico.setEnabled(false); // Habilitado apenas após busca
        this.txtNovoNomeMedico = txtNovoNomeMedico;

        String opcoesEspcialidades[] = {"CARDIOLOGISTA", "CLÍNICO GERAL", "DERMATOLOGISTA", "GERIATRA", "PEDIATRA", "PSICÓLOGO"};
        JComboBox<String> txtNovaEspecialidade = new JComboBox<>(opcoesEspcialidades);
        txtNovaEspecialidade.setBackground(Color.WHITE);
        txtNovaEspecialidade.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNovaEspecialidade.setEnabled(false); // Habilitado apenas após busca
        this.txtNovaEspecialidade = txtNovaEspecialidade;

        pnlDadosResultadoCentral.add(lblNovoCrm);
        pnlDadosResultadoCentral.add(lblNovoNomeMedico);
        pnlDadosResultadoCentral.add(lblNovaEspecialidade);
        pnlDadosResultadoCentral.add(txtNovoCrm);
        pnlDadosResultadoCentral.add(txtNovoNomeMedico);
        pnlDadosResultadoCentral.add(txtNovaEspecialidade);

        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBotoes.setBackground(Color.WHITE);

        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.setBackground(new Color(52, 102, 0));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalvar.setFocusPainted(false); // Botão deixa de ficar 'marcado' após clique
        btnSalvar.setEnabled(false); // Habilitado apenas após consulta
        this.btnSalvar = btnSalvar;

        JButton btnExcluir = new JButton("Excluir Médico");
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
        txtCrmBusca.addKeyListener(this);
        txtNovoCrm.addKeyListener(this);
        txtNovoNomeMedico.addKeyListener(this);
        
        btnBuscar.addActionListener(this);
        btnSalvar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnCancelar.addActionListener(this);
    }
    
    public String getCrmBusca() {
        return (new String(txtCrmBusca.getText().toUpperCase()));
    }

    public String getNovoCrm() {
        return (new String(txtNovoCrm.getText()));
    }

    public String getNovoNomeMedico() {
        return (new String(txtNovoNomeMedico.getText()));
    }

    public String getNovaEspecialidade() {
        return txtNovaEspecialidade.getSelectedItem().toString().toUpperCase();
    }

    public void setNovoCrm(String novoCrm) {
        this.txtNovoCrm.setText(novoCrm);
    }

    public void setNovoNomeMedico(String novoNomeMedico) {
        this.txtNovoNomeMedico.setText(novoNomeMedico.toUpperCase());
    }

    public void setNovaEspecialidade(String novaEspecialidade) {
        this.txtNovaEspecialidade.setSelectedItem(novaEspecialidade.toUpperCase());
    }

    public void setEnabledTxt(boolean e) {
        this.txtNovoCrm.setEnabled(e);
        this.txtNovoNomeMedico.setEnabled(e);
        this.txtNovaEspecialidade.setEnabled(e);
        this.txtCrmBusca.grabFocus(); // Coloca o foco na caixa de texto de novo email

        this.btnSalvar.setEnabled(e);
        this.btnExcluir.setEnabled(e);
    }

    public void limpar() {
        setNovoCrm("");
        setNovoNomeMedico("");
        setNovaEspecialidade("CARDIOLOGISTA");
    }

    // TRATADORES DOS BOTÕES E CAIXAS DE TEXTO
    // Tratador dos botões
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            controller.buscarMedico(getCrmBusca());
        } else if (e.getSource() == btnSalvar) {
            controller.modificarMedico(getCrmBusca(), getNovoCrm(), getNovoNomeMedico(), getNovaEspecialidade());
        } else if (e.getSource() == btnExcluir) {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o (a) atendente? Essa ação é irreversível!", "Confirmar exclusão", JOptionPane.OK_CANCEL_OPTION);
            if (resposta == 0) {
                controller.excluirMedico(getCrmBusca());
            } else {
                txtCrmBusca.grabFocus();
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
        if (this.getNovoCrm().contains("_") || this.getNovoNomeMedico().equals("")) {
            btnSalvar.setEnabled(false);
        } else {
            btnSalvar.setEnabled(true);
        }

        if (e.getSource() == txtCrmBusca) {
            setEnabledTxt(false);
            limpar();
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
        txtCrmBusca.grabFocus();
    }

    public void update(String crm, String senha) {}

    public void update(Paciente p, String senha) {}

    public void update(Consulta c) {}

    public void update(Medico m) {
        if (!m.getCRM().equals("________-_")) {
            setNovoCrm(m.getCRM());
            setNovoNomeMedico(m.getMedico());
            setNovaEspecialidade(m.getEspecialidade());
            btnExcluir.setEnabled(true);
            setEnabledTxt(true);
        } else {
            JOptionPane.showMessageDialog(null, "Médico (a) não encontrado (a). Tente novamente.", "ERRO!", 0);
            limpar(); // limpa as caixas
            btnExcluir.setEnabled(false);
            setEnabledTxt(false);
            txtCrmBusca.grabFocus();
        }
    }

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