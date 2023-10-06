package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 50002);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = br2.readLine()) != null) {
                pw.println(userInput);
                String message = br1.readLine();
                System.out.println("Server: " + message);
                if (userInput.equals("stop")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
