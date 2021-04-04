package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class IntegracaoBd {

    static public void integraBd(){

        String path = "\\home\\gabriel\\IdeaProjects\\exeSistComp\\bd.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            String [] linha = br.readLine().split("/");
            System.out.println(linha[0] + " " + linha[1]);

        } catch (IOException e){
            System.err.println("Erro na integracao com o banco de dados");
        }

    }
}
