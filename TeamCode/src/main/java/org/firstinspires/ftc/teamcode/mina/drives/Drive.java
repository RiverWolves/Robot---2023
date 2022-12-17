package org.firstinspires.ftc.teamcode.mina.drives;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.RWEvent;

public abstract class Drive {

    //Adauga numele noului tau drive
    enum DriveType{
        MECANUM,
        //AICI ADAUGI ALT NUME
    }

    public DriveType driveType;

    public Drive(DriveType driveType){
        this.driveType = driveType;
    }

    public abstract void onInit();

    public abstract void onEvent(RWEvent event);

    public SampleMecanumDrive getMecanum(){
        return RWRobot.mecanumDrive;
    }

}