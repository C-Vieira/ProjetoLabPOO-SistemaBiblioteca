import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroLivro extends javax.swing.JFrame implements ActionListener {
    private JLabel lblTitulo;
    private JLabel lblCategoria;
    private JLabel lblAutor;
    private JLabel lblISBN;
    private JLabel lblPrazoDeEntrega;
    private JLabel lblDisponibilidade;
    private JTextField txtFieldTitulo;
    private JTextField txtFieldCategoria;
    private JTextField txtFieldAutor;
    private JTextField txtFieldISBN;
    private JTextField txtPrazoDeEntrega;
    private JRadioButton radioBtnDisponivel;
    private JButton btnAdicionarLivro;
    private JButton btnBuscarLivro;
    private JButton btnEditarLivro;
    private JButton btnExcluirLivro;
    public static LivroBaseDeDados baseDeDados  = new LivroBaseDeDados(); //Rever isto...

    public TelaCadastroLivro() {
        setTitle("Cadastro de Livros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 400);
        setLayout(new GridLayout(4, 4));

        lblTitulo = new JLabel("Título:");
        lblCategoria = new JLabel("Categoria:");
        lblAutor = new JLabel("Autor:");
        lblISBN = new JLabel("ISBN:");
        lblPrazoDeEntrega = new JLabel("Prazo de Entrega (em dias):");
        lblDisponibilidade = new JLabel("Status de Disponibilidade:");
        txtFieldTitulo = new JTextField();
        txtFieldCategoria = new JTextField();
        txtFieldAutor = new JTextField();
        txtFieldISBN = new JTextField();
        txtPrazoDeEntrega = new JTextField();
        radioBtnDisponivel = new JRadioButton("Disponível", false);
        btnAdicionarLivro = new JButton("Adicionar");
        btnBuscarLivro = new JButton("Buscar");
        btnEditarLivro = new JButton("Editar");
        btnExcluirLivro = new JButton("Excluir");

        btnAdicionarLivro.setActionCommand("adicionar");
        btnAdicionarLivro.addActionListener(this);
        btnBuscarLivro.setActionCommand("buscar");
        btnBuscarLivro.addActionListener(this);
        btnEditarLivro.setActionCommand("editar");
        btnEditarLivro.addActionListener(this);
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
        add(lblPrazoDeEntrega);
        add(txtPrazoDeEntrega);
        add(lblDisponibilidade);
        add(radioBtnDisponivel);
        add(btnAdicionarLivro);
        add(btnBuscarLivro);
        add(btnEditarLivro);
        add(btnExcluirLivro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "adicionar":{
                String titulo = txtFieldTitulo.getText();
                String categoria = txtFieldCategoria.getText();
                String autor = txtFieldAutor.getText();
                String ISBN = txtFieldISBN.getText();
                String prazoTexto = txtPrazoDeEntrega.getText();
                boolean disponivel = radioBtnDisponivel.isSelected();

                if(titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty() || ISBN.isEmpty() || prazoTexto.isEmpty()) {
                    System.out.println("Erro ao Adicionar Livro");
                }else{
                    int prazo = Integer.parseInt(txtPrazoDeEntrega.getText());
                    Livro novoLivro = new Livro(titulo, categoria, autor, ISBN, prazo, disponivel);
                    novoLivro.setDisponibilidade(disponivel);
                    baseDeDados.AdicionarLivro(novoLivro);
                }
                txtFieldTitulo.setText("");
                txtFieldCategoria.setText("");
                txtFieldAutor.setText("");
                txtFieldISBN.setText("");
                txtPrazoDeEntrega.setText("");
                radioBtnDisponivel.setSelected(false);
                break;
            }
            case "buscar":{
                TelaPesquisa pesquisa = new TelaPesquisa();
                pesquisa.setVisible(true);
                break;
            }
            case "editar":{
                System.out.println("Abrindo Tela de Editar");
                break;
            }
            case "excluir":{
                String titulo = txtFieldTitulo.getText();

                if(!titulo.isEmpty())
                    baseDeDados.ExcluirLivro(titulo);
                txtFieldTitulo.setText("");
                break;
            }
        }
    }
}
