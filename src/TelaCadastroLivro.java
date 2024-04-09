import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroLivro extends javax.swing.JFrame implements ActionListener {
    private JLabel lblTitulo;
    private JLabel lblCategoria;
    private JLabel lblAutor;
    private JLabel lblISBN;
    private JTextField txtFieldTitulo;
    private JTextField txtFieldCategoria;
    private JTextField txtFieldAutor;
    private JTextField txtFieldISBN;
    private JButton btnAdicionarLivro;
    private JButton btnExcluirLivro;
    private LivroBaseDeDados baseDeDados  = new LivroBaseDeDados();

    public TelaCadastroLivro() {
        setTitle("Cadastro de Livros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 250);
        setLayout(new GridLayout(5, 2));

        lblTitulo = new JLabel("Título:");
        lblCategoria = new JLabel("Categoria:");
        lblAutor = new JLabel("Autor:");
        lblISBN = new JLabel("ISBN:");
        txtFieldTitulo = new JTextField();
        txtFieldCategoria = new JTextField();
        txtFieldAutor = new JTextField();
        txtFieldISBN = new JTextField();
        btnAdicionarLivro = new JButton("Adicionar");
        btnExcluirLivro = new JButton("Excluir");

        btnAdicionarLivro.setActionCommand("adicionar");
        btnAdicionarLivro.addActionListener(this);
        btnExcluirLivro.setActionCommand("excluir");
        btnExcluirLivro.addActionListener(this);

        add(lblTitulo);
        add(txtFieldTitulo);
        add(lblCategoria);
        add(txtFieldCategoria);
        add(lblAutor);
        add(txtFieldAutor);
        add(lblISBN);
        add(txtFieldISBN);
        add(btnExcluirLivro);
        add(btnAdicionarLivro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("adicionar")) {
            String titulo = txtFieldTitulo.getText();
            String categoria = txtFieldCategoria.getText();
            String autor = txtFieldAutor.getText();
            String ISBN = txtFieldISBN.getText();

            if(titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty() || ISBN.isEmpty()) {
                System.out.println("Erro ao Adicionar Livro");
            }else{
                Livro novoLivro = new Livro(0, titulo, categoria, autor, ISBN);
                baseDeDados.AdicionarLivro(novoLivro);
            }
            txtFieldTitulo.setText("");
            txtFieldCategoria.setText("");
            txtFieldAutor.setText("");
            txtFieldISBN.setText("");
        }
        if(e.getActionCommand().equals("excluir")) {
            String titulo = txtFieldTitulo.getText();

            if(!titulo.isEmpty())
                baseDeDados.ExcluirLivro(titulo);
            txtFieldTitulo.setText("");
        }
    }
}
