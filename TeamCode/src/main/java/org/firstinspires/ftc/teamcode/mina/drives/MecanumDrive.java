package org.firstinspires.ftc.teamcode.mina.drives;


import org.firstinspires.ftc.teamcode.mina.events.RWEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.ButonEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.ControllerEvent;

public class MecanumDrive extends Drive {

    public MecanumDrive(){
        super(DriveType.MECANUM);
    }


    @Override
    public void onInit() {

    }

    @Override
    public void onEvent(RWEvent event) {
        if(event.eController()){
            ControllerEvent controllerEvent = event.getControllerEvent();
            if(controllerEvent.eButon()){
                ButonEvent butonEvent = controllerEvent.getButonEvent();
                if(butonEvent.butonType == ButonEvent.ButonType.B){

                }
            }
        }
    }
}