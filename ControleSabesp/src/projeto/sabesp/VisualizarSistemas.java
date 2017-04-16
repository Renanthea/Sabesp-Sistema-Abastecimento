package projeto.sabesp;

import javax.swing.*;
import javax.swing.*;
import java.security.*;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;
import java.sql.*;
import java.util.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.text.DecimalFormat;

public class VisualizarSistemas extends JFrame implements ActionListener {

	ConectaBanco conecta = new ConectaBanco();
	private JButton botao, voltar, proxima;
	private JTextField texto;
	private JLabel etiqueta;
	private JPanel painelFundo;
	private JComboBox combo_Sistema;

	public VisualizarSistemas() {

		super("Histórico");

		setIcon();
		initComponents();
		conecta.conexao();

		botao = new JButton("Pesquisar");
		proxima = new JButton("Proxima");
		voltar = new JButton("Estabilidade");

		texto = new JTextField("", 14);
		etiqueta = new JLabel("Buscar Sistema : ");

		combo_Sistema = new JComboBox<>();
		combo_Sistema.setModel(new DefaultComboBoxModel(new String[] { "Selecionar" }));

		conecta.executaSQL("select * from sistema_abastecimento");
		try {
			conecta.rs.first();
			do {
				combo_Sistema.addItem(conecta.rs.getString("nome_sistema"));
			} while (conecta.rs.next());

		} catch (SQLException ex) {

			JOptionPane.showMessageDialog(rootPane, "Erro ao preencher combobox sistema: " + ex);
		}

		Container caixa = getContentPane();
		caixa.setLayout(new FlowLayout());

		JPanel painel1 = new JPanel(new FlowLayout());
		JPanel painel2 = new JPanel(new FlowLayout());

		painel1.add(voltar);
		painel1.add(etiqueta);
		painel1.add(combo_Sistema);
		painel1.add(botao);

		painel2.add(jScrollPane1);
		jTable1.setBounds(10, 60, 100, 80);

		caixa.add(painel1);
		caixa.add(painel2);

		botao.addActionListener(this);
		voltar.addActionListener(this);

		setSize(550, 550);

		setVisible(true);
		setResizable(false);
		this.setLocationRelativeTo(null);

		preencherTabela("select * from tabelaCRUD order by data_atual desc");

	}

	public void setIcon() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icone.png")));
	}

	public void actionPerformed(ActionEvent e) {

		// Botao pesquisar, este preenche a tabela
		if (e.getSource() == botao) {

			String a = combo_Sistema.getSelectedItem().toString();

			if (a.equals("Alto Cotia")) {

				preencherTabela("select * from tabelaCRUD where nome_sistema = 'Alto Cotia' order by data_atual desc");
			}

			if (a.equals("Guarapiranga")) {
				preencherTabela(
						"select * from tabelaCRUD where nome_sistema = 'Guarapiranga' order by data_atual desc");
			}
			if (a.equals("Alto Tietê")) {
				preencherTabela("select * from tabelaCRUD where nome_sistema = 'Alto Tietê' order by data_atual desc");
			}
			if (a.equals("Cantareira")) {
				preencherTabela("select * from tabelaCRUD where nome_sistema = 'Cantareira' order by data_atual desc");
			}
			if (a.equals("Rio Claro")) {
				preencherTabela("select * from tabelaCRUD where nome_sistema = 'Rio Claro' order by data_atual desc");
			}
			if (a.equals("Rio Grande")) {
				preencherTabela("select * from tabelaCRUD where nome_sistema = 'Rio Grande' order by data_atual desc");
			}

			if (a.equals("Selecionar")) {
				JOptionPane.showMessageDialog(null, "Todos os sistemas selecionados");
				preencherTabela("select * from tabelaCRUD order by data_atual desc");
			}

		}
		// Botao , este preenche a tabela
		if (e.getSource() == voltar)

			if (combo_Sistema.getSelectedItem().toString().equals("Selecionar")) {
				JOptionPane.showMessageDialog(null, "Selecione um sistema!");

			} else {
				String a = combo_Sistema.getSelectedItem().toString();
				ResultSet rs = conecta.executaQuery(
						"select avg(volume_armazenado) from tabelaCRUD order by day(data_atual) limit 0,5;");

				if (a.equals("Alto Cotia")) {
					rs = conecta.executaQuery(
							"select avg(volume_armazenado) from tabelaCRUD where nome_sistema = 'Alto Cotia' order by day(data_atual) limit 0,5;");
				}
				if (a.equals("Guarapiranga")) {
					rs = conecta.executaQuery(
							"select avg(volume_armazenado) from tabelaCRUD where nome_sistema = 'Guarapiranga' order by day(data_atual) limit 0,5;");
				}
				if (a.equals("Alto Tietê")) {
					rs = conecta.executaQuery(
							"select avg(volume_armazenado) from tabelaCRUD where nome_sistema = 'Alto Tietê' order by day(data_atual) limit 0,5;");
				}
				if (a.equals("Cantareira")) {
					rs = conecta.executaQuery(
							"select avg(volume_armazenado) from tabelaCRUD where nome_sistema = 'Cantareira' order by day(data_atual) limit 0,5;");
				}
				if (a.equals("Rio Claro")) {
					rs = conecta.executaQuery(
							"select avg(volume_armazenado) from tabelaCRUD where nome_sistema = 'Rio Claro' order by day(data_atual) limit 0,5;");
				}
				if (a.equals("Rio Grande")) {
					rs = conecta.executaQuery(
							"select avg(volume_armazenado) from tabelaCRUD where nome_sistema = 'Rio Grande' order by day(data_atual) limit 0,5;");
				}

				try {

					if (rs.next()) {

						Double avg = rs.getDouble(1);
						String estabilidade = combo_Sistema.getSelectedItem().toString();

						if (avg > 50) {

							JOptionPane.showMessageDialog(null, "O nivel do sistema " + estabilidade
									+ " é crescente! \n" + "Aproveite!, faça outra consulta!");
						}
						if (avg == 50) {

							JOptionPane.showMessageDialog(null, "O nivel do sistema " + estabilidade
									+ " é constante! \n" + "Aproveite!, faça outra consulta!");
						}
						if (avg < 50) {

							JOptionPane.showMessageDialog(null, "O nivel do sistema " + estabilidade
									+ " é decrescente! \n" + "Aproveite!, faça outra consulta!");
						}

					}

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(rootPane,
							" Erro ao calcular a Estabilidade, por favor selecione um sistema!" + ex);
				}

			}

	}

	public void preencherTabela(String SQL) {

		DecimalFormat df2 = new DecimalFormat("#,##0.00");
		ArrayList dados = new ArrayList();

		String[] Colunas = new String[] { "SISTEMA", "PLUVIOMETRIA", "VOLUME (%)", "DATA" };

		conecta.executaSQL(SQL);
		try {
			conecta.rs.first();
			do {
				dados.add(new Object[] { conecta.rs.getString("nome_sistema"), conecta.rs.getDouble("pluviometria"),
						df2.format(conecta.rs.getDouble("volume_armazenado")), conecta.rs.getString("data_atual") });
			} while (conecta.rs.next());

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao preencher tabela");
		}

		ModeloTabela modelo = new ModeloTabela(dados, Colunas);

		jTable1.setModel(modelo);
		jTable1.getColumnModel().getColumn(0).setPreferredWidth(135);
		jTable1.getColumnModel().getColumn(0).setResizable(false);
		jTable1.getColumnModel().getColumn(1).setPreferredWidth(105);
		jTable1.getColumnModel().getColumn(1).setResizable(false);
		jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
		jTable1.getColumnModel().getColumn(2).setResizable(false);
		jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
		jTable1.getColumnModel().getColumn(3).setResizable(false);
		jTable1.getTableHeader().setReorderingAllowed(false);
		jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jTable1.setDefaultRenderer(Object.class, new CellRenderer());

	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();

		jTable1.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(jTable1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap(15, Short.MAX_VALUE).addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap(14, Short.MAX_VALUE).addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		pack();
	}

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
}