package dominio;

public class Ingresso {
    private int id;
    private Evento meuEvento;
    private Pessoa minhaPessoa;

    public Ingresso(){

    }

    public Ingresso(int id, Evento meuEvento, Pessoa minhaPessoa){
        this.id = id;
        this.meuEvento = meuEvento;
        this.minhaPessoa = minhaPessoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evento getMeuEvento() {
        return meuEvento;
    }

    public void setMeuEvento(Evento meuEvento) {
        this.meuEvento = meuEvento;
    }

    public Pessoa getMinhaPessoa() {
        return minhaPessoa;
    }

    public void setMinhaPessoa(Pessoa minhaPessoa) {
        this.minhaPessoa = minhaPessoa;
    }
}
