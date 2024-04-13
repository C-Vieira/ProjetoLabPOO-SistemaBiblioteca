import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroLivro extends javax.swing.JFrame implements ActionListener {
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
    private JButton btnAdicionarLivro;
    private JButton btnBuscarLivro;
    private JButton btnEditarLivro;
    private JButton btnExcluirLivro;
    private JTable tblResultados;
    private int IDSelecionado;
    public static LivroBaseDeDados baseDeDados  = new LivroBaseDeDados(); //Rever isto...

    public TelaCadastroLivro() {
        setTitle("Cadastro de Livros");
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

        tblResultados = new JTable();
        tblResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel tableModel = (DefaultTableModel) tblResultados.getModel();
        String[] tituloColunas = {"ID", "Titulo", "Categoria", "Autor", "ISBN", "Prazo", "Disponível"};
        tableModel.setColumnIdentifiers(tituloColunas);

        ListSelectionModel selectionModel = tblResultados.getSelectionModel();
        selectionModel.addListSelectionListener(this::handleSelectionEvent);

        tabelaScrollPane = new JScrollPane(tblResultados);
        tblResultados.setFillsViewportHeight(true);

        panelTabela.add(tabelaScrollPane);

        panelCampos.setLayout(new GridLayout(8, 2));

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
        panelCampos.add(btnAdicionarLivro);
        panelCampos.add(btnBuscarLivro);
        panelCampos.add(btnEditarLivro);
        panelCampos.add(btnExcluirLivro);

        add(panelTabela);
        add(panelCampos);
    }

    private void handleSelectionEvent(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        int i = tblResultados.getSelectedRow();

        if(i < 0) return;

        int id = (int) tblResultados.getValueAt(i, 0);
        String titulo = (String) tblResultados.getValueAt(i, 1);
        String categoria = (String) tblResultados.getValueAt(i, 2);
        String autor = (String) tblResultados.getValueAt(i, 3);
        String ISBN = (String) tblResultados.getValueAt(i, 4);
        int prazo = (int) tblResultados.getValueAt(i, 5);
        boolean disponibilidade = (boolean) tblResultados.getValueAt(i, 6);

        IDSelecionado = id;
        txtFieldTitulo.setText(titulo);
        txtFieldCategoria.setText(categoria);
        txtFieldAutor.setText(autor);
        txtFieldISBN.setText(ISBN);
        txtPrazoDeEntrega.setText(Integer.toString(prazo));
        radioBtnDisponivel.setSelected(disponibilidade);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tblResultados.clearSelection();
        DefaultTableModel tableModel = (DefaultTableModel) tblResultados.getModel();
        String titulo = txtFieldTitulo.getText();
        String categoria = txtFieldCategoria.getText();
        String autor = txtFieldAutor.getText();
        String ISBN = txtFieldISBN.getText();
        String prazoTexto = txtPrazoDeEntrega.getText();
        boolean disponivel = radioBtnDisponivel.isSelected();

        switch (e.getActionCommand()) {
            case "adicionar":{
                if(titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty() || ISBN.isEmpty() || prazoTexto.isEmpty()) {
                    System.out.println("Erro ao Adicionar Livro");
                }else{
                    int prazo = Integer.parseInt(txtPrazoDeEntrega.getText());
                    Livro novoLivro = new Livro(titulo, categoria, autor, ISBN, prazo, disponivel);
                    novoLivro.setDisponibilidade(disponivel);
                    baseDeDados.AdicionarLivro(novoLivro);
                }
                tableModel.setRowCount(0);
                tableModel = baseDeDados.MostrarTodosOsLivros(tableModel);
                tableModel.fireTableDataChanged();
                IDSelecionado = -1;
                break;
            }
            case "buscar":{
                tableModel.setRowCount(0);
                if(!titulo.isEmpty()) {
                    tableModel = baseDeDados.BuscarLivrosPorTitulo(titulo, tableModel);
                    tableModel.fireTableDataChanged();
                } else if (!categoria.isEmpty()) {
                    tableModel = baseDeDados.BuscarLivrosPorCategoria(categoria, tableModel);
                    tableModel.fireTableDataChanged();
                } else if (!autor.isEmpty()) {
                    tableModel = baseDeDados.BuscarLivrosPorAutor(autor, tableModel);
                    tableModel.fireTableDataChanged();
                } else if (!ISBN.isEmpty()) {
                    tableModel = baseDeDados.BuscarLivrosPorISBN(ISBN, tableModel);
                    tableModel.fireTableDataChanged();
                }else{
                    System.out.println("Livro não encontrado");
                }
                break;
            }
            case "editar":{
                if(titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty() || ISBN.isEmpty() || prazoTexto.isEmpty()) {
                    System.out.println("Erro ao Editar Livro");
                    break;
                }
                int prazo = Integer.parseInt(txtPrazoDeEntrega.getText());
                baseDeDados.EditarLivro(IDSelecionado, titulo, categoria, autor, ISBN, prazo, disponivel);

                tableModel.setRowCount(0);
                tableModel = baseDeDados.MostrarTodosOsLivros(tableModel);
                tableModel.fireTableDataChanged();
                IDSelecionado = -1;
                break;
            }
            case "excluir":{
                baseDeDados.ExcluirLivro(IDSelecionado);

                tableModel.setRowCount(0);
                tableModel = baseDeDados.MostrarTodosOsLivros(tableModel);
                tableModel.fireTableDataChanged();
                break;
            }
        }
        txtFieldTitulo.setText("");
        txtFieldCategoria.setText("");
        txtFieldAutor.setText("");
        txtFieldISBN.setText("");
        txtPrazoDeEntrega.setText("");
        radioBtnDisponivel.setSelected(false);
    }
}
