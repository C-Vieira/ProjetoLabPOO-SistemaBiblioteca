import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmprestimoController {
    private final TelaBase emprestimoView;
    private final EmprestimoDAO emprestimoDAO;

    public EmprestimoController(TelaBase emprestimoView, EmprestimoDAO emprestimoDAO) {
        this.emprestimoView = emprestimoView;
        this.emprestimoDAO = emprestimoDAO;
    }

    public void adicionaEmprestimo(Object[] dados){
        Livro livro = (Livro) dados[0];
        Usuario usuario = (Usuario)dados[1];
        String dtaEmprestimo = dados[2].toString();
        String dtaPrevistaDevolucao = dados[3].toString();
        String dtaRealDevolucao = dados[4].toString();
        Boolean devolvido = Boolean.parseBoolean(dados[5].toString());

        if(livro == null || usuario == null || dtaEmprestimo.isEmpty() || dtaPrevistaDevolucao.isEmpty()) {
            System.out.println("Erro ao Adicionar Empréstimo");
        }else if(!livro.isDisponivel()){
            System.out.println("Livro Indisponível");
            emprestimoView.mostrarMensagemDeErro("Livro Indisponível");
        }else {
            emprestimoDAO.inserirEmprestimo(livro, usuario, dtaEmprestimo, dtaPrevistaDevolucao, dtaRealDevolucao, devolvido);
        }
    }

    public void editarEmprestimo(int emprestimoID){
        if(emprestimoID > -1){
            Calendar agora = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

            emprestimoDAO.editarEmprestimo(emprestimoID , dateFormat.format(agora.getTime()), true);
        }else{
            emprestimoView.mostrarMensagemDeErro("Selecione um emprestimo para devolver");
        }
    }

    public void excluirEmprestimo(int emprestimoID){
        if(emprestimoID > -1) {
            emprestimoDAO.excluirEmprestimo(emprestimoID);
        }else{
            System.out.println("Empréstimo não encontrado");
        }
    }
}
