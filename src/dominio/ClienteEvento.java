package dominio;

public class ClienteEvento {
    private Cliente cliente;
    private Evento evento;

    public ClienteEvento(){

    }

    public ClienteEvento(Cliente cliente, Evento evento) {
        this.cliente = cliente;
        this.evento = evento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
