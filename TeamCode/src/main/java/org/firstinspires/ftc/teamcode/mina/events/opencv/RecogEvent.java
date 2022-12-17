package org.firstinspires.ftc.teamcode.mina.events.opencv;

import org.firstinspires.ftc.teamcode.mina.events.RWEvent;

import java.util.List;

public abstract class RecogEvent extends RWEvent {

    //las clasa asta aici in caz ca va mai fi nevoie de ea in viitor

    public enum RecogType{
        CON_RECOG,
        TAG_RECOG;
    }

    private RecogType recogType;
    private boolean eOk = false;

    public RecogEvent(RecogType type) {
        super(EventType.RECOG);
    }

    public RecogType getRecogType() {
        return recogType;
    }

    public boolean eOk() {
        return eOk;
    }

    public void seteOk(boolean eOk) {
        this.eOk = eOk;
    }

    public abstract List<String> getInfo();
}
