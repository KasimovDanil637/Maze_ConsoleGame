package messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.ClientMessage;


public class ClientMessageWriter {
    public static String write(ClientMessage clientMessage){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode json = objectMapper.createObjectNode();
        json.put("command",clientMessage.getCommand());
        if (clientMessage.getClientName() != null && clientMessage.getDirection() == null){
            json.put("clientName",clientMessage.getClientName());
        }
        if (clientMessage.getDirection() != null){
            json.put("direction", clientMessage.getDirection());
        }
        if (clientMessage.getCommand().equals("stop")) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command",clientMessage.getCommand());
            return objectNode.toString();
        }
        return json.toString();
    }

}