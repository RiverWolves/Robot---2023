package org.firstinspires.ftc.teamcode.mina;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.mina.drives.Drive;
import org.firstinspires.ftc.teamcode.mina.events.StartEvent;
import org.firstinspires.ftc.teamcode.mina.events.StopEvent;
import org.firstinspires.ftc.teamcode.mina.listeners.ControllerListener;

import java.util.ArrayList;
import java.util.List;

public class Robot {

    public static OpMode opMode;
    public static List<Drive> drives = new ArrayList<>();
    public static SampleMecanumDrive mecanumDrive = null;

    public static StartEvent.StartType startType;

    public static void init(OpMode mode, StartEvent.StartType startType1){
        opMode = mode;
        startType = startType1;

        RWConfig.init();

        mecanumDrive = new SampleMecanumDrive(opMode.hardwareMap);

        for(Drive d : drives){
            d.onInit();
        }
    }

    public static void start(){
        new StartEvent(startType).execute();
    }

    public static void stop(){
        new StopEvent().execute();
    }

    public static void update(){
        ControllerListener.update();
    }


}
