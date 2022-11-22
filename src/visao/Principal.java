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

        ArrayList<Cliente> listaC = daoC.relatorio();

        for(int i=0;i < listaC.size(); i++){
            System.out.println("\n"+listaC.get(i).getNome()+"\n"+listaC.get(i).getEmail()+"\n"+listaC.get(i).getTelefone());
        }
    }
}
