package projeto.sabesp;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class MenuMain {
	public static void main(String[] args) {
		TelaPrincipal tela = new TelaPrincipal();
		tela.setVisible(true);

		JOptionPane.showMessageDialog(null,
				"                          Seja bem-vindo ao Menu Principal!\n" + "\n"
						+ "Este � o menu principal, aqui voc� pode Inserir, Editar e Deletar as \n"
						+ "informa��es de uma represa, ao clicar em pesquisar voce consegue \n"
						+ "visualizar o volume e pluviometria atual do sistema selecionado!",
				"Informa��o", JOptionPane.PLAIN_MESSAGE);

	}
}