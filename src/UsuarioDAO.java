import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final List<CadastroListener> usuarioListeners = new ArrayList<>();

    public void subscribe(CadastroListener usuarioListener) {
        usuarioListeners.add(usuarioListener);
    }

    private void notificarUsuarios() {
        for (CadastroListener usuarioListener : usuarioListeners) {
            usuarioListener.atualizaDados();
        }
    }

    private void notificarErro(){
        for (CadastroListener usuarioListener : usuarioListeners) {
            usuarioListener.mostrarMensagemDeErro("Usuário não encontrado...");
        }
    }

    private void notificarBusca(List<Usuario> usuarios){
        for (CadastroListener usuarioListener : usuarioListeners) {
            usuarioListener.mostrarResultados(usuarios);
        }
    }

    public void inserirUsuario(String nome, String senha, String CPF, String RG, String email, boolean isAdmin) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var usuario = new Usuario(nome, senha, CPF, RG, email, isAdmin);
                session.persist(usuario);
            });
            System.out.println("Usuário inserido com sucesso");
            notificarUsuarios();
        } catch (Exception e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public void buscarUsuarioPorNome(String nomeUsuario) {
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Usuario> query = session.createSelectionQuery("from Usuario where nome = :nomeUsuario", Usuario.class);
                query.setParameter("nomeUsuario", nomeUsuario);
                if (query.getResultList().isEmpty()) {
                    notificarErro();
                    return getUsuarios();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscarUsuarioPorEmail(String emailUsuario) {
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Usuario> query = session.createSelectionQuery("from Usuario where email = :emailUsuario", Usuario.class);
                query.setParameter("emailUsuario", emailUsuario);
                if (query.getResultList().isEmpty()) {
                    notificarErro();
                    return getUsuarios();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscarUsuarioPorCPF(String cpfUsuario) {
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Usuario> query = session.createSelectionQuery("from Usuario where CPF = :cpfUsuario", Usuario.class);
                query.setParameter("cpfUsuario", cpfUsuario);
                if (query.getResultList().isEmpty()) {
                    notificarErro();
                    return getUsuarios();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscarUsuarioPorRG(String rgUsuario) {
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Usuario> query = session.createSelectionQuery("from Usuario where RG = :rgUsuario", Usuario.class);
                query.setParameter("rgUsuario", rgUsuario);
                if (query.getResultList().isEmpty()) {
                    notificarErro();
                    return getUsuarios();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarUsuario(int usuarioID, String nome, String senha, String cpf, String rg, String email, boolean isAdmin) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var usuario = session.get(Usuario.class, usuarioID);
                usuario.setNome(nome);
                usuario.setSenha(senha);
                usuario.setCPF(cpf);
                usuario.setRG(rg);
                usuario.setEmail(email);
                usuario.setAdmin(isAdmin);
                session.persist(usuario);
            });
            System.out.println("Usuário editado com sucesso");
            notificarUsuarios();
        } catch (Exception e) {
            System.out.println("Erro ao editar usuário: " + e.getMessage());
        }
    }

    public void excluirUsuario(int usuarioID) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                Query query = session.createQuery("delete from Usuario where id = :usuarioID");
                query.setParameter("usuarioID", usuarioID);
                query.executeUpdate();

            });
            System.out.println("Usuário excluído com sucesso");
            notificarUsuarios();
        } catch (Exception e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Usuario", Usuario.class).getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

