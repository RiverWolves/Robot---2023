package org.firstinspires.ftc.teamcode.mina.events;

import org.firstinspires.ftc.teamcode.mina.RWConfig;
import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.drives.Drive;
import org.firstinspires.ftc.teamcode.mina.events.controller.ControllerEvent;
import org.firstinspires.ftc.teamcode.mina.events.opencv.RecogEvent;
import org.firstinspires.ftc.teamcode.mina.utils.Telemetrie;

public abstract class
RWEvent {

    public enum EventType{
        START,
        STOP,
        CONTROLLER,
        RECOG,
    }

    public EventType type;

    public RWEvent(EventType type){
        this.type = type;
    }

    public void execute(){
        if(!RWConfig.INCEPUT)
            return;
        if(RWConfig.DEBUG && eController()){
            Telemetrie.addTel("c-event", getControllerEvent().getInfo());
        }
        if(RWConfig.DEBUG && eRecog()){
            Telemetrie.addTel("r-event", getRecogEvent().getInfo());
        }
        for(Drive d : RWRobot.drives){
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

    public boolean eRecog() {
        return type == EventType.RECOG;
    }

    public ControllerEvent getControllerEvent(){
        return (ControllerEvent) this;
    }
    public RecogEvent getRecogEvent(){
        return (RecogEvent) this;
    }

}
