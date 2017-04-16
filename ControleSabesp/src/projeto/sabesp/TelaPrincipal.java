package projeto.sabesp;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.Toolkit;
import javax.swing.*;
import java.security.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class TelaPrincipal extends JFrame implements ActionListener {

	private JFormattedTextField txt_DataMedicao;
	private JTextField txt_VolArmazenado;
	private JTextField txt_Pluviometria;
	private JButton btn_Pesquisar, btnRelatorio, btnGravar, btnAlterar, btnExcluir;
	private JComboBox combo_Represa, combo_Sistema;

	ConectaBanco conn = new ConectaBanco();

	public TelaPrincipal() {

		conn.conexao();
		setIcon();

		// configurando ações da Janela
		setTitle("Menu Principal");
		setSize(430, 380);
		conn.conexao();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// instanciando GridBagLayout e adcionando na tela
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 17, 118, 107, 63, 92, 47, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 42, 37, 35, 21, 66, 0, 0, 0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// Começo do Label e Combo do Sistema

		JLabel label_Sistema = new JLabel("Sistema");
		label_Sistema.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_label_Sistema = new GridBagConstraints();
		gbc_label_Sistema.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_Sistema.insets = new Insets(0, 0, 5, 5);
		gbc_label_Sistema.gridx = 1;
		gbc_label_Sistema.gridy = 1;
		getContentPane().add(label_Sistema, gbc_label_Sistema);

		combo_Sistema = new JComboBox();
		combo_Sistema.setFont(new Font("Tahoma", Font.PLAIN, 14));
		combo_Sistema.setModel(new DefaultComboBoxModel(new String[] { "Selecionar" }));
		GridBagConstraints gbc_combo_Sistema = new GridBagConstraints();
		gbc_combo_Sistema.gridwidth = 2;
		gbc_combo_Sistema.insets = new Insets(0, 0, 5, 5);
		gbc_combo_Sistema.fill = GridBagConstraints.HORIZONTAL;
		gbc_combo_Sistema.gridx = 2;
		gbc_combo_Sistema.gridy = 1;
		getContentPane().add(combo_Sistema, gbc_combo_Sistema);

		// Começo do Label e Combo de Represa

		JLabel label_Represa = new JLabel("Represa");
		label_Represa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_label_Represa = new GridBagConstraints();
		gbc_label_Represa.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_Represa.insets = new Insets(0, 0, 5, 5);
		gbc_label_Represa.gridx = 1;
		gbc_label_Represa.gridy = 2;

		combo_Represa = new JComboBox();
		combo_Represa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		combo_Represa.setModel(new DefaultComboBoxModel(new String[] { "Selecionar" }));
		GridBagConstraints gbc_combo_Represa = new GridBagConstraints();
		gbc_combo_Represa.gridwidth = 2;
		gbc_combo_Represa.insets = new Insets(0, 0, 5, 5);
		gbc_combo_Represa.fill = GridBagConstraints.HORIZONTAL;
		gbc_combo_Represa.gridx = 2;
		gbc_combo_Represa.gridy = 2;

		JLabel label_DataMedicao = new JLabel("Data da Medição");
		label_DataMedicao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_label_DataMedicao = new GridBagConstraints();
		gbc_label_DataMedicao.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_DataMedicao.insets = new Insets(0, 0, 5, 5);
		gbc_label_DataMedicao.gridx = 1;
		gbc_label_DataMedicao.gridy = 3;
		getContentPane().add(label_DataMedicao, gbc_label_DataMedicao);

		try {
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
			txt_DataMedicao = new javax.swing.JFormattedTextField(data);
		} catch (Exception e) {
		}

		// Puxa os dados do banco nos dois combos

		conn.executaSQL("select * from sistema_abastecimento");
		try {
			conn.rs.first();
			do {
				combo_Sistema.addItem(conn.rs.getString("nome_sistema"));
			} while (conn.rs.next());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(rootPane, "Erro ao preencher combobox sistema: " + ex);
		}

		txt_DataMedicao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_DataMedicao = new GridBagConstraints();
		gbc_txt_DataMedicao.gridwidth = 2;
		gbc_txt_DataMedicao.insets = new Insets(0, 0, 5, 5);
		gbc_txt_DataMedicao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_DataMedicao.gridx = 2;
		gbc_txt_DataMedicao.gridy = 3;
		getContentPane().add(txt_DataMedicao, gbc_txt_DataMedicao);
		txt_DataMedicao.setColumns(10);

		btn_Pesquisar = new JButton("Pesquisar");
		btn_Pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btn_Pesquisar = new GridBagConstraints();
		gbc_btn_Pesquisar.gridwidth = 2;
		gbc_btn_Pesquisar.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Pesquisar.gridx = 4;
		gbc_btn_Pesquisar.gridy = 3;
		getContentPane().add(btn_Pesquisar, gbc_btn_Pesquisar);

		JPanel pn_DadosMedicao = new JPanel();
		pn_DadosMedicao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados da Medição",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_pn_DadosMedicao = new GridBagConstraints();
		gbc_pn_DadosMedicao.gridheight = 2;
		gbc_pn_DadosMedicao.gridwidth = 4;
		gbc_pn_DadosMedicao.insets = new Insets(0, 0, 0, 5);
		gbc_pn_DadosMedicao.fill = GridBagConstraints.BOTH;
		gbc_pn_DadosMedicao.gridx = 1;
		gbc_pn_DadosMedicao.gridy = 5;
		getContentPane().add(pn_DadosMedicao, gbc_pn_DadosMedicao);
		GridBagLayout gbl_pn_DadosMedicao = new GridBagLayout();
		gbl_pn_DadosMedicao.columnWidths = new int[] { 0, 0, 84, 80, 0, 0 };
		gbl_pn_DadosMedicao.rowHeights = new int[] { 21, 0, 0, 25, 0, 0 };
		gbl_pn_DadosMedicao.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pn_DadosMedicao.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pn_DadosMedicao.setLayout(gbl_pn_DadosMedicao);

		JLabel label_ValorArmazenado = new JLabel("Volume Armazenado");
		label_ValorArmazenado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_label_ValorArmazenado = new GridBagConstraints();
		gbc_label_ValorArmazenado.gridwidth = 2;
		gbc_label_ValorArmazenado.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_ValorArmazenado.insets = new Insets(0, 0, 5, 5);
		gbc_label_ValorArmazenado.gridx = 1;
		gbc_label_ValorArmazenado.gridy = 1;
		pn_DadosMedicao.add(label_ValorArmazenado, gbc_label_ValorArmazenado);

		txt_VolArmazenado = new JTextField();
		txt_VolArmazenado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_VolArmazenado = new GridBagConstraints();
		gbc_txt_VolArmazenado.insets = new Insets(0, 0, 5, 5);
		gbc_txt_VolArmazenado.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_VolArmazenado.gridx = 3;
		gbc_txt_VolArmazenado.gridy = 1;
		pn_DadosMedicao.add(txt_VolArmazenado, gbc_txt_VolArmazenado);
		txt_VolArmazenado.setColumns(10);

		JLabel label_Pluviometria = new JLabel("Pluviometria");
		label_Pluviometria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_label_Pluviometria = new GridBagConstraints();
		gbc_label_Pluviometria.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_Pluviometria.insets = new Insets(0, 0, 5, 5);
		gbc_label_Pluviometria.gridx = 1;
		gbc_label_Pluviometria.gridy = 2;
		pn_DadosMedicao.add(label_Pluviometria, gbc_label_Pluviometria);

		txt_Pluviometria = new JTextField();
		txt_Pluviometria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Pluviometria = new GridBagConstraints();
		gbc_txt_Pluviometria.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Pluviometria.fill = GridBagConstraints.BOTH;
		gbc_txt_Pluviometria.gridx = 3;
		gbc_txt_Pluviometria.gridy = 2;
		pn_DadosMedicao.add(txt_Pluviometria, gbc_txt_Pluviometria);
		txt_Pluviometria.setColumns(10);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		pn_DadosMedicao.add(panel, gbc_panel);

		// Botoes

		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnRelatorio);

		btnGravar = new JButton("Gravar");
		btnGravar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnGravar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnAlterar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnExcluir);

		// Açoes dos botoes
		btn_Pesquisar.addActionListener(this);
		btnRelatorio.addActionListener(this);
		btnGravar.addActionListener(this);
		btnAlterar.addActionListener(this);
		btnExcluir.addActionListener(this);

	}

	// Açoes GERAIS DOS BOTOES
	//
	// PESQUISAR
	public void actionPerformed(ActionEvent e) {
		String tD = txt_DataMedicao.getText();
		String tV = txt_VolArmazenado.getText();
		String tP = txt_Pluviometria.getText();

		if (e.getSource() == btn_Pesquisar) {

			if (combo_Sistema.getSelectedItem().toString().equals("Selecionar") || tD.equals("")) {
				JOptionPane.showMessageDialog(null, "Selecione um sistema");
			} else {

				ResultSet rs;

				conn.conexao();
				conn.executaSQL("select * from tabelaCRUD");

				String a = combo_Represa.getSelectedItem().toString();
				String b = combo_Sistema.getSelectedItem().toString();

				try {
					PreparedStatement pst = conn.conn.prepareStatement(
							"select volume_armazenado, pluviometria from tabelaCRUD where nome_sistema = ? AND data_atual = ?");
					pst.setString(1, b);
					pst.setString(2, (txt_DataMedicao.getText()));
					try {
						rs = pst.executeQuery();
						if (rs.next()) {
							txt_VolArmazenado.setText(String.valueOf(rs.getFloat("volume_armazenado")));
							txt_Pluviometria.setText(String.valueOf(rs.getFloat("pluviometria")));
						} else {
							JOptionPane.showMessageDialog(null,
									"Verifique se a data e o sistema estão preenchidos corretamente!");
						}
					} catch (Exception ebu) {
						ebu.printStackTrace();
					}

				}

				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro na pesquisa. \n ERRO: " + ex);
				}
			}
		}

		// RELATORIO
		if (e.getSource() == btnRelatorio) {
			VisualizarSistemas vs = new VisualizarSistemas();

			JOptionPane.showMessageDialog(null,
					"                          Está é a tela Histórico \n"
							+ "     aqui você pode realizar consultas, observando a data \n"
							+ "e tambem verificar o estado de movimentação do sistema",
					"Informação", JOptionPane.PLAIN_MESSAGE);

			vs.setVisible(true);

		}

		// GRAVAR OU CADASTRAR OU INCLUIR (crud)

		if (e.getSource() == btnGravar) {

			if (combo_Sistema.getSelectedItem().toString().equals("Selecionar") || tV.equals("") || tP.equals("")
					|| tD.equals("")) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos, para poder gravar");
			} else {
				try {

					String a = combo_Represa.getSelectedItem().toString();
					String b = combo_Sistema.getSelectedItem().toString();
					int auxiliar = 1;
					conn.conexao(); // chama o metodo de conexao
					conn.executaSQL("select * from tabelaCRUD");

					PreparedStatement pst = conn.conn.prepareStatement(
							"insert into tabelaCRUD(cod_represas, nome_represa, nome_sistema, volume_armazenado, pluviometria, data_atual)  values (default, ?, ?, ?, ?, ?)");
					pst.setString(1, a);
					pst.setString(2, b);
					pst.setDouble(3, Double.parseDouble(txt_VolArmazenado.getText()));
					pst.setDouble(4, Double.parseDouble(txt_Pluviometria.getText()));
					pst.setString(5, (txt_DataMedicao.getText()));
					pst.execute();
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro na inserção. \n ERRO: " + ex);
				}

			}
		}

		// ALTERAR OU EDITAR(crud)

		if (e.getSource() == btnAlterar) {

			if (combo_Sistema.getSelectedItem().toString().equals("Selecionar") || tD.equals("")) {
				JOptionPane.showMessageDialog(null, "Preencha a data para poder alterar");
			} else {
				try {

					String a = combo_Represa.getSelectedItem().toString();
					String b = combo_Sistema.getSelectedItem().toString();
					int auxiliar = 1;
					conn.conexao(); // chama o metodo de conexao
					conn.executaSQL("select * from tabelaCRUD");

					PreparedStatement pst = conn.conn.prepareStatement(
							"UPDATE tabelaCRUD SET volume_armazenado = ?, pluviometria = ? where nome_sistema = ? AND data_atual = ?");
					pst.setDouble(1, Double.parseDouble(txt_VolArmazenado.getText()));
					pst.setDouble(2, Double.parseDouble(txt_Pluviometria.getText()));
					pst.setString(3, b);
					pst.setString(4, (txt_DataMedicao.getText()));

					pst.execute();
					JOptionPane.showMessageDialog(null, "alterado com sucesso!");
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro na alteração. \n ERRO: " + ex);
				}

			}
		}

		// EXCLUIR OU DELETAR(crud)

		if (e.getSource() == btnExcluir) {

			if (combo_Sistema.getSelectedItem().toString().equals("Selecionar") || tV.equals("") || tP.equals("")
					|| tD.equals("")) {
				JOptionPane.showMessageDialog(null, "Preencha a data e o sistema para poder excluir");
			} else {
				try {

					String a = combo_Represa.getSelectedItem().toString();
					String b = combo_Sistema.getSelectedItem().toString();
					int auxiliar = 1;
					conn.conexao(); // chama o metodo de conexao
					conn.executaSQL("select * from tabelaCRUD");

					PreparedStatement pst = conn.conn
							.prepareStatement("delete from tabelaCRUD where nome_sistema = ? AND data_atual = ?");
					pst.setString(1, b);
					pst.setString(2, (txt_DataMedicao.getText()));

					pst.execute();
					JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro na exclusao. \n ERRO: " + ex);
				}

			}
		}
	}

	public void setIcon() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icone.png")));
	}

}