package visao;

import dominio.Evento;
import dominio.Pessoa;
import dominio.Cliente;
import dominio.Ingresso;
import persistencia.ClienteDAO;
import persistencia.EventoDAO;
import persistencia.IngressoDAO;
import persistencia.PessoaDAO;
import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.events.Event;

public class Principal {
    public static void main(String[] args){
        ClienteDAO daoCliente = new ClienteDAO();
        EventoDAO daoEvento = new EventoDAO();
        IngressoDAO daoIngresso = new IngressoDAO();
        PessoaDAO daoPessoa = new PessoaDAO();
        
        int respostaAUX = 0;
        Scanner sc = new Scanner(System.in);
        String resposta = "";
        
        do{
            System.out.println("MENU PRINCIPAL");
            System.out.println("---------------");
            System.out.println("1- LOGIN CLIENTE");
            System.out.println("2- CADASTRO CLIENTE");
            System.out.println("3- COMPRA DE INGRESSO");
            System.out.println("4- CONTATO (SAC)");
            System.out.println("0- SAIR");
            resposta = sc.nextLine();

            String login,senha = "";

            switch(resposta){
                // LOGIN DO CLIENTE - MENU PRINCIPAL
                case "1":
                    System.out.print("\nDIGITE SEU LOGIN: ");
                    login = sc.nextLine();
                    System.out.print("DIGITE SUA SENHA: ");
                    senha = sc.nextLine();

                    System.out.println("MENU DO CLIENTE");
                    System.out.println("-----------------");
                    System.out.println("1- CRIAR EVENTO");
                    System.out.println("2- LISTAR EVENTOS");
                    System.out.println("3- EDITAR EVENTO");
                    System.out.println("4- REMOVER EVENTO");
                    
                    String respostaCliente = sc.nextLine();

                    switch(respostaCliente){
                        case "1":
                            Evento event = new Evento();
                            System.out.println("CADASTRO DE EVENTO");
                            System.out.println("------------------");
                            event.setId(0);

                            System.out.print("DESCRIÇÃO DO EVENTO:");
                            event.setNomeEvento(sc.nextLine());
                            
                            System.out.print("DATA: ");
                            event.setData(sc.nextLine());

                            System.out.print("HORA: ");
                            event.setHora(sc.nextLine());

                            System.out.print("CAPACIDADE: ");
                            event.setCapacidade(sc.nextInt());

                            System.out.print("PREÇO DO INGRESSO: ");
                            event.setPreco(sc.nextDouble());

                            System.out.println("LOCALIDADE=");
                            System.out.print("RUA: ");
                            event.setRua(sc.nextLine());

                            System.out.print("NÚMERO: ");
                            event.setNumero(sc.nextInt());

                            System.out.print("BAIRRO: ");
                            event.setBairro(sc.nextLine());
                            
                            System.out.print("CIDADE: ");
                            event.setCidade(sc.nextLine());
                            
                            System.out.print("UF: ");
                            event.setUF(sc.nextLine());

                            daoEvento.inserir(event);
                            System.out.println("INSERIDO COM SUCESSO");
                        break;
                        case "2":
                            ArrayList<Evento> listaEventos = daoEvento.listar();
                            System.out.println("MEUS EVENTOS");
                            System.out.println("--------------------");

                            for(Evento e: listaEventos){
                                System.out.println(
                                    "ID: " + e.getId() +
                                    "\n" + e.getNomeEvento() +
                                    "\n" + e.getData() + ", " + e.getHora() +
                                    "\nCapacidade: " + e.getCapacidade() +
                                    "\nIngresso: R$ " + e.getPreco() +
                                    "\nLocal: " + e.getRua() + ", " + e.getNumero() + ", " + e.getBairro() + " - " + e.getCidade() + "/" + e.getUF() + "\n"
                                );
                            }
                        case "3": 
                            System.out.println("EDITAR EVENTO");
                            System.out.println("-------------");
                            System.out.println("QUAL O ID DO EVENTO  QUE DESEJA EDITAR?");
                            respostaAUX = sc.nextInt();
                            do{
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
                                respostaCliente = sc.nextLine();
                                switch(respostaCliente){
                                    case "1":
                                    break;
                                    case "2":
                                    break;
                                    case "3":
                                    break;
                                    case "4":
                                    break;
                                    case "5":
                                    break;
                                    case "6":
                                    break;
                                    case "7":
                                    break;
                                    case "8":
                                    break;
                                    case "9":
                                    break;
                                    case "10":
                                    break;
                                }


                            }while(respostaCliente!="0");

                        break;
                        
                        case "4":
                            System.out.println("REMOVER EVENTO");
                            System.out.println("--------------");
                            
                            System.out.print("ID DO EVENTO A SER REMOVIDO: ");
                            respostaAUX = sc.nextInt();
                            
                            System.out.print("TEM CERTEZA? 1-SIM 0-NAO: ");
                            respostaCliente = sc.nextLine();
                            
                            if(respostaCliente != "1"){
                                daoEvento.deletar(respostaAUX);
                            }
                        break;
                    }
                    
                break;
                // CADASTRO DO CLIENTE - MENU PRINCIPAL
                case "2":
                    Cliente client = new Cliente();    
                    System.out.println("\nCADASTRO DE CLIENTE");
                    System.out.println("-------------------");
                    
                    System.out.print("\nUSUARIO DE LOGIN: ");
                    client.setLogin(sc.nextLine());

                    System.out.print("\nSENHA: ");
                    client.setSenha(sc.nextLine());

                    System.out.print("\nNOME COMPLETO: ");
                    client.setNome(sc.nextLine());

                    System.out.print("\nCPF: ");
                    client.setCpf(sc.nextLine());
                    
                    System.out.print("\nTELEFONE:");
                    client.setCpf(sc.nextLine());
                    
                    System.out.print("\nE-MAIL:");
                    client.setCpf(sc.nextLine());

                    daoCliente.inserir(client);
                    System.out.println("INSERIDO COM SUCESSO!");
                    
                break;
                // COMPRA DE INGRESSO - MENU PRINCIPAL
                case "3":
                    do{
                        ArrayList<Evento> listaEventos = daoEvento.listar();
                        System.out.println("EVENTOS");
                        System.out.println("----------");
                        for(int i = 0; i < listaEventos.size(); i++){
                            System.out.println((i+1) + "- " + listaEventos.get(i).getNomeEvento());
                        }
                        respostaAUX = sc.nextInt();
                        System.out.println("ENTRADA PARA");
                        
                        System.out.print("CONTINUAR? 0-NAO 1-SIM: ");
                        resposta = sc.nextLine();

                    }while(resposta != "0");
                    
                break;
                // TELEFONES PARA CONTATO - MENU PRINCIPAL
                case "4":
                    System.out.println("SAC");
                    System.out.println("----------");
                    System.out.println("Zona Metropolitana (84) 99410-0804");
                    System.out.println("Demais localidades (84) 98170-7242");
                    System.out.println("1 - Voltar");

                break;
            }
        }while(resposta != "0");
    }
}