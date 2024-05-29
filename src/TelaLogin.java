import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {
    private JLabel lblUsuarioID;
    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JTextField txtFieldUsuarioID;
    private JTextField txtFieldUsuario;
    private JPasswordField passFieldSenha;
    private JButton btnLogin;

    private final UsuarioDAO usuarioDAO;
    private final UsuarioController usuarioController;

    public TelaLogin() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 250);
        setLayout(new GridLayout(4, 2));

        usuarioDAO = new UsuarioDAO();
        usuarioController = new UsuarioController(this, usuarioDAO);

        lblUsuarioID = new JLabel("ID:");
        lblUsuario = new JLabel("Usuario:");
        lblSenha = new JLabel("Senha:");
        txtFieldUsuarioID = new JTextField();
        txtFieldUsuario = new JTextField();
        passFieldSenha = new JPasswordField();
        btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        add(lblUsuarioID);
        add(txtFieldUsuarioID);
        add(lblUsuario);
        add(txtFieldUsuario);
        add(lblSenha);
        add(passFieldSenha);
        add(new JLabel(""));
        add(btnLogin);
    }

    public void login() {
        String usuarioId = txtFieldUsuarioID.getText();
        String nomeUsuario = txtFieldUsuario.getText();
        String senha = passFieldSenha.getText();

        if(usuarioController.login(usuarioId, nomeUsuario, senha)) {
            TelaPrincipal main = new TelaPrincipal();
            main.setVisible(true);
        }
        txtFieldUsuarioID.setText("");
        txtFieldUsuario.setText("");
        passFieldSenha.setText("");
    }
}
