/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

public class ViewBuscarConsulta extends JFrame implements WindowListener, Observador, ActionListener{
    JFormattedTextField ndata;
    JTextField npaciente, nmedico;
    JButton btnBuscar, btnLimpar, btnSair;
    CRUDController controller;
    JPanel pnlPrincipal;
    JPanel pnlBusca;
    JPanel pnlResultado;
    JTable table;
    String nomePaciente;

    public ViewBuscarConsulta(CRUDController controller, String nomePaciente) {
        super("Buscar Agendamentos");

        addWindowListener(this);

        this.controller = controller;
        this.nomePaciente = nomePaciente;

        JPanel pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.setBackground(Color.WHITE);
        this.pnlPrincipal = pnlPrincipal;

        JPanel pnlBusca = new JPanel(new BorderLayout(0, 30)); 
        pnlBusca.setBackground(Color.WHITE);
        this.pnlBusca = pnlBusca;

        JPanel pnlResultado = new JPanel(new BorderLayout());
        pnlResultado.setBackground(Color.WHITE);
        this.pnlResultado = pnlResultado;

        setPnlBusca(pnlPrincipal, pnlBusca, pnlResultado);
        criarJT();

        setContentPane(pnlPrincipal);
        pack(); // Ajusta tamanho da tela aos elementos
        setLocationRelativeTo(null); // Posiciona JFrame no centro da tela

        setVisible(true);
    }

    // Monta o painel principal junto com os botoes da tela erro
    public void setPnlBusca(JPanel pnlPrincipal, JPanel pnlBusca, JPanel pnlResultado){

        JLabel lblBusca = new JLabel("Buscar Agendamento");
        lblBusca.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pnlContainerBusca = new JPanel(new BorderLayout(0, 3));
        pnlContainerBusca.setBackground(Color.WHITE);

        JPanel pnlDadosBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlDadosBusca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlDadosBusca.setBackground(Color.WHITE);

        // Paciente label, field
        JLabel lblDadosBusca1 = new JLabel("Nome do (a) Paciente");
        lblDadosBusca1.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField npaciente = new JTextField(15);
        npaciente.setFont(new Font("Arial", Font.PLAIN, 14));
        this.npaciente = npaciente;

        // Data label e maskfield
        JLabel lblDadosBusca2 = new JLabel("Data da Consulta");
        lblDadosBusca2.setFont(new Font("Arial", Font.BOLD, 14));

        // Formatacao da data
        try{
            MaskFormatter novadata = new MaskFormatter("##/##/####");
            novadata.setPlaceholderCharacter('_');
            JFormattedTextField ndata = new JFormattedTextField(novadata);
            ndata.setFont(new Font("Arial", Font.PLAIN, 14));
            ndata.setEnabled(true);
            this.ndata = ndata;
            }
            catch (Exception e){
                System.out.println("Falha na Formatação");
            }

        // Medico label e field
        JLabel lblDadosBusca3 = new JLabel("Médico da Consulta");
        lblDadosBusca3.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField nmedico = new JTextField(15);
        nmedico.setFont(new Font("Arial", Font.PLAIN, 14));
        this.nmedico = nmedico;

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(55, 50, 170));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.setFocusPainted(false);
        this.btnBuscar = btnBuscar;
        btnBuscar.addActionListener(this);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(new Color(100, 200, 100));
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpar.setFocusPainted(false);
        this.btnLimpar = btnLimpar;
        btnLimpar.addActionListener(this);

        JButton btnSair = new JButton("Cancelar");
        btnSair.setBackground(new Color(250, 100, 100));
        btnSair.setForeground(Color.WHITE);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.setFocusPainted(false);
        this.btnSair = btnSair;
        btnSair.addActionListener(this);

        JPanel pnlBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBotao.setBackground(Color.WHITE);

        pnlDadosBusca.add(lblDadosBusca1);
        pnlDadosBusca.add(npaciente);
        pnlDadosBusca.add(lblDadosBusca2);
        pnlDadosBusca.add(ndata);
        pnlDadosBusca.add(lblDadosBusca3);
        pnlDadosBusca.add(nmedico);

        pnlBotao.add(btnBuscar);
        pnlBotao.add(btnLimpar);
        pnlBotao.add(btnSair);

        // Organização do painel e layout
        pnlContainerBusca.add(lblBusca, BorderLayout.NORTH);
        pnlContainerBusca.add(pnlDadosBusca, BorderLayout.CENTER);
        pnlContainerBusca.add(pnlBotao, BorderLayout.SOUTH);

        pnlBusca.add(pnlContainerBusca, BorderLayout.SOUTH);

        JLabel lblEsq = new JLabel("ESQ");
        lblEsq.setForeground(Color.WHITE);
        JLabel lblDir = new JLabel("DIR");
        lblDir.setForeground(Color.WHITE);
        JLabel lblInf = new JLabel("I");
        lblInf.setForeground(Color.WHITE);

        pnlPrincipal.add(pnlBusca, BorderLayout.CENTER);
        pnlPrincipal.add(lblEsq, BorderLayout.WEST);
        pnlPrincipal.add(lblDir, BorderLayout.EAST);
        pnlPrincipal.add(pnlResultado, BorderLayout.SOUTH);
    }


    // Funcao que cria Jtable para receber dados das consultas buscadas
    public void criarJT(){
        String colunas [] = {"ID", "Data", "Hora","Paciente","Medico"};

        HashSet<Object> H = new HashSet<Object>();

        Object x [] = {"", "","", "", ""};
        H.add(x);

        Object ls [][] = new Object[25][5] ; int cont = 0;

        Iterator<Object> it = H.iterator();
        while(it.hasNext()){
            
            Object s [] = (Object [])it.next();
                for( int z = 0; z < 5; z++){
                    ls[cont][z] = s[z];
                }
            cont ++;

        TableModel modelo = new DefaultTableModel(ls, colunas) {
            public Class getColumnClass(int column) {
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
            
            pnlResultado.add(pane, BorderLayout.CENTER);

            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel("     Agendamentos de Consulta");
            label.setFont(new Font("Arial", Font.BOLD, 17));
            panel.add(label, BorderLayout.NORTH);
            pnlResultado.add(panel, BorderLayout.NORTH);
            pnlResultado.setVisible(true);
            
        }
    }

    // Retorno de funcoes que capturam o texto
    public String getData() {
        return ndata.getText();
     }
     public String getPaciente() {
         return npaciente.getText();
     }
     public String getMedico() {
         return nmedico.getText();
     }

    // Chamada das acoes dos botoes da tela 
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnLimpar) {
            if (nomePaciente.equals("")) {
                npaciente.setText("");
            }
            ndata.setText("");
            nmedico.setText("");
            this.pnlResultado.removeAll();
            if (!nomePaciente.equals("")) {
                controller.buscarConsulta1("__/__/____", nomePaciente, "");
            } else {
            controller.buscarConsulta1("__/__/____", "", "");   
            }
            this.validate();
            }
        else if (e.getSource() == btnBuscar){
            controller.buscarConsulta1(getData(),getPaciente(),getMedico());
        }
        else if (e.getSource() == btnSair){
            this.dispose(); // fecha apenas essa janela
        }
    }

    // Metodo dos Observadores
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
    // Recebe de volta do model um hashset com as consultas filtradas por busca
    public void update(HashSet<Consulta> DadosConsulta){

        int p = DadosConsulta.size();

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
                    Paciente o = s.getPaciente();
                    ls[cont][z] = o.getNome();
                }
                else if(z == 4){
                    Medico m = s.getMedico();
                    ls[cont][z] = m.getMedico();
                }
            }
            cont ++;
        }
        TableModel modelo = new DefaultTableModel(ls, colunas) {
            public Class getColumnClass(int column) {
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

        this.pnlResultado.removeAll();
        
        this.pnlResultado.add(pane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("      Agendamentos de Consulta");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.NORTH);
        pnlResultado.add(panel, BorderLayout.NORTH);
        pnlResultado.setVisible(true);
        this.validate();

        
        }

    // Tratadores de Janela
    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) { 
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
        if (!nomePaciente.equals("")) {
            npaciente.setText(nomePaciente);
            npaciente.setEditable(false);
            controller.buscarConsulta1("__/__/____", nomePaciente, "");
        } else {
        controller.buscarConsulta1("__/__/____", "", "");   
        }
    } 
}
