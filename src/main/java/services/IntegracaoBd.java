package services;

import server.Agenda;
import server.Contato;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class IntegracaoBd {

    static public void integraBd(Agenda agenda){

        String path = "/home/gabriel/IdeaProjects/bd.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            String linha = br.readLine();

            while (linha != null) {

                String [] cont = linha.split("/");
                agenda.getContatos().add(new Contato(cont[0], cont[1]));
                linha = br.readLine();

            }

            System.out.println("Banco 100% integrado");

        } catch (IOException e){

            System.err.println("Erro na integracao com o banco de dados " + e.getMessage());
        }

    }

}
