package persistencia;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import persistencia.*;
import dominio.Cliente;
import dominio.ClienteEvento;
import dominio.Evento;

public class ClienteEventoDAO {
    private final String INS = "INSERT INTO ClienteEvento(fk_cliente,fk_evento) VALUES (?,?)";
    private final String REL = "SELECT * FROM ClienteEvento";
    private final String FIL = "SELECT li.fk_cliente, li.fk_evento FROM ((cliente c JOIN clienteevento li ON li.fk_cliente = c.cpf) JOIN evento ev ON li.fk_evento = ev.id) WHERE (li.fk_cliente = c.cpf) AND (li.fk_evento = ev.id) AND (li.fk_cliente =  ?)";
    private Conexao c;  

    public ClienteEventoDAO(){
        c = new Conexao();
    }

    public void inserir(ClienteEvento ce){
        try {
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(INS);
            instrucao.setString(1, ce.getCliente().getCpf());
            instrucao.setInt(2, ce.getEvento().getId());
            if(instrucao.execute()){
                System.out.println("Inserção concluída com sucesso!");
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Erro na inserção - " + e.getMessage());
        }
    }

    public ArrayList<ClienteEvento> listar(){
        ArrayList<ClienteEvento> lista = new ArrayList<ClienteEvento>();
        ClienteDAO daoCliente = new ClienteDAO();
        EventoDAO daoEvento = new EventoDAO();
        ClienteEvento ce;
        
        try {
            c.conectar();
            Statement instrucao = c.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(REL);
            while(rs.next()){
                Cliente c = daoCliente.buscar(
                    rs.getString("fk_cliente")
                );
                Evento e = daoEvento.buscar(
                    rs.getInt("fk_evento")
                );
                ce = new ClienteEvento(c,e);
                lista.add(ce);
            }
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao inserir ClienteEvento - " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<ClienteEvento> filtrarPorCliente(String id){
        ArrayList<ClienteEvento> lista = new ArrayList<ClienteEvento>();
        ClienteEvento ce;
        ClienteDAO daoCliente = new ClienteDAO();
        EventoDAO daoEvento = new EventoDAO();
        Cliente client;
        Evento event;

        try {
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(FIL);
            instrucao.setString(1, id);
            ResultSet rs = instrucao.executeQuery();
            while(rs.next()){
                client = daoCliente.buscar(
                    rs.getString("fk_cliente")
                );
                event = daoEvento.buscar(
                    rs.getInt("fk_evento")
                );
                ce = new ClienteEvento(client, event);
                lista.add(ce);
            }
            c.desconectar();  
        } catch (Exception e) {
            System.out.println("Erro ao filtar ClienteEvento - "+ e.getMessage());
        }
        return lista;
    }    
}
