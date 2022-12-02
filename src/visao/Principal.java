package visao;

import dominio.Evento;
import dominio.Pessoa;
import dominio.Cliente;
import dominio.Ingresso;
import persistencia.ClienteDAO;
import persistencia.EventoDAO;
import persistencia.IngressoDAO;
import persistencia.PessoaDAO;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args){
        ClienteDAO daoCliente = new ClienteDAO();
        IngressoDAO daoIngresso = new IngressoDAO();
        EventoDAO daoEvento = new EventoDAO();
        PessoaDAO daoPessoa = new PessoaDAO();

        ArrayList<Cliente> clientes = daoCliente.listar();
        System.out.println("---- CLIENTES ----");
        for(Cliente c: clientes){
            System.out.println("\nNome: " + c.getNome());
            System.out.println("CPF: " + c.getCpf());
            System.out.println("Tel: " + c.getTelefone());
            System.out.println("E-mail: " + c.getEmail());
            System.out.println("Login: " + c.getLogin());
            System.out.println("Senha: " + c.getSenha());
        }

        ArrayList<Pessoa> pessoas = daoPessoa.listar();
        System.out.println("\n---- PESSOAS ----");
        for(Pessoa p: pessoas){
            System.out.println("\nNome: " + p.getNome());
            System.out.println("CPF: " + p.getCpf());
        }

        ArrayList<Evento> eventos = daoEvento.listar();
        System.out.println("\n---- EVENTOS ----");
        for(Evento e: eventos){
            System.out.println("\nID: " + e.getId());
            System.out.println("Nome: " + e.getNomeEvento());
            System.out.println("Data/Hora: " + e.getData() + " " + e.getHora());
            System.out.println("Capacidade: " + e.getCapacidade());
            System.out.println("Local: " + e.getRua() + ", " + e.getNumero() + ", " + e.getBairro() + " - " + e.getCidade() + "/" + e.getUF());
            System.out.println("Preço: R$ " + e.getPreco());
        }

        ArrayList<Ingresso> ingressos = daoIngresso.listar();
        System.out.println("\n---- INGRESSOS ----");
        for(Ingresso i: ingressos){
            System.out.println("\nID: "+ i.getId());
            System.out.println("Nome: " + i.getMeuEvento().getNomeEvento());
            System.out.println("Hora: " + i.getMeuEvento().getHora());
            System.out.println("Local: " + i.getMeuEvento().getRua() + ", " + i.getMeuEvento().getNumero() + ", " + i.getMeuEvento().getBairro() + ", " + i.getMeuEvento().getCidade() + "/" + i.getMeuEvento().getUF());
            System.out.println(i.getMinhaPessoa().getNome());
        }
    }
}