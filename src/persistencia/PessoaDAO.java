package persistencia;

import java.sql.*;
import dominio.Pessoa;

public class PessoaDAO {
    private Conexao c;
    private final String BSC = "SELECT * FROM pessoa WHERE cpf=?";

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
            System.out.println("Erro na busca de pessoa" + e.getMessage());
        }
        return people;
    }
}
