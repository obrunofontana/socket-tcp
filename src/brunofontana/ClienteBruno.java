package brunofontana;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class ClienteBruno {

    private String ip;
    private int porta;

    public ClienteBruno(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;

    }

    private void onInit() {

        try {
            InetAddress address = InetAddress.getByName(ip);

            try {

                Scanner ler = new Scanner(System.in);
                boolean sair = true;
                boolean startProgram = true;
                //isbn / titulo / autor / assunto
                String operacao = "", isbn, titulo, autor, assunto, salvarRegistro, mais = "";

                while (startProgram) {
                    //operacao: 1 - cadastrar 2 - consultar 3 - remover
                    boolean sairCadastro = true;
                    boolean sairConsulta = true;
                    boolean sairRemove = true;

                    System.out.println("Seja Bem-vindo à FontanaStore xD \n");
                    System.out.println("Digite a operação que deseja: \n");
                    System.out.println("1 - Cadastrar Livro \n");
                    System.out.println("2 - Consultar Livro \n");
                    System.out.println("3 - Remover Livro \n");
                    System.out.println("");
                    operacao = "";
                    operacao = ler.next();

                    switch (operacao) {
                        case "1":
                            while (sairCadastro) {
                                //Conecta o socket a porta do servidor
                                Socket socket = new Socket(ip, porta);

                                //Cria os Stream's de entrada e saída
                                DataOutputStream saida = new DataOutputStream(socket.getOutputStream()); // Saída                
                                DataInputStream entrada = new DataInputStream(socket.getInputStream()); // Entrada

                                System.out.println("Digite o titulo do livro: \n");
                                titulo = ler.next();
                                System.out.println("");
                                System.out.println("Digite o nome do Autor: \n");
                                autor = ler.next();
                                System.out.println("");
                                System.out.println("Digite o ISBN do Livro: \n");
                                isbn = ler.next();
                                System.out.println("");
                                System.out.println("Digite o assunto do Livro: \n");
                                assunto = ler.next();
                                System.out.println("");

                                salvarRegistro = "1 " + isbn + " " + titulo + " " + autor + " " + assunto;

                                saida.writeUTF(salvarRegistro);//Envio a mensagem para o servidor

                                //recebo/Aguardo a resposta do server
                                String response = entrada.readUTF();

                                System.out.println("Resposta do servidor " + response);

                                System.out.println("Deseja cadastrar mais? S/N ");
                                mais = ler.next();

                                if (mais.toUpperCase().equals("S")) {
                                    sairCadastro = true;
                                } else {
                                    sairCadastro = false;

                                    socket.close();
                                    entrada.close();
                                    saida.close();
                                }

                            }

                            break;
                        case "2":

                            while (sairConsulta) {
                                //Conecta o socket a porta do servidor
                                Socket socket = new Socket(ip, porta);
                                String nOpcao, cTermo = "",
                                        consultaRegistro = "";

                                //Cria os Stream's de entrada e saída
                                DataOutputStream saida = new DataOutputStream(socket.getOutputStream()); // Saída                
                                DataInputStream entrada = new DataInputStream(socket.getInputStream()); // Entrada

                                System.out.println("Digite 1 para consultar pelo Titulo ou 2 para consultar pelo Autor : \n");
                                nOpcao = ler.next();

                                if (nOpcao.equals("1")) {
                                    System.out.println("");
                                    System.out.println("Digite o Titulo do livro: \n");
                                    cTermo = ler.next();

                                } else if (nOpcao.equals("2")) {
                                    System.out.println("");
                                    System.out.println("Digite o nome do Autor: \n");
                                    cTermo = ler.next();

                                } else {
                                    System.out.println("Opção inválida");
                                    sairConsulta = false;
                                }
                                System.out.println("");

                                consultaRegistro = "2 " + nOpcao + " " + cTermo;

                                saida.writeUTF(consultaRegistro);//Envio a mensagem para o servidor

                                //recebo/Aguardo a resposta do server
                                String response = entrada.readUTF();

                                System.out.println("Resposta do servidor " + response);

                                System.out.println("Deseja consultar mais? S/N ");
                                mais = ler.next();

                                if (mais.toUpperCase().equals("S")) {
                                    sairConsulta = true;
                                } else {
                                    sairConsulta = false;

                                    socket.close();
                                    entrada.close();
                                    saida.close();
                                }
                            }
                            break;
                        case "3":

                            while (sairRemove) {
                                //Conecta o socket a porta do servidor
                                Socket socket = new Socket(ip, porta);
                                String nOpcao, cTermo = "",
                                        removeRegistro = "";

                                //Cria os Stream's de entrada e saída
                                DataOutputStream saida = new DataOutputStream(socket.getOutputStream()); // Saída                
                                DataInputStream entrada = new DataInputStream(socket.getInputStream()); // Entrada

                                System.out.println("Digite o ISBN do livro para remover: \n");
                                nOpcao = ler.next();

                                System.out.println("");

                                removeRegistro = nOpcao;

                                saida.writeUTF("3 " + removeRegistro);//Envio a mensagem para o servidor

                                //recebo/Aguardo a resposta do server
                                String response = entrada.readUTF();

                                System.out.println("Resposta do servidor " + response);

                                System.out.println("Deseja remover mais? S/N ");
                                mais = ler.next();

                                if (mais.toUpperCase().equals("S")) {
                                    sairRemove = true;
                                } else {
                                    sairRemove = false;

                                    socket.close();
                                    entrada.close();
                                    saida.close();
                                }
                            }
                            break;
                        default:

                            break;

                    }

                }
                System.out.println("Parar o programa? s/n: ");
                String nOpcaoPro = ler.next();

                if (nOpcaoPro.toUpperCase().equals("S")) {
                    startProgram = true;
                } else {
                    startProgram = false;
                }

            } catch (IOException ex) {
                Logger.getLogger(ClienteBruno.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClienteBruno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        ClienteBruno client = new ClienteBruno("localhost", 1238);
        client.onInit();
    }

}
