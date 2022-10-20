package org.firstinspires.ftc.teamcode.mina.events.controller;

public class StickEvent extends ControllerEvent {

    public enum StickType{
        STANGA,DREAPTA
    }

    public StickType stickType;
    public float x,y;
    public StickEvent(Controller controller, StickType stickType, float x, float y){
        super(ControllerEventType.STICK,controller);
        this.stickType = stickType;
        this.x = x;
        this.y = y;
    }

    public StickEvent set(Controller controller, StickType stickType, float x, float y){
        this.controller = controller;
        this.stickType = stickType;
        this.x = x;
        this.y = y;

        return this;
    }

    public String getInfo(){
        return stickType.toString()+" - "+x+" - "+y;
    }

}