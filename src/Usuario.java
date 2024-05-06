public class Usuario {
    private int ID;
    private String nome;
    private String senha;
    private String CPF;
    private String RG;
    private String email;
    private boolean isAdmin;

    public Usuario(int ID, String nome, String senha, String CPF, String RG, String email, boolean isAdmin){
        this.ID = ID;
        this.nome = nome;
        this.senha = senha;
        this.CPF = CPF;
        this.RG = RG;
        this.email = email;
        this.isAdmin = isAdmin;
    }
    public int getID(){
        return this.ID;
    }

    public String getNomeUsuario() {
        return this.nome;
    }

    public boolean VerificaSenha(String senha){
        return this.senha.equals(senha);
    }
}
