import java.util.ArrayList;

//  !   !   !   !   !   !  //

// !CLASSE DESCONTINUADA!  //

//  !   !   !   !   !   !  //

public class UsuarioBaseDeDados {
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private static String usuarioAtual;

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarioAtual(String usuario) {
        usuarioAtual = usuario;
    }

    public static String getUsuarioAtual() {
        return usuarioAtual;
    }

    public void AdicionarUsuario(Usuario usuario){ //Create
        usuarios.add(usuario);
        System.out.println("Usuário adicionado");
    }

    public Usuario BuscarUsuario(String usrName){ //Read
        for(Usuario u : usuarios){
            if(u.getNomeUsuario().equals(usrName)){
                System.out.println("Usuário encontrado");
                return u;
            }
        }
        System.out.println("Usuário não encontrado");
        return null;
    }

    public void EditarUsuario(Usuario usuario){ //Update
        for(Usuario u : usuarios){
            if(u.getNomeUsuario().equals(usuario.getNomeUsuario())){
                System.out.println("Usuário editado");
            }
        }
        System.out.println("Usuário não encontrado");
    }
    public void ExcluirUsuario(Usuario usuario){ //Delete
        for(Usuario u : usuarios){
            if(u.getNomeUsuario().equals(usuario.getNomeUsuario())){
                usuarios.remove(u);
                System.out.println("Usuário excluído");
                return;
            }
        }
        System.out.println("Usuário não encontrado");
    }
}
