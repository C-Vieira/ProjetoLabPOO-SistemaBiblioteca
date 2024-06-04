import java.util.List;

public interface LivroDataBase {
    void inserirLivro(String titulo, String categoria, String autor, String ISBN, String prazoDeEntrega, boolean disponivel);
    Livro buscaLivroPorID(int livroID);
    void buscarLivroPorTitulo(String livroTitulo);
    void buscarLivroPorCategoria(String livroCategoria);
    void buscarLivroPorAutor(String livroAutor);
    void buscarLivroPorISBN(String livroISBN);
    void editarLivro(int livroID, String titulo, String categoria, String autor, String isbn, String prazo, boolean disponibilidade);
    void excluirLivro(int livroID);
    List<Livro> getLivros();
}
