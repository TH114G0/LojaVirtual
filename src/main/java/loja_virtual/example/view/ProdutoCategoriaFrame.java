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

	public ProdutoCategoriaFrame() {
		super("Produtos");
		Container container = getContentPane();
		setLayout(null);

		this.categoriaController = new CategoriaController();
		this.produtoController = new ProdutoController();

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

		botaoSalvar = new JButton("Salvar");
		botaoLimpar = new JButton("Limpar");

		botaoSalvar.setBounds(10, 145, 80, 20);
		botaoLimpar.setBounds(100, 145, 80, 20);

		container.add(botaoSalvar);
		container.add(botaoLimpar);

		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identificador do Produto");
		modelo.addColumn("Nome do Produto");
		modelo.addColumn("Descrição do Produto");

		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botaoApagar = new JButton("Excluir");
		botaoEditar = new JButton("Alterar");

		botaoApagar.setBounds(10, 500, 80, 20);
		botaoEditar.setBounds(100, 500, 80, 20);

		container.add(botaoApagar);
		container.add(botaoEditar);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

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

	private void limparTabela() {
		modelo.getDataVector().clear();
	}

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

	private void preencherTabela() {
		List<Produto> produtos = listarProduto();
		for (Produto produto : produtos) {
			modelo.addRow(new Object[] { produto.getId(), produto.getNome(), produto.getDescricao() });
		}
	}

	private List<Categoria> listarCategoria() {
		return this.categoriaController.listar();
	}

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

	private List<Produto> listarProduto() {
		return this.produtoController.listar();
	}

	private void limpar() {
		this.textoNome.setText("");
		this.textoDescricao.setText("");
		this.comboCategoria.setSelectedIndex(0);
	}
}
