package persistencia;

import dominio.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO {
    private Conexao c;
    private String REL = "SELECT * FROM cliente";
    private String BSC = "SELECT * FROM cliente WHERE cpf=?";

    public ClienteDAO(){
        c = new Conexao("jdbc:postgresql://localhost:5432/BDCasaDeFestas","postgres","1234");
    }

    public ArrayList<Cliente> relatorio(){
        Cliente cliente;
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try{
            c.conectar();
            Statement instrucao = c.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(REL);
            while (rs.next()){
                cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"), rs.getString("login"), rs.getString("senha"));
                lista.add(cliente);
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao emitir relatório de Cliente");
        }
        return lista;
    }

    public Cliente buscar(String cpf){
        Cliente cliente = null;
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(BSC);
            instrucao.setString(1,cpf);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"), rs.getString("login"), rs.getString("senha"));
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao buscar Cliente");
        }
        return cliente;
    }
}
