package server;

import connections.Conexao;
import services.AtualizaBd;

import java.net.ServerSocket;
import java.net.Socket;

import static services.IntegracaoBd.integraBd;

public class Servidor {

    static ServerSocket serverSocket;
    static Socket clienteSocket;
    static Conexao c;

    //contrutor
    public Servidor(){
        try{

            serverSocket = new ServerSocket(12313);
            System.out.println("Server Socket criado");

        } catch (Exception e){

            System.err.println("Server Socket não criado");
        }
    }

    //Main
    public static void main(String[]args){

        Agenda agenda = new Agenda();
        new Servidor();
        integraBd(agenda);

        for(;;){ //looping infinito

            if(connect()) {

                String texto = c.recebe(clienteSocket); //recebe mensagem crua
                String[] msg = texto.split("/");

                int opcao = Integer.parseInt(msg[0]);

                switch (opcao) {

                    case 1:  //opcao 1: consulta

                        System.out.println("Req.: " + msg[1]);
                        boolean retorno = false;

                        for (Contato cont : agenda.getContatos()) {

                            if (msg[1].trim().equals(cont.getNome().trim())) {

                                c.envia(clienteSocket, cont.toString());
                                System.out.println("Envio: " + cont.getNumero());
                                retorno = true;

                            }

                        }

                        if (!retorno) { //caso nao tenha encontrado nenhum

                            c.envia(clienteSocket, "Nennhum usuário encontrado");
                        }

                     break;

                    case 2: //opcao 2: cadastro

                        Contato contato = new Contato(msg[1], msg[2].trim());
                        agenda.addNumero(contato);
                        AtualizaBd.atualizaBd(agenda, contato);
                        c.envia(clienteSocket, "Contato adicionado");

                    break;

                } //fecha switch

                exitConnect();

            }   //fecha if(connect())
        }       //fecha looping infinito
    }           //fecha metodo


    static boolean connect(){

        boolean retorno;

        try{

            clienteSocket = serverSocket.accept();
            retorno = true;

        } catch (Exception e){

            System.err.println("Não fez conexão: " + e.getMessage());
            retorno = false;
        }

        return retorno;
    }


    static void exitConnect(){

        try{

            clienteSocket.close();

        } catch (Exception e){

            System.out.println("Não encerrou conexão: " + e.getMessage());
        }
    }
}
