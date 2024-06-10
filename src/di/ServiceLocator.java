package di;

import features.cadastro.datasource.CadastroPublisher;
import features.cadastro.livro.datasource.LivroDAO;
import features.cadastro.livro.datasource.LivroDataBase;
import features.cadastro.livro.presentation.LivroController;
import features.cadastro.livro.presentation.LivroControllerImpl;
import features.cadastro.presentation.BaseView;
import features.cadastro.presentation.TelaCadastro;
import features.cadastro.presentation.TelaPesquisa;
import features.cadastro.presentation.TelaPrincipal;
import features.cadastro.user.datasource.UsuarioDAO;
import features.cadastro.user.datasource.UsuarioDataBase;
import features.cadastro.user.presentation.TelaLogin;
import features.cadastro.user.presentation.UsuarioController;
import features.cadastro.user.presentation.UsuarioControllerImpl;
import features.emprestimo.datasource.EmprestimoDAO;
import features.emprestimo.datasource.EmprestimoDataBase;
import features.emprestimo.datasource.EmprestimoPublisher;
import features.emprestimo.presentation.EmprestimoController;
import features.emprestimo.presentation.EmprestimoControllerImpl;

public class ServiceLocator {

    /* Classe responsável pela criação de dependências e componentes compartilhados entre outras classes */

    //Instância para o singleton
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }

    private LivroDAO livroDAO;

    public LivroDAO getLivroDAO() {
        if (livroDAO == null) {
            livroDAO = new LivroDAO();
        }

        return livroDAO;
    }

    public LivroDataBase getLivroDataBase() {
        return getLivroDAO();
    }

    private UsuarioDAO usuarioDAO;

    public UsuarioDAO getUsuarioDAO() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }

        return usuarioDAO;
    }

    public UsuarioDataBase getUsuarioDatabase(){
        return getUsuarioDAO();
    }

    private EmprestimoDAO emprestimoDAO;

    public EmprestimoDAO getEmprestimoDAO() {
        if (emprestimoDAO == null) {
            emprestimoDAO = new EmprestimoDAO();
        }

        return emprestimoDAO;
    }

    public EmprestimoDataBase getEmprestimoDatabase(){
        return getEmprestimoDAO();
    }

    public CadastroPublisher getLivroPublisher(){
        return getLivroDAO();
    }

    public CadastroPublisher getUsuarioPublisher(){
        return getUsuarioDAO();
    }

    public EmprestimoPublisher getEmprestimoPublisher(){
        return getEmprestimoDAO();
    }

    public LivroController getLivroController(){
        return new LivroControllerImpl(getLivroDataBase());
    }

    public UsuarioController getUsuarioController(){
        return new UsuarioControllerImpl(getUsuarioDatabase());
    }

    public EmprestimoController getEmprestimoController(){
        return new EmprestimoControllerImpl(getEmprestimoDatabase());
    }

    public BaseView getTelaLogin(){
        return new TelaLogin(getUsuarioController());
    }

    public BaseView getTelaPrincipal(){
        return new TelaPrincipal();
    }

    public BaseView getTelaCadastro(){
        return new TelaCadastro(getLivroPublisher(), getUsuarioPublisher(), getLivroController(), getUsuarioController());
    }

    public BaseView getTelaPesquisa(){
        return new TelaPesquisa(getLivroPublisher(), getUsuarioPublisher(), getEmprestimoPublisher(),
                getLivroController(), getUsuarioController(), getEmprestimoController());
    }
}
