
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import maze.Maze;
import messages.ClientMessageWriter;
import messages.RatingMessageWriter;
import messages.ServerMessageWriter;
import models.ClientMessage;
import models.Rating;

public class Game implements Runnable {
    private Socket clientSocket;
    private int id;
    Maze maze = new Maze();
    Rating rating = new Rating();
    RatingMessageWriter ratingMessageWriter = new RatingMessageWriter();
    ClientMessage clientMessage = new ClientMessage();

    public Game(Socket socket, int id) {
        this.clientSocket = socket;
        this.id = id;
    }

    @Override
    public void run() {
        try (PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String inputLine;
            maze.rangomMaze(randomNum());
            int[] startPoint = maze.getStartPoint();
            int min = maze.getMin();
            boolean flag = false;
            int stepCount = 0;
            while ((inputLine = br.readLine()) != null) {
                String[] array = inputLine.split(" ");
                String command = array[0];
                String message = array.length == 2 ? array[1] : null;
                clientMessage.setCommand(command);
                switch (command){
                    case "start":
                        flag = true;
                        clientMessage.setClientName(message);
                        System.out.println(ClientMessageWriter.write(clientMessage));
                        pw.println(ServerMessageWriter.write(clientMessage, startPoint, 1, ratingMessageWriter.readFile(), stepCount, min));
                        break;
                    case "direction":
                        if(flag){
                            stepCount++;
                            clientMessage.setDirection(message);
                            System.out.println(ClientMessageWriter.write(clientMessage));
                            int point = maze.getPoint(message);
                            if (point == 2){
                                setRating(clientMessage.getClientName(),stepCount,min);
                            }
                            pw.println(ServerMessageWriter.write(clientMessage, startPoint,point, ratingMessageWriter.readFile(),stepCount,min));
                        }
                        break;
                    case "stop":
                        if(flag){
                            setRating(clientMessage.getClientName(),stepCount,min);
                            System.out.println(ClientMessageWriter.write(clientMessage));
                            pw.println(ServerMessageWriter.write(clientMessage, startPoint, 1, ratingMessageWriter.readFile(),stepCount,min));
                        }
                    default: pw.println("Неверный формат");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setRating(String name,int steps, int min) throws JsonProcessingException {
        rating.setName(name);
        rating.setSteps(steps);
        rating.setMin(min);
        ratingMessageWriter.writeFile(rating);
    }
    public int randomNum(){
        Random random = new Random();
        int r = random.nextInt(6);
        System.out.println(r);
        return r;
    }
}



