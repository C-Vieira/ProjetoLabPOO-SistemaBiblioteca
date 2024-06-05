package features.emprestimo.datasource;

import features.cadastro.datasource.CadastroListener;
import features.cadastro.livro.model.Livro;

public interface EmprestimoListener extends CadastroListener {
    void atualizaDisponibiliade(Livro livro);
}
