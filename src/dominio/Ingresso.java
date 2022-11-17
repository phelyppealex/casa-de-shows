package dominio;

public class Ingresso {
    private int id;
    private int fkEvento;
    private int fkPessoa;

    public Ingresso(){

    }

    public Ingresso(int id, int fkEvento, int fkPessoa){
        this.id = id;
        this.fkEvento = fkEvento;
        this.fkPessoa = fkPessoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkEvento() {
        return fkEvento;
    }

    public void setFkEvento(int fkEvento) {
        this.fkEvento = fkEvento;
    }

    public int getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(int fkPessoa) {
        this.fkPessoa = fkPessoa;
    }
}
