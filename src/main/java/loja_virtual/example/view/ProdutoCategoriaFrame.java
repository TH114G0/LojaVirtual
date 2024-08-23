package loja_virtual.example.view;

import loja_virtual.example.Model.Categoria;
import loja_virtual.example.Model.Produto;
import loja_virtual.example.controller.CategoriaController;
import loja_virtual.example.controller.ProdutoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe que representa a interface gráfica para gerenciamento de produtos e categorias.
 * <p>
 * Esta classe fornece uma tela para adicionar, editar, excluir e listar produtos,
 * bem como para selecionar categorias associadas a produtos.
 * </p>
 */
public class ProdutoCategoriaFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelNome, labelDescricao, labelCategoria;
	private JTextField textoNome, textoDescricao;
	private JComboBox<Categoria> comboCategoria;
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botaoApagar;
	private JTable tabela;
	private DefaultTableModel modelo;
	private ProdutoController produtoController;
	private CategoriaController categoriaController;

	/**
	 * Construtor da classe ProdutoCategoriaFrame.
	 * <p>
	 * Inicializa a interface gráfica, configura os componentes e define os eventos dos botões.
	 * </p>
	 */
	public ProdutoCategoriaFrame() {
		super("Produtos");
		Container container = getContentPane();
		setLayout(null);

		this.categoriaController = new CategoriaController();
		this.produtoController = new ProdutoController();

		// Inicializa os rótulos
		labelNome = new JLabel("Nome do Produto");
		labelDescricao = new JLabel("Descrição do Produto");
		labelCategoria = new JLabel("Categoria do Produto");

		labelNome.setBounds(10, 10, 240, 15);
		labelDescricao.setBounds(10, 50, 240, 15);
		labelCategoria.setBounds(10, 90, 240, 15);

		labelNome.setForeground(Color.BLACK);
		labelDescricao.setForeground(Color.BLACK);
		labelCategoria.setForeground(Color.BLACK);

		container.add(labelNome);
		container.add(labelDescricao);
		container.add(labelCategoria);

		// Inicializa os campos de texto e combobox
		textoNome = new JTextField();
		textoDescricao = new JTextField();
		comboCategoria = new JComboBox<>();

		comboCategoria.addItem(new Categoria(0, "Selecione"));
		List<Categoria> categorias = this.listarCategoria();
		for (Categoria categoria : categorias) {
			comboCategoria.addItem(categoria);
		}

		textoNome.setBounds(10, 25, 265, 20);
		textoDescricao.setBounds(10, 65, 265, 20);
		comboCategoria.setBounds(10, 105, 265, 20);

		container.add(textoNome);
		container.add(textoDescricao);
		container.add(comboCategoria);

		// Inicializa os botões
		botaoSalvar = new JButton("Salvar");
		botaoLimpar = new JButton("Limpar");

		botaoSalvar.setBounds(10, 145, 80, 20);
		botaoLimpar.setBounds(100, 145, 80, 20);

		container.add(botaoSalvar);
		container.add(botaoLimpar);

		// Inicializa a tabela
		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identificador do Produto");
		modelo.addColumn("Nome do Produto");
		modelo.addColumn("Descrição do Produto");

		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		// Inicializa os botões de apagar e editar
		botaoApagar = new JButton("Excluir");
		botaoEditar = new JButton("Alterar");

		botaoApagar.setBounds(10, 500, 80, 20);
		botaoEditar.setBounds(100, 500, 80, 20);

		container.add(botaoApagar);
		container.add(botaoEditar);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		// Configura os eventos dos botões
		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		botaoApagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alterar();
				limparTabela();
				preencherTabela();
			}
		});
	}

	/**
	 * Limpa todos os dados da tabela.
	 */
	private void limparTabela() {
		modelo.getDataVector().clear();
	}

	/**
	 * Altera o produto selecionado na tabela.
	 * <p>
	 * Obtém o produto selecionado e atualiza seus dados no banco de dados.
	 * </p>
	 */
	private void alterar() {
		int row = tabela.getSelectedRow();
		if (row >= 0) {
			Integer id = (Integer) modelo.getValueAt(row, 0);
			String nome = (String) modelo.getValueAt(row, 1);
			String descricao = (String) modelo.getValueAt(row, 2);
			this.produtoController.alterar(nome, descricao, id);
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecione o produto.");
		}
	}

	/**
	 * Deleta o produto selecionado na tabela.
	 * <p>
	 * Remove o produto do banco de dados e da tabela.
	 * </p>
	 */
	private void deletar() {
		int row = tabela.getSelectedRow();
		if (row >= 0) {
			Integer id = (Integer) modelo.getValueAt(row, 0);
			this.produtoController.deletar(id);
			modelo.removeRow(row);
			JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecione o produto.");
		}
	}

	/**
	 * Preenche a tabela com a lista de produtos do banco de dados.
	 */
	private void preencherTabela() {
		List<Produto> produtos = listarProduto();
		for (Produto produto : produtos) {
			modelo.addRow(new Object[] { produto.getId(), produto.getNome(), produto.getDescricao() });
		}
	}

	/**
	 * Obtém a lista de categorias do banco de dados.
	 *
	 * @return Lista de categorias.
	 */
	private List<Categoria> listarCategoria() {
		return this.categoriaController.listar();
	}

	/**
	 * Salva um novo produto no banco de dados.
	 * <p>
	 * Verifica se os campos de nome e descrição não estão vazios e salva o produto
	 * com a categoria selecionada.
	 * </p>
	 */
	private void salvar() {
		if (!textoNome.getText().isEmpty() && !textoDescricao.getText().isEmpty()) {
			Produto produto = new Produto(textoNome.getText(), textoDescricao.getText());
			Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
			produto.setCategoriaId(categoria.getId());
			this.produtoController.salvar(produto);
			JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
			this.limpar();
		} else {
			JOptionPane.showMessageDialog(this, "Nome e Descrição devem ser informados.");
		}
	}

	/**
	 * Obtém a lista de produtos do banco de dados.
	 *
	 * @return Lista de produtos.
	 */
	private List<Produto> listarProduto() {
		return this.produtoController.listar();
	}

	/**
	 * Limpa os campos de entrada e reseta o combobox de categorias.
	 */
	private void limpar() {
		this.textoNome.setText("");
		this.textoDescricao.setText("");
		this.comboCategoria.setSelectedIndex(0);
	}
}
