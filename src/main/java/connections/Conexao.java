package connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class Conexao {

    public static void envia(Socket socket, String texto){
        OutputStream out;
        try{
            out = socket.getOutputStream();
            out.write(texto.getBytes());
        }
        catch(Exception e){
            System.err.println("Erro no OutputStream: " + e.getMessage());
        }
    }

    public static String recebe(Socket socket){
        InputStream in;
        int qntBytes;
        byte bytesTxt[] = new byte[1000];
        String txt = "";
        try{
            in = socket.getInputStream();
            qntBytes = in.read(bytesTxt);
            if(qntBytes > 0){
                txt = new String(bytesTxt);
            }
        } catch (IOException e){
            System.err.println("Erro no InputStream: " + e.getMessage());
        }
        return txt;
    }
}
