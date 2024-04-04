public class Main { //TESTE
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Conta minhaConta = new Conta("caio", "senha123");
        Usuario caio = new Usuario(0,"Caio", "222222222", "3333333333",
                "email@fastmail.com", true, minhaConta);

        ContaBaseDeDados contaBaseDeDados = new ContaBaseDeDados();
        contaBaseDeDados.AdicionarConta(minhaConta);
        System.out.println(caio.getID());
    }
}