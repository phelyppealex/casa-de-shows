package visao;

import dominio.Cliente;
import persistencia.ClienteDAO;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args){
        ClienteDAO daoC = new ClienteDAO();

        //daoC.deletar("123");
        Cliente cliente = new Cliente("123","123","123","123","123","123");
        daoC.inserir(cliente);

        ArrayList<Cliente> listaC = daoC.listar();

        for(Cliente c:listaC){
            System.out.println("\n"+c.getNome()+"\n"+c.getEmail()+"\n"+c.getTelefone());
        }
    }
}
