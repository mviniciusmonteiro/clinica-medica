/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;//Inc()
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;


public class ViewTelaInicial extends JFrame implements Observador, WindowListener, ActionListener{
    JButton btnAC;
    JButton btnAA;
    JButton btnAP;
    JButton btnAM;
    JButton btnVC;
    JButton btnEP;
    JButton btnEA;
    JButton btnEM;
    JButton btnEC;
    JButton sair;
    CRUDController controller;
    JPanel pnlCentro, pnlPrincipal, pnlbotoes;
    JTable table;
    String perfil, nomePaciente;
// 
    public ViewTelaInicial(CRUDController controller, String perfil, String nomePaciente) {
        super("Tela Inicial");

        addWindowListener(this);

        this.perfil = perfil;
        this.nomePaciente = nomePaciente;

        this.controller = controller;

        // Chamada dos paneis principais return
        JPanel pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.setBackground(Color.WHITE);
        this.pnlPrincipal = pnlPrincipal;

        JPanel pnlbotoes = new JPanel(new BorderLayout()); 
        pnlbotoes.setBackground(Color.WHITE);
        this.pnlbotoes = pnlbotoes;

        JPanel pnlCentro = new JPanel(new BorderLayout());
        pnlCentro.setBackground(Color.WHITE);
        this.pnlCentro = pnlCentro;

        setPnlBotoes(pnlPrincipal,pnlbotoes); // seta os componentes do painel dos botoes e o insere no painel principal
        criarJT(pnlCentro);

        settratadores(); // Chamada da acao dos botoes quando clicados
        
        setPerfil(perfil); //De acordo com o usuario mostrara determinados botoes na tela

        setContentPane(pnlPrincipal);
        pack(); // Ajusta tamanho da tela aos elementos
        setLocationRelativeTo(null); // Posiciona JFrame no centro da tela
        setVisible(true);
    
    }

    // Recebe da tela de login o tipo de usuario para mostrar a tela
    public void setPerfil(final String perfil ){
        if( perfil.equals("PACIENTE")){
            this.btnAC.setVisible(true);
            this.btnVC.setVisible(true);
            this.sair.setVisible(true);
        }
        else if( perfil.equals("ATENDENTE")){
            this.btnAC.setVisible(true);
            this.btnEC.setVisible(true);
            this.btnAP.setVisible(true);
            this.btnVC.setVisible(true);
            this.btnEP.setVisible(true);
            this.sair.setVisible(true);
        }
        else{
            this.btnAC.setVisible(true);
            this.btnEC.setVisible(true);
            this.btnAA.setVisible(true);
            this.btnAP.setVisible(true);
            this.btnAM.setVisible(true);
            this.btnVC.setVisible(true);
            this.btnEP.setVisible(true);
            this.btnEA.setVisible(true);
            this.btnEM.setVisible(true);
            this.sair.setVisible(true);
        }
    }

    // Funcao que cria todos os botoes da tela inicial e adiciona ao painel principal
    public void setPnlBotoes(final JPanel pnlPrincipal, final JPanel pnlbotoes){
        JButton btnAC = new JButton("Agendar Consulta");
        btnAC.setBackground(new Color(55, 50, 170));
        btnAC.setForeground(Color.WHITE);
        btnAC.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAC.setFocusPainted(false);
        btnAC.setVisible(false);
        this.btnAC = btnAC;

        JButton btnVC = new JButton("Visualizar Consulta");
        btnVC.setBackground(new Color(55, 50, 170));
        btnVC.setForeground(Color.WHITE);
        btnVC.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVC.setFocusPainted(false);
        btnVC.setVisible(false);
        this.btnVC = btnVC;

        JButton btnEP = new JButton("Editar Paciente");
        btnEP.setBackground(new Color(55, 50, 170));
        btnEP.setForeground(Color.WHITE);
        btnEP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEP.setFocusPainted(false);
        btnEP.setVisible(false);
        this.btnEP = btnEP;

        JButton btnEA = new JButton("Editar Atendente");
        btnEA.setBackground(new Color(55, 50, 170));
        btnEA.setForeground(Color.WHITE);
        btnEA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEA.setFocusPainted(false);
        btnEA.setVisible(false);
        this.btnEA = btnEA;

        JButton btnEC = new JButton("Editar Consulta");
        btnEC.setBackground(new Color(55, 50, 170));
        btnEC.setForeground(Color.WHITE);
        btnEC.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEC.setFocusPainted(false);
        btnEC.setVisible(false);
        this.btnEC = btnEC;

        JButton btnEM = new JButton("Editar Médico");
        btnEM.setBackground(new Color(55, 50, 170));
        btnEM.setForeground(Color.WHITE);
        btnEM.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEM.setFocusPainted(false);
        btnEM.setVisible(false);
        this.btnEM = btnEM;

        JButton btnAP = new JButton("Adicionar Paciente");
        btnAP.setBackground(new Color(55, 50, 170));
        btnAP.setForeground(Color.WHITE);
        btnAP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAP.setFocusPainted(false);
        btnAP.setVisible(false);
        this.btnAP = btnAP;

        JButton btnAA = new JButton("Adicionar Atendente");
        btnAA.setBackground(new Color(55, 50, 170));
        btnAA.setForeground(Color.WHITE);
        btnAA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAA.setFocusPainted(false);
        btnAA.setVisible(false);
        this.btnAA = btnAA;

        JButton btnAM = new JButton("Adicionar Médico");
        btnAM.setBackground(new Color(55, 50, 170));
        btnAM.setForeground(Color.WHITE);
        btnAM.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAM.setFocusPainted(false);
        btnAM.setVisible(false);
        this.btnAM = btnAM;

        JButton sair = new JButton("SAIR");
        sair.setBackground(Color.RED);
        sair.setForeground(Color.WHITE);
        sair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sair.setFocusPainted(false);
        this.sair = sair;

        JPanel pnlBotao = new JPanel(new GridLayout(9,1,3,3));
        pnlBotao.setBackground(Color.WHITE);
        pnlBotao.add(btnAC);
        pnlBotao.add(btnVC);
        pnlBotao.add(btnEC);
        pnlBotao.add(btnAP);
        pnlBotao.add(btnEP);
        pnlBotao.add(btnAA);
        pnlBotao.add(btnAM);
        pnlBotao.add(btnEA);
        pnlBotao.add(btnEM);

        JPanel pnlWest = new JPanel(new BorderLayout());
        pnlWest.add(pnlBotao,BorderLayout.CENTER);
        JLabel lblEsq = new JLabel(".");
        lblEsq.setForeground(Color.WHITE);
        JLabel lblDir = new JLabel(".");
        lblDir.setForeground(Color.WHITE);
        pnlWest.add(lblEsq,BorderLayout.WEST);
        pnlWest.add(lblDir,BorderLayout.EAST);

        pnlPrincipal.add(pnlWest,BorderLayout.WEST);
        JPanel pnlCima = new JPanel(new BorderLayout());
        JLabel caixabranca = new JLabel(".");
        caixabranca.setForeground(Color.WHITE);
        pnlCima.add(caixabranca);
        pnlCima.setBackground(Color.WHITE);
        
        JPanel pnlBaixo = new JPanel(new BorderLayout());
        JLabel caixabranca2 = new JLabel(".");
        pnlBaixo.add(sair,BorderLayout.EAST);
        caixabranca2.setForeground(Color.WHITE); 
        pnlBaixo.add(caixabranca2);
        
        pnlPrincipal.add(pnlCentro,BorderLayout.CENTER);
        pnlBaixo.setBackground(Color.WHITE);
        pnlPrincipal.add(pnlCima,BorderLayout.NORTH);
        pnlPrincipal.add(pnlBaixo,BorderLayout.SOUTH);
    
    }

    // Funcao que cria Jtable para receber os dados do hashset com todas as consultas
    public void criarJT(JPanel pnlCentro){

        String colunas [] = {"ID", "Data", "Hora","Paciente","Medico"};

        HashSet<Object> H = new HashSet<Object>();

        Object x [] = {"", "","", "", ""};         
        H.add(x);
        int l = H.size();

        Object ls [][] = new Object[l][5] ; int cont = 0;

        Iterator<Object> it = H.iterator();
        while(it.hasNext()){
            
            Object s [] = (Object [])it.next();
                for( int z = 0; z < 5; z++){
                    ls[cont][z] = s[z];
                }
            cont ++;

        TableModel modelo = new DefaultTableModel(ls, colunas) {
            public Class getColumnClass(final int column) {
                Class returnValue;
                  if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                  } else {
                    returnValue = Object.class;
                  }
                  return returnValue;
                }
            };

            final JTable table = new JTable(modelo);
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
            this.table = table;
            table.setRowSorter(sorter);
            JScrollPane pane = new JScrollPane(table);
            
            pnlCentro.add(pane, BorderLayout.CENTER);

            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel("                     Agendamentos de Consulta");
            label.setFont(new Font("Arial", Font.BOLD, 17));
            panel.add(label, BorderLayout.NORTH);
            pnlCentro.add(panel, BorderLayout.NORTH);
            pnlCentro.setVisible(true);     
        }  
    }

    public void settratadores() {
        sair.addActionListener(this);
        btnAA.addActionListener(this);
        btnAC.addActionListener(this);
        btnAM.addActionListener(this);
        btnAP.addActionListener(this);        
        btnEA.addActionListener(this);
        btnEM.addActionListener(this);
        btnEP.addActionListener(this); 
        btnEC.addActionListener(this);
        btnVC.addActionListener(this);
    }

    // Recebe acao dos botoes quando chamados
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == sair) {
            controller.removerObservador(this);
            dispose();
            
            controller.escreverPacientes();
            controller.escreverUsuarios();
            controller.escreverConsultas();
            controller.escreverMedicos();

            CRUDModel novoModel = new CRUDModel();
            CRUDController novoController = new CRUDController(novoModel);

            ViewLogin telaLogin = new ViewLogin(novoController);

            novoController.adicionarObservador(telaLogin);

        } else if(e.getSource() == btnAA){
            CadAten telaAdicionarAtendente = new CadAten(controller);
            controller.adicionarObservador(telaAdicionarAtendente);
            
        } else if(e.getSource() == btnAC){
            if (perfil.equals("PACIENTE")) {
                 IncluirCons telaAdicionarConsulta = new IncluirCons(controller);
    
                //controller.adicionarObservador(telaAdicionarConsulta);
            } else {
                AteIncluirCons atetelaAdicionarConsulta = new AteIncluirCons(controller);
        
                // controller.adicionarObservador(telaAdicionarConsulta);
            }
        } else if(e.getSource() == btnAM){
            IncluirMed telaAdicionarMedico = new IncluirMed(controller);
            controller.adicionarObservador(telaAdicionarMedico);
        } else if(e.getSource() == btnAP){
             CadPaciente telaAdicionarPaciente = new CadPaciente(controller);

            // controller.adicionarObservador(telaAdicionarPaciente);
        } else if(e.getSource() == btnEA){
            ViewEditarAtendente telaEditarAtendente = new ViewEditarAtendente(controller);
            controller.adicionarObservador(telaEditarAtendente);
        } else if(e.getSource() == btnEM){
            ViewEditarMedico telaEditarMedico = new ViewEditarMedico(controller);
            controller.adicionarObservador(telaEditarMedico);
        } else if(e.getSource() == btnEP){
            ViewEditarPaciente telaEditarPaciente = new ViewEditarPaciente(controller);
            controller.adicionarObservador(telaEditarPaciente);
        } else if(e.getSource() == btnEC){
            ViewEditarConsulta telaEditarConsulta = new ViewEditarConsulta(controller);
            controller.adicionarObservador(telaEditarConsulta);
        } else {
            ViewBuscarConsulta telaBuscarConsulta = new ViewBuscarConsulta(controller, nomePaciente);
            controller.adicionarObservador(telaBuscarConsulta);
        }

    }

    // Chamada dos metodos dos Observadores
    public void update(String resposta) {
    }
    public void update(Paciente p, String senha) {
    }
    public void update(String email, String senha) {
    }
    public void update(Consulta c) {
    }
    public void update(Medico m) {
    }
    public void update(String email, String senha, String perfil) {
    }

    // Funcao que recebe o hashset com todas as consultas para mostrar na tela inicial na Jtable
    public void update(HashSet<Consulta> DadosConsulta){

            String colunas [] = {"ID", "Data", "Hora","Paciente","Medico"};
            int l = DadosConsulta.size();
            String ls [][] = new String[l][5] ; int cont = 0;
 
            Iterator<Consulta> ite = DadosConsulta.iterator();
            while(ite.hasNext()){
                
                Consulta s = ite.next();
                for(int z = 0; z < 5; z++ ){
                    if(z == 0){
                        ls[cont][z] = s.getId();
                    }
                    else if(z == 1){
                        ls[cont][z] = s.getData();
                    }
                    else if(z == 2){
                        ls[cont][z] = s.getHora();
                    }
                    else if(z == 3){
                        final Paciente o = s.getPaciente();
                        ls[cont][z] = o.getNome();
                    }
                    else if(z == 4){
                        final Medico m = s.getMedico();
                        ls[cont][z] = m.getMedico();
                    }
                }
                cont ++;
            }
            final TableModel modelo = new DefaultTableModel(ls, colunas) {
                public Class getColumnClass(final int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
                }
            };
            final JTable table = new JTable(modelo);
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);

            table.setRowSorter(sorter);
            this.table = table;
            JScrollPane pane = new JScrollPane(table);

            this.pnlCentro.removeAll();
            
            this.pnlCentro.add(pane, BorderLayout.CENTER);

            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel("    Agendamentos de Consulta");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(label, BorderLayout.NORTH);
            pnlCentro.add(panel, BorderLayout.NORTH);
            pnlCentro.setVisible(true);
            this.validate();
    }

    // Tratadores de Janela
    public void windowActivated(WindowEvent e) {
        if (perfil.equals("PACIENTE")) {
            controller.buscarConsulta1("__/__/____", nomePaciente,"");
        } else {
            controller.buscarConsulta1("__/__/____","","");
        }
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

