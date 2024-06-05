package features.cadastro.presentation;

import features.cadastro.datasource.CadastroListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class TelaBase extends javax.swing.JFrame implements BaseView, CadastroListener {

    /* Classe que define uma tela básica para pesquisa e cadastro */

    protected JPanel panelHeader;
    protected JButton btnHeader1;
    protected JButton btnHeader2;
    protected JPanel panelPrincipal;
    protected JPanel panelTabela;
    protected JScrollPane tabelaScrollPane;
    protected JPanel panelCampos;
    protected JLabel lblCampo1;
    protected JLabel lblCampo2;
    protected JLabel lblCampo3;
    protected JLabel lblCampo4;
    protected JLabel lblCampo5;
    protected JLabel lblCampo6;
    protected JTextField txtFieldCampo1;
    protected JTextField txtFieldCampo2;
    protected JTextField txtFieldCampo3;
    protected JTextField txtFieldCampo4;
    protected JTextField txtFieldCampo5;
    protected JRadioButton radioBtn;
    protected JButton btn1;
    protected JButton btn2;
    protected JButton btn3;
    protected JButton btn4;
    protected JTable tblResultados;
    protected DefaultTableModel tableModel;
    protected int IDSelecionado;

    public TelaBase(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1000, 400);
        setLayout(new BorderLayout());

        panelHeader = new JPanel(new FlowLayout());
        btnHeader1 = new JButton();
        btnHeader2 = new JButton();
        panelHeader.add(btnHeader1);
        panelHeader.add(btnHeader2);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(1, 2));

        panelTabela = new JPanel();
        panelCampos = new JPanel();

        lblCampo1 = new JLabel();
        lblCampo2 = new JLabel();
        lblCampo3 = new JLabel();
        lblCampo4 = new JLabel();
        lblCampo5 = new JLabel();
        lblCampo6 = new JLabel();
        txtFieldCampo1 = new JTextField();
        txtFieldCampo2 = new JTextField();
        txtFieldCampo3 = new JTextField();
        txtFieldCampo4 = new JTextField();
        txtFieldCampo5 = new JTextField();
        radioBtn = new JRadioButton();
        btn1 = new JButton();
        btn2 = new JButton();
        btn3 = new JButton();
        btn4 = new JButton();

        tblResultados = new JTable();
        tblResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableModel = (DefaultTableModel) tblResultados.getModel(); //Cria um modelo de tabela para que possamos manipular as linhas

        ListSelectionModel selectionModel = tblResultados.getSelectionModel();
        selectionModel.addListSelectionListener(this::handleSelectionEvent);

        tabelaScrollPane = new JScrollPane(tblResultados);
        tblResultados.setFillsViewportHeight(true);

        panelTabela.add(tabelaScrollPane);

        panelCampos.setLayout(new GridLayout(8, 2));

        panelCampos.add(lblCampo1);
        panelCampos.add(txtFieldCampo1);
        panelCampos.add(lblCampo2);
        panelCampos.add(txtFieldCampo2);
        panelCampos.add(lblCampo3);
        panelCampos.add(txtFieldCampo3);
        panelCampos.add(lblCampo4);
        panelCampos.add(txtFieldCampo4);
        panelCampos.add(lblCampo5);
        panelCampos.add(txtFieldCampo5);
        panelCampos.add(lblCampo6);
        panelCampos.add(radioBtn);
        panelCampos.add(btn1);
        panelCampos.add(btn2);
        panelCampos.add(btn3);
        panelCampos.add(btn4);

        panelPrincipal.add(panelTabela);
        panelPrincipal.add(panelCampos);

        add(panelHeader, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);
    }

    /*  Este método lida com a seleção de linhas na tabela
        Faz a coleta dos dados em cada célula da linha selecionada e atualiza os campos correspondentes */
    protected void handleSelectionEvent(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        int i = tblResultados.getSelectedRow();

        if(i < 0) return; //Se a tabela estiver vazia, retorne

        //Coleta e conversão dos dados
        int id = (int) tblResultados.getValueAt(i, 0);
        String campo1 = (String) tblResultados.getValueAt(i, 1);
        String campo2 = (String) tblResultados.getValueAt(i, 2);
        String campo3 = (String) tblResultados.getValueAt(i, 3);
        String campo4 = (String) tblResultados.getValueAt(i, 4);
        String campo5 = (String) tblResultados.getValueAt(i, 5);
        boolean campo6 = (boolean) tblResultados.getValueAt(i, 6);

        //Atualização dos campos
        IDSelecionado = id;
        txtFieldCampo1.setText(campo1);
        txtFieldCampo2.setText(campo2);
        txtFieldCampo3.setText(campo3);
        txtFieldCampo4.setText(campo4);
        txtFieldCampo5.setText(campo5);
        radioBtn.setSelected(campo6);
    }

    protected Object[] coletaDados(){
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

    protected void limparCampos(){
        txtFieldCampo1.setText("");
        txtFieldCampo2.setText("");
        txtFieldCampo3.setText("");
        txtFieldCampo4.setText("");
        txtFieldCampo5.setText("");
        radioBtn.setSelected(false);
    }

    @Override
    public void atualizaDados() {
        limparCampos();
    }

    @Override
    public void mostrarMensagemDeErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
        atualizaDados();
    }
}

