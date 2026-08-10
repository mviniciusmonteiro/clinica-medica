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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import com.toedter.calendar.JDateChooser;

public class IncluirCons extends JFrame implements ActionListener, MouseListener, KeyListener, Observador, WindowListener {
    private static JFrame janela;
    private static JPanel painel;
    private static JLabel lbldata;
    private static JLabel lblagendamento;
    private static JLabel lblespeci;
    private static JLabel lblmedico;
    private static JComboBox<String> especialidade;
    private static JComboBox<String> medico;
    private static JButton btnmarcar;
    private static JLabel lblcancelar;
    private static JPanel borda;
    private static JDateChooser escData;
    private static JComboBox<String> txtNovaHora;
    private static JLabel lbldataerr;
    HashSet<String> especialidades = new HashSet<>();
    String data;
    String opcoesHora[];

    CRUDController controller;

    ImageIcon icone;
    ImageIcon iconee;
    
    int day;
    int month;
    int year;

    public IncluirCons(CRUDController controller) {
        this.controller = controller;
        Inc();
    }

    public void Inc() {
        janela = new JFrame();
        painel = new JPanel();

        janela.setSize(450, 350);
        janela.setResizable(false);
        janela.setTitle("Marcar consulta");
        janela.addMouseListener(this);
        janela.setLocationRelativeTo(null);

        this.janela = janela;

		janela.add(painel);
        
        painel.setLayout(null); 
        painel.setBackground(Color.WHITE);

        lblagendamento = new JLabel("Agendamento", SwingConstants.CENTER);
        lblagendamento.setFont(new Font("Serif", Font.BOLD, 20));
        lblagendamento.setSize(200, 20);
        lblagendamento.setBounds(10, 70, 200, 40);
        painel.add(lblagendamento);

        lbldata = new JLabel("Data/Hora");
        lbldata.setBounds(65, 120, 80, 25);
        painel.add(lbldata);

        escData = new JDateChooser();
        escData.setBounds(140, 120, 145, 25);
        escData.setLocale(new Locale("pt", "BR"));
        painel.add(escData);
        
        String opcoesHora[] = {"8:00", "8:30", "9:00", "9:30", "10:00", "10:30", 
        "11:00", "11:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"};
        txtNovaHora = new JComboBox<>(opcoesHora);
        txtNovaHora.setBounds(290, 120, 80, 25);
        txtNovaHora.setEnabled(false);
        painel.add(txtNovaHora);

        lblespeci = new JLabel("Especialidade");
        lblespeci.setBounds(65, 150, 110, 25);
        painel.add(lblespeci);

        especialidade = new JComboBox<>();
        especialidade.setBounds(165, 150, 205, 25);
        Iterator<Medico>  it = controller.model.medicos.iterator();
        while (it.hasNext()) {
            especialidades.add(it.next().getEspecialidade());
        }
        Iterator<String> ite = especialidades.iterator();
        while(ite.hasNext()) {
            especialidade.addItem(ite.next());
        }
        especialidade.setEnabled(false);
        painel.add(especialidade);
        
        lblmedico = new JLabel("Médico");
        lblmedico.setBounds(65, 180, 80, 25);
        painel.add(lblmedico);

        medico = new JComboBox<>();
        medico.setBounds(118, 180, 252, 25);
        medico.setEnabled(false);
        medico.addMouseListener((MouseListener) this);
        painel.add(medico);

        btnmarcar = new JButton("Marcar");
        btnmarcar.setForeground(Color.WHITE);
        btnmarcar.setBackground(new Color(52,102,0));
        btnmarcar.setBounds(350, 270, 85, 25);
        btnmarcar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnmarcar.addActionListener((ActionListener) this);
        btnmarcar.setEnabled(false);
        painel.add(btnmarcar);

        lblcancelar = new JLabel("cancelar");
        lblcancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblcancelar.addMouseListener(this);
        lblcancelar.setBounds(270, 270, 130, 25);
        lblcancelar.setForeground(new Color(205,0,0));
        painel.add(lblcancelar);

        lbldataerr = new JLabel("*Data inválida, por favor selecione uma data válida");
        lbldataerr.setFont(new Font("Serif", Font.BOLD, 10));
        lbldataerr.setForeground(new Color(250,0,0));
        lbldataerr.setVisible(false);
        lbldataerr.setBounds(65, 210, 350, 25);
        painel.add(lbldataerr);

        borda = new JPanel();
		borda.setBounds(30, 110, 390, 103);
        borda.setBackground(Color.WHITE);
		borda.setBorder(BorderFactory.createLineBorder(Color.black));  
        painel.add(borda);



        janela.setVisible(true);
    }

    public boolean Dataevalida() {
        int dia;
        int mes;
        int ano;


        data = ((JTextField)escData.getDateEditor().getUiComponent()).getText();

       if (!data.equals("")) {
            getDate();
        }

        DateTimeFormatter a = DateTimeFormatter.ofPattern("yyyy");  
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd"); 
        DateTimeFormatter m = DateTimeFormatter.ofPattern("MM"); 
        LocalDateTime now = LocalDateTime.now();  
        
        try {
            ano = Integer.parseInt(now.format(a).toString());
            dia = Integer.parseInt(now.format(d).toString());
            mes = Integer.parseInt(now.format(m).toString());

            if (year < ano || month < mes || (month == mes && day < dia)) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("erro no cast de datas Dataevalida()");
        }

        
        return true;
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

        try {
            day = Integer.parseInt(dat[2]);
            month = Integer.parseInt(MonthtoNmbr(dat[1].toLowerCase()));
            year = Integer.parseInt(dat[5]);
        } catch (Exception e) {
            System.out.println("erro no cast de data getDate()");
        }

        return dat[2] + "/" + MonthtoNmbr(dat[1].toLowerCase()) + "/" + dat[5];
    }

    public void attMedico () {
        medico.removeAllItems();

        Iterator<Medico> i = controller.model.medicos.iterator();
        Medico m;

        while(i.hasNext()) {
            m = i.next();

            if (m.getEspecialidade().equals(especialidade.getSelectedItem().toString())) {
                medico.addItem(m.getMedico());
            }
        }
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

    // ACTION LISTENERS


    @Override
    public void actionPerformed(ActionEvent e) {
        Paciente p;
        Medico   m;
        int resposta;

        if (e.getSource() == btnmarcar) {
            m = controller.model.buscarMedicoNome(medico.getSelectedItem().toString());
            p = controller.buscarPacienteEmail(controller.model.getEmailDoPerfil());

             icone = new ImageIcon("success.png");
             iconee = new ImageIcon("failed.png");

            resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente marcar consulta com Dr." + medico.getSelectedItem().toString() + "("+especialidade.getSelectedItem().toString()+")" + " dia " + getDate()+ " às "+txtNovaHora.getSelectedItem().toString()+ " ?", "Confirmar agendamento", JOptionPane.OK_CANCEL_OPTION);
            if (resposta == 0)
                if(controller.IncluirConsulta(p.getCpf(), m.getCRM(), getDate(), txtNovaHora.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(null, "Sua consulta com Dr." + medico.getSelectedItem().toString() + "(" + especialidade.getSelectedItem().toString() + ")"+ " dia "+ getDate() + " às " + txtNovaHora.getSelectedItem().toString() + " foi agendada.", "Confirmado",JOptionPane.NO_OPTION , icone);                
                    janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi posivel agendar essa consulta.", "Não agendado",JOptionPane.NO_OPTION , iconee);
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
        
        data = ((JTextField)escData.getDateEditor().getUiComponent()).getText();
        if (arg0.getSource() == janela) {
            if (data.equals("") ) {
                btnmarcar.setEnabled(false);
                medico.setEnabled(false);
                especialidade.setEnabled(false);
                txtNovaHora.setEnabled(false);
            } else {
                medico.setEnabled(true);
                especialidade.setEnabled(true);
                txtNovaHora.setEnabled(true);
            }
        } else {
            lblcancelar.setForeground(new Color(250,0,0));
        }

        if (arg0.getSource() == medico) {
            attMedico();
        }

        if (!Dataevalida() && !data.equals("")) {
            lbldataerr.setVisible(true);
            btnmarcar.setEnabled(false);
        } else {
            btnmarcar.setEnabled(true);
            lbldataerr.setVisible(false);
        }
    }

    @Override

    public void mouseExited(MouseEvent arg0) {
        //retorna a cor normal do canecelar quando o mouse sai dele
            if (arg0.getSource() == lblcancelar) {
                lblcancelar.setForeground(new Color(205,0,0));
            } else {
                if (arg0.getSource() == medico) {
                    attMedico();
                }
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
 
        if (data.equals("")) {
            btnmarcar.setEnabled(false);
            medico.setEnabled(false);
            especialidade.setEnabled(false);
            txtNovaHora.setEnabled(false);
        } else {
            btnmarcar.setEnabled(true);
            medico.setEnabled(true);
            especialidade.setEnabled(true);
            txtNovaHora.setEnabled(true);
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


