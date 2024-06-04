public class ServiceLocator {
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
