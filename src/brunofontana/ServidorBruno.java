package brunofontana;

import java.io.*;
import static java.lang.Integer.parseInt;
import java.lang.reflect.Array;
import java.net.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author bruno
 */
public class ServidorBruno {

    private int porta = 0;
    IntBruno obj;

    //construtor
    public ServidorBruno(int porta) {
        this.porta = porta;

        obj = new ImpBruno();

        init();
    }

    public static void main(String[] args) {
        ServidorBruno server = new ServidorBruno(1238);

        server.init();
    }

    private void init() {

        try {

            ServerSocket conexao = new ServerSocket(porta);

            System.out.println("Servidor TCP operando na porta " + porta);

            boolean exit = false;
            do {

                //Aguardo conexão do client
                Socket socket = conexao.accept();

                //Crio / Obtenho os stream's de entrada e saída                
                DataInputStream entrada = new DataInputStream(socket.getInputStream()); //Entrada

                DataOutputStream saida = new DataOutputStream(socket.getOutputStream()); //Saída

                //Realizo a comunicação conforme protocolo
                String data = entrada.readUTF(); //recebe a requisição         
                System.out.println("Recebeu do cliente " + data);

                //Estou com problemas aqui, se digitar um autor ou assunto ou titulo com 2 palavras ja ferra tudo, por causa do " " espaço em braco... como faço split...
                String registro[] = data.split(" ");
                String operacao = registro[0];
                String resultado = "";
                //registro[0] = operacao
                //registro[1] = isbn
                //registro[2] = titulo
                //registro[3] = autor
                //registro[4] = assunto               
                switch (operacao) {

                    case "1":
                        resultado = obj.cadastrar(parseInt(registro[1]), registro[2], registro[3], registro[4]);
                        break;
                    case "2":
                        resultado = obj.consultar(parseInt(registro[1]), registro[2]);
                        break;
                    case "3":
                        resultado = obj.remover(parseInt(registro[1]));
                        break;
                    default:
                        break;

                }                   

                //Grava de volta no Stream
                saida.writeUTF(resultado);

                //Fecho as conexões que foram abertas
                saida.close();
                entrada.close();
                socket.close();

            } while (!exit);

            // Fecho o socker servidor
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorBruno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
