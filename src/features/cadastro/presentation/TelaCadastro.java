package features.cadastro.presentation;

import features.cadastro.datasource.CadastroListener;
import features.cadastro.datasource.CadastroPublisher;
import features.cadastro.livro.model.Livro;
import features.cadastro.livro.presentation.LivroController;
import features.cadastro.user.model.Usuario;
import features.cadastro.user.presentation.UsuarioController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class TelaCadastro extends TelaBase implements CadastroListener {

    /* Classe que define uma tela de cadastro, contém todos os campos necessários para criação de um livro ou usuário
       Possui um botão para pesquisa e uma tabela para mostragem de resultados
       Aceita a seleção de linhas na tabela e preenche os campos com os devidos dados para auxílio nas operações de cadastro */

    private CadastroPublisher livroPublisher, usuarioPublisher;
    private final LivroController livroController;
    private final UsuarioController usuarioController;
    private boolean CadastrarUsuario = false;

    public TelaCadastro(CadastroPublisher livroPublisher, CadastroPublisher usuarioPublisher, LivroController livroController, UsuarioController usuarioController) {
        super("Cadastros");

        this.livroPublisher = livroPublisher;
        livroPublisher.subscribe(this);
        this.livroController = livroController;
        livroController.setView(this);

        this.usuarioPublisher = usuarioPublisher;
        usuarioPublisher.subscribe(this);
        this.usuarioController = usuarioController;
        usuarioController.setView(this);

        btnHeader1.setText("Cadastrar Livro");
        btnHeader2.setText("Cadastrar Usuário");

        prepararCamposCadastro();

        btnHeader1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastrarUsuario = false;
                prepararCamposCadastro();
                limparCampos();
            }
        });

        btnHeader2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastrarUsuario = true;
                prepararCamposCadastro();
                limparCampos();
            }
        });

        btn1.setText("Adicionar");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionar();
            }
        });

        btn2.setText("Buscar");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });

        btn3.setText("Editar");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });

        btn4.setText("Excluir");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                desconectar();
            }
        });
    }

    private void prepararCamposCadastro(){
        if(CadastrarUsuario){
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
        }

        recarregarTabela();
    }

    private void recarregarTabela() {
        //Atualização da visualização da tabela
        tableModel.setRowCount(0);
        if (CadastrarUsuario) {
            List<Usuario> usuarios = usuarioController.getUsuarios();
            for (Usuario usuario : usuarios) {
                tableModel.addRow(new Object[]{usuario.getID(), usuario.getNome(), usuario.getSenha(),
                        usuario.getCPF(), usuario.getRG(), usuario.getEmail(), usuario.isAdmin()});
            }
        }else {
            List<Livro> livros = livroController.getLivros();
            for (Livro livro : livros) {
                tableModel.addRow(new Object[]{livro.getID(), livro.getTitulo(), livro.getCategoria(),
                        livro.getAutor(), livro.getISBN(), livro.getPrazoDeEntrega(), livro.isDisponivel()});
            }
        }
        tableModel.fireTableDataChanged();
        IDSelecionado = -1; //Resetamos o ID selecionado para evitar erros
    }

    private void adicionar(){
        if(CadastrarUsuario){
            usuarioController.adicionaUsuario(coletaDados());
        }else {
            livroController.adicionaLivro(coletaDados());
        }
    }

    private void buscar(){
        if(CadastrarUsuario){
            usuarioController.buscarUsuario(coletaDados());
        }else {
            livroController.buscarLivro(coletaDados());
        }
    }

    private void editar(){
        if(CadastrarUsuario){
            usuarioController.editarUsuario(IDSelecionado, coletaDados());
        }else {
            livroController.editarLivro(IDSelecionado, coletaDados());
        }
    }

    private void excluir(){
        if(CadastrarUsuario){
            usuarioController.excluirUsuario(IDSelecionado, coletaDados());
        }else {
            livroController.excluirLivro(IDSelecionado);
        }
    }

    @Override
    public void atualizaDados() {
        recarregarTabela();
        limparCampos();
    }

    @Override
    public <T> void mostrarResultados(List<T> resultado) {
        tableModel.setRowCount(0);
        if (CadastrarUsuario) {
            for (Usuario usuario : (List<Usuario>)resultado) {
                tableModel.addRow(new Object[]{usuario.getID(), usuario.getNome(), usuario.getSenha(),
                        usuario.getCPF(), usuario.getRG(), usuario.getEmail(), usuario.isAdmin()});
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
    public void open() {
        this.setVisible(true);
    }

    private void desconectar(){
        livroPublisher.unsubscribe(this);
        usuarioPublisher.unsubscribe(this);
    }
}