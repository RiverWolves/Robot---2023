package org.firstinspires.ftc.teamcode.mina.drives;


import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.RWEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.ControllerEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.StickEvent;

public class ControlMecanumDrive extends Drive {

    public float x, y, r;

    public ControlMecanumDrive(){
        super(DriveType.MECANUM);
    }


    @Override
    public void onInit() {
        x = 0;
        y = 0;
        r = 0;
    }

    @Override
    public void onEvent(RWEvent event) {
        if(event.eController()){
            ControllerEvent controllerEvent = event.getControllerEvent();
            if(controllerEvent.eController1() && controllerEvent.eStick()){
                StickEvent stickEvent = controllerEvent.getStickEvent();
                if(stickEvent.eSTANGA()){
                    x = stickEvent.x;
                    y = stickEvent.y;
                }else{
                    r = stickEvent.x;
                }
                RWRobot.mecanumDrive.setWeightedDrivePower(new Pose2d(-x, -y, -r));
            }
        }
    }
}