package com.example;

import javax.swing.JOptionPane;

public class Produto {
    static Estoque estoque = new Estoque();
    private String nome;
    private double preco;
    private int quantidade;

    // Construtor com parâmetros
    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Construtor vazio
    public Produto() {

    }

    // Métodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Metodo para cadastrar um novo produto
    public void cadastrarProduto() {
        String nomeProduto = JOptionPane.showInputDialog("Informe o nome do produto:");
        Produto existeProd = encontrarProdutoNoEstoque(nomeProduto);
        
        if (existeProd != null) {
            adicionarQuantidade(nomeProduto);
        } else {
            double precoProduto = 0;
            int quantidadeProduto = 0;
            boolean inputValidoPreco = false;
            boolean inputValidoQuant = false;
    
            while (!inputValidoPreco) {
                try {
                    String precoStr = JOptionPane.showInputDialog("Informe o preço do produto:");
                    precoProduto = Double.parseDouble(precoStr);
                    inputValidoPreco = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira apenas números para preço.");
                }
            }

            while (!inputValidoQuant) {
                try {
                     String quantidadeStr = JOptionPane.showInputDialog("Informe a quantidade do produto:");
                    quantidadeProduto = Integer.parseInt(quantidadeStr);
                    inputValidoQuant= true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira apenas números para quantidade.");
                }
            }
    
            Produto novoProduto = new Produto(nomeProduto, precoProduto, quantidadeProduto);
            estoque.adicionarProduto(novoProduto);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        }
    }
    
    // Metodo para verificar se o produto esa no estoque
    private Produto encontrarProdutoNoEstoque(String nomeProduto) {
        for (Produto p : estoque.listarProdutos()) {
            if (p.getNome().equalsIgnoreCase(nomeProduto)) {
                return p;
            }
        }
        return null;
    }

    // Metodo para adicionar mais unidades do produto ao estoque
    public void adicionarQuantidade(String nomeProduto) {
        int confirmacao = JOptionPane.showConfirmDialog(null,
                "Produto já existe. Deseja adicionar mais unidades ao estoque?", "Produto Existente",
                JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            int quantidadeAdicional = Integer.parseInt(JOptionPane
                    .showInputDialog("Informe a quantidade que deseja adicionar do produto " + nomeProduto + ":"));
            Produto produtoExistente = encontrarProdutoNoEstoque(nomeProduto);
            produtoExistente.setQuantidade(produtoExistente.getQuantidade() + quantidadeAdicional);
            JOptionPane.showMessageDialog(null, "Nova quantidade adicionada!");
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
        }
    }
}