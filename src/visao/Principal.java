package visao;

import dominio.Cliente;
import persistencia.ClienteDAO;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args){
        ClienteDAO daoC = new ClienteDAO();

        Cliente listaC = daoC.buscar("708.654.223-41");

        //for(int i=0;i < listaC.size(); i++){
            System.out.println(listaC.getNome()+"\n"+listaC.getEmail()+"\n"+listaC.getTelefone()+"\n");
        //}
    }
}
