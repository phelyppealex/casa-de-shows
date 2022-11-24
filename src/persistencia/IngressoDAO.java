package persistencia;

import java.sql.*;
import java.util.ArrayList;
import dominio.Evento;
import dominio.Ingresso;
import dominio.Pessoa;

public class IngressoDAO {
    private Conexao c;
    private final String REL = "SELECT * FROM ingresso";

    public IngressoDAO(){
        c = new Conexao("jdbc:postgresql://localhost:5432/BDCasaDeFestas","postgres","1234");
    }

    public ArrayList<Ingresso> listar(){
        Ingresso ticket;
        EventoDAO daoEvent = new EventoDAO();
        Evento event;
        PessoaDAO daoPeople = new PessoaDAO();
        Pessoa people;
        ArrayList<Ingresso> lista = new ArrayList<Ingresso>();

        try{
            c.conectar();
            Statement instrucao = c.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(REL);
            while(rs.next()){
                event = daoEvent.buscar(rs.getInt("fk_evento"));
                people = daoPeople.buscar(rs.getString("fk_pessoa"));
                ticket = new Ingresso(rs.getInt("id"), event, people);

                lista.add(ticket);
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao listar Ingressos" + e.getMessage());
        }
        return lista;
    }
}
