import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCadastroUsuario extends TelaBase implements UsuarioListener {

    /* Classe que define uma tela de cadastro de usuários, contém todos os campos necessários para criação de um usuário
       Possui um botão para pesquisa e uma tabela para mostragem de resultados
       Aceita a seleção de linhas na tabela e preenche os campos com os devidos dados para auxílio nas operações de cadastro */

    private final UsuarioDAO usuarioDAO;
    private final UsuarioController usuarioController;

    public TelaCadastroUsuario() {
        super("Cadastro de Usuários");

        usuarioDAO = new UsuarioDAO();
        usuarioDAO.subscribe(this);
        usuarioController = new UsuarioController(this, usuarioDAO);

        lblCampo1.setText("Nome:");
        lblCampo2.setText("Senha:");
        lblCampo3.setText("CPF:");
        lblCampo4.setText("RG:");
        lblCampo5.setText("Email:");
        lblCampo6.setText("Admin:");

        radioBtn.setText("Sim");
        radioBtn.setSelected(false);

        btn1.setText("Adicionar");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionaUsuario();
            }
        });

        btn2.setText("Buscar");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });

        btn3.setText("Editar");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        btn4.setText("Excluir");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirUsuario();
            }
        });

        String[] tituloColunas = {"ID", "Nome", "Senha", "CPF", "RG", "Email", "Admin"}; //Define o título de cada coluna
        tableModel.setColumnIdentifiers(tituloColunas);

        recarregarTabela();
    }

    private void recarregarTabela() {
        //Atualização da visualização da tabela
        tableModel.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.getUsuarios();
        for (Usuario usuario : usuarios) {
            tableModel.addRow(new Object[]{usuario.getID(), usuario.getNome(), usuario.getSenha(),
                    usuario.getCPF(), usuario.getRG(), usuario.getEmail(), usuario.isAdmin()});
        }
        tableModel.fireTableDataChanged();
        IDSelecionado = -1; //Resetamos o ID selecionado para evitar erros
    }

    private Object[] coletaDados(){
        Object[] dados = new Object[6];

        //Coleta dos dados preenchidos
        dados[0] = txtFieldCampo1.getText();
        dados[1] = txtFieldCampo2.getText();
        dados[2] = txtFieldCampo3.getText();
        dados[3] = txtFieldCampo4.getText();
        dados[4] = txtFieldCampo5.getText();
        dados[5] = radioBtn.isSelected();

        return dados;
    }

    private void adicionaUsuario(){
        usuarioController.adicionaUsuario(coletaDados());
    }

    private void buscarUsuario(){
        usuarioController.buscarUsuario(coletaDados());
    }

    private void editarUsuario(){
        usuarioController.editarUsuario(IDSelecionado, coletaDados());
    }

    private void excluirUsuario(){
        usuarioController.excluirUsuario(IDSelecionado, coletaDados());
    }

    private void limparCampos(){
        txtFieldCampo1.setText("");
        txtFieldCampo2.setText("");
        txtFieldCampo3.setText("");
        txtFieldCampo4.setText("");
        txtFieldCampo5.setText("");
        radioBtn.setSelected(false);
    }

    @Override
    public void atualizaDados() {
        recarregarTabela();
        limparCampos();
    }

    @Override
    public void mostrarResultados(List<Usuario> resultado) {
        tableModel.setRowCount(0);
        for (Usuario usuario : resultado) {
            tableModel.addRow(new Object[]{usuario.getID(), usuario.getNome(), usuario.getSenha(),
                    usuario.getCPF(), usuario.getRG(), usuario.getRG(), usuario.isAdmin()});
        }
        tableModel.fireTableDataChanged();
        limparCampos();
    }

    @Override
    public void mostrarMensagemDeErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}