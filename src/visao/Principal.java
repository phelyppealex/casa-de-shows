package visao;

import dominio.Evento;
import dominio.Cliente;
import dominio.Ingresso;
import persistencia.ClienteDAO;
import persistencia.EventoDAO;
import persistencia.IngressoDAO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args){
        IngressoDAO daoIngresso = new IngressoDAO();
        ArrayList<Ingresso> tickets = daoIngresso.listar();

        System.out.println("---- INGRESSOS ----");
        for(Ingresso i: tickets){
            System.out.println("\nId: "+ i.getId());
            System.out.println("Nome: " + i.getMeuEvento().getNomeEvento());
            System.out.println("Hora: " + i.getMeuEvento().getHora());
            System.out.println("Local: " + i.getMeuEvento().getRua() + ", " + i.getMeuEvento().getNumero() + ", " + i.getMeuEvento().getBairro() + ", " + i.getMeuEvento().getCidade() + "/" + i.getMeuEvento().getUF());
            System.out.println(i.getMinhaPessoa().getNome());
        }
    }
}