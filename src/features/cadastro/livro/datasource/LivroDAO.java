package features.cadastro.livro.datasource;

import features.cadastro.datasource.CadastroListener;
import features.cadastro.datasource.CadastroPublisher;
import features.cadastro.livro.model.Livro;
import infrastructure.DatabaseManager;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO implements LivroDataBase, CadastroPublisher {
    private final List<CadastroListener> livroListeners = new ArrayList<>();

    @Override
    public void subscribe(CadastroListener livroListener) {
        livroListeners.add(livroListener);
    }

    @Override
    public void unsubscribe(CadastroListener listener) {
        livroListeners.remove(listener);
    }

    private void notificarLivros() {
        for (CadastroListener livroListener : livroListeners) {
            livroListener.atualizaDados();
        }
    }

    private void notificarErro(String message){
        for (CadastroListener livroListener : livroListeners) {
            livroListener.mostrarMensagemDeErro(message);
        }
    }

    private void notificarBusca(List<Livro> livros){
        for (CadastroListener livroListener : livroListeners) {
            livroListener.mostrarResultados(livros);
        }
    }

    @Override
    public void inserirLivro(String titulo, String categoria, String autor, String ISBN, String prazoDeEntrega, boolean disponivel) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var livro = new Livro(titulo, categoria, autor, ISBN, prazoDeEntrega, disponivel);
                session.persist(livro);
            });
            System.out.println("Livro inserido com sucesso");
            notificarLivros();
        } catch (Exception e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    @Override
    public Livro buscaLivroPorID(int livroID){
        List<Livro> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Livro> query = session.createSelectionQuery("from Livro where id = :livroID", Livro.class);
                query.setParameter("livroID", livroID);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Livro não encontrado...");
                }
                return query.getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getFirst();
    }

    @Override
    public void buscarLivroPorTitulo(String livroTitulo) {
        List<Livro> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Livro> query = session.createSelectionQuery("from Livro where titulo = :livroTitulo", Livro.class);
                query.setParameter("livroTitulo", livroTitulo);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Livro não encontrado...");
                    return getLivros();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buscarLivroPorCategoria(String livroCategoria) {
        List<Livro> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Livro> query = session.createSelectionQuery("from Livro where categoria = :livroCategoria", Livro.class);
                query.setParameter("livroCategoria", livroCategoria);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Livro não encontrado...");
                    return getLivros();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buscarLivroPorAutor(String livroAutor) {
        List<Livro> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Livro> query = session.createSelectionQuery("from Livro where autor = :livroAutor", Livro.class);
                query.setParameter("livroAutor", livroAutor);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Livro não encontrado...");
                    return getLivros();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buscarLivroPorISBN(String livroISBN) {
        List<Livro> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Livro> query = session.createSelectionQuery("from Livro where ISBN = :livroISBN", Livro.class);
                query.setParameter("livroISBN", livroISBN);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Livro não encontrado...");
                    return getLivros();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editarLivro(int livroID, String titulo, String categoria, String autor, String isbn, String prazo, boolean disponibilidade) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var livro = session.get(Livro.class, livroID);
                livro.setTitulo(titulo);
                livro.setCategoria(categoria);
                livro.setAutor(autor);
                livro.setISBN(isbn);
                livro.setPrazoDeEntrega(prazo);
                livro.setDisponibilidade(disponibilidade);
                session.persist(livro);
            });
            System.out.println("Livro editado com sucesso");
            notificarLivros();
        } catch (Exception e) {
            System.out.println("Erro ao editar livro: " + e.getMessage());
        }
    }

    @Override
    public void excluirLivro(int livroID) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                Query query = session.createQuery("delete from Livro where id = :livroID");
                query.setParameter("livroID", livroID);
                query.executeUpdate();

            });
            System.out.println("Livro excluído com sucesso");
            notificarLivros();
        } catch (Exception e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
        }
    }

    @Override
    public List<Livro> getLivros() {
        List<Livro> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Livro", Livro.class).getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
