package features.cadastro.datasource;

import java.util.List;

public interface CadastroListener {
    void atualizaDados();
    <T> void mostrarResultados(List<T> resultado);
    void mostrarMensagemDeErro(String mensagem);
}
