import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TelaPesquisa extends TelaBase implements EmprestimoListener {

    /* Classe que define uma tela de pesquisa contendo todos os campos para acomodar os dados de um livro
       Possui um botão para pesquisa e uma tabela para mostragem de resultados */

    private final LivroDAO livroDAO;
    private final LivroController livroController;
    private final UsuarioDAO usuarioDAO;
    private final UsuarioController usuarioController;
    private final EmprestimoDAO emprestimoDAO;
    private final EmprestimoController emprestimoController;
    private int IDLivroSelecionado = -1;
    private int IDUsuarioSelecionado = -1;
    private boolean pesquisarPorUsuario = false;
    private boolean mostrarEmprestimos = false;

    public TelaPesquisa() {
        super("Empréstimos");

        livroDAO = new LivroDAO();
        livroDAO.subscribe(this);
        livroController = new LivroController(this, livroDAO);

        usuarioDAO = new UsuarioDAO();
        usuarioDAO.subscribe(this);
        usuarioController = new UsuarioController(this, usuarioDAO);

        emprestimoDAO = new EmprestimoDAO();
        emprestimoDAO.subscribe(this);
        emprestimoController = new EmprestimoController(this, emprestimoDAO);

        prepararCamposCadastro();

        /*//Botões para testes
        //Botão Excluir de Teste
        JButton btnHeader3 = new JButton("Excluir");
        btnHeader3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        panelHeader.add(btnHeader3);
        JButton btnHeader4 = new JButton("SetDisponível");
        btnHeader4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                livroController.editarLivro(IDLivroSelecionado, coletaDados());
            }
        });
        panelHeader.add(btnHeader4);
        //Remover aqui!*/

        btnHeader1.setText("Selecionar Livro");
        btnHeader1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarPorUsuario = false;
                mostrarEmprestimos = false;
                prepararCamposCadastro();
                limparCampos();
            }
        });

        btnHeader2.setText("Selecionar Cliente");
        btnHeader2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarPorUsuario = true;
                mostrarEmprestimos = false;
                prepararCamposCadastro();
                limparCampos();
            }
        });

        btn1.setText("Buscar");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });

        btn2.setText("Mostrar Empréstimos");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEmprestimos();
            }
        });

        btn3.setText("Emprestar");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("IDSelecionado: "+IDSelecionado+
                        "\nIDLivroSelecionado: "+IDLivroSelecionado+
                        "\nIDUsuarioSelecionado: "+IDUsuarioSelecionado);

                //Criar novo empréstimo e atualizar tabela
                mostrarEmprestimos();
                preencherCamposParaEmprestimo();
                adicionarEmprestimo();
            }
        });

        btn4.setText("Devolver");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolver();
            }
        });

        String[] tituloColunas = {"ID", "Titulo", "Categoria", "Autor", "ISBN", "Prazo", "Disponível"}; //Define o título de cada coluna
        tableModel.setColumnIdentifiers(tituloColunas);

        recarregarTabela();
    }

    /* Override do método para seleção de TelaBase para que possamos distinguir os ids... */
    @Override
    protected void handleSelectionEvent(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        int i = tblResultados.getSelectedRow();

        if(i < 0) return; //Se a tabela estiver vazia, retorne

        //Coleta e conversão dos dados
        int id = (int) tblResultados.getValueAt(i, 0);
        String campo1 = (String) tblResultados.getValueAt(i, 1);
        String campo2 = (String) tblResultados.getValueAt(i, 2);
        String campo3 = (String) tblResultados.getValueAt(i, 3);
        String campo4 = (String) tblResultados.getValueAt(i, 4);
        String campo5 = (String) tblResultados.getValueAt(i, 5);
        boolean campo6 = (boolean) tblResultados.getValueAt(i, 6);

        //Atualização dos campos
        IDSelecionado = id;
        if(pesquisarPorUsuario) IDUsuarioSelecionado = id;
        else IDLivroSelecionado = id;
        txtFieldCampo1.setText(campo1);
        txtFieldCampo2.setText(campo2);
        txtFieldCampo3.setText(campo3);
        txtFieldCampo4.setText(campo4);
        txtFieldCampo5.setText(campo5);
        radioBtn.setSelected(campo6);

        atualizaBotaoEmprestimo();
    }

    private void prepararCamposCadastro(){
        if(pesquisarPorUsuario){
            lblCampo1.setText("Nome:");
            lblCampo2.setText("Senha:");
            lblCampo3.setText("CPF:");
            lblCampo4.setText("RG:");
            lblCampo5.setText("Email:");
            lblCampo6.setText("Admin:");

            radioBtn.setText("Sim");
            radioBtn.setSelected(false);

            String[] tituloColunas = {"ID", "Nome", "Senha", "CPF", "RG", "Email", "Admin"}; //Define o título de cada coluna
            tableModel.setColumnIdentifiers(tituloColunas);

            btn1.setEnabled(true);
            btn4.setEnabled(false);
        }else if(mostrarEmprestimos){
            lblCampo1.setText("Título do Livro:");
            lblCampo2.setText("Nome do Cliente:");
            lblCampo3.setText("Data de Empréstimo:");
            lblCampo4.setText("Data Prevista de Devolução:");
            lblCampo5.setText("Data Real de Devolução:");
            lblCampo6.setText("Devolvido:");

            radioBtn.setText("Sim");
            radioBtn.setSelected(false);

            String[] tituloColunas = {"ID", "Livro", "Cliente", "Dta Empréstimo", "Dta Prevista Devolução", "Dta Real Devolução", "Devolvido"}; //Define o título de cada coluna
            tableModel.setColumnIdentifiers(tituloColunas);

            btn1.setEnabled(false);
            btn4.setEnabled(true);
        }else{
            lblCampo1.setText("Título:");
            lblCampo2.setText("Categoria:");
            lblCampo3.setText("Autor:");
            lblCampo4.setText("ISBN:");
            lblCampo5.setText("Prazo de Entrega (em dias):");
            lblCampo6.setText("Status de Disponibilidade:");

            radioBtn.setText("Disponível");
            radioBtn.setSelected(false);

            String[] tituloColunas = {"ID", "Titulo", "Categoria", "Autor", "ISBN", "Prazo", "Disponível"}; //Define o título de cada coluna
            tableModel.setColumnIdentifiers(tituloColunas);

            btn1.setEnabled(true);
            btn4.setEnabled(false);
        }

        recarregarTabela();
    }

    private void recarregarTabela() {
        //Atualização da visualização da tabela
        tableModel.setRowCount(0);
        if (pesquisarPorUsuario) {
            List<Usuario> usuarios = usuarioDAO.getUsuarios();
            for (Usuario usuario : usuarios) {
                tableModel.addRow(new Object[]{usuario.getID(), usuario.getNome(), usuario.getSenha(),
                        usuario.getCPF(), usuario.getRG(), usuario.getEmail(), usuario.isAdmin()});
            }
        }else if(mostrarEmprestimos){
            List<Emprestimo> emprestimos = emprestimoDAO.getEmprestimos();
            for (Emprestimo emprestimo : emprestimos) {
                tableModel.addRow(new Object[]{emprestimo.getID(), emprestimo.getLivro().getTitulo(), emprestimo.getUsuario().getNome(),
                        emprestimo.getDataEmprestimo(), emprestimo.getDataPrevistaDevolucao(),
                        emprestimo.getDataRealDevolucao(), emprestimo.isDevolvido()});
            }
        }else{
            List<Livro> livros = livroDAO.getLivros();
            for (Livro livro : livros) {
                tableModel.addRow(new Object[]{livro.getID(), livro.getTitulo(), livro.getCategoria(),
                        livro.getAutor(), livro.getISBN(), livro.getPrazoDeEntrega(), livro.isDisponivel()});
            }
        }
        atualizaBotaoEmprestimo();
        tableModel.fireTableDataChanged();
        IDSelecionado = -1; //Resetamos o ID selecionado para evitar erros
    }

    private void atualizaBotaoEmprestimo(){
        if(IDLivroSelecionado == -1 || IDUsuarioSelecionado == -1){
            btn3.setEnabled(false);
        }else{
            btn3.setEnabled(true);
        }
    }

    private void mostrarEmprestimos(){
        pesquisarPorUsuario = false;
        mostrarEmprestimos = true;

        prepararCamposCadastro();
        limparCampos();

        if(IDLivroSelecionado != -1) {
            txtFieldCampo1.setText(livroController.buscarLivroPorID(IDLivroSelecionado).getTitulo());
        }
        if(IDUsuarioSelecionado != -1){
            txtFieldCampo2.setText(usuarioController.buscarUsuarioPorID(IDUsuarioSelecionado).getNome());
        }
    }

    private void preencherCamposParaEmprestimo(){
        Calendar agora = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        txtFieldCampo3.setText(dateFormat.format(agora.getTime())); //Data de Empréstimo

        Livro livro = livroController.buscarLivroPorID(IDLivroSelecionado);
        int prazoDeEntrega = Integer.parseInt(livro.getPrazoDeEntrega());
        agora.add(Calendar.DATE, prazoDeEntrega);

        txtFieldCampo4.setText(dateFormat.format(agora.getTime())); //Data Prevista de Entrega
    }

    private void buscar(){
        if(pesquisarPorUsuario){
            usuarioController.buscarUsuario(coletaDados());
        } else {
            livroController.buscarLivro(coletaDados());
        }
    }

    //Para testes...
    private void excluir(){
        emprestimoController.excluirEmprestimo(IDSelecionado);
    }

    private void adicionarEmprestimo(){
        Livro livro = livroController.buscarLivroPorID(IDLivroSelecionado);
        Usuario usuario = usuarioController.buscarUsuarioPorID(IDUsuarioSelecionado);

        Object[] dados = new Object[6];

        //Coleta dos dados preenchidos
        dados[0] = livro;
        dados[1] = usuario;
        dados[2] = txtFieldCampo3.getText();
        dados[3] = txtFieldCampo4.getText();
        dados[4] = txtFieldCampo5.getText();
        dados[5] = radioBtn.isSelected();

        System.out.println("IDSelecionado: "+IDSelecionado+
                "\nIDLivroSelecionado: "+IDLivroSelecionado+
                "\nIDUsuarioSelecionado: "+IDUsuarioSelecionado);

        IDLivroSelecionado = -1;
        IDUsuarioSelecionado = -1;

        emprestimoController.adicionaEmprestimo(dados);
    }

    private void devolver(){
        IDLivroSelecionado = -1;
        IDUsuarioSelecionado = -1;
        atualizaBotaoEmprestimo();
        limparCampos();

        emprestimoController.editarEmprestimo(IDSelecionado);
    }

    @Override
    public void atualizaDados(){
        recarregarTabela();
        limparCampos();
    }

    @Override
    public <T> void mostrarResultados(List<T> resultado) {
        tableModel.setRowCount(0);
        if (pesquisarPorUsuario) {
            for (Usuario usuario : (List<Usuario>)resultado) {
                tableModel.addRow(new Object[]{usuario.getID(), usuario.getNome(), usuario.getSenha(),
                        usuario.getCPF(), usuario.getRG(), usuario.getRG(), usuario.isAdmin()});
            }
        }else {
            for (Livro livro : (List<Livro>) resultado) {
                tableModel.addRow(new Object[]{livro.getID(), livro.getTitulo(), livro.getCategoria(),
                        livro.getAutor(), livro.getISBN(), livro.getPrazoDeEntrega(), livro.isDisponivel()});
            }
        }
        tableModel.fireTableDataChanged();
        limparCampos();
    }

    @Override
    public void atualizaDisponibiliade(Livro livro) {
        livroController.editarLivro(livro.getID(), new Object[] { livro.getTitulo(), livro.getCategoria(),
                livro.getAutor(), livro.getISBN(), livro.getPrazoDeEntrega(), !livro.isDisponivel() });
    }
}
