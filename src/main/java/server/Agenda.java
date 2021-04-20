package server;

import java.util.ArrayList;

public class Agenda {

    private ArrayList<Contato> contatos = new ArrayList();

    public Agenda() {
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setNumeros(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public void addNumero(Contato contato){
        contatos.add(contato);
    }

    public void removeNumero(Contato contato){
        contatos.remove(contato);
    }
}
