package features.cadastro.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class Usuario {

    /*  Classe que define um usuário e seus dados básicos
        Contém os métodos de acesso (get, set) necessários */

    @Id
    //@Column(name = "user_id")
    @SequenceGenerator(name = "seq2", sequenceName = "seq2", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
    private int ID;
    private String nome;
    private String senha;
    private String CPF;
    private String RG;
    private String email;
    private boolean isAdmin;

    public Usuario() {}

    public Usuario(int ID, String nome, String senha, String CPF, String RG, String email, boolean isAdmin){
        this.ID = ID;
        this.nome = nome;
        this.senha = senha;
        this.CPF = CPF;
        this.RG = RG;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public Usuario(String nome, String senha, String CPF, String RG, String email, boolean isAdmin){
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getNomeUsuario() {
        return this.nome;
    }
}
