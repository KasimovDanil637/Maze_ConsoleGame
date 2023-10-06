package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientMessage {
    private String command;
    private String clientName;
    private String direction;
}
