public class Livro {
    private static int ID = 0;
    private String titulo;
    private String categoria;
    private String autor;
    private String ISBN;
    private int prazoDeEntrega;
    private boolean disponivel;

    public Livro(int id, String titulo, String categoria, String autor, String ISBN, int prazoDeEntrega, boolean disponivel) {
        Livro.ID++;
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

    public void setPrazoDeEntrega(int prazoDeEntrega) {
        this.prazoDeEntrega = prazoDeEntrega;
    }

    public void setDisponibilidade(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
