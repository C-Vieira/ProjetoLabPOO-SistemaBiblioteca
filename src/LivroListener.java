import java.util.List;

public interface LivroListener {
    void atualizaDados();
    void mostrarResultados(List<Livro> resultado);
    void mostrarMensagemDeErro(String mensagem);
}
