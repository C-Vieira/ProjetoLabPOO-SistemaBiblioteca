import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaLogin extends JFrame implements ActionListener {
    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JTextField txtFieldUsuario;
    private JPasswordField passFieldSenha;
    private JButton btnLogin;
    private JButton btnCadastro;
    private ContaBaseDeDados baseDeDados;

    public TelaLogin() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 250);
        setLayout(new GridLayout(3, 3));

        lblUsuario = new JLabel("Usuario");
        lblSenha = new JLabel("Senha");
        txtFieldUsuario = new JTextField();
        passFieldSenha = new JPasswordField();
        btnLogin = new JButton("Login");
        btnCadastro = new JButton("Cadastrar");

        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(this);

        btnCadastro.setActionCommand("cadastrar");
        btnCadastro.addActionListener(this);

        add(lblUsuario);
        add(txtFieldUsuario);
        add(lblSenha);
        add(passFieldSenha);
        add(btnCadastro);
        add(btnLogin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("login")) {
            String usuario = txtFieldUsuario.getText();
            String senha = passFieldSenha.getText();

            if(usuario.isEmpty() || senha.isEmpty()) {
                System.out.println("Usuario Inválido");
                txtFieldUsuario.setText("");
                passFieldSenha.setText("");
            }else{
                //Buscar conta
            }
        }

        if(e.getActionCommand().equals("cadastrar")) {
            System.out.println("Acesso Negado");
        }
    }
}
