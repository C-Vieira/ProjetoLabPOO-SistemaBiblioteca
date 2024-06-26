package features.emprestimo.model;

import features.cadastro.livro.model.Livro;
import features.cadastro.user.model.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    /*  Classe que define um empréstimo e seus dados básicos
        Contém os métodos de acesso (get, set) necessários */

    @Id
    @SequenceGenerator(name = "seq3", sequenceName = "seq3", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq3")
    private int ID;

    @ManyToOne
    @JoinColumn(name = "livro_ID")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "usuario_ID")
    private Usuario usuario;

    private String dataEmprestimo;
    private String dataPrevistaDevolucao;
    private String dataRealDevolucao;
    private boolean devolvido;

    public Emprestimo() {}

    public Emprestimo(int ID, Livro livro, Usuario usuario, String dataEmprestimo, String dataPrevistaDevolucao, String dataRealDevolucao, boolean devolvido) {
        this.ID = ID;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataRealDevolucao = dataRealDevolucao;
        this.devolvido = devolvido;
    }

    public Emprestimo(Livro livro, Usuario usario, String dataEmprestimo, String dataPrevistaDevolucao, String dataRealDevolucao, boolean devolvido) {
        this.livro = livro;
        this.usuario = usario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataRealDevolucao = dataRealDevolucao;
        this.devolvido = devolvido;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(String dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public String getDataRealDevolucao() {
        return dataRealDevolucao;
    }

    public void setDataRealDevolucao(String dataRealDevolucao) {
        this.dataRealDevolucao = dataRealDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}
