import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPesquisa  extends javax.swing.JFrame implements ActionListener {
    private JLabel lblTitulo;
    private JLabel lblCategoria;
    private JLabel lblAutor;
    private JLabel lblISBN;
    private JTextField txtFieldTitulo;
    private JTextField txtFieldCategoria;
    private JTextField txtFieldAutor;
    private JTextField txtFieldISBN;
    private JButton btnPesquisar;
    private JTable tblResultados;

    //private LivroBaseDeDados baseDeDados  = new LivroBaseDeDados(); //???

    public TelaPesquisa() {
        setTitle("Pesquisar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 400);
        setLayout(new GridLayout(3, 4));

        lblTitulo = new JLabel("Título:");
        lblCategoria = new JLabel("Categoria:");
        lblAutor = new JLabel("Autor:");
        lblISBN = new JLabel("ISBN:");
        txtFieldTitulo = new JTextField();
        txtFieldCategoria = new JTextField();
        txtFieldAutor = new JTextField();
        txtFieldISBN = new JTextField();
        btnPesquisar = new JButton("Pesquisar");

        btnPesquisar.setActionCommand("pesquisar");
        btnPesquisar.addActionListener(this);

        String[] tituloColunas = {"ID", "Titulo", "Categoria", "Autor", "ISBN", "Prazo", "Disponível"};
        Object[][] dados = { //Mudar isso por uma função de busca que retorna um 2dArray na classe base de dados!
                {"Teste", "Teste", "Teste", "Teste", "Teste", "Teste", "Teste"}
        };
        tblResultados = new JTable(dados, tituloColunas);

        add(lblTitulo);
        add(txtFieldTitulo);
        add(lblCategoria);
        add(txtFieldCategoria);
        add(lblAutor);
        add(txtFieldAutor);
        add(lblISBN);
        add(txtFieldISBN);
        add(btnPesquisar);
        add(new JLabel(""));
        add(tblResultados);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("pesquisar")) {
            System.out.println("Pesquisando...");
        }
    }
}
