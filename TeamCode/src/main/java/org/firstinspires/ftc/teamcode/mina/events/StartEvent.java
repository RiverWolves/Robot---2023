package org.firstinspires.ftc.teamcode.mina.events;

public class StartEvent extends RWEvent {

    public enum StartType{
        CONTROL,
        AUTONOMIE_ALBASTRU_DREAPTA,
        AUTONOMIE_ALBASTRU_STANGA,
        AUTONOMIE_ROSU_DREAPTA,
        AUTONOMIE_ROSU_STANGA,
    }

    public StartType startType;

    public StartEvent(StartType startType) {
        super(EventType.START);

        this.startType = startType;
    }
}
