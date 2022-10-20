package org.firstinspires.ftc.teamcode.mina.events;

import org.firstinspires.ftc.teamcode.mina.Robot;
import org.firstinspires.ftc.teamcode.mina.drives.Drive;
import org.firstinspires.ftc.teamcode.mina.events.controller.ControllerEvent;

public abstract class RWEvent {

    public enum EventType{
        START,
        STOP,
        CONTROLLER

    }

    public EventType type;

    public RWEvent(EventType type){
        this.type = type;
    }

    public void execute(){
        for(Drive d : Robot.drives){
            d.onEvent(this);
        }
    }

    public boolean eStart(){
        return type == EventType.START;
    }

    public boolean eStop(){
        return type == EventType.STOP;
    }

    public boolean eController(){
        return type == EventType.CONTROLLER;
    }

    public ControllerEvent getControllerEvent(){
        return (ControllerEvent) this;
    }

}
