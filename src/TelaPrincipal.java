import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends javax.swing.JFrame {

    /*  Classe que define uma tela contendo três botões (pesquisa, devolução, cadastro)
        Usada para navegação no sistema */

    private JPanel pnlTitulo;
    private JPanel pnlPrincipal;
    private JLabel lblTitulo;
    private JButton btnPesquisa;
    private JButton btnCadastroLivro;
    //private JButton btnDevolucao;
    //private JButton btnCadastroUsuario;

    public TelaPrincipal() {
        setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setSize(500, 250);
        setLayout(new BorderLayout());

        pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new FlowLayout());

        pnlTitulo = new JPanel();
        pnlTitulo.setLayout(new FlowLayout());

        lblTitulo = new JLabel("Sistema Gerenciador de Livros");
        pnlTitulo.add(lblTitulo);

        btnPesquisa = new JButton("Empréstimos");
        btnPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPesquisa pesquisa = new TelaPesquisa(); //Cria uma nova tela de pesquisa
                pesquisa.setVisible(true);
            }
        });

        /*btnDevolucao = new JButton("Realizar Devolução");
        btnDevolucao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrindo Tela de Devolver"); //Cria uma nova tela de devolução
            }
        });*/

        btnCadastroLivro = new JButton("Cadastros");
        btnCadastroLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro cadastro = new TelaCadastro(); //Cria uma nova tela de cadastro (acesso restrito para administradores)
                cadastro.setVisible(true);
            }
        });

        /*btnCadastroUsuario = new JButton("Cadastrar Usuário");
        btnCadastroUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroUsuario cadastroUsuario = new TelaCadastroUsuario(); //Cria uma nova tela de cadastro (acesso restrito para administradores)
                cadastroUsuario.setVisible(true);
            }
        });*/

        pnlPrincipal.add(btnPesquisa);
        pnlPrincipal.add(btnCadastroLivro);

        add(pnlTitulo, BorderLayout.NORTH);
        add(pnlPrincipal, BorderLayout.CENTER);

        //if (!UsuarioBaseDeDados.getUsuarioAtual().equals("admin"))
            //btnCadastro.setEnabled(false);

        pack();
    }
}
