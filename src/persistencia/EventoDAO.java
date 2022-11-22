package persistencia;

import dominio.Evento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EventoDAO {
    private Conexao c;
    private final String REL = "SELECT * FROM evento";
    private final String BSC = "SELECT * FROM evento WHERE id=?";

    public EventoDAO(){
        new Conexao("jdbc:postgresql://localhost:5432/BDCasaDeFestas","postgres","1234");
    }

    public ArrayList<Evento> listar(){
        Evento event;
        ArrayList<Evento> lista = new ArrayList<Evento>();
        try{
            c.conectar();
            Statement instrucao = c.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(REL);
            while(rs.next()){
                event = new Evento(
                        rs.getInt("id"), rs.getString("nomeevento"),
                        rs.getString("data_event"), rs.getString("hora"),
                        rs.getInt("capacidade"), rs.getString("rua"),
                        rs.getInt("numero"), rs.getString("bairro"),
                        rs.getString("cidade"), rs.getString("uf"),
                        rs.getDouble("preco")
                );
                lista.add(event);
            }
            c.desconectar();
        }catch (Exception e){
            System.out.println("Erro na inserção de evento");
        }
        return lista;
    }

    public Evento buscar(int id){
        Evento event = null;
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(BSC);
            instrucao.setInt(1,id);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                event = new Evento(
                        rs.getInt("id"), rs.getString("nomeevento"),
                        rs.getString("data_event"), rs.getString("hora"),
                        rs.getInt("capacidade"), rs.getString("rua"),
                        rs.getInt("numero"), rs.getString("bairro"),
                        rs.getString("cidade"), rs.getString("uf"),
                        rs.getDouble("preco")
                );
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro na busca do evento");
        }
        return event;
    }
}
