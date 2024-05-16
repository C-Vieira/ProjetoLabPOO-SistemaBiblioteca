import java.util.List;

public interface UsuarioListener {
    void atualizaDados();
    void mostrarResultados(List<Usuario> resultado);
    void mostrarMensagemDeErro(String mensagem);
}
