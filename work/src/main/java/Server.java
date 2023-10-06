import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 50002;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int id = 1;
        File file = new File("rating.json");
        file.delete();
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started");
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                Game game = new Game(clientSocket, id++);
                Thread thread = new Thread(game);
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
