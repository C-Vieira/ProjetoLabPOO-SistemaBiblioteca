import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Principal;

public class TelaPrincipal extends javax.swing.JFrame implements ActionListener {
    private JButton btnPesquisa;
    private JButton btnDevolucao;
    private JButton btnCadastro;

    public TelaPrincipal() {
        setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 250);
        setLayout(new GridLayout(3, 3));

        btnPesquisa = new JButton("Fazer Pesquisa");
        btnDevolucao = new JButton("Realizar Devolução");
        btnCadastro = new JButton("Fazer Cadastros");

        btnPesquisa.setActionCommand("pesquisar");
        btnPesquisa.addActionListener(this);
        btnDevolucao.setActionCommand("devolver");
        btnDevolucao.addActionListener(this);
        btnCadastro.setActionCommand("cadastrar");
        btnCadastro.addActionListener(this);

        add(new JLabel(""));
        add(btnPesquisa);
        add(new JLabel(""));
        add(new JLabel(""));
        add(btnDevolucao);
        add(new JLabel(""));
        add(new JLabel(""));
        add(btnCadastro);
        add(new JLabel(""));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("pesquisar")) {
            System.out.println("Abrindo Tela de Pesquisa");
        }
        if(e.getActionCommand().equals("devolver")) {
            System.out.println("Abrindo Tela de Devolver");
        }
        if(e.getActionCommand().equals("cadastrar")) {
           TelaCadastroLivro cadastro = new TelaCadastroLivro();
           cadastro.setVisible(true);
        }
    }
}
