import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCadastroLivro extends TelaBase implements LivroListener {

    /* Classe que define uma tela de cadastro de livros, contém todos os campos necessários para criação de um livro
       Possui um botão para pesquisa e uma tabela para mostragem de resultados
       Aceita a seleção de linhas na tabela e preenche os campos com os devidos dados para auxílio nas operações de cadastro */

    private final LivroDAO livroDAO;
    private final LivroController livroController;

    public TelaCadastroLivro() {
        super("Cadastro de Livros");

        livroDAO = new LivroDAO();
        livroDAO.subscribe(this);
        livroController = new LivroController(this, livroDAO);

        lblCampo1.setText("Título:");
        lblCampo2.setText("Categoria:");
        lblCampo3.setText("Autor:");
        lblCampo4.setText("ISBN:");
        lblCampo5.setText("Prazo de Entrega (em dias):");
        lblCampo6.setText("Status de Disponibilidade:");

        radioBtn.setText("Disponível");
        radioBtn.setSelected(false);

        btn1.setText("Adicionar");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionaLivro();
            }
        });

        btn2.setText("Buscar");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLivro();
            }
        });

        btn3.setText("Editar");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarLivro();
            }
        });

        btn4.setText("Excluir");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirLivro();
            }
        });

        String[] tituloColunas = {"ID", "Titulo", "Categoria", "Autor", "ISBN", "Prazo", "Disponível"}; //Define o título de cada coluna
        tableModel.setColumnIdentifiers(tituloColunas);

        recarregarTabela();
    }

    private void recarregarTabela() {
        //Atualização da visualização da tabela
        tableModel.setRowCount(0);
        List<Livro> livros = livroDAO.getLivros();
        for (Livro livro : livros) {
            tableModel.addRow(new Object[]{livro.getID(), livro.getTitulo(), livro.getCategoria(),
                    livro.getAutor(), livro.getISBN(), livro.getPrazoDeEntrega(), livro.isDisponivel()});
        }
        tableModel.fireTableDataChanged();
        IDSelecionado = -1; //Resetamos o ID selecionado para evitar erros
    }

    private Object[] coletaDados(){
        Object[] dados = new Object[6];

        //Coleta dos dados preenchidos
        dados[0] = txtFieldCampo1.getText();
        dados[1] = txtFieldCampo2.getText();
        dados[2] = txtFieldCampo3.getText();
        dados[3] = txtFieldCampo4.getText();
        dados[4] = txtFieldCampo5.getText();
        dados[5] = radioBtn.isSelected();

        return dados;
    }

    private void adicionaLivro(){
        livroController.adicionaLivro(coletaDados());
    }

    private void buscarLivro(){
        livroController.buscarLivro(coletaDados());
    }

    private void editarLivro(){
        livroController.editarLivro(IDSelecionado, coletaDados());
    }

    private void excluirLivro(){
        livroController.excluirLivro(IDSelecionado, coletaDados());
    }

    private void limparCampos(){
        txtFieldCampo1.setText("");
        txtFieldCampo2.setText("");
        txtFieldCampo3.setText("");
        txtFieldCampo4.setText("");
        txtFieldCampo5.setText("");
        radioBtn.setSelected(false);
    }

    @Override
    public void atualizaDados() {
        recarregarTabela();
        limparCampos();
    }

    @Override
    public void mostrarResultados(List<Livro> resultado) {
        tableModel.setRowCount(0);
        for (Livro livro : resultado) {
            tableModel.addRow(new Object[]{livro.getID(), livro.getTitulo(), livro.getCategoria(),
                    livro.getAutor(), livro.getISBN(), livro.getPrazoDeEntrega(), livro.isDisponivel()});
        }
        tableModel.fireTableDataChanged();
        limparCampos();
    }
}
