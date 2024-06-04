import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends javax.swing.JFrame implements BaseView {

    /*  Classe que define uma tela contendo três botões (pesquisa, devolução, cadastro)
        Usada para navegação no sistema */

    private JPanel pnlTitulo;
    private JPanel pnlPrincipal;
    private JLabel lblTitulo;
    private JButton btnPesquisa;
    private JButton btnCadastros;

    public TelaPrincipal() {
        setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setSize(500, 250);
        setLayout(new BorderLayout());

        pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new FlowLayout());

        pnlTitulo = new JPanel();
        pnlTitulo.setLayout(new CardLayout());

        ImageIcon icon = new ImageIcon("imagens/biblioteca_icon.png");
        lblTitulo = new JLabel("     Sistema Gerenciador de Livros");
        pnlTitulo.add(lblTitulo);

        btnPesquisa = new JButton("Empréstimos");
        btnPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceLocator.getInstance().getTelaPesquisa().open(); //Cria uma nova tela de pesquisa
            }
        });

        btnCadastros = new JButton("Cadastros");
        btnCadastros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceLocator.getInstance().getTelaCadastro().open(); //Cria uma nova tela de cadastro (acesso restrito para administradores)
            }
        });

        pnlPrincipal.add(btnPesquisa);
        pnlPrincipal.add(btnCadastros);

        add(pnlTitulo, BorderLayout.NORTH);
        add(new JLabel(icon), BorderLayout.CENTER);
        add(pnlPrincipal, BorderLayout.SOUTH);

        if (!UsuarioDAO.getUsuarioAtual().isAdmin())
            btnCadastros.setEnabled(false);

        pack();
    }

    @Override
    public void open() {
        this.setVisible(true);
    }

    @Override
    public void mostrarMensagemDeErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
