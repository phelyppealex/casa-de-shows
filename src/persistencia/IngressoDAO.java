package persistencia;

import java.sql.*;
import java.util.ArrayList;
import dominio.Evento;
import dominio.Ingresso;
import dominio.Pessoa;

public class IngressoDAO {
    private Conexao c;
    private final String REL = "SELECT * FROM ingresso";
    private final String BSC = "SELECT * FROM ingresso WHERE id=?";

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
            System.out.println("Erro ao listar Ingressos - " + e.getMessage());
        }
        return lista;
    }

    public Ingresso buscar(int id){
        EventoDAO daoEvent = new EventoDAO();
        Evento event;
        PessoaDAO daoPeople = new PessoaDAO();
        Pessoa people;
        Ingresso ticket = null;
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(BSC);
            instrucao.setInt(1, id);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                event = daoEvent.buscar(rs.getInt("fk_evento"));
                people = daoPeople.buscar(rs.getString("fk_pessoa"));
                ticket = new Ingresso(rs.getInt("id"), event, people);
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro na busca do ingresso - " + e.getMessage());
        }
        return ticket;
    }
}