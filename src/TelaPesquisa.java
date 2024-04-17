import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPesquisa  extends javax.swing.JFrame implements ActionListener{

    /* Classe que define uma tela de pesquisa contendo todos os campos para acomodar os dados de um livro
       Possui um botão para pesquisa e uma tabela para mostragem de resultados */

    private JPanel panelTabela;
    private JScrollPane tabelaScrollPane;
    private JPanel panelCampos;
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
    private JButton btnPesquisar;
    private JTable tblResultados;

    public TelaPesquisa() {
        setTitle("Pesquisar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1000, 400);
        setLayout(new GridLayout(1, 2));

        panelTabela = new JPanel();
        panelCampos = new JPanel();

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
        radioBtnDisponivel = new JRadioButton("Disponivel", false);
        btnPesquisar = new JButton("Pesquisar");

        btnPesquisar.setActionCommand("pesquisar");
        btnPesquisar.addActionListener(this);

        tblResultados = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) tblResultados.getModel(); //Cria um modelo de tabela para que possamos manipular as linhas
        String[] tituloColunas = {"ID", "Titulo", "Categoria", "Autor", "ISBN", "Prazo", "Disponível"}; //Define o título de cada coluna
        tableModel.setColumnIdentifiers(tituloColunas);
        tabelaScrollPane = new JScrollPane(tblResultados);
        tblResultados.setFillsViewportHeight(true);

        panelTabela.add(tabelaScrollPane);

        panelCampos.setLayout(new GridLayout(7, 2));

        panelCampos.add(lblTitulo);
        panelCampos.add(txtFieldTitulo);
        panelCampos.add(lblCategoria);
        panelCampos.add(txtFieldCategoria);
        panelCampos.add(lblAutor);
        panelCampos.add(txtFieldAutor);
        panelCampos.add(lblISBN);
        panelCampos.add(txtFieldISBN);
        panelCampos.add(lblPrazoDeEntrega);
        panelCampos.add(txtPrazoDeEntrega);
        panelCampos.add(lblDisponibilidade);
        panelCampos.add(radioBtnDisponivel);
        panelCampos.add(new JLabel(""));
        panelCampos.add(btnPesquisar);

        add(panelTabela);
        add(panelCampos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("pesquisar")) {
            System.out.println("Pesquisando...");
            DefaultTableModel tableModel = (DefaultTableModel) tblResultados.getModel(); //Cria um modelo de tabela para que possamos manipular as linhas
            tableModel.setRowCount(0); //Limpa os resultados anteriores

            //Captura os dados dos campos preenchidos
            String titulo = txtFieldTitulo.getText();
            String categoria = txtFieldCategoria.getText();
            String autor = txtFieldAutor.getText();
            String ISBN = txtFieldISBN.getText();

            //Se o campo estiver preenchido, pesquisamos por este dado, se não, tentamos o próximo
            if(!titulo.isEmpty()) {
                tableModel = TelaCadastroLivro.baseDeDados.BuscarLivrosPorTitulo(titulo, tableModel); //Referencia a base de dados e realiza uma busca
                tableModel.fireTableDataChanged(); //Atualiza a visualização da tabela com os novos resultados
            } else if (!categoria.isEmpty()) {
                tableModel = TelaCadastroLivro.baseDeDados.BuscarLivrosPorCategoria(categoria, tableModel);
                tableModel.fireTableDataChanged();
            } else if (!autor.isEmpty()) {
                tableModel = TelaCadastroLivro.baseDeDados.BuscarLivrosPorAutor(autor, tableModel);
                tableModel.fireTableDataChanged();
            } else if (!ISBN.isEmpty()) {
                tableModel = TelaCadastroLivro.baseDeDados.BuscarLivrosPorISBN(ISBN, tableModel);
                tableModel.fireTableDataChanged();
            }else{
                System.out.println("Livro não encontrado");
            }

            txtFieldTitulo.setText("");
            txtFieldCategoria.setText("");
            txtFieldAutor.setText("");
            txtFieldISBN.setText("");
            txtPrazoDeEntrega.setText("");
            radioBtnDisponivel.setSelected(false);
        }
    }
}
