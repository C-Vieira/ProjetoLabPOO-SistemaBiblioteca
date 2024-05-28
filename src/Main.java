import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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