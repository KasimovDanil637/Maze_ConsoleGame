package messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Rating;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RatingMessageWriter {
    private List<String> clients = new ArrayList<>();

    public void writeFile(Rating rating) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("name", rating.getName());
        objectNode.put("steps", rating.getSteps());
        objectNode.put("min", rating.getMin());
        try (FileWriter fileWriter = new FileWriter("rating.json", true)) {
            fileWriter.write(objectNode + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readFile(){
        String message = "";
        try(FileReader reader = new FileReader("rating.json")) {
            int c;
            while((c = reader.read()) != -1) {
                if (c != '\n') {
                    message += (char) c;
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        List<String> list = new ArrayList<>();
        list.add(message);
        return Arrays.toString(list.toArray());
    }
}
