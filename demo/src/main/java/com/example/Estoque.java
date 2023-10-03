package com.example;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

// Classe Estoque que estende Produto
public class Estoque extends Produto {
    static List<Produto> produtosEstoque = new ArrayList<>();
    public static Object estoque;

    // Método para adicionar um novo produto ao estoque
    public void adicionarProduto(Produto novoProduto) {
        produtosEstoque.add(novoProduto);
    }

    // Método para listar todos os produtos no estoque
    public List<Produto> listarProdutos() {
        return produtosEstoque;
    }

    // Funcionalidade: Exibir todos os produtos no estoque
    public void verEstoque() {
        StringBuilder estoqueStr = new StringBuilder();
        for (Produto p : produtosEstoque) {
            estoqueStr.append("Nome: ")
                    .append(p.getNome())
                    .append(", Preço: ")
                    .append(p.getPreco())
                    .append(", Quantidade: ")
                    .append(p.getQuantidade())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, "Estoque:\n" + estoqueStr.toString());
    }
}
