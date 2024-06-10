package features.cadastro.presentation;

import features.cadastro.datasource.CadastroPublisher;
import features.emprestimo.datasource.EmprestimoListener;
import features.emprestimo.datasource.EmprestimoPublisher;
import features.emprestimo.model.Emprestimo;
import features.emprestimo.presentation.EmprestimoController;
import features.cadastro.livro.model.Livro;
import features.cadastro.livro.presentation.LivroController;
import features.cadastro.user.model.Usuario;
import features.cadastro.user.presentation.UsuarioController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TelaPesquisa extends TelaBase implements EmprestimoListener {

    /* Classe que define uma tela de pesquisa contendo todos os campos para acomodar os dados de um livro, usuário e empréstimo
       Define a tela de operação do sistema, para realização de busca e seleção de livros / users, criação de empréstimos e devolução
       Possui um botão para pesquisa, uma tabela para mostragem de resultados e um botão para gerenciar empréstimos e devolução */

    private CadastroPublisher livroPublisher, usuarioPublisher;
    private final LivroController livroController;
    private final UsuarioController usuarioController;
    private EmprestimoPublisher emprestimoPublisher;
    private final EmprestimoController emprestimoController;
    private int IDLivroSelecionado = -1;
    private int IDUsuarioSelecionado = -1;
    private boolean pesquisarPorUsuario = false;
    private boolean mostrarEmprestimos = false;

    public TelaPesquisa(CadastroPublisher livroPublisher, CadastroPublisher usuarioPublisher, EmprestimoPublisher emprestimoPublisher,
                        LivroController livroController, UsuarioController usuarioController, EmprestimoController emprestimoController) {
        super("Empréstimos");

        this.livroPublisher = livroPublisher;
        livroPublisher.subscribe(this);
        this.livroController = livroController;
        livroController.setView(this);

        this.usuarioPublisher = usuarioPublisher;
        usuarioPublisher.subscribe(this);
        this.usuarioController = usuarioController;
        usuarioController.setView(this);

        this.emprestimoPublisher = emprestimoPublisher;
        emprestimoPublisher.subscribe(this);
        this.emprestimoController = emprestimoController;
        emprestimoController.setView(this);

        prepararCamposCadastro();

        //Botões para testes
        //Botão Excluir de Teste
        JLabel debug = new JLabel("Para Testes:");
        panelHeader.add(debug);

        JButton btnHeader3 = new JButton("ExcluirEmp");
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
        //Remover aqui!

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
                /* System.out.println("IDSelecionado: "+IDSelecionado+
                        "\nIDLivroSelecionado: "+IDLivroSelecionado+
                        "\nIDUsuarioSelecionado: "+IDUsuarioSelecionado); */

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

        //Evento para desconectar a view, removendo-a da lista de listeners quando fechada
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                desconectar();
            }
        });
    }

    /* Override do método para seleção da classe TelaBase para que possamos distinguir os ids */
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
        if(mostrarEmprestimos) limparIDSelecao();
        else if(pesquisarPorUsuario) IDUsuarioSelecionado = id;
        else IDLivroSelecionado = id;
        txtFieldCampo1.setText(campo1);
        txtFieldCampo2.setText(campo2);
        txtFieldCampo3.setText(campo3);
        txtFieldCampo4.setText(campo4);
        txtFieldCampo5.setText(campo5);
        radioBtn.setSelected(campo6);

        atualizaBotaoEmprestimo();
    }

    //Prepara labels, campos e botões dependendo da operação
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

            //Atualiza estado do botão Busca
            btn1.setEnabled(true);
            //Atualiza estado do botão Devolver
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

            //Atualiza estado do botão Busca
            btn1.setEnabled(false);
            //Atualiza estado do botão Devolução
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

            //Atualiza estado do botão Busca
            btn1.setEnabled(true);
            //Atualiza estado do botão Devolução
            btn4.setEnabled(false);
        }

        recarregarTabela();
    }

    private void recarregarTabela() {
        //Atualização da visualização da tabela
        tableModel.setRowCount(0);
        if (pesquisarPorUsuario) {
            List<Usuario> usuarios = usuarioController.getUsuarios();
            for (Usuario usuario : usuarios) {
                tableModel.addRow(new Object[]{usuario.getID(), usuario.getNome(), usuario.getSenha(),
                        usuario.getCPF(), usuario.getRG(), usuario.getEmail(), usuario.isAdmin()});
            }
        }else if(mostrarEmprestimos){
            List<Emprestimo> emprestimos = emprestimoController.getEmprestimos();
            for (Emprestimo emprestimo : emprestimos) {
                tableModel.addRow(new Object[]{emprestimo.getID(), emprestimo.getLivro().getTitulo(), emprestimo.getUsuario().getNome(),
                        emprestimo.getDataEmprestimo(), emprestimo.getDataPrevistaDevolucao(),
                        emprestimo.getDataRealDevolucao(), emprestimo.isDevolvido()});
            }
        }else{
            List<Livro> livros = livroController.getLivros();
            for (Livro livro : livros) {
                tableModel.addRow(new Object[]{livro.getID(), livro.getTitulo(), livro.getCategoria(),
                        livro.getAutor(), livro.getISBN(), livro.getPrazoDeEntrega(), livro.isDisponivel()});
            }
        }
        atualizaBotaoEmprestimo();
        tableModel.fireTableDataChanged();
        IDSelecionado = -1; //Resetamos o ID selecionado para evitar erros
    }

    //Método para atualização do botão de empréstimo: Disponível somente se um livro e um usuário foram selecionados
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

        /* System.out.println("IDSelecionado: "+IDSelecionado+
                "\nIDLivroSelecionado: "+IDLivroSelecionado+
                "\nIDUsuarioSelecionado: "+IDUsuarioSelecionado); */

        //Preenche campos "Título" e "Nome" com as informações do livro e user selecionados
        if(IDLivroSelecionado != -1) {
            txtFieldCampo1.setText(livroController.buscarLivroPorID(IDLivroSelecionado).getTitulo());
        }
        if(IDUsuarioSelecionado != -1){
            txtFieldCampo2.setText(usuarioController.buscarUsuarioPorID(IDUsuarioSelecionado).getNome());
        }
    }

    //Método para preenchimento dos campos para realização de um empréstimo
    //Realiza a coleta da data atual e sua soma com o prazo de entrega para obter dtaEmprestimo e dtaPrevistaDeEntrega
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

    //Método para testes...
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

        limparIDSelecao();

        emprestimoController.adicionaEmprestimo(dados);
    }

    private void devolver(){
        limparIDSelecao();
        atualizaBotaoEmprestimo();
        limparCampos();

        emprestimoController.editarEmprestimo(IDSelecionado);
    }

    private void limparIDSelecao(){
        IDLivroSelecionado = -1;
        IDUsuarioSelecionado = -1;
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
                        usuario.getCPF(), usuario.getRG(), usuario.getEmail(), usuario.isAdmin()});
            }
        }else {
            for (Livro livro : (List<Livro>)resultado) {
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

    @Override
    public void open() {
        this.setVisible(true);
    }

    private void desconectar(){
        livroPublisher.unsubscribe(this);
        usuarioPublisher.unsubscribe(this);
        emprestimoPublisher.unsubscribe(this);
    }
}
