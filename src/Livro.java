public class Livro {
    private int ID;
    private String titulo;
    private String autor;
    private String categoria;
    private String ISBN;
    private boolean disponivel = false;

    public Livro(int id, String titulo, String autor, String categoria, String ISBN) {
        this.ID = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.ISBN = ISBN;
    }

    public int getID() {
        return this.ID;
    }
}
