package com.example;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venda extends Produto {
    private Produto produto = new Produto();
    private LocalDate dataAtual = LocalDate.now();
    private LocalTime horaAtual = LocalTime.now();
    private List<String> historicoVendas = new ArrayList<>();
    private List<Double> lucroBruto = new ArrayList<>();;
    private int quantidadeVendida;
    
    // Construtor com parâmetros
    public Venda(Produto produto, int quantidadeVendida) {
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }

    // Construtor vazio
    public Venda() {

    }

    // Métodos getters e setters
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void venderProduto() {
        String nomeProduto = JOptionPane.showInputDialog("Informe o nome do produto a ser vendido:");
    
        if (nomeProduto == null || nomeProduto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operação cancelada");
            return;
        }
    
        Produto produtoVendido = encontrarProdutoNoEstoque(nomeProduto);
        boolean inputValidoPreco = false;
    
        if (produtoVendido != null) {
         
    
            // Obtém e valida a quantidade vendida
            while (!inputValidoPreco) {
                try {
                    String quantidadeStr = JOptionPane.showInputDialog("Informe a quantidade vendida:");
                    if (quantidadeStr == null) {
                        JOptionPane.showMessageDialog(null, "Operação cancelada");
                        return;
                    }
                    quantidadeVendida = Integer.parseInt(quantidadeStr);
                    String resultadoVenda = addVendaELucro(produtoVendido);
                    JOptionPane.showMessageDialog(null, resultadoVenda);
                    inputValidoPreco = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira apenas números para quantidade vendida.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    }
    

    // Método auxiliar para adicionar uma venda e calcular o lucro ao estoque
    private String addVendaELucro(Produto produto) {
        produto.setQuantidade(produto.getQuantidade() - quantidadeVendida);
        double precoUnitario = produto.getPreco();
        double lucroVenda = precoUnitario * quantidadeVendida;
        lucroBruto.add(lucroVenda);

        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d MMM uuuu");
        String horaFormatada = horaAtual.format(formatterHora);
        String dataFormatada = dataAtual.format(formatterDate);
        StringBuilder vendaStr = new StringBuilder();

        vendaStr.append("*************************\n")
                .append("Venda realizada! \n")
                .append(quantidadeVendida)
                .append(" unidades de ")
                .append(produto.getNome() + "\n")
                .append(dataFormatada + " às " + horaFormatada)
                .append("\n*************************");

        String venda = vendaStr.toString();
        historicoVendas.add(venda);
        return venda;
    }

    // Método para encontrar um produto no estoque
    private Produto encontrarProdutoNoEstoque(String nomeProduto) {
        for (Produto p : estoque.listarProdutos()) {
            if (p.getNome().equalsIgnoreCase(nomeProduto)) {
                return p;
            }
        }
        return null;
    }

    // Método para listar o histórico de vendas
    public void listarHistoricoVendas() {
        StringBuilder historico = new StringBuilder("Histórico de Vendas:\n");
        for (String venda : historicoVendas) {
            historico.append(venda).append("\n");
        }
        JOptionPane.showMessageDialog(null, historico.toString());
    }

    // Método para calcular o lucro bruto total
    public double calcularLucroBruto() {
        double lucroTotal = 0.0;
        for (Double lucroVenda : lucroBruto) {
            lucroTotal += lucroVenda;
        }
        return lucroTotal;
    }


}
