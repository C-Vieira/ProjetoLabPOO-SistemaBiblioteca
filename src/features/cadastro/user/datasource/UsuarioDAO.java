package features.cadastro.user.datasource;

import features.cadastro.datasource.CadastroListener;
import features.cadastro.datasource.CadastroPublisher;
import features.cadastro.user.model.Usuario;
import infrastructure.DatabaseManager;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements UsuarioDataBase, CadastroPublisher {
    private static Usuario usuarioAtual;
    private final List<CadastroListener> usuarioListeners = new ArrayList<>();

    public static Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    @Override
    public void setUsuarioAtual(Usuario usuario) {
        usuarioAtual = usuario;
    }

    @Override
    public void subscribe(CadastroListener usuarioListener) {
        usuarioListeners.add(usuarioListener);
    }

    @Override
    public void unsubscribe(CadastroListener listener) {
        usuarioListeners.remove(listener);
    }

    private void notificarUsuarios() {
        for (CadastroListener usuarioListener : usuarioListeners) {
            usuarioListener.atualizaDados();
        }
    }

    private void notificarErro(String message){
        for (CadastroListener usuarioListener : usuarioListeners) {
            usuarioListener.mostrarMensagemDeErro(message);
        }
    }

    private void notificarBusca(List<Usuario> usuarios){
        for (CadastroListener usuarioListener : usuarioListeners) {
            usuarioListener.mostrarResultados(usuarios);
        }
    }

    @Override
    public boolean verificaSenha(Usuario usuario, String senha){
        if (usuario.getSenha().equals(senha)) return true;
        else return false;
    }

    @Override
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

    @Override
    public Usuario buscaUsuarioPorID(int usuarioID){
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Usuario> query = session.createSelectionQuery("from Usuario where id = :usuarioID", Usuario.class);
                query.setParameter("usuarioID", usuarioID);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Usuário não encontrado...");
                }
                return query.getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getFirst();
    }

    @Override
    public void buscarUsuarioPorNome(String nomeUsuario) {
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Usuario> query = session.createSelectionQuery("from Usuario where nome = :nomeUsuario", Usuario.class);
                query.setParameter("nomeUsuario", nomeUsuario);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Usuário não encontrado...");
                    return getUsuarios();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buscarUsuarioPorEmail(String emailUsuario) {
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Usuario> query = session.createSelectionQuery("from Usuario where email = :emailUsuario", Usuario.class);
                query.setParameter("emailUsuario", emailUsuario);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Usuário não encontrado...");
                    return getUsuarios();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buscarUsuarioPorCPF(String cpfUsuario) {
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Usuario> query = session.createSelectionQuery("from Usuario where CPF = :cpfUsuario", Usuario.class);
                query.setParameter("cpfUsuario", cpfUsuario);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Usuário não encontrado...");
                    return getUsuarios();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buscarUsuarioPorRG(String rgUsuario) {
        List<Usuario> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                SelectionQuery<Usuario> query = session.createSelectionQuery("from Usuario where RG = :rgUsuario", Usuario.class);
                query.setParameter("rgUsuario", rgUsuario);
                if (query.getResultList().isEmpty()) {
                    notificarErro("Usuário não encontrado...");
                    return getUsuarios();
                }
                return query.getResultList();
            });
            notificarBusca(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
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

    @Override
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

