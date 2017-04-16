package projeto.sabesp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConectaBanco {

	public Statement stm; // responsavel por preparar e realizar pesquisas no
							// banco de dados
	public ResultSet rs; // responsavel por armazanar o resultado de uma
							// pesquisas passado para o statement
	private String driver = "com.mysql.jdbc.Driver"; // responsavel por
														// identificar o serviço
														// de banco de dados
	private String caminho = "jdbc:mysql://localhost:3306/sistem_abastecimento"; // responsavel
																					// por
																					// setar
																					// o
																					// banco
																					// de
																					// dados
	private String usuario = "root";
	private String senha = "1234";
	public Connection conn; // responsável por realizar a conexão com o banco de
							// dados

	public void conexao() { // método resp. por realizar a conexao com o bd

		try {
			System.setProperty("jdbc.Drivers", driver);
			conn = DriverManager.getConnection(caminho, usuario, senha); // seta
																			// a
																			// propriedade
																			// do
																			// driver
																			// de
																			// conexao
			// JOptionPane.showMessageDialog(null, "Conectado com sucesso"); //
			// realiza a conecao com o banco
		} catch (SQLException ex) {
			Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Erro de conexao \n Erro: " + ex.getMessage());
		}
	}

	public void executaSQL(String sql) {
		try {
			stm = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException ex) {
		}
	}

	public ResultSet executaQuery(String sql) {
		try {
			stm = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException ex) {
		}
		return rs;
	}

	public void desconecta() {
		try {
			conn.close();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao fechar a conexao ");
		}

	}

	public Object getString(String nome_estado) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}