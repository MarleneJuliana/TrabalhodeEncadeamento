/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.cadastroclientetrabalho;

/**
 *
 * @author Marlene Juliana
 */
//Gerencia a lista duplamente encadeada com métodos para adicionar, remover, atualizar, recuperar e exibir clientes.
public class DoublyLinkedList {
 
    Node head;
    private Node tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }
//O método isEmpty verifica se a lista está vazia.
    public boolean isEmpty() {
        return head == null;
    }

    public void add(int codigo, String nome, String dataNascimento, String telefone) {
        Node newNode = new Node(codigo, nome, dataNascimento, telefone);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        System.out.println("Cliente " + nome + " adicionado com sucesso!");
    }

    public boolean remove(int codigo) {
        Node current = head;
        while (current != null) {
            if (current.codigo == codigo) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                System.out.println("Cliente " + current.nome + " removido com sucesso!");
                return true;
            }
            current = current.next;
        }
        System.out.println("Nenhum cliente encontrado com o código " + codigo + ".");
        return false;
    }
      //Método update
     // atualiza os dados de um cliente com base no código. 
    //Ele percorre a lista procurando o nó com o código especificado e atualiza suas informações.
    public boolean update(int codigo, String nome, String dataNascimento, String telefone) {
        Node current = head;
        while (current != null) {
            if (current.codigo == codigo) {
                current.nome = nome;
                current.dataNascimento = dataNascimento;
                current.telefone = telefone;
                System.out.println("Cliente " + codigo + " atualizado com sucesso!");
                return true;
            }
            current = current.next;
        }
        System.out.println("Nenhum cliente encontrado com o código " + codigo + ".");
        return false;
    }
    //Método retrieve
    //Este método recupera os dados de um cliente com base no código
    public Node retrieve(int codigo) {
    Node current = head;
    while (current != null) {
        if (current.codigo == codigo) {
            return current;
        }
        current = current.next;
    }
    return null;
    }
    //displayAll, Este método exibe todos os clientes na lista.
    public void displayAll() {
        Node current = head;
        if (isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        while (current != null) {
            System.out.println("Código: " + current.codigo + ", Nome: " + current.nome + ", Data de Nascimento: " + current.dataNascimento + ", Telefone: " + current.telefone);
            current = current.next;
        }
    }
}
