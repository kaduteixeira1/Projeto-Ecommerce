package com.example;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    // Instâncias das classes Cliente, Estoque, Produto e Venda
    private Cliente cliente = new Cliente();
    private Estoque estoque = new Estoque();
    private Produto produto = new Produto();
    private Venda venda = new Venda();

    // Construtor da classe Screen
    public Screen(Main main) {
        setTitle("Sistema de Vendas"); // Título da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ação ao fechar a janela
        setLayout(new BorderLayout()); // Layout principal da janela

        // Painel de boas-vindas
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
        JLabel welcomeLabel = new JLabel("Bem-vindo ao Ecommerce");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);

        // Layout dos botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); // Layout dos botões

        // Configurações de estilo para os botões
        Font buttonFont = new Font("Poppins", Font.PLAIN, 16);
        Color buttonBackgroundColor = new Color(0, 122, 204);
        Color buttonForegroundColor = Color.WHITE;
        Dimension buttonSize = new Dimension(200, 50);
        int buttonShadowSize = 5;

        // Array de botões com rótulos e ações associadas
        JButton[] buttons = {
                createStyledButton("Cadastrar Cliente", buttonFont, buttonBackgroundColor, buttonForegroundColor,
                        buttonSize, buttonShadowSize),
                createStyledButton("Cadastrar Produto", buttonFont, buttonBackgroundColor, buttonForegroundColor,
                        buttonSize, buttonShadowSize),
                createStyledButton("Ver Clientes", buttonFont, buttonBackgroundColor, buttonForegroundColor, buttonSize,
                        buttonShadowSize),
                createStyledButton("Ver Estoque", buttonFont, buttonBackgroundColor, buttonForegroundColor, buttonSize,
                        buttonShadowSize),
                createStyledButton("Vender Produto", buttonFont, buttonBackgroundColor, buttonForegroundColor,
                        buttonSize, buttonShadowSize),
                createStyledButton("Calcular Lucro Bruto", buttonFont, buttonBackgroundColor, buttonForegroundColor,
                        buttonSize, buttonShadowSize),
                createStyledButton("Listar Histórico", buttonFont, buttonBackgroundColor, buttonForegroundColor,
                        buttonSize, buttonShadowSize),
                createStyledButton("Sair", buttonFont, Color.RED, buttonForegroundColor, buttonSize, buttonShadowSize)
        };

        // Adicionar ação aos botões
        buttons[0].addActionListener(e -> cliente.cadastrarCliente());
        buttons[6].addActionListener(e -> venda.listarHistoricoVendas());
        buttons[4].addActionListener(e -> venda.venderProduto());
        buttons[2].addActionListener(e -> cliente.verClientes());
        buttons[3].addActionListener(e -> estoque.verEstoque());
        buttons[1].addActionListener(e -> produto.cadastrarProduto());
        buttons[5].addActionListener(
                e -> JOptionPane.showMessageDialog(null, "LUCRO BRUTO: " + venda.calcularLucroBruto() + " R$"));
        buttons[7].addActionListener(e -> System.exit(0));

        for (JButton button : buttons) {
            buttonPanel.add(button);
        }

        // Adicionar painéis à janela principal
        add(welcomePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);


        // Configurações da janela
        pack(); 
        setLocationRelativeTo(null); 
        setVisible(true); 
    }

    // Método para criar um botão estilizado
    private JButton createStyledButton(String text, Font font, Color background, Color foreground, Dimension size,
            int shadowSize) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setPreferredSize(size);
        button.setBorder(BorderFactory.createEmptyBorder(shadowSize, shadowSize, shadowSize, shadowSize));
        button.setFocusPainted(false); // Remove a borda de foco
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mão ao passar o mouse

        return button;
    }

    // Método main para iniciar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Screen(new Main());
        });
    }
}
