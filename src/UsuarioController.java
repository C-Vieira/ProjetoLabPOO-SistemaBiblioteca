import java.util.List;

public interface UsuarioController {
    void setView(BaseView view);
    void adicionaUsuario(Object[] dados);
    Usuario buscarUsuarioPorID(int usuarioID);
    void buscarUsuario(Object[] dados);
    void editarUsuario(int usuarioID, Object[] dados);
    void excluirUsuario(int usuarioID, Object[] dados);
    public boolean login(String usuarioId, String nomeUsuario, String senha);
    List<Usuario> getUsuarios();
}
