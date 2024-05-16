import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends javax.swing.JFrame {

    /*  Classe que define uma tela contendo três botões (pesquisa, devolução, cadastro)
        Usada para navegação no sistema */

    private JPanel pnlPrincipal;
    private JPanel pnlCadastros;

    private JButton btnPesquisa;
    private JButton btnDevolucao;
    private JButton btnCadastroLivro;
    private JButton btnCadastroUsuario;

    public TelaPrincipal() {
        setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setSize(500, 250);
        setLayout(new BorderLayout());

        pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new FlowLayout());

        pnlCadastros = new JPanel();
        pnlCadastros.setLayout(new FlowLayout());

        btnPesquisa = new JButton("Fazer Pesquisa");
        btnPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPesquisa pesquisa = new TelaPesquisa(); //Cria uma nova tela de pesquisa
                pesquisa.setVisible(true);
            }
        });

        btnDevolucao = new JButton("Realizar Devolução");
        btnDevolucao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrindo Tela de Devolver"); //Cria uma nova tela de devolução
            }
        });

        btnCadastroLivro = new JButton("Cadastrar Livro");
        btnCadastroLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroLivro cadastroLivro = new TelaCadastroLivro(); //Cria uma nova tela de cadastro (acesso restrito para administradores)
                cadastroLivro.setVisible(true);
            }
        });

        btnCadastroUsuario = new JButton("Cadastrar Usuário");
        btnCadastroUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroUsuario cadastroUsuario = new TelaCadastroUsuario(); //Cria uma nova tela de cadastro (acesso restrito para administradores)
                cadastroUsuario.setVisible(true);
            }
        });

        pnlPrincipal.add(btnPesquisa);
        pnlPrincipal.add(btnDevolucao);

        pnlCadastros.add(btnCadastroLivro);
        pnlCadastros.add(btnCadastroUsuario);

        add(pnlPrincipal, BorderLayout.CENTER);
        add(pnlCadastros, BorderLayout.SOUTH);

        //if (!UsuarioBaseDeDados.getUsuarioAtual().equals("admin"))
            //btnCadastro.setEnabled(false);

        pack();
    }
}
