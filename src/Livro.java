import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Livro {

    /*  Classe que define um livro e seus dados básicos
        Contém os métodos de acesso (get, set) necessários */

    @Id
    //@Column(name = "livro_id")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int ID;
    private String titulo;
    private String categoria;
    private String autor;
    private String ISBN;
    private String prazoDeEntrega;
    private boolean disponivel;

    public Livro() {}

    public Livro(int id, String titulo, String categoria, String autor, String ISBN, String prazoDeEntrega, boolean disponivel) {
        this.ID = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.autor = autor;
        this.ISBN = ISBN;
        this.prazoDeEntrega = prazoDeEntrega;
        this.disponivel = disponivel;
    }

    public Livro(String titulo, String categoria, String autor, String ISBN, String prazoDeEntrega, boolean disponivel) {
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

    public String getPrazoDeEntrega() {
        return this.prazoDeEntrega;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public void setID(int id) {
        this.ID = id;
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

    public void setPrazoDeEntrega(String prazoDeEntrega) {
        this.prazoDeEntrega = prazoDeEntrega;
    }

    public void setDisponibilidade(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
