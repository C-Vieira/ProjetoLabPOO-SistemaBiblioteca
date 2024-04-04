public class Usuario { //TESTE
    private int ID;
    private String nome;
    private String CPF;
    private String RG;
    private String email;
    private boolean isAdmin;
    private Conta conta;

    public Usuario(int ID, String nome, String CPF, String RG, String email, boolean isAdmin, Conta conta){
        this.ID = ID;
        this.nome = nome;
        this.CPF = CPF;
        this.RG = RG;
        this.email = email;
        this.isAdmin = isAdmin;
        this.conta = conta;
    }
    public int getID(){
        return this.ID;
    }
}
