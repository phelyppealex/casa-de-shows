package persistencia;

import java.sql.*;
import java.util.ArrayList;

import dominio.Pessoa;

public class PessoaDAO {
    private Conexao c;
    private final String REL = "SELECT * FROM pessoa";
    private final String BSC = "SELECT * FROM pessoa WHERE cpf=?";
    private final String INS = "INSERT INTO pessoa(cpf,nome) VALUES (?,?)";
    private final String DEL = "DELETE FROM pessoa WHERE cpf=?";

    public PessoaDAO() {
        c = new Conexao("jdbc:postgresql://localhost:5432/BDCasaDeFestas","postgres","1234");
    }

    public Pessoa buscar(String cpf){
        Pessoa people = null;
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(BSC);
            instrucao.setString(1, cpf);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                people = new Pessoa(
                    rs.getString("cpf"),
                    rs.getString("nome")
                );
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro na busca de pessoa - " + e.getMessage());
        }
        return people;
    }

    public ArrayList<Pessoa> listar(){
        ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
        Pessoa people;
        try{
            c.conectar();
            Statement instrucao = c.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(REL);
            while(rs.next()){
                people = new Pessoa(
                    rs.getString("cpf"),
                    rs.getString("nome")
                );
                lista.add(people);
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro na emissão de relatório de Pessoa - " + e.getMessage());
        }
        return lista;
    }

    public void inserir(Pessoa people){
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(INS);
            instrucao.setString(1,people.getCpf());
            instrucao.setString(2,people.getNome());
            instrucao.execute();
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao inserir Pessoa - "+ e.getMessage());
        }
    }

    public void deletar(String cpf){
        try{
            c.conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(DEL);
            instrucao.setString(1, cpf);
            instrucao.execute();
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro na exclusão de Pessoa - " + e.getMessage());
        }
    }

    public void atualizar(Pessoa people){
        Pessoa p = buscar(people.getCpf());
        deletar(p.getCpf());
        inserir(people);
    }
}
