import di.ServiceLocator;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        SwingUtilities.invokeLater(() -> {
            ServiceLocator.getInstance().getTelaLogin().open();

            //features.cadastro.presentation.TelaPrincipal main = new features.cadastro.presentation.TelaPrincipal();
            //main.setVisible(true);
        });
    }
}