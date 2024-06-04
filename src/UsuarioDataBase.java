import java.util.List;

public interface UsuarioDataBase {
    void inserirUsuario(String nome, String senha, String CPF, String RG, String email, boolean isAdmin);
    Usuario buscaUsuarioPorID(int usuarioID);
    void buscarUsuarioPorNome(String nomeUsuario);
    void buscarUsuarioPorEmail(String emailUsuario);
    void buscarUsuarioPorCPF(String cpfUsuario);
    void buscarUsuarioPorRG(String rgUsuario);
    void editarUsuario(int usuarioID, String nome, String senha, String cpf, String rg, String email, boolean isAdmin);
    void excluirUsuario(int usuarioID);
    List<Usuario> getUsuarios();
    void setUsuarioAtual(Usuario usuario);
    boolean verificaSenha(Usuario usuario, String senha);
}
