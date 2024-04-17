public class Livro {

    /*  Classe que define um livro e seus dados básicos
        Contém os métodos de acesso (get, set) necessários */

    private static int IDcount = 0;
    private int ID;
    private String titulo;
    private String categoria;
    private String autor;
    private String ISBN;
    private int prazoDeEntrega;
    private boolean disponivel;

    public Livro(String titulo, String categoria, String autor, String ISBN, int prazoDeEntrega, boolean disponivel) {
        this.ID = Livro.IDcount;
        Livro.IDcount++;
        this.titulo = titulo;
        this.categoria = categoria;
        this.autor = autor;
        this.ISBN = ISBN;
        this.prazoDeEntrega = prazoDeEntrega;
        this.disponivel = disponivel;
    }

    public int getID() {
        return this.ID;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public int getPrazoDeEntrega() {
        return this.prazoDeEntrega;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPrazoDeEntrega(int prazoDeEntrega) {
        this.prazoDeEntrega = prazoDeEntrega;
    }

    public void setDisponibilidade(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
