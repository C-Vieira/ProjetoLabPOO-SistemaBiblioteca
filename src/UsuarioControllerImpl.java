import javax.swing.*;
import java.util.List;

public class UsuarioControllerImpl implements UsuarioController {
    private BaseView usuarioView;
    private final UsuarioDataBase usuarioDataBase;

    public UsuarioControllerImpl(UsuarioDataBase usuarioDataBase) {
        this.usuarioDataBase = usuarioDataBase;
    }

    public boolean login(String usuarioId, String nomeUsuario, String senha) {
        try {
            Usuario usuario = usuarioDataBase.buscaUsuarioPorID(Integer.parseInt(usuarioId));
            if(usuario != null && nomeUsuario != null && usuarioDataBase.verificaSenha(usuario, senha)){
                System.out.println("Usuário Autenticado");
                usuarioDataBase.setUsuarioAtual(usuario);
                return true;
            }else {
                System.out.println("Usuário Inválido");
                usuarioView.mostrarMensagemDeErro("Usuário Inválido");
                //JOptionPane.showMessageDialog(usuarioView, "Usuário Inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception e) {
            System.out.println("Usuário Inválido");
            usuarioView.mostrarMensagemDeErro("Usuário Inválido");
            //JOptionPane.showMessageDialog(usuarioView, "Usuário Inválido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public void setView(BaseView view) {
        this.usuarioView = view;
    }

    @Override
    public void adicionaUsuario(Object[] dados){
        String nome = dados[0].toString();
        String senha = dados[1].toString();
        String cpf = dados[2].toString();
        String rg = dados[3].toString();
        String email = dados[4].toString();
        Boolean isAdmin = Boolean.parseBoolean(dados[5].toString());

        if(nome.isEmpty() || senha.isEmpty() || cpf.isEmpty() || rg.isEmpty() || email.isEmpty()) {
            System.out.println("Erro ao Adicionar Usuário");
        }else{
            usuarioDataBase.inserirUsuario(nome, senha, cpf, rg, email, isAdmin);
        }
    }

    @Override
    public Usuario buscarUsuarioPorID(int usuarioID){
        return  usuarioDataBase.buscaUsuarioPorID(usuarioID);
    }

    @Override
    public void buscarUsuario(Object[] dados){
        String nome = dados[0].toString();
        String email = dados[4].toString();
        String cpf = dados[2].toString();
        String rg = dados[3].toString();

        //Se o campo estiver preenchido, pesquisamos por este dado, se não, tentamos o próximo
        if(!nome.isEmpty()) {
            usuarioDataBase.buscarUsuarioPorNome(nome);
        } else if (!email.isEmpty()) {
            usuarioDataBase.buscarUsuarioPorEmail(email);
        } else if (!cpf.isEmpty()) {
            usuarioDataBase.buscarUsuarioPorCPF(cpf);
        } else if (!rg.isEmpty()) {
            usuarioDataBase.buscarUsuarioPorRG(rg);
        }else{
            usuarioView.mostrarMensagemDeErro("Usuário não encontrado...");
            //usuarioView.atualizaDados();
            System.out.println("Usuário não encontrado");
        }
    }

    @Override
    public void editarUsuario(int usuarioID, Object[] dados){
        String nome = dados[0].toString();
        String senha = dados[1].toString();
        String cpf = dados[2].toString();
        String rg = dados[3].toString();
        String email = dados[4].toString();
        Boolean isAdmin = Boolean.parseBoolean(dados[5].toString());

        if(nome.isEmpty() || senha.isEmpty() || cpf.isEmpty() || rg.isEmpty() || email.isEmpty()) {
            System.out.println("Erro ao Editar Usuário");
        }
        usuarioDataBase.editarUsuario(usuarioID, nome, senha, cpf, rg, email, isAdmin); //Atualiza informações de um certo livro por ID

    }

    @Override
    public void excluirUsuario(int usuarioID, Object[] dados){
        if(usuarioID > -1) {
            usuarioDataBase.excluirUsuario(usuarioID);
        }else{
            System.out.println("Usuário não encontrado");
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioDataBase.getUsuarios();
    }
}

