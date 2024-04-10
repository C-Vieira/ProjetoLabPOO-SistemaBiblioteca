import java.util.ArrayList;

public class ContaBaseDeDados {
    private ArrayList<Conta> contas = new ArrayList<Conta>();

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void AdicionarConta(Conta conta){ //Create
        contas.add(conta);
        System.out.println("Conta adicionada");
    }

    public Conta BuscarConta(String usrName){ //Read
        for(Conta c : contas){
            if(c.getNomeUsuario().equals(usrName)){
                System.out.println("Conta encontrada");
                return c;
            }
        }
        System.out.println("Conta não encontrada");
        return null;
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
                return;
            }
        }
        System.out.println("Conta não encontrada");
    }
}
