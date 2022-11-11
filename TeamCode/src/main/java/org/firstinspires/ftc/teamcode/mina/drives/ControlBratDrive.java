package org.firstinspires.ftc.teamcode.mina.drives;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mina.RWConfig;
import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.RWEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.ControllerEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.StickEvent;

public class ControlBratDrive extends Drive{

    public ControlBratDrive(){super(DriveType.BRAT); }

    public static float y;

    @Override
    public void onInit() {
        RWRobot.bratDrive.init();
    }

    @Override
    public void onEvent(RWEvent event) {
        if(event.eController()){
            ControllerEvent controllerEvent = event.getControllerEvent();
            if(controllerEvent.eController1() && controllerEvent.eStick()){
                StickEvent stickEvent = controllerEvent.getStickEvent();
                if(stickEvent.eSTANGA()) {
                    y = stickEvent.y;
                }
                RWRobot.bratDrive.brat(y);
            }
        }
    }
}
