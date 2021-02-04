package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BarrelEvent {

    private LocalDateTime time;
    private String username;
    private String action;
    private long liters;

    private boolean success;

    // some bug, lombok doesn't generate it
    public boolean getSuccess(){
        return success;
    }
}
