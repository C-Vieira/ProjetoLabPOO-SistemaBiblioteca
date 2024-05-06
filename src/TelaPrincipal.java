import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Principal;

public class TelaPrincipal extends javax.swing.JFrame implements ActionListener {

    /*  Classe que define uma tela contendo três botões (pesquisa, devolução, cadastro)
        Usada para navegação no sistema */

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

        if (!UsuarioBaseDeDados.getUsuarioAtual().equals("admin"))
            btnCadastro.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("pesquisar")) {
            TelaPesquisa pesquisa = new TelaPesquisa(); //Cria uma nova tela de pesquisa
            pesquisa.setVisible(true);
        }
        if(e.getActionCommand().equals("devolver")) {
            System.out.println("Abrindo Tela de Devolver"); //Cria uma nova tela de devolução
        }
        if(e.getActionCommand().equals("cadastrar")) {
           TelaCadastroLivro cadastro = new TelaCadastroLivro(); //Cria uma nova tela de cadastro (acesse restrito para administradores)
           cadastro.setVisible(true);
        }
    }
}
