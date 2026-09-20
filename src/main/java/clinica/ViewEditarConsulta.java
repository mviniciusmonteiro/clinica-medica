/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class ViewEditarConsulta extends JFrame implements ActionListener, KeyListener, WindowListener, Observador {                                                                                           
    JTextField txtIdBusca; // Para ficar 'visível' entre os dois painéis (busca e resultado)
    JButton btnBuscar;
    JFormattedTextField txtNovaData, txtNovoCpfPaciente, txtNovaDataNascimentoPaciente, txtNovoCrm;
    JTextField txtNovoMedico, txtNovaEspecialidade, txtNovoNomePaciente, txtNovoSexoPaciente;
    JComboBox<String> txtNovaHora;
    JButton btnSalvar, btnExcluir, btnCancelar, btnBuscarMedico, btnBuscarPaciente;
    CRUDController controller;

    public ViewEditarConsulta(CRUDController controller) {
        super("Editar Consulta");

        addWindowListener(this);

        this.controller = controller;

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
        JLabel lblBusca = new JLabel("Buscar Consulta");
        lblBusca.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pnlContainerBusca = new JPanel(new BorderLayout(0, 3));
        pnlContainerBusca.setBackground(Color.WHITE);

        JPanel pnlDadosBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlDadosBusca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlDadosBusca.setBackground(Color.WHITE);

        JLabel lblDadosBusca = new JLabel("Id da Consulta");
        lblDadosBusca.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField txtIdBusca = new JTextField(15);
        txtIdBusca.setFont(new Font("Arial", Font.PLAIN, 14));
        this.txtIdBusca = txtIdBusca;
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(55, 50, 170));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.setFocusPainted(false);
        this.btnBuscar = btnBuscar;

        pnlDadosBusca.add(lblDadosBusca);
        pnlDadosBusca.add(txtIdBusca);
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

    private void setTratadores() {
        btnBuscar.addActionListener(this);
        btnBuscarMedico.addActionListener(this);
        btnBuscarPaciente.addActionListener(this);
        btnSalvar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnCancelar.addActionListener(this);

        txtIdBusca.addKeyListener(this);
        txtNovaData.addKeyListener(this);
        txtNovaHora.addKeyListener(this);
        txtNovoMedico.addKeyListener(this);
        txtNovoCrm.addKeyListener(this);
        txtNovaEspecialidade.addKeyListener(this);
        txtNovoNomePaciente.addKeyListener(this);
        txtNovaDataNascimentoPaciente.addKeyListener(this);
        txtNovoCpfPaciente.addKeyListener(this);
        txtNovoCpfPaciente.addKeyListener(this);
    }

    private void setPnlResultado(JPanel pnlBusca, JPanel pnlResultado) {
        JPanel pnlDadosResultado = new JPanel(new BorderLayout(0, 5));
        pnlDadosResultado.setBackground(Color.WHITE);
        JLabel lblResultado = new JLabel("Dados da Consulta");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pnlDadosResultadoCentral = new JPanel(new GridLayout(6, 5, 1, 2));
        pnlDadosResultadoCentral.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlDadosResultadoCentral.setBackground(Color.WHITE);

        JLabel lblNovaData = new JLabel("Data");
        lblNovaData.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovaData.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovaHora = new JLabel("Hora");
        lblNovaHora.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovaHora.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovoMedico = new JLabel("Nome do Médico");
        lblNovoMedico.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovoMedico.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovoCrm = new JLabel("CRM");
        lblNovoCrm.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovoCrm.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovaEspecialidade = new JLabel("Especialidade");
        lblNovaEspecialidade.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovaEspecialidade.setVerticalAlignment(JLabel.BOTTOM);

        JLabel lblNovoNomePaciente = new JLabel("Nome do (a) Paciente");
        lblNovoNomePaciente.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovoNomePaciente.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovaIdadePaciente = new JLabel("Data de Nascimento");
        lblNovaIdadePaciente.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovaIdadePaciente.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovoCpfPaciente = new JLabel("CPF");
        lblNovoCpfPaciente.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovoCpfPaciente.setVerticalAlignment(JLabel.BOTTOM);
        JLabel lblNovoSexoPaciente = new JLabel("Sexo");
        lblNovoSexoPaciente.setFont(new Font("Arial", Font.BOLD, 14));
        lblNovoSexoPaciente.setVerticalAlignment(JLabel.BOTTOM);

        // Pontos apenas para ocupar as posições 'vazias' do grid
        JLabel lblPonto = new JLabel(".");
        lblPonto.setForeground(Color.WHITE);
        JLabel lblPonto1 = new JLabel("."); // Apenas para ocupar posição (1, 4) do grid
        lblPonto1.setForeground(Color.WHITE);
        JLabel lblPonto2 = new JLabel(".");
        lblPonto2.setForeground(Color.WHITE);
        JLabel lblPonto3 = new JLabel(".");
        lblPonto3.setForeground(Color.WHITE);
        JLabel lblPonto4 = new JLabel(".");
        lblPonto4.setForeground(Color.WHITE);
        JLabel lblPonto5 = new JLabel(".");
        lblPonto5.setForeground(Color.WHITE);
        JLabel lblPonto6 = new JLabel(".");
        lblPonto6.setForeground(Color.WHITE);
        JLabel lblPonto7 = new JLabel(".");
        lblPonto7.setForeground(Color.WHITE);
        JLabel lblPonto8 = new JLabel(".");
        lblPonto8.setForeground(Color.WHITE);
        JLabel lblPonto9 = new JLabel(".");
        lblPonto9.setForeground(Color.WHITE);

        JPanel pnlBuscarMedico = new JPanel(new BorderLayout());
        pnlBuscarMedico.setBackground(Color.WHITE);

        JPanel pnlBuscarPaciente = new JPanel(new BorderLayout());
        pnlBuscarPaciente.setBackground(Color.WHITE);

        JButton btnBuscarMedico = new JButton("Buscar Médico");
        btnBuscarMedico.setBackground(new Color(55, 50, 170));
        btnBuscarMedico.setForeground(Color.WHITE);
        btnBuscarMedico.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscarMedico.setFocusPainted(false);
        this.btnBuscarMedico = btnBuscarMedico;
        pnlBuscarMedico.add(btnBuscarMedico, BorderLayout.WEST);

        JButton btnBuscarPaciente = new JButton("Buscar Paciente");
        btnBuscarPaciente.setBackground(new Color(55, 50, 170));
        btnBuscarPaciente.setForeground(Color.WHITE);
        btnBuscarPaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscarPaciente.setFocusPainted(false);
        this.btnBuscarPaciente = btnBuscarPaciente;
        pnlBuscarPaciente.add(btnBuscarPaciente, BorderLayout.WEST);

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            JFormattedTextField txtNovaData = new JFormattedTextField(mascaraData);
            txtNovaData.setFont(new Font("Arial", Font.PLAIN, 14));
            txtNovaData.setEnabled(false); // Habilitado apenas após busca
            this.txtNovaData = txtNovaData;
        } catch (Exception e) {
            System.out.println("Falha na formatação.");
        }
        String opcoesHora[] = { "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "14:00", "14:30",
                "15:00", "15:30", "16:00", "16:30" };
        JComboBox<String> txtNovaHora = new JComboBox<>(opcoesHora);
        txtNovaHora.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNovaHora.setEnabled(false); // Habilitado apenas após busca
        txtNovaHora.setMaximumRowCount(14);
        txtNovaHora.setBackground(Color.WHITE);
        this.txtNovaHora = txtNovaHora;

        JTextField txtNovoMedico = new JTextField();
        txtNovoMedico.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNovoMedico.setEditable(false);
        this.txtNovoMedico = txtNovoMedico;

        try {
            MaskFormatter mascaraCrm = new MaskFormatter("########-#");
            mascaraCrm.setPlaceholderCharacter('_');
            JFormattedTextField txtNovoCrm = new JFormattedTextField(mascaraCrm);
            txtNovoCrm.setFont(new Font("Arial", Font.PLAIN, 14));
            txtNovoCrm.setPreferredSize(new Dimension(110, 25));
            txtNovoCrm.setEnabled(false); // Habilitado apenas após busca
            this.txtNovoCrm = txtNovoCrm;
        } catch (Exception e) {
            System.out.println("Falha na formatação.");
        }

        JTextField txtNovaEspecialidade = new JTextField();
        txtNovaEspecialidade.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNovaEspecialidade.setEditable(false);
        this.txtNovaEspecialidade = txtNovaEspecialidade;

        JTextField txtNovoNomePaciente = new JTextField(20);
        txtNovoNomePaciente.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNovoNomePaciente.setEditable(false);
        this.txtNovoNomePaciente = txtNovoNomePaciente;

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            JFormattedTextField txtNovaDataNascimentoPaciente = new JFormattedTextField(mascaraData);
            txtNovaDataNascimentoPaciente.setFont(new Font("Arial", Font.PLAIN, 14));
            txtNovaDataNascimentoPaciente.setEditable(false);
            this.txtNovaDataNascimentoPaciente = txtNovaDataNascimentoPaciente;

            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            JFormattedTextField txtNovoCpfPaciente = new JFormattedTextField(mascaraCpf);
            txtNovoCpfPaciente.setFont(new Font("Arial", Font.PLAIN, 14));
            txtNovoCpfPaciente.setEnabled(false); // Habilitado apenas após busca
            this.txtNovoCpfPaciente = txtNovoCpfPaciente;
        } catch (Exception e) {
            System.out.println("Falha na formatação.");
        }
        JTextField txtNovoSexoPaciente = new JTextField();
        txtNovoSexoPaciente.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNovoSexoPaciente.setEditable(false);
        this.txtNovoSexoPaciente = txtNovoSexoPaciente;

        pnlDadosResultadoCentral.add(lblNovaData);
        pnlDadosResultadoCentral.add(lblNovaHora);
        pnlDadosResultadoCentral.add(lblPonto);
        pnlDadosResultadoCentral.add(lblPonto1);
        pnlDadosResultadoCentral.add(lblPonto2);
        pnlDadosResultadoCentral.add(txtNovaData);
        pnlDadosResultadoCentral.add(txtNovaHora);
        pnlDadosResultadoCentral.add(lblPonto3);
        pnlDadosResultadoCentral.add(lblPonto4);
        pnlDadosResultadoCentral.add(lblPonto5);
        pnlDadosResultadoCentral.add(lblNovoCrm);
        pnlDadosResultadoCentral.add(lblPonto6);
        pnlDadosResultadoCentral.add(lblNovoMedico);
        pnlDadosResultadoCentral.add(lblNovaEspecialidade);
        pnlDadosResultadoCentral.add(lblPonto7);
        pnlDadosResultadoCentral.add(txtNovoCrm);
        pnlDadosResultadoCentral.add(pnlBuscarMedico);
        pnlDadosResultadoCentral.add(txtNovoMedico);
        pnlDadosResultadoCentral.add(txtNovaEspecialidade);
        pnlDadosResultadoCentral.add(lblPonto8);

        pnlDadosResultadoCentral.add(lblNovoCpfPaciente);
        pnlDadosResultadoCentral.add(lblPonto9);
        pnlDadosResultadoCentral.add(lblNovoNomePaciente);
        pnlDadosResultadoCentral.add(lblNovaIdadePaciente);
        pnlDadosResultadoCentral.add(lblNovoSexoPaciente);
        pnlDadosResultadoCentral.add(txtNovoCpfPaciente);
        pnlDadosResultadoCentral.add(pnlBuscarPaciente);
        pnlDadosResultadoCentral.add(txtNovoNomePaciente);
        pnlDadosResultadoCentral.add(txtNovaDataNascimentoPaciente);
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

        JButton btnExcluir = new JButton("Excluir consulta");
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

    public void limpar() {
        setNovaData("");
        setNovaHora("8:00");
        setNovoCrm("");
        setNovoMedico("");
        setNovaEspecialidade("");
        setNovoNomePaciente("");
        setNovaDataNascimentoPaciente("");
        setNovoCpfPaciente("");
        setNovoSexoPaciente("");
    }

    public void setNovaData(String novaData) {
        this.txtNovaData.setText(novaData);
    }

    public void setNovaHora(String hora) {
        this.txtNovaHora.setSelectedItem(hora);
    }

    public void setNovoMedico(String novoMedico) {
        this.txtNovoMedico.setText(novoMedico.toUpperCase());
    }

    public void setNovoCrm(String novoCrm) {
        this.txtNovoCrm.setText(novoCrm);
    }

    public void setNovaEspecialidade(String novaEspecialidade) {
        this.txtNovaEspecialidade.setText(novaEspecialidade.toUpperCase());
    }

    public void setNovoNomePaciente(String novoNomePaciente) {
        this.txtNovoNomePaciente.setText(novoNomePaciente.toUpperCase());
    }

    public void setNovaDataNascimentoPaciente(String novaDataNascimentoPaciente) {
        this.txtNovaDataNascimentoPaciente.setText(novaDataNascimentoPaciente);
    }

    public void setNovoCpfPaciente(String novoCpfPaciente) {
        this.txtNovoCpfPaciente.setText(novoCpfPaciente);
    }

    public void setNovoSexoPaciente(String sexo) {
        this.txtNovoSexoPaciente.setText(sexo.toUpperCase());
    }

    public String getIdBusca() {
        return txtIdBusca.getText();
    }

    public String getNovaData() {
        return txtNovaData.getText();
    }

    public String getNovaHora() {
        return txtNovaHora.getSelectedItem().toString();
    }

    public String getNovoMedico() {
        return txtNovoMedico.getText().toUpperCase();
    }

    public String getNovoCrm() {
        return txtNovoCrm.getText();
    }

    public String getNovaEspecialidade() {
        return txtNovaEspecialidade.getText().toUpperCase();
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
        return txtNovoSexoPaciente.getText().toUpperCase();
    }

    public void setEnabledTxt(boolean b) {
        this.txtNovaData.setEnabled(b);
        this.txtNovaHora.setEnabled(b);
        this.txtNovoCrm.setEnabled(b);
        this.txtNovoCpfPaciente.setEnabled(b);
        this.btnSalvar.setEnabled(b);
        this.btnExcluir.setEnabled(b);
        if (b) {
            this.txtNovaData.grabFocus(); // Coloca o foco na caixa de texto da data
        } else {
            this.txtIdBusca.grabFocus(); // Coloca o foco na caixa de texto do id
        }
    }

    // TRATADORES DOS EVENTOS DE BOTÕES E CAIXAS DE TEXTO
    // Tratatamento dos botões
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            controller.buscarConsulta(getIdBusca());
        } else if (e.getSource() == btnBuscarPaciente) {
            controller.buscarPaciente(getNovoCpfPaciente());
        } else if (e.getSource() == btnBuscarMedico) {
            controller.buscarMedico(getNovoCrm());
        } else if (e.getSource() == btnSalvar) {
            controller.modificarConsulta(getIdBusca(), getNovaData(), getNovaHora(), new Paciente(getNovoNomePaciente(), getNovaDataNascimentoPaciente(), getNovoCpfPaciente(), getNovoSexoPaciente(), ""), new Medico(getNovoMedico(), getNovoCrm(), getNovaEspecialidade()));
        } else if (e.getSource() == btnExcluir) {
             int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a consulta? Essa ação é irreversível!", "Confirmar exclusão", JOptionPane.OK_CANCEL_OPTION);
            if (resposta == 0) {
                controller.excluirConsulta(getIdBusca());
            } else {
                txtIdBusca.grabFocus();
            }
        } else {
            controller.removerObservador(this);
            this.dispose(); // Fecha apenas esta janela
        }
    }

    // Tratamento das caixas de texto
    public void keyPressed(KeyEvent e) { // Invocado quando uma tecla é pressionada
    }

    public void keyReleased(KeyEvent e) { // Invocado quando uma tecla é liberada
        if (this.getNovaData().contains("_") || this.getNovoCrm().contains("_") || this.getNovoMedico().equals("")
                || this.getNovaEspecialidade().equals("")
                || this.getNovoNomePaciente().equals("") || this.getNovaDataNascimentoPaciente().contains("_")
                || this.getNovoCpfPaciente().contains("_")) {
            btnSalvar.setEnabled(false);
        } else {
            btnSalvar.setEnabled(true);
        }

        if (e.getSource() == txtIdBusca) {
            limpar();
            this.setEnabledTxt(false);
        }
        if (e.getSource() == txtNovoCpfPaciente) {
            setNovoNomePaciente("");
            setNovaDataNascimentoPaciente("");
            setNovoSexoPaciente("");
            btnSalvar.setEnabled(false);
        }
        if (e.getSource() == txtNovoCrm) {
            setNovoMedico("");
            setNovaEspecialidade("");
            btnSalvar.setEnabled(false);
        }
    }

    public void keyTyped(KeyEvent e) { // Invocado quando uma chave foi digitada
    }

    // Implementação dos métodos da interface Observador
    public void update(String resposta) {
        if (resposta.contains("sucesso")) {
            JOptionPane.showMessageDialog(null, resposta, "SUCESSO!", 1);
        } else {
            JOptionPane.showMessageDialog(null, resposta, "ERRO!", 0);
        }

        limpar();
        setEnabledTxt(false);
        txtIdBusca.grabFocus();
    }

    public void update(Paciente p, String senha) {
        if (!p.getNome().equals("")) {
            setNovoNomePaciente(p.getNome());
            setNovaDataNascimentoPaciente(p.getDataNascimento());
            setNovoSexoPaciente(p.getSexo());

            if (!getNovoMedico().equals("")) {
                btnSalvar.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Paciente não encontrado (a). Tente novamente.", "ERRO!", 0);
            setNovoNomePaciente("");
            setNovaDataNascimentoPaciente("");
            setNovoSexoPaciente("");
            btnSalvar.setEnabled(false);
            txtNovoCpfPaciente.grabFocus();
        }
    }

    public void update(String email, String senha) {
    }

    public void update(Consulta c) {
        if (!c.getId().equals("")) {
            setNovaData(c.getData());
            setNovaHora(c.getHora());

            setNovoCpfPaciente(c.paciente.getCpf());
            setNovoNomePaciente(c.paciente.getNome().toUpperCase());
            setNovaDataNascimentoPaciente(c.paciente.getDataNascimento());
            setNovoSexoPaciente(c.paciente.getSexo().toUpperCase());

            setNovoMedico(c.medico.getMedico().toUpperCase());
            setNovoCrm(c.medico.getCRM());
            setNovaEspecialidade(c.medico.getEspecialidade().toUpperCase());

            setEnabledTxt(true);
        } else {
            JOptionPane.showMessageDialog(null, "Consulta não encontrada. Tente novamente.", "ERRO!", 0);
            limpar();
            setEnabledTxt(false);
        }
    }

    public void update(Medico m) {
        if (!m.getCRM().contains("_")) {
            setNovoMedico(m.getMedico());
            setNovaEspecialidade(m.getEspecialidade());

            if (!getNovoNomePaciente().equals("")) {
                btnSalvar.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Médico (a) não encontrado (a). Tente novamente.", "ERRO!", 0);
            setNovoMedico("");
            setNovaEspecialidade("");
            btnSalvar.setEnabled(false);
            txtNovoCrm.grabFocus();
        }
    }

    public void update(String email, String senha, String perfil) {}

    public void update(HashSet<Consulta>  DadosConsulta) {}
	
    // Tratadores da janela
    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) { // Chamado quando clica no X da janela
        controller.removerObservador(this); // remove a janela dos observadores do model
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