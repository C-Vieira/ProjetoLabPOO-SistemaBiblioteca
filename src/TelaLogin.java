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
    private ContaBaseDeDados baseDeDados  = new ContaBaseDeDados();

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

        Conta contaAdmin = new Conta("admin", "admin");
        Usuario admin = new Usuario(0,"admin", " ", " ",
                " ", true, contaAdmin);

        baseDeDados.AdicionarConta(contaAdmin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("login")) {
            String usuario = txtFieldUsuario.getText();
            String senha = passFieldSenha.getText();
            Conta conta = baseDeDados.BuscarConta(usuario);

            if(conta != null){
                if(conta.VerificaSenha(senha)){
                    System.out.println("Usuário Autenticado");

                    TelaPrincipal main = new TelaPrincipal();
                    main.setVisible(true);
                }
            }else {
                System.out.println("Usuario Inválido");
                txtFieldUsuario.setText("");
                passFieldSenha.setText("");
            }
        }
    }
}
