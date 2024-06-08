/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unipar.cadastroclientetrabalho;

import javax.swing.*;
import java.util.regex.Pattern;

/**
 *
 * @author Marlene Juliana
 */
public class CadastroClienteTrabalho {


    private static boolean validarCodigo(String codigo) {
        return Pattern.matches("\\d+", codigo);
    }

    private static boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    private static boolean validarData(String data) {
        return Pattern.matches("\\d{2}/\\d{2}/\\d{4}", data);
    }

    private static boolean validarTelefone(String telefone) {
        return Pattern.matches("\\d{10,11}", telefone);
    }

   private static String inputValidado(String prompt, java.util.function.Predicate<String> validacao) {
        while (true) {
            String valor = JOptionPane.showInputDialog(null, prompt);
            if (valor == null) {
                // Se o usuário clicar em "Cancelar" ou fechar a caixa de diálogo
                System.exit(0);
            }
            if (validacao.test(valor)) {
                return valor;
            } else {
                JOptionPane.showMessageDialog(null, "Valor inválido! Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void menu() {
        String menu = "1. Adicionar novo cliente\n" +
                      "2. Remover cliente\n" +
                      "3. Alterar dados de cliente\n" +
                      "4. Recuperar dados de cliente\n" +
                      "5. Exibir todos os clientes\n" +
                      "6. Sair\n" +
                      "Escolha uma opção: ";
        JOptionPane.showMessageDialog(null, menu, "Menu", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        DoublyLinkedList clientes = new DoublyLinkedList();       

        while (true) {
            menu();
            String opcao = JOptionPane.showInputDialog(null, "Escolha uma opção:");
             
        if (opcao == null) {
                // Se o usuário clicar em "Cancelar" ou fechar a caixa de diálogo
                System.exit(0);
            }
        switch (opcao) {
            case "1":
                int codigo = Integer.parseInt(inputValidado("Código:", CadastroClienteTrabalho::validarCodigo));
                String nome = inputValidado("Nome:", CadastroClienteTrabalho::validarNome);
                String dataNascimento = inputValidado("Data de Nascimento (DD/MM/AAAA):", CadastroClienteTrabalho::validarData);
                String telefone = inputValidado("Telefone (ex: 45999666333):", CadastroClienteTrabalho::validarTelefone);
                clientes.add(codigo, nome, dataNascimento, telefone);
                break;

            case "2":
                codigo = Integer.parseInt(inputValidado("Código do cliente a ser removido:", CadastroClienteTrabalho::validarCodigo));
                
                boolean removido = clientes.remove(codigo);
                if (!removido) {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado com o código " + codigo + ".", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "3":
                codigo = Integer.parseInt(inputValidado("Código do cliente a ser alterado:", CadastroClienteTrabalho::validarCodigo));
                Node cliente = clientes.retrieve(codigo);
                if (cliente != null) {
                    nome = inputValidado("Novo Nome:", CadastroClienteTrabalho::validarNome);
                    dataNascimento = inputValidado("Nova Data de Nascimento (DD/MM/AAAA):", CadastroClienteTrabalho::validarData);
                    telefone = inputValidado("Novo Telefone:", CadastroClienteTrabalho::validarTelefone);
                    clientes.update(codigo, nome, dataNascimento, telefone);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado com o código " + codigo + ".", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "4":
                codigo = Integer.parseInt(inputValidado("Código do cliente:", CadastroClienteTrabalho::validarCodigo));
                cliente = clientes.retrieve(codigo);
                if (cliente != null) {
                    String mensagem = "Código: " + cliente.codigo +
                                      "\nNome: " + cliente.nome +
                                      "\nData de Nascimento: " + cliente.dataNascimento +
                                      "\nTelefone: " + cliente.telefone;
                    JOptionPane.showMessageDialog(null, mensagem, "Dados do Cliente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado com o código " + codigo + ".", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "5":
                Node current = clientes.head;
                if (clientes.isEmpty()) {
                   JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder todosClientes = new StringBuilder();
                while (current != null) {
                todosClientes.append("Código: ").append(current.codigo)
                   .append(", Nome: ").append(current.nome)
                   .append(", Data de Nascimento: ").append(current.dataNascimento)
                   .append(", Telefone: ").append(current.telefone).append("\n");
                    current = current.next;
                }
                 JOptionPane.showMessageDialog(null, todosClientes.toString(), "Todos os Clientes", JOptionPane.INFORMATION_MESSAGE);
                }
                break;

            case "6":
               JOptionPane.showMessageDialog(null, "Saindo...", "Sair", JOptionPane.INFORMATION_MESSAGE);
               System.exit(0);

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
             break;
            }
        }
    }
}