public class Conta {
    private String nomeUsuario;
    private String senha;

    public Conta(String nomeUsuario, String senha){
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public boolean VerificaSenha(String senha){
        return this.senha.equals(senha);
    }
}
