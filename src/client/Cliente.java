package client;

import connections.Conexao;
import services.IntegracaoBd;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static services.IntegracaoBd.integraBd;

public class Cliente {

    static Conexao c;
    static Socket socket;
    static Scanner sc = new Scanner(System.in);

    //constructor
    public Cliente() {
        try {

            socket = new Socket("localhost", 12313); //host PC = 192.168.1.119

        } catch (IOException e) {

            System.err.println("Nao consegui resolver o host!");
        }
    }

    //main
    public static void main(String[] args) {

        //new Cliente();
        String msg = "";

        try {

            System.out.print("Escolha entre:\n" +
                    "(1)consultar\n" +
                    "(2)add um numero\n" +
                    ": ");
            int escolha = sc.nextInt();

            System.out.print("Digite um nome: ");
            sc.nextLine(); //limpa buffer
            String nome = sc.nextLine();

            if (escolha == 1) {

                msg = escolha + "/" + nome; //concatena escolha+nome

            } else if (escolha == 2) {

                System.out.print("Digite o numero: ");
                String numero = sc.nextLine(); //recebe numero

                msg = escolha + "/" + nome + "/" + numero; //concatena escolha+nome+numero

            } else {

                System.err.println("Opção invalida");
            }

        } catch (Exception e) {

            System.err.println("Error, apenas números" + e.getMessage());
            msg = "Error";
        }

        c.envia(socket, msg); //envia para servidor

        String texto = c.recebe(socket); //recebe do servidor
        System.out.println(texto);

        exitConnect();
    }

    static void exitConnect(){

        try {

            socket.close(); //tenta encerrar conexão

        } catch (IOException e) {

            System.err.println("Não encerrou a conexão " + e.getMessage());
        }
    }

}
