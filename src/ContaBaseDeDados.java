import java.util.ArrayList;

public class ContaBaseDeDados { //TESTE
    private ArrayList<Conta> contas = new ArrayList<Conta>();

    public void AdicionarConta(Conta conta){
        contas.add(conta);
        System.out.println("Conta adicionada");
    }
    public void EditarConta(Conta conta){
        System.out.println("Conta editada");
    }
    public void ExcluirConta(Conta conta){
        System.out.println("Conta excluída");
    }
}
