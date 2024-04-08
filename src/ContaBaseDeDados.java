import java.util.ArrayList;

public class ContaBaseDeDados { //TESTE
    private ArrayList<Conta> contas = new ArrayList<Conta>();

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void AdicionarConta(Conta conta){ //Create
        contas.add(conta);
        System.out.println("Conta adicionada");
    }

    public void BuscarConta(String usrName){ //Read
        for(Conta c : contas){
            if(c.getNomeUsuario().equals(usrName)){
                System.out.println("Conta encontrada");
            }
        }
        System.out.println("Conta não encontrada");
    }

    public void EditarConta(Conta conta){ //Update
        for(Conta c : contas){
            if(c.getNomeUsuario().equals(conta.getNomeUsuario())){
                System.out.println("Conta editada");
            }
        }
        System.out.println("Conta não encontrada");
    }
    public void ExcluirConta(Conta conta){ //Delete
        for(Conta c : contas){
            if(c.getNomeUsuario().equals(conta.getNomeUsuario())){
                contas.remove(c);
                System.out.println("Conta excluída");
            }
        }
        System.out.println("Conta não encontrada");
    }
}
