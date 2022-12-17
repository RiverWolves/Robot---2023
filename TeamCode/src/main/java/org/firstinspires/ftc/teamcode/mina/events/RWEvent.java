package org.firstinspires.ftc.teamcode.mina.events;

import org.firstinspires.ftc.teamcode.mina.RWConfig;
import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.drives.Drive;
import org.firstinspires.ftc.teamcode.mina.events.controller.ButonEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.ControllerEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.StickEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.TriggerEvent;
import org.firstinspires.ftc.teamcode.mina.events.opencv.AprilEvent;
import org.firstinspires.ftc.teamcode.mina.events.opencv.RecogEvent;
import org.firstinspires.ftc.teamcode.mina.utils.Telemetrie;

public abstract class RWEvent {

    public enum EventType{
        START,
        STOP,
        CONTROLLER,
        RECOG,
        APRIL
    }

    public EventType type;

    public RWEvent(EventType type){
        this.type = type;
    }

    public void execute(){
        if(!RWConfig.INCEPUT)
            return;
        if(RWConfig.DEBUG && eControllerEvent()){
            Telemetrie.addTel("c-event", getControllerEvent().getInfo());
        }
//        if(RWConfig.DEBUG && eRecog()){
//            Telemetrie.addTel("r-event", getRecogEvent().getInfo());
//        }
        if(RWConfig.DEBUG && eAprilEvent()){
            Telemetrie.addTel("a-event", getAprilEvent().getInfo());
        }
        for(Drive d : RWRobot.drives){
            d.onEvent(this);
        }
    }

    public boolean eStartEvent(){
        return type == EventType.START;
    }
    public boolean eStopEvent(){
        return type == EventType.STOP;
    }
    public boolean eControllerEvent(){
        return type == EventType.CONTROLLER;
    }
//    public boolean eRecog() {
//        return type == EventType.RECOG;
//    }
    public boolean eAprilEvent() {
        return type == EventType.APRIL;
    }

    public boolean eButonEvent() {
        return eControllerEvent() && getControllerEvent().eButonType();
    }
    public boolean eStickEvent() {
        return eControllerEvent() && getControllerEvent().eStickType();
    }
    public boolean eTriggerEvent() {
        return eControllerEvent() && getControllerEvent().eTriggerType();
    }

    public StartEvent getStartEvent(){
        return eStartEvent() ? (StartEvent)this : null;
    }
    public StopEvent getStopEvent(){
        return eStopEvent() ? (StopEvent)this: null;
    }
    public ControllerEvent getControllerEvent(){
        return eControllerEvent() ? (ControllerEvent)this : null;
    }

//    public RecogEvent getRecogEvent(){
//        return (RecogEvent) this;
//    }
    public AprilEvent getAprilEvent(){
        return eAprilEvent() ? (AprilEvent) this : null;
    }
    public ButonEvent getButonEvent(){
        return eButonEvent() ? (ButonEvent) this : null;
    }
    public StickEvent getStickEvent(){
        return eStickEvent() ? (StickEvent) this : null;
    }
    public TriggerEvent getTriggerEvent(){
        return eTriggerEvent() ? (TriggerEvent) this : null;
    }

}
