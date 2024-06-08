/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.cadastroclientetrabalho;

/**
 *
 * @author Marlene Juliana
 */
//Representa um nó na lista duplamente encadeada.
public class Node {
   int codigo;
    String nome;
    String dataNascimento;
    String telefone;
    Node prev; //nó anterior
    Node next; //próximo nó
    
    //Cada nó contém informações do cliente 
    public Node(int codigo, String nome, String dataNascimento, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.prev = null; 
        this.next = null;
    }
}
