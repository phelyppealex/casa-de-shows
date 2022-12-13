package persistencia;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dominio.Cliente;
import dominio.ClienteEvento;
import dominio.Evento;

public class ClienteEventoDAO {
    private final String INS = "INSERT INTO ClienteEvento(fk_cliente,fk_evento) VALUES (?,?)";
    private final String REL = "SELECT * FROM ClienteEvento";
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
        ArrayList<ClienteEvento> lista = null;
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

    
}
