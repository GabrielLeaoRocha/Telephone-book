package services;

import server.Agenda;
import server.Contato;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AtualizaBd {

    public static boolean atualizaBd(Agenda agenda, Contato contato){

        boolean atualizado;
        String path = "/home/gabriel/IdeaProjects/bd.txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path,true))){

            bw.write(contato.getNome() + "/" + contato.getNumero());

            System.out.printf("Contato " + contato.getNome() + " sincronizado");

            atualizado = true;

        } catch (IOException e){

            System.out.println("Erro na atualizacao do Banco de dados " + e.getMessage());
            atualizado = false;
        }

        return atualizado;
    }
}
