package features.emprestimo.datasource;

import features.cadastro.livro.model.Livro;
import features.cadastro.user.model.Usuario;
import features.emprestimo.model.Emprestimo;

import java.util.List;

public interface EmprestimoDataBase {
    void inserirEmprestimo(Livro livro, Usuario usuario, String dtaEmprestimo, String dtaPrevistaDevolucao, String dtaRealDevolucao, boolean devolvido);
    void editarEmprestimo(int emprestimoID, String dtaRealDevolucao, boolean devolvido);
    Emprestimo buscarEmprestimoPorID(int emprestimoID);
    void excluirEmprestimo(int emprestimoID);
    List<Emprestimo> getEmprestimos();
}
