public class UsuarioController {
    private final TelaCadastroUsuario usuarioView;
    private final UsuarioDAO usuarioDAO;

    public UsuarioController(TelaCadastroUsuario usuarioView, UsuarioDAO usuarioDAO) {
        this.usuarioView = usuarioView;
        this.usuarioDAO = usuarioDAO;
    }

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
            usuarioDAO.inserirUsuario(nome, senha, cpf, rg, email, isAdmin);
        }
    }

    public void buscarUsuario(Object[] dados){
        String nome = dados[0].toString();
        String email = dados[4].toString();
        String cpf = dados[2].toString();
        String rg = dados[3].toString();

        //Se o campo estiver preenchido, pesquisamos por este dado, se não, tentamos o próximo
        if(!nome.isEmpty()) {
            usuarioDAO.buscarUsuarioPorNome(nome);
        } else if (!email.isEmpty()) {
            usuarioDAO.buscarUsuarioPorEmail(email);
        } else if (!cpf.isEmpty()) {
            usuarioDAO.buscarUsuarioPorCPF(cpf);
        } else if (!rg.isEmpty()) {
            usuarioDAO.buscarUsuarioPorRG(rg);
        }else{
            usuarioView.mostrarMensagemDeErro("Usuário não encontrado...");
            usuarioView.atualizaDados();
            System.out.println("Usuário não encontrado");
        }
    }

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
        usuarioDAO.editarUsuario(usuarioID, nome, senha, cpf, rg, email, isAdmin); //Atualiza informações de um certo livro por ID

    }

    public void excluirUsuario(int usuarioID, Object[] dados){
        if(usuarioID > -1) {
            usuarioDAO.excluirUsuario(usuarioID);
        }else{
            System.out.println("Usuário não encontrado");
        }
    }
}

