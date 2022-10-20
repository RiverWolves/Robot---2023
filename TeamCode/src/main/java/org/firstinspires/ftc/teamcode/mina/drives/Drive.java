package org.firstinspires.ftc.teamcode.mina.drives;

import org.firstinspires.ftc.teamcode.mina.events.RWEvent;

public abstract class Drive {

    enum DriveType{
        MECANUM,
        NONMecanum,
    }

    public DriveType driveType;

    public Drive(DriveType driveType){
        this.driveType = driveType;
    }

    public abstract void onInit();

    public abstract void onEvent(RWEvent event);

}