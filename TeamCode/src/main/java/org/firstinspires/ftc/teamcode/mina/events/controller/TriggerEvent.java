package org.firstinspires.ftc.teamcode.mina.events.controller;

public class TriggerEvent extends ControllerEvent {

    public enum TriggerType{
        STANGA_TRIGGER, DREAPTA_TRIGGER
    }

    public TriggerType triggerType;
    public float v;

    public TriggerEvent(Controller controller, TriggerType tip, float v){
        super(ControllerEventType.TRIGGER,controller);
        this.triggerType = tip;
        this.v = v;
    }

    public TriggerEvent set(Controller controller, TriggerType tip, float v){
        this.controller = controller;
        this.triggerType = tip;
        this.v = v;

        return this;
    }

    public String getInfo(){
        return triggerType.toString()+" - "+v;
    }

}