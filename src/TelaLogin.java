import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame implements ActionListener {
    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JTextField txtFieldUsuario;
    private JPasswordField passFieldSenha;
    private JButton btnLogin;
    private UsuarioBaseDeDados baseDeDados  = new UsuarioBaseDeDados();

    public TelaLogin() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 250);
        setLayout(new GridLayout(3, 2));

        lblUsuario = new JLabel("Usuario:");
        lblSenha = new JLabel("Senha:");
        txtFieldUsuario = new JTextField();
        passFieldSenha = new JPasswordField();
        btnLogin = new JButton("Login");

        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(this);

        add(lblUsuario);
        add(txtFieldUsuario);
        add(lblSenha);
        add(passFieldSenha);
        add(new JLabel(""));
        add(btnLogin);

        Usuario admin = new Usuario(0,"admin", "admin", " ", " ",
                " ", true);
        Usuario caio = new Usuario(1,"caio", "123", " ", " ",
                " ", false);

        baseDeDados.AdicionarUsuario(admin);
        baseDeDados.AdicionarUsuario(caio);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("login")) {
            String nomeUsuario = txtFieldUsuario.getText();
            String senha = passFieldSenha.getText();
            Usuario usuario = baseDeDados.BuscarUsuario(nomeUsuario);

            if(nomeUsuario != null){
                if(usuario.VerificaSenha(senha)){
                    System.out.println("Usuário Autenticado");
                    UsuarioBaseDeDados.setUsuarioAtual(nomeUsuario);

                    TelaPrincipal main = new TelaPrincipal();
                    main.setVisible(true);
                }
            }else {
                System.out.println("Usuario Inválido");
            }
            txtFieldUsuario.setText("");
            passFieldSenha.setText("");
        }
    }
}
