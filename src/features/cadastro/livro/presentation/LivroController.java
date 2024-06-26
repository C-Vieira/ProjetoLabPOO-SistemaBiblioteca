package features.cadastro.livro.presentation;

import features.cadastro.livro.model.Livro;
import features.cadastro.presentation.BaseView;

import java.util.List;

public interface LivroController {
    void setView(BaseView view);
    void adicionaLivro(Object[] dados);
    Livro buscarLivroPorID(int livroID);
    void buscarLivro(Object[] dados);
    void editarLivro(int livroId, Object[] dados);
    void excluirLivro(int livroId);
    List<Livro> getLivros();
}
