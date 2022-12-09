package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private String usuario;
    private String senha;
    private String caminho;
    private Connection minhaConexao;

    public Conexao(){
        this.caminho = "jdbc:postgresql://localhost:5432/BDCasaDeFestas";
        this.usuario = "postgres";
        this.senha = "1234";
    }

    public Conexao(String caminho, String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
        this.caminho = caminho;
    }

    public void conectar(){
        try{
            Class.forName("org.postgresql.Driver");
            minhaConexao = DriverManager.getConnection(caminho, usuario, senha);
        }catch (Exception e){
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }

    public Connection getConexao(){
        return minhaConexao;
    }

    public void desconectar(){
        try{
            minhaConexao.close();
        }catch(Exception e){
            System.out.println("Erro ao desconectar: "+ e.getMessage());
        }
    }
}
