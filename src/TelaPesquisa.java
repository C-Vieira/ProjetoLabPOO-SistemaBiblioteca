import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaPesquisa  extends TelaBase{

    /* Classe que define uma tela de pesquisa contendo todos os campos para acomodar os dados de um livro
       Possui um botão para pesquisa e uma tabela para mostragem de resultados */

    private final LivroDAO livroDAO;

    public TelaPesquisa() {
        super("Empréstimos");

        livroDAO = new LivroDAO();

        lblCampo1.setText("Título:");
        lblCampo2.setText("Categoria:");
        lblCampo3.setText("Autor:");
        lblCampo4.setText("ISBN:");
        lblCampo5.setText("Prazo de Entrega (em dias):");
        lblCampo6.setText("Status de Disponibilidade:");

        radioBtn.setText("Disponível");
        radioBtn.setSelected(false);

        btnHeader1.setText("Mostrar Empréstimos");
        btnHeader1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //carrega tabela com empréstimos
            }
        });

        btnHeader2.setVisible(false);

        btn1.setText("Pesquisar");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pesquisar
            }
        });

        btn2.setText("Emprestar para:");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //carregar tabela com usuários
            }
        });

        btn3.setText("Emprestar");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //criar novo empréstimo
            }
        });

        btn4.setText("Devolver");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //devolver
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

}
