package persistencia;

import dominio.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO {
    private Conexao c;
    private final String REL = "SELECT * FROM cliente";
    private final String BSC = "SELECT * FROM cliente WHERE cpf=?";
    private final String DEL = "DELETE FROM cliente WHERE cpf=?";
    private final String INS = "INSERT INTO cliente(cpf,nome,telefone,email,login,senha) VALUES (?,?,?,?,?,?)";

    public ClienteDAO(){
        c = new Conexao("jdbc:postgresql://localhost:5432/BDCasaDeFestas","postgres","1234");
    }

    public ArrayList<Cliente> listar(){
        Cliente cliente;
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try{
            c.conectar();
            Statement instrucao = c.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(REL);
            while (rs.next()){
                cliente = new Cliente(
                        rs.getString("cpf"), rs.getString("nome"),
                        rs.getString("telefone"), rs.getString("email"),
                        rs.getString("login"), rs.getString("senha")
                );
                lista.add(cliente);
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao emitir relatório de Cliente" + e.getMessage());
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
                cliente = new Cliente(
                        rs.getString("cpf"), rs.getString("nome"),
                        rs.getString("telefone"), rs.getString("email"),
                        rs.getString("login"), rs.getString("senha")
                );
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao buscar Cliente");
        }
        return cliente;
    }

    public void deletar(String cpf){
        Cliente cliente = null;
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(DEL);
            instrucao.setString(1,cpf);
            instrucao.execute();
            c.desconectar();
        }catch (Exception e){
            System.out.println("Erro ao deletar cliente");
        }
    }

    public void inserir(Cliente cliente){
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(INS);
            instrucao.setString(1,cliente.getCpf());
            instrucao.setString(2,cliente.getNome());
            instrucao.setString(3,cliente.getTelefone());
            instrucao.setString(4,cliente.getEmail());
            instrucao.setString(5,cliente.getLogin());
            instrucao.setString(6,cliente.getSenha());
            instrucao.execute();
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro na inserção do cliente" + e.getMessage());
        }
    }
}


