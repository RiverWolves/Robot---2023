package org.firstinspires.ftc.teamcode.mina.events.controller;

import org.firstinspires.ftc.teamcode.mina.events.RWEvent;

public abstract class ControllerEvent extends RWEvent {

    public enum ControllerEventType{
        BUTON,
        STICK,
        TRIGGER
    }
    public enum Controller{
        CONTROLLER1,CONTROLLER2
    }

    public ControllerEventType controllereventtype;
    public Controller controller;

    public ControllerEvent(ControllerEventType controllereventtype, Controller controller){
        super(EventType.CONTROLLER);
        this.controllereventtype = controllereventtype;
        this.controller = controller;
    }

    public abstract String getInfo();

    public boolean eButonType(){
        return controllereventtype == ControllerEventType.BUTON;
    }
    public boolean eStickType(){
        return controllereventtype == ControllerEventType.STICK;
    }
    public boolean eTriggerType(){
        return controllereventtype == ControllerEventType.TRIGGER;
    }


    public boolean eController1(){
        return controller == Controller.CONTROLLER1;
    }
    public boolean eController2(){
        return controller == Controller.CONTROLLER2;
    }

}