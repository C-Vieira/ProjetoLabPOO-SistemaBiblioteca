import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        DatabaseManager.createSessionFactory();

        SwingUtilities.invokeLater(() -> {
            //TelaLogin login = new TelaLogin();
            //login.setVisible(true);

            TelaPrincipal main = new TelaPrincipal();
            main.setVisible(true);
        });
    }
}