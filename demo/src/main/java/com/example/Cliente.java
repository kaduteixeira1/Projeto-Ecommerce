package com.example;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Cliente {
    private String nome;
    private int telefone;
    static List<Cliente> listaDeClientes = new ArrayList<>();

    // Construtor com parâmetros
    public Cliente(String nome, int telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    // Construtor vazio
    public Cliente() {
    }

    // Métodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    // Funcionalidade: Cadastrar novo cliente
    public void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Informe o nome do cliente:");
    
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operação cancelada");
            return;
        }
    
        int telefone = 0;
        boolean inputValido = false;
    
        // Obtém e valida o número de telefone do cliente
        while (!inputValido) {
            try {
                String telefoneStr = JOptionPane.showInputDialog("Informe o telefone do cliente:");
                if (telefoneStr == null) {
                    JOptionPane.showMessageDialog(null, "Operação cancelada");
                    return;
                }
                telefone = Integer.parseInt(telefoneStr);
                inputValido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira apenas números para o telefone.");
            }
        }
    
        // Cria um novo cliente e verifica se já está cadastrado
        Cliente novoCliente = new Cliente(nome, telefone);
        if (!checkCliente(nome, telefone)) {
            listaDeClientes.add(novoCliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já cadastrado!");
        }
    }
    
    // Método para verificar se o cliente já está cadastrado
    public boolean checkCliente(String nome, int telefone) {
        for (Cliente cliente : listaDeClientes) {
            if (cliente.getNome().equals(nome) && cliente.getTelefone() == telefone) {
                return true;
            }
        }     
        return false;
    }
    
    

    // Funcionalidade: Exibir todos os clientes cadastrados
    public void verClientes() {
        StringBuilder clientesStr = new StringBuilder();
        for (Cliente c : listaDeClientes) {
            clientesStr.append("Nome: ").append(c.getNome()).append(", Telefone: ").append(c.getTelefone())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, "Clientes:\n" + clientesStr.toString());
    }
}
