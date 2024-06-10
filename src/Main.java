import di.ServiceLocator;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        /* Ponto de entrada da aplicação */

        SwingUtilities.invokeLater(() -> {
            ServiceLocator.getInstance().getTelaLogin().open();
        });
    }
}