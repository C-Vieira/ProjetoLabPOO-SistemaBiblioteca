package features.emprestimo.presentation;

import features.cadastro.presentation.BaseView;
import features.emprestimo.model.Emprestimo;

import java.util.List;

public interface EmprestimoController {
    void setView(BaseView view);
    void adicionaEmprestimo(Object[] dados);
    void editarEmprestimo(int emprestimoID);
    void excluirEmprestimo(int emprestimoID);
    List<Emprestimo> getEmprestimos();
}
