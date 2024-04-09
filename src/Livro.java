public class Livro {
    private int ID;
    private String titulo;
    private String categoria;
    private String autor;
    private String ISBN;
    private boolean disponivel = false;

    public Livro(int id, String titulo, String categoria, String autor, String ISBN) {
        this.ID = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.autor = autor;
        this.ISBN = ISBN;
    }

    public int getID() {
        return this.ID;
    }

    public String getTitulo() {
        return this.titulo;
    }
}
