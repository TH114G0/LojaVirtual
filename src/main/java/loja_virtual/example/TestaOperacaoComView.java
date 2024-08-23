package loja_virtual.example;

import javax.swing.JFrame;

import loja_virtual.example.view.ProdutoCategoriaFrame;

/**
 * Classe de teste para inicializar a interface gráfica de gerenciamento de produtos e categorias.
 * <p>
 * Esta classe contém o método principal que cria e exibe a tela principal para gerenciamento
 * de produtos e categorias.
 * </p>
 */
public class TestaOperacaoComView {

	/**
	 * Método principal para iniciar a aplicação.
	 * <p>
	 * Cria uma instância da tela de gerenciamento de produtos e categorias e define a operação
	 * de fechamento da janela para encerrar o aplicativo quando a janela é fechada.
	 * </p>
	 *
	 * @param args Argumentos da linha de comando (não utilizados).
	 */
	public static void main(String[] args) {
		// Cria a instância da tela de gerenciamento de produtos e categorias
		ProdutoCategoriaFrame produtoCategoriaFrame = new ProdutoCategoriaFrame();

		// Define a operação de fechamento da janela para encerrar a aplicação
		produtoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
