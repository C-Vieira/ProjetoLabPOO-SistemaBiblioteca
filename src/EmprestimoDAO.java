import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO implements EmprestimoDataBase, EmprestimoPublisher {
    private final List<EmprestimoListener> emprestimoListeners = new ArrayList<>();

    @Override
    public void subscribe(EmprestimoListener emprestimoListener) {
        emprestimoListeners.add(emprestimoListener);
    }

    @Override
    public void unsubscribe(EmprestimoListener listener) {
        emprestimoListeners.remove(listener);
    }

    private void notificarEmprestimos() {
        for (EmprestimoListener emprestimoListener : emprestimoListeners) {
            emprestimoListener.atualizaDados();
        }
    }

    private void atualizaDisponibilidade(Livro livro) {
        for (EmprestimoListener emprestimoListener : emprestimoListeners) {
            emprestimoListener.atualizaDisponibiliade(livro);
        }
    }

    private void notificarErro(String message){
        for (EmprestimoListener emprestimoListener : emprestimoListeners) {
            emprestimoListener.mostrarMensagemDeErro(message);
        }
    }

    private void notificarBusca(List<Emprestimo> emprestimos){
        for (EmprestimoListener emprestimoListener : emprestimoListeners) {
            emprestimoListener.mostrarResultados(emprestimos);
        }
    }

    @Override
    public void inserirEmprestimo(Livro livro, Usuario usuario, String dtaEmprestimo, String dtaPrevistaDevolucao, String dtaRealDevolucao, boolean devolvido) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var emprestimo = new Emprestimo(livro, usuario, dtaEmprestimo, dtaPrevistaDevolucao, dtaRealDevolucao, devolvido);
                session.persist(emprestimo);
            });
            System.out.println("Empréstimp criado com sucesso");
            atualizaDisponibilidade(livro);
            notificarEmprestimos();
        } catch (Exception e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
            notificarErro("Erro ao inserir...");
        }
    }

    @Override
    public void editarEmprestimo(int emprestimoID, String dtaRealDevolucao, boolean devolvido) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var emprestimo = session.get(Emprestimo.class, emprestimoID);
                emprestimo.setDataRealDevolucao(dtaRealDevolucao);
                emprestimo.setDevolvido(devolvido);
                session.persist(emprestimo);
            });
            System.out.println("Empréstimo editado com sucesso");
            Emprestimo emprestimo = buscarEmprestimoPorID(emprestimoID);
            atualizaDisponibilidade(emprestimo.getLivro());
            notificarEmprestimos();
        } catch (Exception e) {
            System.out.println("Erro ao editar empréstimo: " + e.getMessage());
        }
    }

    @Override
    public Emprestimo buscarEmprestimoPorID(int emprestimoID) {
        List<Emprestimo> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Emprestimo> query = session.createSelectionQuery("from Emprestimo where id = :emprestimoID", Emprestimo.class);
                query.setParameter("emprestimoID", emprestimoID);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Empréstimo não encontrado...");
                    return getEmprestimos();
                }
                return query.getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getFirst();
    }

    @Override
    public void excluirEmprestimo(int emprestimoID) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                Query query = session.createQuery("delete from Emprestimo where id = :emprestimoID");
                query.setParameter("emprestimoID", emprestimoID);
                query.executeUpdate();

            });
            System.out.println("Empréstimo excluído com sucesso");
            notificarEmprestimos();
        } catch (Exception e) {
            System.out.println("Erro ao excluir empréstimo: " + e.getMessage());
        }
    }

    @Override
    public List<Emprestimo> getEmprestimos() {
        List<Emprestimo> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Emprestimo", Emprestimo.class).getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}