package org.firstinspires.ftc.teamcode.mina.events.opencv;

import org.firstinspires.ftc.teamcode.mina.events.RWEvent;

public class AprilEvent extends RWEvent {
    private int id = -1;
    public AprilEvent(int id) {
        super(EventType.APRIL);
    }

    public int getId() {
        return id;
    }

    public AprilEvent setId(int id) {
        this.id = id;
        return this;
    }

    public String getInfo(){
        return Integer.toString(id);
    }
}
