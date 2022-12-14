package visao;

import dominio.Evento;
import dominio.Pessoa;
import dominio.Cliente;
import dominio.ClienteEvento;
import dominio.Ingresso;
import persistencia.ClienteDAO;
import persistencia.ClienteEventoDAO;
import persistencia.EventoDAO;
import persistencia.IngressoDAO;
import persistencia.PessoaDAO;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ClienteDAO daoCliente = new ClienteDAO();
        EventoDAO daoEvento = new EventoDAO();
        IngressoDAO daoIngresso = new IngressoDAO();
        PessoaDAO daoPessoa = new PessoaDAO();
        ClienteEventoDAO daoClienteEvento = new ClienteEventoDAO();

        Scanner sc = new Scanner(System.in);
        int resposta, numAux, opAux;
        String varAux = new String();

        do {
            System.out.println("\n   MENU PRINCIPAL");
            System.out.println("--------------------");
            System.out.println("1- LOGIN CLIENTE");
            System.out.println("2- CADASTRO CLIENTE");
            System.out.println("3- MOSTRAR CLIENTES");
            System.out.println("4- COMPRA DE INGRESSO");
            System.out.println("5- CONTATO (SAC)");
            System.out.println("0- SAIR\n");
            resposta = sc.nextInt();

            String login, senha = "";

            switch (resposta) {
                // LOGIN DO CLIENTE - MENU PRINCIPAL
                case 1:
                    System.out.println();
                    sc.nextLine();
                    System.out.println("       LOGIN");
                    System.out.println("---------------------");
                    System.out.print("DIGITE SEU CPF: ");
                    login = sc.nextLine();
                    System.out.print("DIGITE SUA SENHA: ");
                    senha = sc.nextLine();

                    daoCliente.validarLogin(login, senha);

                    int respostaCliente;

                    do {
                        if (daoCliente.buscar(login) != null) {
                            System.out.println("\n   MENU DO CLIENTE");
                            System.out.println("---------------------");
                            System.out.println("1- CRIAR EVENTO");
                            System.out.println("2- MOSTRAR MEUS EVENTOS");
                            System.out.println("3- EDITAR EVENTO");
                            System.out.println("4- REMOVER EVENTO");
                            System.out.println("5- DELETAR CONTA");
                            System.out.println("0- VOLTAR\n");

                            respostaCliente = sc.nextInt();
                        } else {
                            System.out.println("Esta conta não existe mais!");
                            respostaCliente = 0;
                        }

                        int idEvento;

                        switch (respostaCliente) {
                            // CRIAR EVENTO - MENU DO CLIENTE
                            case 1:
                                Evento event = new Evento();
                                System.out.println("\n  CADASTRO DE EVENTO");
                                System.out.println("----------------------");
                                event.setId(0);

                                System.out.print("DESCRIÇÃO DO EVENTO: ");
                                sc.nextLine();
                                event.setNomeEvento(sc.nextLine());

                                System.out.print("DATA: ");
                                event.setData(sc.nextLine());

                                System.out.print("HORA: ");
                                event.setHora(sc.nextLine());

                                System.out.print("CAPACIDADE: ");
                                event.setCapacidade(sc.nextInt());

                                System.out.print("PREÇO DO INGRESSO: ");
                                event.setPreco(sc.nextDouble());

                                System.out.println("\n<<LOCALIDADE>>");
                                System.out.print("RUA: ");
                                sc.nextLine();
                                event.setRua(sc.nextLine());

                                System.out.print("NÚMERO: ");
                                event.setNumero(sc.nextInt());

                                System.out.print("BAIRRO: ");
                                sc.nextLine();
                                event.setBairro(sc.nextLine());

                                System.out.print("CIDADE: ");
                                event.setCidade(sc.nextLine());

                                System.out.print("UF: ");
                                event.setUF(sc.nextLine());

                                daoEvento.inserir(event);
                                Cliente clienteLogado = daoCliente.buscar(login);
                                Evento ultimoEvento = daoEvento.pegarUltimo();
                                ClienteEvento clienteEvento = new ClienteEvento(clienteLogado, ultimoEvento);
                                daoClienteEvento.inserir(clienteEvento);

                                break;
                            // LISTAR EVENTOS - MENU DO CLIENTE
                            case 2:
                                ArrayList<ClienteEvento> listaEventos = daoClienteEvento.filtrarPorCliente(login);
                                System.out.println("\n    MEUS EVENTOS");
                                System.out.println("---------------------");

                                for (ClienteEvento e : listaEventos) {
                                    System.out.println(
                                            "ID: " + e.getEvento().getId() +
                                                    "\n" + e.getEvento().getNomeEvento() +
                                                    "\n" + e.getEvento().getData() + ", " + e.getEvento().getHora() +
                                                    "\nCapacidade: " + e.getEvento().getCapacidade() +
                                                    "\nIngresso: R$ " + e.getEvento().getPreco() +
                                                    "\nLocal: " + e.getEvento().getRua() + ", "
                                                    + e.getEvento().getNumero() +
                                                    ", " + e.getEvento().getBairro() + " - " + e.getEvento().getCidade()
                                                    + "/" + e.getEvento().getUF() + "\n" +
                                                    "-----------------------------------------------");
                                }
                                break;
                            // EDITAR EVENTO - MENU CLIENTE
                            case 3:
                                System.out.println("    EDITAR EVENTO");
                                System.out.println("----------------------");
                                System.out.println("QUAL O ID DO EVENTO (NÚMERO INTEIRO) QUE DESEJA EDITAR?");
                                idEvento = sc.nextInt();

                                Evento e = daoEvento.buscar(idEvento);

                                if (e != null) {
                                    System.out.println(
                                            "-----------------------------------------------\n" +
                                                    "ID: " + e.getId() +
                                                    "\n" + e.getNomeEvento() +
                                                    "\n" + e.getData() + ", " + e.getHora() +
                                                    "\nCapacidade: " + e.getCapacidade() +
                                                    "\nIngresso: R$ " + e.getPreco() +
                                                    "\nLocal: " + e.getRua() + ", " + e.getNumero() + ", "
                                                    + e.getBairro() + " - " + e.getCidade() + "/" + e.getUF() + "\n" +
                                                    "-----------------------------------------------");
                                }

                                System.out.println("1-NOME");
                                System.out.println("2-DATA");
                                System.out.println("3-HORA");
                                System.out.println("4-CAPACIDADE");
                                System.out.println("5-RUA");
                                System.out.println("6-NUMERO");
                                System.out.println("7-BAIRRO");
                                System.out.println("8-CIDADE");
                                System.out.println("9-UF");
                                System.out.println("10-PRECO DO INGRESSO");
                                System.out.println("0-SAIR");
                                respostaCliente = sc.nextInt();

                                System.out.println("Insira o novo valor:");
                                switch (respostaCliente) {
                                    case 1:
                                        e.setNomeEvento(sc.nextLine());
                                        break;
                                    case 2:
                                        e.setData(sc.nextLine());
                                        break;
                                    case 3:
                                        e.setHora(sc.nextLine());
                                        break;
                                    case 4:
                                        e.setCapacidade(sc.nextInt());
                                        break;
                                    case 5:
                                        e.setRua(sc.nextLine());
                                        break;
                                    case 6:
                                        e.setNumero(sc.nextInt());
                                        break;
                                    case 7:
                                        e.setBairro(sc.nextLine());
                                        break;
                                    case 8:
                                        e.setCidade(sc.nextLine());
                                        break;
                                    case 9:
                                        e.setUF(sc.nextLine());
                                        break;
                                    case 10:
                                        e.setPreco(sc.nextDouble());
                                        break;
                                }
                                daoEvento.atualizar(e);
                                break;
                            // REMOÇÃO DE EVENTO - MENU DO CLIENTE
                            case 4:
                                System.out.println("REMOVER EVENTO");
                                System.out.println("--------------");

                                System.out.print("ID DO EVENTO A SER REMOVIDO: ");
                                idEvento = sc.nextInt();

                                System.out.print("TEM CERTEZA? 1-SIM 0-VOLTAR: ");
                                if (sc.nextInt() == 1) {
                                    daoEvento.deletar(idEvento);
                                }
                                break;
                            case 5:
                                daoCliente.deletar(login);
                                break;
                        }
                    } while (respostaCliente != 0);
                    break;
                // CADASTRO DO CLIENTE - MENU PRINCIPAL
                case 2:
                    Cliente client = new Cliente();
                    System.out.println("\nCADASTRO DE CLIENTE");
                    System.out.println("---------------------");

                    System.out.print("\nNOME COMPLETO: ");
                    sc.nextLine();
                    client.setNome(sc.nextLine());

                    System.out.print("\nCPF: ");
                    client.setCpf(sc.nextLine());

                    System.out.print("\nTELEFONE: ");
                    client.setTelefone(sc.nextLine());

                    System.out.print("\nE-MAIL: ");
                    client.setEmail(sc.nextLine());

                    System.out.print("\nSENHA: ");
                    client.setSenha(sc.nextLine());

                    daoCliente.inserir(client);
                    break;
                // MOSTRAR CLIENTES - MENU PRINCIPAL
                case 3:
                    ArrayList<Cliente> lClientes = daoCliente.listar();
                    System.out.println("\n  LISTA DE CLIENTES");
                    System.out.print("-------------------------");

                    for (Cliente c : lClientes) {
                        System.out.println(
                                "\nNome: " + c.getNome() +
                                        "\nCPF: " + c.getCpf() +
                                        "\nContato: " + c.getTelefone() + " - " + c.getEmail() +
                                        "\n------------------------------------------");
                    }
                    break;
                // COMPRA DE INGRESSO - MENU PRINCIPAL
                case 4:
                    ArrayList<Evento> eventos;
                    Pessoa pessoa = new Pessoa();
                    Ingresso ticket;
                    System.out.println("Digite o CPF da pessoa");
                    sc.nextLine();
                    varAux = sc.nextLine();
                    if (daoPessoa.buscar(varAux) == null) {
                        System.out.println("Pessoa não cadastrada");
                        System.out.println("Cadastrando pessoa...");
                        System.out.println("Digite o nome da pessoa");
                        pessoa.setNome(sc.nextLine());
                        pessoa.setCpf(varAux);
                        daoPessoa.inserir(pessoa);

                        eventos = new ArrayList<Evento>();
                        eventos = daoEvento.listar();

                        for (Evento e : eventos) {
                            System.out.println(e.getNomeEvento() + " | " + e.getData());
                        }

                        System.out.println("Deseja continuar a compra? 1-SIM 2-NÃO");
                        opAux = sc.nextInt();
                        if (opAux == 1) {
                            System.out.print("Digite o número do evento: ");
                            numAux = sc.nextInt();
                            Evento evento = null;
                            evento = daoEvento.buscar(numAux);
                            while (evento == null) {
                                System.out.println("Digite um número valido de um evento");
                                evento = daoEvento.buscar(sc.nextInt());
                            }
                            ticket = new Ingresso(evento, pessoa);
                            daoIngresso.inserir(ticket);
                        } else {
                            break;
                        }

                    } else {
                        eventos = new ArrayList<Evento>();
                        pessoa = daoPessoa.buscar(varAux);
                        eventos = daoEvento.listar();

                        for (Evento e : eventos) {
                            System.out.println(e.getId() + "- " + e.getNomeEvento() + " | " + e.getData());
                        }

                        System.out.println("Deseja continuar a compra? 1-SIM 2-NÃO");
                        opAux = sc.nextInt();
                        if (opAux == 2) {
                            break;
                        }
                        System.out.print("Digite o número do evento: ");
                        numAux = sc.nextInt();
                        Evento evento;
                        evento = daoEvento.buscar(numAux);
                        ticket = new Ingresso(evento, pessoa);
                        daoIngresso.inserir(ticket);
                    }
                    break;
                // TELEFONES PARA CONTATO - MENU PRINCIPAL
                case 5:
                    System.out.println("SAC");
                    System.out.println("----------------------------------");
                    System.out.println("Zona metropolitana (84) 99410-0804");
                    System.out.println("Demais localidades (84) 98170-7242");
                    break;
            }
        } while (resposta != 0);
    }
}