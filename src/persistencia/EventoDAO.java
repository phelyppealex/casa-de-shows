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
    private final String DEL = "DELETE FROM evento WHERE id=?";
    private final String INS = "INSERT INTO evento(nomeevento,data_event,hora,capacidade,rua,numero,bairro,cidade,uf,preco) VALUES (?,?,?,?,?,?,?,?,?,?)";

    public EventoDAO(){
        c = new Conexao();
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
            System.out.println("Erro no relatório do evento - " + e.getMessage());
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
            System.out.println("Erro na busca do evento - " + e.getMessage());
        }
        return event;
    }

    public void inserir(Evento event){
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(INS);
            instrucao.setString(1,event.getNomeEvento());
            instrucao.setString(2,event.getData());
            instrucao.setString(3,event.getHora());
            instrucao.setInt(4,event.getCapacidade());
            instrucao.setString(5,event.getRua());
            instrucao.setInt(6,event.getNumero());
            instrucao.setString(7,event.getBairro());
            instrucao.setString(8,event.getCidade());
            instrucao.setString(9,event.getUF());
            instrucao.setDouble(10,event.getPreco());
            instrucao.execute();
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao inserir evento - " + e.getMessage());
        }
    }

    public void deletar(int id){
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(DEL);
            instrucao.setInt(1,id);
            instrucao.execute();
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao deletar evento - " + e.getMessage());
        }
    }

    public void atualizar(Evento evento){
        Evento e = buscar(evento.getId());
        deletar(e.getId());
        inserir(evento);
    }
}