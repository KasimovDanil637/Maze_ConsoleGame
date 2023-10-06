package messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.ClientMessage;
import models.ServerMessage;
import org.jfree.data.json.impl.JSONObject;

import java.util.Arrays;

public class ServerMessageWriter {
    public static String write(ClientMessage clientMessage, int[] startPoint, int result, String rating, int stepCount, int min) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode json = objectMapper.createObjectNode();
        json.put("status", clientMessage.getCommand());
        if (clientMessage.getClientName() != null && clientMessage.getDirection() == null) {
            json.put("message", "Привет," + clientMessage.getClientName());
            json.put("startPoint", Arrays.toString(startPoint));
            json.put("rating", "[]");
        }
        if (clientMessage.getDirection() != null) {
            json.put("result", result);
        }
        if (result == 2){
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("status", "stop");
            objectNode.put("result", stepCount);
            objectNode.put("min", min);
            objectNode.put("rating", rating);
            return objectNode.toString();
        }
        if (clientMessage.getCommand().equals("stop")) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("status", clientMessage.getCommand());
            objectNode.put("rating", rating);
            return objectNode.toString();
        }
        return json.toString();
    }
}