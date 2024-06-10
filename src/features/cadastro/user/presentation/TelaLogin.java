package features.cadastro.user.presentation;

import di.ServiceLocator;
import features.cadastro.presentation.BaseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame implements BaseView {

    /* Classe que define uma tela de login, contém os campos necessários para autenticação (ID, nome, senha) */

    private JLabel lblUsuarioID;
    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JTextField txtFieldUsuarioID;
    private JTextField txtFieldUsuario;
    private JPasswordField passFieldSenha;
    private JButton btnLogin;

    private final UsuarioController usuarioController;

    public TelaLogin(UsuarioController usuarioController) {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 250);
        setLayout(new GridLayout(4, 2));

        this.usuarioController = usuarioController;
        usuarioController.setView(this);

        lblUsuarioID = new JLabel("ID:");
        lblUsuario = new JLabel("Usuário:");
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
            ServiceLocator.getInstance().getTelaPrincipal().open();
        }
        //Limpar campos
        txtFieldUsuarioID.setText("");
        txtFieldUsuario.setText("");
        passFieldSenha.setText("");
    }

    @Override
    public void open() {
        this.setVisible(true);
    }

    @Override
    public void mostrarMensagemDeErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
