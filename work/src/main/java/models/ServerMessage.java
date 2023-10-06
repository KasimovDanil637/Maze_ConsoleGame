package models;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServerMessage {
    private String status;
    private String message;
    private int[] startPoint;
    private String result;
    private String raiting;
    private String min;
}